/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USMEM-W-003157
 */
@Entity
@Table(name = "Qrcode")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qrcode.findAll", query = "SELECT q FROM Qrcode q"),
    @NamedQuery(name = "Qrcode.findByRowid", query = "SELECT q FROM Qrcode q WHERE q.rowid = :rowid"),
    @NamedQuery(name = "Qrcode.findByQrCode", query = "SELECT q FROM Qrcode q WHERE q.qrCode = :qrCode")})
public class Qrcode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rowid")
    private Long rowid;
    @Size(max = 100)
    @Column(name = "qrCode")
    private String qrCode;
    @OneToMany(mappedBy = "qrCodeid")
    private Collection<Scan> scanCollection;

    public Qrcode() {
    }

    public Qrcode(Long rowid) {
        this.rowid = rowid;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    @XmlTransient
    public Collection<Scan> getScanCollection() {
        return scanCollection;
    }

    public void setScanCollection(Collection<Scan> scanCollection) {
        this.scanCollection = scanCollection;
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
        if (!(object instanceof Qrcode)) {
            return false;
        }
        Qrcode other = (Qrcode) object;
        if ((this.rowid == null && other.rowid != null) || (this.rowid != null && !this.rowid.equals(other.rowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dickson.servicescanserverside.Qrcode[ rowid=" + rowid + " ]";
    }
    
}
