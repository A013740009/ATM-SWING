/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package atm;

import javax.swing.JOptionPane;

/**
 *
 * 
 * ITESM CEM
 * Proyecto Final Programacion Orientado a Objetos
 * Creditos:
 * @author Hector David Hernández Rodriguez A01374009, Erick Bautista A01379896
 *
 */
public class ATM {
    private boolean validacionUsuario; // autentifica cualquier usuario
    private int numeroCuentaActual; // numero de cuenta del usuario
    private Screen screen; // Pantalla del Cajero
    private DispensadorEfectivo despachadorDinero; //Dispensador de dinero del Cajero
    private DepositeSlot depositoDinero;
    private BankDataBase datosBanco; // informacion de la cuenta en la base de datos
    
    //constantes de nuestro menu
    private static final int BALANCE_ADQUISICION = 1;
    private static final int RETIRO = 2;
    private static final int DEPOSITO = 3;
    private static final int SALIDA = 4;
    
    //Contructor sin argumentos que inicializa las variables
    public ATM(){
        validacionUsuario = false; // nuestro usuario no se valida al principio
        numeroCuentaActual = 0;// no se introduce el numero de cuenta aun
        screen = new Screen();//crea nuestra pantalla
        despachadorDinero = new DispensadorEfectivo();// crea nustra despachador de dinero
        depositoDinero = new DepositeSlot();//crea nuestro deposito de banco
        datosBanco = new BankDataBase();// crea la conexion a nuestra base de datos  
    }
    //inicia el Cajero
    public void run(){
        // da bienvenida y valida al usuario
        while (true){
            //ciclo miesntras el usuario no se valida
            while(!validacionUsuario){
                validaUsuario();// valida mi usaurio
            }
            realizaTransacciones();//En este punto mi usuario ya esta validado y puede realizar sus operaciones
            validacionUsuario = false; // resetea para el proximo inicio de sesion
            numeroCuentaActual = 0; //resetea nates del proximo inicio de sesion
            screen.displayMessageLine("\nMuchas gracias! Hasta la proxima!");
        }
    }
    //revisa mi usuario en mi base de datos
    private void validaUsuario(){
        try {
            // Se ejecuta algo que puede producir una excepción
            int numeroCuenta = Integer.parseInt(JOptionPane.showInputDialog(null, "Bienvenido.\nIntroduce tu numero de cuenta, por favor:  "));
            try {
                int pin = Integer.parseInt(JOptionPane.showInputDialog(null, "\nIngresa tu PIN:  "));
                //teclado.getInput();// input de mi numero de cuenta
                validacionUsuario = datosBanco.usuarioAutenticar(numeroCuenta, pin);

                if (validacionUsuario){
                    numeroCuentaActual = numeroCuenta; //guarda mi numero de cuenta
                }
                else{
                    screen.displayMessageLine("Numero de pin invalido o incorrecto, por favor intente de nuevo");
                }
                // Se ejecuta algo que puede producir una excepción
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "\n Solo introducir numero, Gracias.");
                // manejo de una excepción de entrada/salida
            } catch (Exception e) {
                // manejo de una excepción cualquiera
            }
        } catch (NumberFormatException e) {
                // manejo de una excepción de entrada/salida
                JOptionPane.showMessageDialog(null, "\n Solo introducir numero, Gracias.");
        }
    }
    //despliega mi menu y hace las trasacciones
    private void realizaTransacciones(){
        Transaccion transaccionActual = null;// variable local para guardar todos los procesos
        boolean exit = false;// falso, porque el usuario aun no decide salir
        //ciclo hasta que el usuaurio decida salir
        
        while(!exit){
            int menuOpcion = displayMenuPrincipal();
            
            //decidimos como proceder en nuestro menu
            switch(menuOpcion){
                
                case BALANCE_ADQUISICION:
                
                case RETIRO:
                
                case DEPOSITO:    
                    transaccionActual = createTransaccion(menuOpcion);
                    transaccionActual.execute();
                
                case SALIDA:// el usuario decide salir de programa
                    screen.displayMessageLine("\n Saliendo del sistema...");
                    exit = true;
                    break;
                
                default:// la entrada no estaba en el rango de 1-4
                    screen.displayMessageLine("\n Su entrada no fue valida, intente de nuevo");
                    break;
                    
            }
        }
    }
    //despliega nuestro menu principal y regresa una seleccion tipo input
    private int displayMenuPrincipal(){
        String menu = "Menu principal\n\n" +
                      "    1.- Ver mi cuenta\n" +
                      "    2.- Realizar un retiro.\n" +
                      "    3.- Deposito\n" +
                      "    4.- Salir.\n";
        try {
            int numero = Integer.parseInt(JOptionPane.showInputDialog(null, menu + "Ingrese una opcion: "));
            return numero;

        } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "\n Solo introducir numero, Gracias.");
                
                // manejo de una excepción de entrada/salida
        } catch (Exception e) {
                // manejo de una excepción cualquier
        }
        return 0;
//        return teclado.getInput();// regresa la seleccion escogida por el usuario
            
        }
    
    private Transaccion createTransaccion(int type){
        Transaccion temporal = null;//transaccion temporal en variable
        
        //determina
        switch(type){
            case BALANCE_ADQUISICION:
                temporal= new BalanceAdquisicion(numeroCuentaActual, screen, datosBanco);
                break;
            case RETIRO:
                temporal = new Retiro(numeroCuentaActual, screen, datosBanco, despachadorDinero);
                break;
            case DEPOSITO:
                temporal = new Deposito(numeroCuentaActual, screen, datosBanco, depositoDinero);
                break;
        }
        return temporal;//regresa nuestro objeto creado
        
    }
    
}