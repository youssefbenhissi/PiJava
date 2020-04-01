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
import java.util.HashMap;
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
import projet.models.Club;
import projet.service.CategorieClubService;
import projet.service.ClubService;
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
public class ModifierClubController implements Initializable {

    @FXML
    public StackPane modifierEvenementStackPane;
    /**
     * modifier evenement *
     */
    @FXML
    public JFXComboBox<String> type_evenement_fx;
    ClubService ClubService = new ClubService();
    public static int id_evenement;
    public static String nomAfficher;

    @FXML
    public JFXTextField nom_club_fx;
    @FXML
    public JFXComboBox<String> categorie;
    @FXML
    public JFXTextField capacite_club_fx;

    @FXML
    public JFXTextField premiere_question_fx;

    @FXML
    public JFXTextField deuxieme_question_fx;
    @FXML
    public JFXTextField troisieme_question_fx;
    @FXML
    public JFXTextField description_club_fx;
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

        try {
            // pour le fileChooser
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                    new FileChooser.ExtensionFilter("Images", "*.*"),
                    new FileChooser.ExtensionFilter("Text File", "*.txt*"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Integer> mapCategorie = categoriesClubService.getAllCategorie();
        for (String s : mapCategorie.keySet()) {
            categorie.getItems().add(s);
        }

        // auto complete lieu en utilisant la librerie controlfx
        PopOver popOver1 = categoriesClubService.popNotification("le nom ne doit pas contenir aucun caractére spécial");
        nom_club_fx.setOnMouseEntered(mouseEvent -> {
            popOver1.show(nom_club_fx);
        });
        nom_club_fx.setOnMouseExited(mouseEvent -> {
            popOver1.hide();
        });

        PopOver popOver2 = categoriesClubService.popNotification("la liste deroulante vous aidera à choisir le club");
        categorie.setOnMouseEntered(mouseEvent -> {
            popOver2.show(categorie);
        });
        categorie.setOnMouseExited(mouseEvent -> {
            popOver2.hide();
        });
        PopOver popOver3 = categoriesClubService.popNotification("la capacité doit étre un entier positif");
        capacite_club_fx.setOnMouseEntered(mouseEvent -> {
            popOver3.show(capacite_club_fx);
        });
        capacite_club_fx.setOnMouseExited(mouseEvent -> {
            popOver3.hide();
        });

        PopOver popOver5 = categoriesClubService.popNotification("la première question doit contenir caractére spécial");
        premiere_question_fx.setOnMouseEntered(mouseEvent -> {
            popOver5.show(premiere_question_fx);
        });
        premiere_question_fx.setOnMouseExited(mouseEvent -> {
            popOver5.hide();
        });
        PopOver popOver6 = categoriesClubService.popNotification("la deuxième question doit un caractére spécial");
        deuxieme_question_fx.setOnMouseEntered(mouseEvent -> {
            popOver6.show(deuxieme_question_fx);
        });
        deuxieme_question_fx.setOnMouseExited(mouseEvent -> {
            popOver6.hide();
        });
        PopOver popOver7 = categoriesClubService.popNotification("la troisième question doit contenir un caractére spécial");
        troisieme_question_fx.setOnMouseEntered(mouseEvent -> {
            popOver7.show(troisieme_question_fx);
        });
        troisieme_question_fx.setOnMouseExited(mouseEvent -> {
            popOver7.hide();
        });

    }

    @FXML
    private void modifierEvenement() {
        int idEvenement = id_evenement;
        String nomClub = nom_club_fx.getText();
        System.out.println(nomClub);

        HashMap<String, Integer> mapCategorie = categoriesClubService.getAllCategorie();
//        int id_Categorie = mapCategorie.get(categorie.getValue());
        int id_Categorie = mapCategorie.get(categorie.getValue());
        String capaciteClub = capacite_club_fx.getText();
        String premiere_question = premiere_question_fx.getText();
        String deuxieme_question = deuxieme_question_fx.getText();
        String troisieme_question = troisieme_question_fx.getText();
        String description = description_club_fx.getText();
        String afficheEvenement;

        if (file == null) {
            afficheEvenement = nomAfficher;
        } else {
            afficheEvenement = replaceFile(file.getAbsolutePath());
        }

        BoxBlur blur = new BoxBlur(2, 2, 2);
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXButton button = new JFXButton("OKAY");
        button.getStyleClass().add("dialog-button");
        JFXDialog dialog = new JFXDialog(modifierEvenementStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
        button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {

            dialog.close();
        });
        if (nomClub.isEmpty()) {
            String tilte = "Champ Vide";
            String message = "tous les champs doivent être remplis";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else if (!ClubService.validationChaineSimpleAvecEspace(nomClub)) {
            String tilte = "Nom Club";
            String message = "le nom du club est non autorisé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else if (!ClubService.validationChaineSimpleNombre(capaciteClub)) {
            String tilte = "Capacite Club";
            String message = "la capacite du club est non autorisé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            int capacite = Integer.parseInt(capaciteClub);
            Club c = new Club();
            c.setId(idEvenement);
            c.setNom(nomClub);
            c.setDescription(description);
            c.setQuestionPr(premiere_question);
            c.setQuestionDe(deuxieme_question);
            c.setQuestionTr(troisieme_question);
            c.setCapacite(capacite);
            c.setPath(afficheEvenement);
            c.setCategorie_id(id_Categorie);
//               c.setCategorie_id(id_Categorie);
            boolean status = ClubService.modifierClub(c);

            if (status) {
                String tilte = "Club Modifié";
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
                String tilte = "Club Modifié";
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
    private void handleBrowser(ActionEvent event) {
        stage = (Stage) GUI.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);

        // try { deskTOP.open(file); } catch (IOException e) { e.printStackTrace(); }
        if (file != null) {
            System.out.println("" + file.getAbsolutePath());
            image = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(),
                    imageView.getFitHeight(), true, true);
            imageView.setImage(image);
            imageView.setPreserveRatio(true);
        }
    }

    @FXML
    private void Annuler(ActionEvent event) {

        Stage stage = (Stage) GUI.getScene().getWindow();
        stage.close();
    }

    private String generateFileName() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return "Evenement_" + saltStr;
    }

    private String replaceFile(String file) {
        String extension = file.substring(file.lastIndexOf("."), file.length());
        String filename = generateFileName() + extension;
        Path sourceDirectory = Paths.get(file);
        Path targetDirectory = Paths.get("C:\\wamp64\\www\\worldfriendship\\web\\Evenement\\image\\affiches\\" + filename);
        try {
            Files.copy(sourceDirectory, targetDirectory);
        } catch (IOException ex) {
            Logger.getLogger(ModifierClubController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return filename;
    }

}
