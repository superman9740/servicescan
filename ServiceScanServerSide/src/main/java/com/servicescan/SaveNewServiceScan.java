/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servicescan;

import com.dickson.servicescanserverside.Scan;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;

/**
 *
 * @author sdickson
 */
@WebServlet(name = "SaveNewServiceScan", urlPatterns = {"/SaveNewServiceScan"})
public class SaveNewServiceScan extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            
            
            String json = request.getParameter("scan");
            JSONObject jsonObject = JSONObject.fromObject( json );  
            Object bean = JSONObject.toBean( jsonObject );  
            
            
            String contractorFirstName = jsonObject.get( "contractorFirstName" ).toString();
            String contractorLastName = jsonObject.get( "contractorLastName" ).toString();
            String contractorAddress = jsonObject.get( "contractorAddress" ).toString();
            String contractorCity = jsonObject.get( "contractorCity" ).toString();
            String contractorState = jsonObject.get( "contractorState" ).toString();
            String contractorZip = jsonObject.get( "contractorZip" ).toString();
            String contractorPhone = jsonObject.get( "contractorPhone" ).toString();
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
            
            //JPA
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            //First, check to make sure this qr code has not already been used
            
            TypedQuery<Scan> query = em.createQuery("SELECT c FROM Scan c WHERE c.qrcode = :qrcode", Scan.class);
             query.setParameter("qrcode", qrCode);
             List<Scan> scans = query.getResultList();
            
             if(scans.size() > 0)
             {
                 
                 out.println("-1");
                 return;
                 
             }
            
            
            
            Scan newScan = new Scan();
            
            newScan.setContractorFirstName(contractorFirstName);
            newScan.setContractorLastName(contractorLastName);
            newScan.setContractorAddress(contractorAddress);
            newScan.setContractorCity(contractorCity);
            newScan.setContractorState(contractorState);
            newScan.setContractorZip(contractorZip);
            newScan.setContractorPhone(contractorPhone);
            
            newScan.setCustomerFirstName(customerFirstName);
            newScan.setCustomerLastName(customerLastName);
            newScan.setCustomerAddress(customerAddress);
            newScan.setCustomerCity(customerCity);
            newScan.setCustomerState(customerState);
            newScan.setCustomerZip(customerZip);
            newScan.setCustomerPhone(customerPhone);
            newScan.setApplianceModel(applianceModelNbr);
            newScan.setApplianceSerial(applianceSerialNbr);
            newScan.setApplianceType(applianceType);
            newScan.setQrcode(qrCode);
            newScan.setDeviceToken(deviceToken);
            em.persist(newScan);
            em.getTransaction().commit();
            em.close();
            emf.close();
            
            
            out.println(json);
            
            
            
        } 
        catch(Exception e)
        {
            System.out.println(e.getStackTrace());
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
