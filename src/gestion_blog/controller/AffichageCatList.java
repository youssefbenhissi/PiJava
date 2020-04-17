/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.controller;




import gestion_blog.models.Articles;
import gestion_blog.models.Categories;
import gestion_blog.service.GestionArticles;
import gestion_blog.service.GestionCategories;
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
public class AffichageCatList implements Initializable{
    
    @FXML
     private Label CatTitre;
    
    @FXML
     private Label CatDesc;
    
    
    @FXML
    private Button modif;
    
    @FXML
    private Button supp;
    
    
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
    
    
    
    public void SetCatTitle(String Title){
        this.CatTitre.setText(Title);
    }
    
    public void SetCatDescri(String descri){
        this.CatDesc.setText(descri);
    }
    
  
    
    public void SetCatID(int id){
        this.modif.setId(Integer.toString(id));
    }
    
    public void SetCatIDSup(int id){
        this.supp.setId(Integer.toString(id));
    }


    
    
    
    public void handleClicks(ActionEvent actionEvent) throws MalformedURLException, IOException {
        if (actionEvent.getSource() == supp) {
            final Node source = (Node) actionEvent.getSource();
      System.out.println(source.getId());   
              GestionCategories gstcatgl = new GestionCategories();
              
              Categories Categorie = new Categories(Integer.parseInt(source.getId()));
              gstcatgl.SupprimerCat(Categorie);
              
              
              try {
                this.displayTray("Catégorie Supprimée");
            } catch (AWTException ex) {
                Logger.getLogger(AjoutArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
              
              
              
              Stage stage = (Stage) supp.getScene().getWindow();
                  
                   URL url = new File("src/gestion_blog/views/GestionCatego.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
     stage.setTitle("Gestion des Catégories");
        Scene scene = new Scene(root);
        stage.setScene(scene);
              
               
        }
        
        if(actionEvent.getSource()==modif)
        {
            Stage stage = (Stage) modif.getScene().getWindow();
                  //System.out.println(modif.getId());
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/ModifierCategorie.fxml"));
                
             
         
          
        Parent root =loader.load();
        
        ModifierCategorie Mod;
                Mod = loader.getController();
                Mod.setId(Integer.parseInt(modif.getId()));
                Mod.load();
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion des Categories");
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
