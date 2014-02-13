/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author USMEM-W-003157
 */
@ManagedBean
@ViewScoped
public class ApplianceRecordManagedBean {

    private Long rowid;
    private String applianceModel;
    private String applianceSerial;
    private String applianceType;
    private String deviceToken;
    private CustomerManagedBean customer;
    private ContractorManagedBean contractor;
    
    /**
     * Creates a new instance of ApplianceRecordManagedBean
     */
    public ApplianceRecordManagedBean() {
    }

    /**
     * @return the applianceModel
     */
    public String getApplianceModel() {
        return applianceModel;
    }

    /**
     * @param applianceModel the applianceModel to set
     */
    public void setApplianceModel(String applianceModel) {
        this.applianceModel = applianceModel;
    }

    /**
     * @return the applianceSerial
     */
    public String getApplianceSerial() {
        return applianceSerial;
    }

    /**
     * @param applianceSerial the applianceSerial to set
     */
    public void setApplianceSerial(String applianceSerial) {
        this.applianceSerial = applianceSerial;
    }

    /**
     * @return the applianceType
     */
    public String getApplianceType() {
        return applianceType;
    }

    /**
     * @param applianceType the applianceType to set
     */
    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
    }

    /**
     * @return the customer
     */
    public CustomerManagedBean getCustomer() {
        return customer;
    }

    /**
     * @param customer the customer to set
     */
    public void setCustomer(CustomerManagedBean customer) {
        this.customer = customer;
    }

    /**
     * @return the contractor
     */
    public ContractorManagedBean getContractor() {
        return contractor;
    }

    /**
     * @param contractor the contractor to set
     */
    public void setContractor(ContractorManagedBean contractor) {
        this.contractor = contractor;
    }

    /**
     * @return the rowid
     */
    public Long getRowid() {
        return rowid;
    }

    /**
     * @param rowid the rowid to set
     */
    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    /**
     * @return the deviceToken
     */
    public String getDeviceToken() {
        return deviceToken;
    }

    /**
     * @param deviceToken the deviceToken to set
     */
    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
    
}
