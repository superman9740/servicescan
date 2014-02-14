/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author USMEM-W-003157
 */
@ManagedBean(name="menuBean")
@RequestScoped
public class MenuBean {

    /**
     * Creates a new instance of MenuBean
     */
    public MenuBean() {
    }
    
    public String gotoMaintainContractors()
    {
        
        return "maintainContractors";
        
        
    }
    public String gotoMaintainCustomers()
    {
        
        return "maintainCustomers";
        
        
    }
    public String gotoMaintainApplianceRecords()
    {
        
        return "maintainApplianceRecords";
        
        
    }

    public String gotoImportQRCodes()
    {
        
        return "importQRCodes";
        
        
    }
}
