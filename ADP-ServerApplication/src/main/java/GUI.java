
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class GUI extends JFrame{
    private JPanel mainPanel, displayLogPanel;
    protected static JTextArea displayLog;
    
    public GUI(){
        super("Server Log");
        
        //constructing panels
        mainPanel = new JPanel();
        displayLogPanel = new JPanel();
        
        //constructing text area 
        displayLog = new JTextArea(4,4);
         
    }
    
    public void setGUI(){
        //setting displayLogPanel layout
        displayLogPanel.setLayout(new GridLayout(1,1));
        displayLogPanel.add(displayLog);
        
        //setting the main Panel layout
        mainPanel.setLayout(new GridLayout(1,1));
        mainPanel.add(displayLogPanel);
        
        //ADDING TO THE FRAME
        this.add(mainPanel);
    }
       
}
