/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import projet.models.Club;
import static projet.service.ClubService.statement;
import projet.utils.DbConnection;

/**
 *
 * @author youssef
 */
public class NewsLetterService {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    public void ajouterEmail(String email) {
        String requete = "INSERT INTO inscription_email (email)"
                + " VALUES ('" + email + "');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public String sendMail(String Email, String Password, String ToEmail, String Subject, String Text) {

        String Msg;

        final String username = Email;
        final String password = Password;

        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("youssef.benhissi@esprit.tn"));//ur email
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(ToEmail));//u will send to
            message.setSubject(Subject);
            message.setText(Text);
            Transport.send(message);
            Msg = "true";
            return Msg;

        } catch (Exception e) {
            return e.toString();
        }

    }

    public List<String> retournerListeEmails() {
        ArrayList<String> listetImage = new ArrayList<>();
        String req = "SELECT c.email FROM inscription_email c ";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {
                listetImage.add(rs.getString(1));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listetImage;
    }

    public void desabonner(String email) {
         String sql = "DELETE FROM inscription_email where email LIKE ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, email);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
