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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USMEM-W-003157
 */
@Entity
@Table(name = "Contractor")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contractor.findAll", query = "SELECT c FROM Contractor c"),
    @NamedQuery(name = "Contractor.findByRowid", query = "SELECT c FROM Contractor c WHERE c.rowid = :rowid"),
    @NamedQuery(name = "Contractor.findByFirstName", query = "SELECT c FROM Contractor c WHERE c.firstName = :firstName"),
    @NamedQuery(name = "Contractor.findByLastName", query = "SELECT c FROM Contractor c WHERE c.lastName = :lastName"),
    @NamedQuery(name = "Contractor.findByAddress", query = "SELECT c FROM Contractor c WHERE c.address = :address"),
    @NamedQuery(name = "Contractor.findByCity", query = "SELECT c FROM Contractor c WHERE c.city = :city"),
    @NamedQuery(name = "Contractor.findByState", query = "SELECT c FROM Contractor c WHERE c.state = :state"),
    @NamedQuery(name = "Contractor.findByZip", query = "SELECT c FROM Contractor c WHERE c.zip = :zip"),
    @NamedQuery(name = "Contractor.findByPhone", query = "SELECT c FROM Contractor c WHERE c.phone = :phone"),
    @NamedQuery(name = "Contractor.findByEmail", query = "SELECT c FROM Contractor c WHERE c.email = :email")})
public class Contractor implements Serializable {
    @OneToMany(mappedBy = "contractorId")
    private Collection<Scan> scanCollection;
    @OneToMany(mappedBy = "contractorId")
    private Collection<Customer> customerCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "rowid")
    private Long rowid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "first_name")
    private String firstName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "last_name")
    private String lastName;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "address")
    private String address;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "city")
    private String city;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "state")
    private String state;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "zip")
    private String zip;
    // @Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$", message="Invalid phone/fax format, should be as xxx-xxx-xxxx")//if the field contains phone or fax number consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "phone")
    private String phone;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 100)
    @Column(name = "email")
    private String email;

    public Contractor() {
    }

    public Contractor(Long rowid) {
        this.rowid = rowid;
    }

    public Contractor(Long rowid, String firstName, String lastName, String address, String city, String state, String zip, String phone) {
        this.rowid = rowid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.phone = phone;
    }

    public Long getRowid() {
        return rowid;
    }

    public void setRowid(Long rowid) {
        this.rowid = rowid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (!(object instanceof Contractor)) {
            return false;
        }
        Contractor other = (Contractor) object;
        if ((this.rowid == null && other.rowid != null) || (this.rowid != null && !this.rowid.equals(other.rowid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.dickson.servicescanserverside.Contractor[ rowid=" + rowid + " ]";
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @XmlTransient
    public Collection<Scan> getScanCollection() {
        return scanCollection;
    }

    public void setScanCollection(Collection<Scan> scanCollection) {
        this.scanCollection = scanCollection;
    }
    
}
