/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.servlets;

import com.dickson.servicescanserverside.Contractor;
import com.dickson.servicescanserverside.Customer;
import com.dickson.servicescanserverside.Qrcode;
import com.dickson.servicescanserverside.Request;
import com.dickson.servicescanserverside.Scan;
import com.dickson.servicescanserverside.ServiceCall;
import java.io.IOException;
import java.io.PrintWriter;
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
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

/**
 *
 * @author USMEM-W-003157
 */
@WebServlet(name = "GetServiceCallHistory", urlPatterns = {"/GetServiceCallHistory"})
public class GetServiceCallHistory extends HttpServlet {

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
         
             Query queryObj = em.createNativeQuery("SELECT * FROM ServiceCall c  WHERE c.qrCodeID = ?1" , ServiceCall.class);
             queryObj.setParameter(1, qrCodeObject.getRowid());
             
             List<ServiceCall> serviceCalls = queryObj.getResultList();
             if(serviceCalls.isEmpty())
             {
                 
                 out.println("-1");
                 return;
                 
             }
            
             
             JsonConfig jsonConfig = new JsonConfig();
            // jsonConfig.setExcludes(new String[]{"files", "createdBy", "lastUpdatedBy"});
             jsonConfig.setIgnoreDefaultExcludes(false);
             jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);  
             JSONArray jsonArray = JSONArray.fromObject(serviceCalls,jsonConfig);
             
             
             
             System.out.println( jsonArray );  
             out.println(jsonArray);
            
            
        } finally {
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
