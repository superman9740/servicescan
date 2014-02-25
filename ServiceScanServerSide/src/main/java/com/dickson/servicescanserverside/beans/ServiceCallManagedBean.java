/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 *//*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author sdickson
 */
@ManagedBean
@SessionScoped
public class ServiceCallManagedBean {

    private Long rowID;
    
    private String customerFirstName;
    private String customerLastName;
    private String customerAddress;
    private String serviceCallNotes;
    
    /**
     * Creates a new instance of ServiceCallMangedBean
     */
    public ServiceCallManagedBean() {
    }

    /**
     * @return the customerFirstName
     */
    public String getCustomerFirstName() {
        return customerFirstName;
    }

    /**
     * @param customerFirstName the customerFirstName to set
     */
    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    /**
     * @return the customerLastName
     */
    public String getCustomerLastName() {
        return customerLastName;
    }

    /**
     * @param customerLastName the customerLastName to set
     */
    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    /**
     * @return the customerAddress
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress the customerAddress to set
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return the serviceCallNotes
     */
    public String getServiceCallNotes() {
        return serviceCallNotes;
    }

    /**
     * @param serviceCallNotes the serviceCallNotes to set
     */
    public void setServiceCallNotes(String serviceCallNotes) {
        this.serviceCallNotes = serviceCallNotes;
    }

    /**
     * @return the rowID
     */
    public Long getRowID() {
        return rowID;
    }

    /**
     * @param rowID the rowID to set
     */
    public void setRowID(Long rowID) {
        this.rowID = rowID;
    }
    
}
