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
public class falla {
    private int ID_FALLA;
    private String NOMBRE;    
    private String DETALLE;    
    private byte[] FOTO;

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

    public int getID_FALLA() {
        return ID_FALLA;
    }

    public void setID_FALLA(int ID_FALLA) {
        this.ID_FALLA = ID_FALLA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }
    
    public falla ObtenerFalla (String NOMBRE) throws IOException, SQLException{
        falla falla = null;                    
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaFallas(?,?,?,?); END;");            
        
        System.out.println("***INICIO CARGA FALLA***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setString(1, NOMBRE);

        System.out.println("Setiando Parametros SALIDA");            
        cs.registerOutParameter(2, Types.INTEGER);                        
        cs.registerOutParameter(3, Types.VARCHAR);            
        cs.registerOutParameter(4, Types.BLOB);
        System.out.println("TERMINO Seteo de Parametros");                                                            
        cs.execute();
        
        //Asignacion a las variables
        System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
           
        this.NOMBRE = NOMBRE;
        ID_FALLA = cs.getOracleObject(2).intValue();            
        DETALLE = cs.getOracleObject(3).stringValue();  
        FOTO = cs.getBytes(4);            
        InputStream z = new ByteArrayInputStream(FOTO);
        BufferedImage FOTO2 = ImageIO.read(z);                                
        System.out.println("IMPRIMIENDO FOTO: "+FOTO);                         
        System.out.println("DETALLE: " +DETALLE);            
        System.out.println("TERMINO CARGA FALLA");
                                                              
        return falla;
    }
    
    public void borrarFalla(int ID_FALLA){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarFalla(?); END;");
            cs.setInt(1, ID_FALLA);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Falla Borrada Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
            
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar la falla debido a que ya ha sido registrada en ordenes de trabajo", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
                    
    }
    
    public void actualizarFalla(falla falla) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de actualizacion Falla");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarFalla(?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, falla.getID_FALLA());
            cs.setString(2, falla.getNOMBRE());            
            cs.setString(3, falla.getDETALLE());
            cs.setBytes(4, falla.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de actualizacion Falla");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }
    
    public void registrarFalla(falla falla) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion Falla");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarFalla(?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, falla.getID_FALLA());
            cs.setString(2, falla.getNOMBRE());            
            cs.setString(3, falla.getDETALLE());
            cs.setBytes(4, falla.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion Falla");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }
    
    public falla ObtenerFallaOT (int IDOT, String PATENTE, String NOMBRE) throws IOException, SQLException{
        falla falla = null;                    
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaFallasRegistrada(?,?,?,?,?,?); END;");        
        System.out.println("***INICIO CARGA FALLA***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setInt(1, IDOT);
        cs.setString(2, PATENTE);                        
        cs.setString(3, NOMBRE);    
        System.out.println("Setiando Parametros SALIDA");            
        cs.registerOutParameter(4, Types.VARCHAR);            
        cs.registerOutParameter(5, Types.INTEGER);                                    
        cs.registerOutParameter(6, Types.BLOB);
        System.out.println("TERMINO Seteo de Parametros");                                                            
        
        cs.execute();
        
        //Asignacion a las variables
        System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
           
        this.NOMBRE = NOMBRE;
        DETALLE = cs.getOracleObject(4).stringValue();            
        ID_FALLA = cs.getOracleObject(5).intValue();  
        FOTO = cs.getBytes(6);                    
        System.out.println("IMPRIMIENDO FOTO: "+FOTO);                         
        System.out.println("DETALLE: " +DETALLE);            
        System.out.println("TERMINO CARGA FALLA");
                                                              
        return falla;
    }
    
    public void borrarFallaOT(int ID_OT, String PATENTE, int ID_FALLA){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarFALLAOT(?,?,?); END;");
            cs.setInt(1, ID_OT);
            cs.setString(2, PATENTE);
            cs.setInt(3, ID_FALLA);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Falla eliminada satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede eliminar la falla", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
        
    }
}
