/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.models;

import java.util.Objects;

/**
 *
 * @author geek alaa
 */
public class Commentaires {
 
    private String email,contenu;
    private int id_article;

    public Commentaires(String email, String contenu, int id_article) {
        this.email = email;
        this.contenu = contenu;
        this.id_article = id_article;
    }

    public Commentaires(String email, int id_article) {
        this.email = email;
        this.id_article = id_article;
    }

    public Commentaires(String email, String contenu) {
        this.email = email;
        this.contenu = contenu;
    }
    
    

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setId_article(int id_article) {
        this.id_article = id_article;
    }

    public String getEmail() {
        return email;
    }

    public String getContenu() {
        return contenu;
    }

    public int getId_article() {
        return id_article;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Commentaires other = (Commentaires) obj;
        if (this.id_article != other.id_article) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Commentaires{" + "email=" + email + ", contenu=" + contenu + ", id_article=" + id_article + '}';
    }
    
    
}
