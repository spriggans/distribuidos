/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import BD.BaseDeDatos;
import BD.Cpu;
import BD.Directorio;
import BD.Filesystem;
import BD.Nodo;
import BD.Proceso;
import BD.Ram;
import chat.metodosRMI;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import hibernate.HibernateUtil;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Set;
import org.hibernate.FlushMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Angel
 */
public class implementarRmi extends UnicastRemoteObject implements metodosRMI, Serializable {
    //  public BaseDeDatos bd=null;

    private Session session;
    private static SessionFactory sessionFactory = null;

    public implementarRmi() throws RemoteException {
    }

@Override
    public List<Proceso> obtenerTopProcesos(String ip) throws RemoteException {
        iniciarSesion();
        Transaction tx = session.beginTransaction();
        //Session sesion=this.getSesion();       
        List qnodo = session.createQuery("from Nodo where ip='" + ip + "' order by id desc").setMaxResults(1).list();
        if (qnodo.isEmpty()) {
            return null;
        } else {
            Nodo nodo = (Nodo) qnodo.get(0);
            Query procesos = session.createQuery("from Proceso where fk_nodo='" + nodo.getId() + "' ORDER by porcentaje DESC").setMaxResults(10);
            List<Proceso> list = (List<Proceso>) procesos.list();
            tx.commit();
            session.close();
            return list;          
                       
        }
    }

    @Override
    public List<Directorio> obtenerTopDirectorios(String ip) throws RemoteException {
        iniciarSesion();
        Transaction tx = session.beginTransaction();
        //Session sesion=this.getSesion();   
        List qnodo = session.createQuery("from Nodo where ip='" + ip + "' order by id desc").setMaxResults(1).list();
        if (qnodo.isEmpty()) {
           return null;
        } else {
             Nodo nodo = (Nodo) qnodo.get(0);
            Query directorios = session.createQuery("from Directorio where fk_nodo='" + nodo.getId() + "' ORDER by id desc").setMaxResults(10);
            List<Directorio> list = (List<Directorio>) directorios.list();
            tx.commit();
            session.close();
            return list;
            
        }
    }

    @Override
    public float usoCpu(String ip) throws RemoteException {
        iniciarSesion();
        Transaction tx = session.beginTransaction();
        List qnodo = session.createQuery("from Nodo where ip='" + ip + "' order by id desc").setMaxResults(1).list();
        if (qnodo.isEmpty()) {
                    return 0;
        } else {
             Nodo nodo = (Nodo) qnodo.get(0);
            Cpu cpu = (Cpu) session.createQuery("from Cpu where fk_nodo =" + nodo.getId() + " order by id desc").setMaxResults(1).list().get(0);
            tx.commit();
            session.close();
            return cpu.getCpu();
   
        }
    }

    @Override
    public float usoRam(String ip) throws RemoteException {
        iniciarSesion();
        Transaction tx = session.beginTransaction();
        List qnodo = session.createQuery("from Nodo where ip='" + ip + "' order by id desc").setMaxResults(1).list();
        if (qnodo.isEmpty()) {
          return 0;
        } else {
            Nodo nodo = (Nodo) qnodo.get(0);
            Ram ram = (Ram) session.createQuery("from Ram where fk_nodo =" + nodo.getId() + " order by id desc").setMaxResults(1).list().get(0);
            tx.commit();
            session.close();
            return ram.getRam();
       
        }
    }

    @Override
    public List<Filesystem> usoFilesystem(String ip) throws RemoteException {
        iniciarSesion();
        Transaction tx = session.beginTransaction();
        List qnodo = session.createQuery("from Nodo where ip='" + ip + "' order by id desc").setMaxResults(1).list();
        if (qnodo.isEmpty()) {
          return null;
        }
        else {
              Nodo nodo = (Nodo) qnodo.get(0);
            List<Filesystem> fs = (List<Filesystem>) session.createQuery("from Filesystem where fk_nodo =" + nodo.getId() + " order by id desc").setMaxResults(10).list();
            tx.commit();
            session.close();
            return fs;
         }
    }

    @Override
    public void iniciarSesion() throws RemoteException {

        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();
            session.setFlushMode(FlushMode.AUTO);
            System.out.println("Insertando registro");
            //     Transaction tx = session.beginTransaction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Session getSesion() throws RemoteException {
        return this.session;
    }

    @Override
    public void matarProceso(String usuario, String pass, String ipnodo, String pid, int tipo) throws RemoteException{

        JSch jsch = new JSch();
        // Es necesario capturar JSchException
        try {
            JSch.setConfig("StrictHostKeyChecking", "no");
            com.jcraft.jsch.Session sesion = jsch.getSession(usuario, ipnodo);
            sesion.setPassword(pass);
            sesion.connect();
            ChannelExec channelExec = (ChannelExec) sesion.openChannel("exec");
            if (tipo == 0) {
                channelExec.setCommand("kill -9 " + pid);
            } else {
                channelExec.setCommand("kill -15 " + pid);
            }
            channelExec.connect();
            channelExec.disconnect();
            sesion.disconnect();
        } catch (JSchException e) {
            System.out.println("Error de JSCH. Mensaje: " + e.getMessage());
        }

    }

    @Override
    public void ejecutarInstalacion(String usuario, String pass, String ipserv,String usuNodo, String passNodo, String ipnodo) throws RemoteException{
        JSch jsch = new JSch();
        // Es necesario capturar JSchException
        try {
            JSch.setConfig("StrictHostKeyChecking", "no");
            System.out.println (usuario+" "+pass);
            com.jcraft.jsch.Session sesion = jsch.getSession(usuario, ipserv);
            sesion.setPassword(pass);
            sesion.connect();
                ChannelExec channelExec = (ChannelExec) sesion.openChannel("exec");
                channelExec.setCommand("/home/xubuntu/NetBeansProjects/distribuidos/Proyecto/./instalacion.sh "+usuNodo+" "+passNodo+" "+ipnodo);
                channelExec.connect();
                channelExec.disconnect();
            sesion.disconnect();
        } catch (JSchException e) {
            System.out.println("Error de JSCH. Mensaje: " + e.getMessage());
        }

    }
    
        @Override
    public void desinstalar(String usuario, String pass, String ipserv,String usuNodo, String passNodo, String ipnodo) throws RemoteException{
        JSch jsch = new JSch();
        // Es necesario capturar JSchException
        try {
            JSch.setConfig("StrictHostKeyChecking", "no");
            com.jcraft.jsch.Session sesion = jsch.getSession(usuario, ipserv);
            sesion.setPassword(pass);
            sesion.connect();
                ChannelExec channelExec = (ChannelExec) sesion.openChannel("exec");
                channelExec.setCommand("/home/xubuntu/NetBeansProjects/distribuidos/Proyecto/./desinstalar.sh "+usuNodo+" "+passNodo+" "+ipnodo);
                channelExec.connect();
                channelExec.disconnect();
            sesion.disconnect();
        } catch (JSchException e) {
            System.out.println("Error de JSCH. Mensaje: " + e.getMessage());
        }

    }
    
    
    @Override
     public void eliminarDirectorio(String usuario, String pass, String ipnodo, String nombre) throws RemoteException{
        JSch jsch = new JSch();
        // Es necesario capturar JSchException
        try {
            JSch.setConfig("StrictHostKeyChecking", "no");
            com.jcraft.jsch.Session sesion = jsch.getSession(usuario, ipnodo);
            sesion.setPassword(pass);
            sesion.connect();
                ChannelExec channelExec = (ChannelExec) sesion.openChannel("exec");
                System.out.println(usuario);
                System.out.println(pass);
                System.out.println(ipnodo);
                System.out.println(nombre);
                channelExec.setCommand("rm -rf "+nombre);
                channelExec.connect();
                channelExec.disconnect();
            sesion.disconnect();
        } catch (JSchException e) {
            System.out.println("Error de JSCH. Mensaje: " + e.getMessage());
        }

    }
     
    @Override
        public void monitorearInmediato(String usuario, String pass, String ipnodo) throws RemoteException{
        JSch jsch = new JSch();
        // Es necesario capturar JSchException
        try {
            JSch.setConfig("StrictHostKeyChecking", "no");
            com.jcraft.jsch.Session sesion = jsch.getSession(usuario, ipnodo);
            sesion.setPassword(pass);
            sesion.connect();
                ChannelExec channelExec = (ChannelExec) sesion.openChannel("exec");
                channelExec.setCommand("/home/xubuntu/Escritorio/./nuevo.py");
                channelExec.connect();
                channelExec.disconnect();
            sesion.disconnect();
        } catch (JSchException e) {
            System.out.println("Error de JSCH. Mensaje: " + e.getMessage());
        }

    }
}
