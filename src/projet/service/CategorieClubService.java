/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.models.CategorieClub;
import projet.models.Club;
import projet.utils.DbConnection;

/**
 *
 * @author youssef
 */
public class CategorieClubService implements ICategorieClubService{
    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    /**
     *
     * @return
     */
    @Override
    public ArrayList<CategorieClub> selectAll() {
        ArrayList<CategorieClub> categories = new ArrayList<>();
        String req = "SELECT * FROM `categorie_club`";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            while (rs.next()) {
                CategorieClub c = new CategorieClub();
                c.setId(rs.getInt(1));
                c.setNomCategorie(rs.getString(2));
                categories.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return categories;
    }
    @Override
     public List<CategorieClub> rechercheCategories(String str) {
         List<CategorieClub> categories=new ArrayList<CategorieClub>();
        String sql = "SELECT * FROM categorie_club WHERE nomCategorie LIKE ? ";
        PreparedStatement statement;
        
        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1,"%" + str + "%");
            //statement.setString(2, "%" + str + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                CategorieClub c = new CategorieClub();
                c.setId(rs.getInt(1));
                c.setNomCategorie(rs.getString(2));
                categories.add(c);
            }
        } catch (SQLException ex) {
            
        }
        return categories;
    }
     @Override
    public void supprimer(int x) {
        String sql = "DELETE FROM categorie_club WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, x);

            statement.executeUpdate();

            System.out.println("Categorie Supprimer");

        } catch (SQLException ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Categorie non Supprimer");
    }
    @Override
    public void ajouterCategorie(CategorieClub c){
      String requete="INSERT INTO categorie_club (nomCategorie)"
                + " VALUES ('"+c.getNomCategorie()+"');";                              
        
        try {
           pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("categorie Ajoutee");
        } catch (SQLException ex) {
            System.out.println("aaaaaaa");
        }
     
     }
}
