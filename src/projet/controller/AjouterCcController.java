/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class AjouterCcController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private VBox vbox;
    private Parent fxml;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(5);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("/projet/views/AjouterClubFormulaire.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(AjouterCcController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void open_ajouter_categorie() {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(460);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("/projet/views/AjouterCategorieFromulaire.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(AjouterCcController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    @FXML
    public void open_ajouter_club() {
        TranslateTransition t = new TranslateTransition(Duration.seconds(1), vbox);
        t.setToX(5);
        t.play();
        t.setOnFinished((e) -> {
            try {
                fxml = FXMLLoader.load(getClass().getResource("/projet/views/AjouterClubFormulaire.fxml"));
                vbox.getChildren().removeAll();
                vbox.getChildren().setAll(fxml);
            } catch (IOException ex) {
                Logger.getLogger(AjouterCcController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }
}
