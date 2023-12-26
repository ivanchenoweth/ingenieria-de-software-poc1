/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import soprafamrv.BD.Conexion;



/**
 *
 * @author Cri
 */
public class compra {
    private int NRO_FACTURA;
    private Date FECHA_COMPRA;
    private int ID_PROVEEDOR;
    private String RUT_ADMINISTRADOR;
    private String DETALLE;

    public String getDETALLE() {
        return DETALLE;
    }

    public void setDETALLE(String DETALLE) {
        this.DETALLE = DETALLE;
    }

    public Date getFECHA_COMPRA() {
        return FECHA_COMPRA;
    }

    public void setFECHA_COMPRA(Date FECHA_COMPRA) {
        this.FECHA_COMPRA = FECHA_COMPRA;
    }

    public int getID_PROVEEDOR() {
        return ID_PROVEEDOR;
    }

    public void setID_PROVEEDOR(int ID_PROVEEDOR) {
        this.ID_PROVEEDOR = ID_PROVEEDOR;
    }

    public int getNRO_FACTURA() {
        return NRO_FACTURA;
    }

    public void setNRO_FACTURA(int NRO_FACTURA) {
        this.NRO_FACTURA = NRO_FACTURA;
    }

    public String getRUT_ADMINISTRADOR() {
        return RUT_ADMINISTRADOR;
    }

    public void setRUT_ADMINISTRADOR(String RUT_ADMINISTRADOR) {
        this.RUT_ADMINISTRADOR = RUT_ADMINISTRADOR;
    }
    
    public void RegistrarCompra(compra com){
        try {
            System.out.println("INICIO del Stored Procedure de insercion OT");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarCompra(?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, com.getNRO_FACTURA());
            cs.setDate(2, com.getFECHA_COMPRA());
            cs.setInt(3, com.getID_PROVEEDOR());
            cs.setString(4, com.getRUT_ADMINISTRADOR());
            cs.setString(5, com.getDETALLE());

            cs.executeUpdate();

            System.out.println("\nOT succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion OT");
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE); 
        }
        
    }   
    
    public static void llenarTablaCompra(JTable tabla, ResultSet resultadoMostrarOT) throws SQLException {
        tabla.removeAll();
        System.out.println("INICIO LLENADO TABLA");
        int cantidadColumnas = resultadoMostrarOT.getMetaData().getColumnCount();
        
        DefaultTableModel modelo = new DefaultTableModel(){
            public boolean isCellEditable(int x, int y) {            
            return false; //Disallow the editing of any cell
    
        }
            
        };
        
        modelo.setColumnCount(cantidadColumnas);
        ArrayList cabeceras = new ArrayList();
        for(int z=0;z<cantidadColumnas;z++){
            //Esto imprime el nombre de las columnas
            cabeceras.add(resultadoMostrarOT.getMetaData().getColumnName(z+1));
            
        }
        modelo.setColumnIdentifiers(cabeceras.toArray()); 
        while(resultadoMostrarOT.next()){
            ArrayList lista = new ArrayList();            
            for(int i=0;i<cantidadColumnas;i++){
                lista.add(i,resultadoMostrarOT.getString(i+1));                    
            }
            modelo.addRow(lista.toArray());
        }
    
        tabla.setModel(modelo);
        tabla.setAutoCreateRowSorter(true);
        tabla.setAutoscrolls(true);
        
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
    
    public void RegistrarCompraRepuesto(int NROFACTURA, int IDREPUESTO, int CANTIDAD, String DETALLE){
        try {
            System.out.println("INICIO del Stored Procedure de insercion COMPRA DETALLE");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarCompraRep(?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, NROFACTURA);
            cs.setInt(2, IDREPUESTO);
            cs.setInt(3, CANTIDAD);
            cs.setString(4, DETALLE);
            cs.executeUpdate();

            System.out.println("\nCOMPRA_DETALLE succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion COMPRA DETALLE");
            JOptionPane.showMessageDialog(null, "Datos Ingresados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE); 
        }
    
    }
    
    public void actualizarCompraRepuesto(int NROFACTURA, int IDREPUESTO, int CANTIDAD, String DETALLE){
        try {
            System.out.println("INICIO del Stored Procedure de Actualización COMPRA DETALLE");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarCompraRep(?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, NROFACTURA);
            cs.setInt(2, IDREPUESTO);
            cs.setInt(3, CANTIDAD);
            cs.setString(4, DETALLE);
            cs.executeUpdate();

            System.out.println("\nCOMPRA_DETALLE succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion COMPRA DETALLE");
            JOptionPane.showMessageDialog(null, "Datos Actualizados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE); 
        }
    
    }
    
    public void borrarRepuestoCompra(int NRO_FACTURA,int ID_REPUESTO){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarRepuestoCompra(?,?); END;");
            cs.setInt(1, NRO_FACTURA);
            cs.setInt(2, ID_REPUESTO);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Repuesto Eliminado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
            
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede eliminado el repuesto", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
                    
    }
    
    public void borrarCompra(int NRO_FACTURA){
        try {
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarCompra(?); END;");
            cs.setInt(1, NRO_FACTURA);
            cs.executeUpdate();
            JOptionPane.showMessageDialog(null, "Compra Borrada Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
            
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo borrar la compra", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
                    
    }
    
    
}
