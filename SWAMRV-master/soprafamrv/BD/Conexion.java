/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.BD;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.*;
import oracle.jdbc.OraclePreparedStatement;
import soprafamrv.SISTEMA.compra;

/**
 *
 * @author Cri
 */
public class Conexion {
    // Comienzo de variables Globales

    public static String usuario = "SOPRAFAMRV";
    public static String clave = "fisica";
    public static String url = "jdbc:oracle:thin:@localhost:1521:xe";
    public static Connection con;

    public Conexion() {
        try {
            //esto es la manera simple, podrias buscar sobre el patron de diseño singleton, que optimiza las llamadas a la bd
            DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
            con = DriverManager.getConnection(url, usuario, clave);
        } catch (SQLException e) {
        }
    }

    public static ResultSet ejecutarQuery(String Query) throws SQLException {
        Conexion x = new Conexion();
        OraclePreparedStatement stmt = (OraclePreparedStatement) x.con.prepareStatement(Query);
        OracleResultSet rs = (OracleResultSet) stmt.executeQuery();
        return rs;                
    }
    
    public static ResultSet ejecutarQuery2(String Query, OraclePreparedStatement stmt) throws SQLException {
        Conexion x = new Conexion();
        stmt = (OraclePreparedStatement) x.con.prepareStatement(Query);
        OracleResultSet rs = (OracleResultSet) stmt.executeQuery();        
        return rs;      
    }
    
    public void registrarVehiculoConductor(String conductor, String patente, Date fecha_asignacion, String descripcion) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion Vehiculo-Conductor");

            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("begin registrarVehiculoConductor(?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, conductor);
            cs.setString(2, patente);
            cs.setDate(3, fecha_asignacion);
            cs.setString(4, descripcion);
            
            cs.executeUpdate();

            System.out.println("DATOS INGRESADOS SATISFACTORIAMENTE");
            con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion Conductor");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
    }
            
    
    
}
