/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import BD.Cpu;
import BD.Directorio;
import BD.Filesystem;
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
    List<Filesystem> usoFilesystem (String ip) throws RemoteException;
    /**
     *
     * @return
     * @throws RemoteException
     */
    Session getSesion() throws RemoteException;
    
    void matarProceso(String usuario, String pass, String ipnodo, String pid,int tipo) throws RemoteException;
    
    void ejecutarInstalacion(String usuario, String pass, String ipserv, String usuNodo, String passNodo, String ipnodo) throws RemoteException;
    
     void eliminarDirectorio(String usuario, String pass, String ipnodo, String nombre) throws RemoteException;
     
     void monitorearInmediato(String usuario, String pass, String ipnodo) throws RemoteException;
     
     void desinstalar(String usuario, String pass, String ipserv, String usuNodo, String passNodo, String ipnodo) throws RemoteException;
}
