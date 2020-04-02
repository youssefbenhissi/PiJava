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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PanneauDeControle {

    VBox sidebarButton;
    @FXML
    Label label_EtatBaseDonnees;
    @FXML
    private StackPane panneauControleStackPane;

    @FXML
    private AnchorPane panneauControleAnchorPane;
    AnchorPane mygames;
    public Boolean etat = false;
    

    @FXML
    private void mouse_clicked(MouseEvent event) throws IOException {
                     Parent root = FXMLLoader.load(getClass().getResource("/projet/views/topthree.fxml"));

        try {
            mygames = FXMLLoader.load(getClass().getResource("/projet/views/topthree.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(PanneauDeControle.class.getName()).log(Level.SEVERE, null, ex);
        }
        // sidebarButton = (VBox) ((Node) event.getSource());

        Stage primaryStage = new Stage();
        if (etat == false) {
           
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            primaryStage.setScene(scene);
            primaryStage.initStyle(StageStyle.TRANSPARENT);
            primaryStage.setY(0);
            primaryStage.setX(500);
            primaryStage.show();
            etat = true;
        } else {
           primaryStage.close();
           etat=false;
        }
    }

    @FXML
    public void etatBaseDonnees(ActionEvent even) throws SQLException {

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
