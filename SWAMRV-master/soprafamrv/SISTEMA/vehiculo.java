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
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import soprafamrv.BD.Conexion;
import soprafamrv.SISTEMA.compra;

/**
 *
 * @author Cri
 */
public class vehiculo {

//    vehiculo v = new vehiculo();
    
    //si quiero llamar con el GET desde otra clase con un objeto q no ha participado en un setter, debo declarar las variables
    //de abajo estaticas, como x ejemplo private String ANO a static String ano;
    private String PATENTE;
    private String CHASIS;
    private String COLOR;
    private String MARCA;
    private int ANO;
    private String MODELO;
    private Date FECHA_INGRESO;
    private String FECHAIN;

    public String getFECHAIN() {
        return FECHAIN;
    }

    public void setFECHAIN(String FECHAIN) {
        this.FECHAIN = FECHAIN;
    }
    private Date FECHA_RETIRO;
    private byte[] FOTO;
    
    public int getANO() {
        return ANO;
    }

    public void setANO(int ANO) {
        this.ANO = ANO;
    }

    public String getCHASIS() {
        return CHASIS;
    }

    public void setCHASIS(String CHASIS) {
        this.CHASIS = CHASIS;
    }

    public String getCOLOR() {
        return COLOR;
    }

    public void setCOLOR(String COLOR) {
        this.COLOR = COLOR;
    }

    public Date getFECHA_INGRESO() {
        return FECHA_INGRESO;
    }

    public void setFECHA_INGRESO(Date FECHA_INGRESO) {
        this.FECHA_INGRESO = FECHA_INGRESO;
    }

    public Date getFECHA_RETIRO() {
        return FECHA_RETIRO;
    }

    public void setFECHA_RETIRO(Date FECHA_RETIRO) {
        this.FECHA_RETIRO = FECHA_RETIRO;
    }

    public byte[] getFOTO() {
        return FOTO;
    }

    public void setFOTO(byte[] FOTO) {
        this.FOTO = FOTO;
    }

    public String getMARCA() {
        return MARCA;
    }

    public void setMARCA(String MARCA) {
        this.MARCA = MARCA;
    }

    public String getMODELO() {
        return MODELO;
    }

    public void setMODELO(String MODELO) {
        this.MODELO = MODELO;
    }

    public String getPATENTE() {
        return PATENTE;
    }

    public void setPATENTE(String PATENTE) {
        this.PATENTE = PATENTE;
    }
/*
    public vehiculo getV() {
        return v;
    }

    public void setV(vehiculo v) {
        this.v = v;
    }
    
   
*/
    
    public static void llenarTablaVehiculos(JTable tabla, ResultSet resultadoMostrarPersonal) throws SQLException {
        tabla.removeAll();
        System.out.println("Inicio Llenado de tabla");
        int cantidadColumnas = resultadoMostrarPersonal.getMetaData().getColumnCount();
        System.out.println("Cantidad columnas:" +cantidadColumnas);
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int x, int y) {            
            return false; //Disallow the editing of any cell
            }            
        };
        
        
        
        modelo.setColumnCount(cantidadColumnas);
        ArrayList cabeceras = new ArrayList();
        for(int z=0;z<cantidadColumnas;z++){
            //Esto imprime el nombre de las columnas
            cabeceras.add(resultadoMostrarPersonal.getMetaData().getColumnName(z+1));                        
        }
        modelo.setColumnIdentifiers(cabeceras.toArray()); 
        while(resultadoMostrarPersonal.next()){
            ArrayList lista = new ArrayList();            
            for(int i=0;i<cantidadColumnas;i++){
                lista.add(i,resultadoMostrarPersonal.getString(i+1));
                System.out.println(resultadoMostrarPersonal.getString(i+1));
                
            }
            modelo.addRow(lista.toArray());
        }
        
        tabla.setModel(modelo);
        tabla.setAutoCreateRowSorter(true);
        tabla.setAutoscrolls(true);
        
        
    }
    
    public static void llenarTablaVehiculoConductor(JTable tabla, ResultSet resultadoMostrarPersonal) throws SQLException {
        tabla.removeAll();
        System.out.println("Inicio Llenado de tabla");
        int cantidadColumnas = resultadoMostrarPersonal.getMetaData().getColumnCount();
        System.out.println("Cantidad columnas:" +cantidadColumnas);
        DefaultTableModel modelo = new DefaultTableModel();
        
        modelo.setColumnCount(cantidadColumnas);
        ArrayList cabeceras = new ArrayList();
        for(int z=0;z<cantidadColumnas;z++){
            //Esto imprime el nombre de las columnas
            cabeceras.add(resultadoMostrarPersonal.getMetaData().getColumnName(z+1));
            System.out.println ("Imprimiendo esta wea de metadata : " +resultadoMostrarPersonal.getMetaData().getColumnName(z+1));
            
        }
        modelo.setColumnIdentifiers(cabeceras.toArray()); 
        while(resultadoMostrarPersonal.next()){
            ArrayList lista = new ArrayList();            
            for(int i=0;i<cantidadColumnas;i++){
                lista.add(i,resultadoMostrarPersonal.getString(i+1));
                System.out.println(resultadoMostrarPersonal.getString(i+1));
                
            }
            modelo.addRow(lista.toArray());
        }
        
        tabla.setModel(modelo);
        tabla.setAutoCreateRowSorter(true);
        tabla.setAutoscrolls(true);
        
        
    }   
    public vehiculo cargarVehiculo(String PATENTE) throws SQLException, IOException{
        vehiculo vehiculo = null;        
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaVehiculos(?,?,?,?,?,?,?,?); END;");
        System.out.println("***INICIO CARGA VEHICULO***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setString(1, PATENTE);

        System.out.println("Setiando Parametros SALIDA");
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3, Types.INTEGER);
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.registerOutParameter(5, Types.VARCHAR);
        cs.registerOutParameter(6, Types.VARCHAR);
        cs.registerOutParameter(7, Types.DATE);                              
        cs.registerOutParameter(8, Types.BLOB);
                                                
        cs.execute();                       
        byte[] FOTOByte;
               
        //Asignacion a las variables
        this.PATENTE = PATENTE;
        CHASIS = cs.getOracleObject(2).stringValue();
        ANO = cs.getOracleObject(3).intValue();
        COLOR = cs.getOracleObject(4).stringValue();
        MARCA = cs.getOracleObject(5).stringValue();
        MODELO = cs.getOracleObject(6).stringValue();
        FECHAIN = cs.getOracleObject(7).stringValue();                
        FOTO = cs.getBytes(8);
        InputStream z = new ByteArrayInputStream(FOTO);
        BufferedImage FOTO2 = ImageIO.read(z);
        
        return vehiculo;
    }
    
    public void borrarVehiculo(String PATENTE){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarVehiculo(?); END;");
            cs.setString(1, PATENTE);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Vehiculo Borrado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar el vehiculo debido a que ya ha sido registrada en ordenes de trabajo", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    public void actualizarVehiculo(vehiculo v) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion Conductor");
            
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarVehiculo(?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, v.getPATENTE());
            cs.setString(2, v.getCHASIS());
            cs.setInt(3, v.getANO());
            cs.setString(4, v.getCOLOR());
            cs.setString(5, v.getMARCA());
            cs.setString(6, v.getMODELO());
            cs.setDate(7, v.getFECHA_INGRESO());            
            cs.setBytes(8, v.getFOTO());
            
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Datos Actualizados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                        
            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de actualizacion Conductor");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la actualizacion", "Error", JOptionPane.ERROR_MESSAGE);
    
        }
    }
        
    public void registrarVehiculo(vehiculo v) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion Vehiculo");
            
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarVehiculo(?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, v.getPATENTE());
            cs.setString(2, v.getCHASIS());
            cs.setInt(3, v.getANO());
            cs.setString(4, v.getCOLOR());
            cs.setString(5, v.getMARCA());
            cs.setString(6, v.getMODELO());
            cs.setDate(7, v.getFECHA_INGRESO());            
            cs.setBytes(8, v.getFOTO());
            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            JOptionPane.showMessageDialog(null, "Datos Ingresados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                        
            System.out.println("TERMINO del Stored Procedure de insercion Vehiculo");
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Se ha producido un error en la inserción", "Error", JOptionPane.ERROR_MESSAGE);
    
        }    
    }                
                    
}
