/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import projet.models.CategorieEvenement;
import projet.service.CategorieEvenementService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author yassine
 */
public class ModifierCategorieEvenementController implements Initializable {

    @FXML
    private JFXTextField nomCategorie;

    @FXML
    private Label errorsNom;

    @FXML
    private JFXTextField description;

    @FXML
    private Label errorsLibelle;

    @FXML
    private ImageView imageView;

    @FXML
    private Label errorsImage;

    private CategorieEvenement categorie;

    private FileChooser filechooser;

    private Image image;

    File file;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void closeDialog(MouseEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void libelleEvent(KeyEvent event) {
        errorsLibelle.setText("");
    }

    public void setData(CategorieEvenement categorieProduit) throws FileNotFoundException {
        FileInputStream imageStream;
        Image image;
        this.categorie = categorieProduit;
        nomCategorie.setText(categorieProduit.getNomCategorieEvenement());
        description.setText(categorieProduit.getDescriptionCat());
        imageStream = new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\"+ categorieProduit.getImageCat());
        image = new Image(imageStream);
        imageView.setImage(image);

    }

    @FXML
    private void uploadimg(MouseEvent event) {
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
    private void modifierCategorie(MouseEvent event) {

        if(nomCategorie.getText().equals("")){
            errorsLibelle.setText("Name field is required");
        }
        if(description.getText().equals("")){
            errorsLibelle.setText("Description field is required");
        }
        else if(imageView==null && categorie.getImageCat().equals("")){
            errorsImage.setText("First image field is required");
        }
        else {
            
            String path;
            
            String nomC = nomCategorie.getText();
            String descp = description.getText();
            
            if(file==null){
              path = categorie.getImageCat();
            }else {
              path = replaceFile(file.getAbsolutePath());
            }
           
           
            CategorieEvenement c = new CategorieEvenement(categorie.getIdCtegorieEvenement(), nomC, path, descp);
     
            CategorieEvenementService service = new CategorieEvenementService();
            
            if(service.modifierCategorieEvenement(c)){
                
                closeDialog(event);
                ObservableList observableList = FXCollections.observableArrayList( service.selectAllCategorieEvenement());
                EvenementBackController gestion = new EvenementBackController();
                
                gestion.ObservableList.clear();
                gestion.ObservableList.addAll(observableList);
                String tilte = "Modification validé";
                String message = "on a valide votre modificationi";
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
