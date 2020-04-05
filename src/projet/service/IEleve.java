/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.util.List;
import projet.models.Eleve;

/**
 *
 * @author user
 */
public interface IEleve {
    public List<Eleve> selectAllEleve();
     public void supprimerEleve(int x);
     public boolean ajouterEleve(Eleve e);
     public void modifierE(Eleve e);
}
