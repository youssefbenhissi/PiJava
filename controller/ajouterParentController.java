/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import projet.models.Parent;



/**
 *
 * @author user
 */
public class ajouterParentController implements Initializable {
    @FXML
    private JFXTextField nomP;

    @FXML
    private Label errorsNom;

    @FXML
    private JFXTextField prenomP;

    @FXML
    private Label errorPrenom;
    private FileChooser filechooser;
    private File file;
   
    @FXML
    private ImageView imageView;

    @FXML
    private Label errorsImage;
     @FXML
    private JFXTextField numTelephoneP;

    private Label errorNumTelephone;
    @FXML
    private Label errornumTelephone;
    
     @FXML
    private void uploadImage(MouseEvent event) {
        filechooser = new FileChooser();
        filechooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("image files", "*.png", "*.jpg", "*.jpeg"));

        file = filechooser.showOpenDialog(null);
        if (file != null) {
            Image image = new Image(file.toURI().toString());
            imageView.setImage(image);
            errorsImage.setText("");

        } else {
            errorsImage.setText("Choose Type :  PNG JPEG JPG");
        }
    }
     private String generateFileName() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 25) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
      private String replaceFile(String file) {

        String extension = file.substring(file.lastIndexOf("."), file.length());
        String filename = generateFileName() + extension;

        Path sourceDirectory = Paths.get(file);
        Path targetDirectory = Paths.get("C:\\xampp\\htdocs\\pidev4\\web\\assets\\images\\" + filename);
        try {
            //copy source to target using Files Class
            Files.copy(sourceDirectory, targetDirectory);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return filename;
    }
      @FXML
    private void ajouterParent(MouseEvent event) {

        if (nomP.getText().equals("")) {
            errorsNom.setText("Nom field is required");
        }
        if (prenomP.getText().equals("")) {
            errorPrenom.setText("Prenom field is required");
        }
        if (numTelephoneP.getText().equals("") && Integer.parseInt(numTelephoneP.getText()) > 8) {
            errorNumTelephone.setText("numTelephone field is required");
          if (file == null) {
            errorsImage.setText("Image field is required");
        } else {
            String afficherParent;
            if (file == null) {
                afficherParent = "";
            } else {
                afficherParent = replaceFile(file.getAbsolutePath());
            }
            Parent p=new Parent();
            p.setNom(nomP.getText());
            p.setPrenom(prenomP.getText());
            p.getNumTelephone(Integer.parseInt(numTelephoneP.getText()));
            p.setImage(afficherParent);
            

    
    
    
          }}}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
        