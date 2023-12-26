/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import oracle.jdbc.OracleCallableStatement;
import soprafamrv.BD.Conexion;

/**
 *
 * @author Cri
 */
public class repuesto {    
    private int ID_REPUESTO;
    private String NOMBRE;
    private String MARCA;
    private String DETALLE;
    private int CANTIDAD;
    private byte[] FOTO;

    public int getCANTIDAD() {
        return CANTIDAD;
    }

    public void setCANTIDAD(int CANTIDAD) {
        this.CANTIDAD = CANTIDAD;
    }

    public String getDETALLE() {
        return DETALLE;
    }

    public void setDETALLE(String DETALLE) {
        this.DETALLE = DETALLE;
    }

    public byte[] getFOTO() {
        return FOTO;
    }

    public void setFOTO(byte[] FOTO) {
        this.FOTO = FOTO;
    }

    public int getID_REPUESTO() {
        return ID_REPUESTO;
    }

    public void setID_REPUESTO(int ID_REPUESTO) {
        this.ID_REPUESTO = ID_REPUESTO;
    }

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
    
    public repuesto ObtenerRepuesto (String NOMBRE) throws IOException, SQLException{
        repuesto repuesto = null;
                    
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaRepuestos(?,?,?,?,?); END;");
            
           System.out.println("***INICIO CARGA REPUESTO***");
            System.out.println("Setiando Parametros ENTRADA");
            cs.setString(1, NOMBRE);

            System.out.println("Setiando Parametros SALIDA");            
            cs.registerOutParameter(2, Types.INTEGER);                        
            cs.registerOutParameter(3, Types.INTEGER);                        
            cs.registerOutParameter(4, Types.VARCHAR);            
            cs.registerOutParameter(5, Types.BLOB);
            System.out.println("TERMINO Seteo de Parametros");              
            cs.execute();
                    
            //Asignacion a las variables
            System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
            
            System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
            
            this.NOMBRE = NOMBRE;
            ID_REPUESTO = cs.getOracleObject(2).intValue();            
            CANTIDAD = cs.getOracleObject(3).intValue();            
            DETALLE = cs.getOracleObject(4).stringValue();  
            FOTO = cs.getBytes(5);            
            InputStream z = new ByteArrayInputStream(FOTO);
            BufferedImage FOTO2 = ImageIO.read(z);
            System.out.println("IMPRIMIENDO FOTO: "+FOTO);                         
            System.out.println("DETALLE: " +DETALLE);            
            System.out.println("TERMINO CARGA REPUESTO");
                                                              
        return repuesto;
    }
    
    public repuesto ObtenerRepuesto2 (int IDREPUESTO) throws IOException, SQLException{
        repuesto repuesto = null;
                    
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaRepuestos2(?,?,?,?,?); END;");
            
            System.out.println("***INICIO CARGA REPUESTO***");
            System.out.println("Setiando Parametros ENTRADA");
            cs.setInt(1, IDREPUESTO);

            System.out.println("Setiando Parametros SALIDA");            
            cs.registerOutParameter(2, Types.VARCHAR);                        
            cs.registerOutParameter(3, Types.INTEGER);                        
            cs.registerOutParameter(4, Types.VARCHAR);            
            cs.registerOutParameter(5, Types.BLOB);
            System.out.println("TERMINO Seteo de Parametros");              
            cs.execute();
                    
            //Asignacion a las variables
            System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
            
            System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
            
            this.ID_REPUESTO = IDREPUESTO;
            NOMBRE = cs.getOracleObject(2).stringValue();            
            CANTIDAD = cs.getOracleObject(3).intValue();            
            DETALLE = cs.getOracleObject(4).stringValue();  
            FOTO = cs.getBytes(5);            
            InputStream z = new ByteArrayInputStream(FOTO);
            BufferedImage FOTO2 = ImageIO.read(z);
            System.out.println("IMPRIMIENDO FOTO: "+FOTO);                         
            System.out.println("DETALLE: " +DETALLE);            
            System.out.println("TERMINO CARGA REPUESTO");
                                                              
        return repuesto;
    }
    
     public void borrarRepuesto(int IDREPUESTO){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarRepuesto(?); END;");
            cs.setInt(1, IDREPUESTO);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Repuesto Borrado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar el repuesto debido a que ya ha sido registrado en ordenes de trabajo", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
                    
    }
     
     public void actualizarRepuesto(repuesto repuesto) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de actualizacion Repuesto");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarRepuesto(?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, repuesto.getID_REPUESTO());
            cs.setString(2, repuesto.getNOMBRE());            
            cs.setString(3, repuesto.getDETALLE());
            cs.setBytes(4, repuesto.getFOTO());
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de actualizacion Repuesto");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }
     
     public void registrarRepuesto(repuesto repuesto) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion Repuesto");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarRepuesto(?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, repuesto.getID_REPUESTO());
            cs.setString(2, repuesto.getNOMBRE());            
            cs.setString(3, repuesto.getDETALLE());
            cs.setBytes(4, repuesto.getFOTO());
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion Repuesto");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }
     
    public int obtenerMesRepuesto(String MES){
        int numeroMes = 0;
        if("Enero".equals(MES)){
        numeroMes = 1;
        }
        else if("Febrero".equals(MES)){
        numeroMes = 2;        
        }
        else if("Marzo".equals(MES)){
        numeroMes = 3;
         }
        else if("Abril".equals(MES)){
        numeroMes = 4;
         }
        else if("Mayo".equals(MES)){
        numeroMes = 5;
        }
        else if("Junio".equals(MES)){
        numeroMes = 6;
        }
        else if("Julio".equals(MES)){
        numeroMes = 7;
        }
        else if("Agosto".equals(MES)){
        numeroMes = 8;
        }
        else if("Septiembre".equals(MES)){
        numeroMes = 9;
        }
        else if("Octubre".equals(MES)){
        numeroMes = 10;
        }
        else if("Noviembre".equals(MES)){
        numeroMes = 11;
        }
        else if("Diciembre".equals(MES)){
        numeroMes = 12;
        }
        return numeroMes;
        
        } 
    
    public void borrarRepuestoOT(int ID_OT, String PATENTE, int ID_REPUESTO){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarRepuestoOT(?,?,?); END;");
            cs.setInt(1, ID_OT);
            cs.setString(2, PATENTE);
            cs.setInt(3, ID_REPUESTO);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Repuesto devuelto satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede devolver el repuesto", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
}
