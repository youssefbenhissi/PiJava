/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.service;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import jxl.write.Label;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import projet.models.CategorieClub;
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
                c.setBtn_delete(new Button("Supprimer"));
                evenements.add(c);
            }
        } catch (Exception ex) {
            Logger.getLogger(EvenementService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return evenements;
    }

    @Override
    public boolean supprimerEvenement(int x) {
        String sql = "DELETE FROM evenement WHERE id = ? ";
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
    public boolean ajouterEvenement(Evenement c) {
        System.out.println(c.getIdCatgeorie());
        String requete = "INSERT INTO Evenement (nomE,capaciteE,description,imgE,prixE,categorieEvenement_id,dateD)"
                + " VALUES ( '" + c.getNomEvenement() + "','" + c.getCapaciteEvenement() + "', '" + c.getDescriptionEvenement() + "', '" + c.getImageEvenement() + "', '" + c.getPrixEvenement() + "', '" + c.getIdCatgeorie() + "', '" +  c.getDateEvenement()+"' )";

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
            //System.out.println(c.getIdCatgeorie());
            int res = pst.executeUpdate();

            if (res > 0) {
                System.out.println(c.getIdCatgeorie());
                return true;
            }
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
            System.out.println(e1);
        }
        return false;
    }

    @Override
    public HashMap<String, Integer> getAllCategorie() {

        HashMap<String, Integer> mapCategorie = new HashMap<String, Integer>();

        String req = "SELECT id,nomCategorieEvenement FROM categorie_evenement";

        try {
            statement = connection.createStatement();
            ResultSet res = statement.executeQuery(req);
            CategorieEvenement c = new CategorieEvenement();
            while (res.next()) {
                c.setIdCtegorieEvenement(res.getInt(1));
                c.setNomCategorieEvenement(res.getString(2));
                mapCategorie.put(c.getNomCategorieEvenement(), c.getIdCtegorieEvenement());
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            System.out.println("aaaa");
            System.out.println(e);
        }

        return mapCategorie;
    }

    public boolean exportXLS() throws WriteException {
        try {

            ObservableList<Evenement> list = FXCollections.observableArrayList(selectAllEvenement());

            File file = new File("C:\\Users\\youssef\\Desktop\\categorie.xls");
            WritableWorkbook myexcel = Workbook.createWorkbook(file);
            WritableSheet mysheet = myexcel.createSheet("categorie", 0);
           // Label id = new Label(0, 0, "id");
            Label libelle = new Label(1, 0, "nom");
            Label capacite = new Label(2, 0, "capacite");
            Label description = new Label(3, 0, "description");
            Label prix = new Label(4, 0, "prix");
            Label date = new Label(5, 0, "date");

            //mysheet.addCell(id);
            mysheet.addCell(libelle);
            mysheet.addCell(capacite);
            mysheet.addCell(description);
            mysheet.addCell(prix);
            mysheet.addCell(date);

            int i = 1;
            for (Evenement c : list) {

              //  id = new Label(0, i, String.valueOf(c.getIdEvenement()));
                libelle = new Label(1, i, c.getNomEvenement());
                capacite = new Label(2, i, Integer.toString(c.getCapaciteEvenement()));
                description = new Label(3, i, c.getDescriptionEvenement());
                prix = new Label(4, i, Integer.toString(c.getPrixEvenement()));
                Date d;
                d = (Date) c.getDateEvenement();
                DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                String strDate = dateFormat.format(d);
                date= new Label(5, i, strDate);
                //mysheet.addCell(id);
                mysheet.addCell(libelle);
                mysheet.addCell(capacite);
                mysheet.addCell(description);
                mysheet.addCell(prix);
                mysheet.addCell(date);

                i++;
            }

            myexcel.write();
            myexcel.close();

            return true;

        } catch (IOException ex) {
            //        Logger.getLogger(ProduitService.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false;
    }
    public List<Evenement> rechercheCategories(String str) {
        ArrayList<Evenement> evenements = new ArrayList<>();
        String sql = "SELECT e.id,e.nomE,e.capaciteE,e.description,e.imgE,e.prixE,c.nomCategorieEvenement,e.dateD FROM evenement e,categorie_evenement c where( e.categorieEvenement_id = c.id AND e.nomE LIKE ? )";
        PreparedStatement statement;

        try {

            statement = connection.prepareStatement(sql);

            statement.setString(1, "%" + str + "%");
            //statement.setString(2, "%" + str + "%");
            ResultSet rs = statement.executeQuery();

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
                c.setBtn_delete(new Button("Supprimer"));
                evenements.add(c);
            }
        } catch (SQLException ex) {

        }
        return evenements;
    }
}
