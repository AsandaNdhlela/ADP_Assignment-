
import java.sql.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class DAO {
    
    private int initialVote;
    
    private Connection conn;
    
    public DAO(){
        conn = DatabaseConn.DatabaseConn();
        GUI.displayLog.append("Database connection is established \n");

    }
    
    public void insert(String carname){
        //creating an intial vote as zero, since a car has been just add
        initialVote = 0;
        
        //sql query
        String insertSQL = "INSERT INTO Car_Votes Car_Name VALUE (?,?) ";
        
        //using the try-with-resources to create the Prepared Statement for automatic 
        //closing when done executing the sql query
        try(PreparedStatement pstmt = conn.prepareStatement(insertSQL)){
            
            pstmt.setString(1, carname);
            pstmt.setInt(2,initialVote);
            
            int rowAdded = pstmt.executeUpdate();
            
            if(rowAdded > 0){
                GUI.displayLog.append("New car successfully added \n");
            }else{
                GUI.displayLog.append("Adding new car failed \n");
            }
        }catch(SQLException ioe){
            ioe.getMessage();
        }
    }
    
    public void update(String carname){
    
    }
    
}
