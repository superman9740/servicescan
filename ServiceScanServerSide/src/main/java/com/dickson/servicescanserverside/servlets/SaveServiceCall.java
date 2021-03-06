/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.servlets;

import com.dickson.servicescanserverside.Qrcode;
import com.dickson.servicescanserverside.Request;
import com.dickson.servicescanserverside.Scan;
import com.dickson.servicescanserverside.ServiceCall;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

/**
 *
 * @author USMEM-W-003157
 */
@WebServlet(name = "SaveServiceCall", urlPatterns = {"/SaveServiceCall"})
public class SaveServiceCall extends HttpServlet {

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
            String notes = request.getParameter("notes");
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            Query query = em.createNamedQuery("Qrcode.findByQrCode");
            query.setParameter("qrCode", qrCode);
            Qrcode qrCodeObject = (Qrcode)query.getSingleResult();
        
            
            Query query2 = em.createNamedQuery("Request.findByQrCode");
            query2.setParameter("qrCode", qrCode);
            List<Request> requests = query2.getResultList();
            
        
            TypedQuery<Scan> query3 = em.createQuery("SELECT c FROM Scan c WHERE c.qrCodeid = :qrcode", Scan.class);
            query3.setParameter("qrcode", qrCodeObject);
            Scan scan = query3.getSingleResult();
            
            ServiceCall serviceCall = new ServiceCall();
            serviceCall.setNotes(notes);
            serviceCall.setQrCodeID(scan);
            em.persist(serviceCall);
            em.getTransaction().commit();
            em.close();
            emf.close();
            
              //Send push notification to contractor
             String[] command = { "/opt/PushNotifications/src/Pusher", requests.get(0).getDeviceToken(), "-1"}; 
             
             BufferedReader is = null;
             BufferedReader es = null;
             Process process;
            process = Runtime.getRuntime().exec(command);
            String line;
            is = new BufferedReader(new InputStreamReader(process.getInputStream()));
            while((line = is.readLine()) != null)
                System.out.println(line);
                es = new BufferedReader(new InputStreamReader(process.getErrorStream()));
            
            while((line = es.readLine()) != null)
            System.err.println(line);

    int exitCode = process.waitFor();
    if (exitCode == 0)
        System.out.println("It worked");
    else
        System.out.println("Something bad happend. Exit code: " + exitCode);
             
             
             
             
            
            out.println("Request sent.");
            
            
            
            
        } catch (InterruptedException ex) {
            Logger.getLogger(SaveServiceCall.class.getName()).log(Level.SEVERE, null, ex);
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
