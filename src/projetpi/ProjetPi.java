/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetpi;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import jxl.write.WriteException;
import projet.models.CategorieEvenement;
import projet.models.Evenement;
import projet.service.CategorieEvenementService;
import projet.service.EvenementService;

/**
 *
 * @author Iheb
 */
public class ProjetPi extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException, WriterException {
       /*  String qrCodeData = "Bonjour Monsieur : na9leha w ella nfawarha? " ;
                
                
                String filePath = "C:\\Users\\Iheb\\Desktop\\document autorisé\\qrGenerated.png";
                String charset = "UTF-8"; // or "ISO-8859-1"
                Map< EncodeHintType, ErrorCorrectionLevel> hintMap = new HashMap< EncodeHintType, ErrorCorrectionLevel>();
                hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
                BitMatrix matrix = new MultiFormatWriter().encode(
                        new String(qrCodeData.getBytes(charset), charset),
                        BarcodeFormat.QR_CODE, 200, 200, hintMap);
                MatrixToImageWriter.writeToFile(matrix, filePath.substring(filePath
                        .lastIndexOf('.') + 1), new File(filePath));
                System.out.println("QR Code image created successfully!");*/
      EvenementService ser=new EvenementService();
        try {
            ser.exportXLS();
        } catch (WriteException ex) {
            Logger.getLogger(ProjetPi.class.getName()).log(Level.SEVERE, null, ex);
        }
        URL url = new File("src/projet/views/EvenementBack.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
