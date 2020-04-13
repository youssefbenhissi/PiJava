/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import Service.ServiceCategorier;
import com.sbix.jnotify.NPosition;
import com.sbix.jnotify.NoticeType;
import com.sbix.jnotify.NoticeWindow;
import models.categorier;
import java.awt.Desktop;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class AjouterCatigorieController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField libelle;
    @FXML
    private TextField description;
    @FXML
    private ImageView imageView;
    @FXML
    private Label erreur_libelle;
    @FXML
    private Label erreur_description;

    private String photopath;
    private File photofile;

    private FileChooser fileChooser;
    private final Desktop descDesktop = Desktop.getDesktop();
    private Image Image;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    @FXML
    private void AjoutCatigorie(ActionEvent event) throws SQLException {
        boolean isLibelleEmpty = Validation.TextfielValidation.isTextfielValidation(libelle, erreur_libelle, "le champ du libelle est vide");
        boolean isDescriptionEmpty = Validation.TextfielValidation.isTextfielValidation(description, erreur_description, "le champ du description est vide");

        String libe = libelle.getText();
        String desc = description.getText();

        categorier l = new categorier(libe, desc, photofile.getName());
        l.setFile(photofile);
        ServiceCategorier sl = new ServiceCategorier();

        try {

            sl.addCtegorier(l);
        new NoticeWindow(NoticeType.SUCCESS_NOTIFICATION,"ajout avec sucee",NoticeWindow.LONG_DELAY,NPosition.BOTTOM_RIGHT);

        } catch (SQLException ex) {

            Logger.getLogger(AjouterCatigorieController.class.getName()).log(Level.SEVERE, null, ex);

        }
        
       
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
            photopath = photofile.getAbsolutePath();

            image = new Image(photofile.toURI().toString(), 270, 280, true, true);
            
            File outputFile = new File("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\"+photofile.getName());
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
    private void vider(ActionEvent event) {
        libelle.clear();
        description.clear();
    }
}
