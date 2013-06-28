/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import GUI.IniciarServidor;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;

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
    public static JTextArea sala0= new JTextArea ();
    public static JTextArea sala1= new JTextArea ();
    public static JTextArea sala2= new JTextArea ();
    public static JTextArea sala3= new JTextArea ();
    

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
                    if (chat.mensaje.equals("solicitarLista")) enviarTodosLista();
                    else {
                    chat.horaEnvio=new Date().toLocaleString();
                    
                    if (chat.mensaje.equals("SALA1"))
                    {
                        if (IniciarServidor.sala1.getText().equals("")) chat.mensaje="FAIL";
                        else if (IniciarServidor.sala1.getRows()<10){
                            chat.mensaje=chat.mensaje+"-"+IniciarServidor.sala1.getText();
                        //    IniciarServidor.sala1.append("Admins de SO "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje.substring(5)+"\n");
                        }              
                    } else
                    if (chat.mensaje.equals("SALA2"))
                    {
                        if (IniciarServidor.sala1.getText().equals("")) chat.mensaje="FAIL";
                        else if (IniciarServidor.sala1.getRows()<10){
                            chat.mensaje=chat.mensaje+"-"+IniciarServidor.sala1.getText();
                        //    IniciarServidor.sala1.append("Admins de BD "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje.substring(5)+"\n");
                        }
                        
                    } else
                    if (chat.mensaje.equals("SALA3"))
                    {
                        if (IniciarServidor.sala1.getText().equals("")) chat.mensaje="FAIL";
                        else if (IniciarServidor.sala1.getRows()<10){
                            chat.mensaje=chat.mensaje+"-"+IniciarServidor.sala1.getText();
                        //    IniciarServidor.sala1.append("Monitoreo "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje.substring(5)+"\n");
                        }
                        
                    } else
                    if (chat.sala==1){
                        if (IniciarServidor.sala1.getRows()<10)
                        IniciarServidor.sala1.append("Admins de SO "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                    } else
                     if (chat.sala==2){
                         if (IniciarServidor.sala2.getRows()<10)
                        IniciarServidor.sala2.append("Admins de BD "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                     } else
                      if (chat.sala==3){
                        if (IniciarServidor.sala3.getRows()<10)
                        IniciarServidor.sala3.append("Monitoreo "+chat.ip+":"+chat.puerto+" "+chat.horaEnvio+" > "+chat.mensaje+"\n");
                      }
                    enviarTodos();
                    }
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
       
        private void enviarTodosLista() {
            for (int i=0; i<listaCliente.size(); i++){
                try {
                    out= new ObjectOutputStream (listaCliente.get(i).getOutputStream());
                        out.writeObject (listaCliente);
                        out.flush(); 
                } catch (IOException ex) {
                    Logger.getLogger(lecturaCliente.class.getName()).log(Level.SEVERE, null, ex);
                    listaCliente.remove(i);
                } 
            }
     }
    
}
