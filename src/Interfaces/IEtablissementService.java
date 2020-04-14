/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Entities.Etablissement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author Asus
 */
public interface IEtablissementService<E> {
    void ajouter(E e) throws SQLException;
    boolean delete(int id) throws SQLException;
    void update(E e) throws SQLException;
    List<E> readAll() throws SQLException;
    public Etablissement FindEtablissement(int id);
}
