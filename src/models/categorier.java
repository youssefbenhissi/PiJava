/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.File;

/**
 *
 * @author HP
 */
public class categorier {
    public int id;
    public String libelle;
    public String description;
     public String nom_image;
      private File file;

    public categorier(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public categorier(String libelle, String description) {
        this.libelle = libelle;
        this.description = description;
    }

    public categorier(int id, String libelle, String description, File file) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.file = file;
    }

    
    public categorier(int id, String libelle, String description, String nom_image) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.nom_image = nom_image;
    }

    public categorier(int id, String libelle, String description) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
    }

    public categorier() {
    }

    public categorier(String libe, String desc, String photopath) {

this.nom_image = desc;
        this.libelle = libe;
        this.description = desc;
    }

    public int getId() {
        return id;
    }

    public categorier(String libelle, String description, File file) {
        this.libelle = libelle;
        this.description = description;
        this.file = file;
    }

    
    public String getLibelle() {
        return libelle;
    }

    public String getDescription() {
        return description;
    }

    public String getNom_image() {
        return nom_image;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNom_image(String nom_image) {
        this.nom_image = nom_image;
    }
    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return file;
    }


    
}
