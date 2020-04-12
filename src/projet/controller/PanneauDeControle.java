package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import jxl.write.WriteException;
import projet.service.EvenementService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class PanneauDeControle {

    @FXML
    Label label_EtatBaseDonnees;
    @FXML
    private StackPane panneauControleStackPane;

    @FXML
    private AnchorPane panneauControleAnchorPane;

    @FXML
    public void etatBaseDonnees(ActionEvent even) throws SQLException {

      
    }

    public void afficherPage(String nomPage, String titre) throws IOException {

       
    }

    @FXML
    public void login(ActionEvent even) throws IOException {
    }

    @FXML
    public void CreerCompte(ActionEvent even) throws IOException {
    }

    @FXML
    public void accueilEvenement(ActionEvent even) throws IOException {
    }

      @FXML
    public void dashBoardEvenement(ActionEvent even) throws IOException {
    }
    /**
     * test
     */
    
    @FXML
    void afficherMesEvenementGUI(ActionEvent event) {
        EvenementService service =new EvenementService();
        try {
            service.exportXLS();
             String tilte = "fichier pret";
                String message = "le fichier est telecharge";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;
                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
        } catch (WriteException ex) {
            Logger.getLogger(PanneauDeControle.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void afficherMesReservationGUI(ActionEvent event) {

    }

    @FXML
    void ajouterCategorieGUI(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterCategorieEvenement.fxml"));
        Scene scene = new Scene(root);  
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    public void ajouterEvenementGUI(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterEvenement.fxml"));
        Scene scene = new Scene(root);  
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
@FXML
    public void afficherReservationGUI(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/reservationBack.fxml"));
        Scene scene = new Scene(root);  
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
