/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author USMEM-W-003157
 */
@ManagedBean
@ViewScoped
public class ServiceRequestManagedBean {

    /**
     * Creates a new instance of ServiceRequestManagedBean
     */
    
    private Long rowid;
    private String qrcode;
    private String contractorFirstName;
    private String contractorLastName;
    private String customerFirstName;
    private String customerLastName;
    private String applianceType;
    
    
    public ServiceRequestManagedBean() {
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
     * @return the qrcode
     */
    public String getQrcode() {
        return qrcode;
    }

    /**
     * @param qrcode the qrcode to set
     */
    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    /**
     * @return the contractorFirstName
     */
    public String getContractorFirstName() {
        return contractorFirstName;
    }

    /**
     * @param contractorFirstName the contractorFirstName to set
     */
    public void setContractorFirstName(String contractorFirstName) {
        this.contractorFirstName = contractorFirstName;
    }

    /**
     * @return the contractorLastName
     */
    public String getContractorLastName() {
        return contractorLastName;
    }

    /**
     * @param contractorLastName the contractorLastName to set
     */
    public void setContractorLastName(String contractorLastName) {
        this.contractorLastName = contractorLastName;
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
    
}
