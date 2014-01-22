/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sdickson
 */
@Entity
@Table(name = "Scan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scan.findAll", query = "SELECT s FROM Scan s"),
    @NamedQuery(name = "Scan.findById", query = "SELECT s FROM Scan s WHERE s.id = :id"),
    @NamedQuery(name = "Scan.findByQrcode", query = "SELECT s FROM Scan s WHERE s.qrcode = :qrcode"),
    @NamedQuery(name = "Scan.findByContractorFirstName", query = "SELECT s FROM Scan s WHERE s.contractorFirstName = :contractorFirstName"),
    @NamedQuery(name = "Scan.findByContractorLastName", query = "SELECT s FROM Scan s WHERE s.contractorLastName = :contractorLastName"),
    @NamedQuery(name = "Scan.findByContractorAddress", query = "SELECT s FROM Scan s WHERE s.contractorAddress = :contractorAddress"),
    @NamedQuery(name = "Scan.findByContractorCity", query = "SELECT s FROM Scan s WHERE s.contractorCity = :contractorCity"),
    @NamedQuery(name = "Scan.findByContractorState", query = "SELECT s FROM Scan s WHERE s.contractorState = :contractorState"),
    @NamedQuery(name = "Scan.findByContractorZip", query = "SELECT s FROM Scan s WHERE s.contractorZip = :contractorZip"),
    @NamedQuery(name = "Scan.findByContractorPhone", query = "SELECT s FROM Scan s WHERE s.contractorPhone = :contractorPhone"),
    @NamedQuery(name = "Scan.findByCustomerFirstName", query = "SELECT s FROM Scan s WHERE s.customerFirstName = :customerFirstName"),
    @NamedQuery(name = "Scan.findByCustomerLastName", query = "SELECT s FROM Scan s WHERE s.customerLastName = :customerLastName"),
    @NamedQuery(name = "Scan.findByCustomerAddress", query = "SELECT s FROM Scan s WHERE s.customerAddress = :customerAddress"),
    @NamedQuery(name = "Scan.findByCustomerCity", query = "SELECT s FROM Scan s WHERE s.customerCity = :customerCity"),
    @NamedQuery(name = "Scan.findByCustomerState", query = "SELECT s FROM Scan s WHERE s.customerState = :customerState"),
    @NamedQuery(name = "Scan.findByCustomerZip", query = "SELECT s FROM Scan s WHERE s.customerZip = :customerZip"),
    @NamedQuery(name = "Scan.findByCustomerPhone", query = "SELECT s FROM Scan s WHERE s.customerPhone = :customerPhone"),
    @NamedQuery(name = "Scan.findByApplianceSerial", query = "SELECT s FROM Scan s WHERE s.applianceSerial = :applianceSerial"),
    @NamedQuery(name = "Scan.findByApplianceModel", query = "SELECT s FROM Scan s WHERE s.applianceModel = :applianceModel"),
    @NamedQuery(name = "Scan.findByApplianceType", query = "SELECT s FROM Scan s WHERE s.applianceType = :applianceType")})
public class Scan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "qrcode")
    private String qrcode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contractorFirstName")
    private String contractorFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "contractorLastName")
    private String contractorLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "contractorAddress")
    private String contractorAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "contractorCity")
    private String contractorCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "contractorState")
    private String contractorState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "contractorZip")
    private String contractorZip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "contractorPhone")
    private String contractorPhone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "customerFirstName")
    private String customerFirstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "customerLastName")
    private String customerLastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "customerAddress")
    private String customerAddress;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "customerCity")
    private String customerCity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "customerState")
    private String customerState;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "customerZip")
    private String customerZip;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "customerPhone")
    private String customerPhone;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "applianceSerial")
    private String applianceSerial;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "applianceModel")
    private String applianceModel;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "applianceType")
    private String applianceType;

    @Column(name = "deviceToken")
    private String deviceToken;

    public Scan() {
    }

    public Scan(Long id) {
        this.id = id;
    }

    public Scan(Long id, String qrcode, String contractorFirstName, String contractorLastName, String contractorAddress, String contractorCity, String contractorState, String contractorZip, String contractorPhone, String customerFirstName, String customerLastName, String customerAddress, String customerCity, String customerState, String customerZip, String customerPhone, String applianceSerial, String applianceModel, String applianceType,String deviceToken) {
        this.id = id;
        this.qrcode = qrcode;
        this.contractorFirstName = contractorFirstName;
        this.contractorLastName = contractorLastName;
        this.contractorAddress = contractorAddress;
        this.contractorCity = contractorCity;
        this.contractorState = contractorState;
        this.contractorZip = contractorZip;
        this.contractorPhone = contractorPhone;
        this.customerFirstName = customerFirstName;
        this.customerLastName = customerLastName;
        this.customerAddress = customerAddress;
        this.customerCity = customerCity;
        this.customerState = customerState;
        this.customerZip = customerZip;
        this.customerPhone = customerPhone;
        this.applianceSerial = applianceSerial;
        this.applianceModel = applianceModel;
        this.applianceType = applianceType;
        this.deviceToken = deviceToken;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQrcode() {
        return qrcode;
    }

    public void setQrcode(String qrcode) {
        this.qrcode = qrcode;
    }

    public String getContractorFirstName() {
        return contractorFirstName;
    }

    public void setContractorFirstName(String contractorFirstName) {
        this.contractorFirstName = contractorFirstName;
    }

    public String getContractorLastName() {
        return contractorLastName;
    }

    public void setContractorLastName(String contractorLastName) {
        this.contractorLastName = contractorLastName;
    }

    public String getContractorAddress() {
        return contractorAddress;
    }

    public void setContractorAddress(String contractorAddress) {
        this.contractorAddress = contractorAddress;
    }

    public String getContractorCity() {
        return contractorCity;
    }

    public void setContractorCity(String contractorCity) {
        this.contractorCity = contractorCity;
    }

    public String getContractorState() {
        return contractorState;
    }

    public void setContractorState(String contractorState) {
        this.contractorState = contractorState;
    }

    public String getContractorZip() {
        return contractorZip;
    }

    public void setContractorZip(String contractorZip) {
        this.contractorZip = contractorZip;
    }

    public String getContractorPhone() {
        return contractorPhone;
    }

    public void setContractorPhone(String contractorPhone) {
        this.contractorPhone = contractorPhone;
    }

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public String getCustomerZip() {
        return customerZip;
    }

    public void setCustomerZip(String customerZip) {
        this.customerZip = customerZip;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getApplianceSerial() {
        return applianceSerial;
    }

    public void setApplianceSerial(String applianceSerial) {
        this.applianceSerial = applianceSerial;
    }

    public String getApplianceModel() {
        return applianceModel;
    }

    public void setApplianceModel(String applianceModel) {
        this.applianceModel = applianceModel;
    }

    public String getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scan)) {
            return false;
        }
        Scan other = (Scan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dickson.servicescanserverside.Scan[ id=" + id + " ]";
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
