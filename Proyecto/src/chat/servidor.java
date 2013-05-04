/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import Implementacion.implementarRmi;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author angel
 */
public class servidor {
    
    private Socket cliente = null;
    private ServerSocket ss= null;    
    public static ArrayList <Socket> listaClientes= new ArrayList <Socket>();

    
    public servidor() {

        try {
            ss = new ServerSocket(8888);
        } catch (IOException ex) {
            System.err.println("No se puede escuchar en el puerto 8888");
        }

        while (true) {
            try {
                cliente = ss.accept();
                listaClientes.add(cliente);
                Thread hiloLectura = new Thread(new lecturaCliente(listaClientes, cliente));
                hiloLectura.start();
            } catch (IOException ex) {
                Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        try {
            implementarRmi rmi= new implementarRmi();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind ("RMI", rmi);
            servidor ser = new servidor();
        } catch (Exception e){}  
    }
    
    
    
}
