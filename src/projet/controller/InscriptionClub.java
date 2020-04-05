package projet.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import projet.models.Inscription;
import projet.service.InscriptionService;

/**
 *
 * @author Benny
 */
public class InscriptionClub implements Initializable {

    @FXML
    public Label questionP;

    @FXML
    public Label questionD;

    @FXML
    public Label questionT;
    @FXML
    private TextField ReponsePr;

    @FXML
    private TextField ReponseDe;

    @FXML
    private TextField ReponseTr;
    @FXML
    public Label idClub;
    InscriptionService service = new InscriptionService();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }
    @FXML
    public void ajouterInscrip(ActionEvent even){
        Inscription insc = new Inscription();
        insc.setQuestionPr(questionP.getText());
        insc.setQuestionDe(questionD.getText());
        insc.setQuestionTr(questionT.getText());
        insc.setReponsePr(ReponsePr.getText());
        insc.setReponseDe(ReponseDe.getText());
        insc.setReponseTr(ReponseTr.getText());
        insc.setIdClub(Integer.parseInt(idClub.getText()));
        insc.setStatus("non valide");
        insc.setIdUser(7);
        service.ajouterInscription(insc);
    }

}
