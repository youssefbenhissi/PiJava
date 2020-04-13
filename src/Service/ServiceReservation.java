/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeType;
import com.sbix.jnotify.NoticeWindow;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.Reservation;
import models.categorier;
import controller.FTPUploader;
import utils.ConnexionBase;

/**
 *
 * @author HP
 */
public class ServiceReservation {
    Connection cn = ConnexionBase.getInstance().getCnx();
    Statement st; //execute la req
    PreparedStatement pst;
   
    public void addCtegorier(Reservation c) throws SQLException {
          
            PreparedStatement pre=cn.prepareStatement("INSERT INTO `resevationn`(`id_user`,`nom`,`id_livre`)" + " VALUES (1,?,?);");
        
            pre.setString(1, c.getNom());
            pre.setInt(2, c.getId_livre());
    
     
        pre.executeUpdate();
        new NoticeWindow(NoticeType.SUCCESS_NOTIFICATION,"reseavation avec sucee",NoticeWindow.LONG_DELAY,NPosition.BOTTOM_RIGHT);

                
       }
     public List<Reservation> getAll() {
List<Reservation> cats = new ArrayList<>();
        String req = "select * from resevationn ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Reservation c= new Reservation(
                        resultSet.getInt("id"),
                       
                        resultSet.getString("nom"),
                        resultSet.getInt("id_livre")
                      
                       
                        
                );
                cats.add(c); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cats;
    }


}
