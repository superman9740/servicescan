/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.servlets;

import com.dickson.servicescanserverside.Contractor;
import com.dickson.servicescanserverside.Customer;
import com.dickson.servicescanserverside.Scan;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 *
 * @author USMEM-W-003157
 */
public class AddNewContractor extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
          
              String json = request.getParameter("contractor");
            JSONObject jsonObject = JSONObject.fromObject( json );  
            Object bean = JSONObject.toBean( jsonObject );  
            
            
            String contractorFirstName = jsonObject.get( "contractorFirstName" ).toString();
            String contractorLastName = jsonObject.get( "contractorLastName" ).toString();
            String contractorAddress = jsonObject.get( "contractorAddress" ).toString();
            String contractorCity = jsonObject.get( "contractorCity" ).toString();
            String contractorState = jsonObject.get( "contractorState" ).toString();
            String contractorZip = jsonObject.get( "contractorZip" ).toString();
            String contractorPhone = jsonObject.get( "contractorPhone" ).toString();
           
            /*
            String customerFirstName = jsonObject.get( "customerFirstName" ).toString();
            String customerLastName = jsonObject.get( "customerLastName" ).toString();
            String customerAddress = jsonObject.get( "customerAddress" ).toString();
            String customerCity = jsonObject.get( "customerCity" ).toString();
            String customerState = jsonObject.get( "customerState" ).toString();
            String customerZip = jsonObject.get( "customerZip" ).toString();
            String customerPhone = jsonObject.get( "customerPhone" ).toString();
            
            String applianceModelNbr = jsonObject.get( "applianceModel" ).toString();
            String applianceSerialNbr = jsonObject.get( "applianceSerial" ).toString();
            String applianceType = jsonObject.get( "applianceType" ).toString();
            
            String qrCode = jsonObject.get("qrCode").toString();
            String deviceToken = jsonObject.get("deviceToken").toString();
            */
            
            //JPA
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            //First, check to make sure this qr code has not already been used
            /*
            TypedQuery<Scan> query = em.createQuery("SELECT c FROM Scan c WHERE c.qrcode = :qrcode", Scan.class);
             query.setParameter("qrcode", qrCode);
             List<Scan> scans = query.getResultList();
            
             if(scans.size() > 0)
             {
                 
                 out.println("-1");
                 return;
                 
             }
            
            */
            
            
            Contractor newContractor = new Contractor();
            //Customer newCustomer = new Customer();
            
            
            newContractor.setFirstName(contractorFirstName);
            newContractor.setLastName(contractorLastName);
            newContractor.setAddress(contractorAddress);
            newContractor.setCity(contractorCity);
            newContractor.setState(contractorState);
            newContractor.setZip(contractorZip);
            newContractor.setPhone(contractorPhone);
            /*
            newCustomer.setFirstName(customerFirstName);
            newCustomer.setLastName(customerLastName);
            newCustomer.setAddress(customerAddress);
            newCustomer.setCity(customerCity);
            newCustomer.setState(customerState);
            newCustomer.setZip(customerZip);
            newCustomer.setPhone(customerPhone);
            */
            
            Query queryObj = em.createNativeQuery("SELECT * FROM Contractor c  WHERE c.first_name = ?1 and c.last_name = ?2 and c.address = ?3 and c.city = ?4 and c.state = ?5 and c.zip = ?6" , Contractor.class);
             queryObj.setParameter(1, contractorFirstName);
             queryObj.setParameter(2, contractorLastName);
             queryObj.setParameter(3, contractorAddress);
             queryObj.setParameter(4, contractorCity);
             queryObj.setParameter(5, contractorState);
             queryObj.setParameter(6, contractorZip);
         
             List<Contractor> contractors = queryObj.getResultList();
             
             if(contractors.isEmpty())
             {
            
                       em.persist(newContractor);
           
                 
             }
            else
             {
                 Contractor foundContractor = contractors.get(0);
                 
                 foundContractor.setPhone(newContractor.getPhone());
                 foundContractor.setEmail(newContractor.getEmail());
                 em.merge(foundContractor);
            
            
             }
            em.getTransaction().commit();
            em.close();
            emf.close();
            
            
            out.println(json);
            
            
            
        } 
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        finally 
        {
            out.close();
        }
        
    } 
    



    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
