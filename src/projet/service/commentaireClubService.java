/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.models.commentaireClub;
import projet.utils.DbConnection;

/**
 *
 * @author youssef
 */
public class commentaireClubService {
    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();
    public void  ajouterCommentaireEvenement(String commentaireEvenement,int id) throws SQLException {
        
        String sql = "INSERT INTO commantaire(user,message) VALUES('" + id + "','" + commentaireEvenement + "')";
        pst = connection.prepareStatement(sql);
        pst.executeUpdate(sql);


    }

   
    public  List<commentaireClub> afficherCommentaire() {
        List<commentaireClub> tsCommentaire = new ArrayList<commentaireClub>();
        ResultSet resultSet = null;
        // ResultSet resultSet2 = null;
         String req = "SELECT c.message,f.username FROM commantaire c,fos_user f where f.id=c.user";
       try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);

            while (res.next()) {
                commentaireClub p = new commentaireClub ();
                
               // p.setId_commentaire(res.getInt(3));
                p.setUsername(res.getString(2));
                p.setMeessage(res.getString(1));
             
                tsCommentaire.add(p);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return tsCommentaire;

    }

        public String retournerEmailUtilisateur(int id){
        String idClub="";
        String req = "SELECT c.username FROM fos_user c where c.id = "+id;
         try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                idClub=rs.getString(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idClub;
    }
}
