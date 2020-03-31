/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;
import java.util.List;
import projet.models.Parent;

/**
 *
 * @author user
 */
public interface IParent {
    public List<Parent> selectAllParent();
    public void supprimerParents(int x);
    public void ajouterParent(Parent p);
    public void modifierP(Parent p);
    
}
