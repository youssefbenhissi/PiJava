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
import projet.utils.DbConnection;
import projet.models.Inscription;
import static projet.service.CategorieClubService.statement;

/**
 *
 * @author youssef
 */
public class InscriptionService {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();
    
    public List<Inscription> selectAllInscris() {
        ArrayList<Inscription> clubs = new ArrayList<>();
        String req = "SELECT c.id,c.reponsePr,c.reponseDe,c.reponseTr,c.club_id,c.eleve_id,c.status,c.questionPr,c.questionDe,c.questionTr,cl.nom FROM inscription c,club cl where cl.id=c.club_id";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Inscription c = new Inscription();
                c.setId(rs.getInt(1));
                c.setReponsePr(rs.getString(2));
                c.setReponseDe(rs.getString(3));
                c.setReponseTr(rs.getString(4));
                c.setIdClub(rs.getInt(5));
                c.setIdUser(rs.getInt(6));
                c.setStatus(rs.getString(7));
                c.setQuestionPr(rs.getString(8));
                c.setQuestionDe(rs.getString(9));
                c.setQuestionTr(rs.getString(10));
                c.setNomClub(rs.getString(11));
                c.setBtn_delete(new Button("Supprimer"));
                c.setBtn_confirmer(new Button("valider"));
                
                clubs.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clubs;
    }
    public boolean supprimerInscription(int id) {
        
         String req="DELETE FROM inscription WHERE id="+id;
              
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
    public boolean confirmerInscription(int id) {
        
         String req="UPDATE inscription SET status= 'Approuvée' WHERE id="+id;
              
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
    public List<Integer> getState() {
        String req11 = "Select id from club";
        List<Integer> liste = new ArrayList<Integer>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req11);

            while (rs.next()) {

                liste.add(rs.getInt(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }
    public String getState1(int x) {
        String g = "";
        String req11 = "Select nom  From club where id=? ";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req11);

            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                g = rs.getString(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }
    public Integer getState12(int x) {
        int g = 0;
        String req11 = "Select id From inscription where club_id=? ";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req11);

            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                g++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }
    public void ajouterInscription(Inscription c) {
        String requete = "INSERT INTO inscription (reponsePr,reponseDe,reponseTr,club_id,eleve_id,status,questionPr,questionDe,questionTr)"
                + " VALUES ('" + c.getReponseTr()+ "','"+c.getReponseDe()+ "','"+c.getReponseTr()+ "','"+c.getIdClub()+"','"+c.getIdUser()+"','"+c.getStatus()+"','"+c.getQuestionPr()+"','"+c.getReponseDe()+"','"+c.getReponseTr()+"');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }
    public int retournerIdClub(int id){
        int idClub=0;
        String req = "SELECT c.club_id FROM inscription c where c.id = "+id;
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
     public int retournerIdUtilisateur(int id){
        int idClub=0;
        String req = "SELECT c.eleve_id FROM inscription c where c.id = "+id;
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
    public Club retournerCapacite(int id){
        Club c=new Club();
        String req = "SELECT c.id,c.capacite,c.nom FROM club c where c.id = "+id;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                
                c.setId(rs.getInt(1));
                c.setCapacite(rs.getInt(2));
                c.setNom(rs.getString(3));
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return c;
    }
    public boolean modifierCapacite(int id,int capacite) {
        String req = "UPDATE club SET capacite= ? WHERE id= "+id;
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
}
