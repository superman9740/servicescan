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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.json.JSONObject;
import com.dickson.servicescanserverside.Request;
import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 *
 * @author USMEM-W-003157
 */
@WebServlet(name = "CreateNewRequest", urlPatterns = {"/CreateNewRequest"})
public class CreateNewRequest extends HttpServlet {

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
            
            
            String qrCode = request.getParameter("qrCode");
            com.dickson.servicescanserverside.Request newRequest = new com.dickson.servicescanserverside.Request();
            newRequest.setQrCode(qrCode);
            //JPA
            
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
            EntityManager em = emf.createEntityManager();
            em.getTransaction().begin();
            
            //First, check to make sure this qr code has not already been used
            em.persist(newRequest);
            em.getTransaction().commit();
            
            
            
             TypedQuery<Scan> query = em.createQuery("SELECT c FROM Scan c WHERE c.qrcode = :qrcode", Scan.class);
             query.setParameter("qrcode", qrCode);
             List<Scan> scans = query.getResultList();
            
             if(scans.isEmpty())
             {
                 
                 out.println("-1");
                 return;
                 
             }
             
             em.close();
             emf.close();
            
             //Send push notification to contractor
             String[] command = { "/opt/PushNotifications/src/Pusher", scans.get(0).getDeviceToken(), scans.get(0).getQrcode() }; 
             
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
