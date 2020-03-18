package projet.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import projet.models.CategorieClub;
import projet.service.CategorieClubService;
import projet.service.ClubService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author JISOO
 */
public class AjouterCategorieEtClubController implements Initializable {

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer2;
    @FXML
    private JFXButton signin;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
    @FXML
    private JFXButton signup;
    @FXML
    private Label a2;
    @FXML
    private Label b2;
    
    
    @FXML
    private JFXButton btnsignup;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private TextField u1;
    @FXML
    private TextField u2;
    @FXML
    private TextField u3;

    @FXML
    private TextField u4;
    @FXML
    private TextField n1;
    @FXML
    private AnchorPane layer1;

    ClubService ClubService = new ClubService();

    CategorieClubService categoriesClubService = new CategorieClubService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        b2.setVisible(false);
        btnsignin.setVisible(false);
        n1.setVisible(false);
        u1.setVisible(true);
        u2.setVisible(true);
        u3.setVisible(true);
        u4.setVisible(true);

        PopOver popOver1 = categoriesClubService.popNotification("le nom ne doit pas contenir aucun caractére spécial");
        u1.setOnMouseEntered(mouseEvent -> {
            popOver1.show(n1);
        });
        u1.setOnMouseExited(mouseEvent -> {
            popOver1.hide();
        });

        PopOver popOver2 = categoriesClubService.popNotification("la liste deroulante vous aidera à choisir le club");
        u2.setOnMouseEntered(mouseEvent -> {
            popOver2.show(u2);
        });
        u2.setOnMouseExited(mouseEvent -> {
            popOver2.hide();
        });
        PopOver popOver3 = categoriesClubService.popNotification("la capacité doit étre un entier positif");
        u4.setOnMouseEntered(mouseEvent -> {
            popOver3.show(u4);
        });
        u4.setOnMouseExited(mouseEvent -> {
            popOver3.hide();
        });
        PopOver popOver4 = categoriesClubService.popNotification("le nom ne doit pas contenir aucun caractére spécial");
        n1.setOnMouseEntered(mouseEvent -> {
            popOver4.show(n1);
        });
        n1.setOnMouseExited(mouseEvent -> {
            popOver4.hide();
        });
    }

    @FXML
    private void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);
        b2.setVisible(true);

        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        u1.setVisible(false);
        u2.setVisible(false);
        u3.setVisible(false);
        u4.setVisible(false);
        slide.setOnFinished((e -> {
            u1.setText("");
            u2.setText("");
            u3.setText("");
            u4.setText("");

        }));
    }

    @FXML
    private void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);
        btnsignin.setVisible(false);
        b2.setVisible(false);

        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        signin.setVisible(true);
        a2.setVisible(true);
        btnsignup.setVisible(true);
        n1.setVisible(false);
        u1.setVisible(true);
        u2.setVisible(true);
        u3.setVisible(true);
        u4.setVisible(true);
        slide.setOnFinished((e -> {

            n1.setText("");

        }));
    }

    @FXML
    private void btnsignup(MouseEvent event) {
    }

    @FXML
    private void sign(MouseEvent event) {

    }

    @FXML
    private void click(ActionEvent event) {
        String nomCategorie = n1.getText();
        
        if (nomCategorie.isEmpty()) {
            String tilte = "Champ Vide";
            String message = "tous les champs doivent être remplis";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        }
        else if (!categoriesClubService.validationChaineSimpleSansEspace(nomCategorie)){
            String tilte = "Nom Catgeorie";
            String message = "le nom de la catégorie est non autorisé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        }
        else {
            CategorieClub c = new CategorieClub();
            c.setNomCategorie(nomCategorie);
            categoriesClubService.ajouterCategorie(c);
            String tilte = "Ajout validé";
            String message = n1.getText();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }
    @FXML
    private void quitter() {
        // get a handle to the stage
        Stage stage = (Stage) layer1.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
