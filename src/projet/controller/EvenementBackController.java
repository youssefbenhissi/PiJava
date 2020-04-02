/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import projet.models.CategorieEvenement;
import projet.models.Evenement;
import projet.service.CategorieEvenementService;
import projet.service.EvenementService;

/**
 *
 * @author Iheb
 */
public class EvenementBackController implements Initializable {

    @FXML
    private TableColumn<?, ?> id;
    @FXML
    private TableColumn<?, ?> idEvenement;
    @FXML
    private TableColumn<?, ?> nomEvenement;
    @FXML
    private TableColumn<?, ?> descriptionCat;
    @FXML
    private TableColumn<?, ?> nomCategorieEvenement;
    @FXML
    private TableColumn<?, ?> capaciteEvenement;
    @FXML
    private TableColumn<?, ?> descriptionEvenement;
    @FXML
    private TableColumn<?, ?> prixEvenement;
    @FXML
    private TableColumn<?, ?> dateEvenement;
    @FXML
    private TableColumn<?, ?> idUser;
    @FXML
    private TableView<CategorieEvenement> listeCategorie;
    @FXML
    private TableColumn<?, ?> nomCategorie;
    CategorieEvenementService serviceCategorie = new CategorieEvenementService();
    @FXML
    private TableView<Evenement> listeEvenement;
    EvenementService serviceEvenement = new EvenementService();
//drawer 
    
    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficherCategorieEvenement();
         afficherEvenement();
         drawer();
    }

    void afficherCategorieEvenement() {
        List<CategorieEvenement> myList = serviceCategorie.selectAllCategorieEvenement();
        ObservableList<CategorieEvenement> myObservableList = FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("idCtegorieEvenement"));
        descriptionCat.setCellValueFactory(new PropertyValueFactory<>("descriptionCat"));
        nomCategorieEvenement.setCellValueFactory(new PropertyValueFactory<>("nomCategorieEvenement"));

        myList.forEach(e -> {
            myObservableList.add(e);
            listeCategorie.setItems(myObservableList);
        });
    }

    void afficherEvenement() {
        List<Evenement> myList = serviceEvenement.selectAllEvenement();
        ObservableList<Evenement> myObservableList = FXCollections.observableArrayList();
        idEvenement.setCellValueFactory(new PropertyValueFactory<>("idEvenement"));
        nomEvenement.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        capaciteEvenement.setCellValueFactory(new PropertyValueFactory<>("capaciteEvenement"));
        descriptionEvenement.setCellValueFactory(new PropertyValueFactory<>("descriptionEvenement"));
        prixEvenement.setCellValueFactory(new PropertyValueFactory<>("prixEvenement"));
        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
        dateEvenement.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
        myList.forEach(e -> {
            myObservableList.add(e);
            listeEvenement.setItems(myObservableList);
        });
    }
    void drawer(){
         try {
            VBox menu = FXMLLoader.load(getClass().getResource("/projet/views/MenuEvenementGUI.fxml"));

            drawer.setSidePane(menu);
            drawer.setDefaultDrawerSize(200);
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
