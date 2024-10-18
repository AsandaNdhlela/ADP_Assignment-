
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

/**
 *
 * @author admin
 */
public class ADPServerApplication {

    public static void main(String[] args) {
        GUI runServerGUI = new GUI();
        
        runServerGUI.pack();
        runServerGUI.setSize(500,500);
        runServerGUI.setGUI();
        runServerGUI.setVisible(true);
        runServerGUI.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        Server connectSever = new Server();
        
        connectSever.listen();
        connectSever.communication();
        
    }
}
