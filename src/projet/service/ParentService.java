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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.mindrot.jbcrypt.BCrypt;
import projet.models.Eleve;
import projet.models.Utilisateur;

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
    public ObservableList<Utilisateur> selectAllParent() {
        ObservableList<Utilisateur> listeParent = FXCollections.observableArrayList();
        String req = "SELECT * FROM fos_user WHERE roles = 'a:1:{i:0;s:11:\"ROLE_PARENT\";}'" ;
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Utilisateur p = new Utilisateur();
                p.setId_Utilisateur(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
                p.setTelephone(rs.getInt("telephone"));
                p.setImage(rs.getString("image"));
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

    public void supprimerParent(Utilisateur u) {
        
     String requete = " delete from fos_user where id='"+u.getId_Utilisateur()+"'" ;
        try {
            
            statement=connection.createStatement();
            statement.executeUpdate(requete);
        } catch (SQLException ex) {
            Logger.getLogger(ParentService.class.getName()).log(Level.SEVERE, null, ex);
        }
   
           }
    
        
        
        
    
    @Override
    public boolean ajouterParent(Utilisateur p) {
         int workload = 13;
        int status = 0; 
       String mdp = BCrypt.hashpw("1234", BCrypt.gensalt(workload));
        mdp =   mdp.replaceFirst("2a", "2y");
        String requete = "INSERT INTO fos_user (username,username_canonical,email,email_canonical,enabled,password,roles,image,telephone,nom,prenom)"
                + " VALUES ('" + p.getNom_Utilisateur()+ "','" + p.getNom_Utilisateur() + "','" + p.getEmail()+ "','" +  p.getEmail()+ "','1','"+  mdp+ "','a:1:{i:0;s:11:\"ROLE_PARENT\";}','"  + p.getImage() + "','"+p.getTelephone()+"','"+p.getNom()+"','"+p.getPrenom()+"');";

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
    public void modifierP(Utilisateur p) {
        String req = "UPDATE fos_user SET nom= ?,prenom= ?,email= ?,email_canonical= ?,telephone=? ,image= ? ,password=? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, p.getNom());
            pst.setString(2, p.getPrenom());
            pst.setString(3, p.getEmail());
            pst.setString(4, p.getEmail());
            pst.setInt(5, p.getTelephone());
            pst.setString(6, p.getImage());
            
          
            pst.setString(7, p.getMotDePasse_Utilisateur());
            pst.setInt(8, p.getId_Utilisateur());
            pst.executeUpdate();

        } catch (SQLException e1) {
            System.out.println(e1);
        }

    }
    
        }

    
    

