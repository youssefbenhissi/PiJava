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
import org.controlsfx.control.InfoOverlay;
import org.controlsfx.control.PopOver;
import org.controlsfx.control.textfield.TextFields;
import projet.models.CategorieClub;
import projet.models.Club;
import projet.service.CategorieClubService;
import projet.service.ClubService;

public class AfficherCategoriesEtClubs implements Initializable {

    @FXML
    private Label title_filter;

    @FXML
    private JFXComboBox<String> categorie_combo;

    @FXML
    private JFXComboBox<String> tri_combo;

    @FXML
    private VBox content_product;

    private ObservableList<Club> listProduit;

    private HBox row;

    private String ref_combo;

    private String filter;

    private CategorieClubService categorieproduitservice = new CategorieClubService();

    private ClubService ps = new ClubService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        getCategorie();
        tri_combo.getItems().addAll("Nom, A à Z", "Nom, Z à A", "Prix,croissant", "Prix, décroissant");
        listProduit = FXCollections.observableArrayList(ps.getListProduitsFilter(null, null));
        getProduit();
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
                //detailProduit(event, produit);
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

//        getSousCategorie();
        String nombre = "2";
        
//categorie_combo.setValue(nombre);

        filter();

    }

    @FXML
    private void TriEvent(ActionEvent event) {
        filter();
    }
}
