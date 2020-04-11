/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import javafx.event.ActionEvent;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import projet.models.CategorieEvenement;
import projet.models.Evenement;
import projet.service.CategorieEvenementService;
import projet.service.EvenementService;
import tray.animations.AnimationType;

/**
 * FXML Controller class
 *
 * @author yassine
 */
public class AjouterEvenementController implements Initializable {

    @FXML
    private JFXTextField nomE;

    @FXML
    private Label errorsNom;
    @FXML
    private JFXDatePicker datepickerP;
    @FXML
    private JFXTextField capacite;

    @FXML
    private Label errorCapacite;

    @FXML
    private JFXTextField description;

    @FXML
    private Label errorDescription;

    @FXML
    private JFXTextField prixEvenement;

    @FXML
    private Label errorPrix;

    @FXML
    private ImageView imageView;
    @FXML
    private JFXComboBox<String> caetgorie;
    @FXML
    private Label errorsImage;
    private FileChooser filechooser;
    private File file;
    EvenementService service = new EvenementService();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        HashMap<String, Integer> mapCategorie = service.getAllCategorie();
        // System.out.println(mapCategorie.values());
        for (String s : mapCategorie.keySet()) {
            System.out.println(s);
            caetgorie.getItems().add(s);
        }
    }

    @FXML
    private void closeDialog(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void ajouterCategorie(ActionEvent event) {

        if (nomE.getText().equals("")) {
            errorsNom.setText("Nom est obligatoire");
        } else if (capacite.getText().equals("") && Integer.parseInt(capacite.getText()) < 0) {
            errorCapacite.setText("Capaciter nom valide");
        } else if (description.getText().equals("")) {
            errorDescription.setText("Description est obligatoire");
        } else if (prixEvenement.getText().equals("") && Integer.parseInt(prixEvenement.getText()) < 0) {
            errorPrix.setText("Prix nom valide");
        } else if (file == null) {
            errorsImage.setText("Image field is required");
        } else {
            HashMap<String, Integer> mapCategorie = service.getAllCategorie();
            int id_Categorie = mapCategorie.get(caetgorie.getValue());
            String NomEvenement = nomE.getText();
            int capaciteInt = Integer.parseInt(capacite.getText());
            String desc = description.getText();
            String path = replaceFile(file.getAbsolutePath());
            int prixEv = Integer.parseInt(prixEvenement.getText());
            Evenement e = new Evenement();
            e.setNomEvenement(NomEvenement);
            e.setCapaciteEvenement(capaciteInt);
            e.setDescriptionEvenement(desc);
            e.setImageEvenement(path);
            e.setPrixEvenement(prixEv);
            java.sql.Date sqlDate =java.sql.Date.valueOf(datepickerP.getValue());
            e.setDateEvenement(sqlDate);
            System.out.println(id_Categorie);
            e.setIdCatgeorie(id_Categorie);
            EvenementService service = new EvenementService();
            if (service.ajouterEvenement(e)) {
                closeDialog(event);
                ObservableList observableList = FXCollections.observableArrayList(service.selectAllEvenement());
                EvenementBackController gestion = new EvenementBackController();

                gestion.myObservableList.clear();
                gestion.myObservableList.addAll(observableList);
                String tilte = "Ajout validé";
                String message = "on a valide votre ajout";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
            }

        }
    }

    @FXML
    private void uploadImage(ActionEvent event) {
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

    @FXML
    private void libelleEvent(KeyEvent event) {
        //     errorsLibelle.setText("");

    }

    @FXML
    private void DesciptionEvent(KeyEvent event) {
        //   errorsDescription.setText("");

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
        Path targetDirectory = Paths.get("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + filename);
        try {
            //copy source to target using Files Class
            Files.copy(sourceDirectory, targetDirectory);
        } catch (IOException ex) {
        }

        return filename;
    }

}
