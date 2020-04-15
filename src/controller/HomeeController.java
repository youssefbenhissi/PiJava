/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.controller.backcontroller;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class HomeeController implements Initializable {

    @FXML
    private BorderPane border_pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   @FXML
    private void AfficherC(ActionEvent event) {
         BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/affiche.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(HomeeController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border_pane.setCenter(root);
    }
/*
    @FXML
    private void AfficherC(MouseEvent event) {
        Load("affiche");
    }
   

    
     public void Load(String ui){
         Parent root = null;
         try {
        root= FXMLLoader.load(getClass().getResource(ui+".fxml"));
                

          
        }
        
         catch(IOException ex) {
            Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
           border_pane.setCenter(root);
    }
     */
//
//    @FXML
//    private void AfficherC(MouseEvent event) {
//    }

   
    
    
    
    
     
     @FXML
    private void AfficherClubs(ActionEvent event) throws MalformedURLException, IOException {
       
         Stage stage = (Stage) this.border_pane.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubback.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void Afficherpersonnel(ActionEvent event) throws MalformedURLException, IOException {

        
        
         Stage stage = (Stage) this.border_pane.getScene().getWindow();
        URL url = new File("src/projet/views/affichageBackPersonnel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEvenements(ActionEvent event) throws MalformedURLException, IOException {

        
        
         Stage stage = (Stage) this.border_pane.getScene().getWindow();
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
    private void AfficherGestionBlog(ActionEvent event) throws MalformedURLException, IOException {

         Stage stage = (Stage) this.border_pane.getScene().getWindow();
        URL url = new File("src/projet/views/Home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
     
        stage.setTitle("Gestion de Blog");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
    
    
}
