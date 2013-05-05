/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import BD.BaseDeDatos;
import BD.Cpu;
import BD.Nodo;
import chat.metodosRMI;
import hibernate.HibernateUtil;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Set;
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
        // bd=new BaseDeDatos();
//        Session session = null;
//
//        try {
//            sessionFactory = HibernateUtil.getSessionFactory();
//            session = sessionFactory.openSession();
//
//            System.out.println("Insertando registro");
//            Transaction tx = session.beginTransaction();
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
    }

    @Override
    public List<Cpu> obtenerTopCPU(String ip) throws RemoteException {
        iniciarSesion();
        //Session sesion=this.getSesion();
        Query querynodo = session.createQuery("from Nodo where ip='"+ip+"'").setMaxResults(1);
        List<Nodo> nodo = (List<Nodo>) querynodo.list();
        Long idnodo = nodo.get(0).getId();

        Query cpus = session.createQuery("from Cpu where fk_nodo='" + idnodo + "' ORDER by id desc").setMaxResults(10);
        List<Cpu> list = (List<Cpu>) cpus.list();
        //   System.out.println(list.get(0).getCpu().toString());
        session.close();
        return list;
    }

    @Override
    public String obtenerTopDirectorios(String ip) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public float usoCpu(String ip) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Long usoRam(String ip) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String usoFilesystem(String ip) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void iniciarSesion() throws RemoteException {


        try {
            sessionFactory = HibernateUtil.getSessionFactory();
            session = sessionFactory.openSession();

            System.out.println("Insertando registro");
            Transaction tx = session.beginTransaction();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    @Override
    public Session getSesion() throws RemoteException {
        return this.session;
    }
}
