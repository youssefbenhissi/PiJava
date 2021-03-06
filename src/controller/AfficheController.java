/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import utils.ConnexionBase;
import Iservice.IcategorierService;
import Service.ServiceCategorier;
import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import java.io.BufferedOutputStream;
import models.categorier;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;







/**
 * FXML Controller class
 *
 * @author HP
 */
public class AfficheController implements Initializable {

    @FXML
    private TableView<categorier> table;
    @FXML
    private TableColumn<categorier,String> id_c;
    @FXML
    private TableColumn<categorier,String> libelle_c;
    @FXML
    private TableColumn<categorier,String> description_c;
    @FXML
    private TextField rechercheBar;
     private IcategorierService annonceService;
     ObservableList<categorier> oblist = FXCollections.observableArrayList();
     ServiceCategorier es=new ServiceCategorier();
     private JTable tabl; //la tabla que recibira

    @FXML
    private Pane pane;
    Connection cn = ConnexionBase.getInstance().getCnx();
    Statement st; //execute la req
    PreparedStatement pst;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        annonceService = new ServiceCategorier();
        oblist = FXCollections.observableArrayList(annonceService.getAll());

        ObservableList observableList = FXCollections.observableArrayList(oblist);
        table.setItems(observableList);
        id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle_c.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description_c.setCellValueFactory(new PropertyValueFactory<>("description"));
        
        // TODO
    }    

   

 private void AfficherAll() {

        annonceService = new ServiceCategorier();
        oblist = FXCollections.observableArrayList(es.getAll());

        ObservableList observableList = FXCollections.observableArrayList(oblist);
        table.setItems(observableList);
        id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
        libelle_c.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        description_c.setCellValueFactory(new PropertyValueFactory<>("description"));
        

      
       

}

    private void afficher(ActionEvent event) {
          try {
            Connection con =ConnexionBase.getInstance().getCnx();
            
            ResultSet rs = con.createStatement().executeQuery("select * from category");
          while(rs.next()){
          oblist.add(new categorier(rs.getInt("id"),rs.getString("libelle"),rs.getString("description")));}
        } catch (SQLException ex) {
            Logger.getLogger(AfficheController.class.getName()).log(Level.SEVERE, null, ex);
        }
          
            id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
            libelle_c.setCellValueFactory(new PropertyValueFactory<>("libelle"));
            description_c.setCellValueFactory(new PropertyValueFactory<>("description"));

            table.setItems(oblist);
    }
@FXML
    private void rechercher(KeyEvent event) {
      if (!rechercheBar.getText().isEmpty()) {
            table.setVisible(true);
            annonceService = new ServiceCategorier();
            List<categorier> myList = annonceService.rechercheCategories(rechercheBar.getText());
            ObservableList<categorier> observableList = FXCollections.observableArrayList();

            id_c.setCellValueFactory(new PropertyValueFactory<>("id"));
            libelle_c.setCellValueFactory(new PropertyValueFactory<>("libelle"));
             description_c.setCellValueFactory(new PropertyValueFactory<>("description"));

            myList.forEach(e -> {

                observableList.add(e);
                // System.out.println(observableList);
            });
            table.setItems(observableList);
        } else {
            if (rechercheBar.getText().isEmpty()) {
                table.getItems().clear();
                table.getItems().addAll(annonceService.getAll());
            }

        }
    }
 @FXML
    private void supprimer(ActionEvent event) {
         es= new ServiceCategorier();
        int index = table.getSelectionModel().getSelectedItem().getId();
        //System.out.println(index);
        es.deleteCtegorier(index);
        AfficherAll();
        
            }
  
  @FXML
    private void ajouteC(ActionEvent event) {
         BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterCatigorie.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCatigorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border_pane.setCenter(root);
    }

    @FXML
    private void openLivre(ActionEvent event) throws FileNotFoundException, DocumentException {
        
         BorderPane border_pane = (BorderPane) ((Node) event.getSource()).getScene().getRoot();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/AjouterLivre.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AjouterCatigorieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        border_pane.setCenter(root);
        
        
        
    }
      
      @FXML
    private void openPDF(ActionEvent event) throws FileNotFoundException, DocumentException {
        
        
        String path="";
        JFileChooser j = new JFileChooser();
        j.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int x=j.showSaveDialog(j);
        
        if (x==JFileChooser.APPROVE_OPTION)
        {
            path=j.getSelectedFile().getPath();
        }
        
        com.lowagie.text.Document doc = new com.lowagie.text.Document();
        
        PdfWriter.getInstance(doc, new FileOutputStream(path+"Suivi.pdf"));
        
        doc.open();
       
        PdfPTable tb1 = new PdfPTable(2);
        
        //Adding headers

        tb1.addCell("libelle_c");
        tb1.addCell("description_c");
        
        
        
            categorier enf = table.getSelectionModel().getSelectedItem();
                        int l = enf.getId();  

             String n = enf.getLibelle();  
            String e =  enf.getDescription(); 
          
             tb1.addCell(n);
            tb1.addCell(e);
          
       
                    doc.add(tb1);
/*
            Paragraph para = new Paragraph("Test !");
            doc.add(para);
            */
                    doc.close();

        }

    @FXML
    private void exportToXL(ActionEvent event) 
        throws SQLException, FileNotFoundException, IOException {
        try{
            String query ="Select * from category";
           PreparedStatement statement;
            statement= cn.prepareStatement(query);
            pst = cn.prepareStatement(query);
          //  st = (Statement) pst.executeQuery();
            ResultSet st = pst.executeQuery();
            // ResultSet st = statement.executeQuery();
            //st =  (Statement) pst.executeQuery();
            
            
            XSSFWorkbook wb = new XSSFWorkbook();
            XSSFSheet sheet = wb.createSheet("category details");
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("id");
            header.createCell(1).setCellValue("libelle");
            header.createCell(2).setCellValue("description");
            
            
            sheet.autoSizeColumn(1);
             sheet.autoSizeColumn(2);
              sheet.setColumnWidth(3, 256);
               
              sheet.setZoom(150);
            
            
            

           // ResultSet st = statement.executeQuery();
            
            int index = 1;
            while(st.next()){
                XSSFRow row = sheet.createRow(index);
                row.createCell(0).setCellValue(st.getString("id"));
                 row.createCell(1).setCellValue(st.getString("libelle"));
                  row.createCell(2).setCellValue(st.getString("description"));
                   
                    index++;
                 
            }
                    
            FileOutputStream fileout = new FileOutputStream("C:\\Users\\HP\\Desktop\\CategorieDetails.xlsx");
            wb.write(fileout);
            fileout.close();
            
             Alert alert = new Alert(AlertType.INFORMATION);
            alert.setTitle("database details");
            alert.setHeaderText(null);
            alert.setContentText("category details exported");
            alert.showAndWait();
            
            pst.close();
            st.close();
            
            
            
        }
        catch(SQLException ex){
            
        }
    }
    }

   


    
    


 
    

