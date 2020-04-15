/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import utils.ConnexionBase;
import Iservice.IlivreService;
import Service.ServiceCategorier;
import Service.ServiceLivre;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.awt.image.BufferedImage;
import java.io.File;
import models.categorier;
import models.livre;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterLivreController implements Initializable {

    @FXML
    private TextField description;
    @FXML
    private TextField auteur;
    @FXML
    private TextField nbr;
    @FXML
    private ComboBox<categorier> nom;
    @FXML
    private TextField nom_l;
    @FXML
    private TableView<livre> tab;
    @FXML
    private TableColumn<livre, String> id_l;
    @FXML
    private TableColumn<livre, String> idcat;
    @FXML
    private TableColumn<livre, String> noml;
    @FXML
    private TableColumn<livre, String> desci;
    @FXML
    private TableColumn<livre, String> auteur_l;
    @FXML
    private TableColumn<livre, String> nbr_l;
    private int id_categorie;
    ObservableList<livre> oblist = FXCollections.observableArrayList();
    ServiceLivre es = new ServiceLivre();
    private ObservableList<categorier> categorie;
    private PreparedStatement pst = null;
    private ResultSet rs = null;
    @FXML
    private TextField rechercheBar;
    private IlivreService annonceService;
    @FXML
    private ImageView imageView;
    private File photofile;
    private Image Image;
    private String photopath;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ServiceLivre annonceService = new ServiceLivre();
        oblist = FXCollections.observableArrayList(es.getAll());

        ObservableList observableList = FXCollections.observableArrayList(oblist);
        tab.setItems(observableList);
        id_l.setCellValueFactory(new PropertyValueFactory<>("id"));
        idcat.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        noml.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desci.setCellValueFactory(new PropertyValueFactory<>("description"));

        auteur_l.setCellValueFactory(new PropertyValueFactory<>("auteur"));

        nbr_l.setCellValueFactory(new PropertyValueFactory<>("nombredepage"));
        // TODO

        Connection con = ConnexionBase.getInstance().getCnx();
        categorie = FXCollections.observableArrayList();
        try {
            pst = con.prepareStatement("select * from category");
            rs = pst.executeQuery();
            while (rs.next()) {
                categorie.add(new categorier(rs.getInt(1), rs.getString(2), rs.getString(3)));

            }
            //2ism l entitie mte3i
            nom.setItems(categorie);
        } catch (Exception e) {
        }
        nom.setConverter(new StringConverter<categorier>() {
            @Override
            public String toString(categorier object) {

                return object.getLibelle();
            }

            @Override
            public categorier fromString(String string) {
                return null;
            }

        });

        nom.valueProperty().addListener((obs, oldVaue, newValue) -> {
            if (newValue != null) {
                id_categorie = newValue.getId();
            }
        });

    }

    @FXML
    private void AjouterLivre(ActionEvent event) throws SQLException {
        String nom = nom_l.getText();
        String auteu = auteur.getText();
        String descriptio = description.getText();
        int nombredepage = Integer.parseInt(nbr.getText());
        ServiceLivre sl = new ServiceLivre();
        livre l = new livre(nom, descriptio, auteu, nombredepage, id_categorie, photopath);
        l.setFile(photofile);
        sl.addLivre(l);
        AfficherAll();
    }

    private void AfficherAll() {

        ServiceLivre annonceService = new ServiceLivre();
        oblist = FXCollections.observableArrayList(es.getAll());

        ObservableList observableList = FXCollections.observableArrayList(oblist);
        tab.setItems(observableList);
        id_l.setCellValueFactory(new PropertyValueFactory<>("id"));
        idcat.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        noml.setCellValueFactory(new PropertyValueFactory<>("nom"));
        desci.setCellValueFactory(new PropertyValueFactory<>("description"));

        auteur_l.setCellValueFactory(new PropertyValueFactory<>("auteur"));

        nbr_l.setCellValueFactory(new PropertyValueFactory<>("nombredepage"));

    }

    @FXML
    private void rechercher(KeyEvent event) {
        if (!rechercheBar.getText().isEmpty()) {
            tab.setVisible(true);
            ServiceLivre annonceService = new ServiceLivre();
            List<livre> myList = annonceService.rechercheCategories(rechercheBar.getText());
            ObservableList<livre> observableList = FXCollections.observableArrayList();

            id_l.setCellValueFactory(new PropertyValueFactory<>("id"));
            idcat.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            noml.setCellValueFactory(new PropertyValueFactory<>("nom"));
            desci.setCellValueFactory(new PropertyValueFactory<>("description"));

            auteur_l.setCellValueFactory(new PropertyValueFactory<>("auteur"));

            nbr_l.setCellValueFactory(new PropertyValueFactory<>("nombredepage"));

            myList.forEach(e -> {

                observableList.add(e);
                // System.out.println(observableList);
            });
            tab.setItems(observableList);
        } else {
            if (rechercheBar.getText().isEmpty()) {
                tab.getItems().clear();
                tab.getItems().addAll(annonceService.getAll());
            }

        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        es = new ServiceLivre();
        int index = tab.getSelectionModel().getSelectedItem().getId();
        //System.out.println(index);
        es.deleteLivre(index);
        AfficherAll();

    }

    @FXML
    private void openPDF(ActionEvent event) throws FileNotFoundException, com.lowagie.text.DocumentException {

        String path = "";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x = j.showSaveDialog(j);

        if (x == JFileChooser.APPROVE_OPTION) {
            path = j.getSelectedFile().getPath();
        }

        com.lowagie.text.Document doc = new com.lowagie.text.Document();

        PdfWriter.getInstance(doc, new FileOutputStream(path + "Suivi.pdf"));

        doc.open();

        PdfPTable tb1 = new PdfPTable(4);

        //Adding headers
        tb1.addCell("id");
        tb1.addCell("nom");
        tb1.addCell("description");
        tb1.addCell("auteur");
        List<livre> listeLivre = es.getAll();
        //livre enf = tab.getSelectionModel().getSelectedItem();
        for(livre enf:listeLivre){
        int l = enf.getId();

        String n = enf.getLibelle();
        String e = enf.getNom();
        String f = enf.getDescription();
        String k = enf.getAuteur();
        int v = enf.getNombredepage();

        tb1.addCell(n);
        tb1.addCell(e);
        tb1.addCell(f);
        tb1.addCell(k);

        }
        doc.add(tb1);
        /*
            Paragraph para = new Paragraph("Test !");
            doc.add(para);
         */
        doc.close();

    }

    @FXML
    private void Vider(ActionEvent event) {
        description.clear();
        auteur.clear();
        nbr.clear();
        nom_l.clear();

        nom.getSelectionModel().clearSelection();

    }

    @FXML
    private void Browser(ActionEvent event) {

        JFileChooser file = new JFileChooser();
        file.setCurrentDirectory(new File(System.getProperty("user.home")));
        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.Images", "jpg", "png", "jpeg");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            photofile = file.getSelectedFile();
            Image image;
            photopath = "file:C:\\wamp64\\www\\img"+photofile.getAbsolutePath();

            image = new Image(photofile.toURI().toString(), 270, 280, true, true);
            
            image = new Image(photofile.toURI().toString(), 270, 280, true, true);
            
            File outputFile = new File("C:\\wamp64\\www\\img\\"+photofile.getName());
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
    try {
        ImageIO.write(bImage, "png", outputFile);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
    
    
            // imagepdp=new ImageView(image);

            imageView.setImage(image);
            imageView.setFitHeight(270);
            imageView.setFitHeight(280);
            imageView.setPreserveRatio(true);

        }
    }
    
     @FXML
    private void listeReservation(ActionEvent event) {
         BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/listeRservation.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCatigorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border_pane.setCenter(root);
    }

    
}
