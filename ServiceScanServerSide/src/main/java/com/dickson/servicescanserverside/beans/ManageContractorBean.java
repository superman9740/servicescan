/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import com.dickson.servicescanserverside.Contractor;
import com.dickson.servicescanserverside.Scan;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.primefaces.model.SelectableDataModel;

/**
 *
 * @author USMEM-W-003157
 */
@ManagedBean(name="manageContractorBean")
@RequestScoped
public class ManageContractorBean implements Serializable {

    /**
     * Creates a new instance of ManageContractorBean
     */
     private List<ContractorManagedBean> contractors;
     private Contractor selectedContractor;
     public void deleteRow()
     {
         
           EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<Contractor> query = em.createNamedQuery("Contractor.findByRowid", Contractor.class);
            query.setParameter("rowid",selectedContractor.getRowid());

            Contractor record = query.getSingleResult();
            em.getTransaction().begin();
            if(record != null)
            {

                em.remove(record);
                em.getTransaction().commit();
          
            }

               
     }
    public void onEdit(RowEditEvent event)
    {
        ContractorManagedBean newValue = (ContractorManagedBean) event.getObject();  
        if(newValue.getRowID() == -1)
        {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Contractor newRecord = new Contractor();
            newRecord.setFirstName(newValue.getFirstName());
            newRecord.setLastName(newValue.getLastName());
            newRecord.setAddress(newValue.getAddress());
            newRecord.setCity(newValue.getCity());
            newRecord.setState(newValue.getState());
            newRecord.setZip(newValue.getZip());
            newRecord.setPhone(newValue.getPhone());
            newRecord.setEmail(newValue.getEmail());
            em.persist(newRecord);
            em.getTransaction().commit();
        
            System.out.println("Contractor record has been inserted.");

            
        }
        else
        {
         
             EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<Contractor> query = em.createNamedQuery("Contractor.findByRowid", Contractor.class);
            query.setParameter("rowid",newValue.getRowID());

            Contractor record = query.getSingleResult();
            em.getTransaction().begin();
            if(record != null)
            {

                record.setFirstName(newValue.getFirstName());
                record.setLastName(newValue.getLastName());
                record.setAddress(newValue.getAddress());
                record.setCity(newValue.getCity());
                record.setState(newValue.getState());
                record.setZip(newValue.getZip());
                record.setPhone(newValue.getPhone());
                record.setEmail(newValue.getEmail());
                em.merge(record);
                System.out.println("Contractor record has been updated.");

            }

                em.getTransaction().commit();
        
            }    

        
    }
    public void onCancel()
    {
        
        
    }
    
    public void refreshData()
    {
        
          
        
    }
    public void insertRow()
    {
          ContractorManagedBean contractor = new ContractorManagedBean();
          contractor.setRowID(new Long(-1));
          
          contractor.setFirstName(" ");
          contractor.setLastName(" ");
          contractor.setAddress(" ");
          contractor.setCity(" ");
          contractor.setState(" ");
          contractor.setZip(" ");
          contractor.setPhone(" ");
          contractor.setEmail(" ");
          this.contractors.add(contractor);
          
        
    }
    public ManageContractorBean()
    {
        this.contractors = new ArrayList<ContractorManagedBean>();
        System.out.println("ContractorManagementBean - constructor");
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
          TypedQuery<Contractor> query = em.createNamedQuery("Contractor.findAll", Contractor.class);
            
            List<Contractor> tempContractors = query.getResultList();
            
            for(int x = 0;x < tempContractors.size();x++)
            {
                Contractor tempObject = tempContractors.get(x);
                ContractorManagedBean contractor = new ContractorManagedBean();
                contractor.setRowID(tempObject.getRowid());
                
                contractor.setFirstName(tempObject.getFirstName());
                contractor.setLastName(tempObject.getLastName());
                contractor.setAddress(tempObject.getAddress());
                contractor.setCity(tempObject.getCity());
                contractor.setState(tempObject.getState());
                contractor.setZip(tempObject.getZip());
                contractor.setPhone(tempObject.getPhone());
                contractor.setEmail(tempObject.getEmail());
                
                contractors.add(contractor);
                
                
            }
    
    
    
        
    
    }
    
    public List<ContractorManagedBean> getContractors() {
        
        
        return contractors;
    }

    /**
     * @param contractors the contractors to set
     */
    public void setContractors(List<ContractorManagedBean> contractors) {
        this.contractors = contractors;
    }

    /**
     * @return the selectedContractor
     */
    public Contractor getSelectedContractor() {
        return selectedContractor;
    }

    /**
     * @param selectedContractor the selectedContractor to set
     */
    public void setSelectedContractor(Contractor selectedContractor) {
        this.selectedContractor = selectedContractor;
    }

    
    
    
}
