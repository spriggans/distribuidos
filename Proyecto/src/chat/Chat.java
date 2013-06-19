/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package chat;

/**
 *
 * @author angel
 */
public class Chat implements java.io.Serializable{
    public String mensaje;
    public String horaEnvio;
    public int puerto;
    
    
    public Chat (String mensaje, int puerto ){
        this.mensaje=mensaje;
        this.puerto=puerto;
    }
    
    
    
    
}
