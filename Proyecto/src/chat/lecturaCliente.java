/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angel
 */
public class lecturaCliente extends Thread{
    private Socket cliente;
    private ObjectOutputStream out=null;
    private ObjectInputStream in=null;
    private Chat chat=null;
    public static ArrayList <Socket> listaCliente=null;

    lecturaCliente(ArrayList listaClientes, Socket cliente) {
        this.cliente=cliente;
        this.listaCliente=listaClientes;
    }
    
    @Override
    public void run(){
        String nombre= cliente.getInetAddress().toString();
        String lista="";     
            try {
                
                for (int i=0; i<listaCliente.size(); i++)
                    lista= lista+listaCliente.get(i).getInetAddress().toString()+"\n";
          
                while (true){             
                    in = new ObjectInputStream(cliente.getInputStream());
                    chat= (Chat) in.readObject();
                    chat.horaEnvio=new Date().toLocaleString();
                    enviarTodos();
                }
            } catch (IOException ex) {
                System.out.println ("cerro la conexion un cliente");
                in= null;
                listaCliente.remove(cliente);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(lecturaCliente.class.getName()).log(Level.SEVERE, null, ex);
            }     
    }
    
    private void enviarTodos() {
            for (int i=0; i<listaCliente.size(); i++){
                try {
                    out= new ObjectOutputStream (listaCliente.get(i).getOutputStream());
                    out.writeObject(chat);
                    out.flush();
                } catch (IOException ex) {
                    System.out.println ("Borrando un cliente de la lista");
                    listaCliente.remove(i);
                }
            }
     }
    
    
}
