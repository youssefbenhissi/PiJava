package projet.controller;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import static java.awt.print.Printable.NO_SUCH_PAGE;
import static java.awt.print.Printable.PAGE_EXISTS;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import projet.API.Printer;
import projet.models.Inscription;
import projet.service.InscriptionService;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;

/**
 *
 * @author Benny
 */
public class InscriptionClub implements Initializable, Printable {

    @FXML
    private AnchorPane layer1;
    @FXML
    public Label questionP;

    @FXML
    public Label questionD;

    @FXML
    public Label questionT;
    @FXML
    private TextField ReponsePr;

    @FXML
    private TextField ReponseDe;
    @FXML
    public Label idUtilisateur;
    @FXML
    private TextField ReponseTr;
    @FXML
    public Label idClub;
    public int idUtilis;
    InscriptionService service = new InscriptionService();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println(idUtilisateur.getText());
    }

    @FXML
    public void ajouterInscrip(ActionEvent even) {
        Inscription insc = new Inscription();
        System.out.println(idUtilisateur);
        insc.setQuestionPr(questionP.getText());
        insc.setQuestionDe(questionD.getText());
        insc.setQuestionTr(questionT.getText());
        insc.setReponsePr(ReponsePr.getText());
        insc.setReponseDe(ReponseDe.getText());
        insc.setReponseTr(ReponseTr.getText());
        insc.setIdClub(Integer.parseInt(idClub.getText()));
        insc.setStatus("non traitée");
      
        try {
            File myObj = new File("C:\\Users\\youssef\\Desktop\\PiJava-master (2)\\FileWriter.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                 idUtilis = Integer.parseInt(myReader.nextLine());
                //System.out.println(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
          System.out.println("hhhhhhhhhhhhhh" + idUtilis);
        insc.setIdUser(idUtilis);
        service.ajouterInscription(insc);
        String tilte = "Inscription enregistre";
        String message = "votre inscription a été bien enregistrée.";
        TrayNotification tray = new TrayNotification();
        AnimationType type = AnimationType.POPUP;
        tray.setAnimationType(type);
        tray.setTitle(tilte);
        tray.setMessage(message);
        tray.setNotificationType(NotificationType.SUCCESS);
        tray.showAndDismiss(Duration.millis(3000));
    }

    public void setIdUtilisateur(int id) {
        idUtilis = id;
    }

    @FXML
    private void quitter(ActionEvent event) {
        // get a handle to the stage
        Stage stage = (Stage) layer1.getScene().getWindow();
        // do what you have to do
        stage.close();
    }

    @FXML
    private void Imprimer(ActionEvent event) {
        Printer p = new Printer();
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setPrintable(new Printer(), p.getPageFormat(pj));
        try {
            String tilte = "Imprssion inscription";
            String message = "votre inscription est prete a etre imprimée.";
            TrayNotification tray = new TrayNotification();
            AnimationType type = AnimationType.POPUP;
            tray.setAnimationType(type);
            tray.setTitle(tilte);
            tray.setMessage(message);
            tray.setNotificationType(NotificationType.SUCCESS);
            tray.showAndDismiss(Duration.millis(3000));
            pj.print();
        } catch (PrinterException ex) {
            //Logger.getLogger(.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {

        int result = NO_SUCH_PAGE;
        if (pageIndex == 0) {

            Graphics2D g2d = (Graphics2D) graphics;

            double width = pageFormat.getImageableWidth();

            g2d.translate((int) pageFormat.getImageableX(), (int) pageFormat.getImageableY());
            FontMetrics metrics = g2d.getFontMetrics(new Font("Arial", Font.BOLD, 7));
            int idLength = metrics.stringWidth("000");
            int amtLength = metrics.stringWidth("000000");
            int qtyLength = metrics.stringWidth("00000");
            int priceLength = metrics.stringWidth("000000");
            int prodLength = (int) width - idLength - amtLength - qtyLength - priceLength - 17;
            int productPosition = 0;
            int discountPosition = prodLength + 5;
            int pricePosition = discountPosition + idLength + 10;
            int qtyPosition = pricePosition + priceLength + 4;
            int amtPosition = qtyPosition + qtyLength;
            try {
                /*Draw Header*/
                int y = 20;
                int yShift = 10;
                int headerRectHeight = 15;
                int headerRectHeighta = 40;
                g2d.setFont(new Font("Monospaced", Font.PLAIN, 9));
                g2d.drawString("-------------------------------------", 12, y);
                y += yShift;
                g2d.drawString("      Inscription au club        ", 12, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 12, y);
                y += headerRectHeight;
                g2d.drawString(" QUESTION                                  REPONSE", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += headerRectHeight;
                g2d.drawString(" " + questionP.getText() + "                  " + ReponsePr.getText() + "  ", 10, y);
                y += yShift;
                g2d.drawString(" " + questionD.getText() + "                  " + ReponseDe.getText() + "  ", 10, y);
                y += yShift;
                g2d.drawString(" " + questionT.getText() + "                  " + ReponseTr.getText() + "  ", 10, y);
                y += yShift;
                g2d.drawString("-------------------------------------", 10, y);
                y += yShift;
                g2d.drawString("          Merci pour votre inscription au sein de notre club        ", 10, y);
                y += yShift;
                g2d.drawString("*************************************", 10, y);
                y += yShift;
                g2d.drawString("    Tres Prochainement   ", 10, y);
                y += yShift;
                g2d.drawString("*************************************", 10, y);
                y += yShift;

            } catch (Exception r) {
                r.printStackTrace();
            }

            result = PAGE_EXISTS;
        }
        return result;
    }

    public void sIdUtilisateur(int id) {
        System.out.println("hhhhhhhhjjjjj");
        System.out.println(id);
        idUtilis = id;
    }
}
