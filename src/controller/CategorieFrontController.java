/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller ;

import Service.ServiceCategorier;
import Service.ServiceLivre;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.StageStyle;
import models.categorier;
import models.livre;
import controller.AjouterCatigorieController;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class CategorieFrontController implements Initializable {

    @FXML
    private ComboBox<String> combo;
//    @FXML
//    private ComboBox<String> TrieCom;
      private ResultSet res;
        private PreparedStatement pst = null;
    private ResultSet rs = null;
    private ServiceCategorier ServiceCategorier = new ServiceCategorier();
    @FXML
    private VBox cont;
     private ObservableList<categorier> categorie;
  private int id_categorie;
      private ObservableList<livre> listLivre;
      private ServiceLivre ps = new ServiceLivre();
    @FXML
    private HBox row;
    public static livre liv1;
    @FXML
    private ComboBox<String> TrieCom;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         categorieCombobox();
          TrieCom.getItems().clear();
        TrieCom.getItems().addAll("A à Z", "Z à A");

    }    
 private void categorieCombobox() {
       

        combo.getItems().clear();

        HashMap<String, Integer> mapCategorie = ServiceCategorier.getAllCategorie();
        for (String s : mapCategorie.keySet()) {
            combo.getItems().add(s);

        }
    } 
 private void LivreCategorie() throws SQLException {


        if (combo.getValue() != null) {

            HashMap<String, Integer> mapCategorie = ServiceCategorier.getAllCategorie();

            int id_category = mapCategorie.get(combo.getValue());

            listLivre = FXCollections.observableArrayList(ps.EnsembleDesLivre(id_category));
            
            
          getLiv(); 
           
        }
        

    }
 public void getLiv(){
                   cont.getChildren().clear();


     int index = 0;
     livre lib;
       for (int i=0;i<listLivre.size();i++) {
           lib=listLivre.get(i);
           if (index % 4 == 0) {
                row = new HBox();
                cont.getChildren().add(row);
            }
                       VBox con = new VBox();

                           // System.out.println(lib.getNom_image());

            //VBox content = new VBox();
 Image image = null;
           
                try {

                    image = new Image(new FileInputStream("C:\\wamp64\\www\\img\\"+ lib.getNom_image()));
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(CategorieFrontController.class.getName()).log(Level.SEVERE, null, ex);
                }
          

            ImageView imageView = new ImageView(image);

                  Label title = new Label(lib.getNom());
                

                 imageView.setFitHeight(100);
            imageView.setFitWidth(150);
                 
                 con.getChildren().addAll(title,imageView); 
                  Button item = new Button("", con);
                  item.setId(Integer.toString(i));
           item.setOnAction(event -> {
               liv1=listLivre.get(Integer.parseInt(item.getId()));
                detailLivre(listLivre.get(Integer.parseInt(item.getId())));

            });

            row.getChildren().add(item);

            index++;
                 
                           
       }
       
        
        

    

     
  
 

 }
 

    @FXML
    private void afficher(ActionEvent event) throws SQLException {
        LivreCategorie();
        
    }
public static void loadWindow(URL loc, String title, Stage parentStage) {
        try {
            Parent parent = FXMLLoader.load(loc);
            Stage stage = null;
            
            if (parentStage != null) {
                stage = parentStage;
            } else {
                stage = new Stage(StageStyle.DECORATED);
            }
            stage.setTitle(title);
            stage.setScene(new Scene(parent));
            stage.show();
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
   
  private void detailLivre(livre exp) {
  
loadWindow(getClass().getResource("/views/DetailLivre.fxml"), "DetailLivre",null);

}
   private void TrieLiv() {

        String categorie = combo.getValue();
        String classement = "all";

        String tri = TrieCom.getValue();

        if (TrieCom.getValue() != null) {

            switch (TrieCom.getValue()) {
                case "A à Z":
                    tri = "nom_asc";
                    break;
                case "Z à A":
                    tri = "nom_desc";
                    break;
                default:
                    break;
            }
        }

    listLivre = FXCollections.observableArrayList(ps.getListProduitsFilter(categorie, tri));
       getLiv();
}

    @FXML
    private void Trie(ActionEvent event) {
        TrieLiv();
    }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   

}