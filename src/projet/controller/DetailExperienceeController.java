/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.Pulse;
import animatefx.animation.Swing;
import com.jfoenix.controls.JFXButton;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import projet.API.QrCodeMailApi;
import projet.models.Club;
import projet.models.Evenement;
import projet.models.Inscription;
import projet.models.reservation;
import projet.service.ReservationService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 * FXML Controller class
 *
 * @author houba
 */
public class DetailExperienceeController implements Initializable {

    @FXML
    private Label nom;
    @FXML
    private Label nomClub;
    @FXML
    private Label lieu;
    @FXML
    private Label cat;
    @FXML
    private Label date;
    @FXML
    private ImageView cover;
    @FXML
    private JFXButton close;
    @FXML
    private Rating rate;
    @FXML
    private TextField invi;
    @FXML
    private Label ratee;
    @FXML
    private Label nomEvenement;

    @FXML
    private Label capaciteEvenement;

    @FXML
    private Label prixEvenement;

    @FXML
    private Label Evenement;
    ReservationService service = new ReservationService();

    @FXML
    private Label ratee1;

    @FXML
    private Label descriptionEvenement;
    private Evenement experience;
    @FXML
    private Label desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ExperienceService experienceService = new ExperienceService();
        /*rate.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                int test2 = Integer.parseInt(invi.getText());
                //ratee.setText(newValue.toString());

               
                System.out.println("Evaluer");

                //ratee.setText(experienceService.CalculerMoyenneNote(test2)+"");

            }
        });
         */
    }

    @FXML
    private void ajouterPanier(MouseEvent event) {
    }

    public void setExperience(Evenement exp) throws FileNotFoundException {
        experience = exp;

        Image image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + experience.getImageEvenement()));
        //ImageView img = new ImageView(image);
        cover.setImage(image);

        String test = String.valueOf(exp.getIdEvenement());

//        invi.setText(test);
        //      invi.setVisible(false);
        nomEvenement.setText(experience.getNomEvenement());
        capaciteEvenement.setText(Integer.toString(experience.getCapaciteEvenement()));
        prixEvenement.setText(Integer.toString(experience.getPrixEvenement()));
        //Evenement.setText(test);
        String pattern = "MM/dd/yyyy HH:mm:ss";
        DateFormat df = new SimpleDateFormat(pattern);
        Date today = exp.getDateEvenement();
        String todayAsString = df.format(today);
        Evenement.setText(todayAsString);
        descriptionEvenement.setText(exp.getDescriptionEvenement());
        //date.setText(experience.getCapacite());

        //cat.setText(experience.getDateEvenement());
        //desc.setText(experience.getDescription());
    }

    @FXML
    private void exit() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void rate(MouseEvent event) {
    }

    @FXML
    public void ajouterInscrip(ActionEvent even) {
       
        reservation insc = new reservation();
        // System.out.println(idUtilisateur);
        insc.setIdevenement(experience.getIdEvenement());
        //insc.setStatus("non traitée");
        insc.setStatus("non confirmer");
        insc.setIdUser(8);
        service.ajouterInscription(insc);
        String tilte = "Reservation enregistre";
        String message = "votre reservation a été bien enregistrée.";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }
}
