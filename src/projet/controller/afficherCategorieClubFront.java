/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.jfoenix.controls.JFXTextField;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;
import projet.models.Club;
import projet.service.ClubService;

/**
 * FXML Controller class
 *
 * @author houba
 */
public class afficherCategorieClubFront implements Initializable {

    @FXML
    private VBox home;
    @FXML
    private JFXTextField rechercheBar;
    private Label nomUtilisateurConnecte;
    @FXML
    private BorderPane borderpane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ClubService clubService = new ClubService();
        List<Club> arrayList = clubService.selectAllClubs();
        int i = 0;
        HBox hbox = new HBox();
        for (Club exp : arrayList) {

            try {
                if (i % 3 == 0) {
                    hbox = new HBox();
                    hbox.getStyleClass().add("row");

                    home.getChildren().add(hbox);
                }
                Image image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + exp.getPath()));
                File imageFile = new File("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + exp.getPath());

                VBox vboxImage = new VBox();
                vboxImage.setPrefHeight(250);
                vboxImage.setPrefWidth(250);
                BackgroundImage myBI = new BackgroundImage(new Image(imageFile.toURI().toString(), 250, 300, false, true),
                        BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
//then you set to your node
                vboxImage.setBackground(new Background(myBI));
                VBox slidingVBOx = new VBox();
                Label TextNom=new Label();
                Label TextDescription=new Label();
                vboxImage.setOnMouseEntered(mouseEvent -> {

                    slidingVBOx.setPrefHeight(250);
                    slidingVBOx.setPrefWidth(250);
                    BackgroundFill myBF = new BackgroundFill(Color.ORANGE, new CornerRadii(1),
                            new Insets(0.0, 0.0, 0.0, 0.0));// or null for the padding
                    slidingVBOx.setBackground(new Background(new BackgroundFill(Color.rgb(212, 175, 127,0.9), CornerRadii.EMPTY, Insets.EMPTY)));
                    //slidingVBOx.setBackground(new Background(myBF));
                    //slidingVBOx.setStyle("-fx-opacity: 0.6");
                    
                    TextNom.setText(exp.getNom());
                    
                    TextDescription.setText(exp.getDescription());
                    slidingVBOx.getChildren().add(TextNom);
                    slidingVBOx.getChildren().add(TextDescription);
                    vboxImage.getChildren().add(slidingVBOx);
                    

                });
                vboxImage.setOnMouseExited(mouseEvent -> {
                    slidingVBOx.getChildren().remove(TextNom);
                    slidingVBOx.getChildren().remove(TextDescription);
                    vboxImage.getChildren().remove(slidingVBOx);

                });
                ImageView img = new ImageView(image);
                img.setFitWidth(250);
                img.setFitHeight(125);
                Label label = new Label(exp.getNom());
                Label label2 = new Label(exp.getDescription());

                HBox detail = new HBox(label, label2);
                detail.getStyleClass().add("detail_btn");
                VBox vb = new VBox(vboxImage, detail);
                Button btn = new Button("", vb);
                hbox.getChildren().add(btn);
                i++;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(afficherCategorieClubFront.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
