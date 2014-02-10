/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import com.dickson.servicescanserverside.Contractor;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

/**
 *
 * @author USMEM-W-003157
 */
@ManagedBean(name="manageContractorBean")
@SessionScoped
public class ManageContractorBean implements Serializable {

    /**
     * Creates a new instance of ManageContractorBean
     */
     private List<ContractorManagedBean> contractors;
    public void onEdit()
    {
        
        
    }
    public void onCancel()
    {
        
        
    }
    
    public ManageContractorBean()
    {
    
        
    
        System.out.println("ContractorManagementBean - constructor");
        contractors = new ArrayList<ContractorManagedBean>();
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
          EntityManager em = emf.createEntityManager();
          TypedQuery<Contractor> query = em.createNamedQuery("Contractor.findAll", Contractor.class);
            
            List<Contractor> tempContractors = query.getResultList();
            
            for(int x = 0;x < tempContractors.size();x++)
            {
                Contractor tempObject = tempContractors.get(x);
                ContractorManagedBean contractor = new ContractorManagedBean();
                contractor.setFirstName(tempObject.getFirstName());
                contractor.setLastName(tempObject.getLastName());
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
    
    
    
}
