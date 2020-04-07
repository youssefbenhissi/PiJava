/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidevfin;

import java.io.File;

import java.net.URL;
import javafx.application.Application;
import static javafx.application.Application.launch;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 *
 * @author geek alaa
 */
public class PIDEVFIN extends Application {
    
    private double x, y;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        URL url = new File("src/gestion_blog/views/Home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.getIcons().add(new Image("/gestion_blog/images/article-512.png"));
        primaryStage.setTitle("Gestion de Blog");
        Scene scene = new Scene(root);
        
      

       // primaryStage.initStyle(StageStyle.UNDECORATED);

        //drag it here
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);

        });
        
        
        
        
        

        
        
        
        
        
        
        
        
        
        
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    
    
  
    
    
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
