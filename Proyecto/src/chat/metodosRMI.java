/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import BD.Cpu;
import BD.Directorio;
import BD.Proceso;
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
    /**
     *
     * @param ip
     * @return
     * @throws RemoteException
     */
    List<Proceso> obtenerTopProcesos (String ip) throws RemoteException;
    /**
     *
     * @param ip
     * @return
     * @throws RemoteException
     */
    List<Directorio> obtenerTopDirectorios (String ip) throws RemoteException;
    /**
     *
     * @param ip
     * @return
     * @throws RemoteException
     */
    float usoCpu (String ip) throws RemoteException;
    /**
     *
     * @param ip
     * @return
     * @throws RemoteException
     */
    float usoRam (String ip) throws RemoteException;
    /**
     *
     * @param ip
     * @return
     * @throws RemoteException
     */
    String usoFilesystem (String ip) throws RemoteException;
    /**
     *
     * @return
     * @throws RemoteException
     */
    Session getSesion() throws RemoteException;
}
