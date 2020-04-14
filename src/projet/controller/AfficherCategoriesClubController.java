/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.events.JFXDialogEvent;
//import com.jfoenix.controls.JFXTextField;
import java.util.Timer;
import com.sun.speech.freetts.*;
import controller.HomeeController;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import projet.models.CategorieClub;
import projet.models.Club;
import projet.service.CategorieClubService;
import projet.service.ClubService;
import projet.service.InscriptionService;
import projet.service.NewsLetterService;
import t2s.son.LecteurTexte;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class AfficherCategoriesClubController implements Initializable {

    @FXML
    private AnchorPane panel;
    @FXML
    private TableColumn<?, ?> id;
    private NewsLetterService newsLetter = new NewsLetterService();
    @FXML
    private StackPane afficherTsEvenementStackPane;
    @FXML
    private HBox compteur;
    @FXML
    private TableColumn<?, ?> nomCategorie;
    @FXML
    private TableColumn<?, ?> idClub;
    @FXML
    private TableColumn<?, ?> nomClub;
    @FXML
    private TableColumn<?, ?> descriptionClub;
    @FXML
    private TableColumn<?, ?> capaciteClub;
    @FXML
    private TableColumn<?, ?> nomCat;
    @FXML
    private TableColumn<?, ?> moyenneLike;
    @FXML
    private TableView<CategorieClub> listeCategorie;
    @FXML
    private TableView<Club> listeClubs;
    @FXML
    private Label countCategorie;
    @FXML
    private Label countClubs;
    @FXML
    private Label countInscrisp;
    @FXML
    private Button Clubs;

    @FXML
    private JFXTextField rechercheBar;
    /* @FXML
    private JFXTextField search;*/

    public static final String VOICE_ALAN = "alan";
    public static final String VOICE_KEVIN = "kevin";
    public static final String VOICE_KEVIN16 = "kevin16";
    CategorieClubService categoriesClubService = new CategorieClubService();
    public static final String VOICE = "kevin16";

    int counter = 0;
    int counterCat = 0;
    int counterIns = 0;
    Boolean isIt = false;
    ClubService ClubService = new ClubService();

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        compteurCategorie();
        compteurClub();
        compteurInscription();
        Timer timer = new Timer(); //new timer
        CategorieClubService cs = new CategorieClubService();
        ClubService cc = new ClubService();
        //cs.ajouterCategorie(p);
        List<CategorieClub> myList = categoriesClubService.selectAll();
        ObservableList<CategorieClub> myObservableList = FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
        listeCategorie.setEditable(true);
        myList.forEach(e -> {
            myObservableList.add(e);
            listeCategorie.setItems(myObservableList);
        });
        afficherClub();
    }

    @FXML
    public void modifierCategorie(TableColumn.CellEditEvent<CategorieClub, String> e) {
        CategorieClub c = listeCategorie.getSelectionModel().getSelectedItem();
        c.setNomCategorie(e.getNewValue());
    }

    public void compteurClub() {
        Timer timer = new Timer();
        counter = 0;
        ClubService cc = new ClubService();
        countClubs.setText(String.valueOf(0));
        if (cc.selectAllClubs().size() == 0) {
            countClubs.setText(String.valueOf(0));
        } else {
            Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                counter++;
                countClubs.setText(String.valueOf(counter));
                int nbrClub = cc.selectAllClubs().size();
                if (counter == nbrClub) {

                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timeline.setCycleCount(cc.selectAllClubs().size());
            timeline.play();
        }
    }

    public void changeLastNameCellEvent(TableColumn.CellEditEvent edittedCell) {
        CategorieClub personSelected = listeCategorie.getSelectionModel().getSelectedItem();
        personSelected.setNomCategorie(edittedCell.getNewValue().toString());
    }

    public void compteurCategorie() {
        Timer timer = new Timer();
        CategorieClubService cs = new CategorieClubService();
        if (cs.selectAll().size() == 0) {
            countCategorie.setText(String.valueOf(0));
        } else {
            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                counterCat++;
                countCategorie.setText(String.valueOf(counterCat));
                int nbrClub = cs.selectAll().size();
                if (counterCat == nbrClub) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(cs.selectAll().size());
            timelineCat.play();
        }
    }

    public void compteurInscription() {
        Timer timer = new Timer();
        InscriptionService cs = new InscriptionService();
        if (cs.selectAllInscris().size() == 0) {
            countInscrisp.setText(String.valueOf(0));
        } else {
            Timeline timelineCat = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
                counterIns++;
                countInscrisp.setText(String.valueOf(counterIns));
                int nbrClub = cs.selectAllInscris().size();
                if (counterCat == nbrClub) {
                } else if (isIt) {
                    timer.cancel();
                    isIt = false;
                }
            }));
            timelineCat.setCycleCount(cs.selectAllInscris().size());
            timelineCat.play();
        }
    }

    @FXML
    private void convertirEnPdf(ActionEvent event) {
        Alert a2 = new Alert(Alert.AlertType.CONFIRMATION);
        a2.setTitle("Conversion PDF !");
        a2.setContentText("PDF telecharge avec succés!");
        a2.show();
        Club clubSelect = listeClubs.getSelectionModel().getSelectedItem();
        pdf(clubSelect.getNom(), clubSelect.getNomcategorie(), clubSelect.getDescription(), clubSelect.getCapacite(), clubSelect.getMoyenneLike());
    }

    @FXML
    private void lireDescriptionEvenement(ActionEvent event) {
        try {
            Club clubSelect = listeClubs.getSelectionModel().getSelectedItem();
            LecteurTexte lecteur = new LecteurTexte(clubSelect.getDescription());
            lecteur.playAll();
        } catch (java.lang.RuntimeException e) {
            Alert a1 = new Alert(Alert.AlertType.WARNING);
            a1.setTitle("Selction obligatoire");
            a1.setContentText("Vous devez selectionner un club afin d'ecouter sa description");
            Optional<ButtonType> result = a1.showAndWait();
        }
    }

    public void pdf(String nomClub, String NomCategorie, String Description, int capacite, float moyenne) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\youssef\\Desktop\\" + nomClub + ".pdf"));
            document.open();

            Font f = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 24, Font.UNDERLINE));
            f.setColor(0, 153, 255);

            Font f2 = new Font(FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD));
            f2.setColor(0, 0, 0);

            Paragraph p1 = new Paragraph("Club  " + nomClub + ":", f);
            p1.setAlignment(Paragraph.ALIGN_CENTER);

            Paragraph p12 = new Paragraph("    ");
            Paragraph p13 = new Paragraph("     ");
            Paragraph p14 = new Paragraph("     ");

            Paragraph p15 = new Paragraph("     ");
            Paragraph p16 = new Paragraph("     ");
            Paragraph p17 = new Paragraph("     ");
            Paragraph p2 = new Paragraph("La categorie dont il appartient : ");
            p2.add(NomCategorie);

            Paragraph p3 = new Paragraph("Description du club : ");
            p3.add(Description);
            Paragraph p4 = new Paragraph("Capacite :   ");
            String capaciteString = Integer.toString(capacite);
            p4.add(capaciteString);
            Paragraph p5 = new Paragraph("La moyenne du club :  ");
            String moyenneString = Float.toString(moyenne);
            p5.add(moyenneString);

            document.add(p1);

            document.add(p12);
            document.add(p13);
            document.add(p14);

            document.add(p2);

            document.add(p15);
            document.add(p15);
            document.add(p15);

            document.add(p3);

            document.add(p16);
            document.add(p16);
            document.add(p16);

            document.add(p4);

            document.add(p17);
            document.add(p17);
            document.add(p17);

            document.add(p5);

        } catch (Exception e) {
            System.out.println(e);
        }
        document.close();
    }

    @FXML
    private void edit() {
        System.out.println("nkjkjnk");
    }

    @FXML
    private void afficherCategorieClub() {
        //compteurCategorie();
        //compteurClub();
        List<CategorieClub> myList = categoriesClubService.selectAll();
        ObservableList<CategorieClub> myObservableList = FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));

        listeCategorie.setEditable(true);
        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
        myList.forEach(e -> {
            myObservableList.add(e);
            listeCategorie.setItems(myObservableList);
        });
    }

    @FXML
    private void afficherClub() {

        List<Club> myList = ClubService.selectAllClubs();
        ObservableList<Club> myObservableList = FXCollections.observableArrayList();
        idClub.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomClub.setCellValueFactory(new PropertyValueFactory<>("nom"));
        descriptionClub.setCellValueFactory(new PropertyValueFactory<>("description"));
        capaciteClub.setCellValueFactory(new PropertyValueFactory<>("capacite"));
        moyenneLike.setCellValueFactory(new PropertyValueFactory<>("moyenneLike"));
        nomCat.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
        myList.forEach(e -> {
            myObservableList.add(e);
            listeClubs.setItems(myObservableList);
        });
    }

    private int index() {
        int selectedItem = listeCategorie.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = listeCategorie.getSelectionModel().getSelectedIndex();
        // System.out.println(selectedItem);
        return selectedItem;
    }

    @FXML
    private void supprimerCategorieClub(ActionEvent event) {
        try {

            int x = index();
            CategorieClub cat = listeCategorie.getSelectionModel().getSelectedItem();
            Alert a1 = new Alert(Alert.AlertType.WARNING);
            a1.setWidth(750);
            a1.setHeight(400);
            a1.setTitle("Supprimer categorie");
            String message = "Attention vous allez supprimer ainsi les clubs suivants: \n";
            List<Club> listeClubsASuprrimer = ClubService.retournerListeDesClubsSupprission(cat.getId());
            for (Club c : listeClubsASuprrimer) {
                message = message + c.getNom() + "\n";
            }
            a1.setContentText(message);
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == ButtonType.OK) {
                categoriesClubService.supprimer(x);
                Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                a2.setTitle("Supprimer categorie");
                a2.setContentText("Categorie supprimé avec succés!");
                a2.show();

                listeCategorie.getItems().clear();
                listeCategorie.getItems().addAll(categoriesClubService.selectAll());

            } else {
                a1.close();
            }
        } catch (Exception e) {
        }
    }

    private int indexClub() {
        int selectedItem = listeClubs.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = listeClubs.getSelectionModel().getSelectedIndex();
        //  System.out.println(selectedItem);
        return selectedItem;
    }

    @FXML
    private void supprimerClub(ActionEvent event) {
        try {

            int x = indexClub();
            Alert a1 = new Alert(Alert.AlertType.WARNING);
            a1.setTitle("Supprimer club");
            a1.setContentText("Vous voulez vraiment supprimer ce club ?");
            Optional<ButtonType> result = a1.showAndWait();
            if (result.get() == ButtonType.OK) {
                List<Integer> idUsers = ClubService.selectAllIdSupprimer(x);
                for (int id : idUsers) {
                    String email = ClubService.retournerEmailUtilisateur(id);
                    newsLetter.sendMail("youssef.benhissi@esprit.tn", "ilovetennis", email, "Suppression club", "on a supprimé le club " + listeClubs.getSelectionModel().getSelectedItem().getNom());
                }
                ClubService.supprimerClub(x);
                Alert a2 = new Alert(Alert.AlertType.INFORMATION);
                a2.setTitle("Supprimer Club");
                a2.setContentText("Club supprimé avec succés!");
                a2.show();

                listeClubs.getItems().clear();
                listeClubs.getItems().addAll(ClubService.selectAllClubs());

            } else {
                a1.close();
            }
        } catch (Exception e) {
        }
    }
/*
    @FXML
    private void AfficherC(ActionEvent event) {
        BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/afficherCategorieClubback.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategoriesClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border_pane.setCenter(root);
    }
*/
    @FXML
    public void ajouterEvenementGUI(ActionEvent even) throws IOException {
        Stage stage = (Stage) panel.getScene().getWindow();
        // do what you have to do
        stage.close();
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterCategorieEtClub.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    public void divers(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/divers.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    public void Satitiques(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/StatistiquesCategorieEtClub.fxml"));
        Scene scene = new Scene(root);
        //scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void rechercher(KeyEvent event) {

        if (!rechercheBar.getText().isEmpty()) {
            listeCategorie.setVisible(true);
            List<CategorieClub> myList = categoriesClubService.rechercheCategories(rechercheBar.getText());
            ObservableList<CategorieClub> observableList = FXCollections.observableArrayList();

            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));
            myList.forEach(e -> {

                observableList.add(e);
                // System.out.println(observableList);
            });
            listeCategorie.setItems(observableList);
        } else {
            if (rechercheBar.getText().isEmpty()) {
                listeCategorie.getItems().clear();
                listeCategorie.getItems().addAll(categoriesClubService.selectAll());
            }

        }
    }

    @FXML
    private void modifierCategorieAction(ActionEvent event) {
        CategorieClub categorieselect = listeCategorie.getSelectionModel().getSelectedItem();
        if (categorieselect == null) {
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            JFXButton button = new JFXButton("OKAY");
            button.getStyleClass().add("dialog-button");
            JFXDialog dialog = new JFXDialog(afficherTsEvenementStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
            dialogLayout.setHeading(new Label("Modification de categorie"));
            dialogLayout.setBody(new Label("il faut selectionner une categorie"));
            dialogLayout.setActions(button);
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
                afficherTsEvenementStackPane.setEffect(null);
            });
            return;
        }
        try {
            //pour que les champs soient rempli à la place de  // Parent root = FXMLLoader.load(getClass().getResource("AjouterEvenementGUI.fxml"));
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ModifierEvenementGUI.fxml"));
            Parent root = loader.load();
            ModifierCategorieClubController controller = (ModifierCategorieClubController) loader.getController();
            controller.id_evenement = categorieselect.getId();
            controller.nom_categorie_fx.setText(categorieselect.getNomCategorie());
            Stage primaryStage = new Stage();

            primaryStage.setTitle("Modifier Evenement");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/projet/style/EvenementCss.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setMaxWidth(670);
            primaryStage.setMaxHeight(750);
            primaryStage.setMinWidth(570);
            primaryStage.setMinHeight(688);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    @FXML
    private void modifierClubAction(ActionEvent event) {
        Club clubselect = listeClubs.getSelectionModel().getSelectedItem();
        if (clubselect == null) {
            JFXDialogLayout dialogLayout = new JFXDialogLayout();
            JFXButton button = new JFXButton("OKAY");
            button.getStyleClass().add("dialog-button");
            JFXDialog dialog = new JFXDialog(afficherTsEvenementStackPane, dialogLayout, JFXDialog.DialogTransition.TOP);
            button.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
            dialogLayout.setHeading(new Label("Modification de club"));
            dialogLayout.setBody(new Label("il faut selectionner un club"));
            dialogLayout.setActions(button);
            dialog.show();
            dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
                afficherTsEvenementStackPane.setEffect(null);
            });
            return;
        }
        try {
            Club clubselec = listeClubs.getSelectionModel().getSelectedItem();
            System.out.println(clubselec);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ModifierClubGUI.fxml"));
            Parent root = loader.load();
            ModifierClubController controller = (ModifierClubController) loader.getController();
            controller.id_evenement = clubselect.getId();
            controller.nom_club_fx.setText(clubselec.getNom());
            controller.capacite_club_fx.setText(Integer.toString(clubselec.getCapacite()));
            controller.premiere_question_fx.setText(clubselec.getQuestionPr());
            controller.deuxieme_question_fx.setText(clubselec.getQuestionDe());
            controller.troisieme_question_fx.setText(clubselec.getQuestionTr());
            controller.nom_club_fx.setText(clubselec.getNom());
            controller.description_club_fx.setText(clubselec.getDescription());
            controller.nomAfficher = clubselec.getPath();
            controller.categorie.setValue(clubselec.getNomcategorie());
            controller.image = new Image("file:///C:/Users/youssef/PhpstormProjects/pidevFinal/web/assets/images/" + clubselec.getPath(), controller.imageView.getFitWidth(),
                    controller.imageView.getFitHeight(), true, true);
            controller.imageView.setImage(controller.image);
            controller.imageView.setPreserveRatio(true);
            System.out.println(clubselec.getCategorie_id());

            Stage primaryStage = new Stage();

            primaryStage.setTitle("Modifier Evenement");
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("/projet/style/EvenementCss.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setMaxWidth(670);
            primaryStage.setMaxHeight(750);
            primaryStage.setMinWidth(570);
            primaryStage.setMinHeight(688);
            primaryStage.show();

        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    
    
    
    
    
     @FXML
    private void AfficherClubs(ActionEvent event) {
        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/afficherCategorieClubback.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void Afficherpersonnel(ActionEvent event) {

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/affichageBackPersonnel.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
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
            root = FXMLLoader.load(getClass().getResource("/projet/views/EvenementBack.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
     @FXML
    private void login(ActionEvent event) {

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/projet/views/LoginGUI.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }
    
    @FXML
    private void AfficherC(ActionEvent event) {
       Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/views/affiche.fxml"));
        } catch (IOException ex) {
            Logger.getLogger(backcontroller.class.getName()).log(Level.SEVERE, null, ex);
        }
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.show();
    }

    @FXML
    private void AfficherBlogs(ActionEvent event) {

        Button btn = (Button) event.getSource();
        Stage stage = (Stage) btn.getScene().getWindow();
        stage.close();
        Stage primaryStage = new Stage();
        javafx.scene.Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/gestion_blog/views/Home.fxml"));
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
