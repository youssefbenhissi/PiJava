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
public class Reclamation {
    private int id,etablissement_id,user_id;
    private String sujet,description,dateRec;

    public Reclamation(int id, int etablissement_id, int user_id, String sujet, String description, String dateRec) {
        this.id = id;
        this.etablissement_id = etablissement_id;
        this.user_id = user_id;
        this.sujet = sujet;
        this.description = description;
        this.dateRec = dateRec;
    }

    public Reclamation(int etablissement_id, int user_id, String sujet, String description, String dateRec) {
        this.etablissement_id = etablissement_id;
        this.user_id = user_id;
        this.sujet = sujet;
        this.description = description;
        this.dateRec = dateRec;
    }
    

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDateRec() {
        return dateRec;
    }

    public void setDateRec(String dateRec) {
        this.dateRec = dateRec;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtablissement_id() {
        return etablissement_id;
    }

    public void setEtablissement_id(int etablissement_id) {
        this.etablissement_id = etablissement_id;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", etablissement_id=" + etablissement_id + ", user_id=" + user_id + ", sujet=" + sujet + ", description=" + description + ", dateRec=" + dateRec + '}';
    }
    
    
}
