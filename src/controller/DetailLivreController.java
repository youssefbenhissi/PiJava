/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.ServiceCategorier;
import Service.ServiceReservation;
import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeType;
import com.sbix.jnotify.NoticeWindow;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import models.Reservation;
import models.categorier;
import models.livre;
import controller.AjouterCatigorieController;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class DetailLivreController implements Initializable {

    @FXML
    private ImageView imageBack;
    
    
    @FXML
    private Label nom;
    @FXML
    private Label description;
    @FXML
    private Label nbr;
    livre liv;
    Stage stage;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        liv=CategorieFrontController.liv1;
        Image  image;
        
        try {
            image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\"+ liv.getNom_image()));
            imageBack.setImage(image);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DetailLivreController.class.getName()).log(Level.SEVERE, null, ex);
        }
        String test = String.valueOf(liv.getId());
        
        nom.setText(liv.getNom());
        description.setText(liv.getDescription());
      // nbr.setText(liv.getNombredepage());
        
    }  

    @FXML
    private void reservation(ActionEvent event) throws SQLException {
        Reservation r= new Reservation( liv.getNom(), liv.getId());
        ServiceReservation sr=new ServiceReservation();
        sr.addCtegorier(r);
        
        
    }

//    @FXML
//    private void exit(ActionEvent event) {
//         stage= (Stage)((Button)event.getSource()).getScene().getWindow();
//        stage.close();
//    }

   
    
   
    
    
}
