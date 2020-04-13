/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Iservice;

import models.categorier;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author HP
 */
public interface IcategorierService {
    public void addCtegorier(categorier c) throws SQLException;
     public void deleteCtegorier(int id) ;
      public void updateCtegorier(categorier c) throws SQLException;
       public List<categorier> getAll();  
    List<categorier> rechercheCategories(String str); 
}
