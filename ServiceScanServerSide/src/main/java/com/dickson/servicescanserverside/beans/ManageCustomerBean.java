/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import com.dickson.servicescanserverside.Contractor;
import com.dickson.servicescanserverside.Customer;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class ManageCustomerBean {

    /**
     * Creates a new instance of ManageCustomerBean
     */
  
    
    
     private List<CustomerManagedBean> customers;
    public void onEdit(RowEditEvent event)
    {
        
        CustomerManagedBean newValue = (CustomerManagedBean) event.getObject();  
        if(newValue.getRowid() == -1)
        {
        
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Customer record = new Customer();
            record.setFirstName(newValue.getFirstName());
            record.setLastName(newValue.getLastName());
            record.setAddress(newValue.getAddress());
            record.setCity(newValue.getCity());
            record.setState(newValue.getState());
            record.setZip(newValue.getZip());
            record.setPhone(newValue.getPhone());
            em.persist(record);
            
            em.getTransaction().commit();

            System.out.println("Customer record has been inserted.");

        
        }
        else
        {
        
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
                EntityManager em = emf.createEntityManager();
                TypedQuery<Customer> query = em.createNamedQuery("Customer.findByRowid", Customer.class);
                query.setParameter("rowid",newValue.getRowid());

                Customer record = query.getSingleResult();
                em.getTransaction().begin();
                record.setFirstName(newValue.getFirstName());
                record.setLastName(newValue.getLastName());
                record.setAddress(newValue.getAddress());
                record.setCity(newValue.getCity());
                record.setState(newValue.getState());
                record.setZip(newValue.getZip());
                record.setPhone(newValue.getPhone());
                em.merge(record);
                
                em.getTransaction().commit();

                System.out.println("Customer record has been updated.");

            
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
          CustomerManagedBean customer = new CustomerManagedBean();
          customer.setRowid(new Long(-1));
          
          customer.setFirstName(" ");
          customer.setLastName(" ");
          customer.setAddress(" ");
          customer.setCity(" ");
          customer.setState(" ");
          customer.setZip(" ");
          customer.setPhone(" ");
          //customer.setEmail(" ");
          this.customers.add(customer);
          
        
    }
    public ManageCustomerBean()
    {
        this.customers = new ArrayList<CustomerManagedBean>();
        System.out.println("CustomerManagementBean - constructor");
        
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
        emf.getCache().evictAll();
        EntityManager em = emf.createEntityManager();
          TypedQuery<Customer> query = em.createNamedQuery("Customer.findAll", Customer.class);
            
            List<Customer> tempCustomers = query.getResultList();
            
            for(int x = 0;x < tempCustomers.size();x++)
            {
                Customer tempObject = tempCustomers.get(x);
                CustomerManagedBean customer = new CustomerManagedBean();
                customer.setRowid(tempObject.getRowid());
                
                customer.setFirstName(tempObject.getFirstName());
                customer.setLastName(tempObject.getLastName());
                customer.setAddress(tempObject.getAddress());
                customer.setCity(tempObject.getCity());
                customer.setState(tempObject.getState());
                customer.setZip(tempObject.getZip());
                customer.setPhone(tempObject.getPhone());
                
                customers.add(customer);
                
                
            }
    
    
    
        
    
    }

    /**
     * @return the customers
     */
    public List<CustomerManagedBean> getCustomers() {
        return customers;
    }

    /**
     * @param customers the customers to set
     */
    public void setCustomers(List<CustomerManagedBean> customers) {
        this.customers = customers;
    }
}
