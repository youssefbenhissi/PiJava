/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

/**
 *
 * @author Iheb
 */
public class CategorieEvenement {
    private int idCtegorieEvenement;
    private String imageCat;
    private String descriptionCat;
    private String nomCategorieEvenement;

    public CategorieEvenement(int idCtegorieEvenement,String nomCategorieEvenement, String imageCat, String descriptionCat) {
        this.idCtegorieEvenement = idCtegorieEvenement;
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
