/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpi;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import projet.models.CategorieEvenement;
import projet.models.Evenement;
import projet.service.CategorieEvenementService;
import projet.service.EvenementService;

/**
 *
 * @author Iheb
 */
public class ProjetPi extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL url = new File("src/projet/views/EvenementBack.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
