/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

import java.util.Date;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import projet.API.QrCodeMailApi;
import projet.controller.ReservationBackController;
import projet.controller.inscriptionBackController;
import projet.service.InscriptionService;
import projet.service.NewsLetterService;
import projet.service.ReservationService;

/**
 *
 * @author youssef
 */
public class reservation {

    private int id;
    private String status;
    private int idevenement;
    private int idUser;
    private String nomEvenement;
    private String nomUser;
    private Button btn_confirmer;

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public reservation(int id, String status, int idevenement, int idUser) {
        this.id = id;
        this.status = status;
        this.idevenement = idevenement;
        this.idUser = idUser;
    }

    public reservation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdevenement() {
        return idevenement;
    }

    public void setIdevenement(int idevenement) {
        this.idevenement = idevenement;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Button getBtn_confirmer() {
        return btn_confirmer;
    }

    public void setBtn_confirmer(Button btn_confirmer) {
        this.btn_confirmer = btn_confirmer;

        this.btn_confirmer.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous voulez vraiment confirmer cette inscription?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                ReservationService service = new ReservationService();
                int idClub = service.retournerIdClub(id);
                int idUtillisateur = service.retournerIdUtilisateur(id);
                Evenement c = service.retournerCapacite(idClub);
                NewsLetterService newsLetter = new NewsLetterService();
                if (c.getCapaciteEvenement()> 0) {
                    String emailUtilisateur = service.retournerEmailUtilisateur(idUtillisateur);
                  //  newsLetter.sendMail("youssef.benhissi@esprit.tn", "ilovetennis", emailUtilisateur, c.getNomEvenement(), "on a approuvé votre inscription dans le club");
                  Date today = new Date();  
                  QrCodeMailApi.envoyerQrCode(nomEvenement, today);
                  if (service.confirmerReservation(id)) {
                        service.modifierCapacite(idClub, c.getCapaciteEvenement()- 1);
                        ReservationBackController gestionInscription = new ReservationBackController();
                        ObservableList observableList = FXCollections.observableArrayList(service.selectAllReservations());
                        gestionInscription.observableList.clear();
                        gestionInscription.observableList.addAll(observableList);
                    }
                } else {
                    String emailUtilisateur = service.retournerEmailUtilisateur(idUtillisateur);
                    System.out.println(emailUtilisateur);
                    newsLetter.sendMail("youssef.benhissi@esprit.tn", "ilovetennis", emailUtilisateur, c.getNomEvenement(), "Nous sommes desoles. on a atteint la capacite maximale. Nous vous verrons tres prochainement");
                }
            } else {

            }
        });
    }
}
