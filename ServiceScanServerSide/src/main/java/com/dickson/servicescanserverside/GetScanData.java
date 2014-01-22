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
import java.util.List;
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
            
             TypedQuery<Scan> query = em.createQuery("SELECT c FROM Scan c WHERE c.qrcode = :qrcode", Scan.class);
             query.setParameter("qrcode", qrCode);
             List<Scan> scans = query.getResultList();
            
             if(scans.isEmpty())
             {
                 
                 out.println("-1");
                 return;
                 
             }
             
             
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
             
             
             
             
             
             
             
            
             JSONObject jsonObject = JSONObject.fromObject(scans.get(0));
             
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
