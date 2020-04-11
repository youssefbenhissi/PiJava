/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import projet.models.Parent;
import projet.models.Personnel;
import projet.service.PersonnelService;

/**
 *
 * @author user
 */
public class ajouterPersonnelController implements Initializable {

    private FileChooser filechooser;
    private File file;

    @FXML
    private VBox layer1;

    @FXML
    private JFXTextField nomP;

    @FXML
    private Label errorsNom;

    @FXML
    private JFXTextField prenomP;

    @FXML
    private Label errorPrenom;

    @FXML
    private JFXTextField descriptionP;

    @FXML
    private Label errorDescription;

    @FXML
    private ComboBox<String> typeP;

    @FXML
    private JFXTextField soldeP;

    @FXML
    private Label errorSolde;

    @FXML
    private JFXDatePicker datepickerP;

    @FXML
    private ImageView imageView;

    @FXML
    private Label errorsImage;
    PersonnelService service = new PersonnelService();

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
        Path targetDirectory = Paths.get("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + filename);
        try {
            //copy source to target using Files Class
            Files.copy(sourceDirectory, targetDirectory);
        } catch (IOException ex) {
            System.out.println(ex);
        }

        return filename;
    }

    @FXML
    private void ajouterParent(MouseEvent event) throws ParseException {
        if (nomP.getText().equals("")) {
            errorsNom.setText("Nom field is required");
        }
        if (prenomP.getText().equals("")) {
            errorPrenom.setText("Prenom field is required");
        }
        if (descriptionP.getText().equals("")) {
            errorDescription.setText("Description field is required");
            if (file == null) {
                System.out.println("");
                errorsImage.setText("Image field is required");
            }
        } else {
            System.out.println("jjjjjjjjjjj");

            String afficherParent;
            if (file == null) {
                afficherParent = "";
            } else {
                afficherParent = replaceFile(file.getAbsolutePath());
            }
            Personnel p = new Personnel();
            p.setNom(nomP.getText());
            p.setPrenom(prenomP.getText());
//DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//            ZoneId defaultZoneId = ZoneId.systemDefault();
            //LocalDate localDate = 
            //Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
           java.sql.Date sqlDate =java.sql.Date.valueOf(datepickerP.getValue());
  //          Date d =dateFormat.parse(formattedDate);
            p.setDate_debutTravail(sqlDate);
            p.setSoldeConge(Integer.parseInt(soldeP.getText()));
            p.setDescription(descriptionP.getText());
            //p.getNumTelephone(Integer.parseInt(numTelephoneP.getText()));
            p.setImage(afficherParent);
            p.setType(typeP.getValue());
            service.ajouterPersonnel(p);
        }
    }


@Override
        public void initialize(URL location, ResourceBundle resources) {
        //  throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        typeP.getItems().add("enseignant");
        typeP.getItems().add("surveillant");
        typeP.getItems().add("directeur");
        typeP.getItems().add("concierge");
    }

    @FXML
        private void quitter() {
        // get a handle to the stage
        Stage stage = (Stage) layer1.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
