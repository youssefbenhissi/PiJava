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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author youssef
 */
public class backhomecontroller implements Initializable {

    @FXML
    private Button btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @FXML
    private void AfficherClubs(ActionEvent event) throws IOException, MalformedURLException {
        
         Stage stage = (Stage) this.btn.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubback.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        // stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
    }

    @FXML
    private void Afficherpersonnel(ActionEvent event) throws MalformedURLException, IOException {

       
         Stage stage = (Stage) this.btn.getScene().getWindow();
        URL url = new File("src/projet/views/affichageBackPersonnel.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        // stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEvenements(ActionEvent event) throws MalformedURLException, IOException {

       
        
         Stage stage = (Stage) this.btn.getScene().getWindow();
        URL url = new File("src/projet/views/EvenementBack.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        // stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
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
       
        
        Stage stage = (Stage) this.btn.getScene().getWindow();
        URL url = new File("src/views/homee.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        // stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherGestionBlog(ActionEvent event) throws MalformedURLException, IOException {

        Stage stage = (Stage) this.btn.getScene().getWindow();
        URL url = new File("src/projet/views/Home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        // stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
        stage.setTitle("Gestion de Blog");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
