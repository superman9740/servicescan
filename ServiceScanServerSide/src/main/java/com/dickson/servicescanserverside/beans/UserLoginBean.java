/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author USMEM-W-003157
 */
@ManagedBean(name="userLoginBean")
@SessionScoped
public class UserLoginBean implements Serializable{

    /**
     * Creates a new instance of UserLoginBean
     */
    public UserLoginBean() {
    }

    private String userID;
    private String password;
    private String actionText;
    public String validateLogin()
    {
        
        System.out.println("validateLogin - test");

        if(userID.equals("admin"))
        {
            if(password.equals("Password"))
            {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Logged in."));  
                 return "maintainContractors";
                 
            }
            else
            {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The user id and/or password was invalid."));  
                 return null;
            }
                
        }
        else
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The user id and/or password was invalid."));  
             return null;
             
        }
       
    }
            
    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the actionText
     */
    public String getActionText() {
        
        this.validateLogin();
        
        
        return actionText;
    }

    /**
     * @param actionText the actionText to set
     */
    public void setActionText(String actionText) {
        this.actionText = actionText;
    }
    
}
