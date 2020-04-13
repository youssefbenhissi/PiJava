/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import utils.ConnexionBase;
import Iservice.IlivreService;
import models.categorier;
import models.livre;
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
import javax.swing.JOptionPane;
import controller.FTPUploader;

/**
 *
 * @author HP
 */
public class ServiceLivre implements IlivreService{
static Statement statement = null;
    PreparedStatement pst;
     Connection cn = ConnexionBase.getInstance().getCnx();
 private Statement ste;

    Connection connection;


  /*
@Override
    public void addLivre(livre l) throws SQLException {
 Connection cn = ConnexionBase.getInstance().getCnx();
 String requete = "INSERT INTO livre (id_category,nom,description,auteur,nombredepage)" + " VALUES ('" + l.getId_categorie() +  "','" + l.getNom()+ "','" + l.getDecription() + "','" + l.getAuteur() + "','" + l.getNombredepage() + "');";

        try {
           
             pst = cn.prepareStatement(requete);
            pst.executeUpdate(requete);
            JOptionPane.showMessageDialog(null,"Ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }

    @Override
    public List<livre> getAll() {
List<livre> cats = new ArrayList<>();
        String req = "SELECT l.id,ct.libelle,l.nom,l.description,l.auteur,l.nombredepage FROM livre l ,category ct where l.id_category=ct.id; ";
                PreparedStatement preparedStatement;

        try {
             preparedStatement = cn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                livre c = new livre();
                c.setId(resultSet.getInt(1));
                 c.setLibelle(resultSet.getString(2));
                c.setNom(resultSet.getString(3));
                c.setDecription(resultSet.getString(4));
                c.setAuteur(resultSet.getString(5));
                c.setNombredepage(resultSet.getInt(6));
              
              
                      
                       
                        
           
                cats.add(c); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cats; 
    }

    @Override
    public void deleteLivre(int id) {
  
String req = "delete from livre where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null,"supprition avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
                

    }

    @Override
    public List<livre> rechercheCategories(String str) {
 List<livre> livre=new ArrayList<livre>();
        String sql = "SELECT * FROM livre WHERE nom LIKE ? ";
        PreparedStatement statement;
        
        try {

         statement= cn.prepareStatement(sql);
            statement.setString(1,"%" + str + "%");
            //statement.setString(2, "%" + str + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
               livre c = new livre();
                c.setId(rs.getInt(1));
                 c.setLibelle(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setDecription(rs.getString(4));
                c.setAuteur(rs.getString(5));
                c.setNombredepage(rs.getInt(6));
               livre.add(c);
            }
        } catch (SQLException ex) {
            
        }
        return livre;
    


    }
*/

    @Override
    public List<livre> getAll() {
        List<livre> cats = new ArrayList<>();
        String req = "SELECT l.id,ct.libelle,l.nom,l.description,l.auteur,l.nombredepage FROM livre l ,category ct where l.id_category=ct.id; ";
                PreparedStatement preparedStatement;

        try {
             preparedStatement = cn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                livre c = new livre();
                c.setId(resultSet.getInt(1));
                 c.setLibelle(resultSet.getString(2));
                c.setNom(resultSet.getString(3));
                c.setDescription(resultSet.getString(4));
                c.setAuteur(resultSet.getString(5));
                c.setNombredepage(resultSet.getInt(6));
              
              
                      
                       
                        
           
                cats.add(c); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cats;
    }
    
  /*  
    @Override
    public void addLivre(livre l) throws SQLException {
 Connection cn = ConnexionBase.getInstance().getCnx();
 String requete = "INSERT INTO livre (id_category,nom,description,auteur,nombredepage)" + " VALUES ('" + l.getId_categorie() +  "','" + l.getNom()+ "','" + l.getDescription() + "','" + l.getAuteur() + "','" + l.getNombredepage() + "');";

        try {
           
             pst = cn.prepareStatement(requete);
            pst.executeUpdate(requete);
            JOptionPane.showMessageDialog(null,"Ajout avec succes");
        } catch (SQLException ex) {
            System.out.println(ex);
        }    }
    */
     @Override
    public List<livre> rechercheCategories(String str) {
 List<livre> livre=new ArrayList<livre>();
        String sql = "SELECT * FROM livre WHERE nom LIKE ? ";
        PreparedStatement statement;
        
        try {

         statement= cn.prepareStatement(sql);
            statement.setString(1,"%" + str + "%");
            //statement.setString(2, "%" + str + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
               livre c = new livre();
                c.setId(rs.getInt(1));
                 c.setLibelle(rs.getString(2));
                c.setNom(rs.getString(3));
                c.setDescription(rs.getString(4));
                c.setAuteur(rs.getString(5));
                c.setNombredepage(rs.getInt(6));
               livre.add(c);
            }
        } catch (SQLException ex) {
            
        }
        return livre;
    


    }

    @Override
    public void deleteLivre(int id) {
String req = "delete from livre where id =?";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null,"supprition avec succes");

        } catch (SQLException ex) {
            ex.printStackTrace();
          
        }
      
    }

    @Override
    public void addLivre(livre l) throws SQLException {
 PreparedStatement pre=cn.prepareStatement("INSERT INTO `livre`(`id_category`, `nom`, `nom_image`, `description`, `auteur`, `nombredepage`)" + " VALUES (?,?,?,?,?,?);");
        FTPUploader ftp=new FTPUploader();
        ftp.FTPTransfer(l.getFile());
        pre.setInt(1, l.getId_categorie());
        pre.setString(2, l.getNom());
        pre.setString(3, l.getFile().getName());

        pre.setString(4, l.getDescription());
        pre.setString(5, l.getAuteur());
         pre.setInt(6, l.getNombredepage());
        pre.executeUpdate();
                JOptionPane.showMessageDialog(null,"Ajout avec succes");


    }
    public List<livre> EnsembleDesLivre(int id) {
        List<livre> categories = new ArrayList<livre>();
        String sql = "SELECT l.id,l.nom,l.description,l.nom_image FROM livre l where l.id_category LIKE ? ;";
       
        PreparedStatement statement;

        try {

            statement = cn.prepareStatement(sql);
            statement.setString(1, "%" + id + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                livre c = new livre();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setNom_image(rs.getString(4));
               
                categories.add(c);
            }
        } catch (SQLException ex) {

        }
        return categories;
    }

}
    

    

  
   

   




    

    

   

