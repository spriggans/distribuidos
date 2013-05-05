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
        Nodo nodo= (Nodo) session.createQuery("from Nodo where ip='"+ip+"'").uniqueResult();
        Query procesos = session.createQuery("from Proceso where fk_nodo='" + nodo.getId() + "' ORDER porcentaje desc").setMaxResults(10);
        List<Proceso> list = (List<Proceso>) procesos.list();
        tx.commit();
        session.close();
        return list;
    }

    @Override
    public List<Directorio> obtenerTopDirectorios(String ip) throws RemoteException {
         iniciarSesion();
         Transaction tx = session.beginTransaction();
        //Session sesion=this.getSesion();       
        Nodo nodo= (Nodo) session.createQuery("from Nodo where ip='"+ip+"'").uniqueResult();
        Query directorios = session.createQuery("from Directorio where fk_nodo='" + nodo.getId() + "' ORDER by id desc").setMaxResults(10);
        List<Directorio> list = (List<Directorio>) directorios.list();
         tx.commit();
        session.close();
        return list;
    }

    @Override
    public float usoCpu(String ip) throws RemoteException {
        iniciarSesion();
        Transaction tx = session.beginTransaction();
        Nodo nodo= (Nodo) session.createQuery("from Nodo where ip='"+ip+"'").uniqueResult();
        Cpu cpu= (Cpu) session.createQuery("from Cpu where fk_nodo ="+nodo.getId()+" order by id desc").setMaxResults(1).list().get(0);
         tx.commit();
         session.close();
        return cpu.getCpu();
    }

    @Override
    public float usoRam(String ip) throws RemoteException {
        iniciarSesion();
        Transaction tx = session.beginTransaction();
        Nodo nodo= (Nodo) session.createQuery("from Nodo where ip='"+ip+"'").uniqueResult();
        Ram ram= (Ram) session.createQuery("from Ram where fk_nodo ="+nodo.getId()+" order by id desc").setMaxResults(1).list().get(0);
         tx.commit();
         session.close();
        return ram.getRam();
    }

    @Override
    public String usoFilesystem(String ip) throws RemoteException {
        iniciarSesion();
        Transaction tx = session.beginTransaction();
        Nodo nodo= (Nodo) session.createQuery("from Nodo where ip='"+ip+"'").uniqueResult();
        Filesystem fs= (Filesystem) session.createQuery("from Filesystem where fk_nodo ="+nodo.getId()+" order by id desc").setMaxResults(1).list().get(0);
         tx.commit();
         session.close();
        return fs.getNombre();
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
}
