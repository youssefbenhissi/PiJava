/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog;

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
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
        
    }
    
    
    
    
    
    
    
    
    
    
    
     public void AfficherTags(){
         
         String sql = "SELECT * FROM tags";
 
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
    Tags tag = new Tags(idint, nom);
    String output = "TAG #%d: %s - %s";
    System.out.println(String.format(output, ++count, tag.getId(), tag.getNom()));
}
 
       
       } catch (SQLException ex) {
           System.out.println("Une erreur est survenue ! "); 
           
        }
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
 
PreparedStatement statement;
        try 
        {
    statement = conn.prepareStatement(sql);
statement.setString(1, Integer.toString(tag.getId()));
 
int rowsDeleted = statement.executeUpdate();
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
      
      
      
      
      
      
      
      
      
      
      
      
      
       
    
}
