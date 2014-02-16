/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import com.dickson.servicescanserverside.Contractor;
import com.dickson.servicescanserverside.Scan;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author USMEM-W-003157
 */
@ManagedBean
@ViewScoped
public class ManageApplianceRecordBean {

    
    private List<ApplianceRecordManagedBean> applianceRecords;
    private List<ApplianceRecordManagedBean> filteredApplianceRecords;
    
    
    
    
    /**
     * Creates a new instance of ManageApplianceRecordBean
     */
    
    
    public ManageApplianceRecordBean() {
    
     this.applianceRecords = new ArrayList<ApplianceRecordManagedBean>();
        System.out.println("ManageApplianceRecordBean - constructor");
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
        TypedQuery<Scan> query = em.createNamedQuery("Scan.findAll", Scan.class);
        List<Scan> tempScans = query.getResultList();
        for(int x = 0;x < tempScans.size();x++)
         {
                Scan tempObject = tempScans.get(x);
                ApplianceRecordManagedBean applianceRecord = new ApplianceRecordManagedBean();
                applianceRecord.setRowid(tempObject.getRowid());
                
                applianceRecord.setApplianceModel(tempObject.getApplianceModel());
                applianceRecord.setApplianceSerial(tempObject.getApplianceSerial());
                applianceRecord.setApplianceType(tempObject.getApplianceType());
                applianceRecord.setDeviceToken(tempObject.getDeviceToken());
                
                this.applianceRecords.add(applianceRecord);
                
                
                
            }
    
    
    
        
    
    }
    
    
    
    
     public void onEdit(RowEditEvent event)
    {
        /*
        ApplianceRecordManagedBean newValue = (ApplianceRecordManagedBean) event.getObject();  
        if(newValue.getRowid() == -1)
        {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Scan newRecord = new Scan();
            newRecord.setApplianceModel(newValue.getApplianceModel());
            newRecord.setApplianceSerial(newValue.getApplianceSerial());
            newRecord.setApplianceType(newValue.getApplianceType());
            newRecord.setDeviceToken(newValue.getDeviceToken());
            em.persist(newRecord);
            em.getTransaction().commit();
        
            System.out.println("Scan record has been inserted.");

            
        }
        else
        {
         
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            TypedQuery<Scan> query = em.createNamedQuery("Scan.findByRowid", Scan.class);
            query.setParameter("rowid",newValue.getRowid());

            Scan record = query.getSingleResult();
            em.getTransaction().begin();
            if(record != null)
            {

                record.setApplianceModel(newValue.getApplianceModel());
                record.setApplianceSerial(newValue.getApplianceSerial());
                record.setApplianceType(newValue.getApplianceType());
                record.setDeviceToken(newValue.getDeviceToken());
          
                em.merge(record);
                System.out.println("Scan record has been updated.");

            }

                em.getTransaction().commit();
        
            }    

        */
        
    }
    public void onCancel()
    {
        
        
    }
    
    public void refreshData()
    {
        
          
        
    }
    public void insertRow()
    {
          ApplianceRecordManagedBean record = new ApplianceRecordManagedBean();
          record.setRowid(new Long(-1));
          
          record.setApplianceModel(" ");
          record.setApplianceSerial(" ");
          record.setApplianceType(" ");
          this.getApplianceRecords().add(record);
          
        
    }

    /**
     * @return the applianceRecords
     */
    public List<ApplianceRecordManagedBean> getApplianceRecords() {
        return applianceRecords;
    }

    /**
     * @param applianceRecords the applianceRecords to set
     */
    public void setApplianceRecords(List<ApplianceRecordManagedBean> applianceRecords) {
        this.applianceRecords = applianceRecords;
    }

    /**
     * @return the filteredApplianceRecords
     */
    public List<ApplianceRecordManagedBean> getFilteredApplianceRecords() {
        return filteredApplianceRecords;
    }

    /**
     * @param filteredApplianceRecords the filteredApplianceRecords to set
     */
    public void setFilteredApplianceRecords(List<ApplianceRecordManagedBean> filteredApplianceRecords) {
        this.filteredApplianceRecords = filteredApplianceRecords;
    }
}
