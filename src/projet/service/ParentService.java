/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.util.List;
import projet.models.Parent;

import java.util.ArrayList;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import projet.utils.DbConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Button;
import projet.models.Eleve;
import projet.models.Utilisateur;

/**
 *
 * @author user
 */
public class ParentService implements IParent {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    @Override
    public List<Parent> selectAllParent() {
        ArrayList<Parent> listeParent = new ArrayList<>();
        String req = "SELECT nom,prenom,numTelephone,image FROM parents";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Parent p = new Parent();
                p.setNom(rs.getString(1));
                p.setPrenom(rs.getString(2));
                p.setNumTelephone(rs.getInt(3));
                p.setImage(rs.getString(4));
                p.setBtn_delete(new Button("Supprimer"));
                listeParent.add(p);
            }
        } catch (Exception ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeParent;
    }

    public void supprimerParents(int x) {
        String sql = "DELETE FROM parents WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, Integer.toString(x));
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean ajouterParent(Parent p) {
        System.out.println("nom" + p.getNom());
        System.out.println("prenom" + p.getPrenom());
        System.out.println("num" + p.getNumTelephone());
        System.out.println("age" + p.getImage());
        String requete = "INSERT INTO parents (nom,prenom,numTelephone,image)"
                + " VALUES ('" + p.getNom() + "','" + p.getPrenom() + "','" + p.getNumTelephone() + "','" + p.getImage() + "');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("parent Ajouté");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);

        }
        return false;

    }

    @Override
    public void modifierP(Utilisateur p) {
        String req = "UPDATE fos_user SET nom= ?,prenom= ?,email= ?,email_canonical= ?,telephone=? ,image= ? , password= ? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getEmail());
            pst.setString(4, p.getEmail());
            pst.setInt(5, p.getTelephone());
            pst.setString(6, p.getImage());
            pst.setString(7, p.getMotDePasse_Utilisateur());
            
          
            //pst.setString(7, p.getMotDePasse_Utilisateur());
            pst.setInt(8, p.getId_Utilisateur());
            pst.executeUpdate();

        } catch (SQLException e1) {
            System.out.println(e1);
        }

    }

    public Utilisateur selectUser(int id) {
//        ArrayList<Parent> listeParent = new ArrayList<>();
        Utilisateur us = new Utilisateur();
        String req = "SELECT nom,prenom,telephone,email,image,id ,password FROM fos_user where id = "+id;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                
                us.setNom(rs.getString(1));
                us.setPrenom(rs.getString(2));
                us.setTelephone(rs.getInt(3));
                us.setEmail(rs.getString(4));
                us.setImage(rs.getString(5));
                us.setId_Utilisateur(rs.getInt(6));
                us.setMotDePasse_Utilisateur(rs.getString(7));
                
            }
        } catch (Exception ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return us;
    }

}
