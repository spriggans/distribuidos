/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Logica;

/**
 *
 * @author Keyma
 */
import com.jcraft.jsch.*;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConexionSSH {

    private JSch jsch;

    public void matarProceso9(String usuario, String pass, String ipnodo, String pid) {

        jsch = new JSch();

        // Es necesario capturar JSchException
        try {

            JSch.setConfig("StrictHostKeyChecking", "no");

            Session sesion = jsch.getSession(usuario, ipnodo);

            sesion.setPassword(pass);

            sesion.connect();

            ChannelExec channelExec = (ChannelExec) sesion.openChannel("exec");

            channelExec.setCommand("kill -9 "+pid);
            channelExec.connect();

            channelExec.disconnect();

            sesion.disconnect();

        } catch (JSchException e) {
            System.out.println("Error de JSCH. Mensaje: " + e.getMessage());
        }

    }

    public ConexionSSH() throws IOException {
        jsch = new JSch();

        // Es necesario capturar JSchException
        try {

            // NO realizar revision estricta de llaves
            JSch.setConfig("StrictHostKeyChecking", "no");

            // Creamos la nueva sesion SSH
            Session sesion = jsch.getSession("xubuntu", "192.168.1.6");

            // Establecemos la clave
            sesion.setPassword("xubuntu");

            // Conectamos la sesion
            sesion.connect();

            // Obtenemos un nuevo canal para enviar/recibir comandos
            // de consola
//      ChannelShell consola = (ChannelShell) sesion.openChannel("shell");
// 
//      // Utilizamos la entrada y salida estándar del sistema
//      // para recibir comandos y desplegar el resultado
//      consola.setInputStream(System.in);
//      consola.setOutputStream(System.out); 
// 

            ChannelExec channelExec = (ChannelExec) sesion.openChannel("exec");

            channelExec.setCommand("kill -9 4279");
            channelExec.connect();

            channelExec.disconnect();

            sesion.disconnect();

            // Conectamos nuestro canal
            //   consola.connect();
        } catch (JSchException e) {
            System.out.println("Error de JSCH. Mensaje: " + e.getMessage());
        }
    }

    public static void main(String args[]) throws IOException {
        new ConexionSSH();
    }
}
