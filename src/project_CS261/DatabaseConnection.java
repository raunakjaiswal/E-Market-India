/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project_CS261;


import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.SQLException; 
  

/**
 *
 * @author TEJENDRA
 */
public class DatabaseConnection {
    private static Connection con = null; 
  
    static
    { 
        
        String url = "jdbc:mysql:// mysql80-afe9.euw2.cloud.ametnes.com:3316/1516289225"; 
        String user = "mkWNYCsIAt"; 
        String pass = "tzVJfMuMoVy0V9JCCavs";  //Replace with your password
        try { 
            Class.forName("com.mysql.jdbc.Driver"); 
            con = DriverManager.getConnection(url, user, pass); 
        } 
        catch (ClassNotFoundException | SQLException e) { 
            e.printStackTrace(); 
        } 
    } 
    public static Connection getConnection() 
    { 
        return con; 
    } 
}
