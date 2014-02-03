/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author USMEM-W-003157
 */
@ManagedBean(name = "pprBean")
@SessionScoped
public class PPRBean {

    /**
     * Creates a new instance of PPRBean
     */
    public PPRBean() {
    }
    
    	private String firstname;
	
	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
}
