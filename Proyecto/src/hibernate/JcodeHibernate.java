/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import BD.Cpu;
import BD.Nodo;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author Keyma
 */
public class JcodeHibernate {

    private static SessionFactory sessionFactory = null;

    public static void main(String[] args) {
        Session session = null;
        try {
            try {
                sessionFactory = HibernateUtil.getSessionFactory();
                session = sessionFactory.openSession();

                System.out.println("Insertando registro");
                Transaction tx = session.beginTransaction();
                //Creando un Objeto
                Nodo n = new Nodo();
                n.setIp("192.168.1.5");

                //Guardando
                session.save(n);

                Cpu c = new Cpu();
                c.setCpu((float) 2.55);
                c.setNodo(n);
                session.save(c);
                Query query = session.createQuery("from Nodo");
                Iterator it = query.iterate();

                tx.commit();
                System.out.println("Insert Finalizado...");
                Long id;
                while (it.hasNext()) {
                    Nodo e = (Nodo) it.next();
                    id = e.getId();
                    System.out.println("nodo on ID " + id);
                }

                Query query1 = session.createQuery("from Cpu");
                Iterator ite = query1.iterate();
                Long idnodo;
                  while (ite.hasNext()) {
                    Cpu e = (Cpu) ite.next();
                    idnodo = e.getId();
                    System.out.println("cpu on ID " + idnodo);
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {

            session.close();
        }
    }
}
