/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;
import java.sql.SQLException;
import java.util.List;


/**
 *
 * @author Asus
 */
public interface IReclamationService<R> {
     void ajouter(R r) throws SQLException;
    boolean delete(int id) throws SQLException;
    boolean update(String sujet,String description,String dateRec,int id) throws SQLException;
    List<R> readAll() throws SQLException;
}
