/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpi;

import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
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
    public void start(Stage primaryStage) {
/*        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        EvenementService service =new EvenementService();
        List<Evenement> mylise=service.selectAllEvenement();
        for(Evenement e:mylise){
            System.out.println("nom de l'evenement: "+e.getNomEvenement());
        }
        Evenement e=new Evenement(59,"yousef", 100, "benhissi", "image", 0,53);
        service.modifierEvenement(e);
        service.supprimerEvenement(59);
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
