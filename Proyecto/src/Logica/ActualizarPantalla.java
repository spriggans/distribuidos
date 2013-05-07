/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import BD.Directorio;
import BD.Proceso;
import GUI.Cliente;
import chat.metodosRMI;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Angel
 */
public class ActualizarPantalla extends Thread{
    private JTable pantallaProcesos;
    private JTable pantallaDirectorio;
    private JTable pantallaFileS;
    private JTextField cpu;
    private JTextField ram;
    private Cliente pantalla;
    private String ipNodo;
    private String ipServ;
    private metodosRMI interfaz= null;
    
    public ActualizarPantalla (JTable procesos, JTable directorio, JTable filesystem, JTextField cpu, JTextField ram, String ipNodo, String ipServ) {
         this.ipNodo=ipNodo;
         this.pantallaProcesos=procesos;
         this.pantallaDirectorio=directorio;
         this.pantallaFileS=filesystem;
         this.cpu=cpu;
         this.ram=ram;
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

    public void setIpNodo(String ipNodo) {
        this.ipNodo = ipNodo;
    }

    @Override
    public void run (){
        List <Proceso> topProceso;
        List <Directorio> topDirectorio;
        float usoCpu;
        float usoRam;
        
        while (true){
            try {
                usoCpu= interfaz.usoCpu("192.168.1.1"); // la ip es ipNodo                
                usoRam= interfaz.usoRam("192.168.1.1");
                topProceso= interfaz.obtenerTopProcesos("192.168.1.1");
                topDirectorio=interfaz.obtenerTopDirectorios("192.168.1.1");
                
                ram.setText(usoRam+"");
                cpu.setText(usoCpu+" %"); 
                
                DefaultTableModel topro = new DefaultTableModel();
                DefaultTableModel todir = new DefaultTableModel();
                DefaultTableModel tofils = new DefaultTableModel();
                
                topro.addColumn("PID");
                topro.addColumn("Porcentaje");
                topro.addColumn("Valor");
                topro.setNumRows(topProceso.size());
                
                todir.addColumn("Nombre");
                todir.addColumn("Valor");
                todir.addColumn("Porcentaje");
                todir.setNumRows(topDirectorio.size());
                
                tofils.addColumn("Nombre");
                tofils.addColumn("Usado");
                tofils.addColumn("Porcentaje");
                
                

                for (int i=0; i<topProceso.size(); i++){
                    topro.setValueAt(topProceso.get(i).getPid().toString(), i, 0);
                    topro.setValueAt(topProceso.get(i).getPorcentaje().toString(), i, 1);
                    topro.setValueAt(topProceso.get(i).getValor().toString(), i, 2);
                }
                this.pantallaProcesos.setModel(topro);
                
                for (int i=0; i<topDirectorio.size(); i++){
                    topro.setValueAt(topDirectorio.get(i).getNombre().toString(), i, 0);
                    topro.setValueAt(topDirectorio.get(i).getValor().toString(), i, 1);
                    topro.setValueAt(topDirectorio.get(i).getPorcentaje(), i, 2);
                }
                this.pantallaDirectorio.setModel(todir);
                
                Thread.sleep(10000);             
            } catch (RemoteException | InterruptedException ex) {
                Logger.getLogger(ActualizarPantalla.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
