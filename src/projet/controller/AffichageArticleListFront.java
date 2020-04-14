/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.controller;




import static projet.controller.AjoutArticle.html2text;
import projet.models.Articles;
import projet.models.Categories;
import projet.service.GestionArticles;
import projet.service.GestionCategories;
import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import static java.nio.charset.StandardCharsets.ISO_8859_1;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


/**
 *
 * @author geek alaa
 */
public class AffichageArticleListFront implements Initializable{
    
    @FXML
     private Text articletitre1;
    
    @FXML
     private Text articledesc1;
    
    @FXML
     private Text articlevue1;
    
    @FXML
     private Text articlecat1;
    
    @FXML
     private ImageView articleimage1;
    
    @FXML
     private VBox vboxArticle;
    
    private int id;

    
     @Override
    public void initialize(URL location, ResourceBundle resources) {
     
        
     
        
        
    }

    
     
    
    public void setArticletitre1(String articletitre1) {
        this.articletitre1.setText(articletitre1);
    }

    public void setArticledesc1(String articledesc1) {
        //byte[] ptext = articledesc1.getBytes(ISO_8859_1); 
        //String value = new String(ptext, UTF_8); 
        this.articledesc1.setText(html2text(articledesc1));
    }

    public void setArticlevue1(String articlevue1) {
        this.articlevue1.setText(articlevue1);
    }

    public void setArticlecat1(String articlecat1 ) {
        this.articlecat1.setText(articlecat1);
    }

    public void setArticleimage1(javafx.scene.image.Image articleimage1) {
        this.articleimage1.setImage(articleimage1);
    }
    
     public void SetArticleID(int id){
        this.articletitre1.setId(Integer.toString(id));
        this.id = id;
    }
    
     
  
        
     
       public void AfficherArticle() throws IOException{
           Stage stage1 = (Stage) this.vboxArticle.getScene().getWindow();
                  
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
       //  stage.setTitle("Blog");
        Scene scene1 = new Scene(root);
        stage1.setScene(scene1);  
       }
   
    
   
}