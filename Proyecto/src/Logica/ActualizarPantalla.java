/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

import BD.Directorio;
import BD.Filesystem;
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
import javax.swing.DefaultListModel;
import javax.swing.JList;
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
    private JList listaProcesos;
    private JList listaDirectorio;
    
    public ActualizarPantalla (JTable procesos, JTable directorio, JTable filesystem, JTextField cpu, JTextField ram, String ipNodo, String ipServ, JList listaProcesos, JList listaDirectorio) {
         this.ipNodo=ipNodo;
         this.pantallaProcesos=procesos;
         this.pantallaDirectorio=directorio;
         this.pantallaFileS=filesystem;
         this.cpu=cpu;
         this.ram=ram;
         this.listaProcesos=listaProcesos;
         this.listaDirectorio=listaDirectorio;
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
    
    public ActualizarPantalla (String ipServ){
         String toString = ipServ.split("/")[1].toString();
         String toString1 = toString.split(":")[0].toString();
         this.ipServ=toString1;
         System.setProperty("java.security.policy", "client.policy");
         System.setSecurityManager(new RMISecurityManager());
         String url = "rmi://"+this.ipServ+"/";
         try {
             Context c= new InitialContext();
             interfaz= (metodosRMI) c.lookup(url+ "servidor");
          //   interfaz.matarProceso(user, password, ip, pid, tipo);
         } catch (NamingException ex) {
            System.err.println ("Error en el Naming");
        } 
    }
    
    public void MatarProceso (String user, String password, String ip,String pid, int tipo){
        try {
            interfaz.matarProceso(user, password, ip, pid, tipo);
        } catch (RemoteException ex) {
            Logger.getLogger(ActualizarPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
    
    public void EliminarDirectorio (String user, String password, String ip, String directorio){
        try {
            //llamar a eliminar directorio
             interfaz.eliminarDirectorio(user, password, ip, directorio);
        } catch (RemoteException ex) {
            Logger.getLogger(ActualizarPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void instalar(String user, String password, String ip){
        try {
            interfaz.ejecutarInstalacion("angel", "123456", ipServ,user,password,ip);
        } catch (RemoteException ex) {
            Logger.getLogger(ActualizarPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void desinstalar(String user, String password, String ip){
        try {
            interfaz.desinstalar("angel", "123456", ipServ,user,password,ip);
        } catch (RemoteException ex) {
            Logger.getLogger(ActualizarPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setIpNodo(String ipNodo) {
        this.ipNodo = ipNodo;
    }
    
    public void refrescar (String user, String password, String ip){
         try {
            interfaz.monitorearInmediato(user, password, ip);
        } catch (RemoteException ex) {
            Logger.getLogger(ActualizarPantalla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run (){
        List <Proceso> topProceso;
        List <Directorio> topDirectorio;
        List <Filesystem> filesystem;
        float usoCpu;
        float usoRam;
        
        while (true){
            try {
                if (ipNodo!=null){
                    usoCpu= interfaz.usoCpu(ipNodo); // la ip es ipNodo                
                    usoRam= interfaz.usoRam(ipNodo);
                    topProceso= interfaz.obtenerTopProcesos(ipNodo);
                    filesystem=interfaz.usoFilesystem(ipNodo);
                    topDirectorio=interfaz.obtenerTopDirectorios(ipNodo);

                    ram.setText(usoRam+" Kb");
                    cpu.setText(usoCpu+" %"); 

                    DefaultTableModel topro = new DefaultTableModel();
                    DefaultTableModel todir = new DefaultTableModel();
                    DefaultTableModel tofils = new DefaultTableModel();
                    DefaultListModel toprolis = new DefaultListModel();
                    DefaultListModel todirlis = new DefaultListModel();

                    topro.addColumn("PID");
                    topro.addColumn("Porcentaje");
                    topro.addColumn("Valor");
                    topro.setNumRows(topProceso.size());

                    todir.addColumn("Nombre");
                    todir.addColumn("Espacio en disco");
                    todir.setNumRows(topDirectorio.size());

                    tofils.addColumn("Nombre");
                    tofils.addColumn("Usado");
                    tofils.addColumn("Porcentaje");
                    tofils.setNumRows(filesystem.size());



                    for (int i=0; i<topProceso.size(); i++){
                        topro.setValueAt(topProceso.get(i).getPid().toString(), i, 0);
                        topro.setValueAt(topProceso.get(i).getPorcentaje().toString(), i, 1);
                        topro.setValueAt(topProceso.get(i).getValor().toString(), i, 2);
                        toprolis.addElement(topProceso.get(i).getPid()+" "+topProceso.get(i).getPorcentaje()+" "+topProceso.get(i).getValor());          
                    }
                    this.pantallaProcesos.setModel(topro);
                    this.listaProcesos.setModel(toprolis);

                    for (int i=0; i<topDirectorio.size(); i++){
                        todir.setValueAt(topDirectorio.get(i).getNombre().toString(), i, 0);
                        todir.setValueAt(topDirectorio.get(i).getValor().toString(), i, 1);
                        todirlis.addElement(topDirectorio.get(i).getNombre()+"     "+topDirectorio.get(i).getValor());
                    }
                    this.pantallaDirectorio.setModel(todir);
                    this.listaDirectorio.setModel(todirlis);
                    for (int i=0; i<filesystem.size(); i++){
                        tofils.setValueAt(filesystem.get(i).getNombre().toString(), i, 0);
                        tofils.setValueAt(filesystem.get(i).getValor().toString(), i, 1);
                        tofils.setValueAt(filesystem.get(i).getPorcentaje(), i, 2);
                    }
                    this.pantallaFileS.setModel(tofils);   
                    Thread.sleep(300000);             
                }
            } catch (RemoteException | InterruptedException ex) {
                Logger.getLogger(ActualizarPantalla.class.getName()).log(Level.SEVERE, null, ex);
                System.out.println ("Se ha interrumpido el hilo");
            }
        }
    }
    
}
