/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.service;


import gestion_blog.models.Articles;
import gestion_blog.models.Categories;
import gestion_blog.utils.Conx_BD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek alaa
 */
public class GestionCategories {
    
        List<Categories> listcat = new ArrayList<Categories>();
    Conx_BD connbase = new Conx_BD();
    Connection conn = connbase.obtenirconnexion();
 List<Articles> listarticle = new ArrayList<Articles>();
    public GestionCategories() {
    }
    
     
    public boolean AjouterCat(Categories cat){
    String sql = "INSERT INTO categorie (id, nom, description) VALUES (?, ?, ?)";
    PreparedStatement statement;
        try 
        {
        statement = conn.prepareStatement(sql);
        statement.setString(1, null);
        statement.setString(2, cat.getNom());
        statement.setString(3, cat.getDescription());
         
        int rowsInserted = statement.executeUpdate();
        
            if(rowsInserted > 0) {
                this.UpdateListCat();
            return true ;
            }else{
            return false;
            }
        } catch (SQLException ex) {
          //  Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
        
    }
    
    
    
    
    
    
    
    
    
    
    
     public void AfficherCat(){
         
         String sql = "SELECT * FROM categorie";
 
         PreparedStatement statement;
        try 
        {
         statement = conn.prepareStatement(sql);
         ResultSet result = statement.executeQuery(sql);
         
int count = 0;
 
while (result.next()){
    String id = result.getString("id");
    int idint = Integer.parseInt(id);
    String nom = result.getString("nom");
    String description = result.getString("description");
    Categories cat = new Categories(idint, nom,description);
    String output = "Catégorie #%d: %s - %s - %s";
    System.out.println(String.format(output, ++count, cat.getId(), cat.getNom(), cat.getDescription()));
}
 
       
       } catch (SQLException ex) {
           System.out.println("Une erreur est survenue ! "); 
           
        }
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
      public boolean ModifierCat(Categories cat){
          String sql = "UPDATE categorie SET nom=? , description =? WHERE id=?";
 
PreparedStatement statement;
        try 
        {
    statement = conn.prepareStatement(sql);
statement.setString(1,cat.getNom());
statement.setString(2,cat.getDescription());
statement.setString(3, Integer.toString(cat.getId()));
 
int rowsUpdated = statement.executeUpdate();
if (rowsUpdated > 0) {
    this.UpdateListCat();
    return true;
}
 } catch (SQLException ex) {
           
           return false;
        }
        return false;
      }
      
      
      
      
      
      
      
      
      
      
      
      public boolean SupprimerCat(Categories cat){
          String sql = "DELETE FROM categorie WHERE id=?";
 
PreparedStatement statement;
        try 
        {
            GestionArticles gst = new GestionArticles();
            listarticle = gst.getArticles();
            for(int i = 0 ; i < listarticle.size(); i++){
                if(listarticle.get(i).getCat_id() == cat.getId()){
                    gst.SupprimerArticle(listarticle.get(i));
                }
            }
            
    statement = conn.prepareStatement(sql);
statement.setString(1, Integer.toString(cat.getId()));
 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    this.UpdateListCat();
    return true;
}
 } catch (SQLException ex) {
           
           return false;
        }
        return false;
      }
      
      
      
      public void UpdateListCat(){
         
         String sql = "SELECT * FROM categorie";
 
         PreparedStatement statement;
        try 
        {
         statement = conn.prepareStatement(sql);
         ResultSet result = statement.executeQuery(sql);
    
 
while (result.next()){
    String id = result.getString("id");
    int idint = Integer.parseInt(id);
    String nom = result.getString("nom");
    String description = result.getString("description");
    Categories cat = new Categories(idint, nom, description);
    listcat.add(cat);
}
 
       
       } catch (SQLException ex) {
           System.out.println("Une erreur est survenue ! ");  
        }
     }     
      
      
      
        public List<Categories> getcatlist() {
          this.UpdateListCat();
          
        return listcat;
    }
        
        public String getcatbyid(int id) {
          this.UpdateListCat();
          for (int i = 0; i < listcat.size(); i++) {
              if(this.listcat.get(i).getId() == id){
                  return this.listcat.get(i).getNom();
              }
          }
        return null;
    }
      
        
         public void RechercheCat(String terme){
         
         String sql = "SELECT * FROM categorie WHERE categorie.nom LIKE ?";
 
         PreparedStatement statement;
        try 
        {
         statement = conn.prepareStatement(sql);
          statement.setString(1, terme);
         ResultSet result = statement.executeQuery();
    
 
while (result.next()){
    String id = result.getString("id");
    int idint = Integer.parseInt(id);
    String nom = result.getString("nom");
    String description = result.getString("description");
    Categories cat = new Categories(idint, nom, description);
    listcat.add(cat);
}
 
       
       } catch (SQLException ex) {
           System.out.println("Une erreur est survenue ! ");  
        }
     }       
      
      
     public List<Categories> getArticlesSearch(String terme) {
              terme = "%"+terme+"%";
          this.RechercheCat(terme);
          
        return listcat;
    } 
      
      
      
      
      
   
    
}
