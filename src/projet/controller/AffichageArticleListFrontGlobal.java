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
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import projet.models.Tags;
import projet.service.GestionTags;


/**
 *
 * @author geek alaa
 */
public class AffichageArticleListFrontGlobal implements Initializable{
    
 
    
     @FXML
    private VBox PNLarticle = null;
     
      @FXML
    private VBox PNLCatList = null;
      
      @FXML
    private VBox PNLTagList = null;
    
       @FXML
    private ScrollPane scrollPane;
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        GestionArticles gst = new GestionArticles();
         List<Articles> listarticles = new ArrayList<Articles>();
         listarticles = gst.getArticles();
         Node[] nodess;
         System.out.println(listarticles.size());
         
          nodess = new Node[listarticles.size()];
          
       for (int i = 0; i < listarticles.size()  ; i++) {
            try {
                if(listarticles.get(i).getType() != 0){
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/LigneArticleFront.fxml"));
                
                nodess[i] = loader.load();
                
                
                //Get controller of scene2
                AffichageArticleListFront Affich;
                Affich = loader.getController();
                Affich.SetArticleID(listarticles.get(i).getId());
               Affich.setArticletitre1(listarticles.get(i).getTitre());
               Affich.SetArticleID(listarticles.get(i).getId());
                Affich.setArticledesc1(listarticles.get(i).getDescription());
                Affich.setArticlevue1(Integer.toString(listarticles.get(i).getVues())+" Vues");
                
                GestionCategories gstcat = new GestionCategories();
                List<Categories> listCat = gstcat.getcatlist();
                Categories categor = new Categories(listarticles.get(i).getCat_id());
                int index = listCat.indexOf(categor);
                Affich.setArticlecat1(listCat.get(index).getNom());
               
                 String path = "http://127.0.0.1:8000/assets/images/" + listarticles.get(i).getImage();
               
                  Image img = new Image(path);
                 
                
                Affich.setArticleimage1(img);
                
          
                
                
                
                
                PNLarticle.getChildren().add(nodess[i]);
            }
            } catch (IOException ex) {
                Logger.getLogger(AffichageArticleListFrontGlobal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
       GestionCategories gstcat1 = new GestionCategories();
       
       List<Categories> listcategorie = new ArrayList<Categories>();
       listcategorie = gstcat1.getcatlist();
       Node[] nodes;
         System.out.println(listcategorie.size());
         
          nodes = new Node[listcategorie.size()];
          
       for (int i = 0; i < listcategorie.size()  ; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ItemcatListFront.fxml"));
                
                nodes[i] = loader.load();
                
                
                //Get controller of scene2
                AffichageCatListFront Affich;
                Affich = loader.getController();
                
               Affich.SetCatTitle(listcategorie.get(i).getNom());
                Affich.SetCatID(listcategorie.get(i).getId());
                
                
                int j =i;
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #d4d2d2;-fx-background-radius: 1em;");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #ebebeb;-fx-background-radius: 1em;");
                });
                
                
                
                PNLCatList.getChildren().add(nodes[i]);
            } catch (IOException ex) {
                Logger.getLogger(AffichageArticleListFrontGlobal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
       
      GestionTags gstTag = new GestionTags();
       
       List<Tags> listTags = new ArrayList<Tags>();
       listTags = gstTag.getTags();
       Node[] nodesss;
         //System.out.println(listcategorie.size());
         
          nodesss = new Node[listTags.size()];
          
       for (int i = 0; i < listTags.size()  ; i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ItemtagListFront.fxml"));
                
                nodesss[i] = loader.load();
                
                
                //Get controller of scene2
                AffichagetaglistFrontDroit Affich;
                Affich = loader.getController();
                
                
               Affich.SetTagTitle(listTags.get(i).getNom());
                Affich.SettagID(listTags.get(i).getId());
                
                
                int j =i;
                nodesss[i].setOnMouseEntered(event -> {
                    nodesss[j].setStyle("-fx-background-color : #d4d2d2;-fx-background-radius: 1em;");
                });
                nodesss[i].setOnMouseExited(event -> {
                    nodesss[j].setStyle("-fx-background-color : #ebebeb;-fx-background-radius: 1em;");
                });
                
                
                
                PNLTagList.getChildren().add(nodesss[i]);
            } catch (IOException ex) {
                Logger.getLogger(AffichageArticleListFrontGlobal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }  
     
       
       
        
    }
    
    
         @FXML
    private void AfficherC(ActionEvent event) throws IOException {
       
        
        Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherEvenements(ActionEvent event) throws IOException {
        
       
        
         Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet/views/EvenemnetFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
        @FXML
    private void login(ActionEvent event) throws IOException {
        
        
        
         Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet/views/LoginGUI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherB(ActionEvent event) throws MalformedURLException, IOException {
        
         Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/views/CategorieFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherEtablissement(ActionEvent event) throws MalformedURLException, IOException {
       
        
         Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet2020/AficcherEtablissement.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
      @FXML
      public void AfficherBlog(ActionEvent event) throws MalformedURLException, IOException{
         Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet/views/affichageArticlesFrontList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
   
    
    

    
    
   
}