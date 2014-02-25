/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;

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
                 
                 out.println("-1");
                 return;
                 
             }
            
             
             
             
             
             //Grab all appliance records for this contractor
            Query queryObj2 = em.createNativeQuery("SELECT * FROM Scan c  WHERE c.contractor_id = ?1" , Scan.class);
            queryObj2.setParameter(1, contractors.get(0).getRowid());
            List<Scan> scans = queryObj2.getResultList();
             Map map = new HashMap();
             
            for(Scan tempScan : scans)
            {
                  Query queryObj3 = em.createNativeQuery("SELECT * FROM Request c  WHERE c.qrCode = ?1" , Request.class);
                  queryObj3.setParameter(1, tempScan.getQrCodeid().getQrCode());
                  List<Request> requests = queryObj3.getResultList();
             
                  TypedQuery<Customer> query4 = em.createQuery("SELECT c FROM Customer c WHERE c.rowid = :rowid", Customer.class);
                  query4.setParameter("rowid", tempScan.getCustomerId().getRowid());
                  Customer customer = (Customer)query4.getSingleResult();
             
                  for(Request tempRequest : requests)
                  {
                      
                      
                      map.put("request", tempRequest);
                      map.put("customerFirstName", customer.getFirstName());
                      map.put("customerLastName", customer.getLastName());
                      map.put("customerCity", customer.getCity());
                      map.put("customerState", customer.getState());
                      map.put("customerZip", customer.getZip());
                      map.put("customerPhone", customer.getPhone());
                      
                      
                  }
               
            }
            
            
            JsonConfig jsonConfig = new JsonConfig();
            // jsonConfig.setExcludes(new String[]{"files", "createdBy", "lastUpdatedBy"});
             jsonConfig.setIgnoreDefaultExcludes(false);
             jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);  
             JSONObject jsonObject2 = JSONObject.fromObject(map,jsonConfig);
             System.out.println( jsonObject2 );  
             out.println(jsonObject2);
                 
    
            
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
