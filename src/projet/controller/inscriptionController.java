/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import projet.service.InscriptionService;

/**
 *
 * @author youssef
 */
public class inscriptionController implements Initializable {
    @FXML
    public Label questionP;

    @FXML
    public Label questionD;

    @FXML
    public Label questionT;
    
    

    InscriptionService service=new InscriptionService();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
    public void ajouterInscription(){
        
    }
}
