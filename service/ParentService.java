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
import projet.models.Eleve;

/**
 *
 * @author user
 */
public class ParentService implements IParent{
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
            statement.setInt(1, x);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimerParent(int i) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    @Override
    public boolean ajouterParent(Parent p) {
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
        return false ;

    }
     @Override
    public void modifierP(Parent p) {
        String req = "UPDATE parents SET nom= ?,prenom= ?,numTelephone= ?,image= ? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setInt(3, p.getNumTelephone());
            pst.setString(4, p.getImage());
            
            pst.setInt(5, p.getId());

            pst.executeUpdate();

        } catch (SQLException e1) {
            System.out.println(e1);
        }

    }
    
        }

    
    

