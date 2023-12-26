/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public class proveedor {
    private int ID_PROVEEDOR;
    private String NOMBRE;
    private String DIRECCION;
    private String EMAIL;
    private int TELEFONO;
    private String DESCRIPCION;
    private int ID_COMUNA;

    public String getDESCRIPCION() {
        return DESCRIPCION;
    }

    public void setDESCRIPCION(String DESCRIPCION) {
        this.DESCRIPCION = DESCRIPCION;
    }

    public String getDIRECCION() {
        return DIRECCION;
    }

    public void setDIRECCION(String DIRECCION) {
        this.DIRECCION = DIRECCION;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public int getID_COMUNA() {
        return ID_COMUNA;
    }

    public void setID_COMUNA(int ID_COMUNA) {
        this.ID_COMUNA = ID_COMUNA;
    }

    public int getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(int ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(int TELEFONO) {
        this.TELEFONO = TELEFONO;
    }
    
    
    public proveedor ObtenerProveedor (String NOMBRE) throws IOException, SQLException{
        proveedor proveedor = null;                    
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaProveedor(?,?,?,?,?,?,?); END;");            
        
        System.out.println("***INICIO CARGA PROVEEDOR***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setString(1, NOMBRE);

        System.out.println("Setiando Parametros SALIDA");                                     
        cs.registerOutParameter(2, Types.INTEGER);                        
        cs.registerOutParameter(3, Types.INTEGER);                        
        cs.registerOutParameter(4, Types.VARCHAR);            
        cs.registerOutParameter(5, Types.VARCHAR);            
        cs.registerOutParameter(6, Types.VARCHAR);            
        cs.registerOutParameter(7, Types.INTEGER);                        
        
        System.out.println("TERMINO Seteo de Parametros");                                                            
        cs.execute();
        
        //Asignacion a las variables
        System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
           
        this.NOMBRE = NOMBRE;
        ID_PROVEEDOR = cs.getOracleObject(2).intValue();            
        TELEFONO = cs.getOracleObject(3).intValue();  
        DESCRIPCION = cs.getOracleObject(4).stringValue();
        DIRECCION = cs.getOracleObject(5).stringValue();
        EMAIL = cs.getOracleObject(6).stringValue();
        ID_COMUNA = cs.getOracleObject(7).intValue();                                           
        System.out.println("TERMINO CARGA PROVEEDOR");
                                                              
        return proveedor;
    }
    
    public int ObtenerComuna (String NOMBRE) throws SQLException{
        int IDCOMUNA = 0;
         OracleCallableStatement cs;
         Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
         cs = (OracleCallableStatement) con.prepareCall("BEGIN ObtenerComuna(?,?); END;");

         System.out.println("Setiando Parametros ENTRADA");
         cs.setString(1, NOMBRE);
         System.out.println("Setiando Parametros SALIDA");
         cs.registerOutParameter(2, Types.INTEGER);
         cs.execute();
         
         IDCOMUNA = cs.getOracleObject(2).intValue();
        
        return IDCOMUNA;
    }
    
    public int ObtenerMaxID() throws SQLException{        
        int id = 0;
        String query = "Select max(id_proveedor) from proveedor";
        ResultSet rs = Conexion.ejecutarQuery(query);
        while (rs.next()) {
            if (rs.getString("max(id_proveedor)") != null){
                id = Integer.parseInt(rs.getString("max(id_proveedor)"))+1;                                       
                }else{
                id =  1;
                }
            }
        return id;
    }
    
    public void borrarProveedor(int ID_PROVEEDOR){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarProveedor(?); END;");
            cs.setInt(1, ID_PROVEEDOR);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Proveedor Borrado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
            
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar el proveedor debido a que ya ha sido registrado en una compra", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
                    
    }    
    
    public void actualizarProveedor(proveedor proveedor) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de actualizacion Proveedor");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarProveedor(?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, proveedor.getNOMBRE());
            cs.setInt(2, proveedor.getID_PROVEEDOR());            
            cs.setInt(3, proveedor.getTELEFONO());
            cs.setString(4, proveedor.getDESCRIPCION());            
            cs.setString(5, proveedor.getDIRECCION());            
            cs.setString(6, proveedor.getEMAIL());            
            cs.setInt(7, proveedor.getID_COMUNA());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de actualizacion Proveedor");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }
    
     public void registrarProveedor(proveedor proveedor) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de registro Proveedor");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarProveedor(?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, proveedor.getNOMBRE());
            cs.setInt(2, proveedor.getID_PROVEEDOR());            
            cs.setInt(3, proveedor.getTELEFONO());
            cs.setString(4, proveedor.getDESCRIPCION());            
            cs.setString(5, proveedor.getDIRECCION());            
            cs.setString(6, proveedor.getEMAIL());            
            cs.setInt(7, proveedor.getID_COMUNA());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de registro Proveedor");
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }
    
    
}
