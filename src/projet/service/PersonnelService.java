/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.util.List;
import projet.models.Personnel;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import projet.utils.DbConnection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.models.Eleve;




/**
 *
 * @author user
 */
public class PersonnelService implements IPersonnel {
    static Statement statement = null;
    PreparedStatement pst;
DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    /**
     *
     * @return 
     */
    @Override
    public List<Personnel> selectAllPersonnel(){
        
        
        ArrayList<Personnel> listePersonnel = new ArrayList<>();
        String req = "SELECT matricule,nom,prenom,dateDebTravail,description,image,soldeConge,type FROM personnel";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Personnel pe = new Personnel();
                pe.setMatricule(rs.getInt(1));
                pe.setNom(rs.getString(2));
                pe.setPrenom(rs.getString(3));
                pe.setDate_debutTravail(rs.getDate(4));
                pe.setDescription(rs.getString(5));
                pe.setImage(rs.getString(6));
                pe.setSoldeConge(rs.getInt(7));
                pe.setType(rs.getString(8));
                
                listePersonnel.add(pe);
            }
        } catch (Exception ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listePersonnel;
    }

    
    @Override
    public void supprimerPersonnel(int x) {
        String sql = "DELETE FROM personnel WHERE matricule = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, x);
            statement.executeUpdate();
        } catch (java.sql.SQLException ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
public void ajouterPersonnel(Personnel pe) {
        String requete = "INSERT INTO personnel (matricule,nom,prenom,dateDebTravail,soldeConge,type,description,image)"
                + " VALUES ('" + pe.getMatricule() + "','" + pe.getNom() + "','" + pe.getPrenom() + "','" + pe.getDate_debutTravail() + "','" + pe.getSoldeConge() + "','" + pe.getType() + "','" + pe.getDescription() + "','" + pe.getImage() + "');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("Personnel Ajouté");
        } catch (java.sql.SQLException ex) {
            System.out.println(ex);
        }

    }
 @Override
    public void modifierPe(Personnel pe) {
        String req = "UPDATE personnel SET nom= ?,prenom= ?,soldeConge= ?,description= ?,type= ?,image= ? WHERE matricule= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, pe.getNom());
            pst.setString(2, pe.getPrenom());
            pst.setInt(3, pe.getSoldeConge());
            pst.setString(4, pe.getDescription());
            pst.setString(5, pe.getType());
            pst.setString(6, pe.getImage());
            pst.setInt(7, pe.getMatricule());
           
            pst.executeUpdate();

        } catch (java.sql.SQLException e1) {
            System.out.println(e1);
        }

    }


    
}
