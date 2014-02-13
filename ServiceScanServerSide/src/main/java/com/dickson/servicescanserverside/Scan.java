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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USMEM-W-003157
 */
@Entity
@Table(name = "Scan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Scan.findAll", query = "SELECT s FROM Scan s"),
    @NamedQuery(name = "Scan.findByRowid", query = "SELECT s FROM Scan s WHERE s.rowid = :rowid"),
    @NamedQuery(name = "Scan.findByApplianceModel", query = "SELECT s FROM Scan s WHERE s.applianceModel = :applianceModel"),
    @NamedQuery(name = "Scan.findByApplianceSerial", query = "SELECT s FROM Scan s WHERE s.applianceSerial = :applianceSerial"),
    @NamedQuery(name = "Scan.findByApplianceType", query = "SELECT s FROM Scan s WHERE s.applianceType = :applianceType"),
    @NamedQuery(name = "Scan.findByDeviceToken", query = "SELECT s FROM Scan s WHERE s.deviceToken = :deviceToken")})
public class Scan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rowid")
    private Long rowid;
    @Size(max = 255)
    @Column(name = "applianceModel")
    private String applianceModel;
    @Size(max = 255)
    @Column(name = "applianceSerial")
    private String applianceSerial;
    @Size(max = 255)
    @Column(name = "applianceType")
    private String applianceType;
    @Size(max = 255)
    @Column(name = "deviceToken")
    private String deviceToken;
    @JoinColumn(name = "customer_id", referencedColumnName = "ROWID")
    @ManyToOne
    private Customer customerId;
    @JoinColumn(name = "contractor_id", referencedColumnName = "rowid")
    @ManyToOne
    private Contractor contractorId;
    @JoinColumn(name = "qrCode_id", referencedColumnName = "rowid")
    @ManyToOne
    private Qrcode qrCodeid;

    public Scan() {
    }

    public Scan(Long rowid) {
        this.rowid = rowid;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getApplianceModel() {
        return applianceModel;
    }

    public void setApplianceModel(String applianceModel) {
        this.applianceModel = applianceModel;
    }

    public String getApplianceSerial() {
        return applianceSerial;
    }

    public void setApplianceSerial(String applianceSerial) {
        this.applianceSerial = applianceSerial;
    }

    public String getApplianceType() {
        return applianceType;
    }

    public void setApplianceType(String applianceType) {
        this.applianceType = applianceType;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
    }

    public Contractor getContractorId() {
        return contractorId;
    }

    public void setContractorId(Contractor contractorId) {
        this.contractorId = contractorId;
    }

    public Qrcode getQrCodeid() {
        return qrCodeid;
    }

    public void setQrCodeid(Qrcode qrCodeid) {
        this.qrCodeid = qrCodeid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (rowid != null ? rowid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Scan)) {
            return false;
        }
        Scan other = (Scan) object;
        if ((this.rowid == null && other.rowid != null) || (this.rowid != null && !this.rowid.equals(other.rowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dickson.servicescanserverside.Scan[ rowid=" + rowid + " ]";
    }
    
}
