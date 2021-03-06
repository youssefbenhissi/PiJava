/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import projet.models.Evenement;
import projet.models.Utilisateur;
import projet.service.EvenementService;

/**
 * FXML Controller class
 *
 * @author houba
 */
public class EvenementFrontController implements Initializable {

    @FXML
    private BorderPane borderpane;
    @FXML
    private VBox home;
    private Utilisateur user = world.recupererUtilisateurConnecte;
    @FXML
    private Circle myCircle;

    //int id = WorldfriendshipController.recupererUtilisateurConnecte.getId_Utilisateur();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        myCircle.setStroke(Color.SEAGREEN);
        Image iiii = new Image("file:" + "C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + user.getImage());
        myCircle.setFill(new ImagePattern(iiii));
        afficher();
//        System.out.println(user.getEmail());
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
        Stage stage = (Stage) this.borderpane.getScene().getWindow();
        URL url = new File("src/projet/views/LoginGUI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
    private void afficher() {

        EvenementService evenService = new EvenementService();
        List<Evenement> arrayList = evenService.selectAllEvenement();
        int i = 0;
        HBox hbox = new HBox();
        for (Evenement exp : arrayList) {

            if (i % 3 == 0) {
                hbox = new HBox();
                hbox.getStyleClass().add("row");
                home.getChildren().add(hbox);
            }
            Image image = null;
            try {
                image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + exp.getImageEvenement()));
            } catch (FileNotFoundException ex) {
                Logger.getLogger(EvenementFrontController.class.getName()).log(Level.SEVERE, null, ex);
            }
            ImageView img = new ImageView(image);
            img.setFitWidth(400);
            img.setFitHeight(250);
            Label label = new Label(exp.getNomEvenement());
            String pattern = "MM/dd/yyyy HH:mm:ss";
            DateFormat df = new SimpleDateFormat(pattern);
            Date today = exp.getDateEvenement();
            String todayAsString = df.format(today);
            Label label2 = new Label(todayAsString);
            HBox detail = new HBox(label, label2);
            detail.getStyleClass().add("detail_btn");

            VBox vb = new VBox(img, detail);

            HBox suppBox = new HBox();
            suppBox.getStyleClass().add("supp_Box");

            Button btn = new Button("", vb);
            btn.setOnAction(event -> {
                detailExperience(exp);
            });
            hbox.getChildren().add(btn);
            hbox.getChildren().add(suppBox);
            i++;

        }
    }

    private void detailExperience(Evenement exp) {
        try {

            FXMLLoader Loader = new FXMLLoader();
            Loader.setLocation(getClass().getResource("/projet/views/DetailExperiencee.fxml"));
            Parent p = Loader.load();

            DetailExperienceeController display = Loader.getController();
            display.setExperience(exp);
            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(p);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.show();
        } catch (IOException ex) {
            //Logger.getLogger(HomeController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void AfficherC(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.borderpane.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEvenements(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.borderpane.getScene().getWindow();
        URL url = new File("src/projet/views/EvenemnetFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.borderpane.getScene().getWindow();
        URL url = new File("src/projet/views/LoginGUI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherB(ActionEvent event) throws MalformedURLException, IOException {

        Stage stage = (Stage) this.borderpane.getScene().getWindow();
        URL url = new File("src/views/CategorieFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEtablissement(ActionEvent event) throws MalformedURLException, IOException {

        Stage stage = (Stage) this.borderpane.getScene().getWindow();
        URL url = new File("src/projet2020/AficcherEtablissement.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void AfficherBlog(ActionEvent event) throws MalformedURLException, IOException {
        Stage stage = (Stage) this.borderpane.getScene().getWindow();
        URL url = new File("src/projet/views/affichageArticlesFrontList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
