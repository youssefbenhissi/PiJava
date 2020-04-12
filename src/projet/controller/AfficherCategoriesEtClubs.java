package projet.controller;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import org.controlsfx.control.InfoOverlay;
import projet.models.Club;
import projet.service.CategorieClubService;
import projet.service.ClubService;
import projet.service.NewsLetterService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

public class AfficherCategoriesEtClubs implements Initializable {
public static AnchorPane contnt = null;
    @FXML
    private JFXHamburger hamburger;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label title_filter;
    @FXML
    private Text nomP;
    @FXML
    private Text nomD;
    @FXML
    private Text nomT;
    @FXML
    private Text capaciteP;
    @FXML
    private Text capaciteD;
    @FXML
    private Text capaciteT;
    @FXML
    private Text descriptionP;
    @FXML
    private Text descriptionD;
    @FXML
    private Text descriptionT;
    @FXML
    private Text categorieP;
    @FXML
    private Text categorieD;
    @FXML
    private Text categorieT;
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
    public int idUtilistaeur;
    private String ref_combo;
    @FXML
    private HBox muhbox;
    @FXML
    private HBox box;
    @FXML
    private ImageView present_img;
    @FXML
    private ImageView imageP;
    @FXML
    private ImageView imageD;
    @FXML
    private ImageView imageT;
    private String filter;
    private InfoOverlay infooverlay;
    private CategorieClubService categorieproduitservice = new CategorieClubService();
    private int c;
    private ClubService ps = new ClubService();
    private NewsLetterService newsLetter = new NewsLetterService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        getCategorie();
        tri_combo.getItems().addAll("Nom, A à Z", "Nom, Z à A", "Moyenne Croissante", "Moyenne Decroissante");
        listProduit = FXCollections.observableArrayList(ps.getListProduitsFilter(null, null));
        getProduit();
        try {
            setMeilleurProduct();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(AfficherCategoriesEtClubs.class.getName()).log(Level.SEVERE, null, ex);
        }
      present_img.setFitHeight(300);
present_img.setFitWidth(300);
       reinitialiserAletoire();
        List<Club> listeImages = ps.retournerListeImages();
        for (Club i : listeImages) {
            String info = "Bienvenue chez :" + i.getNom()
                    + "\nIl appartient a la categorie : " + i.getNomcategorie()
                    + "\nSa description est :" + i.getDescription()
                    + "\nIl a une capacite de : " + i.getCapacite()
                    + "\nSa moyenne de Like est : " + i.getMoyenneLike()
                    + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
            String SatGrande = "file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + i.getPath();
            present_img.setImage(new Image(SatGrande));
            present_img.setFitHeight(395);
            present_img.setFitWidth(381);
            infooverlay = new InfoOverlay(present_img, info);
            muhbox.getChildren().add(infooverlay);
            break;
        }
        for (Club i : listeImages) {
            Button button = new Button();
            String SatGrand = "file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + i.getPath();
            String stat = "-fx-background-image: url('file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + i.getPath() + "');  -fx-background-size: 100px 106px; ";
            button.setStyle(stat);

            button.setPrefHeight(100);
            button.setPrefWidth(106);
            button.getStyleClass().add("btn-image");
            button.setOnMouseClicked(e -> {
                muhbox.getChildren().remove(infooverlay);

                String info = "Bienvenue chez :" + i.getNom()
                        + "\nIl appartient a la categorie : " + i.getNomcategorie()
                        + "\nSa description est :" + i.getDescription()
                        + "\nIl a une capacite de : " + i.getCapacite()
                        + "\nSa moyenne de Like est : " + i.getMoyenneLike()
                        + "\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n";
                File im = new File(SatGrand);
                present_img.setImage(new Image(SatGrand));

                present_img.setFitHeight(395);
                present_img.setFitWidth(381);
                infooverlay = new InfoOverlay(present_img, info);
                muhbox.getChildren().remove(present_img);

                muhbox.getChildren().add(infooverlay);
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
                case "Moyenne Croissante":
                    tri = "etoi_asc";
                    break;
                case "Moyenne Decroissante":
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
                Logger.getLogger(AfficherCategoriesEtClubs.class
                        .getName()).log(Level.SEVERE, null, ex);
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
        tri_combo.getItems().addAll("Nom, A à Z", "Nom, Z à A", "Moyenne Croissante", "Moyenne Decroissante");

        getCategorie();

        title_filter.setText(filter);

    }

    @FXML
    private void reinitialiserAletoire() {
      List<Club> listeIma = ps.selectAllClubsAleatoire();

        String SatG = "file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + listeIma.get(0).getPath();
        File imgFile = new File(SatG);
        imageP.setImage(new Image(SatG));
        nomP.setText(listeIma.get(0).getNom());
        descriptionP.setText("\t\t\t\t" + listeIma.get(0).getDescription());
        capaciteP.setText("capacite: "+Integer.toString(listeIma.get(0).getCapacite()));
        categorieP.setText(listeIma.get(0).getNomcategorie());
        String Sat = "file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + listeIma.get(1).getPath();
        File imgFil = new File(Sat);
        imageD.setImage(new Image(Sat));
        nomD.setText(listeIma.get(1).getNom());
        descriptionD.setText("\t\t\t\t" + listeIma.get(1).getDescription());
        capaciteD.setText("capacite: "+Integer.toString(listeIma.get(1).getCapacite()));
        categorieD.setText(listeIma.get(1).getNomcategorie());

        String Sa = "file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + listeIma.get(2).getPath();
        File imgFi = new File(Sa);
        imageT.setImage(new Image(Sa));
        nomT.setText(listeIma.get(2).getNom());
        descriptionT.setText("\t\t\t\t" + listeIma.get(2).getDescription());
        capaciteT.setText("capacite: "+Integer.toString(listeIma.get(2).getCapacite()));
        categorieT.setText(listeIma.get(2).getNomcategorie());

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
            VBox content = new VBox();
            Image image = new Image(new FileInputStream("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + produit.getPath()));
            ImageView imageView = new ImageView(image);
            Label title = new Label(produit.getNom());
            title.getStyleClass().add("title_prod");
            Label prix = new Label(produit.getNomcategorie());
            imageView.setFitHeight(255);
            imageView.setFitWidth(246);
            content.getChildren().addAll(imageView, title, prix);
            Button item = new Button("", content);
            item.setOnAction(event -> {
                detailExperience(produit);
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
            System.out.println("houni"+exp.getQuestionPr());
            display.setClub(exp);

            Dialog dialog = new Dialog();
            dialog.getDialogPane().setContent(p);
            dialog.initStyle(StageStyle.UNDECORATED);
            dialog.show();

        } catch (IOException ex) {
            Logger.getLogger(AfficherCategoriesEtClubs.class
                    .getName()).log(Level.SEVERE, null, ex);
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

    @FXML
    private void SeDesabonner() {

        newsLetter.desabonner(emailField.getText());
        String tilte = "Nous sommes tristes";
        String message = "votre email a été bien supprimé.";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }
    
    
    private void initDrawer() throws IOException {
         VBox menu = FXMLLoader.load(getClass().getResource("/projet/views/MenuEvenementGUI.fxml"));

            drawer.setSidePane(menu);
            drawer.setDefaultDrawerSize(200);
            HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
            transition.setRate(-1);
            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
                transition.setRate(transition.getRate() * -1);
                transition.play();

                if (drawer.isShown()) {
                    drawer.close();
                } else {
                    drawer.open();
                }
            });
    }

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @FXML
    private void AfficherC(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/afficherCategorieClubFront.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    @FXML
    private void AfficherEvenements(ActionEvent event) {
        
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/EvenemnetFront.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    
}
