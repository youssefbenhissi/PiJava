/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import projet.controller.inscriptionBackController;
import projet.service.InscriptionService;

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
        
        this.btn_delete.setOnAction(event -> {
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
               alert.setTitle("Confirmation");
               alert.setHeaderText("Vous voulez vraiment supprimer cette inscription?");

               Optional<ButtonType> result = alert.showAndWait();
               if (result.get() == ButtonType.OK){
                   InscriptionService service = new InscriptionService();
                   if(service.supprimerInscription(id)){
                        inscriptionBackController gestionInscription = new inscriptionBackController();
                        gestionInscription.observableList.remove(this);
                   }
               } else {

               }
           });
    }

}
