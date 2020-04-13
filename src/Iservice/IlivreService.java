/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import models.categorier;
import models.livre;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IlivreService {
        /*public void addLivre(livre l) throws SQLException;
             public void deleteLivre(int id) ;
          public List<livre> getAll();
              List<livre> rechercheCategories(String str); */

           public List<livre> getAll();
                   public void addLivre(livre l) throws SQLException;
              List<livre> rechercheCategories(String str); 
                           public void deleteLivre(int id) ;



}
