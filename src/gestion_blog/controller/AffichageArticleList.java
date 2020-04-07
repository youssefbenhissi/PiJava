/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.controller;




import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


/**
 *
 * @author geek alaa
 */
public class AffichageArticleList implements Initializable{
    
    @FXML
     private Label ArticleTitre;
    
    @FXML
     private Label Articlecat;
    
    @FXML
     private Label Articledate;
    
    @FXML
     private Label Articlevues;
    
    @FXML
    private Button modif;
    
    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
     
    }
    
    
    
    public void SetArticleTitle(String Title){
        this.ArticleTitre.setText(Title);
    }
    
    public void SetArticleCategorie(String nom){
        this.Articlecat.setText(nom);
    }
    
    public void SetArticleDate(String date){
        this.Articledate.setText(date);
    }
    
    public void SetArticleVues(int vues){
        this.Articlevues.setText(Integer.toString(vues));
    }
    
    public void SetArticleID(int id){
        this.modif.setId(Integer.toString(id));
    }
    
    public void handleClicks(ActionEvent actionEvent) {
        if (actionEvent.getSource() == modif) {
            final Node source = (Node) actionEvent.getSource();
              String id = source.getId();
                System.out.println(id);   
        }
    }
}
