package projet.controller;

import projet.models.Articles;
import projet.models.Categories;
import projet.models.Tags;
import projet.service.GestionArticles;
import projet.service.GestionCategories;
import projet.service.GestionTags;
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
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.imageio.ImageIO;

public class Gestiontags implements Initializable {

    @FXML
    private VBox pnItems = null;
    
    
    
    @FXML
    private Button Ajout;
    
    @FXML
    private Button btnaccueil;
    
    @FXML
    private Button btntags;

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
    private Pane pnlTags;
    
    
     @FXML
     private TextField recherche;
    
     @FXML
    private Pane gestioncatpan;
     
     
      @FXML
     private Label nbrtaglabel;
      
     
 
    
    
  String terme;
  boolean modifint = false;
  boolean erreurrech = true;


    List<Tags> listtag = new ArrayList<Tags>();
    GestionTags gsttagglobal = new GestionTags();
    GestionTags gsttag = new GestionTags();
    Node[] nodes;

    
    
    
    
    
    
    
    
    
    
    
    
    
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
        
        try {
           
                Update();
           
                nbrtaglabel.setText(Integer.toString(listtag.size()));
               
            
            
            
            
     
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
                  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/GestionCatego.fxml"));
        Parent root = loader.load();
        stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
        stage.setTitle("Gestion des Catégories");
        Scene scene = new Scene(root);
        stage.setScene(scene);  
        }
        
     
        
        if (actionEvent.getSource() == btnaccueil) {
          Stage stage = (Stage) recherchebtn.getScene().getWindow();
                  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/Home.fxml"));
        Parent root = loader.load();
        stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
        stage.setTitle("Gestion des Articles");
        Scene scene = new Scene(root);
        stage.setScene(scene); 
        }
        if(actionEvent.getSource()==Ajout)
        {
            ScrollPane sp = new ScrollPane();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/projet/views/AjouterTag.fxml"));
            AjoutPane.getChildren().add(newLoadedPane);
           AjoutPane.setStyle("-fx-background-color : #f2f2f2");
           sp.setContent(AjoutPane);
            AjoutPane.toFront();
            
        }
        if(actionEvent.getSource()==recherchebtn)
        {
         
            if(this.terme != null && !erreurrech){
               Stage stage = (Stage) recherchebtn.getScene().getWindow();
                  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/RechercheTags.fxml"));
        Parent root = loader.load();
        Recherchetags rech;
                rech = loader.getController();
                rech.handleRecherche(this.terme);
        stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
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
           listtag = gsttag.getTags();
          
        nodess = new Node[listtag.size()];
    
         
            
        
        for (int i = 0; i < nodess.length  ; i++) {
            //System.out.println(i);
            try {

                final int j = i;
                /*URL url = new File("src/projet/views/Item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);*/
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ItemTags.fxml"));
                nodess[i] = loader.load();
             
            //Get controller of scene2
            AffichagetagsList Affich;
                Affich = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            
           
            
          Affich.SettagTitle(listtag.get(i).getNom());
          
          Affich.SetTagIDSup(listtag.get(i).getId());
          Affich.SetTagID(listtag.get(i).getId());
                
               
           
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

       
        java.awt.Image image = Toolkit.getDefaultToolkit().createImage("/projet/images/how-to-edit-a-png-image-1.png");
       
        TrayIcon trayIcon = new TrayIcon(image, "Recherche");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        //trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Recherche", msg, TrayIcon.MessageType.ERROR);
    }     
    
    
         public void retourGlobal() throws MalformedURLException, IOException{
         Stage stage = (Stage) this.accueilpane.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubback.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
 
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
           
          @FXML
    private void AfficherClubs(ActionEvent event) throws MalformedURLException, IOException {
       
         Stage stage = (Stage) this.pnItems.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubback.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void Afficherpersonnel(ActionEvent event) throws MalformedURLException, IOException {

        
        
         Stage stage = (Stage) this.pnItems.getScene().getWindow();
        URL url = new File("src/projet/views/affichageBackPersonnel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEvenements(ActionEvent event) throws MalformedURLException, IOException {

        
        
         Stage stage = (Stage) this.pnItems.getScene().getWindow();
        URL url = new File("src/projet/views/EvenementBack.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
      
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
       
    }
     @FXML
    private void login(ActionEvent event) {

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/LoginGUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    
    @FXML
    private void AfficherC(ActionEvent event) throws MalformedURLException, IOException {
      
     Stage stage1 = (Stage) this.pnItems.getScene().getWindow();
        URL url1 = new File("src/views/homee.fxml").toURI().toURL();
        Parent root1;

        root1 = FXMLLoader.load(url1);

        Scene scene1 = new Scene(root1);
        stage1.setScene(scene1);   
    
    }
    

    @FXML
    private void AfficherGestionBlog(ActionEvent event) throws MalformedURLException, IOException {

         Stage stage = (Stage) this.pnItems.getScene().getWindow();
        URL url = new File("src/projet/views/Home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
     
        stage.setTitle("Gestion de Blog");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }  
    
    
    
}
