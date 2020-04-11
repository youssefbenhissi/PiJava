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

/**
 *
 * @author user
 */
public class Eleve {

    private int id;

    private String nom;
    private String prenom;
    private String Email;
    private String image;
    private String Sexe;
    private int Age;
    private String nomParent;
    private int parent_id;

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
                EleveService service = new EleveService();
                service.supprimerEleve(id);
                backcontroller gestionInscription = new backcontroller();
                gestionInscription.myObservableList.remove(this);
            }
        });
    }

    public int getParent_id() {
        return parent_id;
    }

    public void setParent_id(int parent_id) {
        this.parent_id = parent_id;
    }

    public String getNomParent() {
        return nomParent;
    }

    public void setNomParent(String nomParent) {
        this.nomParent = nomParent;
    }

    public Eleve() {
    }

    public int getId() {
        return id;
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

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getSexe() {
        return Sexe;
    }

    public void setSexe(String Sexe) {
        this.Sexe = Sexe;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }

    public Eleve(int id, String nom, String prenom, String Email, String image, String Sexe, int Age) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.image = image;
        this.Sexe = Sexe;
        this.Age = Age;
        //    this.nomParent = nomParent;
    }

    public Eleve(String nom, String prenom) {
        this.nom = nom;
        this.prenom = prenom;
    }

    @Override
    public String toString() {
        return "Eleve{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", Email=" + Email + ", image=" + image + ", Sexe=" + Sexe + ", Age=" + Age + '}';
    }

}
