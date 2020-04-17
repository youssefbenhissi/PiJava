/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;
import java.util.List;
import projet.models.Parent;
import projet.models.Utilisateur;

/**
 *
 * @author user
 */
public interface IParent {
    public List<Parent> selectAllParent();
    public void supprimerParents(int x);
    public boolean ajouterParent(Parent p);
    public void modifierP(Utilisateur p);
    
}
