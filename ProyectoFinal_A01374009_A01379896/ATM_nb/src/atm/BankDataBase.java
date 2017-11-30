/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author ERICK
 */
public class BankDataBase {
    private Cuenta[] cuentas; //Array de cuentas

    public BankDataBase() {
        cuentas = new Cuenta[2]; //En este caso solo ponemos 2 cuentas para el testeo
        cuentas[0] = new Cuenta(12345, 54321, 1000.0, 1200.0);
        cuentas[1] = new Cuenta(98765, 56789, 200.0, 200.0);
    }

    private Cuenta getCuenta(int numeroCuenta){
        for(Cuenta cuentaActual : cuentas){
            if(cuentaActual.getNumeroCuenta() == numeroCuenta){
                return cuentaActual;
            }
        }
        return null;
    }

    public boolean usuarioAutenticar(int usuarioCuentaNumero, int usuarioPIN){
        Cuenta usuarioCuenta = getCuenta(usuarioCuentaNumero);
        
        if(usuarioCuenta != null){
            return usuarioCuenta.validarPIN(usuarioPIN);
        }
        else{
            return false;
        }
    }
    
    public double getBalanceDisponible(int usuarioCuentaNumero){
        return getCuenta(usuarioCuentaNumero).getBalanceDisponible();
    }
    
    public double getBalanceTotal(int usuarioCuentaNumero){
        return getCuenta(usuarioCuentaNumero).getBalanceTotal();
    }
    
    public void credito(int usuarioCuentaNumero, double monto){
        getCuenta(usuarioCuentaNumero).credito(monto);
    }
    
    public void debito(int usuarioCuentaNumero, double monto){
        getCuenta(usuarioCuentaNumero).debito(monto);
    }

}
