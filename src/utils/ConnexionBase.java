/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author HP
 */
public class ConnexionBase {
    private String url = "jdbc:mysql://localhost:3306/pi";
    private String login = "root";
    private String pwd = "";
    private Connection cnx;
    private static ConnexionBase instance; // Pour éviter plusieurs  connexions

    private ConnexionBase() {

        try {
            cnx = (Connection) DriverManager.getConnection(url, login, pwd);
            System.out.println("Connection établie ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public static ConnexionBase getInstance() {
        if (instance == null) {
            instance = new ConnexionBase();
        }
        return (instance);
    }

    public Connection getCnx() {
        return cnx;
    }

    public Object getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
