package projet.controller;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class UIController implements Initializable {

    @FXML
    private AnchorPane pane;
    @FXML
    private JFXButton signin;

    boolean flag = true;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Pane parent;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        pane.setVisible(false);

    }

    @FXML
    private void clicked_on_signin(ActionEvent event) {
        if (flag) {
            pane.setVisible(true);
            flag = false;
            signin.setText("Fermer");
        } else {
            pane.setVisible(false);
            flag = true;
            signin.setText("Ouvrir");
        }
    }

    @FXML
    private void open_facebook(ActionEvent event) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/StatistiquesCategorieEtClub.fxml"));
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void open_twitter(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/inscriptionBack.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void open_likedin(ActionEvent event) throws IOException {
         Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterCategorieEtClub.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void open_google(ActionEvent event) {
         // get a handle to the stage
        Stage stage = (Stage) pane.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

}
