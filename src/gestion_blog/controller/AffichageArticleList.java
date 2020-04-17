/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.controller;




import gestion_blog.models.Articles;
import gestion_blog.service.GestionArticles;
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
public class AffichageArticleList implements Initializable{
    
    @FXML
     private Label ArticleTitre;
    
    @FXML
     private Label Articlecat;
    
    @FXML
     private Label Articledate;
    
    @FXML
     private Label Articlevues;
    
    @FXML
    private Button modif;
    
    @FXML
    private Button supp;
    
    @FXML
     private ImageView iconartic;
    
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
    
    
    
    public void SetArticleTitle(String Title){
        this.ArticleTitre.setText(Title);
    }
    
    public void SetArticleCategorie(String nom){
        this.Articlecat.setText(nom);
    }
    
    public void SetArticleDate(String date){
        this.Articledate.setText(date);
    }
    
    public void SetArticleVues(int vues){
        this.Articlevues.setText(Integer.toString(vues));
    }
    
    public void SetArticleID(int id){
        this.modif.setId(Integer.toString(id));
    }
    
    public void SetArticleIDSup(int id){
        this.supp.setId(Integer.toString(id));
    }

    public void setIconartic(WritableImage iconartic) {
        this.iconartic.setImage(iconartic);
    }
    
    
    
    public void handleClicks(ActionEvent actionEvent) throws MalformedURLException, IOException {
        if (actionEvent.getSource() == supp) {
            final Node source = (Node) actionEvent.getSource();
      System.out.println(source.getId());   
              
              GestionArticles gstart = new GestionArticles();
              Articles article = new Articles(null, null, null, null, null, Integer.parseInt(source.getId()), 0, 0, 0, null);
              gstart.SupprimerArticle(article);
              
              
              try {
                this.displayTray("Article Supprimé");
            } catch (AWTException ex) {
                Logger.getLogger(AjoutArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
              
              
              
              Stage stage = (Stage) ArticleTitre.getScene().getWindow();
                  
                   URL url = new File("src/gestion_blog/views/Home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion de Blog");
        Scene scene = new Scene(root);
        stage.setScene(scene);
              
               
        }
        
        if(actionEvent.getSource()==modif)
        {
            Stage stage = (Stage) modif.getScene().getWindow();
                  //System.out.println(modif.getId());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/ModifierArticle.fxml"));
                
             
         
          
        Parent root =loader.load();
        
        ModifierArticle Mod;
                Mod = loader.getController();
                Mod.setId(Integer.parseInt(modif.getId()));
                Mod.load();
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion de Blog");
        Scene scene = new Scene(root);
        stage.setScene(scene);
            
        }
    }
    
    
        public void displayTray(String msg) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

       
        Image image = Toolkit.getDefaultToolkit().createImage("/gestion_blog/images/how-to-edit-a-png-image-1.png");
       
        TrayIcon trayIcon = new TrayIcon(image, "Gestion de Blog");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        //trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Gestion de Blog", msg, TrayIcon.MessageType.INFO);
    }
}
