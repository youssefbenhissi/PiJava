/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import utils.ConnexionBase;
import Iservice.IcategorierService;
import static Service.ServiceLivre.statement;
import models.categorier;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import controller.FTPUploader;

/**
 *
 * @author HP
 */
public class ServiceCategorier implements IcategorierService {
Connection cn = ConnexionBase.getInstance().getCnx();
    Statement st; //execute la req
    PreparedStatement pst;
   
    
    private String file;
    
   

  /*  @Override
    public void addCtegorier(categorier c) throws SQLException  {
        try {
 
 
           Connection con =ConnexionBase.getInstance().getCnx();
            //Excecute la requete et envoie dans ResultSet
 
 
 
            String RequeteAjout = "INSERT INTO `category`( `id`,`libelle`, `description`) VALUES ('"+c.getId()+"','"+c.getLibelle()+"','"+c.getDescription()+"')";
            /*TFPrenom.getText(), TFNom.getText(), TFPoste.getText(), TFDate_de_naissance.getText() , TFNationalite.getText()*/
           /*   
       
           pst = con.prepareStatement(RequeteAjout);
            pst.executeUpdate(RequeteAjout);
            System.out.println("categorie Ajoutee");
        
          
           

        } 
        
        catch (SQLException e) {
            System.out.println("Ajout impossible à effectuer.\nErreur :" + e);
        }
    
    }
*/

    @Override
    public void deleteCtegorier(int id) {
String req = "delete from category where id =?";
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
    public void updateCtegorier(categorier c) throws SQLException {
        String req = "UPDATE `category` SET `id`= '"+ c.getId() +"',`libelle`= '"+ c.getLibelle()+"',`description`= '"+ c.getDescription()+"' ";
        
        try {
              pst = cn.prepareStatement(req);
            
            pst.executeUpdate(req);
            
            System.out.println("categorie Ajoutee");
        
          
            //TEST DEBUG///

        } 
        
        catch (SQLException e) {
            System.out.println("Ajout impossible à effectuer.\nErreur :" + e);
        }
       
    }

    @Override
    public List<categorier> getAll() {
List<categorier> cats = new ArrayList<>();
        String req = "select * from category ";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = cn.prepareStatement(req);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                categorier c= new categorier(
                        resultSet.getInt("id"),
                       
                        resultSet.getString("libelle"),
                        resultSet.getString("description")
                      
                       
                        
                );
                cats.add(c); 
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cats;
    }

    @Override
    public List<categorier> rechercheCategories(String str) {
          List<categorier> categorier=new ArrayList<categorier>();
        String sql = "SELECT * FROM category WHERE libelle LIKE ? ";
        PreparedStatement statement;
        
        try {

         statement= cn.prepareStatement(sql);
            statement.setString(1,"%" + str + "%");
            //statement.setString(2, "%" + str + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
               categorier c = new categorier();
                c.setId(rs.getInt(1));
                c.setLibelle(rs.getString(2));
                 c.setDescription(rs.getString(3));
               categorier.add(c);
            }
        } catch (SQLException ex) {
            
        }
        return categorier;
    }

    @Override
    public void addCtegorier(categorier c) throws SQLException {
          
    
        

        
        
        PreparedStatement pre=cn.prepareStatement("INSERT INTO `category`(`libelle`, `description`, `nom_image`)" + " VALUES (?,?,?);");
        FTPUploader ftp=new FTPUploader();
        ftp.FTPTransfer(c.getFile());
        pre.setString(1, c.getLibelle());
        pre.setString(2, c.getDescription());
        pre.setString(3, c.getFile().getName());
        pre.executeUpdate();
                JOptionPane.showMessageDialog(null,"Ajout avec succes");

    
    
        
   
    }

        public HashMap<String, Integer> getAllCategorie() {

        HashMap<String, Integer> mapCategorie = new HashMap<String, Integer>();

        String req = "SELECT * FROM category";

        try {
            statement = cn.createStatement();
            ResultSet res = statement.executeQuery(req);
            categorier categorie;
            while (res.next()) {
                categorie = new categorier(res.getInt(1), res.getString(2));
                mapCategorie.put(categorie.getLibelle(), categorie.getId());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mapCategorie;
    }


   

}
    

