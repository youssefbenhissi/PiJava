package gestion_blog.controller;

import gestion_blog.models.Articles;
import gestion_blog.service.GestionArticles;
import gestion_blog.service.GestionCategories;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Controller implements Initializable {

    @FXML
    private VBox pnItems = null;
    
    
    
    @FXML
    private Button Ajout;
    
    @FXML
    private Button btnOverview;

    @FXML
    private Button btnOrders;

    @FXML
    private Button btnCustomers;

    @FXML
    private Button btnMenus;

    @FXML
    private Button btnPackages;

    @FXML
    private Button btnSettings;

    @FXML
    private Button btnSignout;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlOverview;

    @FXML
    private Pane pnlMenus;
    
    int size1 =0;
    int size2 =0;

    List<Articles> listarticle = new ArrayList<Articles>();
    List<Articles> listarticles = new ArrayList<Articles>();
    GestionArticles gstart = new GestionArticles();
    GestionCategories gstcat = new GestionCategories();
    Node[] nodes;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listarticle = gstart.getArticles();
        size1 = listarticle.size();
        nodes = new Node[listarticle.size()];
        for (int i = 0; i < nodes.length; i++) {
            //System.out.println(i);
            try {

                final int j = i;
                /*URL url = new File("src/gestion_blog/views/Item.fxml").toURI().toURL();
                nodes[i] = FXMLLoader.load(url);*/
                
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/Item.fxml"));
                nodes[i] = loader.load();
             
            //Get controller of scene2
            AffichageArticleList Affich;
                Affich = loader.getController();
            //Pass whatever data you want. You can have multiple method calls here
            
            Affich.SetArticleTitle(listarticle.get(i).getTitre());
            String cat_nom = gstcat.getcatbyid(listarticle.get(i).getCat_id());
            Affich.SetArticleCategorie(cat_nom);
            Affich.SetArticleVues(listarticle.get(i).getVues());
            Affich.SetArticleDate(listarticle.get(i).getDate());
            Affich.SetArticleID(listarticle.get(i).getId());
                //give the items some effect

                 nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #666666;-fx-background-radius: 1em;");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #b5b5b5;-fx-background-radius: 1em;");
                });
                
                
                pnItems.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        
        
        
            /*   Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        // pnItems.getChildren().clear();
                        // Update();
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        }); */
        
        
        //thread.setDaemon(true);
       // thread.start();
        
        
        

    }


    public void handleClicks(ActionEvent actionEvent) throws IOException {
        if (actionEvent.getSource() == btnCustomers) {
            pnlCustomer.setStyle("-fx-background-color : #1620A1");
            pnlCustomer.toFront();
        }
        
        if (actionEvent.getSource() == btnOverview) {
            pnlOverview.setStyle("-fx-background-color : #f2f2f2");
            pnlOverview.toFront();
        }
        if(actionEvent.getSource()==Ajout)
        {
            ScrollPane sp = new ScrollPane();
            Pane newLoadedPane =  FXMLLoader.load(getClass().getResource("/gestion_blog/views/AjoutArticle.fxml"));
            pnlOrders.getChildren().add(newLoadedPane);
           pnlOrders.setStyle("-fx-background-color : #f2f2f2");
           sp.setContent(pnlOrders);
            pnlOrders.toFront();
            
        }
        /*if (actionEvent.getSource() == Ajout) {
            
            
            
            
                     Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("/gestion_blog/views/AjoutArticle.fxml"));
            Stage stage;
            stage = new Stage();
            stage.getIcons().add(new Image("/gestion_blog/images/unnamed.png"));
            stage.setTitle("Ajouter un article");
            stage.setScene(new Scene(root));
            stage.show();
            // Hide this current window (if this is what you want)
            
        }
        catch (IOException e) {
            e.printStackTrace();
        }
            
            
            
            
            
        }*/
        
        
    }
    
    
    
    
    
    
    
    
    
    
    public void Update() throws IOException{
      // pnItems.getChildren().clear();
        Node[] nodess;
           listarticles = gstart.getArticles();
          size2 = listarticles.size();
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
    
    
    
    
    
    
    
    
}
