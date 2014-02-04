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
@Table(name = "Service_Record")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiceRecord.findAll", query = "SELECT s FROM ServiceRecord s"),
    @NamedQuery(name = "ServiceRecord.findByRowid", query = "SELECT s FROM ServiceRecord s WHERE s.rowid = :rowid"),
    @NamedQuery(name = "ServiceRecord.findByDateOfService", query = "SELECT s FROM ServiceRecord s WHERE s.dateOfService = :dateOfService"),
    @NamedQuery(name = "ServiceRecord.findByAccessible", query = "SELECT s FROM ServiceRecord s WHERE s.accessible = :accessible"),
    @NamedQuery(name = "ServiceRecord.findByServiceNotes", query = "SELECT s FROM ServiceRecord s WHERE s.serviceNotes = :serviceNotes")})
public class ServiceRecord implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Long rowid;
    @Column(name = "date_of_service")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfService;
    @Size(max = 5)
    private String accessible;
    @Size(max = 400)
    @Column(name = "service_notes")
    private String serviceNotes;
    @JoinColumn(name = "contractor_id", referencedColumnName = "rowid")
    @ManyToOne
    private Contractor contractorId;
    @JoinColumn(name = "customer_id", referencedColumnName = "rowid")
    @ManyToOne
    private Customer customerId;

    public ServiceRecord() {
    }

    public ServiceRecord(Long rowid) {
        this.rowid = rowid;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public Date getDateOfService() {
        return dateOfService;
    }

    public void setDateOfService(Date dateOfService) {
        this.dateOfService = dateOfService;
    }

    public String getAccessible() {
        return accessible;
    }

    public void setAccessible(String accessible) {
        this.accessible = accessible;
    }

    public String getServiceNotes() {
        return serviceNotes;
    }

    public void setServiceNotes(String serviceNotes) {
        this.serviceNotes = serviceNotes;
    }

    public Contractor getContractorId() {
        return contractorId;
    }

    public void setContractorId(Contractor contractorId) {
        this.contractorId = contractorId;
    }

    public Customer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Customer customerId) {
        this.customerId = customerId;
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
        if (!(object instanceof ServiceRecord)) {
            return false;
        }
        ServiceRecord other = (ServiceRecord) object;
        if ((this.rowid == null && other.rowid != null) || (this.rowid != null && !this.rowid.equals(other.rowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dickson.servicescanserverside.ServiceRecord[ rowid=" + rowid + " ]";
    }
    
}
