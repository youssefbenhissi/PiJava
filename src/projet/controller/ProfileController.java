/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RegexValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projet.models.Utilisateur;
import projet.service.ParentService;

/**
 * FXML Controller class
 *
 * @author Damdoum
 */
public class ProfileController implements Initializable {

    @FXML
    private Circle myCircle;
    @FXML
    private JFXTextField NomField;
    @FXML
    private JFXTextField PrenomField;
    @FXML
    private JFXTextField TelephoneField;
    @FXML
    private JFXTextField EmailField;

    @FXML
    private JFXButton imgBtn;

    Alert alert = new Alert(Alert.AlertType.INFORMATION);

    Utilisateur user;

    File selectedFile;
    @FXML
    private JFXButton validateBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        /*RequiredFieldValidator validator = new RequiredFieldValidator();
          validator.setMessage("Champ obligatoire !");
          RegexValidator reg = new RegexValidator();
          reg.setRegexPattern("^[a-zA-Z ]+$");
          RegexValidator regNum = new RegexValidator();
          regNum.setRegexPattern("^[0-9]{8}");
          reg.setMessage("Des lettres seulement !");
          regNum.setMessage("Ce Champ Contient 8 chiffres !");
              fieldNom.getValidators().add(validator);
          fieldNom.getValidators().add(reg);
       
          fieldPrenom.getValidators().add(validator);
           fieldPrenom.getValidators().add(reg);
           
           fieldNumero.getValidators().add(regNum);*/
        //fieldDate.getValidators().add(regDate);
        // fieldNumero.getValidators().add(validator);
        // fieldNom.getValidators().add(reg);
        // fieldPrenom.getValidators().add(reg);
        //fieldDate.getValidators().add(reg);
        //  fieldNumero.getValidators().add(reg);
        ParentService service = new ParentService();
        user = service.selectUser(8);
        System.err.println(user);
        PrenomField.setText(user.getPrenom());
        NomField.setText(user.getNom());
        TelephoneField.setText(user.getTelephone() + "");
        EmailField.setText(user.getEmail());
        myCircle.setStroke(Color.SEAGREEN);
        Image im = new Image("file:" + user.getImage());
        myCircle.setFill(new ImagePattern(im));
        myCircle.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));

    }

    // TextField yourTextField = new TextField();
    @FXML
    private void Modifier(ActionEvent event) {

        /*if(fieldNom.validate()&& fieldNumero.validate()&&fieldPrenom.validate())
        {*/
        try {
            user.setNom(NomField.getText());
            user.setPrenom(PrenomField.getText());
            user.setEmail(EmailField.getText());
            user.setTelephone(Integer.parseInt(TelephoneField.getText()));
            if (selectedFile != null) {
                if (!user.getImage().equals("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + user.getEmail() + selectedFile.getName())) {
                    File image = new File("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + user.getEmail() + selectedFile.getName());
                    Files.copy(selectedFile.toPath(), image.toPath());
                }
                user.setImage("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + user.getEmail() + selectedFile.getName());
            }

        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }

        alert.setTitle("Inscription");
        alert.setHeaderText("Profil Modifié !");
        alert.setContentText("Vous allez etre rederigé vers le menu principal !!");
        alert.showAndWait();
//        UserSession.getInstace(user);
        ParentService serv = new ParentService();
        serv.modifierP(user);
        Stage stage = (Stage) imgBtn.getScene().getWindow();
        // do what you have to do
        stage.close();
        // }
    }

    @FXML
    private void changeImg(ActionEvent event) {

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG files", "*.jpg"), new FileChooser.ExtensionFilter("PNG files", "*.png"), new FileChooser.ExtensionFilter("JPEG files", "*.jpeg"));
        selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            Image im = new Image("file:" + selectedFile.getPath());
            myCircle.setFill(new ImagePattern(im));
        }
    }

    @FXML
    private void Capture(ActionEvent event) {
        try {
            Stage primaryStage = new Stage();
            Parent root = FXMLLoader.load(getClass().getResource("/projet/views/CaptureWindow.fxml"));
            Scene scene = new Scene(root);
            //scene.setFill(Color.TRANSPARENT);
            primaryStage.setScene(scene);
            //primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.show();
//      ToolBarAssistant.loadWindow(getClass().getResource("/projet/views/CaptureWindow.fxml"), "Prendre Une Photo", null);
//         Stage stage = (Stage) imgBtn.getScene().getWindow();
//    // do what you have to do
//    stage.close();
        } catch (IOException ex) {
            Logger.getLogger(ProfileController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
