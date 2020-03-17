/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

/**
 *
 * @author youssef
 */
public class Club {
    private int id;
    private String nom;
    private String description;
    private int capacite;
    private String path;
    private  float moyenneLike;
    private String nomcategorie;

    public Club() {
    }

    public Club(int id, String nom, String description, int capacite, String path, float moyenneLike) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.capacite = capacite;
        this.path = path;
        this.moyenneLike = moyenneLike;
    }
       public Club(int id, String nom, String description, int capacite ,float moyenneLike) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.capacite = capacite;
        this.moyenneLike = moyenneLike;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomcategorie() {
        return nomcategorie;
    }

    public void setNomcategorie(String nomcategorie) {
        this.nomcategorie = nomcategorie;
    }

    
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCapacite() {
        return capacite;
    }

    public void setCapacite(int capacite) {
        this.capacite = capacite;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getMoyenneLike() {
        return moyenneLike;
    }

    public void setMoyenneLike(float moyenneLike) {
        this.moyenneLike = moyenneLike;
    }
    
}
