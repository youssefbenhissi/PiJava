/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2020;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import Entities.Etablissement;
import Services.EtablissementService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class ModifierEtablissementController implements Initializable {

    @FXML
    private AnchorPane ass;
    @FXML
    private JFXButton annuler;
    @FXML
    private JFXTextField nom;
    @FXML
    private JFXTextField Adresse;
    @FXML
    private JFXTextField Numtel;
    
    private Etablissement e;
    
      private  FileChooser Fc= new FileChooser();
    private File file;
     File selectedFile ;
      private String newPhoto = null;

    private File selected_photo = null;
    private String oldPhoto;
    @FXML
    private ImageView img;
    @FXML
    private JFXButton backtolist;
    @FXML
    private JFXButton aj;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
        public void setEtablissement(Etablissement e) {
        this.e = e;
    }
  public void show() {
        nom.setText(e.getNom());


//        prix.setText(String.valueOf(eve.getPrix()));
        Adresse.setText(e.getAdresse());
       String v=Integer.toString(e.getNumTel());
        Numtel.setText(v);
     //   nbplace.setText(String.valueOf(eve.getNbPlace()));

//        
    }    

    @FXML
    private void selectPhoto(ActionEvent event) {
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
    private void goToAffichage2(ActionEvent event) throws IOException {
   
                Stage stage = (Stage) Adresse.getScene().getWindow();
                  
                   URL url = new File("src/projet2020/AficcherEtablissement.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void validUpdate(ActionEvent event) throws SQLException, IOException {
        EtablissementService es=new EtablissementService();
        this.e = es.FindEtablissement(this.e.getId());
        System.out.println(this.e.getId());
        this.e.setNom(nom.getText());
        this.e.setAdresse(Adresse.getText());
        int v=Integer.parseInt(Numtel.getText());
        this.e.setNumTel(v);
        if (this.selected_photo != null) {
            this.e.setImage(this.newPhoto);
        }
        System.out.println(this.e.getImage());
        System.out.println(this.e.getId());
        es.update(this.e);
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Ecole modifié");
        alert.setHeaderText("L'ecole choisi a été modifié");
        alert.setContentText("Votre modification a été enregistrée");
        alert.showAndWait();
        alert.close();
        Stage stage = (Stage) Adresse.getScene().getWindow();
                  
                   URL url = new File("src/projet2020/AficcherEtablissement.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    
}
