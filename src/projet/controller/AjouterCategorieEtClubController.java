
package projet.controller;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;
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
    private Label a2;
    @FXML
    private Label b2;
    @FXML
    private Label a1;
    @FXML
    private Label b1;
    @FXML
    private JFXButton btnsignup;
    @FXML
    private JFXButton btnsignin;
    @FXML
    private TextField u1;
    @FXML
    private TextField u2;
    @FXML
    private TextField u3;
    
    @FXML
    private TextField u4;
    @FXML
    private TextField n1;
    @FXML
    private AnchorPane layer1;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        btnsignin.setVisible(false);
        n1.setVisible(false);
        u1.setVisible(true);
        u2.setVisible(true);
        u3.setVisible(true);
        u4.setVisible(true);
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
        b1.setVisible(true);
        b2.setVisible(true);
        
        s1.setVisible(true);
        s2.setVisible(true);
        s3.setVisible(true);
        signup.setVisible(true);
        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        signin.setVisible(false);
        a1.setVisible(false);
        a2.setVisible(false);
        btnsignup.setVisible(false);
        n1.setVisible(true);
        u1.setVisible(false);
        u2.setVisible(false);
        u3.setVisible(false);
        u4.setVisible(false);
        slide.setOnFinished((e->{
        
        
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
        b1.setVisible(false);
        b2.setVisible(false);
        
        s1.setVisible(false);
        s2.setVisible(false);
        s3.setVisible(false);
        signup.setVisible(false);
        l1.setVisible(true);
        l2.setVisible(true);
        l3.setVisible(true);
        signin.setVisible(true);
        a1.setVisible(true);
        a2.setVisible(true);
        btnsignup.setVisible(true);
        n1.setVisible(false);
        u1.setVisible(true);
        u2.setVisible(true);
        u3.setVisible(true);
        u4.setVisible(true);
        slide.setOnFinished((e->{
        
        
        }));
    }

    @FXML
    private void btnsignup(MouseEvent event) {
    }

    @FXML
    private void sign(MouseEvent event) {
        
    }

    @FXML
    private void click(ActionEvent event) {
        if("cyberdeveloper".equals(n1.getText())){
            String tilte = "Sign In";
            String message = n1.getText();
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
        }
        if(!"cyberdeveloper".equals(n1.getText())){
            String tilte = "Sign In";
            String message = "Error Username "+"'"+n1.getText()+"'"+" Wrong";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
        
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.ERROR);
            tray.showAndDismiss(Duration.millis(3000));
        }
        if (!"cyberdeveloper".equals(n1.getText())){
            String tilte = "Sign In";
            String message = "Error Username "+"'"+n1.getText();
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

