/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet2020;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import Entities.Etablissement;
import Services.EtablissementService;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.textfield.TextFields;
import projet.controller.backcontroller;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AficcherEtablissementController implements Initializable {

    @FXML
    private AnchorPane a;
    @FXML
    private ScrollPane s1;
    @FXML
    private VBox vbox1;
    @FXML
    private MenuButton choice;
    @FXML
    private MenuItem aff1;
    @FXML
    private JFXTextField recherche;
    @FXML
    private JFXComboBox<?> type;
    @FXML
    private JFXButton Aj;
    List<String> li = new ArrayList<>();
    EtablissementService es=new EtablissementService();
    Etablissement et=new Etablissement();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         List<Etablissement> le = new ArrayList<>();
        try {
            le = es.readAll();
        } catch (SQLException ex) {
            Logger.getLogger(AficcherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Etablissement ett : le){
            Label nom = new Label();
            Label address = new Label();
            Label Numtel= new Label();
            ImageView photo = new ImageView(new Image("file:///C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + ett.getImage()));
            photo.setFitHeight(200);
            photo.setFitWidth(200);
            Text add = new Text("Adresse: ");
            Text nm= new Text("Nom: ");
            Text nt = new Text("Numero telephone: ");
            add.setFill(Color.DARKORANGE);
            nm.setFill(Color.DARKORANGE);
            nt.setFill(Color.DARKORANGE);
            nom.setText(ett.getNom());
            address.setText(ett.getAdresse());
            String v=Integer.toString(ett.getNumTel());
            Numtel.setText(v);
            HBox h1 = new HBox();
            HBox btn = new HBox();
            VBox v1 = new VBox();
            VBox v2 = new VBox();
            VBox rv = new VBox();
            HBox hv1 = new HBox();
            JFXButton bt = new JFXButton("Afficher Détails");
            Button bt2 = new Button("modifier"); 
            Button bt3 = new Button("Supprimer");
            Button sig = new Button("Signaler");
               Button btfb = new Button("Share on Facebook");
            final Separator sep = new Separator();
            vbox1.setSpacing(20);
            vbox1.setStyle("-fx-background-color: green; -fx-text-fill: red;");
             sep.setMaxWidth(Double.MAX_EXPONENT);
            sep.setStyle("-fx-background-color: green; -fx-text-fill: red;");
            h1.setSpacing(10);
            hv1.getChildren().add(nom);
            v2.getChildren().add(add);
            v2.getChildren().add(address);
            h1.getChildren().add(nt);
            h1.getChildren().add(Numtel);
            h1.getChildren().add(photo);
                btn.getChildren().add(bt);
                btn.getChildren().add(bt2);
                btn.getChildren().add(bt3);
                btn.getChildren().add(sig);
                vbox1.getChildren().add(hv1);
                vbox1.getChildren().add(v1);
                vbox1.getChildren().add(v2);
                vbox1.getChildren().add(h1);
                vbox1.getChildren().add(btn);
                vbox1.getChildren().add(sep);
                btn.setAlignment(Pos.CENTER);
                btn.setSpacing(10);
                 nom.setFont(javafx.scene.text.Font.font("Courier", 20));

                nom.setStyle("-fx-text-fill: #20b2aa");
                address.setFont(javafx.scene.text.Font.font("Courier", 15));
                bt3.setStyle("-fx-background-color: red; -fx-text-fill: white;");

                vbox1.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;"
                        + "-fx-border-width: 2;" + "-fx-border-insets: 5;" + "-fx-background-color:#ffffff;"
                        + "-fx-border-radius: 5;" + "-fx-border-color: black;" + "-fx-border-height: 70;");
                bt2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        try {
                            FXMLLoader root = new FXMLLoader(getClass().getResource("ModifierEtablissement.fxml"));
                            AnchorPane x = root.load();
                            ModifierEtablissementController E = root.getController();
                            vbox1.getChildren().setAll(x);
                            E.setEtablissement(ett);
                            E.show();

                        } catch (IOException ex) {
                            Logger.getLogger(ModifierEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                });
                bt3.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {

                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

                            alert.setTitle("Alert!!!!!!!!!!");
                            alert.setHeaderText("etes vous sure de bien vouloir supprimer cette Ecole");
                            Optional<ButtonType> res = alert.showAndWait();

                            if (res.get() == ButtonType.OK) {

                                try {
                                    es.delete(ett.getId());
                                } catch (SQLException ex) {
                                    Logger.getLogger(AficcherEtablissementController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                vbox1.getChildren().remove(hv1);
                                vbox1.getChildren().remove(v1);
                                vbox1.getChildren().remove(h1);
                                vbox1.getChildren().remove(btn);
                                vbox1.getChildren().remove(v2);
                                vbox1.getChildren().remove(sep);
                                Alert alertSec = new Alert(Alert.AlertType.INFORMATION);
                                alertSec.setHeaderText("Ecole suprrimé avec succées");
                                alertSec.showAndWait();

                            }

                        }

                    });
               
            
        }
    }    

    @FXML
    private void aff1(ActionEvent event) {
    }

    @FXML
    private void goToAj(ActionEvent event) throws IOException {
        AnchorPane lp = FXMLLoader.load(getClass().getResource("AjouterEtablissement.fxml"));
        this.a.getChildren().setAll(lp);
    }
    
    
    
    @FXML
    private void AfficherC(ActionEvent event) throws IOException {
       
        
        Stage stage = (Stage) this.a.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherEvenements(ActionEvent event) throws IOException {
        
       
        
         Stage stage = (Stage) this.a.getScene().getWindow();
        URL url = new File("src/projet/views/EvenemnetFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
        @FXML
    private void login(ActionEvent event) throws IOException {
        
        
        
         Stage stage = (Stage) this.a.getScene().getWindow();
        URL url = new File("src/projet/views/LoginGUI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherB(ActionEvent event) throws MalformedURLException, IOException {
        
         Stage stage = (Stage) this.a.getScene().getWindow();
        URL url = new File("src/views/CategorieFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    @FXML
    private void AfficherEtablissement(ActionEvent event) throws MalformedURLException, IOException {
       
        
         Stage stage = (Stage) this.a.getScene().getWindow();
        URL url = new File("src/projet2020/AficcherEtablissement.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
      @FXML
      public void AfficherBlog(ActionEvent event) throws MalformedURLException, IOException{
         Stage stage = (Stage) this.a.getScene().getWindow();
        URL url = new File("src/projet/views/affichageArticlesFrontList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
       
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
