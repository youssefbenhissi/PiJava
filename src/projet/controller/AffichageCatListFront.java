/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;




import projet.models.Articles;
import projet.models.Categories;
import projet.service.GestionArticles;
import projet.service.GestionCategories;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;


/**
 *
 * @author geek alaa
 */
public class AffichageCatListFront implements Initializable{
    
    @FXML
     private Label CatTitre;
    
   private int id;
    
 
    
    
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
    
    
    
    public void SetCatTitle(String Title){
        this.CatTitre.setText(Title);
    }
    
  public void SetCatID(int id){
        this.CatTitre.setId(Integer.toString(id));
        this.id=id;
    }
  
        public void AfficherCat() throws IOException{
           Stage stage = (Stage) CatTitre.getScene().getWindow();
                  
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/affichageArtiParCatFront.fxml"));
        Parent root = loader.load();
        AffichageArticleParCategorie Affich;
                Affich = loader.getController();
                System.out.println(this.CatTitre.getId());
                Affich.AffichParCat(Integer.parseInt(this.CatTitre.getId()));
                
               // Affich.LoadArticleParCat(this.id);
        stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
        stage.setTitle("Blog : Catégorie");
        Scene scene = new Scene(root);
        stage.setScene(scene);  
       }
    
  
    
   


    
    
    
   
    
    
      
}
