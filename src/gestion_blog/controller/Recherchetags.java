package gestion_blog.controller;

import gestion_blog.models.Articles;
import gestion_blog.models.Categories;
import gestion_blog.models.Tags;
import gestion_blog.service.GestionArticles;
import gestion_blog.service.GestionCategories;
import gestion_blog.service.GestionTags;
import java.awt.AWTException;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

public class Recherchetags implements Initializable {

    @FXML
    private VBox pnItems = null;
    
    
    
    @FXML
    private Button Ajout;
    
    @FXML
    private Button btnaccueil;

     @FXML
    private Pane accueilpane;
     
     @FXML
    private Pane RechercheArticle;
   

    @FXML
    private Button gestioncat;
    
    @FXML
    private Button recherchebtn;
    
    @FXML
    private Pane AjoutPane;

    
    @FXML
    private Pane Modifier;
    
    
     @FXML
     private TextField recherche;
    
     @FXML
    private Pane gestioncatpan;
     
     
      @FXML
     private Label nbrcatlabel;
      
          
    @FXML
    private Button accueil;
      
    
     @FXML
    private Button btntags;
      
    /*@FXML
    private Button pnlMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

   

    

   
*/
    
    
  String terme;
  boolean modifint = false;
  boolean erreurrech = true;


    List<Tags> listTags = new ArrayList<Tags>();
    GestionTags gsttagglobal = new GestionTags();
    GestionTags gsttag = new GestionTags();
    Node[] nodes;

    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
        
        
        
       
       
       
       
    recherche.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
           
            if(newValue.trim().isEmpty()){
                recherche.setStyle("-fx-border-color :#ff4242;");
            }else if(newValue.length() < 3){
                
                recherche.setStyle("-fx-border-color :#ff4242;");
                }else{
                erreurrech = false;
                this.terme = newValue;
                recherche.setId(newValue);
                 System.out.println(recherche.getId());
                recherche.setStyle("-fx-background-radius: 0em ;");
            }
        });
       
       
       
       
       
       
       
       
       
       
       
        

    }


    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == gestioncat) {
              Stage stage = (Stage) recherchebtn.getScene().getWindow();
                  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/GestionCatego.fxml"));
        Parent root = loader.load();
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion des Catégories");
        Scene scene = new Scene(root);
        stage.setScene(scene);  
        }
            
            
      if (actionEvent.getSource() == btntags) {
              Stage stage = (Stage) btntags.getScene().getWindow();
                  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/Gestiontags.fxml"));
        Parent root = loader.load();
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion des Tags");
        Scene scene = new Scene(root);
        stage.setScene(scene);  
        }
        
        if (actionEvent.getSource() == btnaccueil) {
          Stage stage = (Stage) recherchebtn.getScene().getWindow();
                  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/Gestiontags.fxml"));
        Parent root = loader.load();
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion Tags");
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        }
        if(actionEvent.getSource()==Ajout)
        {
            ScrollPane sp = new ScrollPane();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/gestion_blog/views/AjouterTag.fxml"));
            AjoutPane.getChildren().add(newLoadedPane);
           AjoutPane.setStyle("-fx-background-color : #f2f2f2");
           sp.setContent(AjoutPane);
            AjoutPane.toFront();
            
        }
       if(actionEvent.getSource()==recherchebtn)
        {
         
            if(this.terme != null && !erreurrech){
               Stage stage = (Stage) recherchebtn.getScene().getWindow();
                  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/RechercheTags.fxml"));
        Parent root = loader.load();
        Recherchetags rech;
                rech = loader.getController();
                rech.handleRecherche(this.terme);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Recherche Tags");
        Scene scene = new Scene(root);
        stage.setScene(scene);  
            }else{
                try {
                this.displayTray("Minimum 3 caracteres !!!");
            } catch (AWTException ex) {
                Logger.getLogger(AjoutArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
            
            
            
                  
            
        }
        
      
        
        
    }
    


    
    
    
    
    
    
    
    public void Update() throws IOException{
      // pnItems.getChildren().clear();
        Node[] nodess;
           listTags = gsttag.getTags();
          
        nodess = new Node[listTags.size()];
    
         
            
        
       for (int i = 0; i < nodess.length  ; i++) {
            //System.out.println(i);
            try {

                final int j = i;
                /*URL url = new File("src/gestion_blog/views/Item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);*/
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/ItemTags.fxml"));
                nodess[i] = loader.load();
             
            //Get controller of scene2
            AffichagetagsList Affich;
                Affich = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            
           
            
          Affich.SettagTitle(listTags.get(i).getNom());
          
          Affich.SetTagIDSup(listTags.get(i).getId());
          Affich.SetTagID(listTags.get(i).getId());
                
               
           
                //give the items some effect

                 nodess[i].setOnMouseEntered(event -> {
                    nodess[j].setStyle("-fx-background-color : #666666;-fx-background-radius: 1em;");
                });
                nodess[i].setOnMouseExited(event -> {
                    nodess[j].setStyle("-fx-background-color : #b5b5b5;-fx-background-radius: 1em;");
                });
                
                
                pnItems.getChildren().add(nodess[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
    }
    
    
 


       public void displayTray(String msg) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

       
        java.awt.Image image = Toolkit.getDefaultToolkit().createImage("/gestion_blog/images/how-to-edit-a-png-image-1.png");
       
        TrayIcon trayIcon = new TrayIcon(image, "Recherche");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        //trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Recherche", msg, TrayIcon.MessageType.ERROR);
    }     
       
       
    
       public void handleRecherche(String terme) throws IOException{
      // pnItems.getChildren().clear();
        Node[] nodess;
           listTags = gsttagglobal.getTagsSearch(terme);
          nbrcatlabel.setText(Integer.toString(listTags.size()));
        nodess = new Node[listTags.size()];
    
         
            
        
        for (int i = 0; i < nodess.length  ; i++) {
            //System.out.println(i);
            try {

                final int j = i;
                /*URL url = new File("src/gestion_blog/views/Item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);*/
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/ItemTags.fxml"));
                nodess[i] = loader.load();
             
            //Get controller of scene2
            AffichagetagsList Affich;
                Affich = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            
           
            
             
          Affich.SettagTitle(listTags.get(i).getNom());
          
          Affich.SetTagIDSup(listTags.get(i).getId());
          Affich.SetTagID(listTags.get(i).getId());
                
                //give the items some effect

                 nodess[i].setOnMouseEntered(event -> {
                    nodess[j].setStyle("-fx-background-color : #666666;-fx-background-radius: 1em;");
                });
                nodess[i].setOnMouseExited(event -> {
                    nodess[j].setStyle("-fx-background-color : #b5b5b5;-fx-background-radius: 1em;");
                });
                
                
                pnItems.getChildren().add(nodess[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
       
    } 
    
      public void retouraccu() throws MalformedURLException, IOException{
         Stage stage = (Stage) accueil.getScene().getWindow();
        URL url = new File("src/gestion_blog/views/Gestiontags.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion Tags");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    } 
    
    
    
    
}
