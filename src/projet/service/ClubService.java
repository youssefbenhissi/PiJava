/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
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
import javafx.scene.control.Button;
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
        String req = "SELECT c.id,c.nom,c.description,c.capacite,c.moyenneLike,cat.nomCategorie FROM club c,categorie_club cat WHERE c.categorie_id = cat.id";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();

            // System.out.println("Column Name of 1st column: "+rsmd.getColumnName(6));  
            //System.out.println("Column Type Name of 1st column: "+rsmd.getColumnTypeName(6)); 
            //System.out.println(rs.getInt(1));
            while (rs.next()) {
                Club c = new Club();
                c.setId(rs.getInt(1));
                c.setNom(rs.getString(2));
                c.setDescription(rs.getString(3));
                c.setCapacite(rs.getInt(4));
                c.setMoyenneLike(rs.getFloat(5));

                //System.out.println(rs.getString(5));
                c.setNomcategorie(rs.getString(6));
                //System.out.println(rs.getString(6));
                clubs.add(c);
                /*Document document=new Document();
                PdfWriter.getInstance(document, new FileOutputStream("C:\\Users\\youssef\\Desktop\\libYoussef\\youssef.pdf"));
                    document.open();
                    Paragraph p=new Paragraph(c.getNom());
                    document.add(p);
                    Paragraph pDescr=new Paragraph(c.getDescription());
                    document.add(pDescr);
                    document.close();*/
            }
        } catch (Exception ex) {
            Logger.getLogger(CategorieClubService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return clubs;
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
}
