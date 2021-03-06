/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;


/**
 *
 * @author sdickson
 */
@WebServlet(name = "GetScanData", urlPatterns = {"/GetScanData"})
public class GetScanData extends HttpServlet {

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
            
            String qrCode = request.getParameter("qrCode");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            
            Query query = em.createNamedQuery("Qrcode.findByQrCode");
            query.setParameter("qrCode", qrCode);
            Qrcode qrCodeObject = (Qrcode)query.getSingleResult();
         
            TypedQuery<Scan> query2 = em.createQuery("SELECT c FROM Scan c WHERE c.qrCodeid = :qrcode", Scan.class);
            query2.setParameter("qrcode", qrCodeObject);
            List<Scan> scans = query2.getResultList();
            
             if(scans.isEmpty())
             {
                 
                 out.println("-1");
                 return;
                 
             }
             Scan scanObject = scans.get(0);
             
             //Look up contractor information
            TypedQuery<Contractor> query3 = em.createQuery("SELECT c FROM Contractor c WHERE c.rowid = :rowid", Contractor.class);
            query3.setParameter("rowid", scanObject.getContractorId().getRowid());
            Contractor contractor = (Contractor)query3.getSingleResult();
            
            
            //Look up customer information
            TypedQuery<Customer> query4 = em.createQuery("SELECT c FROM Customer c WHERE c.rowid = :rowid", Customer.class);
            query4.setParameter("rowid", scanObject.getCustomerId().getRowid());
            Customer customer = (Customer)query4.getSingleResult();
            
             
            
            
             
             
             Map map = new HashMap();
             
             map.put("applianceModel", scanObject.getApplianceModel());
             map.put("applianceSerial", scanObject.getApplianceSerial());
             map.put("applianceType", scanObject.getApplianceType());
             map.put("contractor", contractor);
             map.put("customer", customer);
             JsonConfig jsonConfig = new JsonConfig();
            // jsonConfig.setExcludes(new String[]{"files", "createdBy", "lastUpdatedBy"});
             jsonConfig.setIgnoreDefaultExcludes(false);
             jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);  
             JSONObject jsonObject = JSONObject.fromObject(map,jsonConfig);
             
           
            
             
             
             System.out.println( jsonObject );  
             out.println(jsonObject);
             
             
    
            
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
