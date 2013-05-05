/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import BD.Cpu;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import org.hibernate.Session;

/**
 *
 * @author angel
 */
public interface metodosRMI extends Remote {
   
    void iniciarSesion() throws RemoteException;
    List<Cpu> obtenerTopCPU (String ip) throws RemoteException;
    String obtenerTopDirectorios (String ip) throws RemoteException;
    float usoCpu (String ip) throws RemoteException;
    Long usoRam (String ip) throws RemoteException;
    String usoFilesystem (String ip) throws RemoteException;
    Session getSesion() throws RemoteException;
}
