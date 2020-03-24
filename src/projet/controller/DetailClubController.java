package projet.controller;

import animatefx.animation.Swing;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import projet.models.Club;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        rating.setUpdateOnHover(false);
        rating.ratingProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {

                //msg.setText("rating"+newValue);
                rating.setDisable(true);
                
                System.out.println(newValue);

            }
        });
    }

    public void setClub(Club exp) throws FileNotFoundException {
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
