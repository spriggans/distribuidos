/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Logica.ActualizarPantalla;
import Logica.Usuario;
import chat.Chat;
import chat.escucharCliente;
import static chat.escucharCliente.sala;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author angel
 */
public class Cliente extends javax.swing.JFrame{
    ObjectOutputStream out=null;
    ObjectInputStream in=null;
    InputStream is=null;
    OutputStream os=null;
    Socket cliente=null;
    public String ipNodo=null;
    public String ipServ=null;
    private ArrayList <Usuario> user=new ArrayList <Usuario> ();
    private Thread hiloActualizar;
    private DefaultListModel listamodelo = new DefaultListModel();
    private static int sala=0;
    private  Thread hilo;
    private ArrayList <Socket> listaClientes = new ArrayList <Socket>();

    /**
     * Creates new form Cliente
     */
    public Cliente(String ip, boolean servi) {
        try {
             initComponents();
             cliente= new Socket (ip,8888);
             System.out.println (cliente.getInetAddress());
             is = cliente.getInputStream();
             os = cliente.getOutputStream();          
             this.jRadioButton1.setSelected(true);
             ipServ= cliente.getRemoteSocketAddress().toString();
             this.setLocationRelativeTo(null);
             hilo = new Thread(new escucharCliente(pantalla,cliente,sala));    
             hilo.start();     
//             solicitarLista();
             
             
             this.hiloActualizar = new Thread (new ActualizarPantalla(this.tproc,this.directorio,this.filesystem,this.cpu,this.ram,ipNodo,ipServ,this.listaProcesos,this.listaDirectorios));
             hiloActualizar.start();     
             if (servi) this.jLabel7.setText("Este cliente es actualmente servidor");
             else this.jLabel7.setText ("Solo cliente");
        } catch (UnknownHostException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        mensaje = new javax.swing.JTextArea();
        enviar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        pantalla = new javax.swing.JTextArea();
        cpu = new javax.swing.JTextField();
        ram = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jScrollPane8 = new javax.swing.JScrollPane();
        listaProcesos = new javax.swing.JList();
        jScrollPane10 = new javax.swing.JScrollPane();
        listaDirectorios = new javax.swing.JList();
        JTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane3 = new javax.swing.JScrollPane();
        tproc = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        directorio = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        filesystem = new javax.swing.JTable();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        listaNodos = new javax.swing.JList();
        selecNodo = new javax.swing.JButton();
        refrescar = new javax.swing.JButton();
        botonV = new javax.swing.JButton();
        desinstalar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        nameSala = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        agregarNodo = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        mensaje.setColumns(20);
        mensaje.setRows(5);
        mensaje.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                mensajeKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(mensaje);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 390, 280, -1));

        enviar.setText("Enviar");
        enviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                enviarActionPerformed(evt);
            }
        });
        getContentPane().add(enviar, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 390, 90, 90));

        pantalla.setColumns(20);
        pantalla.setRows(5);
        pantalla.setEnabled(false);
        jScrollPane2.setViewportView(pantalla);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 250, 280, 130));

        cpu.setEditable(false);
        getContentPane().add(cpu, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 100, -1));

        ram.setEditable(false);
        getContentPane().add(ram, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 90, 100, -1));

        jLabel1.setText("Uso de CPU");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 61, -1, -1));

        jLabel2.setText("Uso de RAM");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 94, -1, -1));

        jScrollPane8.setViewportView(listaProcesos);

        jTabbedPane2.addTab("Procesos", jScrollPane8);

        jScrollPane10.setViewportView(listaDirectorios);

        jTabbedPane2.addTab("Directorios", jScrollPane10);

        getContentPane().add(jTabbedPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 310, 220));

        tproc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tproc.setEnabled(false);
        jScrollPane3.setViewportView(tproc);

        JTabbedPane1.addTab("Procesos", jScrollPane3);

        directorio.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        directorio.setEnabled(false);
        jScrollPane4.setViewportView(directorio);

        JTabbedPane1.addTab("Directorios", jScrollPane4);

        filesystem.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        filesystem.setEnabled(false);
        jScrollPane5.setViewportView(filesystem);

        JTabbedPane1.addTab("FileSystems", jScrollPane5);

        getContentPane().add(JTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 310, 220));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 480, 10));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 30, 20, 450));

        jRadioButton1.setText("De forma controlada");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 310, -1, -1));

        jRadioButton2.setText("De forma inmediata");
        jRadioButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, -1, -1));

        jRadioButton3.setText("Directorio");
        jRadioButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 400, -1, -1));

        jLabel3.setText("Terminar un proceso:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 290, -1, -1));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 370, 160, 10));

        jLabel4.setText("Eliminar recursivamente:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 380, -1, -1));

        jLabel5.setText("Lista de nodos");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 40, -1, -1));

        jScrollPane9.setViewportView(listaNodos);

        getContentPane().add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 70, 230, 150));

        selecNodo.setText("Seleccionar Nodo");
        selecNodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selecNodoActionPerformed(evt);
            }
        });
        getContentPane().add(selecNodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 70, 140, -1));

        refrescar.setText("Refrescar");
        refrescar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refrescarActionPerformed(evt);
            }
        });
        getContentPane().add(refrescar, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, 90, -1));

        botonV.setText("Terminar Proceso");
        botonV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVActionPerformed(evt);
            }
        });
        getContentPane().add(botonV, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 430, 170, -1));

        desinstalar.setText("Desinstalar");
        desinstalar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desinstalarActionPerformed(evt);
            }
        });
        getContentPane().add(desinstalar, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 110, 140, -1));

        jButton1.setText("Admins de SO");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 250, 110, -1));

        jButton2.setText("Admins de BD");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 280, 110, -1));

        jButton3.setText("Monitoreo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(830, 310, 110, -1));

        jLabel6.setText("Te encuentras en la sala: ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 230, 150, -1));

        nameSala.setText("General");
        getContentPane().add(nameSala, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 230, -1, -1));

        jLabel7.setText("label");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 10, 320, 20));

        jMenu1.setText("Nodo");

        agregarNodo.setText("Agregar");
        agregarNodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarNodoActionPerformed(evt);
            }
        });
        jMenu1.add(agregarNodo);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void solicitarLista(){
        try {            
                out= new ObjectOutputStream(os);           
                Chat chat2;
               chat2= new Chat ("solicitarLista",cliente.getLocalPort());
                chat2.setSala(escucharCliente.sala);
                out.writeObject(chat2);
            } catch (UnknownHostException ex) {
                System.err.println ("No se encuentra el host");
            } catch (IOException ex) {
                System.err.println ("Error de i/o");
            } 
    }
    
    
    private void enviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_enviarActionPerformed
        // TODO add your handling code here:
        if (mensaje.getText().length()>0){
            try {            
                out= new ObjectOutputStream(os);           
                Chat chat2;
               chat2= new Chat (mensaje.getText(),cliente.getLocalPort());
                chat2.setSala(escucharCliente.sala);
                out.writeObject(chat2);
                mensaje.setText("");  
            } catch (UnknownHostException ex) {
                System.err.println ("No se encuentra el host");
            } catch (IOException ex) {
                System.err.println ("Error de i/o");
            } 
        }      
    }//GEN-LAST:event_enviarActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        try {
            is.close();
            os.close();
            if (out!=null)
            out.close();
            cliente.close();
            this.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_formWindowClosing

    private void agregarNodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarNodoActionPerformed
        // TODO add your handling code here:
        agregarNodo add= new agregarNodo(user,this.listaNodos,listamodelo,ipServ);
        add.setVisible(true);
    }//GEN-LAST:event_agregarNodoActionPerformed

    private void jRadioButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton3ActionPerformed
        // TODO add your handling code here:
          if (this.jRadioButton3.isSelected()){
            this.jRadioButton2.setSelected(false);
            this.jRadioButton1.setSelected(false);
            this.botonV.setText("Eliminar Directorio");
        }
    }//GEN-LAST:event_jRadioButton3ActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
        if (this.jRadioButton1.isSelected()){
            this.jRadioButton2.setSelected(false);
            this.jRadioButton3.setSelected(false);
            this.botonV.setText("Terminar Proceso");
        }
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jRadioButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton2ActionPerformed
        // TODO add your handling code here:
          if (this.jRadioButton2.isSelected()){
            this.jRadioButton1.setSelected(false);
            this.jRadioButton3.setSelected(false);
            this.botonV.setText("Terminar Proceso");
        }
    }//GEN-LAST:event_jRadioButton2ActionPerformed

    private void selecNodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selecNodoActionPerformed
        // TODO add your handling code here:
        if (!listaNodos.getSelectedValue().equals("")){
            ipNodo=listaNodos.getSelectedValue().toString();
            this.hiloActualizar.stop();
            this.hiloActualizar = new Thread (new ActualizarPantalla(this.tproc,this.directorio,this.filesystem,this.cpu,this.ram,ipNodo,ipServ,this.listaProcesos,this.listaDirectorios));
            hiloActualizar.start();
        }
    }//GEN-LAST:event_selecNodoActionPerformed

    private void refrescarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refrescarActionPerformed
        // TODO add your handling code here:
        if (ipNodo!=null){
            this.hiloActualizar.stop();
            Usuario u=null;
            for (int i=0; i<user.size(); i++)
                if (user.get(i).getIpNodo().equals(ipNodo))
                    u=user.get(i);   
            ActualizarPantalla ac= new ActualizarPantalla(ipServ);
            ac.refrescar(u.getUsuario(), u.getPassword(), u.getIpNodo());
            try {
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            this.hiloActualizar = new Thread (new ActualizarPantalla(this.tproc,this.directorio,this.filesystem,this.cpu,this.ram,ipNodo,ipServ,this.listaProcesos,this.listaDirectorios));
            hiloActualizar.start();
        }
    }//GEN-LAST:event_refrescarActionPerformed

    private void botonVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVActionPerformed
        // TODO add your handling code here:
        Usuario u=null;
        for (int i=0; i<user.size(); i++)
                if (user.get(i).getIpNodo().equals(ipNodo))
                    u=user.get(i);  
        
        if (u!= null && this.botonV.getText().equals("Terminar Proceso") && this.jRadioButton1.isSelected()){        
                int seleccion= this.listaProcesos.getSelectedIndex();
                DefaultTableModel dft= new DefaultTableModel();
                DefaultListModel dlm= new DefaultListModel();
                dlm= (DefaultListModel) this.listaProcesos.getModel();
                dft=(DefaultTableModel) this.tproc.getModel();
                String pid=dft.getValueAt(seleccion, 0).toString();
                ActualizarPantalla ac= new ActualizarPantalla(ipServ);
                ac.MatarProceso(u.getUsuario(), u.getPassword(), u.getIpNodo(), pid, 1);
                dlm.remove(seleccion);
                dft.removeRow(seleccion);
                this.tproc.setModel(dft);
                this.listaProcesos.setModel(dlm);
        } else if (u!=null && this.botonV.getText().equals("Terminar Proceso") && this.jRadioButton2.isSelected()){
                int seleccion= this.listaProcesos.getSelectedIndex();
                DefaultTableModel dft= new DefaultTableModel();
                DefaultListModel dlm= (DefaultListModel) this.listaProcesos.getModel();
                dft=(DefaultTableModel) this.tproc.getModel();
                String pid=dft.getValueAt(seleccion, 0).toString();
                ActualizarPantalla ac= new ActualizarPantalla(ipServ);
                ac.MatarProceso(u.getUsuario(), u.getPassword(), u.getIpNodo(), pid, 0);
                dlm.remove(seleccion);
                dft.removeRow(seleccion);
                this.tproc.setModel(dft);
                this.listaProcesos.setModel(dlm);
        }
        else if (u!=null && !this.botonV.getText().equals("Terminar Proceso") && this.jRadioButton3.isSelected()){
                int seleccion= this.listaDirectorios.getSelectedIndex();
                DefaultTableModel dtm= (DefaultTableModel) this.directorio.getModel();
                DefaultListModel dlm= (DefaultListModel) this.listaDirectorios.getModel();
                String nombredir=dtm.getValueAt(seleccion, 0).toString();
                ActualizarPantalla ac= new ActualizarPantalla(ipServ);
                ac.EliminarDirectorio(u.getUsuario(), u.getPassword(), u.getIpNodo(), nombredir);
                dlm.remove(seleccion);
                dtm.removeRow(seleccion);
                this.directorio.setModel(dtm);
                this.listaDirectorios.setModel(dlm);
        }
    }//GEN-LAST:event_botonVActionPerformed

    private void desinstalarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desinstalarActionPerformed
        // TODO add your handling code here:
        if (!listaNodos.getSelectedValue().equals("")){
            ipNodo=listaNodos.getSelectedValue().toString();
            this.hiloActualizar.stop();
            Usuario u= null;
            for (int i=0; i<user.size(); i++){
                if (user.get(i).getIpNodo().equals(ipNodo)){
                    u=user.get(i);
                    break;
                }
            }
            ActualizarPantalla ap= new ActualizarPantalla(ipServ);
            ap.desinstalar(u.getUsuario(), u.getPassword(), u.getIpNodo());
            DefaultListModel d= (DefaultListModel) this.listaNodos.getModel();
            user.remove(listaNodos.getSelectedIndex());
            d.remove(listaNodos.getSelectedIndex());          
            listaNodos.setModel(d);
            DefaultListModel d2= (DefaultListModel) this.listaDirectorios.getModel();
            DefaultListModel d3= (DefaultListModel) this.listaProcesos.getModel();
            d2.removeAllElements();
            d3.removeAllElements();
            listaDirectorios.setModel(d2);
            listaProcesos.setModel(d3);
            DefaultTableModel dtm= new DefaultTableModel(); 
            tproc.setModel(dtm);
            this.directorio.setModel(dtm);
            this.filesystem.setModel(dtm);
            this.cpu.setText("");
            this.ram.setText("");
            
        }
    }//GEN-LAST:event_desinstalarActionPerformed

    private void mensajeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_mensajeKeyReleased
        // TODO add your handling code here:
        if(evt.getKeyCode()==10)
        if (mensaje.getText().length()>0){
            try {            
                out= new ObjectOutputStream(os);           
                Chat chat2;
                chat2= new Chat (mensaje.getText(),cliente.getLocalPort());
                out.writeObject(chat2);
                mensaje.setText("");  
            } catch (UnknownHostException ex) {
                System.err.println ("No se encuentra el host");
            } catch (IOException ex) {
                System.err.println ("Error de i/o");
            } 
        }      
    }//GEN-LAST:event_mensajeKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        escucharCliente.sala=1;
        this.nameSala.setText("Administradores de SO");
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         escucharCliente.sala=2;
         this.nameSala.setText("Administradores de BD");
 
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         escucharCliente.sala=3;
         this.nameSala.setText("Personal de Monitoreo");
       
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cliente(null,false).setVisible(true);  
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane JTabbedPane1;
    private javax.swing.JMenuItem agregarNodo;
    private javax.swing.JButton botonV;
    private javax.swing.JTextField cpu;
    private javax.swing.JButton desinstalar;
    private javax.swing.JTable directorio;
    private javax.swing.JButton enviar;
    private javax.swing.JTable filesystem;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JList listaDirectorios;
    private javax.swing.JList listaNodos;
    private javax.swing.JList listaProcesos;
    private javax.swing.JTextArea mensaje;
    private javax.swing.JLabel nameSala;
    private javax.swing.JTextArea pantalla;
    private javax.swing.JTextField ram;
    private javax.swing.JButton refrescar;
    private javax.swing.JButton selecNodo;
    private javax.swing.JTable tproc;
    // End of variables declaration//GEN-END:variables

}
