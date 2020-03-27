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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import projet.models.CategorieClub;
import projet.models.Club;
import projet.utils.DbConnection;

/**
 *
 * @author youssef
 */
public class ClubService implements IClub {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    @Override
    public List<Club> selectAllClubs() {
        ArrayList<Club> clubs = new ArrayList<>();
        String req = "SELECT c.id,c.nom,c.description,c.capacite,c.moyenneLike,cat.nomCategorie,c.image,c.questionPr,c.questionDe,c.questionTr FROM club c,categorie_club cat WHERE c.categorie_id = cat.id";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            //System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(6)); 
            //System.out.println(rs.getInt(1));
            while (rs.next()) {
                Club c = new Club();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setCapacite(rs.getInt(4));
                c.setMoyenneLike(rs.getFloat(5));
                c.setNomcategorie(rs.getString(6));
                c.setPath(rs.getString(7));
                c.setQuestionPr(rs.getString(8));
                c.setQuestionDe(rs.getString(9));
                c.setQuestionTr(rs.getString(10));
                clubs.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clubs;
    }

    public void ajouterCategorie(Club c) {
        String requete = "INSERT INTO club (nom,description,capacite,image,nbrLike,nbrFoisLike,moyenneLike,categorie_id,questionPr,questionDe,questionTr)"
                + " VALUES ('" + c.getNom() + "','" + c.getDescription() + "','" + c.getCapacite() + "','" + c.getPath() + "','" + c.getNbrLike() + "','" + c.getNbrFoisLike() + "','" + c.getMoyenneLike() + "','" + c.getCategorie_id() + "','" + c.getQuestionPr() + "','" + c.getQuestionDe() + "','" + c.getQuestionTr() + "');";

        try {
            pst = connection.prepareStatement(requete);
            pst.executeUpdate(requete);
            System.out.println("club Ajoute");
        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public Club ClubLike(int x) {
        Club cc = new Club();
        String sql = "SELECT moyenneLike, nbrLike, nbrFoisLike FROM club WHERE id = ? ";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, x);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Club c = new Club();
                c.setMoyenneLike(rs.getFloat(1));
                c.setNbrLike(rs.getInt(2));
                c.setNbrFoisLike(rs.getInt(3));
                cc = c;

            }
        } catch (SQLException ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cc;
    }

    @Override
    public void supprimerClub(int x) {
        String sql = "DELETE FROM club WHERE id = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, x);
            statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Club> search(String libelle) {

        ArrayList<Club> clubs = new ArrayList<>();
        String req = "SELECT c.* FROM club c WHERE  c.nom LIKE '%" + libelle + "%' ORDER BY(c.id) DESC";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {
                Club c = new Club();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setCapacite(rs.getInt(4));
                c.setMoyenneLike(rs.getFloat(8));
                // Club sc = new Club(res.getInt(1),res.getString(2),res.getString(3),res.getInt(4),res.getFloat(8));
                //System.out.println(sc);
                clubs.add(c);

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return clubs;
    }

    /* CONTROLE DE SAISIE */
    //variable de controle de saisie
    private static Matcher matcher;
    private static final String email_pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern email_pattern_compile = Pattern.compile(email_pattern);

    public static boolean validationEmail(final String emailSaisie) {
        matcher = email_pattern_compile.matcher(emailSaisie);
        return matcher.matches();
    }
    private static final String chaineSimple_avecEspace_pattern = "^[A-Z a-z 0-9]+$";
    private static Pattern chaineSimple_pattern_avecEspace_complie = Pattern.compile(chaineSimple_avecEspace_pattern);

    public static boolean validationChaineSimpleAvecEspace(final String chaineSaisie) {
        matcher = chaineSimple_pattern_avecEspace_complie.matcher(chaineSaisie);
        return matcher.matches();
    }

    private static final String chaineSimple_nombre_pattern = "^[0-9]+$";
    private static Pattern chaineSimple_pattern__nombre_complie = Pattern.compile(chaineSimple_nombre_pattern);

    public static boolean validationChaineSimpleNombre(final String chaineSaisie) {
        matcher = chaineSimple_pattern__nombre_complie.matcher(chaineSaisie);
        return matcher.matches();
    }

    @Override
    public List<Club> retournerListeDesClubsSupprission(int id) {
        List<Club> categories = new ArrayList<Club>();
        String sql = "SELECT c.id,c.nom,c.description,c.capacite,c.moyenneLike,c.image FROM club c WHERE c.categorie_id LIKE ? ";
        PreparedStatement statement;

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + id + "%");
            //statement.setString(2, "%" + str + "%");
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Club c = new Club();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setCapacite(rs.getInt(4));
                c.setMoyenneLike(rs.getFloat(5));
                // c.setNomcategorie(rs.getString(6));
                c.setPath(rs.getString(6));
                categories.add(c);
            }
        } catch (SQLException ex) {

        }
        return categories;
    }

    //@Override
    public List<Club> getListProduitsFilter(String categorie, String triNom) {

        ObservableList<Club> listproduits = FXCollections.observableArrayList();
        String req = null;

        if (categorie == null) {
            if (triNom == null) {
                req = "SELECT c.id,c.nom,c.description,c.capacite,c.moyenneLike,c.image,c.nbrLike,c.nbrFoisLike,cat.nomCategorie FROM club c,categorie_club cat WHERE c.categorie_id = cat.id ORDER BY c.id DESC ";
                System.out.println("c est le cas");
            }

            if (triNom == "nom_asc") {
                req = "SELECT c.id,c.nom,c.description,c.capacite,c.moyenneLike,c.image,c.nbrLike,c.nbrFoisLike,cat.nomCategorie  FROM club c,categorie_club cat WHERE c.categorie_id = cat.id ORDER BY(c.nom) ASC";
            }
            if (triNom == "nom_desc") {
                req = "SELECT c.id,c.nom,c.description,c.capacite,c.moyenneLike,c.image,c.nbrLike,c.nbrFoisLike,cat.nomCategorie  FROM club c,categorie_club cat WHERE c.categorie_id = cat.id ORDER BY(c.nom) DESC";
            }
            if (triNom == "etoi_asc") {
                req = "SELECT c.id,c.nom,c.description,c.capacite,c.moyenneLike,c.image,c.nbrLike,c.nbrFoisLike,cat.nomCategorie  FROM club c,categorie_club cat WHERE c.categorie_id = cat.id ORDER BY(c.moyenneLike) ASC";
            }
            if (triNom == "etoi_desc") {
                req = "SELECT c.id,c.nom,c.description,c.capacite,c.moyenneLike,c.image,c.nbrLike,c.nbrFoisLike,cat.nomCategorie  FROM club c,categorie_club cat WHERE c.categorie_id = cat.id ORDER BY(c.moyenneLike) DESC";
            }

        } else {
            if (triNom == null) {
                System.out.println("lehnnnnna");
                String sql = "SELECT c.id,c.nom FROM club c  WHERE c.categorie_id = '" + categorie + "' ORDER BY c.id DESC ";
            }

        }
        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req);

            while (rs.next()) {
                Club c = new Club();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setCapacite(rs.getInt(4));
                c.setMoyenneLike(rs.getFloat(5));
                // c.setNomcategorie(rs.getString(6));
                c.setPath(rs.getString(6));
                c.setNbrLike(rs.getInt(7));
                c.setNbrFoisLike(rs.getInt(8));
                c.setNomcategorie(rs.getString(9));
                listproduits.add(c);
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listproduits;
    }

    @Override
    public boolean modifierLike(Club c) {
        String req = "UPDATE club SET moyenneLike= ?,nbrFoisLike= ?,nbrLike = ? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            // System.out.println(c.getId());
            pst.setFloat(1, c.getMoyenneLike());
            pst.setInt(2, c.getNbrFoisLike());
            pst.setInt(3, c.getNbrLike());
            pst.setInt(4, c.getId());

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

    @Override
    public List<String> retournerListeImages() {
        ArrayList<String> listetImage = new ArrayList<>();
        String req = "SELECT c.image FROM club c ";

        try {
            statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(req);
            while (rs.next()) {
                listetImage.add(rs.getString(1));

            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return listetImage;
    }

    @Override
    public boolean modifierClub(Club c) {
        String req = "UPDATE club SET nom = ?,description = ? , capacite = ? , image = ?  , questionPr = ? , questionDe = ? , questionTr = ? WHERE id= ?";
        try {
            pst = connection.prepareStatement(req);
            pst.setString(1, c.getNom());
            pst.setString(2, c.getDescription());
            pst.setInt(3, c.getCapacite());
            pst.setString(4, c.getPath());
          //  pst.setInt(5, c.getCategorie_id());
            pst.setString(5, c.getQuestionPr());
            pst.setString(6, c.getQuestionDe());
            pst.setString(7, c.getQuestionTr());
            pst.setInt(8, c.getId());
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
