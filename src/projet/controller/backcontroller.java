/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.models.CategorieClub;
import projet.models.Eleve;
import projet.models.Parent;
import projet.models.Personnel;
import projet.service.EleveService;
import projet.service.ParentService;
import projet.service.PersonnelService;

/**
 *
 * @author user
 */
public class backcontroller implements Initializable {

    EleveService EleveService = new EleveService();
    ParentService parentService = new ParentService();
    PersonnelService personnelService = new PersonnelService();
    @FXML

    private TableColumn<?, ?> nomEleve;
    @FXML
    private TableColumn<?, ?> actionCategprie;
    @FXML
    private TableColumn<?, ?> actionParent;
    @FXML
    private TableColumn<?, ?> actionPer;
    @FXML
    private JFXTextField rechercheBar;
    @FXML
    private TableColumn<?, ?> prenomEleve;
    @FXML
    private TableColumn<?, ?> sexeEleve;
    @FXML
    private TableColumn<?, ?> imageEleve;
    @FXML
    private TableColumn<?, ?> AgeEleve;
    @FXML
    private TableColumn<?, ?> emailEleve;
    @FXML
    public TableView<Eleve> listeEleve;
    @FXML
    private TableView<Parent> listeParent;
    @FXML
    private TableView<Personnel> listePersonnel;

    @FXML
    private TableColumn<?, ?> nomParent;
    @FXML
    private TableColumn<?, ?> prenomParent;
    @FXML
    private TableColumn<?, ?> numTelephoneParent;
    @FXML
    private TableColumn<?, ?> imageParent;
    @FXML
    private TableColumn<?, ?> nomPersonnel;
    @FXML
    private TableColumn<?, ?> prenomPersonnel;
    @FXML
    private TableColumn<?, ?> soldeCongePersonnel;
    @FXML
    private TableColumn<?, ?> Date_debutTravailPersonnel;
    public static ObservableList<Eleve> myObservableList;
    public static ObservableList<Parent> myObservableListP;
    public static ObservableList<Personnel> myObservableListPer;

    @FXML
    private TableColumn<?, ?> typePersonnel;
    @FXML
    private TableColumn<?, ?> descriptionPersonnel;
    @FXML
    private TableColumn<?, ?> imagePersonnel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        afficherEleve();
        afficherParents();
        afficherPersonnel();
    }

    @FXML
    private void afficherEleve() {
        EleveService = new EleveService();

        List<Eleve> myList = EleveService.selectAllEleve();
        myObservableList = FXCollections.observableArrayList();

        nomEleve.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomEleve.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeEleve.setCellValueFactory(new PropertyValueFactory<>("Sexe"));
        // imageEleve.setCellValueFactory(new PropertyValueFactory<>("image"));
        AgeEleve.setCellValueFactory(new PropertyValueFactory<>("Age"));
        emailEleve.setCellValueFactory(new PropertyValueFactory<>("Email"));
        actionCategprie.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));
        myList.forEach(e -> {
            myObservableList.add(e);
            listeEleve.setItems(myObservableList);
        });

    }

    @FXML
    private void afficherParents() {
        ParentService ParentSe = new ParentService();
        List<Parent> myList = ParentSe.selectAllParent();
        myObservableListP = FXCollections.observableArrayList();

        nomParent.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomParent.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        numTelephoneParent.setCellValueFactory(new PropertyValueFactory<>("Sexe"));
        actionParent.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));
        // imageParent.setCellValueFactory(new PropertyValueFactory<>("image"));

        myList.forEach(e -> {
            myObservableListP.add(e);
            listeParent.setItems(myObservableListP);
        });
    }

    @FXML
    private void afficherPersonnel() {

        PersonnelService PersonnelSe = new PersonnelService();
        List<Personnel> myList = PersonnelSe.selectAllPersonnel();
        myObservableListPer = FXCollections.observableArrayList();
        nomPersonnel.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomPersonnel.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        soldeCongePersonnel.setCellValueFactory(new PropertyValueFactory<>("soldeConge"));
        Date_debutTravailPersonnel.setCellValueFactory(new PropertyValueFactory<>("Date_debutTravail"));
        typePersonnel.setCellValueFactory(new PropertyValueFactory<>("type"));
        descriptionPersonnel.setCellValueFactory(new PropertyValueFactory<>("description"));
        actionPer.setCellValueFactory(new PropertyValueFactory<>("btn_delete"));
        //     imagePersonnel.setCellValueFactory(new PropertyValueFactory<>("image"));
        myList.forEach(e -> {
            myObservableListPer.add(e);
            listePersonnel.setItems(myObservableListPer);
        });

    }

    @FXML
    private void AjouterEleve(ActionEvent event) {
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterEleve.fxml"));
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
    private void AjouterParent(ActionEvent event) {
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterParent.fxml"));
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
    private void AjouterPersonnel(ActionEvent event) {
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterPersonnel.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    

    //Menuuuuuuuuuu
    //Menuuuuuuuuuu
   
    
     @FXML
    private void AfficherClubs(ActionEvent event) {
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
    
    @FXML
    private void AfficherC(ActionEvent event) {
       Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/affiche.fxml"));
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
    private void AfficherBlogs(ActionEvent event) {

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gestion_blog/views/Home.fxml"));
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
