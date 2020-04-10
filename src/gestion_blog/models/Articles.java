/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.models;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author geek alaa
 */
public class Articles {
    private String titre,image,description,contenu,date;
    private int id,vues,type,cat_id;
    private List<Tags> listags = new ArrayList<Tags>();;

    public Articles(String titre, String image, String description, String contenu, String date, int id, int vues, int type, int cat_id ,List listt) {
        this.titre = titre;
        this.image = image;
        this.description = description;
        this.contenu = contenu;
        this.date = date;
        this.id = id;
        this.vues = vues;
        this.type = type;
        this.cat_id = cat_id;
        this.listags = listt;
    }

    public Articles() {
    }

    public Articles(int id) {
        this.id = id;
    }
    
    
    

    public String getTitre() {
        return titre;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public String getContenu() {
        return contenu;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public int getVues() {
        return vues;
    }

    public int getType() {
        return type;
    }

    public int getCat_id() {
        return cat_id;
    }

    public List<Tags> getListags() {
        return listags;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVues(int vues) {
        this.vues = vues;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public void setListags(List<Tags> listags) {
        this.listags = listags;
    }

    @Override
    public String toString() {
        return "Articles{" + "titre=" + titre + ", image=" + image + ", description=" + description + ", contenu=" + contenu + ", date=" + date + ", id=" + id + ", vues=" + vues + ", type=" + type + ", cat_id=" + cat_id + ", listags=" + listags.toString() + '}';
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
        final Articles other = (Articles) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
   
    
    
 
    
}
