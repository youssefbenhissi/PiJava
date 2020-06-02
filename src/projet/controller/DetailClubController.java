package projet.controller;

import animatefx.animation.Swing;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
    private Button inscription;
    public int idUtilisateurDetail;
    @FXML
    private Rating rating;
    @FXML
    private Label msg;
    public int valeurEtoile;
    ClubService ClubService = new ClubService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        System.out.println("hahhhahjhbhsdjhbsdf :"+idUtilisateurDetail);
        rating.setUpdateOnHover(false);

    }

    public void setClub(Club exp) throws FileNotFoundException {
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                rating.setDisable(true);
                valeurEtoile = newValue.intValue();
                String tilte = "Merci pour votre avis";
                String message = "On a approuvé votre avis.";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                Club ClubDeBase = new Club();
                int nbrFoisLike = exp.getNbrFoisLike();
                System.out.println("nombre precedent: " + nbrFoisLike);
                nbrFoisLike++;
                System.out.println(nbrFoisLike);
                int nbrLike = exp.getNbrLike();
                nbrLike += valeurEtoile;
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

    @FXML
    public void Inscription(ActionEvent even) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/InscriptionClub.fxml"));
        Parent root = loader.load();
        InscriptionClub controller = (InscriptionClub) loader.getController();
        controller.idClub.setText(Integer.toString(experience.getId()));
        controller.questionP.setText(experience.getQuestionPr());
        controller.questionD.setText(experience.getQuestionDe());
        controller.questionT.setText(experience.getQuestionTr());
        Stage primaryStage = new Stage();
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
