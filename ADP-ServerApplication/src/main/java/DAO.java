
import java.sql.*;
import java.util.*;
import javax.swing.*;

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
    
    public void createTable(){
        String createSQL = "CREATE TABLE Car_Votes (Car_Name VARCHAR(25), Votes INTEGER)"; // Removed extra parenthesis
        
        try(PreparedStatement pstmt = conn.prepareStatement(createSQL)){
            
            GUI.displayLog.append("Database connection is established \n");
            pstmt.executeUpdate();
            GUI.displayLog.append("Database connection is established \n");

        
        }catch(SQLException ioe){}
    }
    
    public void insert(String carname){
        //creating an intial vote as zero, since a car has been just add
        initialVote = 0;
        
        //sql query
        String insertSQL = "INSERT INTO Car_Votes (Car_Name, Votes) VALUES (?, ?)";
        
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
        
        String updateSQL = "UPDATE SET Votes = ? Where Car_name = ?";
        
        try(PreparedStatement pstmt = conn.prepareStatement(updateSQL)){
            
           //caliing a getCurrentVotes method that retrieves the
           //current votes for a car provided
           int vote = getCurrentVotes(carname);
           //incrementing the the previous vote by 1 
           int newVote = vote++;
           
           //paasing the updated votes for the specified car 
           pstmt.setInt(1, newVote);
           pstmt.setString(2, carname);
           
           pstmt.executeUpdate();
           GUI.displayLog.append("Vote success!! for: " +carname+" \n" );
            
        }catch(SQLException ioe){
            ioe.getMessage();
        }
    }
    
    public int getCurrentVotes(String carname){
        String selectSQL = "SELECT Votes FROM Car_Votes Where Car_name = ?";
        int voteCount = 0;
        
        try(PreparedStatement pstmt = conn.prepareStatement(selectSQL)){
            //provide the name of the to retrive the matching votes for it
            pstmt.setString(1, carname);
            
            ResultSet results = pstmt.executeQuery();
            
            if(results.next()){
                voteCount = results.getInt("Votes");
            }
                        
        }catch(SQLException ioe){
            ioe.getMessage();
        }
        return voteCount;
    }
    
    public ArrayList getAllCarNames(){
        //creating an Array List to store all data retrived 
        ArrayList<String> carNames = new ArrayList<>();
        
        String selectCarNamesSQL = "SELECT Car_name FROM Car_Votes";
        
        try(PreparedStatement pstms = conn.prepareStatement(selectCarNamesSQL)){
            
            GUI.displayLog.append("Retreiving all Car names... \n");
            ResultSet result = pstms.executeQuery();
            GUI.displayLog.append("Success!! \n");

            while(result.next()){
                
                carNames.add(result.getString("Car_name"));
                
            }
        }catch(SQLException ioe){
            ioe.getMessage();
        }
    
        return carNames;
    }
    
    public ArrayList<String[]> getAllData() {
        
        ArrayList<String[]> allData = new ArrayList<>();
        
        String selectAllSQL = "SELECT Car_Name, Votes FROM Car_Votes";

        try (PreparedStatement pstmt = conn.prepareStatement(selectAllSQL)) {
            
            GUI.displayLog.append("Retreiving Car names and matching votes... \n");
            ResultSet result = pstmt.executeQuery();
            GUI.displayLog.append("Success!! \n");


            while (result.next()) {
                // Create a String array for each row and add it to the list
                String[] rowData = new String[2];
                
                rowData[0] = result.getString("Car_Name");
                rowData[1] = String.valueOf(result.getInt("Votes"));
                
                allData.add(rowData);
                
            }
        } catch (SQLException ioe) {
            // Handle exceptions
        }
        return allData;
    }

}
