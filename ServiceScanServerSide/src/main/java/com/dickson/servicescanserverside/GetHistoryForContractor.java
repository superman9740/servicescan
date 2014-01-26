/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.Query;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 *
 * @author sdickson
 */
@WebServlet(name = "GetHistoryForContractor", urlPatterns = {"/GetHistoryForContractor"})
public class GetHistoryForContractor extends HttpServlet {

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
            
            //JPA
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            //First, check to make sure this qr code has not already been used
            
             Query queryObj = em.createNativeQuery("SELECT * FROM Scan c  WHERE c.contractorFirstName = ?1 and c.contractorLastName = ?2 and c.contractorAddress = ?3 and c.contractorCity = ?4 and c.contractorState = ?5 and c.contractorZip = ?6 and c.contractorPhone = ?7 and exists (select qrCode from Request where Request.qrCode = c.qrCode)", Scan.class);
             queryObj.setParameter(1, contractorFirstName);
             queryObj.setParameter(2, contractorLastName);
             queryObj.setParameter(3, contractorAddress);
             queryObj.setParameter(4, contractorCity);
             queryObj.setParameter(5, contractorState);
             queryObj.setParameter(6, contractorZip);
             queryObj.setParameter(7, contractorPhone);
            List<Scan> scans = queryObj.getResultList();
             
             
             
            
             if(scans.isEmpty())
             {
                 
                 out.println("-1");
                 return;
                 
             }
            
            JSONArray jsonArray = JSONArray.fromObject( scans );  
            
            out.println(jsonArray);
              
    
            
        } 
        catch(Exception e)
        {
               System.out.println(e.getMessage());
        }
        finally {
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
