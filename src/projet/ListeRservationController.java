/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet;

import Service.ServiceLivre;
import Service.ServiceReservation;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Reservation;
import models.categorier;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class ListeRservationController implements Initializable {
         @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation,String> id_r;
    @FXML
    private TableColumn<Reservation,String> id_rl;
    @FXML
    private TableColumn<Reservation,String> nomlivre;
     ObservableList<Reservation> oblist = FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceReservation annonceService = new ServiceReservation();
        oblist = FXCollections.observableArrayList(annonceService.getAll());

        ObservableList observableList = FXCollections.observableArrayList(oblist);
        table.setItems(observableList);
        id_r.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomlivre.setCellValueFactory(new PropertyValueFactory<>("nom"));
                id_rl.setCellValueFactory(new PropertyValueFactory<>("id_livre"));

      
        
   
        
        
        // TODO
    }    
    
     public void setCellValue(){
        
        
     }
     private void accepter(ActionEvent event) {
         table.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Reservation p1=table.getItems().get(table.getSelectionModel().getSelectedIndex());
                p1.getId();
                
            // Reservation a = new Reservation(p1.getId());
                
            }
        }
        
        );
     }
    
}
