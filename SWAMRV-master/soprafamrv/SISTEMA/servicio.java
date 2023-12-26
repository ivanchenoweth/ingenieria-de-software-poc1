/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleCallableStatement;
import soprafamrv.BD.Conexion;

/**
 *
 * @author Cri
 */
public class servicio {
    private int ID_SERVICIO;
    private String NOMBRE;
    private String DESCRIPCION;

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public int getID_SERVICIO() {
        return ID_SERVICIO;
    }

    public void setID_SERVICIO(int ID_SERVICIO) {
        this.ID_SERVICIO = ID_SERVICIO;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }    
    
    public void borrarServicio(int ID_SERVICIO){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarServicio(?); END;");
            cs.setInt(1, ID_SERVICIO);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Servicio Borrado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
            
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar el servicio debido a que ya ha sido registrada en ordenes de trabajo", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public servicio ObtenerServicio (String NOMBRE) throws IOException, SQLException{
        servicio servicio = null;                    
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaServicio(?,?,?); END;");            
        
        System.out.println("***INICIO CARGA SERVICIO***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setString(1, NOMBRE);

        System.out.println("Setiando Parametros SALIDA");            
        cs.registerOutParameter(2, Types.INTEGER);                        
        cs.registerOutParameter(3, Types.VARCHAR);                    
        System.out.println("TERMINO Seteo de Parametros");                                                            
        cs.execute();
        
        //Asignacion a las variables
        System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
           
        this.NOMBRE = NOMBRE;
        ID_SERVICIO = cs.getOracleObject(2).intValue();            
        DESCRIPCION = cs.getOracleObject(3).stringValue();          
        System.out.println("TERMINO CARGA SERVICIO");
                                                              
        return servicio;
    }
    
    public void actualizarServicio(servicio servicio) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de actualizacion Falla");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarServicio(?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, servicio.getID_SERVICIO());
            cs.setString(2, servicio.getNOMBRE());            
            cs.setString(3, servicio.getDESCRIPCION());                
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de actualizacion Falla");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }
    
    public void registrarServicio(servicio servicio) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion Falla");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarServicio(?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, servicio.getID_SERVICIO());
            cs.setString(2, servicio.getNOMBRE());            
            cs.setString(3, servicio.getDESCRIPCION());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion Falla");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }
                
}
