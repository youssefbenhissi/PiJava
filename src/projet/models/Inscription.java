/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.cell.PropertyValueFactory;
import projet.controller.inscriptionBackController;
import projet.service.InscriptionService;
import projet.service.NewsLetterService;

/**
 *
 * @author youssef
 */
public class Inscription {

    private int id;
    private String reponsePr;
    private String reponseDe;
    private String reponseTr;
    private String questionPr;
    private String questionDe;
    private String questionTr;
    private String status;
    private int idClub;
    private int idUser;
    private String nomClub;
    private Button btn_delete;
    private Button btn_confirmer;

    public String getNomClub() {
        return nomClub;
    }

    public void setNomClub(String nomClub) {
        this.nomClub = nomClub;
    }

    public Inscription(int id, String reponsePr, String reponseDe, String reponseTr, String questionPr, String questionDe, String questionTr, String status, int idClub, int idUser) {
        this.id = id;
        this.reponsePr = reponsePr;
        this.reponseDe = reponseDe;
        this.reponseTr = reponseTr;
        this.questionPr = questionPr;
        this.questionDe = questionDe;
        this.questionTr = questionTr;
        this.status = status;
        this.idClub = idClub;
        this.idUser = idUser;
    }

    public Inscription() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReponsePr() {
        return reponsePr;
    }

    public void setReponsePr(String reponsePr) {
        this.reponsePr = reponsePr;
    }

    public String getReponseDe() {
        return reponseDe;
    }

    public void setReponseDe(String reponseDe) {
        this.reponseDe = reponseDe;
    }

    public String getReponseTr() {
        return reponseTr;
    }

    public void setReponseTr(String reponseTr) {
        this.reponseTr = reponseTr;
    }

    public String getQuestionPr() {
        return questionPr;
    }

    public void setQuestionPr(String questionPr) {
        this.questionPr = questionPr;
    }

    public String getQuestionDe() {
        return questionDe;
    }

    public void setQuestionDe(String questionDe) {
        this.questionDe = questionDe;
    }

    public String getQuestionTr() {
        return questionTr;
    }

    public void setQuestionTr(String questionTr) {
        this.questionTr = questionTr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Button getBtn_delete() {
        return btn_delete;
    }

    public int getIdClub() {
        return idClub;
    }

    public void setIdClub(int idClub) {
        this.idClub = idClub;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setBtn_delete(Button btn_delete) {
        this.btn_delete = btn_delete;
        System.out.println("ahhhhh");
        this.btn_delete.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous voulez vraiment supprimer cette inscription?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                InscriptionService service = new InscriptionService();
                if (service.supprimerInscription(id)) {
                    int idClub = service.retournerIdClub(id);
                    Club c = service.retournerCapacite(idClub);
                    service.modifierCapacite(idClub, c.getCapacite()+1);
                    inscriptionBackController gestionInscription = new inscriptionBackController();
                    gestionInscription.observableList.remove(this);
                }
            } else {

            }
        });
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
                InscriptionService service = new InscriptionService();
                int idClub = service.retournerIdClub(id);
                int idUtillisateur=service.retournerIdUtilisateur(id);
                Club c = service.retournerCapacite(idClub);
                NewsLetterService newsLetter = new NewsLetterService();
                if (c.getCapacite() > 0) {
                    String emailUtilisateur=service.retournerEmailUtilisateur(idUtillisateur);
                    newsLetter.sendMail("youssef.benhissi@esprit.tn","ilovetennis",emailUtilisateur, c.getNom(), "on a approuvé votre inscription dans le club");
                    if (service.confirmerInscription(id)) {
                        service.modifierCapacite(idClub, c.getCapacite()-1);
                        inscriptionBackController gestionInscription = new inscriptionBackController();
                        ObservableList observableList = FXCollections.observableArrayList(service.selectAllInscris());
                        gestionInscription.observableList.clear();
                        gestionInscription.observableList.addAll(observableList);
                    }
                }
                else{
                     String emailUtilisateur=service.retournerEmailUtilisateur(idUtillisateur);
                    newsLetter.sendMail("youssef.benhissi@esprit.tn","ilovetennis",emailUtilisateur, c.getNom(), "Nous sommes desoles. on a atteint la capacite maximale. Nous vous verrons tres prochainement");
                }
            } else {

            }
        });
    }
}
