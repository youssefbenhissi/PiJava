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
import projet.controller.EvenementBackController;
import projet.service.CategorieEvenementService;

/**
 *
 * @author Iheb
 */
public class CategorieEvenement {

    private int idCtegorieEvenement;
    private String imageCat;
    private String descriptionCat;
    private String nomCategorieEvenement;
    private Button btn_delete;

    public Button getBtn_delete() {
        return btn_delete;
    }

    public void setBtn_delete(Button btn_delete) {
        this.btn_delete = btn_delete;
        this.btn_delete.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Vous voulez vraiment supprimer cette categorie?");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
                CategorieEvenementService service = new CategorieEvenementService();
                if (service.supprimerCategorieEvenement(idCtegorieEvenement)) {
                    EvenementBackController gestionInscription = new EvenementBackController();
                    gestionInscription.ObservableList.remove(this);
                }
            } else {

            }
        });

    }

    public CategorieEvenement(int idCtegorieEvenement, String nomCategorieEvenement, String imageCat, String descriptionCat) {
        this.idCtegorieEvenement = idCtegorieEvenement;
        this.imageCat = imageCat;
        this.descriptionCat = descriptionCat;
        this.nomCategorieEvenement = nomCategorieEvenement;
    }

    public CategorieEvenement(String nomCategorieEvenement, String imageCat, String descriptionCat) {
        this.imageCat = imageCat;
        this.descriptionCat = descriptionCat;
        this.nomCategorieEvenement = nomCategorieEvenement;
    }

    public CategorieEvenement() {
    }

    public int getIdCtegorieEvenement() {
        return idCtegorieEvenement;
    }

    public void setIdCtegorieEvenement(int idCtegorieEvenement) {
        this.idCtegorieEvenement = idCtegorieEvenement;
    }

    public String getImageCat() {
        return imageCat;
    }

    public void setImageCat(String imageCat) {
        this.imageCat = imageCat;
    }

    public String getDescriptionCat() {
        return descriptionCat;
    }

    public void setDescriptionCat(String descriptionCat) {
        this.descriptionCat = descriptionCat;
    }

    public String getNomCategorieEvenement() {
        return nomCategorieEvenement;
    }

    public void setNomCategorieEvenement(String nomCategorieEvenement) {
        this.nomCategorieEvenement = nomCategorieEvenement;
    }

    @Override
    public String toString() {
        return "CategorieEvenement{" + "idCtegorieEvenement=" + idCtegorieEvenement + ", imageCat=" + imageCat + ", descriptionCat=" + descriptionCat + ", nomCategorieEvenement=" + nomCategorieEvenement + '}';
    }

}
