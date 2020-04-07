/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projet.models;
import java.util.*;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import projet.controller.EvenementBackController;
import projet.service.EvenementService;

/**
 *
 * @author Iheb
 */
public class Evenement {
    
    
    private int idEvenement ;
    private String nomEvenement;
    private int capaciteEvenement;
    private String descriptionEvenement;
    private String imageEvenement;
    private int prixEvenement;
    private Date dateEvenement;
    private int idUser;
    private String nomCategorie;
    private int idCatgeorie;
    private Button btn_delete;
    public int getIdCatgeorie() {
        return idCatgeorie;
    }

    public Button getBtn_delete() {
        return btn_delete;
    }

    public void setBtn_delete(Button btn_delete) {
        this.btn_delete = btn_delete;
        this.btn_delete.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous voulez vraiment supprimer cet evenement?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                EvenementService service = new EvenementService();
                if (service.supprimerEvenement(idEvenement)) {
                    EvenementBackController gestionInscription = new EvenementBackController();
                    gestionInscription.myObservableList.remove(this);
                }
            } else {

            }
        });

    }

    public void setIdCatgeorie(int idCatgeorie) {
        this.idCatgeorie = idCatgeorie;
    }
    
    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }
    
    
    
    public Evenement(){
    
    
    }
    public Evenement(int idEvenement ,String nomEvenement,int capaciteEvenement,String descriptionEvenement,
                        String imageEvenement,int prixEvenement,Date dateEvenement,int idUser){
        
        
        this.idEvenement=idEvenement;
        this.nomEvenement=nomEvenement;
        this.capaciteEvenement=capaciteEvenement;
        this.descriptionEvenement=descriptionEvenement;
        this.imageEvenement=imageEvenement;
        this.prixEvenement=prixEvenement;
        this.dateEvenement=dateEvenement;
        this.idUser=idUser;
    }
    public Evenement(int id ,String nomEvenement,int capaciteEvenement,String description,String imgE,int prixE,int idCategorie){
    this.idEvenement=id;
    this.nomEvenement=nomEvenement;
    this.capaciteEvenement=capaciteEvenement;
    this.descriptionEvenement=description;
    this.imageEvenement=imgE;
    this.prixEvenement=prixE;
    this.idCatgeorie=idCategorie;
    }
    public int getIdEvenement() {
        return idEvenement;
    }

    public void setIdEvenement(int idEvenement) {
        this.idEvenement = idEvenement;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public int getCapaciteEvenement() {
        return capaciteEvenement;
    }

    public void setCapaciteEvenement(int capaciteEvenement) {
        this.capaciteEvenement = capaciteEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public String getImageEvenement() {
        return imageEvenement;
    }

    public void setImageEvenement(String imageEvenement) {
        this.imageEvenement = imageEvenement;
    }

    public int getPrixEvenement() {
        return prixEvenement;
    }

    public void setPrixEvenement(int prixEvenement) {
        this.prixEvenement = prixEvenement;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Evenement{" + "idEvenement=" + idEvenement + ", nomEvenement=" + nomEvenement + ", capaciteEvenement=" + capaciteEvenement + ", descriptionEvenement=" + descriptionEvenement + ", imageEvenement=" + imageEvenement + ", prixEvenement=" + prixEvenement + ", dateEvenement=" + dateEvenement + ", idUser=" + idUser + '}';
    }
    
    
    
}
