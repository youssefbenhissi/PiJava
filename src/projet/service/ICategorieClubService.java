/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import projet.models.CategorieClub;
/**
 *
 * @author youssef
 */
public interface ICategorieClubService {
    public ArrayList<CategorieClub> selectAll();
    public void supprimer(int x);
    List<CategorieClub> rechercheCategories(String str); 
    public void ajouterCategorie(CategorieClub c);
    public HashMap<String, Integer> getAllCategorie();
}
