/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;
import java.util.logging.Logger;
import javafx.scene.control.ComboBox;

/**
 *
 * @author HP
 */
public class livre {
    private int id;
    private String nom;
    private String description;
    private String auteur;
    private int nombredepage;
    private int id_categorie;
        private String libelle;
        public String nom_image;
      private File file;

    public livre(String nom, String photopath, String descriptio, String auteu, int nombredepage, int id_categorie) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String getNom_image() {
        return nom_image;
    }

    public File getFile() {
        return file;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public livre(String nom, String description, String auteur, int nombredepage, String libelle, String nom_image, File file) {
        this.nom = nom;
        this.description = description;
        this.auteur = auteur;
        this.nombredepage = nombredepage;
        this.libelle = libelle;
        this.nom_image = nom_image;
        this.file = file;
    }

    public livre(int id, String nom, String description, String auteur, int nombredepage, int id_categorie, String libelle, String nom_image, File file) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.auteur = auteur;
        this.nombredepage = nombredepage;
        this.id_categorie = id_categorie;
        this.libelle = libelle;
        this.nom_image = nom_image;
        this.file = file;
    }

    public livre(String nom, String descriptio, String auteu, int nombredepage, int id_categorie, String photopath) {

 this.nom = nom;
        this.description = descriptio;
        this.auteur = auteu;
        this.nombredepage = nombredepage;
        this.id_categorie = id_categorie;
        this.libelle = photopath;
      
    }
      

   
   

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getLibelle() {
        return libelle;
    }

   



    public livre(int id, String nom, String description, String auteur, int nombredepage, int id_categorie) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.auteur = auteur;
        this.nombredepage = nombredepage;
        this.id_categorie = id_categorie;
    }

    public livre(String no, String desc, String au, int libe, int id_categorie) {
 this.nom = no;
       
        this.description = desc;
        this.auteur = au;
        this.nombredepage = libe;
        this.id_categorie = id_categorie;
    }

    public livre() {
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public String getAuteur() {
        return auteur;
    }

    public int getNombredepage() {
        return nombredepage;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public void setNombredepage(int nombredepage) {
        this.nombredepage = nombredepage;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }

    
   

   

   
}
