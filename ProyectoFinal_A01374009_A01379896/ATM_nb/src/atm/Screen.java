/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;
import javax.swing.JOptionPane;
/**
 *
 * @author A01374009
 */
public class Screen { 
    //despliega nuestro mensaje sin necesidad de un return
    public void displayMessage(String message){
        JOptionPane.showInputDialog(message);
    }
    public void displayMessageLine(String message){
        JOptionPane.showMessageDialog(null, message);
    }
    // nos imprime nuestro monto en signo de dolares
    public void displayDollarMonto(double monto){
        JOptionPane.showMessageDialog(null, monto);
    }
}
