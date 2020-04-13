/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.commons.net.ProtocolCommandEvent;
import org.apache.commons.net.ProtocolCommandListener;


/**
 *
 * @author HP
 */
public class FTPUploader {public void FTPTransfer(File selectedFile)
        {
        String server = "127.0.0.1";
        int port = 21;
        String user = "user";
        String pass = "";
        FTPClient ftpClient = new FTPClient();
        try {
            
            ftpClient.connect(server, port);
            int replyCode = ftpClient.getReplyCode();
            if (!FTPReply.isPositiveCompletion(replyCode)) {
                System.out.println("Operation failed. Server reply code: " + replyCode);
                return;
            }
            boolean success = ftpClient.login(user, pass);
            
           
            if (!success) {
                System.out.println("Could not login to the server");
                return;
            } else {
                System.out.println("LOGGED IN SERVER");
                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
            }
        
           
           
         
            InputStream is= new FileInputStream(new File(selectedFile.getAbsolutePath()));      
            ftpClient.storeFile("images/"+selectedFile.getName(), is);
            ftpClient.disconnect();
         //   ftpClient.deleteFile(selectedFile.getName());
           
             
                
                
            
        } catch (IOException ex) {
            System.out.println("Oops! Something wrong happened");
            ex.printStackTrace();
        }
        }
        
}

