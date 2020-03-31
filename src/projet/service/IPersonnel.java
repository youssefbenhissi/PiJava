/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;
import java.util.List;
import projet.models.Personnel;
import java.util.Date;
/**
 *
 * @author user
 */
public interface IPersonnel {
     public List<Personnel> selectAllPersonnel();
     public void supprimerPersonnel(int x);
     public void ajouterPersonnel(Personnel pe);
      public void modifierPe(Personnel pe);
    
}
