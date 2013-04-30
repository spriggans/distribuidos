/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import GUI.Cliente;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

/**
 *
 * @author Angel
 */
public class escucharCliente extends Thread{
    ObjectInputStream in=null;
    InputStream is=null;
    private Chat chat;
    private JTextArea pantalla;
    private Socket cliente;


    public escucharCliente(JTextArea pantalla, Socket cliente) {
        try {
            this.pantalla=pantalla;
            is=cliente.getInputStream();
            this.cliente=cliente;
        } catch (IOException ex) {
            Logger.getLogger(escucharCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
    @Override
   public void run(){
     while (true){
            try {
                if (is!=null){
                in = new ObjectInputStream(is);
                chat= (Chat) in.readObject();   
                pantalla.append(cliente.getInetAddress().getHostAddress()+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n"); 
                }
            } catch (IOException ex) {
                System.out.println ("Se ha cerrado la conexion");
                break;
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }  
   }
    
}
