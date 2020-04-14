/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2020;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import org.apache.commons.io.FileUtils;
import Services.EtablissementService;
import javafx.scene.control.Alert;
import Entities.Etablissement;
import java.sql.SQLException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AjouterEtablissementController implements Initializable {

    @FXML
    private AnchorPane anch;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField Adresse;
    @FXML
    private JFXTextField Numtel;
    @FXML
    private JFXButton filechooser;
    @FXML
    private JFXButton annuler;
    @FXML
    private JFXButton add;
    @FXML
    private ImageView img;
         private  FileChooser Fc= new FileChooser();
    private File file;
     File selectedFile ;
      private String newPhoto;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void selectPhoto(ActionEvent event) throws Exception {
           File dest=new File("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images");
        Fc.setTitle("Open Resource File");
        Fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("images", "*.bmp", "*.png", "*.jpg", "*.gif"));
        Fc.setInitialDirectory(new File("C:\\wamp64\\www\\ProjetIntegration2020"));
        selectedFile = Fc.showOpenDialog(null);
           try {
               FileUtils.copyFileToDirectory(selectedFile, dest);
           } catch (IOException ex) {
               Logger.getLogger(AjouterEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
           }
         
          
           try {
               Image imge = new Image(selectedFile.toURI().toURL().toString());
               System.out.println(selectedFile.toURI().toURL().toString());
                this.img.setImage(imge);
           } catch (MalformedURLException ex) {
               Logger.getLogger(AjouterEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
           }
       //     photoName.setText(file.getName());
    }

    @FXML
    private void EventAdd(ActionEvent event) throws SQLException {
        EtablissementService es=new EtablissementService();
        if(nom.getText().isEmpty()||Adresse.getText().isEmpty()||Numtel.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setHeaderText("Veuillez remplir tous les champs");
            alert.setContentText("Veuillez remplir tous les champs");
            alert.showAndWait();
        }else{
           int v=Integer.parseInt(Numtel.getText());
            Etablissement e=new Etablissement(v, Adresse.getText(), nom.getText(),this.newPhoto);
            e.setImage(selectedFile.getName());
            es.ajouter(e);
            
             Alert alert = new Alert(Alert.AlertType.INFORMATION);
             alert.setHeaderText("School Added");
            alert.setContentText("School successfully added");
            alert.showAndWait();
             Parent homePage;
            try {
                AnchorPane pp = FXMLLoader.load(getClass().getResource("AficcherEtablissement.fxml"));
                this.anch.getChildren().setAll(pp);

            } catch (IOException ex) {
                Logger.getLogger(AjouterEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void goToAffichage(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        AnchorPane pp = FXMLLoader.load(getClass().getResource("AficcherEtablissement.fxml"));
        this.anch.getChildren().setAll(pp);
    }
    
}
