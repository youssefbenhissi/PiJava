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
}
