/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog;

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
public class GestionArticles {
    
   
    List<Articles> listarticle = new ArrayList<Articles>();
    Conx_BD connbase = new Conx_BD();
    Connection conn = connbase.obtenirconnexion();

    public GestionArticles() {
    }
    
    public boolean AjouterArticle(Articles art){
    String sql = "INSERT INTO article (id, titre, image, contenu, categorie_id, date, description, vues, type) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    PreparedStatement statement;
        try 
        {
        statement = conn.prepareStatement(sql);
        statement.setString(1, null);
        statement.setString(2, art.getTitre());
        statement.setString(3, art.getImage());
        statement.setString(4, art.getContenu());
        statement.setString(5, Integer.toString(art.getCat_id()));
        statement.setString(6, art.getDate());
        statement.setString(7, art.getDescription());
        statement.setString(8, Integer.toString(art.getVues()));
        statement.setString(9, Integer.toString(art.getType()));
         
        int rowsInserted = statement.executeUpdate();
        
            if(rowsInserted > 0) {
                this.UpdateListArticles();
                int newid = listarticle.get(listarticle.size()-1).getId();
                       
                String sql1 = "INSERT INTO cattag (article_id, tag_id) VALUES (?, ?)";
        try 
        {
            for(int i = 0 ; i < art.getListags().size(); i++){
                statement = conn.prepareStatement(sql1);
                statement.setString(1,Integer.toString(newid));
                statement.setString(2, Integer.toString(art.getListags().get(i).getId()));
                statement.executeUpdate();
            }
            
            
        }catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
            return true ;
            }else{
            return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
       
        
    }
    
    
    
    
    
    
    
    
    
    
    
     public void AfficherArticle(){
         UpdateListArticles();
          for(int i = 0 ; i < listarticle.size(); i++){
              System.out.println(listarticle.get(i));
          }
         
     }
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
     
      public boolean ModifierArticle(Articles art){
          String sql = "UPDATE article SET titre =? , image =? , contenu =? , categorie_id =? , date =? , description =?, vues =?, type =?  WHERE id=?";
 
PreparedStatement statement;
        try 
        {
    statement = conn.prepareStatement(sql);
     
        statement.setString(1, art.getTitre());
        statement.setString(2, art.getImage());
        statement.setString(3, art.getContenu());
        statement.setString(4, Integer.toString(art.getCat_id()));
        statement.setString(5, art.getDate());
        statement.setString(6, art.getDescription());
        statement.setString(7, Integer.toString(art.getVues()));
        statement.setString(8, Integer.toString(art.getType()));
        statement.setString(9, Integer.toString(art.getId()));
 
        int rowsUpdated = statement.executeUpdate();
   if (rowsUpdated > 0) {
    this.UpdateListArticles();
    return true;
}
 } catch (SQLException ex) {
           
           return false;
        }
        return false;
      }
      
      
      
      
      
      
      
      
      
      
      
      public boolean SupprimerArticle(Articles art){
          String sql = "DELETE FROM article WHERE id=?";
 
PreparedStatement statement;
        try 
        {
    statement = conn.prepareStatement(sql);
statement.setString(1, Integer.toString(art.getId()));
 
int rowsDeleted = statement.executeUpdate();
if (rowsDeleted > 0) {
    this.UpdateListArticles();
    return true;
}
 } catch (SQLException ex) {
           
           return false;
        }
        return false;
      }
      
      
      
      public void UpdateListArticles(){
         
         String sql = "SELECT * FROM article";
 
         PreparedStatement statement;
        try 
        {
         statement = conn.prepareStatement(sql);
         ResultSet result = statement.executeQuery(sql);
    
 
while (result.next()){
   String id = result.getString("id");
    int idint = Integer.parseInt(id);
    String titre = result.getString("titre");
    String image = result.getString("image");
    String contenu = result.getString("contenu");
    String categorie_id = result.getString("categorie_id");
    int cat_id = Integer.parseInt(categorie_id);
    String date = result.getString("date");
    String description = result.getString("description");
    String vues = result.getString("vues");
    int vu = Integer.parseInt(vues);
    String type = result.getString("type");
    int typ = Integer.parseInt(type);
    List<Tags> listags = new ArrayList<Tags>();
    String sqll = "SELECT * FROM article INNER JOIN cattag INNER JOIN tags ON article.id=cattag.article_id AND tags.id=cattag.tag_id WHERE article_id=?";
        PreparedStatement statementt;
        try 
        {
         statementt = conn.prepareStatement(sqll);
          statementt.setString(1, id);
          ResultSet resulttags = statementt.executeQuery();
         while (resulttags.next()){
             String idtag = resulttags.getString("tag_id");
    int idinttag = Integer.parseInt(idtag);
    String nom = resulttags.getString("nom");
    Tags tag = new Tags(idinttag, nom);
    listags.add(tag);
     //System.out.println(idinttag+"IDTAG "+nom);  
         }
        }catch (SQLException ex) {
            System.out.println("Une erreur est survenue ! ");  
             ex.printStackTrace();
        }
    Articles article = new Articles(titre, image, description, contenu, date, idint, vu, typ, cat_id, listags);
    listarticle.add(article);
}
 
       
       } catch (SQLException ex) {
           System.out.println("Une erreur est survenue ! ");  
           
        }
     }     
      
      
    
    
    
    
}
