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
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import projet.models.Club;

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

    private Club experience;
    @FXML
    private Label desc;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //ExperienceService experienceService = new ExperienceService();
        rate.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                int test2 = Integer.parseInt(invi.getText());
                //ratee.setText(newValue.toString());

               
                System.out.println("Evaluer");

                //ratee.setText(experienceService.CalculerMoyenneNote(test2)+"");

            }
        });

    }

    @FXML
    private void ajouterPanier(MouseEvent event) {
    }

    public void setExperience(Club exp) throws FileNotFoundException {
        experience = exp;

        Image image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + experience.getPath()));
        //ImageView img = new ImageView(image);
        cover.setImage(image);

        String test = String.valueOf(exp.getId());
        
        invi.setText(test);
        invi.setVisible(false);
        nom.setText(experience.getNom());
        lieu.setText(experience.getNomcategorie());
         
        //date.setText(experience.getCapacite());
        cat.setText(experience.getNomcategorie());
        desc.setText(experience.getDescription());
    }

    @FXML
    private void exit() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void rate(MouseEvent event) {
    }
}