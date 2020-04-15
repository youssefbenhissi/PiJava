/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import projet.models.Utilisateur;
import projet.service.ParentService;

/**
 * FXML Controller class
 *
 * @author Damdoum
 */
public class GestionUtilisateurBackController implements Initializable {

    
    
    @FXML
    private JFXButton BtnAjouter;
    @FXML
    private JFXTextField NomField;
    @FXML
    private JFXTextField PrenomField;
    @FXML
    private JFXTextField EmailField;
    @FXML
    private JFXTextField TelephoneField;
    @FXML
    private JFXTextField UsernameField;
     @FXML
    private JFXTextField SearchField;
    
    
    @FXML
    private TableView<Utilisateur> TabParent;
    
    FilteredList<Utilisateur> filteredDataParent ;
    
      ObservableList<Utilisateur> ParentData =FXCollections.observableArrayList();
      
    @FXML
    private ImageView ParentPicture;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        CheckEmptyField(UsernameField);
        CheckEmptyField(NomField);
        CheckEmptyField(PrenomField);
        CheckEmptyField(EmailField);
        CheckEmptyField(TelephoneField);
        
        
        
        
        TableColumn<Utilisateur,Integer> columnParentNom =new TableColumn("Nom");
        TableColumn<Utilisateur,Integer> columnParentPrenom =new TableColumn("Prenom");
        TableColumn<Utilisateur,Integer> columnParentEmail =new TableColumn("Email");
        TableColumn<Utilisateur,Integer> columnParentTelephone =new TableColumn("Telephone");

        columnParentNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        columnParentPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        columnParentEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        columnParentTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
     
        columnParentNom.prefWidthProperty().bind(TabParent.widthProperty().divide(4));
        columnParentPrenom.prefWidthProperty().bind(TabParent.widthProperty().divide(4));
        columnParentEmail.prefWidthProperty().bind(TabParent.widthProperty().divide(4));
        columnParentTelephone.prefWidthProperty().bind(TabParent.widthProperty().divide(4));
        
        TabParent.getColumns().addAll(columnParentNom,columnParentPrenom,columnParentEmail,columnParentTelephone);
          ParentService ps = new ParentService();
         ParentData.addAll(ps.selectAllParent());
        
         
          filteredDataParent=new FilteredList<>(ParentData,e -> true);
        TabParent.setItems(filteredDataParent);
        
        
        
    }    
    
    
    
    @FXML
    private void ajouterParent(ActionEvent event) {
       
        if (UsernameField.validate() && NomField.validate() && PrenomField.validate() && EmailField.validate() && TelephoneField.validate() )
            
        {
        
        
             Utilisateur p=new Utilisateur();
            p.setNom_Utilisateur(UsernameField.getText());
            p.setEmail(EmailField.getText());
            p.setTelephone(Integer.parseInt(TelephoneField.getText()));
            p.setNom(NomField.getText());
            p.setPrenom(PrenomField.getText());
            p.setImage("C:/xampp/htdocs/image/default.jpg");
            
            ParentService ps = new ParentService();
            ps.ajouterParent(p);
           
         
         ParentService parents = new ParentService();
         ParentData=parents.selectAllParent();
         
           filteredDataParent=new FilteredList<>(ParentData,e -> true);
         TabParent.setItems(ParentData);
       UsernameField.setText("");
       PrenomField.setText("");
       NomField.setText("");
       TelephoneField.setText("");
        EmailField.setText("");
   
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText("Success");
            alert.setContentText("Parent Ajouté !");
            alert.showAndWait();
              try {
                sendMail.sendMail(p.getEmail(),p);
            } catch (Exception ex) {
                Logger.getLogger(GestionUtilisateurBackController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return;
        
        }
        
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("une erreur est survenu");
            alert.showAndWait();
            return;

        }
         
       /* 
        if (NomField.getText().equals("")) {
            errorsNom.setText("Nom field is required");
        }
        if (PrenomField.getText().equals("")) {
            errorsNom.setText("email field is required");
        }
        if (EmailField.getText().equals("")) {
            errorPrenom.setText("Prenom field is required");
        }
        if (TelephoneField.getText().equals("") && Integer.parseInt(numTelephoneP.getText()) > 8) {
            errorNumTelephone.setText("numTelephone field is required");
          if (file == null) {
            errorsImage.setText("Image field is required");
        } else {
            String afficherParent;
            if (file == null) {
                afficherParent = "";
            } else {
                afficherParent = replaceFile(file.getAbsolutePath());
            }
            Utilisateur p=new Utilisateur();
            p.setNom_Utilisateur(nomP.getText()+" "+prenomP.getText());
            p.setEmail(emailP.getText());
            p.setTelephone(Integer.parseInt(numTelephoneP.getText()));
            p.setImage(afficherParent);
            
              ;
            

    
    
    
          }}*/}
    
    public void CheckEmptyField(JFXTextField t)
    {
    
    RequiredFieldValidator validator = new RequiredFieldValidator() ;
         t.getValidators().add(validator);
         validator.setMessage("Ce Champ est obligatoire");
         t.focusedProperty().addListener(new ChangeListener<Boolean>
         () {
             @Override
             public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                 if (!newValue)
                 {
                  t.validate();
                 }
             }
         });
    
    }
    
    
    
     @FXML
    private void research(KeyEvent event) {
        
        
        
          SearchField.textProperty().addListener((observableValue,oldValue,newValue) ->{
        filteredDataParent.setPredicate((Predicate<? super Utilisateur>) (Utilisateur parent)->{
        if(newValue == null || newValue.isEmpty())
        {return true ;}
        String lowerCaseFilter =newValue.toLowerCase();
        if(parent.getNom().toLowerCase().contains(lowerCaseFilter))
        {return true;}
        else if(parent.getPrenom().toLowerCase().contains(lowerCaseFilter))
        {return true;}
         else if(Integer.toString(parent.getTelephone()).toLowerCase().contains(lowerCaseFilter))
        {return true;}
         else if(parent.getEmail().toString().toLowerCase().contains(lowerCaseFilter))
        {return true;}
        return false ;
    });
    });
         SortedList<Utilisateur> sortedData = new SortedList<>(filteredDataParent);
         sortedData.comparatorProperty().bind(TabParent.comparatorProperty());
         TabParent.setItems(sortedData);
        
        
        
    }
    
    
    @FXML
    private void showPicture(MouseEvent event) {
        
         Utilisateur user  = TabParent.getSelectionModel().getSelectedItem();
         Image img = new Image("file:"+user.getImage());
         ParentPicture.setImage(img);
  
         
         
        
        
        
        
    }
    
    
    @FXML
    private void DeleteParent(ActionEvent event) {
        
         Utilisateur user  = TabParent.getSelectionModel().getSelectedItem();
        ParentService ps = new ParentService();
        System.out.println(user);
        ps.supprimerParent(user);
          ParentData=ps.selectAllParent();
         
           filteredDataParent=new FilteredList<>(ParentData,e -> true);
         TabParent.setItems(ParentData);
         
         
        
        
        
        
    }
      
    
}
