/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

/**
 *
 * @author A01374009, A01379896
 * ITESM CEM
 * Proyecto Final Programacion Orientado a Objetos
 */
public class BalanceAdquisicion extends Transaccion{

    public BalanceAdquisicion(int usuarioCuentaNumero, Screen atmScreen, BankDataBase atmBankDataBase) {
        super(usuarioCuentaNumero, atmScreen, atmBankDataBase);
    }

    @Override
    public void execute(){
        BankDataBase bankDataBase = getBankDataBase();
        Screen screen = getScreen();
        double balanceDisponible = bankDataBase.getBalanceDisponible(getCuentaNumero());

         // get the total balance for the account involved
         double balanceTotal = bankDataBase.getBalanceTotal(getCuentaNumero());

         // display the balance information on the screen
         screen.displayMessageLine("\nInformacion de balance:" + "\n - Balance disponible: " + balanceDisponible);
//         screen.displayDollarMonto(balanceDisponible);
         screen.displayMessageLine("\n - Balance Total: " + balanceTotal);
//         screen.displayDollarMonto(balanceTotal);
    }
}
