
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author admin
 */
public class ADPClientApplication {

    public static void main(String[] args) {
        GUI runClientGUI = new GUI();
        
        runClientGUI.pack();
        runClientGUI.setSize(500,500);
        runClientGUI.setGUI();
        runClientGUI.setVisible(true);
        runClientGUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Client connect = new Client();
        connect.communication();

                
    }
}
