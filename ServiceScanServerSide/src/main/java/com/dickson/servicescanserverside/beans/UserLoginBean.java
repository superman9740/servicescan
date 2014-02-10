/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

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
public class UserLoginBean {

    /**
     * Creates a new instance of UserLoginBean
     */
    public UserLoginBean() {
    }

    private String userID;
    private String password;

    public void validateLogin()
    {
        
        System.out.println("validateLogin - test");

        if(userID.equals("admin"))
        {
            if(password.equals("Password"))
            {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Logged in."));  
            }
            else
            {
                 FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The user id and/or password was invalid."));  
            }
                
        }
        else
        {
             FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The user id and/or password was invalid."));  
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
    
}