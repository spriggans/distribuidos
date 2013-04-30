package BD;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author angel
 */
import java.sql.*;

public class BaseDeDatos {

    private Connection conexion;
    private static String bd = "proyecto";
    private static String user = "root";
    private static String password = "root";
    private static String server = "jdbc:mysql://localhost/" + bd;
    private ResultSet rs;
    private PreparedStatement ps;

    public BaseDeDatos() {
    }

    public void establecerConexion() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(server, user, password);
        } catch (Exception e) {
            System.out.println("Imposible realizar conexion con la BD");
            e.printStackTrace();
        }
    }

    public Connection getConexion() {
        return conexion;
    }

    public void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (Exception e) {
                System.out.print("No es posible cerrar la Conexion");
            }
        }
    }

    public void cerrar(java.sql.Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (Exception e) {
            }
        }
    }

    public void destruir() {

        if (conexion != null) {

            try {
                conexion.close();
            } catch (Exception e) {
            }
        }
    }
    
    public ResultSet ejecutarQuery(String query) throws SQLException {
        establecerConexion();
        ps = conexion.prepareStatement(query);
        rs = ps.executeQuery();
        return rs;
    }
    
    public int ejecutarUpdate(String query)throws SQLException {
        establecerConexion();
        ps = conexion.prepareStatement(query);
        return ps.executeUpdate();
    }
    
    public static void main(String[] args) throws SQLException{
        BaseDeDatos b=new BaseDeDatos();
        b.establecerConexion();
        String [][] resul=new String[1][1];
        
       ResultSet x=b.ejecutarQuery("select * from nodo");

        while (x.next()){
            resul[0][0]= x.getString("ip");
        }
        x.close();
        System.out.print(resul[0][0]);
    }
}
