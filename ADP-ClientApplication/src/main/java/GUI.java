
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import javax.swing.*;
import javax.swing.table.*;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
    public class GUI extends JFrame implements ActionListener {
    
    private JButton add, vote,view,exit;
    private JLabel selectCarLabel;
    private JComboBox comboBox;
    private JPanel mainPanel, comboBoxPanel, tablePanel, buttonsPanel;
    private DefaultTableModel model;
    private JTable table;
    private JScrollPane scrollPane;
    
    public GUI(){
        super("Car Voting Application");
        
        //constructing panels
        mainPanel = new JPanel();
        comboBoxPanel = new JPanel();
        tablePanel = new JPanel();
        buttonsPanel = new JPanel();
        
        //constructing buttons
        add = new JButton("Add Car");
        vote = new JButton("Vote");
        view = new JButton("View");
        exit = new JButton("Exit");
        
        //constructing label
        selectCarLabel = new JLabel("Select Car To Vote For");
        
        //constructing comboBox
        comboBox = new JComboBox();
        
        //constructing table
        model = new DefaultTableModel();
        table = new JTable(model);
        scrollPane = new JScrollPane(table);
        
        //adding columns to the Table 
        model.addColumn("Car Names");
        model.addColumn("Car Vote");
    }
    
    public void setGUI(){
        //setting comboBoxPanel layout
        comboBoxPanel.setLayout(new GridLayout(1,1));
        comboBoxPanel.add(selectCarLabel);
        comboBoxPanel.add(comboBox);
        
        //setting table panel
        tablePanel.add(scrollPane);
        
        //setting buttons panel
        buttonsPanel.setLayout(new GridLayout(1,4));
        buttonsPanel.add(add);
        buttonsPanel.add(vote);
        buttonsPanel.add(view);
        buttonsPanel.add(exit);
        
        //setting up the main panel
        mainPanel.setLayout(new GridLayout(3,1)); 
        mainPanel.add(comboBoxPanel);
        mainPanel.add(tablePanel);
        mainPanel.add(buttonsPanel, BorderLayout.PAGE_END);
        
        //ADDING TO THE MAIN FRAME
        this.add(mainPanel);
               
        //registering buttons functionality
        add.addActionListener(this);
        vote.addActionListener(this);
        view.addActionListener(this);
        exit.addActionListener(this);
    }

    public void sendData(String msg){
        try{
            Client.out.writeObject(msg);
            Client.out.flush();
        }catch(IOException ioe){}
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == add){
            String value = JOptionPane.showInputDialog(null, "Enter new car");
            if(JOptionPane.OK_OPTION == 0){
                String requestAndValue = "Add#" + value;
                sendData(requestAndValue);
                JOptionPane.showMessageDialog(null, "Request Made");
            }
        }
        
        if(e.getSource() == vote){
            String carToVoteFor = (String)comboBox.getSelectedItem();
            String requestAndValue = "Vote#" + carToVoteFor;
            sendData(requestAndValue);
            JOptionPane.showMessageDialog(null, "Request Made");
        }
        
        if(e.getSource() == view){
            //with view we need to retrieve data from the data base
            //and display that data into the JTable 
        }
        
        
        
    }
    
    
    
}
    

