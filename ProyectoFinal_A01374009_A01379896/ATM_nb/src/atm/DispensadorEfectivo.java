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
public class DispensadorEfectivo {
    //Numero inicial de cuentas en este dispensador
    private final static int CUENTA_INICIAL=500;
    private int cuenta;
    public DispensadorEfectivo(){
        cuenta = CUENTA_INICIAL;
    }
    
    public void DispensadoEfectivo(int monto){
        int facturasRequeridas = monto/20;//numero de billetes de 20 requeridos
        cuenta -= facturasRequeridas;// actualiza la cuenta de facturación
    }
    
    public boolean suficienteEfectivo(int monto){
        int facturasRequeridas= monto/20;// numero de billetes de 20
        if (cuenta >= facturasRequeridas){
            return true;// suficientes billetes diponibles
        }
        else{
            return false;// no hay suficientes billetes
            
        }
        
    }
    
    
}
