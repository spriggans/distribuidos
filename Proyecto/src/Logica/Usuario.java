/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Angel
 */
public class Usuario {
    private String ipNodo=null;
    private String usuario=null;
    private String password=null;
    
    public Usuario(String ip, String user, String pw){
        ipNodo=ip;
        usuario=user;
        password=pw;
    }

    public String getIpNodo() {
        return ipNodo;
    }

    public void setIpNodo(String ipNodo) {
        this.ipNodo = ipNodo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
