package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.events.JFXDialogEvent;
import java.io.IOException;
import java.sql.SQLException;

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
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

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

    }

    @FXML
    void afficherMesReservationGUI(ActionEvent event) {

    }

    @FXML
    void afficherTsEvenementGUI(ActionEvent event) {
        
    }

    @FXML
    public void ajouterEvenementGUI(ActionEvent even) throws IOException {
        
    }

}
