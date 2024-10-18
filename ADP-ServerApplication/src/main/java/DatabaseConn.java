
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class DatabaseConn {
    
    private  static Connection conn;
    private  static String url;
    private static String username;
    private static String password;
    
    
    public static Connection DatabaseConn(){
        try{
            url = "";
            username = "Administractor";
            password = "admin";
            
            GUI.displayLog.append("Verifying the Database credintials \n");
            conn = DriverManager.getConnection(url,username,password);
        }catch(SQLException ioe){
            ioe.getMessage();
        }
    GUI.displayLog.append("Database connection is ready... \n");
    return conn;
    } 
}
