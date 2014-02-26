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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sdickson
 */
@Entity
@Table(name = "LabelOrder")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LabelOrder.findAll", query = "SELECT l FROM LabelOrder l"),
    @NamedQuery(name = "LabelOrder.findByRowid", query = "SELECT l FROM LabelOrder l WHERE l.rowid = :rowid"),
    @NamedQuery(name = "LabelOrder.findByTimestamp", query = "SELECT l FROM LabelOrder l WHERE l.timestamp = :timestamp"),
    @NamedQuery(name = "LabelOrder.findByQuantity", query = "SELECT l FROM LabelOrder l WHERE l.quantity = :quantity"),
    @NamedQuery(name = "LabelOrder.findByStatus", query = "SELECT l FROM LabelOrder l WHERE l.status = :status")})
public class LabelOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rowid")
    private Long rowid;
    @Column(name = "timestamp")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timestamp;
    @Basic(optional = false)
    @NotNull
    @Column(name = "quantity")
    private long quantity;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "status")
    private String status;
    @JoinColumn(name = "contractor_id", referencedColumnName = "rowid")
    @ManyToOne(optional = false)
    private Contractor contractorId;

    public LabelOrder() {
    }

    public LabelOrder(Long rowid) {
        this.rowid = rowid;
    }

    public LabelOrder(Long rowid, long quantity, String status) {
        this.rowid = rowid;
        this.quantity = quantity;
        this.status = status;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Contractor getContractorId() {
        return contractorId;
    }

    public void setContractorId(Contractor contractorId) {
        this.contractorId = contractorId;
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
        if (!(object instanceof LabelOrder)) {
            return false;
        }
        LabelOrder other = (LabelOrder) object;
        if ((this.rowid == null && other.rowid != null) || (this.rowid != null && !this.rowid.equals(other.rowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dickson.servicescanserverside.LabelOrder[ rowid=" + rowid + " ]";
    }
    
}
