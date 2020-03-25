package projet.controller;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDatePicker;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.InfoOverlay;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;
import projet.models.CategorieClub;
import projet.models.Club;
import projet.service.CategorieClubService;
import projet.service.ClubService;
import projet.service.NewsLetterService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AfficherCategoriesEtClubs implements Initializable {

    @FXML
    private Label title_filter;
    @FXML
    private TextField emailField;
    @FXML
    private Button inscription;
    @FXML
    private HBox meilleursProduit;
    @FXML
    private JFXComboBox<String> categorie_combo;

    @FXML
    private JFXComboBox<String> tri_combo;

    @FXML
    private VBox content_product;

    private ObservableList<Club> listProduit;

    private HBox row;

    private String ref_combo;
    @FXML
    private HBox box;
    @FXML
    private ImageView present_img;
    private String filter;

    private CategorieClubService categorieproduitservice = new CategorieClubService();

    private ClubService ps = new ClubService();
    private NewsLetterService newsLetter = new NewsLetterService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        getCategorie();
        tri_combo.getItems().addAll("Nom, A à Z", "Nom, Z à A", "Prix,croissant", "Prix, décroissant");
        listProduit = FXCollections.observableArrayList(ps.getListProduitsFilter(null, null));
        getProduit();
        try {
            setMeilleurProduct();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherCategoriesEtClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
        List<String> listeImages = ps.retournerListeImages();
        for (String i : listeImages) {
            Button button = new Button();
            String SatGrande = "file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + i;
            String stat = "-fx-background-image: url('file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + i + "\')";
            button.setStyle(stat);
            button.setPrefHeight(100);
            button.setPrefWidth(106);
            button.getStyleClass().add("btn-image");
            button.setOnMouseClicked(e -> {
                File imgFile = new File(SatGrande);
                present_img.setImage(new Image(SatGrande));
            });
            box.getChildren().add(button);
        }
    }

    private void filter() {

        String categorie = categorie_combo.getValue();
        String classement = "all";

        String tri = tri_combo.getValue();

        if (tri_combo.getValue() != null) {

            switch (tri_combo.getValue()) {
                case "Nom, A à Z":
                    tri = "nom_asc";
                    break;
                case "Nom, Z à A":
                    tri = "nom_desc";
                    break;
                case "Prix,croissant":
                    tri = "etoi_asc";
                    break;
                case "Prix, décroissant":
                    tri = "etoi_desc";
                    break;
                default:
                    break;
            }
        }

        listProduit = FXCollections.observableArrayList(ps.getListProduitsFilter(categorie, tri));
        getProduit();
    }

    private void getProduit() {

        content_product.getChildren().clear();

        int index = 0;

        for (Club produit : listProduit) {
            if (index % 5 == 0) {
                row = new HBox();
                row.getStyleClass().add("content-item");
                content_product.getChildren().add(row);
            }

            VBox content = new VBox();

            Image image = null;
            try {
                //System.out.println(produit.getPath());

                image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + produit.getPath()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(AfficherCategoriesEtClubs.class.getName()).log(Level.SEVERE, null, ex);
            }

            ImageView imageView = new ImageView(image);

            Label title = new Label(produit.getNom());
            title.getStyleClass().add("title_prod");

            title.setStyle("-fx-font-weight: bold");
            Label prix = new Label(produit.getDescription());
            imageView.setFitHeight(255);
            imageView.setFitWidth(246);

            content.getChildren().addAll(imageView, title, prix);
            Button item = new Button("", content);
            item.setOnAction(event -> {
                detailExperience(produit);
            });

            row.getChildren().add(item);

            index++;
        }

    }

    private void getCategorie() {
        /* HashMap<String, Integer> mapCategorie = categoriesClubService.getAllCategorie();
        for (String s : mapCategorie.keySet()) {
            categorie.getItems().add(s);
        }*/

        categorie_combo.getItems().clear();

        HashMap<String, Integer> mapCategorie = categorieproduitservice.getAllCategorie();
        for (String s : mapCategorie.keySet()) {
            categorie_combo.getItems().add(s);

        }
    }

    @FXML
    private void reinitialiser(MouseEvent event) {

        tri_combo.getItems().clear();
        tri_combo.getItems().addAll("Nom, A à Z", "Nom, Z à A", "Prix,croissant", "Prix, décroissant");

        getCategorie();

        title_filter.setText(filter);

    }

    @FXML
    private void CagetorieEvent(ActionEvent event) {

        getSousCategorie();
        String nombre = "2";

//categorie_combo.setValue(nombre);
        //filter();
    }

    private void getSousCategorie() {

        if (categorie_combo.getValue() != null) {

            HashMap<String, Integer> mapCategorie = categorieproduitservice.getAllCategorie();

            int id_Categorie = mapCategorie.get(categorie_combo.getValue());

            System.out.println(id_Categorie);
            listProduit = FXCollections.observableArrayList(ps.retournerListeDesClubsSupprission(id_Categorie));
            getProduit();
        }
    }

    @FXML
    private void TriEvent(ActionEvent event
    ) {
        filter();
    }

    //top3
    private void setMeilleurProduct() throws FileNotFoundException {

        listProduit = FXCollections.observableArrayList(ps.getListProduitsFilter(null, "etoi_desc"));

        int index = 0;

        for (Club produit : listProduit) {
            System.out.println(produit);
            VBox content = new VBox();
            Image image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + produit.getPath()));
            ImageView imageView = new ImageView(image);
            Label title = new Label(produit.getDescription());
            title.getStyleClass().add("title_prod");
            Label prix = new Label(produit.getNomcategorie());
            imageView.setFitHeight(255);
            imageView.setFitWidth(246);
            content.getChildren().addAll(imageView, title, prix);
            Button item = new Button("", content);
            item.setOnAction(event -> {
                //detailProduit(event, produit);
            });
            meilleursProduit.getChildren().add(item);

            if (index == 2) {
                break;
            }

            index++;
        }
    }

    //Detail 
    private void detailExperience(Club exp) {
        try {

            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/projet/views/DetailClub.fxml"));
            Parent p = Loader.load();

            DetailClubController display = Loader.getController();
            display.setClub(exp);

            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(p);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.show();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategoriesEtClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void EnregitrerEmail() {
        
        if (!ClubService.validationEmail(emailField.getText())) {
            String tilte = "Email";
            String message = "votre Email n'est correcte";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            newsLetter.ajouterEmail(emailField.getText());
            String tilte = "Merci pour votre Confiance";
            String message = "votre email a été bien enregistré.";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }
    }
}
