/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import Implementacion.implementarRmi;
import static chat.lecturaCliente.listaCliente;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.rmi.server.*;

/**
 *
 * @author angel
 */
public class servidor extends Thread{
    
    private Socket cliente = null;
    private ServerSocket ss= null;    
    public static ArrayList <Socket> listaClientes= new ArrayList <Socket>();
    public static boolean servi;
    private ObjectOutputStream out;

    
    public servidor(String ip) {

        try {
            System.setProperty("java.rmi.server.hostname", ip);
            implementarRmi rmi= new implementarRmi();
            Registry reg = LocateRegistry.createRegistry(1099);
            reg.rebind ("servidor", rmi);
        }
        catch (Exception e){}  
        
        try {
            ss = new ServerSocket(8888);     
            servi=true;
        } catch (IOException ex) {
            servi=false;
            System.err.println("No se puede escuchar en el puerto 8888");
            this.stop();
        }
        
    }
    
    @Override
    public void run (){
        while (true) {
            try {
                cliente = ss.accept();
                listaClientes.add(cliente);
                Thread hiloLectura = new Thread(new lecturaCliente(listaClientes, cliente));
                hiloLectura.start();
            } catch (IOException ex) {
                Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (NullPointerException ex){
                this.stop();
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
    //    try {
            
          /*
            servidor ser = new servidor();
    /       */
    //    } catch (Exception e){}  
    }
    
    
    
}
