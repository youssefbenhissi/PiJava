/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.controller;

import static gestion_blog.controller.AjoutArticle.html2text;
import gestion_blog.models.Articles;
import gestion_blog.models.Categories;
import gestion_blog.models.Tags;
import gestion_blog.service.GestionArticles;
import gestion_blog.service.GestionCategories;
import gestion_blog.service.GestionTags;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author geek alaa
 */
public class ModifierCategorie implements Initializable{
    
    
    

    
    @FXML
     private TextField titre;
    
    @FXML
     private TextField descri;
    
   
    
    
    
    
    
    
    
   
    
   
    
     @FXML
     private Button Retourner;
    
   
    
  
    @FXML
    private Button submitButton;
    
    
    private int id;
    private String titre_Cat ="";
    private String Descri_Cat ="" ;
   
    

   private boolean titrechange =false;
      private boolean descchange =false;
    
    
    private boolean errortitre = false;
    private boolean errordescri = false;
   
    
     Categories categoriemodif ;   
    
      List<Categories> listcat = new ArrayList<Categories>();
   
 

    
    public void initialize(URL location, ResourceBundle resources) {

        
        
        titre.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(newValue);
         
            /*try {
               
                this.displayTray("Titre mini 3 caractères  -  200 max");
            } catch (AWTException ex) {
                Logger.getLogger(AjoutArticle.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            if(newValue.trim().isEmpty()){
                this.errortitre = true;
                titre.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                titre.clear();
            }else if(newValue.length() < 3){
                 this.errortitre = true;
                titre.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                }else if(newValue.length() > 200){
                     this.errortitre = true;
                titre.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
            }else{
                    titrechange = true;
                    this.errortitre = false;
                    this.titre_Cat = newValue;
                titre.setStyle("-fx-background-radius: 1em ;");
            }
            
        });
        
        
        
        descri.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(newValue);
            
           
            if(newValue.trim().isEmpty()){
                this.errordescri = true;
                descri.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                descri.clear();
            }else if(newValue.length() < 10){
                this.errordescri = true;
                descri.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                }else if(newValue.length() > 300){
                    this.errordescri = true;
                descri.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
            }else{
                    descchange = true;
                    this.Descri_Cat = newValue;
                    this.errordescri = false;
                descri.setStyle("-fx-background-radius: 1em ;");
            }
            
        });
        
        
        
        
        
        
        
        
       
        
     
       
       
       
       
       
       
       
        
       
       
    }
    
    
   public void setId(int id) {
        this.id = id;
         
    }
   
   
   public void load(){
       
   GestionCategories gstcat = new GestionCategories();
   listcat = gstcat.getcatlist();
   Categories categorie2 = new Categories(id);
   int index = listcat.indexOf(categorie2);
   categoriemodif = listcat.get(index);
   
   titre.setText(categoriemodif.getNom());
   descri.setText(categoriemodif.getDescription());
if(!titrechange){
    this.titre_Cat = categoriemodif.getNom();
}

if(!descchange){
    this.Descri_Cat = categoriemodif.getDescription();
}
        

        
        
       
       
       
   
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    
   
    
  
 
    
    public void displayTray(String msg) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

       
        Image image = Toolkit.getDefaultToolkit().createImage("/gestion_blog/images/how-to-edit-a-png-image-1.png");
       
        TrayIcon trayIcon = new TrayIcon(image, "Modification Catégorie");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        //trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Modification Catégorie", msg, TrayIcon.MessageType.INFO);
    }
    

            

            
 
         @FXML
 public void handleModificationCateg(ActionEvent event) throws MalformedURLException, IOException {
             GestionCategories gst = new GestionCategories();
             Categories categoriem = new Categories(id, this.titre_Cat, this.Descri_Cat);
     //System.out.println(article);
             
              int errors =0;
    
  
     
     
    
  
             if(errortitre){
                  titre.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                errors = errors+1;
             }else if(errordescri){
                  descri.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
             errors = errors+1;
             }else{
 
                 if(gst.ModifierCat(categoriem)){
         
                  System.out.println("true : true");
                  
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/GestionCatego.fxml"));
             Node[] node = new Node[1];
                     
                     
                    node[0] = loader.load();
            
             
            //Get controller of scene2
            GestionCatego contr;
                contr = loader.getController();
                
                contr.Update();
                 try {
                this.displayTray("Catégorie Modifié");
            } catch (AWTException ex) {
                Logger.getLogger(AjoutArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
                  Stage stage = (Stage) submitButton.getScene().getWindow();
                  
                   URL url = new File("src/gestion_blog/views/GestionCatego.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion Catégories");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    
    //stage.close();*/
    
    
             }else{
                 System.out.println("error : error");
             }
                 
             }
             
       
                 
                 
                 
                 
                 
                 
                 
           
             
 }

    public void retouraccu() throws MalformedURLException, IOException{
         Stage stage = (Stage) Retourner.getScene().getWindow();
        URL url = new File("src/gestion_blog/views/GestionCatego.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion des Catégories");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

   
    
    
    
    
    
    
}
