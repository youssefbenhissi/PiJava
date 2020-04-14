/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Asus
 */
public class Etablissement {
    private int id,numTel;
    private String adresse,nom,image;

    public Etablissement(int id, int numTel, String adresse, String nom, String image) {
        this.id = id;
        this.numTel = numTel;
        this.adresse = adresse;
        this.nom = nom;
        this.image = image;
    }

    public Etablissement(int numTel, String adresse, String nom, String image) {
        this.numTel = numTel;
        this.adresse = adresse;
        this.nom = nom;
        this.image = image;
    }

    public Etablissement() {
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumTel() {
        return numTel;
    }

    public void setNumTel(int numTel) {
        this.numTel = numTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Etablissement{" + "id=" + id + ", numTel=" + numTel + ", adresse=" + adresse + ", nom=" + nom + ", image=" + image + '}';
    }
    
    
    
         
}
