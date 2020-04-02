/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.models.CategorieEvenement;
import projet.models.Evenement;
import projet.utils.DbConnection;

/**
 *
 * @author Iheb
 */
public class EvenementService implements IEvenement {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    @Override
    public List<Evenement> selectAllEvenement() {
        ArrayList<Evenement> evenements = new ArrayList<>();
        String req = "SELECT e.id,e.nomE,e.capaciteE,e.description,e.imgE,e.prixE,c.nomCategorieEvenement,e.dateD FROM evenement e,categorie_evenement c where e.categorieEvenement_id = c.id";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(6)); 
            //System.out.println(rs.getInt(1));
            while (rs.next()) {
                Evenement c = new Evenement();
                c.setIdEvenement(rs.getInt(1));
                c.setNomEvenement(rs.getString(2));
                c.setCapaciteEvenement(rs.getInt(3));
                c.setDescriptionEvenement(rs.getString(4));
                c.setImageEvenement(rs.getString(5));
                c.setPrixEvenement(rs.getInt(6));
                c.setNomCategorie(rs.getString(7));
                c.setDateEvenement(rs.getDate(8));
                evenements.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements;
    }

    @Override
    public void supprimerEvenement(int x) {
        String sql = "DELETE FROM evenement WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, x);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void ajouterEvenement(Evenement c) {
        String requete = "INSERT INTO Evenement (nomE,capaciteE,description,imgE,prixE,categorieEvenement_id)"
                + " VALUES ( '" + c.getNomEvenement() + "','" + c.getCapaciteEvenement() + "', '" + c.getDescriptionEvenement() + "', '" + c.getImageEvenement() + "', '" + c.getPrixEvenement() + "', '" + c.getIdCatgeorie() + "' )";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("Evenement Ajoute");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public boolean modifierEvenement(Evenement c) {
        String req = "UPDATE Evenement SET nomE = ?, capaciteE= ? , description = ? , imgE = ?  , prixE = ? ,categorieEvenement_id = ? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, c.getNomEvenement());
            pst.setInt(2, c.getCapaciteEvenement());
            pst.setString(3, c.getDescriptionEvenement());
            pst.setString(4, c.getImageEvenement());
            pst.setInt(5, c.getPrixEvenement());
            pst.setInt(6, c.getIdCatgeorie());
            pst.setInt(7, c.getIdEvenement());

            int res = pst.executeUpdate();

            if (res > 0) {
                return true;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        return false;
    }

}
