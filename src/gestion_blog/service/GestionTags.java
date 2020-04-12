/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.service;


import gestion_blog.models.Tags;
import gestion_blog.utils.Conx_BD;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author geek alaa
 */
public class GestionTags {
    
    
    
    
    private List<Tags> listags = new ArrayList<Tags>();
    Conx_BD connbase = new Conx_BD();
    Connection conn = connbase.obtenirconnexion();

    public GestionTags() {
    }

    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
     
    public boolean AjouterTag(Tags tag){
    String sql = "INSERT INTO tags (id, nom) VALUES (?, ?)";
    PreparedStatement statement;
        try 
        {
        statement = conn.prepareStatement(sql);
        statement.setString(1, null);
        statement.setString(2, tag.getNom());
         
        int rowsInserted = statement.executeUpdate();
        
            if(rowsInserted > 0) {
                this.UpdateListTags();
            return true ;
            }else{
            return false;
            }
        } catch (SQLException ex) {
          //  Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
        
    }
    
    
    
    
    
    
    
    
    
    
    
     public void AfficherTags(){
         
this.UpdateListTags();
         System.out.println(listags.toString());
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
      public boolean ModifierTag(Tags tag){
          String sql = "UPDATE tags SET nom=? WHERE id=?";
 
PreparedStatement statement;
        try 
        {
    statement = conn.prepareStatement(sql);
statement.setString(1,tag.getNom());
statement.setString(2, Integer.toString(tag.getId()));
 
int rowsUpdated = statement.executeUpdate();
if (rowsUpdated > 0) {
    this.UpdateListTags();
    return true;
}
 } catch (SQLException ex) {
           
           return false;
        }
        return false;
      }
      
      
      
      
      
      
      
      
      
      
      
      public boolean SupprimerTag(Tags tag){
          String sql = "DELETE FROM tags WHERE id=?";
          String sql2 = "DELETE FROM cattag WHERE tag_id=?"; 
PreparedStatement statement;
PreparedStatement statement2;
        try 
        {
    statement = conn.prepareStatement(sql2);
statement.setString(1, Integer.toString(tag.getId()));
     statement2 = conn.prepareStatement(sql);
statement2.setString(1, Integer.toString(tag.getId()));
int rowsDeleted = statement.executeUpdate();
statement2.executeUpdate();
if (rowsDeleted > 0) {
    this.UpdateListTags();
    return true;
}
 } catch (SQLException ex) {
           
           return false;
        }
        return false;
      }
      
      
      
      public void UpdateListTags(){
         
         String sql = "SELECT * FROM tags";
 
         PreparedStatement statement;
        try 
        {
         statement = conn.prepareStatement(sql);
         ResultSet result = statement.executeQuery(sql);
    
 
while (result.next()){
    String id = result.getString("id");
    int idint = Integer.parseInt(id);
    String nom = result.getString("nom");
    Tags tag = new Tags(idint, nom);
    listags.add(tag);
}
 
       
       } catch (SQLException ex) {
           System.out.println("Une erreur est survenue ! ");  
            //ex.printStackTrace();
        }
     }     
      
      
      
      
      public List<Tags> getTags() {
          this.UpdateListTags();
          
        return listags;
    }
      
      
      
     public void GetRechercheTags(String terme){
         
         String sql = "SELECT * FROM tags WHERE tags.nom LIKE ?";
 
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
    Tags tag = new Tags(idint, nom);
    listags.add(tag);
}
 
       
       } catch (SQLException ex) {
           System.out.println("Une erreur est survenue ! ");  
            //ex.printStackTrace();
        }
     }    
      
      public List<Tags> getTagsSearch(String terme) {
              terme = "%"+terme+"%";
          this.GetRechercheTags(terme);
          
        return listags;
    } 
      
      
       
    
}
