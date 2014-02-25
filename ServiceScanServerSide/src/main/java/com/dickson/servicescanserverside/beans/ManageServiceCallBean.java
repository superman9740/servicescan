/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import com.dickson.servicescanserverside.Contractor;
import com.dickson.servicescanserverside.Customer;
import com.dickson.servicescanserverside.Qrcode;
import com.dickson.servicescanserverside.Scan;
import com.dickson.servicescanserverside.ServiceCall;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author sdickson
 */
@ManagedBean
@RequestScoped
public class ManageServiceCallBean {

    
    private List<ServiceCallManagedBean> serviceCalls;
    private List<ServiceCallManagedBean> filteredServiceCalls;
    
    
    /**
     * Creates a new instance of ManageServiceCallBean
     */
    public ManageServiceCallBean() {
        
         this.serviceCalls = new ArrayList<ServiceCallManagedBean>();
        System.out.println("ManageServiceCallBean - constructor");
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
        TypedQuery<ServiceCall> query = em.createNamedQuery("ServiceCall.findAll", ServiceCall.class);
        List<ServiceCall> tempScans = query.getResultList();
        for(int x = 0;x < tempScans.size();x++)
         {
                ServiceCall tempObject = tempScans.get(x);
                ServiceCallManagedBean serviceCall = new ServiceCallManagedBean();
                serviceCall.setRowID(tempObject.getRowid());
                
                //Get customer info
            Query query2 = em.createNamedQuery("Customer.findByRowid");
            query2.setParameter("rowid", tempObject.getQrCodeID().getCustomerId().getRowid());
            Customer customer = (Customer)query2.getSingleResult();
            serviceCall.setCustomerAddress(customer.getAddress());
            serviceCall.setCustomerFirstName(customer.getFirstName());
            serviceCall.setCustomerLastName(customer.getLastName());
            serviceCall.setServiceCallNotes(tempObject.getNotes());
                
            this.serviceCalls.add(serviceCall);
                
                
                
            }
    
    
    
    }
    
    
    public void insertRow()
    {
          ServiceCallManagedBean record = new ServiceCallManagedBean();
          record.setRowID(new Long(-1));
          
          record.setCustomerAddress(" ");
          record.setCustomerFirstName(" ");
          
          record.setCustomerLastName(" ");
          
          this.getServiceCalls().add(record);
          
        
    }

    /**
     * @return the serviceCalls
     */
    public List<ServiceCallManagedBean> getServiceCalls() {
        return serviceCalls;
    }

    /**
     * @param serviceCalls the serviceCalls to set
     */
    public void setServiceCalls(List<ServiceCallManagedBean> serviceCalls) {
        this.serviceCalls = serviceCalls;
    }

    /**
     * @return the filteredServiceCalls
     */
    public List<ServiceCallManagedBean> getFilteredServiceCalls() {
        return filteredServiceCalls;
    }

    /**
     * @param filteredServiceCalls the filteredServiceCalls to set
     */
    public void setFilteredServiceCalls(List<ServiceCallManagedBean> filteredServiceCalls) {
        this.filteredServiceCalls = filteredServiceCalls;
    }

}
