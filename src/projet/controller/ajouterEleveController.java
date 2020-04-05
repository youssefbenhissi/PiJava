/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import projet.models.Eleve;
import projet.service.EleveService;

/**
 *
 * @author user
 */
public class ajouterEleveController implements Initializable {

    @FXML
    private JFXTextField nomE;

    @FXML
    private Label errorsNom;

    @FXML
    private JFXTextField prenomE;

    @FXML
    private Label errorPrenom;

    @FXML
    private JFXTextField ageE;

    private FileChooser filechooser;
    private File file;
    @FXML
    private Label errorAge;
    @FXML
    private ComboBox<String> sexe;
    @FXML
    private JFXTextField emailE;

    @FXML
    private Label errorEmail;

    @FXML
    private JFXComboBox<String> parent;
    EleveService service = new EleveService();

    @FXML
    private ImageView imageView;

    @FXML
    private Label errorsImage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sexe.getItems().add("male");
        sexe.getItems().add("femelle");
        sexe.getItems().add("other");
        HashMap<String, Integer> mapCategorie = service.getAllParents();
        for (String s : mapCategorie.keySet()) {
            //System.out.println(s);
            parent.getItems().add(s);
        }
    }

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
    private void ajouterEleve(MouseEvent event) {

        if (nomE.getText().equals("")) {
            errorsNom.setText("Libelle field is required");
        }
        if (prenomE.getText().equals("")) {
            errorPrenom.setText("Prenom field is required");
        }
        if (ageE.getText().equals("") && Integer.parseInt(ageE.getText()) < 6) {
            errorPrenom.setText("Prenom field is required");
        } else if (file == null) {
            errorsImage.setText("Image field is required");
        } else {
            String afficheEleve;
            if (file == null) {
                afficheEleve = "";
            } else {
                afficheEleve = replaceFile(file.getAbsolutePath());
            }
            HashMap<String, Integer> mapCategorie = service.getAllParents();
            int id_Categorie = mapCategorie.get(parent.getValue());
            Eleve e = new Eleve();
            e.setNom(nomE.getText());
            e.setPrenom(prenomE.getText());
            e.setSexe(sexe.getValue());
            e.setSexe(sexe.getValue());
            e.setImage(afficheEleve);
            e.setAge(Integer.parseInt(ageE.getText()));
            e.setEmail(emailE.getText());
            e.setParent_id(id_Categorie);
            if (service.ajouterEleve(e)) {

                
                

            }

        }
    }
}
