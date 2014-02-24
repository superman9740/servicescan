/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USMEM-W-003157
 */
@Entity
@Table(name = "ServiceCall")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceCall.findAll", query = "SELECT s FROM ServiceCall s"),
    @NamedQuery(name = "ServiceCall.findByRowid", query = "SELECT s FROM ServiceCall s WHERE s.rowid = :rowid"),
    @NamedQuery(name = "ServiceCall.findByNotes", query = "SELECT s FROM ServiceCall s WHERE s.notes = :notes"),
    @NamedQuery(name = "ServiceCall.findByCreatedTimestamp", query = "SELECT s FROM ServiceCall s WHERE s.createdTimestamp = :createdTimestamp")})
public class ServiceCall implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rowid")
    private Long rowid;
    @Size(max = 2000)
    @Column(name = "notes")
    private String notes;
    @Column(name = "created_timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdTimestamp;
    @JoinColumn(name = "qrCodeID", referencedColumnName = "qrCode_id")
    @ManyToOne
    private Scan qrCodeID;

    public ServiceCall() {
    }

    public ServiceCall(Long rowid) {
        this.rowid = rowid;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Date getCreatedTimestamp() {
        return createdTimestamp;
    }

    public void setCreatedTimestamp(Date createdTimestamp) {
        this.createdTimestamp = createdTimestamp;
    }

    public Scan getQrCodeID() {
        return qrCodeID;
    }

    public void setQrCodeID(Scan qrCodeID) {
        this.qrCodeID = qrCodeID;
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
        if (!(object instanceof ServiceCall)) {
            return false;
        }
        ServiceCall other = (ServiceCall) object;
        if ((this.rowid == null && other.rowid != null) || (this.rowid != null && !this.rowid.equals(other.rowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dickson.servicescanserverside.ServiceCall[ rowid=" + rowid + " ]";
    }
    
}
