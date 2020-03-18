/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Month;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXTextField;
//import com.jfoenix.controls.JFXTextField;
import java.util.Timer;
import com.sun.speech.freetts.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.Duration;
import javafx.util.converter.NumberStringConverter;
import javax.swing.JFormattedTextField;
import projet.models.CategorieClub;
import projet.models.Club;
import projet.service.CategorieClubService;
import projet.service.ClubService;
import t2s.son.LecteurTexte;

/**
 * FXML Controller class
 *
 * @author youssef
 */
public class AfficherCategoriesClubController implements Initializable {

    @FXML
    private TableColumn<?, ?> id;
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
        //pdf("lobna", "youssef", "desciption", 12,2);
        //LecteurTexte lecteur = new LecteurTexte("club de foot");
        //lecteur.playAll();
        //lecteur.setTexte("je suis un synthétiseur vocal, qui êtes-vous?");
        //lecteur.playAll();
        compteurCategorie();
        compteurClub();
        //CategorieClub p =new CategorieClub();
        //p.setNomCategorie("mustapha");
        
        Timer timer = new Timer(); //new timer
        CategorieClubService cs = new CategorieClubService();
        ClubService cc = new ClubService();
        //cs.ajouterCategorie(p);
        List<CategorieClub> myList = categoriesClubService.selectAll();
        ObservableList<CategorieClub> myObservableList = FXCollections.observableArrayList();
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCategorie.setCellValueFactory(new PropertyValueFactory<>("nomCategorie"));

        listeCategorie.setEditable(true);
        //nomCategorie.setCellFactory(TextFieldTableCell.forTableColumn());
        //recupererUtilisateurConnecte
        //nomCategorie.setCellValueFactory(TextFieldTableCell.forTableColumn());
        myList.forEach(e -> {
            myObservableList.add(e);
            listeCategorie.setItems(myObservableList);
        });
        afficherClub();
    }
    
    @FXML
    public void modifierCategorie(TableColumn.CellEditEvent<CategorieClub,String> e){
        CategorieClub c=listeCategorie.getSelectionModel().getSelectedItem();
        c.setNomCategorie(e.getNewValue());
    }
    public void compteurClub() {
        Timer timer = new Timer(); //new timer
        counter = 0; //setting the counter to 10 sec
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
     public void changeLastNameCellEvent(TableColumn.CellEditEvent edittedCell)
    {
        CategorieClub personSelected =  listeCategorie.getSelectionModel().getSelectedItem();
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
            Voice talk;
            VoiceManager vm = VoiceManager.getInstance();
            System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
            talk = vm.getVoice(VOICE_KEVIN16);
            talk.allocate();
            talk.speak(clubSelect.getDescription());
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
            // PdfWriter.getInstance(document, new FileOutputStream(nomClub + ".pdf"));
            PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\youssef\\Desktop\\libYoussef\\" + nomClub + ".pdf"));
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
        System.out.println(selectedItem);
        return selectedItem;
    }

    @FXML
    private void supprimerCategorieClub(ActionEvent event) {
        int x = index();
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("Supprimer categorie");
        a1.setContentText("Vous voulez vraiment supprimer cette categorie ?");
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
    }

    private int indexClub() {
        int selectedItem = listeClubs.getSelectionModel().getSelectedItem().getId();
        int selectedIndex = listeClubs.getSelectionModel().getSelectedIndex();
        System.out.println(selectedItem);
        return selectedItem;
    }

    @FXML
    private void supprimerClub(ActionEvent event) {
        int x = indexClub();
        Alert a1 = new Alert(Alert.AlertType.WARNING);
        a1.setTitle("Supprimer club");
        a1.setContentText("Vous voulez vraiment supprimer ce club ?");
        Optional<ButtonType> result = a1.showAndWait();
        if (result.get() == ButtonType.OK) {
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
    }

    @FXML
    private void AfficherC(ActionEvent event) {
        BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/afficherCategoriesClub.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AfficherCategoriesClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border_pane.setCenter(root);
    }

    @FXML
    public void ajouterEvenementGUI(ActionEvent even) throws IOException {
        Stage primaryStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/projet/views/AjouterCategorieEtClub.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
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

}