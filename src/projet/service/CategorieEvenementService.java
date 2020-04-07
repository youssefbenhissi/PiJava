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
import javafx.scene.control.Button;
import projet.models.CategorieEvenement;
import projet.models.Evenement;
import projet.utils.DbConnection;

/**
 *
 * @author Iheb
 */
public class CategorieEvenementService implements ICategorieEvenement {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    @Override
    public List<CategorieEvenement> selectAllCategorieEvenement() {
        ArrayList<CategorieEvenement> CategorieEvenements = new ArrayList<>();
        String req = "SELECT c.id,c.nomCategorieEvenement,c.image,c.description FROM categorie_evenement c";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(6)); 
            //System.out.println(rs.getInt(1));
            while (rs.next()) {
                CategorieEvenement c = new CategorieEvenement();
                c.setIdCtegorieEvenement(rs.getInt(1));
                c.setNomCategorieEvenement(rs.getString(2));
                c.setImageCat(rs.getString(3));
                c.setDescriptionCat(rs.getString(4));
                c.setBtn_delete(new Button("Supprimer"));
                CategorieEvenements.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return CategorieEvenements;
    }
    @Override
    public boolean supprimerCategorieEvenement(int x) {
        String sql = "DELETE FROM categorie_evenement WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, x);
            statement.executeUpdate();
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(CategorieEvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    @Override
    public boolean ajouterCategorieEvenement(CategorieEvenement c) {
        String requete = "INSERT INTO categorie_evenement (nomCategorieEvenement,image,description)"
                + " VALUES ( '" + c.getNomCategorieEvenement()+ "','" + c.getImageCat()+ "', '" + c.getDescriptionCat()+ "' );";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
            return true;
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return false;
    }
    @Override
    public boolean modifierCategorieEvenement(CategorieEvenement c) {
        System.out.println(c.getNomCategorieEvenement());
        String req = "UPDATE categorie_evenement SET nomCategorieEvenement = ?, image= ? , description = ?   WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, c.getNomCategorieEvenement());
            pst.setString(2, c.getImageCat());
            pst.setString(3, c.getDescriptionCat());
            pst.setInt(4, c.getIdCtegorieEvenement());
            
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
