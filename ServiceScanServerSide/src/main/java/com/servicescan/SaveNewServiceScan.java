/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.servicescan;

import com.dickson.servicescanserverside.Contractor;
import com.dickson.servicescanserverside.Customer;
import com.dickson.servicescanserverside.Qrcode;
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
            
            //First, get the qr code row id
            
            Query query = em.createNamedQuery("Qrcode.findByQrCode");
            query.setParameter("qrCode", qrCode);
            Qrcode qrCodeObject = (Qrcode)query.getSingleResult();
            
            //Next, we check to see if an appliance record exists for this qr code
             Query queryObj = em.createNativeQuery("SELECT * FROM Scan c  WHERE c.qrCode_id = ?1 " , Scan.class);
             queryObj.setParameter(1, qrCodeObject.getRowid());
             Scan scanObject = null;
            try
            {
             scanObject = (Scan) queryObj.getSingleResult();
            }
            catch(Exception ex)
            {
                
            }
            
            
            
             if(scanObject != null)
             {
                 //This qr code has already been assigned to an appliance record
                 out.println("-1");
                 return;
                 
             }
            
            
             //Look up the contractor row id
             queryObj = em.createNativeQuery("SELECT * FROM Contractor c  WHERE c.first_name = ?1 and c.last_name = ?2 and c.address = ?3 and c.city = ?4 and c.state = ?5 and c.zip = ?6 and c.phone = ?7 ", Contractor.class);
             queryObj.setParameter(1, contractorFirstName);
             queryObj.setParameter(2, contractorLastName);
             queryObj.setParameter(3, contractorAddress);
             queryObj.setParameter(4, contractorCity);
             queryObj.setParameter(5, contractorState);
             queryObj.setParameter(6, contractorZip);
             queryObj.setParameter(7, contractorPhone);
             Contractor contractor = (Contractor) queryObj.getSingleResult();
             
             
             //Look up the customer row id
             queryObj = em.createNativeQuery("SELECT * FROM Customer c  WHERE c.first_name = ?1 and c.last_name = ?2 and c.address = ?3 and c.city = ?4 and c.state = ?5 and c.zip = ?6 and c.phone = ?7 ", Customer.class);
             queryObj.setParameter(1, customerFirstName);
             queryObj.setParameter(2, customerLastName);
             queryObj.setParameter(3, customerAddress);
             queryObj.setParameter(4, customerCity);
             queryObj.setParameter(5, customerState);
             queryObj.setParameter(6, customerZip);
             queryObj.setParameter(7, customerPhone);
             
             
             Customer customer = null;
             try
             {
                    customer = (Customer) queryObj.getSingleResult();
             
             }
             catch(Exception ex)
             {
                 
                 
             }
             
             
             if(customer == null)
             {
                 customer = new Customer();
                 customer.setFirstName(customerFirstName);
                 customer.setLastName(customerLastName);
                 customer.setAddress(customerAddress);
                 customer.setCity(customerCity);
                 customer.setState(customerState);
                 customer.setZip(customerZip);
                 customer.setPhone(customerPhone);
                 em.persist(customer);
             }
             //Now create a new appliance record
             
             Scan newScan = new Scan();
             
            
            
            newScan.setApplianceModel(applianceModelNbr);
            newScan.setApplianceSerial(applianceSerialNbr);
            newScan.setApplianceType(applianceType);
            newScan.setDeviceToken(deviceToken);
            newScan.setQrCodeid(qrCodeObject);
            newScan.setContractorId(contractor);
            newScan.setCustomerId(customer);
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
