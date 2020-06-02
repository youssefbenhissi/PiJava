/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import projet.models.CategorieEvenement;
import projet.models.Evenement;
import projet.service.CategorieEvenementService;
import projet.service.EvenementService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author yassine
 */
public class ModifierEvenementController implements Initializable {

    @FXML
    private JFXTextField nomCategorie;

    @FXML
    private Label errorsNom;

    @FXML
    private JFXTextField description;

    @FXML
    private Label errorsLibelle;

    @FXML
    private JFXTextField capacite;
    @FXML
    private JFXTextField prix;
    @FXML
    private Label errorsCapacite;

    @FXML
    private Label prixError;

    @FXML
    private JFXComboBox<String> caetgorie;

    @FXML
    private ImageView imageView;

    @FXML
    private Label errorsImage;

    private Evenement categorie;

    private FileChooser filechooser;

    private Image image;

    File file;
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
    private void closeDialog(MouseEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void libelleEvent(KeyEvent event) {
        errorsLibelle.setText("");
    }

    public void setData(Evenement categorieProduit) throws FileNotFoundException {
        FileInputStream imageStream;
        Image image;
        this.categorie = categorieProduit;
        nomCategorie.setText(categorieProduit.getNomEvenement());
        description.setText(categorieProduit.getDescriptionEvenement());
        capacite.setText(Integer.toString(categorieProduit.getCapaciteEvenement()));
        prix.setText(Integer.toString(categorieProduit.getPrixEvenement()));
        caetgorie.setValue(categorieProduit.getNomCategorie());
        imageStream = new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\"+ categorieProduit.getImageEvenement());
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
        if(capacite.getText().equals("")){
            errorsCapacite.setText("Description field is required");
        }
        if(prix.getText().equals("")){
            prixError.setText("Description field is required");
        }
        else if(imageView==null && categorie.getImageEvenement().equals("")){
            errorsImage.setText("First image field is required");
        }
        else {
        
            
            String path;
            
            String nomE = nomCategorie.getText();
            String descp = description.getText();
            int cap = Integer.parseInt(capacite.getText());
            int pr=Integer.parseInt(prix.getText());
            HashMap<String, Integer> mapCategorie = service.getAllCategorie();
            int id_Categorie = mapCategorie.get(caetgorie.getValue());
            if(file==null){
              path = categorie.getImageEvenement();
            }else {
              path = replaceFile(file.getAbsolutePath());
            }
           
            Evenement c = new Evenement(categorie.getIdEvenement(), nomE, cap, descp, path,pr,id_Categorie);
     
            EvenementService service = new EvenementService();
            
            if(service.modifierEvenement(c)){

                closeDialog(event);
                ObservableList observableList = FXCollections.observableArrayList( service.selectAllEvenement());
                EvenementBackController gestion = new EvenementBackController();
                
                gestion.myObservableList.clear();
                gestion.myObservableList.addAll(observableList);
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
            else
                System.out.println("zzzzzzzzzzzzz");
             
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
