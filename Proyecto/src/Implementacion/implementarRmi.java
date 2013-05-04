/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Implementacion;

import BD.BaseDeDatos;
import chat.metodosRMI;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 *
 * @author Angel
 */
public class implementarRmi extends UnicastRemoteObject implements metodosRMI, Serializable{
    public BaseDeDatos bd=null;

    
    public implementarRmi () throws RemoteException{
        bd=new BaseDeDatos();
    }
    
    @Override
    public String obtenerTopCPU(String ip) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
