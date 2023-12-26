/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

import com.itextpdf.text.DocumentException;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import oracle.jdbc.OracleCallableStatement;
import soprafamrv.ARCHIVOS_EXP.GeneratePDF;
import soprafamrv.BD.Conexion;

/**
 *
 * @author Administrador
 */
public class ot {
    
    private int ID_OT;
    private String PATENTE;
    private String RUT_ADMINISTRADOR;
    private String RUT_MECANICO;
    private Date FECHA_INICIO;
    private Date FECHA_TERMINO;
    private String TIPOTRABAJO;
    
    public Date getFECHA_INICIO() {
        return FECHA_INICIO;
    }

    public void setFECHA_INICIO(Date FECHA_INICIO) {
        this.FECHA_INICIO = FECHA_INICIO;
    }

    public Date getFECHA_TERMINO() {
        return FECHA_TERMINO;
    }

    public void setFECHA_TERMINO(Date FECHA_TERMINO) {
        this.FECHA_TERMINO = FECHA_TERMINO;
    }

    public int getID_OT() {
        return ID_OT;
    }

    public void setID_OT(int ID_OT) {
        this.ID_OT = ID_OT;
    }

    public String getTIPOTRABAJO() {
        return TIPOTRABAJO;
    }

    public void setTIPOTRABAJO(String TIPOTRABAJO) {
        this.TIPOTRABAJO = TIPOTRABAJO;
    }

    public String getPATENTE() {
        return PATENTE;
    }

    public void setPATENTE(String PATENTE) {
        this.PATENTE = PATENTE;
    }

    public String getRUT_ADMINISTRADOR() {
        return RUT_ADMINISTRADOR;
    }

    public void setRUT_ADMINISTRADOR(String RUT_ADMINISTRADOR) {
        this.RUT_ADMINISTRADOR = RUT_ADMINISTRADOR;
    }

    public String getRUT_MECANICO() {
        return RUT_MECANICO;
    }

    public void setRUT_MECANICO(String RUT_MECANICO) {
        this.RUT_MECANICO = RUT_MECANICO;
    }
    
    public static void llenarTablaOT(JTable tabla, ResultSet resultadoMostrarOT) throws SQLException {
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
    
     public static void llenarTablaOT2(JTable tabla, ResultSet resultadoMostrarOT) throws SQLException {
        tabla.removeAll();
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
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
            System.out.println("imprimiendo cabeceras:" +cabeceras);     
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
        System.out.println("FIN LLENADO TABLA");
        
    }
     
     public void registrarOT (ot ordentrabajo) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion OT");

            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarOT(?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, ordentrabajo.getID_OT());
            cs.setString(2, ordentrabajo.getPATENTE());
            cs.setString(3, ordentrabajo.getRUT_ADMINISTRADOR());
            cs.setString(4, ordentrabajo.getRUT_MECANICO());
            cs.setDate(5, ordentrabajo.getFECHA_INICIO());
            cs.setDate(6, ordentrabajo.getFECHA_TERMINO());            
            cs.setString(7, ordentrabajo.getTIPOTRABAJO());            

            cs.executeUpdate();

            System.out.println("\nOT succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion OT");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }    
    }
     
     
            
    public void registrarOTSERVICIO (int idot, String patente, int id_servicio) throws SQLException, DocumentException, FileNotFoundException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion Conductor");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarOTSERVICIO(?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, idot);
            cs.setString(2, patente);
            cs.setInt(3, id_servicio);

            cs.executeUpdate();

            System.out.println("\nOTSERVICIO succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion OTSERVICIO");
            GeneratePDF gpdf = new GeneratePDF();
            
            //Obtencion datos personal de la orden_trabajo
            String query = "select ot.fecha_inicio as fechainicio, mec.nombre as mecnombre, mec.apellido_paterno as mecapepa, mec.apellido_materno as mecamema, adm.nombre as adnombre, adm.apellido_paterno as adapepa, adm.apellido_materno as adamema, ot.id_ot as idot, ot.patente as patente from orden_trabajo ot, mecanico mec, administrador adm where ot.id_ot = "+idot+" and ot.patente = '"+patente+"' and ot.rut_mecanico = mec.rut_mecanico and ot.rut_administrador = adm.rut_administrador";
            ResultSet rs = Conexion.ejecutarQuery(query);     
            
            String query2 = "select s.id_servicio, s.nombre from orden_trabajo_servicio ots, servicio s where ots.id_ot = "+idot+" and ots.patente = '"+patente+"' and ots.id_servicio = s.id_servicio";
            ResultSet rs2 = Conexion.ejecutarQuery(query2);     
            //Creacion de PDF
            gpdf.crearDocumento("ORDEN DE TRABAJO N°"+idot+"-PATENTE-"+patente);
            //Asignacion de contenido PDF a la clase GeneratePDF
            gpdf.ContenidoDocumento("SOPRAF S.A. SOFTWARE AMRV", "O  R  D  E  N     D  E     T  R  A  B  A  J  O", "S  E  R  V  I  C  I  O  S", rs, rs2);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }    
}
    
    public void actualizarOTSERVICIO (int idot, String patente, int id_servicio) throws SQLException, DocumentException, FileNotFoundException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion Conductor");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarOTSERVICIO(?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setInt(1, idot);
            cs.setString(2, patente);
            cs.setInt(3, id_servicio);

            cs.executeUpdate();

            System.out.println("\nOTSERVICIO succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion OTSERVICIO");
            GeneratePDF gpdf = new GeneratePDF();
            
            //Obtencion datos personal de la orden_trabajo
            String query = "select ot.fecha_inicio as fechainicio, mec.nombre as mecnombre, mec.apellido_paterno as mecapepa, mec.apellido_materno as mecamema, adm.nombre as adnombre, adm.apellido_paterno as adapepa, adm.apellido_materno as adamema, ot.id_ot as idot, ot.patente as patente from orden_trabajo ot, mecanico mec, administrador adm where ot.id_ot = "+idot+" and ot.patente = '"+patente+"' and ot.rut_mecanico = mec.rut_mecanico and ot.rut_administrador = adm.rut_administrador";
            ResultSet rs = Conexion.ejecutarQuery(query);     
            
            String query2 = "select s.id_servicio, s.nombre from orden_trabajo_servicio ots, servicio s where ots.id_ot = "+idot+" and ots.patente = '"+patente+"' and ots.id_servicio = s.id_servicio";
            ResultSet rs2 = Conexion.ejecutarQuery(query2);     
            //Creacion de PDF
            gpdf.crearDocumento("ORDEN DE TRABAJO N°"+idot+"-PATENTE-"+patente);
            //Asignacion de contenido PDF a la clase GeneratePDF
            gpdf.ContenidoDocumento("SOPRAF S.A. SOFTWARE AMRV", "O  R  D  E  N     D  E     T  R  A  B  A  J  O", "S  E  R  V  I  C  I  O  S", rs, rs2);
            
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }    
}
    
    
    public void RegistrarRepuestoOT(int NUM_ORDEN, String PATENTE, int ID_REPUESTO, String RUT_ENCARGADO, String OBSERVACIONES, int CANTIDAD) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion OTREPUESTO");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarOTREP(?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            System.out.println("AQUI YA RECIBI PARAMETROS DESDE RETIROREPUESTOS: " +NUM_ORDEN+ " , " +PATENTE+ " , " +ID_REPUESTO+ " , " +OBSERVACIONES+ " , " +CANTIDAD+ " , " +RUT_ENCARGADO);
            cs.setInt(1, NUM_ORDEN);
            cs.setString(2, PATENTE);
            cs.setInt(3, ID_REPUESTO);
            cs.setString(4, RUT_ENCARGADO);
            cs.setString(5, OBSERVACIONES);
            cs.setInt(6, CANTIDAD);
            
            cs.executeUpdate();
            System.out.println("\nSuccesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion OTREPUESTO");
            JOptionPane.showMessageDialog(null, "Datos Ingresados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE); 
    
        }
         
    }
    
    public void RegistrarFallaOT(int NUM_ORDEN, String PATENTE, int ID_FALLA, String OBSERVACIONES) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de insercion OTREPUESTO");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarOTFALLA(?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            System.out.println("AQUI YA RECIBI PARAMETROS DESDE RETIROREPUESTOS: " +NUM_ORDEN+ " , " +PATENTE+ " , " +ID_FALLA+ " , " +OBSERVACIONES);
            cs.setInt(1, NUM_ORDEN);
            cs.setString(2, PATENTE);
            cs.setInt(3, ID_FALLA);           
            cs.setString(4, OBSERVACIONES);            
            
            cs.executeUpdate();
            System.out.println("\nSuccesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion OTFALLA");
            JOptionPane.showMessageDialog(null, "Datos Ingresados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null,ex, "Error", JOptionPane.ERROR_MESSAGE); 
    
        }
         
    }
    
    
     
    
}
