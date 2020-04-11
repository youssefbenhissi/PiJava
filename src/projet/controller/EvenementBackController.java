/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javafx.application.ConditionalFeature.FXML;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.models.CategorieClub;
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
    private TableColumn<?, ?> actionEvenement;
    @FXML
    private TableColumn<?, ?> actionCategprie;
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
    private JFXTextField rechercheBar;
    @FXML
    private TableView<CategorieEvenement> listeCategorie;
    @FXML
    private TableColumn<?, ?> nomCategorie;
    CategorieEvenementService serviceCategorie = new CategorieEvenementService();
    @FXML
    private TableView<Evenement> listeEvenement;
    EvenementService serviceEvenement = new EvenementService();
    public static ObservableList<Evenement> myObservableList;
    public static ObservableList<CategorieEvenement> ObservableList;
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
        ObservableList = FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("idCtegorieEvenement"));
        descriptionCat.setCellValueFactory(new PropertyValueFactory<>("descriptionCat"));
        nomCategorieEvenement.setCellValueFactory(new PropertyValueFactory<>("nomCategorieEvenement"));
        actionCategprie.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));

        myList.forEach(e -> {
            ObservableList.add(e);
            listeCategorie.setItems(ObservableList);
        });
        listeCategorie.setOnMouseClicked((MouseEvent event) -> {
            CategorieEvenement categorie = listeCategorie.getSelectionModel().getSelectedItem();
            try {
                modifierDialog(categorie);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EvenementBackController.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

    }

    private void modifierDialog(CategorieEvenement categorie) throws FileNotFoundException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ModifierCategorie.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

        ModifierCategorieEvenementController controller = loader.<ModifierCategorieEvenementController>getController();

        controller.setData(categorie);

        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.show();

    }

    void afficherEvenement() {
        List<Evenement> myList = serviceEvenement.selectAllEvenement();
        myObservableList = FXCollections.observableArrayList();
        idEvenement.setCellValueFactory(new PropertyValueFactory<>("idEvenement"));
        nomEvenement.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
        capaciteEvenement.setCellValueFactory(new PropertyValueFactory<>("capaciteEvenement"));
        descriptionEvenement.setCellValueFactory(new PropertyValueFactory<>("descriptionEvenement"));
        prixEvenement.setCellValueFactory(new PropertyValueFactory<>("prixEvenement"));
        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
        dateEvenement.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
        actionEvenement.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));

        myList.forEach(e -> {
            myObservableList.add(e);
            listeEvenement.setItems(myObservableList);
        });
        listeEvenement.setOnMouseClicked((MouseEvent event) -> {
            Evenement categorie = listeEvenement.getSelectionModel().getSelectedItem();
            modifierDialogEvenement(categorie);

        });
    }

    private void modifierDialogEvenement(Evenement ev) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ModifierEvenement.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            //Logger.getLogger(GestionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }

        //ModifierEvenementController controller = loader.<ModifierEvenementController>getController();
        ModifierEvenementController controller = loader.<ModifierEvenementController>getController();
        try {
            controller.setData(ev);
        } catch (FileNotFoundException ex) {
            //Logger.getLogger(GestionCategorieController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Dialog dialog = new Dialog();
        dialog.getDialogPane().setContent(root);
        dialog.initStyle(StageStyle.UNDECORATED);
        dialog.show();

    }

    void drawer() {
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

    @FXML
    public void ajouterCategorieEvenementGUI(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterCategorieEvenement.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void rechercher(KeyEvent event) {
        if (!rechercheBar.getText().isEmpty()) {
            listeEvenement.setVisible(true);
            List<Evenement> myList = serviceEvenement.rechercheCategories(rechercheBar.getText());
            ObservableList<Evenement> observableList = FXCollections.observableArrayList();

            idEvenement.setCellValueFactory(new PropertyValueFactory<>("idEvenement"));
            nomEvenement.setCellValueFactory(new PropertyValueFactory<>("nomEvenement"));
            capaciteEvenement.setCellValueFactory(new PropertyValueFactory<>("capaciteEvenement"));
            descriptionEvenement.setCellValueFactory(new PropertyValueFactory<>("descriptionEvenement"));
            prixEvenement.setCellValueFactory(new PropertyValueFactory<>("prixEvenement"));
            nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
            dateEvenement.setCellValueFactory(new PropertyValueFactory<>("dateEvenement"));
            actionEvenement.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));

            myList.forEach(e -> {

                observableList.add(e);
                // System.out.println(observableList);
            });
            listeEvenement.setItems(myObservableList);
        } else {
            if (rechercheBar.getText().isEmpty()) {
                listeEvenement.getItems().clear();
                listeEvenement.getItems().addAll(serviceEvenement.selectAllEvenement());
            }

        }
    }
    
    
    
    
    
    
    
    
     //Menuuuuuuuuuu
    @FXML
    private void AfficherC(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/afficherCategorieClubback.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    @FXML
    private void Afficherpersonnel(ActionEvent event) {
        
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/affichageBackPersonnel.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    @FXML
    private void AfficherEvenements(ActionEvent event) {
        
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/EvenementBack.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    @FXML
    private void login(ActionEvent event) {
        
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/LoginGUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
}
