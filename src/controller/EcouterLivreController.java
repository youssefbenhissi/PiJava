/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;


import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * FXML Controller class
 *
 * @author HP
 */
public class EcouterLivreController implements Initializable {
 String path = "C:\\Users\\youssef\\Desktop\\conntes\\Le_petit_chaperon_rouge.mp3";
    Media media = new Media(new File(path).toURI().toString()); 
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    
//    
//    
        // TODO
    }    

    @FXML
    private void playsound(ActionEvent event) {
        
    mediaPlayer.play();
    }

   @FXML
    private void StopSound(ActionEvent event) {
        mediaPlayer.stop();
    }

   
    @FXML
    private void pause(ActionEvent event) {
        mediaPlayer.pause();
    }

  
    
    
}
