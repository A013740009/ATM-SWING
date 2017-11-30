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
public abstract class Transaccion {
    private int cuentaNumero;
    private Screen screen;
    private BankDataBase bankDataBase;

    public Transaccion(int usuarioCuentaNumero, Screen atmScreen, BankDataBase atmBankDataBase) {
        cuentaNumero = usuarioCuentaNumero;
        screen = atmScreen;
        bankDataBase = atmBankDataBase;
    }
    
    public int getCuentaNumero(){
        return cuentaNumero;
    }
    
    public Screen getScreen(){
        return screen;
    }
    
    public BankDataBase getBankDataBase(){
        return bankDataBase;
    }
    
    abstract public void execute();
    
}
