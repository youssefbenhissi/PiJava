package projet.controller;

import animatefx.animation.Swing;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.Rating;
import projet.models.Club;
import projet.service.ClubService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class DetailClubController implements Initializable {

    @FXML
    private AnchorPane layer1;
    @FXML
    private Label cap;
    private Club experience;
    @FXML
    private ImageView imageBack;
    @FXML
    private Label nom;
    @FXML
    private Label nomCategorie;
    @FXML
    private Label description;
    @FXML
    private Label capacite;
    @FXML
    private Label nomClub;
    
    @FXML
    private Rating rating;
    @FXML
    private Label msg;
    public int valeurEtoile;
    ClubService ClubService = new ClubService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rating.setUpdateOnHover(false);

        

    }

    public void setClub(Club exp) throws FileNotFoundException {

        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rating.setDisable(true);
                valeurEtoile = newValue.intValue();
                System.out.println(newValue);
                String tilte = "Merci pour votre avis";
                String message = "On a approuvé votre avis.";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                Club ClubDeBase = new Club();
                int nbrFoisLike = exp.getNbrFoisLike();
                nbrFoisLike++;
                int nbrLike = exp.getNbrLike();
                System.out.println(nbrLike);
                nbrLike += valeurEtoile;
                System.out.println(nbrLike);
                ClubDeBase.setNbrFoisLike(nbrFoisLike);
                float moyenneLike = (nbrLike / nbrFoisLike);
                ClubDeBase.setMoyenneLike(moyenneLike);
                ClubDeBase.setNbrLike(nbrLike);
                ClubDeBase.setId(exp.getId());
                ClubService.modifierLike(ClubDeBase);
                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));

            }
        });

        experience = exp;

        Image image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + experience.getPath()));
        //ImageView img = new ImageView(image);
        imageBack.setImage(image);

        String test = String.valueOf(exp.getId());
        nomClub.setText(experience.getNom());
        nom.setText(experience.getNom());
        nomCategorie.setText(experience.getNomcategorie());
        String capaciteText = Integer.toString(experience.getCapacite());
        cap.setText(capaciteText);
        capacite.setText(capaciteText);
        description.setText(experience.getDescription());

    }

    @FXML
    private void quitter() {
        // get a handle to the stage
        Stage stage = (Stage) layer1.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
