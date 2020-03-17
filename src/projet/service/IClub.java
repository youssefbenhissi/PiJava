/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.util.List;
import projet.models.Club;

/**
 *
 * @author youssef
 */
public interface IClub {
        public List<Club> selectAllClubs();
        public void supprimerClub(int x);
        public List<Club> search(String libelle);
}
