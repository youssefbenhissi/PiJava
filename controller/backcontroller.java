/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

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
    ParentService parentService =new ParentService();
    PersonnelService personnelService=new PersonnelService();
    @FXML

    private TableColumn<?, ?> nomEleve;
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
    private TableView<Eleve> listeEleve;
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
        ObservableList<Eleve> myObservableList = FXCollections.observableArrayList();

        nomEleve.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomEleve.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        sexeEleve.setCellValueFactory(new PropertyValueFactory<>("Sexe"));
        imageEleve.setCellValueFactory(new PropertyValueFactory<>("image"));
        AgeEleve.setCellValueFactory(new PropertyValueFactory<>("Age"));
        emailEleve.setCellValueFactory(new PropertyValueFactory<>("Email"));
        myList.forEach(e -> {
            myObservableList.add(e);
            listeEleve.setItems(myObservableList);
        });

    }
     @FXML
    private void afficherParents() {
         ParentService  ParentSe= new ParentService();
          List<Parent> myList = ParentSe.selectAllParent();
        ObservableList<Parent> myObservableList = FXCollections.observableArrayList();

        nomParent.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomParent.setCellValueFactory(new PropertyValueFactory<>("prenom"));
       numTelephoneParent.setCellValueFactory(new PropertyValueFactory<>("Sexe"));
        imageParent.setCellValueFactory(new PropertyValueFactory<>("image"));
        
        myList.forEach(e -> {
            myObservableList.add(e);
            listeParent.setItems(myObservableList);
        });
    }
    @FXML
    private void afficherPersonnel() {
    
        PersonnelService PersonnelSe= new PersonnelService();
        List<Personnel> myList =PersonnelSe.selectAllPersonnel();
        ObservableList<Personnel> myObservableList = FXCollections.observableArrayList();
    nomPersonnel.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomPersonnel.setCellValueFactory(new PropertyValueFactory<>("prenom"));
      soldeCongePersonnel.setCellValueFactory(new PropertyValueFactory<>("soldeConge"));
        Date_debutTravailPersonnel.setCellValueFactory(new PropertyValueFactory<>("Date_debutTravail"));
       typePersonnel.setCellValueFactory(new PropertyValueFactory<>("type"));
       descriptionPersonnel.setCellValueFactory(new PropertyValueFactory<>("description"));
            imagePersonnel.setCellValueFactory(new PropertyValueFactory<>("image"));
        myList.forEach(e -> {
            myObservableList.add(e);
            listePersonnel.setItems(myObservableList);
        });
    
    }
    
}
