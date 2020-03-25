package projet.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.controlsfx.control.PopOver;
import projet.models.CategorieClub;
import projet.models.Club;
import projet.service.CategorieClubService;
import projet.service.ClubService;
import projet.service.NewsLetterService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author JISOO
 */
public class AjouterCategorieEtClubController implements Initializable {

    @FXML
    private AnchorPane layersignup;
    @FXML
    private AnchorPane layer2;
    @FXML
    private JFXButton signin;
    @FXML
    private Label l1;
    @FXML
    private Label l2;
    @FXML
    private Label l3;
    @FXML
    private Label s1;
    @FXML
    private Label s2;
    @FXML
    private Label s3;
    @FXML
    private JFXButton signup;

    @FXML
    private JFXButton btnsignup;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private TextField u1;
    @FXML
    private TextField u3;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField u4;
    @FXML
    private TextField uquestion1;
    @FXML
    private TextField uquestion2;
    @FXML
    private TextField uquestion3;
    @FXML
    private TextField n1;
    @FXML
    private AnchorPane layer1;

    @FXML
    private JFXComboBox<String> categorie;

    @FXML
    private Button btnBrowser;

    private File file = null;

    private Image image;

    private FileChooser fileChooser;
    ClubService ClubService = new ClubService();

    CategorieClubService categoriesClubService = new CategorieClubService();
    private NewsLetterService newsLetter = new NewsLetterService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            // pour le fileChooser
            fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All files", "*.*"),
                    new FileChooser.ExtensionFilter("Images", "*.*"),
                    new FileChooser.ExtensionFilter("Text File", "*.txt*"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Integer> mapCategorie = categoriesClubService.getAllCategorie();
        for (String s : mapCategorie.keySet()) {
            categorie.getItems().add(s);
        }
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        btnsignin.setVisible(false);
        n1.setVisible(false);
        u1.setVisible(true);
        u3.setVisible(true);
        u4.setVisible(true);
        uquestion1.setVisible(true);
        uquestion2.setVisible(true);
        uquestion3.setVisible(true);
        categorie.setVisible(true);

        PopOver popOver1 = categoriesClubService.popNotification("le nom ne doit pas contenir aucun caractére spécial");
        u1.setOnMouseEntered(mouseEvent -> {
            popOver1.show(u1);
        });
        u1.setOnMouseExited(mouseEvent -> {
            popOver1.hide();
        });

        PopOver popOver2 = categoriesClubService.popNotification("la liste deroulante vous aidera à choisir le club");
        categorie.setOnMouseEntered(mouseEvent -> {
            popOver2.show(categorie);
        });
        categorie.setOnMouseExited(mouseEvent -> {
            popOver2.hide();
        });
        PopOver popOver3 = categoriesClubService.popNotification("la capacité doit étre un entier positif");
        u4.setOnMouseEntered(mouseEvent -> {
            popOver3.show(u4);
        });
        u4.setOnMouseExited(mouseEvent -> {
            popOver3.hide();
        });
        PopOver popOver4 = categoriesClubService.popNotification("le nom ne doit pas contenir aucun caractére spécial");
        n1.setOnMouseEntered(mouseEvent -> {
            popOver4.show(n1);
        });
        n1.setOnMouseExited(mouseEvent -> {
            popOver4.hide();
        });
        PopOver popOver5 = categoriesClubService.popNotification("la première question doit contenir caractére spécial");
        uquestion1.setOnMouseEntered(mouseEvent -> {
            popOver5.show(uquestion1);
        });
        uquestion1.setOnMouseExited(mouseEvent -> {
            popOver5.hide();
        });
        PopOver popOver6 = categoriesClubService.popNotification("la deuxième question doit un caractére spécial");
        uquestion2.setOnMouseEntered(mouseEvent -> {
            popOver6.show(uquestion2);
        });
        uquestion2.setOnMouseExited(mouseEvent -> {
            popOver6.hide();
        });
        PopOver popOver7 = categoriesClubService.popNotification("la troisième question doit contenir un caractére spécial");
        uquestion3.setOnMouseEntered(mouseEvent -> {
            popOver7.show(uquestion3);
        });
        uquestion3.setOnMouseExited(mouseEvent -> {
            popOver7.hide();
        });
    }

    @FXML
    private void btn(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(491);
        slide.play();

        layer1.setTranslateX(-309);
        btnsignin.setVisible(true);

        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        u1.setVisible(false);
        u3.setVisible(false);
        u4.setVisible(false);
        uquestion1.setVisible(false);
        uquestion2.setVisible(false);
        uquestion3.setVisible(false);
        categorie.setVisible(false);

        btnBrowser.setVisible(false);
        slide.setOnFinished((e -> {
            HashMap<String, Integer> mapCategorie = categoriesClubService.getAllCategorie();
            for (String s : mapCategorie.keySet()) {
                categorie.getItems().remove(s);
            }
            for (String s : mapCategorie.keySet()) {
                categorie.getItems().add(s);
            }
            u1.setText("");
            u3.setText("");
            u4.setText("");
            uquestion1.setText("");
            uquestion2.setText("");
            uquestion3.setText("");
        }));
    }

    @FXML
    private void btn2(MouseEvent event) {
        TranslateTransition slide = new TranslateTransition();
        slide.setDuration(Duration.seconds(0.7));
        slide.setNode(layer2);

        slide.setToX(0);
        slide.play();

        layer1.setTranslateX(0);
        btnsignin.setVisible(false);

        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        signin.setVisible(true);
        btnsignup.setVisible(true);
        n1.setVisible(false);
        u1.setVisible(true);
        u3.setVisible(true);
        u4.setVisible(true);
        uquestion1.setVisible(true);
        uquestion2.setVisible(true);
        uquestion3.setVisible(true);
        btnBrowser.setVisible(true);
        categorie.setVisible(true);
        slide.setOnFinished((e -> {

            HashMap<String, Integer> mapCategorie = categoriesClubService.getAllCategorie();
            for (String s : mapCategorie.keySet()) {
                categorie.getItems().remove(s);
            }
            for (String s : mapCategorie.keySet()) {
                categorie.getItems().add(s);
            }
            n1.setText("");

        }));
    }

    @FXML
    private void btnsignup(MouseEvent event) {
        String nomClub = u1.getText();
        String capaciteClub = u4.getText();
        if (nomClub.isEmpty()) {
            String tilte = "Champ Vide";
            String message = "tous les champs doivent être remplis";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else if (!ClubService.validationChaineSimpleAvecEspace(nomClub)) {
            String tilte = "Nom Club";
            String message = "le nom du club est non autorisé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else if (!ClubService.validationChaineSimpleNombre(capaciteClub)) {
            String tilte = "Capacite Club";
            String message = "la capacite du club est non autorisé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            String afficheClub;
            if (file == null) {
                afficheClub = "";
            } else {
                afficheClub = replaceFile(file.getAbsolutePath());
            }
            String extension = afficheClub.substring(afficheClub.lastIndexOf("."), afficheClub.length());
            if (extension.equals(".jpg") || extension.equals(".png")) {
                HashMap<String, Integer> mapCategorie = categoriesClubService.getAllCategorie();
                int id_Categorie = mapCategorie.get(categorie.getValue());
                int capacite = Integer.parseInt(capaciteClub);
                Club c = new Club();
                c.setNom(nomClub);
                c.setDescription(u3.getText());
                c.setQuestionPr(uquestion1.getText());
                c.setQuestionDe(uquestion2.getText());
                c.setQuestionTr(uquestion3.getText());
                c.setNbrFoisLike(0);
                c.setNbrLike(0);
                c.setCategorie_id(id_Categorie);
                c.setMoyenneLike(0);
                c.setCapacite(capacite);
                c.setPath(afficheClub);
                ClubService.ajouterCategorie(c);
                String tilte = "Ajout validé";
                String message = "votre club est bien ajoute";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.SUCCESS);
                tray.showAndDismiss(Duration.millis(3000));
                List<String> listeEmail = newsLetter.retournerListeEmails();
                for (String i : listeEmail) {
                    newsLetter.sendMail("youssef.benhissi@esprit.tn","ilovetennis", i, "nouveau club", c.getNom()+" "+c.getDescription());
                }
            } else {
                String tilte = "image uploadé";
                String message = "le fichier doit être de type jpg ou png";
                TrayNotification tray = new TrayNotification();
                AnimationType type = AnimationType.POPUP;

                tray.setAnimationType(type);
                tray.setTitle(tilte);
                tray.setMessage(message);
                tray.setNotificationType(NotificationType.ERROR);
                tray.showAndDismiss(Duration.millis(3000));
            }
        }
    }

    @FXML
    private void sign(MouseEvent event) {

    }

    @FXML
    private void click(ActionEvent event) {
        String nomCategorie = n1.getText();

        if (nomCategorie.isEmpty()) {
            String tilte = "Champ Vide";
            String message = "tous les champs doivent être remplis";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else if (!categoriesClubService.validationChaineSimpleSansEspace(nomCategorie)) {
            String tilte = "Nom Catgeorie";
            String message = "le nom de la catégorie est non autorisé";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;

            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        } else {
            CategorieClub c = new CategorieClub();
            c.setNomCategorie(nomCategorie);
            categoriesClubService.ajouterCategorie(c);
            String tilte = "Ajout validé";
            String message = n1.getText();
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
    private void handleBrowser(ActionEvent event) {

        Stage stage = (Stage) layer2.getScene().getWindow();
        file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            //System.out.println("" + file.getAbsolutePath());
            try {
                image = new Image(file.getAbsoluteFile().toURI().toString(), imageView.getFitWidth(),
                        imageView.getFitHeight(), true, true);
                imageView.setImage(image);
                imageView.setPreserveRatio(true);
            } catch (Exception e) {
                System.out.println("lenna");
            }

        }
    }

    private String generateFileName() {

        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 5) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return "club_" + saltStr;
    }

    private String replaceFile(String file) {

        String extension = file.substring(file.lastIndexOf("."), file.length());
        //System.out.println(extension);

        String filename = generateFileName() + extension;

        Path sourceDirectory = Paths.get(file);
        Path targetDirectory = Paths.get("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + filename);

        try {
            //copy source to target using Files Class
            Files.copy(sourceDirectory, targetDirectory);
        } catch (IOException ex) {
            //Logger.getLogger(AjouterEvenementController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ahawa");
        }

        return filename;
    }

    @FXML
    private void quitter() {
        // get a handle to the stage
        Stage stage = (Stage) layer1.getScene().getWindow();
        // do what you have to do
        stage.close();
    }
}
