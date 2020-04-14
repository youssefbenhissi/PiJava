/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import static projet.controller.AjoutArticle.html2text;
import projet.models.Articles;
import projet.models.Categories;
import projet.models.Tags;
import projet.service.GestionArticles;
import projet.service.GestionCategories;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import projet.models.Commentaires;

/**
 *
 * @author geek alaa
 */
public class AffichageArticleSingleFront implements Initializable {

    @FXML
    private ImageView likebtn;

    @FXML
    private ImageView Image;
    @FXML
    private Label Titre;
    @FXML
    private Label Categorie;
    @FXML
    private Label nbrlike;

    @FXML
    private Label avertissement;
    @FXML
    private Text contenu;
    @FXML
    private VBox PNLCatList = null;

    @FXML
    private HBox Hboxtags = null;

    @FXML
    private VBox PNLcommentaire = null;

    @FXML
    private Button Ajoutbtn;

    @FXML
    private TextArea commentairetextarea;

    private String commcontenu;

    private boolean commerror = true;

    private List<Tags> listagss = new ArrayList<Tags>();

    private String email_user = "youssef.benhissi@esprit.tn"; /////////////////////////////// USER MAIL
    private int id;
    private boolean liked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        commentairetextarea.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(newValue);

            if (newValue.trim().isEmpty()) {

                commentairetextarea.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                commentairetextarea.clear();
                this.Ajoutbtn.setVisible(false);
                // this.avertissement.setText("tu ne peux utiliser ces mots : esprit,ESPRIT et 3a15");
            } else if (newValue.length() < 10) {
                commentairetextarea.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                this.Ajoutbtn.setVisible(false);
                this.avertissement.setText("Minimum 10 caracteres !");
            } else if (newValue.length() > 300) {
                this.avertissement.setText("Maximum 300 caracteres !");
                this.Ajoutbtn.setVisible(false);
                commentairetextarea.setStyle("-fx-border-color :#ff4242;");
            } else if (newValue.contains("esprit") || newValue.contains("ESPRIT") || newValue.contains("3a15")) {
                this.Ajoutbtn.setVisible(false);
                this.avertissement.setText("tu ne peux utiliser ces mots : esprit,ESPRIT et 3a15");

            } else {
                this.Ajoutbtn.setVisible(true);
                this.avertissement.setText("");
                this.commerror = false;
                this.commcontenu = newValue;

                commentairetextarea.setStyle("-fx-background-radius: 1em ;");
            }

        });

        GestionCategories gstcat1 = new GestionCategories();

        List<Categories> listcategorie = new ArrayList<Categories>();
        listcategorie = gstcat1.getcatlist();
        Node[] nodes;
        // System.out.println(listcategorie.size());

        nodes = new Node[listcategorie.size()];

        for (int i = 0; i < listcategorie.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ItemcatListFront.fxml"));

                nodes[i] = loader.load();

                //Get controller of scene2
                AffichageCatListFront Affich;
                Affich = loader.getController();

                Affich.SetCatTitle(listcategorie.get(i).getNom());
                Affich.SetCatID(listcategorie.get(i).getId());

                int j = i;
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #d4d2d2;-fx-background-radius: 1em;");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #ebebeb;-fx-background-radius: 1em;");
                });

                PNLCatList.getChildren().add(nodes[i]);
            } catch (IOException ex) {
                Logger.getLogger(AffichageArticleListFrontGlobal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void SetImage(javafx.scene.image.Image articleimage1) throws IOException {
        this.Image.setImage(articleimage1);
        this.loadCommentaires(this.id);

        GestionArticles gstarti = new GestionArticles();
        List<String> listlikes = gstarti.GetEmailLikes(this.id);
        System.out.println(this.id);
        if (listlikes.contains(this.email_user)) {
            this.liked = true;
            File file = new File("src\\projet\\images\\like_PNG14_enabled.png");
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.likebtn.setImage(image);
        } else {
            this.liked = false;
            File file = new File("src\\projet\\images\\like_PNG14_disabled.png");
            BufferedImage bufferedImage = ImageIO.read(file);
            WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
            this.likebtn.setImage(image);
        }
    }

    public void SetTitle(String Title) {
        this.Titre.setText(Title);
    }

    public void SetArticleId(int id) {
        this.id = id;
    }

    public void SetCateg(String cat) {
        this.Categorie.setText("Catégorie : " + cat);
    }

    public void SetContenu(String contenu) {
        this.contenu.setText(contenu);
        GestionArticles gstar = new GestionArticles();
        List<Articles> listarti = gstar.getArticles();
        Articles article = new Articles(this.id);
        int index = listarti.indexOf(article);
        Articles artiAffich = listarti.get(index);
        int vuesnbr = artiAffich.getVues() + 1;
        List<String> listLikes = gstar.GetEmailLikes(this.id);
        this.nbrlike.setText("J'aime :" + Integer.toString(listLikes.size()));
        gstar.UpdateVues(vuesnbr, this.id);
    }

    public void retouraccu() throws MalformedURLException, IOException {
        Stage stage = (Stage) Image.getScene().getWindow();
        URL url = new File("src/projet/views/affichageArticlesFrontList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
        stage.setTitle("Blog");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    public void SetTags(List<Tags> listags) {
        this.listagss = listags;

        Node[] nodes;
        // System.out.println(listagss.size());

        nodes = new Node[listagss.size()];

        for (int i = 0; i < listagss.size(); i++) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ItemtagsFront.fxml"));

                nodes[i] = loader.load();

                //Get controller of scene2
                AffichagetaglistFront Affich;
                Affich = loader.getController();

                Affich.SetTagTitle(listags.get(i).getNom());
                Affich.SetTagID(listags.get(i).getId());

                int j = i;
                nodes[i].setOnMouseEntered(event -> {
                    nodes[j].setStyle("-fx-background-color : #d4d2d2;-fx-background-radius: 1em;");
                });
                nodes[i].setOnMouseExited(event -> {
                    nodes[j].setStyle("-fx-background-color : #ebebeb;-fx-background-radius: 1em;");
                });

                Hboxtags.getChildren().add(nodes[i]);
            } catch (IOException ex) {
                Logger.getLogger(AffichageArticleListFrontGlobal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void hundleLike() throws IOException {
        if (this.liked) {
            GestionArticles gstartic = new GestionArticles();
            gstartic.DeleteLike(this.email_user, this.id);

            Stage stage = (Stage) likebtn.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/affichageArticleSingle.fxml"));
            Parent root = loader.load();
            AffichageArticleSingleFront Affich;

            Affich = loader.getController();
            GestionArticles gstart = new GestionArticles();
            List<Articles> listarti = gstart.getArticles();
            Articles article = new Articles(this.id);
            int index = listarti.indexOf(article);
            Articles artiAffich = listarti.get(index);
            Affich.SetTitle(artiAffich.getTitre()); // titre
            Affich.SetArticleId(listarti.get(index).getId());
            GestionCategories gstcat = new GestionCategories();
            gstcat.getcatlist();
            List<Categories> listcat = gstcat.getcatlist();
            Categories categorie = new Categories(listarti.get(index).getCat_id());
            Affich.SetCateg(listcat.get(listcat.indexOf(categorie)).getNom()); // categorie
            Affich.SetContenu(html2text(listarti.get(index).getContenu()));

            String path = "http://127.0.0.1/www/PIJAVA/web/assets/images/" + listarti.get(index).getImage();

            javafx.scene.image.Image img = new javafx.scene.image.Image(path);
            Affich.SetImage(img);
            Affich.SetTags(listarti.get(index).getListags());
            stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
            stage.setTitle("Blog");
            Scene scene = new Scene(root);
            stage.setScene(scene);
        } else {
            GestionArticles gstartic = new GestionArticles();
            gstartic.addLike(this.email_user, this.id);

            Stage stage = (Stage) likebtn.getScene().getWindow();

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/affichageArticleSingle.fxml"));
            Parent root = loader.load();
            AffichageArticleSingleFront Affich;

            Affich = loader.getController();
            GestionArticles gstart = new GestionArticles();
            List<Articles> listarti = gstart.getArticles();
            Articles article = new Articles(this.id);
            int index = listarti.indexOf(article);
            Articles artiAffich = listarti.get(index);
            Affich.SetTitle(artiAffich.getTitre()); // titre
            Affich.SetArticleId(listarti.get(index).getId());
            GestionCategories gstcat = new GestionCategories();
            gstcat.getcatlist();
            List<Categories> listcat = gstcat.getcatlist();
            Categories categorie = new Categories(listarti.get(index).getCat_id());
            Affich.SetCateg(listcat.get(listcat.indexOf(categorie)).getNom()); // categorie
            Affich.SetContenu(html2text(listarti.get(index).getContenu()));

            String path = "http://127.0.0.1/www/PIJAVA/web/assets/images/" + listarti.get(index).getImage();

            javafx.scene.image.Image img = new javafx.scene.image.Image(path);
            Affich.SetImage(img);
            Affich.SetTags(listarti.get(index).getListags());
            stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
            stage.setTitle("Blog");
            Scene scene = new Scene(root);
            stage.setScene(scene);
        }
    }

    public void AjoutCommentaire() throws IOException {

        GestionArticles gstcom = new GestionArticles();
        if (!commerror) {
            if (gstcom.addComm(this.email_user, this.id, this.commcontenu)) {
                Stage stage = (Stage) likebtn.getScene().getWindow();

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/affichageArticleSingle.fxml"));
                Parent root = loader.load();
                AffichageArticleSingleFront Affich;

                Affich = loader.getController();
                GestionArticles gstart = new GestionArticles();
                List<Articles> listarti = gstart.getArticles();
                Articles article = new Articles(this.id);
                int index = listarti.indexOf(article);
                Articles artiAffich = listarti.get(index);
                Affich.SetTitle(artiAffich.getTitre()); // titre
                Affich.SetArticleId(listarti.get(index).getId());
                GestionCategories gstcat = new GestionCategories();
                gstcat.getcatlist();
                List<Categories> listcat = gstcat.getcatlist();
                Categories categorie = new Categories(listarti.get(index).getCat_id());
                Affich.SetCateg(listcat.get(listcat.indexOf(categorie)).getNom()); // categorie
                Affich.SetContenu(html2text(listarti.get(index).getContenu()));

                String path = "http://127.0.0.1/www/PIJAVA/web/assets/images/" + listarti.get(index).getImage();

                javafx.scene.image.Image img = new javafx.scene.image.Image(path);
                Affich.SetImage(img);
                Affich.SetTags(listarti.get(index).getListags());
                stage.getIcons().add(new javafx.scene.image.Image("/projet/images/article-512.png"));
                stage.setTitle("Blog");
                Scene scene = new Scene(root);
                stage.setScene(scene);
            }
        }

    }

    public void loadCommentaires(int id) {
        GestionArticles gstartcom = new GestionArticles();

        List<Commentaires> listcommentaire = new ArrayList<Commentaires>();
        listcommentaire = gstartcom.GetCommentaires(id);
        Node[] nodescom;
        // System.out.println(listcategorie.size());

        nodescom = new Node[listcommentaire.size()];

        for (int i = 0; i < listcommentaire.size(); i++) {
            try {

                FXMLLoader loader = new FXMLLoader(getClass().getResource("/projet/views/ItemCommentaireFront.fxml"));

                nodescom[i] = loader.load();

                //Get controller of scene2
                AffichageCommentaireFront Affich;
                Affich = loader.getController();

                Affich.SetUsername(gstartcom.GetUsername(listcommentaire.get(i).getEmail()));
                Affich.SetContenu(listcommentaire.get(i).getContenu());

                PNLcommentaire.getChildren().add(nodescom[i]);

            } catch (IOException ex) {
                Logger.getLogger(AffichageArticleListFrontGlobal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @FXML
    private void AfficherC(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet/views/afficherCategorieClubFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEvenements(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet/views/EvenemnetFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet/views/LoginGUI.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherB(ActionEvent event) throws MalformedURLException, IOException {

        Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/views/CategorieFront.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    private void AfficherEtablissement(ActionEvent event) throws MalformedURLException, IOException {

        Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet2020/AficcherEtablissement.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    @FXML
    public void AfficherBlog(ActionEvent event) throws MalformedURLException, IOException {
        Stage stage = (Stage) this.PNLCatList.getScene().getWindow();
        URL url = new File("src/projet/views/affichageArticlesFrontList.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);

        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

}
