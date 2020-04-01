package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;
import projet.models.CategorieClub;
import projet.service.CategorieClubService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author solta
 */
public class ModifierCategorieClubController implements Initializable {

    @FXML
    public StackPane modifierEvenementStackPane;
    /**
     * modifier evenement *
     */
    @FXML
    public JFXComboBox<String> type_evenement_fx;

    public static int id_evenement;
    public static String nomAfficher;

    @FXML
    public JFXTextField nom_categorie_fx;
    @FXML
    public JFXTextField id_categorie_fx;

    @FXML
    public JFXTextField lieu_evenement_fx;

    @FXML
    public JFXTextField description_evenement_fx;

    @FXML
    public JFXTextField capacite_evenement_fx;
    @FXML
    public JFXDatePicker date_debut_evenement_fx;
    @FXML
    public JFXDatePicker date_fin_evenement_fx;
    @FXML
    public ImageView imageView;
    public File file = null;

    @FXML
    public AnchorPane GUI;

    /**
     * end modifier evenement *
     */
    ObservableList<String> dataTypeEvenement = FXCollections.observableArrayList("Associatif", "Culturel", "Autres");
    public FileChooser fileChooser;

    @FXML
    public Stage stage;

    public Image image;

    @FXML
    private Button btnBrowser;

    @FXML
    private Button modifierButton;
    CategorieClubService categoriesClubService = new CategorieClubService();

    @FXML
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        PopOver popOver1 = categoriesClubService.popNotification("le nom ne doit pas contenir aucun caractére spécial ni espace");
        nom_categorie_fx.setOnMouseEntered(mouseEvent -> {
            popOver1.show(nom_categorie_fx);
        });
        nom_categorie_fx.setOnMouseExited(mouseEvent -> {
            popOver1.hide();
        });
    }

    @FXML
    private void modifierEvenement() {

        String nomCategorie = nom_categorie_fx.getText();
        int idEvenement = id_evenement;

        BoxBlur blur = new BoxBlur(2, 2, 2);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OKAY");
        button.getStyleClass().add("dialog-button");
        JFXDialog dialog = new JFXDialog(modifierEvenementStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {

            dialog.close();
        });
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
        } else if (!categoriesClubService.validationChaineSimpleSansEspace(nomCategorie)) {
            String tilte = "Nom Catgeorie";
            String message = "le nom de la catégorie est non autorisé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            CategorieClub evenement = new CategorieClub();
            evenement.setNomCategorie(nomCategorie);
            evenement.setId(idEvenement);
            Boolean status = categoriesClubService.modifierCategorie(evenement);
            if (status) {
                String tilte = "Evenement Modifié";
                String message = "votre modification a été bien enregistré.";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                return;
            } else {
                String tilte = "Evenement Modifié";
                String message = "votre modification n'est correcte";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(3000));
                return;
            }
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {

        Stage stage = (Stage) GUI.getScene().getWindow();
        stage.close();
    }

}
