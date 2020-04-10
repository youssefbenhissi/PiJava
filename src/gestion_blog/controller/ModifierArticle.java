/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.controller;

import static gestion_blog.controller.AjoutArticle.html2text;
import gestion_blog.models.Articles;
import gestion_blog.models.Categories;
import gestion_blog.models.Tags;
import gestion_blog.service.GestionArticles;
import gestion_blog.service.GestionCategories;
import gestion_blog.service.GestionTags;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.web.HTMLEditor;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author geek alaa
 */
public class ModifierArticle implements Initializable{
    
    
    

    
    @FXML
     private TextField titre;
    
    @FXML
     private TextField descri;
    
    @FXML
     private MenuButton categ;
    
    @FXML
     private javafx.scene.control.Label catlabel;
    
    @FXML
     private ListView tags;
    
    @FXML
     private javafx.scene.control.Label taglabel;
    
    @FXML
     private HTMLEditor contenu;
    
    @FXML
     private Button imagecho;
    
     @FXML
     private Button Retourner;
    
    @FXML
     private ImageView imagev;
    
    @FXML
     private MenuButton type;
    
    @FXML
    private Button submitButton;
    
    
    private int id;
    private String titre_Article ="";
    private String Descri_Article ="" ;
    private int catid_Article ;
    private String date_Article="" ;
    private String contenu_Article ;
    private String Image_Article="" ;
    private int typeid = 0;
    
    File file ;
    boolean categchange = false;
    boolean tagchange = false;
    boolean imagechange = false;
    boolean typechange = false;
    boolean contenuchange = false;
    
    
    private boolean errortitre = false;
    private boolean errordescri = false;
    private boolean errorcat = false;
    private boolean errortag = false;
    private boolean errorcont = false;
    private boolean errorimage = false;
    private boolean errortype = false;
    
     Articles articlemodf ;   
    
    java.util.List<Categories> listcat = new ArrayList<Categories>();
    java.util.List<Tags> listags = new ArrayList<Tags>();
    java.util.List<Tags> listags2 = new ArrayList<Tags>();
    java.util.List<Tags> listagsSelectedaj = new ArrayList<Tags>();
    List<Articles> listarticle = new ArrayList<Articles>();
    
    public void initialize(URL location, ResourceBundle resources) {
         catlabel.setText("");   
         taglabel.setText(""); 
    
      
        
        
        
        
        
        
        
        
        
        
        
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

      this.date_Article = format.format( new Date()   );
        
        
        
        titre.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(newValue);
         
            /*try {
               
                this.displayTray("Titre mini 3 caractères  -  200 max");
            } catch (AWTException ex) {
                Logger.getLogger(AjoutArticle.class.getName()).log(Level.SEVERE, null, ex);
            }*/
            if(newValue.trim().isEmpty()){
                this.errortitre = true;
                titre.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                titre.clear();
            }else if(newValue.length() < 3){
                 this.errortitre = true;
                titre.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                }else if(newValue.length() > 200){
                     this.errortitre = true;
                titre.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
            }else{
                    this.errortitre = false;
                    this.titre_Article = newValue;
                titre.setStyle("-fx-background-radius: 1em ;");
            }
            
        });
        
        
        
        descri.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            System.out.println(newValue);
            
           
            if(newValue.trim().isEmpty()){
                this.errordescri = true;
                descri.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                descri.clear();
            }else if(newValue.length() < 10){
                this.errordescri = true;
                descri.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                }else if(newValue.length() > 300){
                    this.errordescri = true;
                descri.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
            }else{
                    this.Descri_Article = newValue;
                    this.errordescri = false;
                descri.setStyle("-fx-background-radius: 1em ;");
            }
            
        });
        GestionCategories gstcat = new GestionCategories();
        listcat = gstcat.getcatlist();
        
        for (int i = 0; i < listcat.size(); i++) {
            MenuItem m = new MenuItem(listcat.get(i).toString());
            m.setId(Integer.toString(listcat.get(i).getId()));
            m.setOnAction(Cateven);
            categ.getItems().add(m);
        }
        
        
        
        
        
        
        
        GestionTags gstags = new GestionTags();
        listags = gstags.getTags();
        ObservableList<Tags> listtagsview = FXCollections.observableArrayList();
        
        for (int i = 0; i < listags.size(); i++) {
                listtagsview.add(listags.get(i));
        }
        tags.setItems(listtagsview);
        tags.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        
   
        tags.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                   ObservableList<Tags> selectedtags =  tags.getSelectionModel().getSelectedItems();
                        if(selectedtags.size() == 0){
                             tagchange = true;
                             errortag = true;
                        }else{
                            tagchange = true;
                             errortag = false;
                             listagsSelectedaj = selectedtags;
                        }
                           //System.out.println(listagsSelectedaj.size());
                            System.out.println(selectedtags);
                           /*for (int i = 0; i < listagsSelectedaj.size(); i++) {
                                System.out.println(listagsSelectedaj.get(i).getId());
                           }*/
                          
            }
            
        });
        
       contenu.setOnKeyReleased(new EventHandler<KeyEvent>()
      {
             @Override
             public void handle(KeyEvent event) {

                 if(html2text(contenu.getHtmlText()).trim().isEmpty()){
                errorcont = true;
                contenu.setStyle("-fx-border-color :#ff4242;");
               
            }else if(html2text(contenu.getHtmlText()).length() < 200){
                errorcont = true;
                contenu.setStyle("-fx-border-color :#ff4242;");
                }else if(html2text(contenu.getHtmlText()).length() > 1000){
                    errorcont = true;
                contenu.setStyle("-fx-border-color :#ff4242;");
            }else{
                    contenuchange = true;
                    contenu_Article = contenu.getHtmlText();
                    errorcont = false;
                contenu.setStyle("-fx-background-radius: 0em ;");
            }
                 
            //  System.out.println();
          
             }
          
      });
       
       
       
       
       
       
       
       
        
        MenuItem publier = new MenuItem("Publier");
        MenuItem brouillon = new MenuItem("Brouillon");
        type.getItems().add(publier);
        type.getItems().add(brouillon);
        publier.setOnAction(typeeven);
        type.setText("Brouillon");
         brouillon.setOnAction(typeeven);
   
       
    }
    
    
   public void setId(int id) {
        this.id = id;
         
    }
   
   
   public void load(){
       
   GestionArticles gstar = new GestionArticles();
   listarticle = gstar.getArticles();
   Articles article = new Articles(id);
   int index = listarticle.indexOf(article);
   articlemodf = listarticle.get(index);
   
   titre.setText(articlemodf.getTitre());
   descri.setText(articlemodf.getDescription());
   GestionCategories gstcat = new GestionCategories();
        listcat = gstcat.getcatlist();
        Categories categorie = new Categories(articlemodf.getCat_id());
        int indexcateg = listcat.indexOf(categorie);
        Categories cat = new Categories();
        
        cat = listcat.get(indexcateg);
        if(!categchange){
            categ.setText(cat.getNom());
            this.catid_Article = cat.getId();
        }
        
        
        
        
        if(!tagchange){
          
           listags2 = articlemodf.getListags();
        for (int i = 0; i < listags2.size(); i++) {
            tags.getSelectionModel().select(listags2.get(i));
        }
         listagsSelectedaj = listags2;
        }
        
        
        contenu.setHtmlText(articlemodf.getContenu());
        if(!contenuchange){
            this.contenu_Article = articlemodf.getContenu();
        }
        
        if(!imagechange){
            this.Image_Article = articlemodf.getImage();
        }
        
        int type_preloaded = articlemodf.getType();
        
        
        
        if(!typechange){
            this.typeid = type_preloaded;
        }
        
   
       //System.out.println(id);
       
   
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
    
   
    
   EventHandler<ActionEvent> Cateven = (ActionEvent e) -> {
      // System.out.println(((MenuItem)e.getSource()).getId());
       categ.setText(((MenuItem)e.getSource()).getText());
       this.catid_Article = Integer.parseInt(((MenuItem)e.getSource()).getId());
       categchange = true;
        this.errorcat = false;
         //System.out.println(id);
    }; 
   
   EventHandler<ActionEvent> typeeven = (ActionEvent e) -> {
       
       type.setText(((MenuItem)e.getSource()).getText());
       typechange = true;
       if("Publier".equals(((MenuItem)e.getSource()).getText())){
           this.typeid = 1;
       }else if("Brouillon".equals(((MenuItem)e.getSource()).getText())){
           this.typeid = 0;
       }else {
           this.errortype = true;
       }
    }; 
    
    public void displayTray(String msg) throws AWTException {
        //Obtain only one instance of the SystemTray object
        SystemTray tray = SystemTray.getSystemTray();

       
        Image image = Toolkit.getDefaultToolkit().createImage("/gestion_blog/images/how-to-edit-a-png-image-1.png");
       
        TrayIcon trayIcon = new TrayIcon(image, "Modification Article");
        //Let the system resize the image if needed
        trayIcon.setImageAutoSize(true);
        //Set tooltip text for the tray icon
        //trayIcon.setToolTip("System tray icon demo");
        tray.add(trayIcon);

        trayIcon.displayMessage("Modification Article", msg, TrayIcon.MessageType.INFO);
    }
    
         @FXML
            public void handleImage(ActionEvent event) {
                FileChooser.ExtensionFilter imageFilter
    = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png" , "*.gif");
                 FileChooser fileChooser = new FileChooser();
                fileChooser.getExtensionFilters().add(imageFilter);
                
                 file = fileChooser.showOpenDialog(null);
                if (file != null) {
                    imagechange = true;
                  // Image image = new Image(selectedFile.toURI().toString());
this.errorimage = false;
try {
                BufferedImage bufferedImage = ImageIO.read(file);
                WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
                imagev.setImage(image);
            } catch (IOException ex) {
               // Logger.getLogger(JavaFXPixel.class.getName()).log(Level.SEVERE, null, ex);
            }
this.Image_Article = file.getAbsolutePath();
                     System.out.println(file.getAbsolutePath());
                     
                    // System.out.println(getFileExtension(file));
                }
            }
            
            private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
            
 
         @FXML
 public void handleAjouterArticleButton(ActionEvent event) throws MalformedURLException, IOException {
             GestionArticles gst = new GestionArticles();
             Articles article = new Articles(this.titre_Article, this.Image_Article, this.Descri_Article, this.contenu_Article, this.date_Article, this.id, 0, this.typeid, this.catid_Article, listagsSelectedaj);
     //System.out.println(article);
             
              int errors =0;
    
  
     
     
    
  
             if(errortitre){
                  titre.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
                errors = errors+1;
             }else if(errordescri){
                  descri.setStyle("-fx-background-radius: 1em ;-fx-border-color :#ff4242; -fx-border-radius: 1em;");
             errors = errors+1;
             }else if(errorcat){
                  catlabel.setText("Champ obligatoire !!");   
            errors = errors+1;
             }else if(errortag){
             taglabel.setText("Minimum un TAG !!");
            errors = errors+1;
             }else if(errorcont){
             contenu.setStyle("-fx-border-color :#ff4242;");
            errors = errors+1;
             }
             else if(errorimage){
                 try {
                this.displayTray("Sélectionner une image");
            } catch (AWTException ex) {
                Logger.getLogger(AjoutArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
             
             }else{
 
                 if(gst.ModifierArticle(article)){
                     if(file != null){
         FileUploader fu = new FileUploader("http://127.0.0.1/www/PIJAVA/images/");
        
        //Upload
        String fileNameInServer = fu.upload(file.getAbsolutePath());
                     }
                     
                     
                  System.out.println("true : true");
                  
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("/gestion_blog/views/Home.fxml"));
             Node[] node = new Node[1];
                     
                     
                    node[0] = loader.load();
            
             
            //Get controller of scene2
            Controller contr;
                contr = loader.getController();
                
                contr.Update();
                 try {
                this.displayTray("Article Modifié");
            } catch (AWTException ex) {
                Logger.getLogger(AjoutArticle.class.getName()).log(Level.SEVERE, null, ex);
            }
                  Stage stage = (Stage) submitButton.getScene().getWindow();
                  
                   URL url = new File("src/gestion_blog/views/Home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion de Blog");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    
    //stage.close();*/
    
    
             }else{
                 System.out.println("error : error");
             }
                 
             }
             
       
                 
                 
                 
                 
                 
                 
                 
           
             
 }

    public void retouraccu() throws MalformedURLException, IOException{
         Stage stage = (Stage) Retourner.getScene().getWindow();
        URL url = new File("src/gestion_blog/views/Home.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage.getIcons().add(new javafx.scene.image.Image("/gestion_blog/images/article-512.png"));
        stage.setTitle("Gestion de Blog");
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

   
    
    
    
    
    
    
}
