/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;
import java.util.List;
import javafx.collections.ObservableList;
import projet.models.Parent;
import projet.models.Utilisateur;

/**
 *
 * @author user
 */
public interface IParent {
    public ObservableList<Utilisateur> selectAllParent();
    public void supprimerParents(int x);
    public boolean ajouterParent(Utilisateur p);
    public void modifierP(Utilisateur p);
    
}
