/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author angel
 */
public interface metodosRMI extends Remote {
   
    String obtenerTopCPU (String ip) throws RemoteException;
    String obtenerTopDirectorios (String ip) throws RemoteException;
    float usoCpu (String ip) throws RemoteException;
    Long usoRam (String ip) throws RemoteException;
    String usoFilesystem (String ip) throws RemoteException;
    
}
