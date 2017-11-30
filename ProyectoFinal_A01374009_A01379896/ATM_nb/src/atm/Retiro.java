
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
public class Retiro extends Transaccion{
    private int monto; // monto a retirar de la cuenta
    private DispensadorEfectivo dispensadorEfectivo; //Dispensador de dinero del Cajero
   
    private final static int CANCELADO = 7;

    public Retiro(int usuarioCuentaNumero, Screen atmScreen, BankDataBase atmBankDataBase, DispensadorEfectivo atmDispensadorEfectivo) {
        super(usuarioCuentaNumero, atmScreen, atmBankDataBase);
        dispensadorEfectivo = atmDispensadorEfectivo;
    }
    
    @Override
    public void execute(){
        boolean dispensadorEfectivoR = false;
        double balanceDisponible;
        
        BankDataBase bankDataBase = getBankDataBase();
        Screen screen = getScreen();
        
        do{
            monto = displayMenuMontos();
            
            if(monto != CANCELADO){
                balanceDisponible = bankDataBase.getBalanceDisponible(getCuentaNumero());
                if(monto <= balanceDisponible){
                    if(dispensadorEfectivo.suficienteEfectivo(monto)){
                        bankDataBase.debito(getCuentaNumero(), monto);
                        dispensadorEfectivo.DispensadoEfectivo(monto);
                        dispensadorEfectivoR = true;
                        
                        screen.displayMessageLine("\nTu dinero ha sido dispensado. Porfavor toma tu dinero ahora.");    
                    }else{
                        screen.displayMessageLine("\nDinero insuficiente en el ATM" + "\n\nPorfavor elije una cantidad mas pequeña.");
                    }
                }else{
                    screen.displayMessageLine("\nFondos insuficientes en tu cuenta."+"\n\nPorfavor elige una cantidad mas pequeña.");
                }
            }else{
                screen.displayMessageLine("\nCancelando transaccion....");
                return;
            }
        } while(!dispensadorEfectivoR);
    }
    
    private int displayMenuMontos(){
        int usuarioEleccion = 0;
        
        Screen screen = getScreen();
        
        @SuppressWarnings("MismatchedReadAndWriteOfArray")
        int[] montos = {0, 20, 40, 60, 100, 200, 500};
        
        while (usuarioEleccion == 0){ 
            try {
                int input = Integer.parseInt(JOptionPane.showInputDialog(null, "\nMenu de retiro:" + "\n1 - $20" + "\n2 - $40" + "\n3 - $60" + "\n4 - $100" + "\n5 - $200" + "\n6 - $500" + "\n7 - Transaccion cancelada" + "\nElije un monto para retirar: "));
    //            int input = teclado.getInput();
                switch(input){
                    case 1:
                    case 2:
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                        usuarioEleccion = montos[input];
                        break;
                    case CANCELADO:
                        usuarioEleccion = CANCELADO;
                        break;
                    default:
                        screen.displayMessageLine("\nSeleccion invalida. Intenta de nuevo.");
                }
                return usuarioEleccion;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "\n Solo introducir numero, Gracias.");
                // manejo de una excepción de entrada/salida
            }
            
        }
        return usuarioEleccion;
    }
    
    
}
