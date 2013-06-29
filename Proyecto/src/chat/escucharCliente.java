/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import GUI.Cliente;
import GUI.IniciarServidor;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.ArrayList;
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
    public static int sala=0;
    private ArrayList <Socket> listaCliente= new ArrayList <Socket>();


    public escucharCliente(JTextArea pantalla, Socket cliente,int sala) {
        try {
            this.pantalla=pantalla;
            is=cliente.getInputStream();
            this.cliente=cliente;
            this.sala=sala;
        } catch (IOException ex) {
            Logger.getLogger(escucharCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public escucharCliente (ArrayList <Socket> listaClientes){
        this.listaCliente= listaClientes;
    };
    
    
    public void cambiar (int sala){
        this.sala=sala;
    }

    
    @Override
   public void run(){
     while (true){
            try {
                if (is!=null){
                in = new ObjectInputStream(is);
                chat= (Chat) in.readObject();  
                
                if (chat.mensaje.substring(0,1).equals("@") || chat.sala==sala){
                    if (chat.mensaje.substring(0,1).equals("@"))
                        chat.mensaje=chat.mensaje.substring(1);  
                        if (chat.sala==0)
                            pantalla.append("General "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                        else
                        if (chat.sala==1){
                            if (IniciarServidor.sala1.getRows()<10)
                            IniciarServidor.sala1.append("Admins de SO "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                            pantalla.append("Admins de SO "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                        }
                        else
                        if (chat.sala==2){
                             if (IniciarServidor.sala2.getRows()<10)
                             IniciarServidor.sala2.append("Admins de BD "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                            pantalla.append("Admins de BD "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                        }
                        else
                        if (chat.sala==3){
                            if (IniciarServidor.sala3.getRows()<10)
                            IniciarServidor.sala3.append("Monitoreo "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");                              
                            pantalla.append("Monitoreo "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                        }
                        else
                        pantalla.append(chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n"); 
                }
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
