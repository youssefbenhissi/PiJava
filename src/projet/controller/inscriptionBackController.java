/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import animatefx.animation.Pulse;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import projet.models.CategorieClub;
import projet.models.Inscription;
import projet.service.CategorieClubService;
import projet.service.ClubService;
import projet.service.InscriptionService;

/**
 *
 * @author youssef
 */
public class inscriptionBackController implements Initializable {

    @FXML
    private StackPane afficherTsEvenementStackPane;
    @FXML
    private Pane conteneurCategories;
    @FXML
    private Pane conteneurInscrip;
    @FXML
    private Pane conteneurClubs;
    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

    @FXML
    private HBox compteur;

    @FXML
    private Label countCategorie;

    @FXML
    private Label countClubs;

    @FXML
    private Label countInscrisp;

    @FXML
    public TableView<Inscription> listeClubs;

    @FXML
    private TableColumn<?, ?> questionP;

    @FXML
    private TableColumn<?, ?> reponseP;

    @FXML
    private TableColumn<?, ?> questionD;

    @FXML
    private TableColumn<?, ?> reponseD;

    @FXML
    private TableColumn<?, ?> questionT;

    @FXML
    private TableColumn<?, ?> reponseT;

    @FXML
    private TableColumn<?, ?> nomClub;
    @FXML
    private TableColumn<?, ?> idClub;

    @FXML
    public TableColumn<?, ?> status;
    @FXML
    private TableColumn<?, ?> actionD;
    @FXML
    private TableColumn<Inscription, String> action;
    InscriptionService service = new InscriptionService();
    int counter = 0;
    int counterCat = 0;

    int counterIns = 0;
    Boolean isIt = false;
    public static ObservableList<Inscription> observableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        compteurClub();
        compteurCategorie();
        compteurInscription();
        afficherCategorieClub();
    }

    @FXML
    public void afficherCategorieClub() {
        List<Inscription> myList = service.selectAllInscris();
        
        observableList = FXCollections.observableArrayList();
        questionP.setCellValueFactory(new PropertyValueFactory<>("questionPr"));
        reponseP.setCellValueFactory(new PropertyValueFactory<>("reponsePr"));
        questionD.setCellValueFactory(new PropertyValueFactory<>("questionDe"));
        reponseD.setCellValueFactory(new PropertyValueFactory<>("reponseDe"));
        questionT.setCellValueFactory(new PropertyValueFactory<>("reponseTr"));
        reponseT.setCellValueFactory(new PropertyValueFactory<>("reponseTr"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        nomClub.setCellValueFactory(new PropertyValueFactory<>("nomClub"));
        //idClub.setCellValueFactory(new PropertyValueFactory<>("idClub"));
        action.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));
        actionD.setCellValueFactory(new PropertyValueFactory<>("btn_confirmer"));
        myList.forEach(e -> {
            observableList.add(e);
            listeClubs.setItems(observableList);
        });
    }

    public void compteurClub() {
        Timer timer = new Timer(); //new timer
        counter = 0; //setting the counter to 10 sec
        ClubService cc = new ClubService();
        countClubs.setText(String.valueOf(0));
        if (cc.selectAllClubs().size() == 0) {
            countClubs.setText(String.valueOf(0));
        } else {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                new Pulse(conteneurClubs).play();
                counter++;
                countClubs.setText(String.valueOf(counter));
                int nbrClub = cc.selectAllClubs().size();
                if (counter == nbrClub) {

                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timeline.setCycleCount(cc.selectAllClubs().size());
            timeline.play();
        }
    }

    public void compteurCategorie() {
        Timer timer = new Timer();
        CategorieClubService cs = new CategorieClubService();
        if (cs.selectAll().size() == 0) {
            countCategorie.setText(String.valueOf(0));
        } else {
            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                new Pulse(conteneurCategories).play();

                counterCat++;
                countCategorie.setText(String.valueOf(counterCat));
                int nbrClub = cs.selectAll().size();
                if (counterCat == nbrClub) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(cs.selectAll().size());
            timelineCat.play();
        }
    }

    public void compteurInscription() {
        Timer timer = new Timer();
        InscriptionService cs = new InscriptionService();
        if (cs.selectAllInscris().size() == 0) {
            countInscrisp.setText(String.valueOf(0));
        } else {

            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                new Pulse(conteneurInscrip).play();
                counterIns++;
                countInscrisp.setText(String.valueOf(counterIns));
                int nbrClub = cs.selectAllInscris().size();
                if (counterCat == nbrClub) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(cs.selectAllInscris().size());
            timelineCat.play();
        }
    }
}
