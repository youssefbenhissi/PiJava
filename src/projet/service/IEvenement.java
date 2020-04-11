/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.util.HashMap;
import java.util.List;
import projet.models.Evenement;

/**
 *
 * @author Iheb
 */
public interface IEvenement {
    public List<Evenement> selectAllEvenement();
    public boolean supprimerEvenement(int x);
    public boolean ajouterEvenement(Evenement c) ;
    public boolean modifierEvenement(Evenement c);
    public HashMap<String, Integer> getAllCategorie();
  
}
