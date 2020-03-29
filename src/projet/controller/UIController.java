package projet.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
        makeStageDrageable();

    }

    private void makeStageDrageable() {
        parent.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        parent.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                 Stage primaryStage = new Stage();
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    @FXML
    private void clicked_on_signin(ActionEvent event) {
        if (flag) {
            pane.setVisible(true);
            flag = false;
            signin.setText("Close");
        } else {
            pane.setVisible(false);
            flag = true;
            signin.setText("Sign In");
        }
    }

    @FXML
    private void open_facebook(ActionEvent event) {
    }

    @FXML
    private void open_twitter(ActionEvent event) {
    }

    @FXML
    private void open_likedin(ActionEvent event) {
    }

    @FXML
    private void open_google(ActionEvent event) {
    }

}
