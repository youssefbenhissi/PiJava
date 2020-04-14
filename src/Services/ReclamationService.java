/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Util.MyDbConnection;
import Entities.Reclamation;
import Interfaces.IReclamationService;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Asus
 */
public class ReclamationService implements IReclamationService<Reclamation>{
    private Connection con;
    private Statement ste;

    public ReclamationService() {
    con=MyDbConnection.getInstance().getConnexion();
    }

    @Override
    public void ajouter(Reclamation r) throws SQLException {
        ste = con.createStatement();
        String requeteInsert = "INSERT INTO `projetintegration2020`.`reclamation` (`id`, `etablissement_id`, `user_id`, `sujet`,`description`,`dateRec`) VALUES (NULL, '" + r.getEtablissement_id()+ "', '" + r.getUser_id()+ "', '" + r.getSujet()+ "', '" + r.getDescription()+ "', '" + r.getDateRec()+ "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `projetintegration2020`.`reclamation` where id =?");
        pre.setInt(1,id);
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A Complain was deleted successfully!");
        }
        return true;
    }

    @Override
    public boolean update(String sujet, String description,String dateRec, int id) throws SQLException {
        String sql = "UPDATE reclamation SET sujet=?, description=?, dateRec=? WHERE id=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, sujet);
        statement.setString(2, description);
        statement.setString(3,dateRec );
        statement.setInt(4, id);

        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing Complain was updated successfully!");
        }
        return true;
    }

    @Override
    public List<Reclamation> readAll() throws SQLException {
        List<Reclamation> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from reclamation r INNER JOIN etablissement e on r.etablissement_id=e.id");
        while (rs.next()) {
            int id = rs.getInt(1);
            int etablissement_id = rs.getInt("etablissement_id");
            int user_id=rs.getInt("user_id");
            String sujet = rs.getString("sujet");
            String description = rs.getString("description");
            String dateRec = rs.getString("dateRec");
            Reclamation r = new Reclamation(id, etablissement_id, user_id, sujet, description, dateRec);
            arr.add(r);
        }
        return arr;
    }
    
}
