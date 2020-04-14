/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import static projet.controller.inscriptionBackController.observableList;
import projet.models.CategorieClub;
import projet.models.Inscription;
import projet.models.reservation;
import projet.service.InscriptionService;
import projet.service.ReservationService;

/**
 *
 * @author youssef
 */
public class ReservationBackController implements Initializable {

    @FXML
    private JFXButton close;
    @FXML
    private StackPane afficherTsEvenementStackPane;

    @FXML
    private Pane pnlCustomer;

    @FXML
    private Pane pnlOrders;

    @FXML
    private Pane pnlMenus;

    @FXML
    private Pane pnlOverview;

    @FXML
    private TableView<reservation> listeClubs;

    @FXML
    private TableColumn<?, ?> idReservation;

    @FXML
    private TableColumn<?, ?> nomEvenement;

    @FXML
    private TableColumn<?, ?> nomUtilisateur;

    @FXML
    private TableColumn<?, ?> statusEvenement;
    @FXML
    private JFXTextField rechercheBar;
    //public static observableList;
    @FXML
    private TableColumn<?, ?> action;
    static public ObservableList<reservation> observableList;
    ReservationService service = new ReservationService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficherCategorieClub();
    }

    @FXML
    public void afficherCategorieClub() {
        List<reservation> myList = service.selectAllReservations();

        observableList = FXCollections.observableArrayList();
        idReservation.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomEvenement.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        nomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
        statusEvenement.setCellValueFactory(new PropertyValueFactory<>("status"));
        action.setCellValueFactory(new PropertyValueFactory<>("btn_confirmer"));
        myList.forEach(e -> {
            //System.out.println(e.getNomUser());
            observableList.add(e);
            listeClubs.setItems(observableList);
        });
    }

    @FXML
    private void rechercher(KeyEvent event) {

        if (!rechercheBar.getText().isEmpty()) {
            listeClubs.setVisible(true);
            List<reservation> myList = service.rechercheCategories(rechercheBar.getText());
            ObservableList<reservation> observableList = FXCollections.observableArrayList();
            idReservation.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomEvenement.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
            nomUtilisateur.setCellValueFactory(new PropertyValueFactory<>("nomUser"));
            statusEvenement.setCellValueFactory(new PropertyValueFactory<>("status"));
            action.setCellValueFactory(new PropertyValueFactory<>("btn_confirmer"));
            myList.forEach(e -> {
                System.out.println(e);
                observableList.add(e);
                // System.out.println(observableList);
            });
            listeClubs.setItems(observableList);
        } else {
            if (rechercheBar.getText().isEmpty()) {
                listeClubs.getItems().clear();
                listeClubs.getItems().addAll(service.selectAllReservations());
            }

        }
    }
     @FXML
    private void exit() {
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
}
