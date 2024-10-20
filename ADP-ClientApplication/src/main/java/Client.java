
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class Client {
    protected static ObjectOutputStream out;
    protected static ObjectInputStream in;
    private Socket server;
    
    public Client(){
        try{
            server = new Socket("127.0.0.1",6666);
        }catch(IOException ioe){
            ioe.getMessage();
        }
    }
    
    public void getStreams() throws IOException{
        out = new ObjectOutputStream(server.getOutputStream());
        out.flush();
        in = new ObjectInputStream(server.getInputStream());
    }
    
    public void closeStreamsAndConnection() throws IOException{
        out.close();
        in.close();
        server.close();
    }
    
    //a method that read the car names that are received from the sever
    public ArrayList<String> getCarNames(){
        
        ArrayList<String> carNames = new ArrayList<>();
        
        try{
            carNames = (ArrayList<String>) in.readObject();
                   
        }catch(IOException | ClassNotFoundException ioe){
            
            ioe.getMessage();
        }
        return carNames;
    }
    
    //a method that displays all the car names from the database to the combobox
    public void displayInTheComboBox(JComboBox<String> comboBox){
        //
        
        ArrayList<String> carNames = getCarNames();
        
        //resetting the combo box 
        comboBox.removeAllItems();
        
        //for each carname in carNames, add it to the combo box
        for(String carname : carNames){
            GUI.comboBox.addItem(carname);
        }
    }
    
    // a method that reads the cars and votes received from the server
    public ArrayList<String[]> getCarsAndVotesData(){
        ArrayList<String[]> allData = new ArrayList<>();
        
        try{
            
            allData = (ArrayList<String[]>) in.readObject();

        }catch(IOException | ClassNotFoundException ioe){
            
            ioe.getMessage();
        }
        return allData;
    }
    
    //a method that displays the data into the JTable
    public void displayDataInTable(JTable table){
        //storing the return data 
        ArrayList<String[]> allData = getCarsAndVotesData();
        
        DefaultTableModel model = new DefaultTableModel();
        
        //creating columns for the JTable 
        model.addColumn("Car Name");
        model.addColumn("Votes");
        
        for(String[] rowData : allData){
            model.addRow(rowData);
        }
        
        table.setModel(model);
    }
    
    public void communication(){
        try{
            getStreams();
            //get response from server and display those values in JTable
            //arrayList

            //for each
        
        }catch(IOException ioe){
            ioe.getMessage();
        }
    }
    
    

}
