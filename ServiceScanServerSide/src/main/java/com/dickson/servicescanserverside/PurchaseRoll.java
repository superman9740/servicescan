/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.math.BigDecimal;
import java.util.Map;
import net.authorize.Environment;
import net.authorize.Merchant;
import net.authorize.TransactionType;
import net.authorize.aim.Result;
import net.authorize.aim.Transaction;
import net.authorize.data.*;
import net.authorize.data.creditcard.*;
import net.sf.json.JSONObject;
/**
 *
 * @author USMEM-W-003157
 */
@WebServlet(name = "PurchaseRoll", urlPatterns = {"/PurchaseRoll"})
public class PurchaseRoll extends HttpServlet {

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
            
            String json = request.getParameter("inapp_purchase");
            JSONObject jsonObject = JSONObject.fromObject( json );  
            Object bean = JSONObject.toBean( jsonObject );  
            
            
            String nameOnCard = jsonObject.get( "nameOnCard" ).toString();
            String cardNumber = jsonObject.get( "cardNumber" ).toString();
            String cardAddress = jsonObject.get( "cardAddress" ).toString();
            String cardCity = jsonObject.get( "cardCity" ).toString();
            String cardState = jsonObject.get( "cardState" ).toString();
            String cardZip = jsonObject.get( "cardZip" ).toString();
            String cardExpMonth = jsonObject.get( "cardExpMonth" ).toString();
            String cardExpYear = jsonObject.get( "cardExpYear" ).toString();
            String cardCVV = jsonObject.get( "cardCVV" ).toString();
            
            
            
           String apiLoginID = "86h3s58L5Mk";
            String transactionKey = "5Q36GuV9CwnNR46K";
            Merchant merchant = Merchant.createMerchant(Environment.SANDBOX,
                apiLoginID, transactionKey);

            // create credit card
            CreditCard creditCard = CreditCard.createCreditCard();
            creditCard.setCreditCardNumber(cardNumber);
            creditCard.setExpirationMonth(cardExpMonth);
            creditCard.setExpirationYear("cardExpYear");
            creditCard.setCardCode(cardCVV);
            // create transaction
            Transaction authCaptureTransaction = merchant.createAIMTransaction(
                TransactionType.AUTH_CAPTURE, new BigDecimal(1.99));
            authCaptureTransaction.setCreditCard(creditCard);

            Result<Transaction> result = (Result<Transaction>)
              merchant.postTransaction(authCaptureTransaction);

            if(result.isApproved())
            {
              out.println("Approved");
              //out.println("Transaction Id: " + result.getTarget().getTransactionId());
            } else if (result.isDeclined())
            {
              out.println("Declined");
              out.println(result.getReasonResponseCode() + " : " + result.getResponseText());
            } else
            {
              out.println("Error");

              out.println(result.getReasonResponseCode() + " : " + result.getResponseText());
            }
            
            
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
