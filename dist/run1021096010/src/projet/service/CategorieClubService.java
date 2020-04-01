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
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import org.controlsfx.control.PopOver;
import projet.models.CategorieClub;
import projet.utils.DbConnection;

/**
 *
 * @author youssef
 */
public class CategorieClubService implements ICategorieClubService {

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
        List<CategorieClub> categories = new ArrayList<CategorieClub>();
        String sql = "SELECT * FROM categorie_club WHERE nomCategorie LIKE ? ";
        PreparedStatement statement;

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + str + "%");
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
    public HashMap<String, Integer> getAllCategorie() {

        HashMap<String, Integer> mapCategorie = new HashMap<String, Integer>();

        String req = "SELECT * FROM categorie_club";

        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);
            CategorieClub categorie;
            while (res.next()) {
                categorie = new CategorieClub(res.getInt(1), res.getString(2));
                mapCategorie.put(categorie.getNomCategorie(), categorie.getId());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return mapCategorie;
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
            //Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void ajouterCategorie(CategorieClub c) {
        String requete = "INSERT INTO categorie_club (nomCategorie)"
                + " VALUES ('" + c.getNomCategorie() + "');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("categorie Ajoutee");
        } catch (SQLException ex) {
            System.out.println("aaaaaaa");
        }

    }

    public static PopOver popNotification(String notification) {
        Text notifcationText = new Text(notification);
        notifcationText.setWrappingWidth(230);
        notifcationText.setStyle("-fx-font-weight: bold");

        HBox hBox = new HBox(notifcationText);
        hBox.setPadding(new Insets(0, 5, 0, 5));

        PopOver popOver = new PopOver(hBox);
        popOver.setTitle("New Notification");

        return popOver;
    }

    public List<Integer> getState() {
        String req11 = "Select id from categorie_club";
        List<Integer> liste = new ArrayList<Integer>();
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req11);

            while (rs.next()) {

                liste.add(rs.getInt(1));

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public String getState1(int x) {
        String g = "";
        String req11 = "Select nomCategorie  From categorie_club where id=? ";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req11);

            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                g = rs.getString(1);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }

    public Integer getState12(int x) {
        int g = 0;
        String req11 = "Select id From club where categorie_id=? ";

        PreparedStatement ps;
        try {
            ps = connection.prepareStatement(req11);

            ps.setInt(1, x);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                g++;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return g;
    }

    @Override
    public boolean modifierCategorie(CategorieClub c) {
        String req = "UPDATE categorie_club SET nomCategorie= ? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, c.getNomCategorie());
            pst.setInt(2, c.getId());
            System.out.println(c.getId());
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
    /* CONTROLE DE SAISIE */
    //variable de controle de saisie
    private static Matcher matcher;
    private static final String chaineSimple_sanEspace_pattern = "^[A-Za-z]+$";
    private static Pattern chaineSimple_pattern__sanEspace_complie = Pattern.compile(chaineSimple_sanEspace_pattern);

    public static boolean validationChaineSimpleSansEspace(final String chaineSaisie) {
        matcher = chaineSimple_pattern__sanEspace_complie.matcher(chaineSaisie);
        return matcher.matches();
    }
}
