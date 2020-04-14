/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author youssef
 */
public class frontHome implements Initializable{
    @FXML
    private Label etab;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
     @FXML
    private void AfficherC(MouseEvent event) throws IOException {
       
        
        Stage stage = (Stage) this.etab.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherEvenements(MouseEvent event) throws IOException {
        
       
        
         Stage stage = (Stage) this.etab.getScene().getWindow();
        URL url = new File("src/projet/views/EvenemnetFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
        @FXML
    private void login(MouseEvent event) throws IOException {
        
        
        
         Stage stage = (Stage) this.etab.getScene().getWindow();
        URL url = new File("src/projet/views/LoginGUI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherB(MouseEvent event) throws MalformedURLException, IOException {
        
         Stage stage = (Stage) this.etab.getScene().getWindow();
        URL url = new File("src/views/CategorieFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherEtablissement(MouseEvent event) throws MalformedURLException, IOException {
       
        
         Stage stage = (Stage) this.etab.getScene().getWindow();
        URL url = new File("src/projet2020/AficcherEtablissement.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
      @FXML
      public void AfficherBlog(MouseEvent event) throws MalformedURLException, IOException{
         Stage stage = (Stage) this.etab.getScene().getWindow();
        URL url = new File("src/projet/views/affichageArticlesFrontList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
}
