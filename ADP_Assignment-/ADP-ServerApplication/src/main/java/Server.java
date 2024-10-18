
import java.io.*;
import java.net.*;

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
    
    public void communication(){
        try{
            getStreams();
            
            
            GUI.displayLog.append("Streams made \n");
            
            GUI.displayLog.append("Waiting for client request... \n");
            request = (String) in.readObject();
            GUI.displayLog.append("Reading the client request and value \n");

            String readRequest[] = request.split("#");
           
            GUI.displayLog.append("Client has requested to: "+ readRequest[0] + " " + readRequest[1] + "\n");
            
            if(readRequest[0].equalsIgnoreCase("add")){
                //call the insert method from dao and insert the value read
                DAO runDAO = new DAO();
                runDAO.insert(readRequest[1]);
                
            }
            
            if(readRequest[0].equalsIgnoreCase("vote")){
                //call the method to update the vote of a car give 
            }
            
            if(readRequest[0].equalsIgnoreCase("view")){
                //call the method select from dao to view the data vailable
                //ArrayList
                //for each loop
                //send Method
            }
            
            if(readRequest[0].equalsIgnoreCase("exit")){
                
                closeStreamsAndConnection();
            }
           
        }catch(IOException | ClassNotFoundException ioe){
            ioe.getMessage();
        }
    }
}
