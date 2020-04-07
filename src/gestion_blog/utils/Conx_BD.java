/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestion_blog.utils;
import java.sql.*;
/**
 *
 * @author geek alaa
 */
public class Conx_BD {
    
    Connection con;
    
    public Conx_BD(){
    	 String user="root";
    	 String pass="";
    	 String url = "jdbc:mysql://localhost:3306/pidev?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Berlin";
    	
      try{
          Class.forName("com.mysql.cj.jdbc.Driver"); 
      }catch(ClassNotFoundException e){
          
          System.err.println(e); 
      }
      try{
          

          con=DriverManager.getConnection(url,user,pass);
          
      }catch(SQLException e){
          System.err.println(e);
      }
    }
    public Connection obtenirconnexion(){
        return con;
    }
    
}
