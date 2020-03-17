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
public class CategorieClub {
    private int  id;
    private String nomCategorie;

    public CategorieClub() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomCategorie() {
        return nomCategorie;
    }

    public void setNomCategorie(String nomCategorie) {
        this.nomCategorie = nomCategorie;
    }

    @Override
    public String toString() {
        return "CategorieClub{" + "id=" + id + ", nomCategorie=" + nomCategorie + '}';
    }
    

    public CategorieClub(int id, String nomCategorie) {
        this.id = id;
        this.nomCategorie = nomCategorie;
    }
    
}
