/*  
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author A01374009
 */
public class Cuenta {
    private int numeroCuenta; //Es el numero de cuenta del usuario
    private int pin;
    private double balanceDisponible;
    private double balanceTotal;

    //Se inicializa el constructor de cuenta
    public Cuenta(int elNumeroCuenta, int elPIN, double elBalanceDisponible, double elBalanceTotal) {
        numeroCuenta = elNumeroCuenta;
        pin = elPIN;
        balanceDisponible = elBalanceDisponible;
        balanceTotal = elBalanceTotal;
    }

    //Determina si el PIN del usuario coincide con el PIN de una cuenta
    public boolean validarPIN(int usuarioPIN){
        if(usuarioPIN == pin){
            return true;
        }
        else{
            return false;
        }
    }
    
    //Regresa un balance valido
    public double getBalanceDisponible() {
        return balanceDisponible;
    }

    //Regresa el balance total
    public double getBalanceTotal(){
        return balanceTotal;
    }
    
    // Acredita un monto de una cuenta
    public void credito(double monto){
        balanceTotal += monto; //Se le agrega al balance total
    }
    
    
    public void debito(double monto){
        balanceDisponible -= monto; 
        balanceTotal -= monto;
    }
    
    //Regresa el numero de cuenta
    public int getNumeroCuenta(){
        return numeroCuenta;
    }
}
