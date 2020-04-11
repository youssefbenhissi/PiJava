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
import projet.controller.backcontroller;
import projet.service.EleveService;
import projet.service.ParentService;

/**
 *
 * @author user
 */
public class Parent {

    private int id;

    private String nom;
    private String prenom;
    private int numTelephone;
    private String image;
    private Button btn_delete;

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
                ParentService service = new ParentService();
                service.supprimerParents(id);
                backcontroller gestionInscription = new backcontroller();
                gestionInscription.myObservableListP.remove(this);
            }
        });
    }

    public Parent(int id, String nom, String prenom, int numTelephone, String image) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.numTelephone = numTelephone;
        this.image = image;
    }

    @Override
    public String toString() {
        return "Parent{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", numTelephone=" + numTelephone + ", image=" + image + '}';
    }

    public Parent() {
    }

    public Parent(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    public int getId() {
        return id;
    }

    public int getNumTelephone() {
        return numTelephone;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setNumTelephone(int numTelephone) {
        this.numTelephone = numTelephone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
