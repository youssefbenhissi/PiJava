/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.util.List;
import projet.models.CategorieEvenement;

/**
 *
 * @author Iheb
 */
public interface ICategorieEvenement {
     public List<CategorieEvenement> selectAllCategorieEvenement();
     public boolean supprimerCategorieEvenement(int x) ;
      public boolean ajouterCategorieEvenement(CategorieEvenement c);
      public boolean modifierCategorieEvenement(CategorieEvenement c);
    
}
