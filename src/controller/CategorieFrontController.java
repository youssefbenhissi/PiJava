/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import java.io.File;
import java.net.MalformedURLException;
import java.util.List;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import projet.controller.world;
import projet.models.Utilisateur;

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
    @FXML
    private VBox home;
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
    @FXML
    private Circle myCircle;
    private Utilisateur user = world.recupererUtilisateurConnecte;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myCircle.setStroke(Color.SEAGREEN);
        Image iiii = new Image("file:" + "C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + user.getImage());
        myCircle.setFill(new ImagePattern(iiii));
        // TODO
        categorieCombobox();
        TrieCom.getItems().clear();
        TrieCom.getItems().addAll("A à Z", "Z à A");
        listLivre = FXCollections.observableArrayList(ps.Tous());
        getLiv();
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

    public void getLivdeux() {

    }

    public void getLiv() {
        home.getChildren().clear();

        int index = 0;
        livre lib;
        HBox hbox = new HBox();
        for (int i = 0; i < listLivre.size(); i++) {
            lib = listLivre.get(i);
            if (index % 3 == 0) {
                hbox = new HBox();
                hbox.getStyleClass().add("row");
                home.getChildren().add(hbox);
            }
            Image image = null;
            try {
                image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + lib.getNom_image()));
            } catch (FileNotFoundException ex) {
            }
            ImageView img = new ImageView(image);
            img.setFitWidth(400);
            img.setFitHeight(250);
            Label label = new Label(lib.getNom());
            HBox detail = new HBox(label);
            detail.getStyleClass().add("detail_btn");

            VBox vb = new VBox(img, detail);

            HBox suppBox = new HBox();
            suppBox.getStyleClass().add("supp_Box");

            Button btn = new Button("", vb);
            btn.setId(Integer.toString(i));
            btn.setOnAction(event -> {
                liv1 = listLivre.get(Integer.parseInt(btn.getId()));
                detailLivre(listLivre.get(Integer.parseInt(btn.getId())));
            });
            hbox.getChildren().add(btn);
            hbox.getChildren().add(suppBox);
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

        loadWindow(getClass().getResource("/views/DetailLivre.fxml"), "DetailLivre", null);

    }

    private void TrieLiv() {

        String category = combo.getValue();
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

        listLivre = FXCollections.observableArrayList(ps.getListProduitsFilter(category, tri));
        getLiv();
    }

    @FXML
    private void Trie(ActionEvent event) {
        TrieLiv();
    }

    @FXML
    private void AfficherC(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.home.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEvenements(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.home.getScene().getWindow();
        URL url = new File("src/projet/views/EvenemnetFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.home.getScene().getWindow();
        URL url = new File("src/projet/views/LoginGUI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherB(ActionEvent event) throws MalformedURLException, IOException {

        Stage stage = (Stage) this.home.getScene().getWindow();
        URL url = new File("src/views/CategorieFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEtablissement(ActionEvent event) throws MalformedURLException, IOException {

        Stage stage = (Stage) this.home.getScene().getWindow();
        URL url = new File("src/projet2020/AficcherEtablissement.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void AfficherBlog(ActionEvent event) throws MalformedURLException, IOException {
        Stage stage = (Stage) this.home.getScene().getWindow();
        URL url = new File("src/projet/views/affichageArticlesFrontList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void profile(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/Profile.fxml"));
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    public void exit(ActionEvent even) throws IOException {
        Stage stage = (Stage) this.home.getScene().getWindow();
        URL url = new File("src/projet/views/LoginGUI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
