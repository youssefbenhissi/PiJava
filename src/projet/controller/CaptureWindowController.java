/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import com.github.sarxos.webcam.Webcam;
import com.jfoenix.controls.JFXButton;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import projet.models.Utilisateur;
import projet.service.ParentService;

/**
 * FXML Controller class
 *
 * @author Damdoum
 */
public class CaptureWindowController implements Initializable {

    @FXML
    private ImageView imgHolder;
    ParentService service = new ParentService();

    public boolean run = true;

    File TakenImage;

    Image image;
    Webcam cam;
    @FXML
    private JFXButton valid;
    Utilisateur user;
    @FXML
    private AnchorPane mainAnchor;
    
    String name  ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        valid.setVisible(false);
        cam = Webcam.getDefault();
        cam.setViewSize(new Dimension(640, 480));
        cam.open();
        new VideoFeedTaker().start();
        //cam.close();

    }

    @FXML
    private void snap(ActionEvent event) {

        user = service.selectUser(8);
        System.out.println(user);
        run = false;
        name = generateFileName() ;
        try {
            ImageIO.write(cam.getImage(), "PNG", new File("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + name +".png"));
        } catch (IOException ex) {
            Logger.getLogger(CaptureWindowController.class.getName()).log(Level.SEVERE, null, ex);
        }
        valid.setVisible(true);
        cam.close();

    }

    @FXML
    private void repeat(ActionEvent event) {
        cam.open();
        valid.setVisible(false);
        run = true;
        new VideoFeedTaker().start();

    }

    @FXML
    private void Validate(ActionEvent event) {
        
            user = service.selectUser(8);
            
            TakenImage = new File("C:\\Users\\youssef\\PhpstormProjects\\pidevFinal\\web\\assets\\images\\" + name+".png");
            
            user.setImage(name+".png");
            ParentService serv = new ParentService();
            serv.modifierP(user);
            
            //ProfileController controller = new ProfileController();
              
          
             //Image im = new Image("file:" + TakenImage.getPath());
             
        // do whatever you need with newSelection....
    
            //controller.myCircle.setFill(new ImagePattern(im));
            Stage stage = (Stage) mainAnchor.getScene().getWindow();
            stage.close();
       

    }

    class VideoFeedTaker extends Thread {

        @Override
        public void run() {
            while (run) {
                try {
                    BufferedImage img = cam.getImage();

                    image = SwingFXUtils.toFXImage(img, null);
                    imgHolder.setImage(image);
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    Logger.getLogger(CaptureWindowController.class.getName()).log(Level.SEVERE, null, ex);
                }

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
        return "parent_" + saltStr;
    }

}
