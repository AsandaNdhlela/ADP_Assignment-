
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
