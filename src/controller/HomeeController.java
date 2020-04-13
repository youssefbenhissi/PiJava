/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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

   
    
}
