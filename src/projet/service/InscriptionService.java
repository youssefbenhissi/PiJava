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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import projet.utils.DbConnection;
import projet.models.Inscription;
import static projet.service.CategorieClubService.statement;

/**
 *
 * @author youssef
 */
public class InscriptionService {

    static Statement statement = null;
    PreparedStatement pst;

    DbConnection cnx = DbConnection.getInstance();
    Connection connection = cnx.getConnection();

    public List<Inscription> selectAllInscris() {
        ArrayList<Inscription> clubs = new ArrayList<>();
        String req = "SELECT c.id,c.reponsePr,c.reponseDe,c.reponseTr,c.club_id,c.eleve_id,c.status,c.questionPr,c.questionDe,c.questionTr FROM inscription c";
        try {
            PreparedStatement ps = connection.prepareStatement(req);
            ps.executeQuery();
            ResultSet rs = ps.getResultSet();
            ResultSetMetaData rsmd = rs.getMetaData();
            while (rs.next()) {
                Inscription c = new Inscription();
                c.setId(rs.getInt(1));
                c.setReponsePr(rs.getString(2));
                c.setReponseDe(rs.getString(3));
                c.setReponseTr(rs.getString(4));
                c.setIdClub(rs.getInt(5));
                c.setIdUser(rs.getInt(6));
                c.setStatus(rs.getString(7));
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
    public List<Integer> getState() {
        String req11 = "Select id from club";
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
        String req11 = "Select nom  From club where id=? ";

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
        String req11 = "Select id From inscription where club_id=? ";

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
}
