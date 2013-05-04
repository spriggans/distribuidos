/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import GUI.Cliente;
import chat.metodosRMI;
import java.rmi.RMISecurityManager;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JTextArea;

/**
 *
 * @author Angel
 */
public class ActualizarPantalla extends Thread{
    private JTextArea pantallaProcesos;
    private JTextArea pantallaDirectorio;
    private JTextArea pantallaFileS;
    private Cliente pantalla;
    private String ipNodo;
    private String ipServ;
    private metodosRMI interfaz= null;
    
    public ActualizarPantalla (JTextArea procesos, JTextArea directorio, JTextArea filesystem, String ipNodo, String ipServ) {
         this.ipNodo=ipNodo;
         this.pantallaProcesos=procesos;
         this.pantallaDirectorio=directorio;
         this.pantallaFileS=filesystem;
         String toString = ipServ.split("/")[1].toString();
         String toString1 = toString.split(":")[0].toString();
         this.ipServ=toString1;
         System.setProperty("java.security.policy", "client.policy");
         System.setSecurityManager(new RMISecurityManager());
         String url = "rmi://"+this.ipServ+"/";
         try {
             Context c= new InitialContext();
             interfaz= (metodosRMI) c.lookup(url+ "servidor");
         } catch (NamingException ex) {
            System.err.println ("Error en el Naming");
        }
         
    }
    
    @Override
    public void run (){
        
    }
    
}
