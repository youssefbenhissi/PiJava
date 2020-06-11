/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;
import Util.MyDbConnection;
import Entities.Etablissement;
import Interfaces.IEtablissementService;
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
public class EtablissementService implements IEtablissementService<Etablissement>{
   
   private Connection con;
    private Statement ste;

   

    public EtablissementService() {
       con=MyDbConnection.getInstance().getConnexion();
    } 

    @Override
    public void ajouter(Etablissement e) throws SQLException {
         ste = con.createStatement();
        String requeteInsert = "INSERT INTO `pi`.`etablissement` (`id`, `numTel`, `adresse`, `nom`,`Image`) VALUES (NULL, '" + e.getNumTel()+ "', '" + e.getAdresse()+ "', '" + e.getNom()+ "', '" + e.getImage()+ "');";
        ste.executeUpdate(requeteInsert);
    }

    @Override
    public boolean delete(int id) throws SQLException {
        PreparedStatement pre = con.prepareStatement("DELETE FROM `pi`.`etablissement` where id =?");
        pre.setInt(1,id);
        pre.executeUpdate();
        int rowsDeleted = pre.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("A School was deleted successfully!");
        }
        return true;
    }

    @Override
    public void update(Etablissement e) throws SQLException {
     String sql = "UPDATE etablissement SET image=?, adresse=?, nom=?, numTel=? WHERE id=?";

        PreparedStatement statement = con.prepareStatement(sql);
        statement.setString(1, e.getImage());
        System.out.println("waaaaaaaaaa"+e.getImage());
        statement.setString(2, e.getAdresse());
        statement.setString(3,e.getNom() );
        statement.setInt(4,e.getNumTel());
        statement.setInt(5,e.getId());
        statement.executeUpdate();

        
        int rowsUpdated = statement.executeUpdate();
        if (rowsUpdated > 0) {
            System.out.println("An existing School was updated successfully!");
        }
       
    }

    @Override
    public List<Etablissement> readAll() throws SQLException {
        List<Etablissement> arr = new ArrayList<>();
        ste = con.createStatement();
        ResultSet rs = ste.executeQuery("select * from etablissement");
        while (rs.next()) {
            int id = rs.getInt(1);
            String image = rs.getString("image");
            String adresse = rs.getString("adresse");
            String nom = rs.getString("nom");
            int numTel = rs.getInt("numTel");
            Etablissement e = new Etablissement(id, numTel, adresse, image, image);
            arr.add(e);
        }
        return arr;
    }

    @Override
    public Etablissement FindEtablissement(int id) {
        Etablissement et = null;

        try {
            String select = "SELECT * FROM etablissement WHERE  id = '" + id + "' ";
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery(select);

            if (result.next()) {
                et= new Etablissement();
                et.setId(id);
                et.setNom(result.getString("Nom"));
                et.setAdresse(result.getString("Adresse"));
                et.setNumTel(result.getInt("Numtel"));
      
                et.setImage(result.getNString("image"));

                EtablissementService es = new EtablissementService();

            }
        } catch (SQLException e) {
            System.err.println("SQLException: " + e.getMessage());
            System.err.println("SQLSTATE: " + e.getSQLState());
            System.err.println("VnedorError: " + e.getErrorCode());
        }
        return et;
    }
    
}
 


