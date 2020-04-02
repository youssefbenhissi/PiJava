/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpi;

import java.io.File;
import java.net.URL;
import projet.service.CategorieClubService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author youssef
 */
public class ProjetPi extends Application {

    private double x, y;

    @Override

    public void start(Stage primaryStage) {

        CategorieClubService c = new CategorieClubService();
        c.selectAll();
        try {
            URL url = new File("src/projet/views/FXML.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
