/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.util.List;
import projet.models.Eleve;

import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import projet.utils.DbConnection;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class EleveService implements IEleve {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    @Override
    public List<Eleve> selectAllEleve() {
        ArrayList<Eleve> listeEleve = new ArrayList<>();
        String req = "SELECT e.nom,e.prenom,e.sexe,e.image,e.age,e.email,p.nom FROM eleve e,parents p where e.parents_id=p.id";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                Eleve e = new Eleve();
                e.setNom(rs.getString(1));
                e.setPrenom(rs.getString(2));

                e.setSexe(rs.getString(3));
                e.setImage(rs.getString(4));
                e.setAge(rs.getInt(5));;
                e.setEmail(rs.getString(6));
                e.setNomParent(rs.getString(7));
                listeEleve.add(e);
            }
        } catch (Exception ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listeEleve;
    }

    @Override
    public void supprimerEleve(int x) {
        String sql = "DELETE FROM eleve WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, x);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(EleveService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterEleve(Eleve e) {
        String requete = "INSERT INTO eleve (nom,prenom,sexe,image,age,email)"
                + " VALUES ('" + e.getNom() + "','" + e.getPrenom() + "','" + e.getSexe() + "','" + e.getImage() + "','" + e.getAge() + "','" + e.getEmail() + "');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("eleve Ajoute");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void modifierE(Eleve e) {
        String req = "UPDATE eleve SET nom= ?,prenom= ?,image= ?,sexe= ?,Age= ?,email= ?,parents_id=? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, e.getNom());
            pst.setString(2, e.getPrenom());
            pst.setString(3, e.getImage());
            pst.setString(4, e.getSexe());
            pst.setInt(5, e.getAge());
            pst.setString(6, e.getEmail());
            pst.setInt(7, e.getParent_id());
            pst.setInt(8, e.getId());

            pst.executeUpdate();

        } catch (SQLException e1) {
            System.out.println(e1);
        }

    }

}
