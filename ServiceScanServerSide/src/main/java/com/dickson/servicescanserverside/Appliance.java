/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USMEM-W-003157
 */
@Entity
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Appliance.findAll", query = "SELECT a FROM Appliance a"),
    @NamedQuery(name = "Appliance.findByRowid", query = "SELECT a FROM Appliance a WHERE a.rowid = :rowid"),
    @NamedQuery(name = "Appliance.findByType", query = "SELECT a FROM Appliance a WHERE a.type = :type"),
    @NamedQuery(name = "Appliance.findByBrand", query = "SELECT a FROM Appliance a WHERE a.brand = :brand")})
public class Appliance implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long rowid;
    @Size(max = 200)
    private String type;
    @Size(max = 200)
    private String brand;

    public Appliance() {
    }

    public Appliance(Long rowid) {
        this.rowid = rowid;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
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
        if (!(object instanceof Appliance)) {
            return false;
        }
        Appliance other = (Appliance) object;
        if ((this.rowid == null && other.rowid != null) || (this.rowid != null && !this.rowid.equals(other.rowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dickson.servicescanserverside.Appliance[ rowid=" + rowid + " ]";
    }
    
}
