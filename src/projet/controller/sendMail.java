/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.RecipientTerm;
import projet.models.Utilisateur;

/**
 *
 * @author khlil
 */
public class sendMail {
    public static void sendMail(String recepient , Utilisateur u) throws Exception{
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String mygmail="mustapha.feriani1@esprit.tn";
        String password="54544382mF!";
        Session session =Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mygmail,password); //To change body of generated methods, choose Tools | Templates.
                    
            
            }

        
            
        });
        Message message=prepareMessage(session,mygmail,recepient,u);
         Transport.send(message);  
        
    }

    private static Message prepareMessage(Session session,String mygmail,String recepient,Utilisateur u) {
        try {
            Message message  =new MimeMessage(session); 
            message.setFrom(new InternetAddress(mygmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("INSCRIPTION");
            message.setText("Inscription terminé ! Votre mot de passe est \"1234\" vous pouver le changer ultériérement  ");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(sendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
    }
    
    
    
     public static void sendMailPass(String recepient , Utilisateur u) throws Exception{
        Properties properties=new Properties();
        properties.put("mail.smtp.auth","true");
        properties.put("mail.smtp.starttls.enable","true");
        properties.put("mail.smtp.host","smtp.gmail.com");
        properties.put("mail.smtp.port","587");
        String mygmail="mustapha.feriani1@esprit.tn";
        String password="54544382mF!";
        Session session =Session.getInstance(properties,new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(mygmail,password); //To change body of generated methods, choose Tools | Templates.
                    
            
            }

        
            
        });
        Message message=prepareMessagepass(session,mygmail,recepient,u);
         Transport.send(message);  
        
    }

    private static Message prepareMessagepass(Session session,String mygmail,String recepient,Utilisateur u) {
        try {
            Message message  =new MimeMessage(session); 
            message.setFrom(new InternetAddress(mygmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Récupération de mot de passe");
            message.setText(" Votre nouveau mot de passe est \"1234\" vous pouver le changer ultériérement  ");
            return message;
        } catch (Exception ex) {
            Logger.getLogger(sendMail.class.getName()).log(Level.SEVERE, null, ex);
        }
      return null;
    }
    
}
