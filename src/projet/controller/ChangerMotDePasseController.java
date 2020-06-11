/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;
import projet.models.Utilisateur;
import projet.service.ParentService;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class ChangerMotDePasseController implements Initializable {

    
    
    @FXML
    private JFXTextField oldPass;

    @FXML
    private JFXTextField newPass;

    @FXML
    private JFXButton btnValider;
    
    
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    
    Utilisateur user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ParentService service = new ParentService();
        user = service.selectUser(8);
    }    
    
    
      @FXML
    private void ModifierPass(ActionEvent event) {

        /*if(fieldNom.validate()&& fieldNumero.validate()&&fieldPrenom.validate())
        {*/
      
          
            String mdp = BCrypt.hashpw(newPass.getText(), BCrypt.gensalt(13));
               mdp =   mdp.replaceFirst("2a", "2y");
            user.setMotDePasse_Utilisateur(mdp);
            
            ParentService serv = new ParentService();
            serv.modifierP(user);

       

        alert.setTitle("Changement mot de passe");
        alert.setHeaderText("Mot de passe modifié");
        alert.setContentText("Vous allez etre rederigé vers le menu principal !!");
        alert.showAndWait();
//        UserSession.getInstace(user);
      
        Stage stage = (Stage) newPass.getScene().getWindow();
        // do what you have to do
        stage.close();
        // }
    }
    
}
