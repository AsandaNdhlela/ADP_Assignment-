
import java.io.*;
import java.net.*;
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
public class Server {
    protected static ObjectOutputStream out;
    protected static ObjectInputStream in;
    private ServerSocket server;
    private Socket client;
    private String request;
    
    public Server(){
        try{
            server = new ServerSocket(6666,1);
            GUI.displayLog.append("Port number checked \n");

        }catch( IOException ioe){
            ioe.getMessage();
        }
    }
    
    public void listen(){
        try{
            GUI.displayLog.append("Waiting for client connection... \n");
            client = server.accept();
            GUI.displayLog.append("Connnection Established \n" );
        }catch(IOException ioe){
            ioe.getMessage();
        }

    }
    
    public void getStreams() throws IOException{
        out = new ObjectOutputStream(client.getOutputStream());
        out.flush();
        in = new ObjectInputStream(client.getInputStream());
    }
    
    public void closeStreamsAndConnection() throws IOException{
        out.close();
        in.close();
        server.close();
    }
    
    public void sendData(ArrayList<String> msg)throws IOException {
        out.writeObject(msg);
        out.flush();
    }
        
    
    public void communication(){
        try{
            getStreams();
          
            GUI.displayLog.append("Streams made \n");

            GUI.displayLog.append("Waiting for client request... \n");
            request = (String) in.readObject();
            GUI.displayLog.append("Reading the client request and value \n");

            String readRequest[] = request.split("#");

            GUI.displayLog.append("Client has requested to: "+ readRequest[0] + " " + readRequest[1] + "\n");

            if(readRequest[0].equalsIgnoreCase("Add")){
                //call the insert method from dao and insert the value read
                DAO runDAO = new DAO();
                runDAO.insert(readRequest[1]);

            }

            if(readRequest[0].equalsIgnoreCase("Vote")){
                //call the method to update the vote of a car give 
                DAO runDAO = new DAO();
                runDAO.update(readRequest[1]);
            }

            if(readRequest[0].equalsIgnoreCase("View")){
                //call the method select from dao to view the data vailable
                DAO runDAO = new DAO();
                ArrayList<String> carNames = runDAO.getAllCarNames();  
                out.writeObject(carNames);

                //ArrayList
                //for each loop
                //send Method
            }

            if(readRequest[0].equalsIgnoreCase("Exit")){

                closeStreamsAndConnection();
            }
                    
        }catch(IOException | ClassNotFoundException ioe){
            ioe.getMessage();
        }
    }
}
