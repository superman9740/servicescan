/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dickson.servicescanserverside.beans;

import com.dickson.servicescanserverside.Qrcode;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;

/**
 *
 * @author sdickson
 */
@ManagedBean(name="qrCodeFileImporter")
@RequestScoped
public class QRCodeFileImporter {

    
    private UploadedFile file;
    
    
    /**
     * Creates a new instance of QRCodeFileImporter
     */
    public QRCodeFileImporter() {
    
    
    }
    
    
    public void importFile(FileUploadEvent event)
    {
        
        String uploadedFilePath = event.getFile().getFileName();
        
        UploadedFile file = event.getFile();
        String csvFile = file.getFileName();
	BufferedReader br = null;
	
        String line = "";
 
	try {
 
                       EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.dickson_ServiceScanServerSide_war_1.0-SNAPSHOTPU");
                       EntityManager em = emf.createEntityManager();
                
            br = new BufferedReader(new InputStreamReader(event.getFile().getInputstream()));
		while ((line = br.readLine()) != null) 
                {
 
		        
		
                       
                            Qrcode qrCode = new Qrcode();
                            qrCode.setQrCode(line);
                            em.getTransaction().begin();
                            em.persist(qrCode);
                            em.getTransaction().commit();
                            
                        
		}
 
                em.close();
                emf.close();
                FacesMessage msg = new FacesMessage("The import completed successfully. ");  
                FacesContext.getCurrentInstance().addMessage(null, msg); 
	} catch (FileNotFoundException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		if (br != null) {
			try {
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
 
	System.out.println("Done");
  }

    /**
     * @return the file
     */
    public UploadedFile getFile() {
        return file;
    }

    /**
     * @param file the file to set
     */
    public void setFile(UploadedFile file) {
        this.file = file;
    }
        
    }



