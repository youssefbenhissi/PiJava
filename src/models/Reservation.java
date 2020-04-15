/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author HP
 */
public class Reservation {
    public int id;
    public String nom;
    public String Reponse;
    public int id_livre;

    public Reservation(int id, String nom, String Reponse, int id_livre) {
        this.id = id;
        this.nom = nom;
        this.Reponse = Reponse;
        this.id_livre = id_livre;
    }

    public Reservation( String nom, int id_livre) {
       
        this.nom = nom;
        this.id_livre = id_livre;
    }
    

    public Reservation(int id, String nom, int id_livre) {
        this.id = id;
        this.nom = nom;
        this.id_livre = id_livre;
    }

   

    public int getId() {
        return id;
    }

    public String getReponse() {
        return Reponse;
    }

    public void setReponse(String Reponse) {
        this.Reponse = Reponse;
    }

    public String getNom() {
        return nom;
    }

    public int getId_livre() {
        return id_livre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setId_livre(int id_livre) {
        this.id_livre = id_livre;
    }
    
    
}
