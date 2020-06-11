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
import javafx.scene.control.Button;
import projet.models.CategorieClub;
import projet.models.Club;
import projet.models.Evenement;
import projet.models.Inscription;
import projet.models.reservation;
import projet.utils.DbConnection;

/**
 *
 * @author youssef
 */
public class ReservationService {
     static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();
     public void ajouterInscription(reservation c) {
        String requete = "INSERT INTO reservation (statut,Evenement_id,id_user)"
                + " VALUES ('" + c.getStatus()+ "','"+c.getIdevenement()+ "','"+c.getIdUser()+"');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
      public String retournerEmailUtilisateur(int id){
        String idClub="";
        String req = "SELECT c.email FROM fos_user c where c.id = "+id;
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
     public int retournerIdUtilisateur(int id){
        int idClub=0;
        String req = "SELECT c.id_user FROM reservation c where c.id = "+id;
         try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                idClub=rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idClub;
    }
     public List<reservation> selectAllReservations() {
        ArrayList<reservation> clubs = new ArrayList<>();
        String req = "SELECT c.id,c.statut,cEvenement.nomE,cUser.username,c.Evenement_id,c.id_user FROM reservation c,evenement cEvenement,fos_user cUser  where ( c.Evenement_id = cEvenement.id AND c.id_user = cUser.id  )";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                reservation c = new reservation();
                System.out.println(rs.getInt(1));
                c.setId(rs.getInt(1));
                c.setStatus(rs.getString(2));
                c.setNomEvenement(rs.getString(3));
                c.setNomUser(rs.getString(4));
                c.setIdevenement(rs.getInt(5));
                c.setIdUser(rs.getInt(6));
                
                c.setBtn_confirmer(new Button("confirmer"));
                clubs.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clubs;
    }
     public boolean confirmerReservation(int id) {
        
         String req="UPDATE reservation SET statut= 'Confirme' WHERE id="+id;
              
        try {
            pst = connection.prepareStatement(req);
            int res = pst.executeUpdate();

            if(res > 0) {
                    return true;
            }	
        } catch (SQLException e1) {
                e1.printStackTrace();
        }   
        return false;
    }
     public int retournerIdClub(int id){
        int idClub=0;
        String req = "SELECT c.Evenement_id FROM reservation c where c.id = "+id;
         try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                idClub=rs.getInt(1);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return idClub;
    }
     public Evenement retournerCapacite(int id){
        Evenement c=new Evenement();
        String req = "SELECT c.id,c.capaciteE,c.nomE FROM evenement c where c.id = "+id;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                c.setIdEvenement(rs.getInt(1));
                c.setCapaciteEvenement(rs.getInt(2));
                c.setNomEvenement(rs.getString(3));
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
      public boolean modifierCapacite(int id,int capacite) {
        String req = "UPDATE evenement SET capaciteE= ? WHERE id= "+id;
        try {
            pst = connection.prepareStatement(req);
            // System.out.println(c.getId());
            pst.setInt(1,capacite);

            int res = pst.executeUpdate();

            if (res > 0) {
                return true;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }
       public List<reservation> rechercheCategories(String str) {
        List<reservation> categories = new ArrayList<reservation>();
        String sql = "SELECT c.id,c.statut,cEvenement.nomE,cUser.username FROM reservation c,evenement cEvenement,fos_user cUser where ( c.Evenement_id = cEvenement.id AND c.id_user = cUser.id AND cEvenement.nomE LIKE ? )";
        PreparedStatement statement;

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + str + "%");
            //statement.setString(2, "%" + str + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                 reservation c = new reservation();
                System.out.println(rs.getInt(1));
                c.setId(rs.getInt(1));
                c.setStatus(rs.getString(2));
                c.setNomEvenement(rs.getString(3));
                c.setNomUser(rs.getString(4));
                c.setBtn_confirmer(new Button("confirmer"));
                categories.add(c);
            }
        } catch (SQLException ex) {

        }
        return categories;
    }

}
