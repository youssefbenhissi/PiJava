package gestion_blog.controller;

import gestion_blog.models.Articles;
import gestion_blog.service.GestionArticles;
import gestion_blog.service.GestionCategories;
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

public class Controller implements Initializable {

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
    private Button btntags;
    
    @FXML
    private Button recherchebtn;
    
    @FXML
    private Pane AjoutPane;

    
    @FXML
    private Pane Modifier;
    @FXML
    private Pane pnlTags;
    
    
     @FXML
     private TextField recherche;
    
     @FXML
    private Pane gestioncatpan;
     
     
      @FXML
     private Label artipubint;
      
      @FXML
     private Label artibrou;
      
      @FXML
     private Label vuetotal;
      
 
    
    
  String terme;
  boolean modifint = false;
  boolean erreurrech = true;


    List<Articles> listarticles = new ArrayList<Articles>();
    List<Articles> listarticless = new ArrayList<Articles>();
    GestionArticles gstart = new GestionArticles();
    GestionCategories gstcat = new GestionCategories();
    Node[] nodes;

    public void setModifint(boolean modifint) {
        this.modifint = modifint;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
        try {
           
                Update();
           
                
               int vuespub = 0;
            for (int i = 0; i < listarticles.size() ; i++) {
              
                    vuespub = vuespub + listarticles.get(i).getVues();
               
            }
            vuetotal.setText(Integer.toString(vuespub));
            int artinbrpub = 0;
            int artinbrbrou = 0;
            for (int i = 0; i < listarticles.size() ; i++) {
              if(listarticles.get(i).getType() == 1){
                  artinbrpub = artinbrpub+1;
              }else if(listarticles.get(i).getType() == 0){
                  artinbrbrou = artinbrbrou+1;
              }
   
            }
             artipubint.setText(Integer.toString(artinbrpub));
             artibrou.setText(Integer.toString(artinbrbrou));
            
            
            
     
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
       
       
       
       
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
            accueilpane.setStyle("-fx-background-color : #f2f2f2");
            accueilpane.toFront();
        }
        if(actionEvent.getSource()==Ajout)
        {
            ScrollPane sp = new ScrollPane();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/gestion_blog/views/AjoutArticle.fxml"));
            AjoutPane.getChildren().add(newLoadedPane);
           AjoutPane.setStyle("-fx-background-color : #f2f2f2");
           sp.setContent(AjoutPane);
            AjoutPane.toFront();
            
        }
        if(actionEvent.getSource()==recherchebtn)
        {
         
            if(this.terme != null && !erreurrech){
               Stage stage = (Stage) recherchebtn.getScene().getWindow();
                  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/RechercheArticle.fxml"));
        Parent root = loader.load();
        RechercheArticle rech;
                rech = loader.getController();
                rech.handleRecherche(this.terme);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion de Blog");
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
           listarticles = gstart.getArticles();
          
        nodess = new Node[listarticles.size()];
    
         
            
        
        for (int i = 0; i < nodess.length  ; i++) {
            //System.out.println(i);
            try {

                final int j = i;
                /*URL url = new File("src/gestion_blog/views/Item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);*/
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/Item.fxml"));
                nodess[i] = loader.load();
             
            //Get controller of scene2
            AffichageArticleList Affich;
                Affich = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            
           Affich.SetArticleTitle(listarticles.get(i).getTitre());
            String cat_nom = gstcat.getcatbyid(listarticles.get(i).getCat_id());
            Affich.SetArticleCategorie(cat_nom);
            Affich.SetArticleVues(listarticles.get(i).getVues());
            Affich.SetArticleDate(listarticles.get(i).getDate());
            Affich.SetArticleID(listarticles.get(i).getId());
            Affich.SetArticleIDSup(listarticles.get(i).getId());
            
            
            
                
             
                
                if(listarticles.get(i).getType() == 1){
                File file = new File("src\\gestion_blog\\images\\publier.png");
                 BufferedImage bufferedImage = ImageIO.read(file);
                 WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                 Affich.setIconartic(image);
                }
                 if(listarticles.get(i).getType() == 0){
              File file = new File("src\\gestion_blog\\images\\Brou.png");
                 BufferedImage bufferedImage = ImageIO.read(file);
                 WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                   Affich.setIconartic(image);
                 }
           
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
    
    
    
    
    
    
    
    
}
