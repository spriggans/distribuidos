/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import BD.Cpu;
import BD.Nodo;
import java.util.Iterator;
import java.util.List;
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
//                Nodo n = new Nodo();
//                n.setIp("192.168.1.5");
//
//                //Guardando
//                session.save(n);

//                Cpu c = new Cpu();
//                c.setCpu((float) 2.55);
//                c.setNodo(n);
//                session.save(c);
//                Query query = session.createQuery("from Nodo");
//                Iterator it = query.iterate();
                Nodo x= (Nodo) session.createQuery("from Nodo where ip='192.168.1.1'").uniqueResult();
                Nodo y =(Nodo) session.load(Nodo.class,new Long(1));
                System.out.println(y.getIp());
          //      List <Nodo> nodo=(List<Nodo>)idnodo.list();
                System.out.println("id nodo" + x.getId());
                Query cpus = session.createQuery("from Cpu where fk_nodo='" + x.getId() + "' ORDER by id desc").setMaxResults(10);
                List<Cpu> list = (List<Cpu>) cpus.list();
                System.out.println(list.get(0).getCpu().toString());
                
           
        Cpu cpu= (Cpu) session.createQuery("from Cpu where fk_nodo ="+x.getId()+" order by id desc").setMaxResults(1).list().get(0);
        System.out.println(cpu.getCpu());
                tx.commit();
//                System.out.println("Insert Finalizado...");
//                Long id;
//                while (it.hasNext()) {
//                    Nodo e = (Nodo) it.next();
//                    id = e.getId();
//                    System.out.println("nodo on ID " + id);
//                }
//
//                Query query1 = session.createQuery("from Cpu");
//                Iterator ite = query1.iterate();
//                Long idnodo;
//                  while (ite.hasNext()) {
//                    Cpu e = (Cpu) ite.next();
//                    idnodo = e.getId();
//                    System.out.println("cpu on ID " + idnodo);
//                }

//                  Query query = em.createQuery("SELECT e FROM Student e");
//    List<Student> list = (List<Student>) query.getResultList();
//    System.out.println(list);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {

            session.close();
        }
    }
}
