/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
public class personal {
    
    private String RUT;
    private String NOMBRE;
    private String CONTRASENA;
    private String APELLIDOPAT;
    private String APELLIDOMAT;
    private String DIRECCION;
    private int TELEFONO;
    private String EMAIL;
    private int ID_COMUNA;
    private Date FECHA_INGRESO;
    private Date FECHA_RETIRO;
    private Date FECHA_NACIMIENTO;
    private int RADIO;
    private String LICENCIA;
    private String DETALLE;
    private byte[] FOTO;
    private String FECHAIN;
    private String FECHANA;
    private String day;
    private String day2;
    private String month;
    private String month2;
    private String year;

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getMes2() {
        return mes2;
    }

    public void setMes2(String mes2) {
        this.mes2 = mes2;
    }
    private String mes;
    private String mes2;

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getDay2() {
        return day2;
    }

    public void setDay2(String day2) {
        this.day2 = day2;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getMonth2() {
        return month2;
    }

    public void setMonth2(String month2) {
        this.month2 = month2;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear2() {
        return year2;
    }

    public void setYear2(String year2) {
        this.year2 = year2;
    }
    private String year2;
    

    public String getNOMBRE_COMUNA() {
        return NOMBRE_COMUNA;
    }

    public void setNOMBRE_COMUNA(String NOMBRE_COMUNA) {
        this.NOMBRE_COMUNA = NOMBRE_COMUNA;
    }
    private String NOMBRE_COMUNA;

    public String getFECHAIN() {
        return FECHAIN;
    }

    public void setFECHAIN(String FECHAIN) {
        this.FECHAIN = FECHAIN;
    }

    public String getFECHANA() {
        return FECHANA;
    }

    public void setFECHANA(String FECHANA) {
        this.FECHANA = FECHANA;
    }

    public String getAPELLIDOMAT() {
        return APELLIDOMAT;
    }

    public void setAPELLIDOMAT(String APELLIDOMAT) {
        this.APELLIDOMAT = APELLIDOMAT;
    }

    public String getAPELLIDOPAT() {
        return APELLIDOPAT;
    }

    public void setAPELLIDOPAT(String APELLIDOPAT) {
        this.APELLIDOPAT = APELLIDOPAT;
    }

    public String getCONTRASENA() {
        return CONTRASENA;
    }

    public void setCONTRASENA(String CONTRASENA) {
        this.CONTRASENA = CONTRASENA;
    }

    public String getDETALLE() {
        return DETALLE;
    }

    public void setDETALLE(String DETALLE) {
        this.DETALLE = DETALLE;
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

    public Date getFECHA_INGRESO() {
        return FECHA_INGRESO;
    }

    public void setFECHA_INGRESO(Date FECHA_INGRESO) {
        this.FECHA_INGRESO = FECHA_INGRESO;
    }

    public Date getFECHA_NACIMIENTO() {
        return FECHA_NACIMIENTO;
    }

    public void setFECHA_NACIMIENTO(Date FECHA_NACIMIENTO) {
        this.FECHA_NACIMIENTO = FECHA_NACIMIENTO;
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

    public int getID_COMUNA() {
        return ID_COMUNA;
    }

    public void setID_COMUNA(int ID_COMUNA) {
        this.ID_COMUNA = ID_COMUNA;
    }

    public String getLICENCIA() {
        return LICENCIA;
    }

    public void setLICENCIA(String LICENCIA) {
        this.LICENCIA = LICENCIA;
    }

    public String getNOMBRE() {
        return NOMBRE;
    }

    public void setNOMBRE(String NOMBRE) {
        this.NOMBRE = NOMBRE;
    }

    public int getRADIO() {
        return RADIO;
    }

    public void setRADIO(int RADIO) {
        this.RADIO = RADIO;
    }

    public String getRUT() {
        return RUT;
    }

    public void setRUT(String RUT) {
        this.RUT = RUT;
    }

    public int getTELEFONO() {
        return TELEFONO;
    }

    public void setTELEFONO(int TELEFONO) {
        this.TELEFONO = TELEFONO;
    }

    public boolean verificarRut(int rut, char dv){
        int m = 0 , s = 1;
        for (; rut!= 0; rut /=10){
        s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s +47 : 75);
    }
    
    public static void llenarTabla(JTable tabla, ResultSet resultadoMostrarPersonal) throws SQLException {
        tabla.removeAll();
        int cantidadColumnas = resultadoMostrarPersonal.getMetaData().getColumnCount();
        System.out.println(cantidadColumnas);
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
    
    private String retornarMes(String month){
    if (month.equals("1")){
        month = "Enero";                
        }                              
    else if(month.equals("2")){                    
        month = "Febrero";                    
        }
    else if(month.equals("3")){                    
        month = "Marzo";                    
        }
    else if(month.equals("4")){                    
        month = "Abril";                    
        }
    else if(month.equals("5")){                    
        month = "Mayo";                    
        }
    else if(month.equals("6")){                    
        month = "Junio";                    
        }
    else if(month.equals("7")){                    
        month = "Julio";                    
        }
    else if(month.equals("8")){                    
        month = "Agosto";                    
        }
    else if(month.equals("9")){          
        month = "Septiembre";                    
        }
    else if(month.equals("10")){                    
        month = "Octubre";                    
        }
    else if(month.equals("11")){                    
        month = "Noviembre";                    
        }
    else if(month.equals("12")){                    
        month = "Diciembre";                    
        }
    System.out.println ("MES RETORNADO: " +month);
    return month;
    }
    
    public personal ObtenerConductor (String RUT) throws SQLException, IOException{
        personal personal = null;
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaConductores(?,?,?,?,?,?,?,?,?,?,?,?,?,?); END;");

        System.out.println("***INICIO CARGA CONDUCTOR***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setString(1, RUT);

        System.out.println("Setiando Parametros SALIDA");
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.registerOutParameter(5, Types.VARCHAR);
        cs.registerOutParameter(6, Types.INTEGER);
        cs.registerOutParameter(7, Types.VARCHAR);
        cs.registerOutParameter(8, Types.INTEGER);
        cs.registerOutParameter(9, Types.DATE);
        cs.registerOutParameter(10, Types.DATE);                
        cs.registerOutParameter(11, Types.INTEGER);
        cs.registerOutParameter(12, Types.CHAR);
        cs.registerOutParameter(13, Types.VARCHAR);
        cs.registerOutParameter(14, Types.BLOB);
                                
        cs.execute();
        
        System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
        this.RUT = RUT;
        NOMBRE = cs.getOracleObject(2).stringValue();
        APELLIDOPAT = cs.getOracleObject(3).stringValue();
        APELLIDOMAT = cs.getOracleObject(4).stringValue();
        DIRECCION = cs.getOracleObject(5).stringValue();
        TELEFONO = cs.getOracleObject(6).intValue();
        EMAIL = cs.getOracleObject(7).stringValue();
        ID_COMUNA = cs.getOracleObject(8).intValue();        
        String query = "Select nombre from comuna where id_comuna="+ID_COMUNA+"";
        ResultSet rs = Conexion.ejecutarQuery(query);
        while (rs.next()){
            NOMBRE_COMUNA = rs.getString("nombre");
            }
        FECHAIN = cs.getOracleObject(9).stringValue();                
        FECHANA = cs.getOracleObject(10).stringValue();
        RADIO = cs.getOracleObject(11).intValue();
        LICENCIA = cs.getOracleObject(12).stringValue();
        DETALLE = cs.getOracleObject(13).stringValue();                                
        FOTO = cs.getBytes(14);                   
        
        String FINGRESO = FECHAIN.substring(0, 10);
        String FNACIMIENTO = FECHANA.substring(0, 10);
        String dateParts1[] = FINGRESO.split("/");
        String dateParts2[] = FNACIMIENTO.split("/");
        month  = dateParts1[0];
        day  = dateParts1[1];
        year = dateParts1[2].trim();
        month2  = dateParts2[0];
        day2  = dateParts2[1];
        year2 = dateParts2[2].trim();
        System.out.println ("FECHAINGRESO sin java sql: " +month);
        System.out.println ("FECHAINGRESO sin java sql: " +day);
        System.out.println ("FECHAINGRESO sin java sql: " +year);
                
        System.out.println ("FECHAINGRESO sin java sql: " +month2);
        System.out.println ("FECHAINGRESO sin java sql: " +day2);
        System.out.println ("FECHAINGRESO sin java sql: " +year2);
        mes = retornarMes(month);
        mes2 = retornarMes(month2);
                        
                //Hasta aqui el codigo funciona bien!
        System.out.println(NOMBRE);
        System.out.println(APELLIDOPAT);
        System.out.println(APELLIDOMAT);
        System.out.println(DIRECCION);
        System.out.println(TELEFONO);
        System.out.println(EMAIL);
        System.out.println(ID_COMUNA);
        System.out.println(FECHAIN);        
        System.out.println(FECHANA);
        System.out.println(RADIO);
        System.out.println(LICENCIA);
        System.out.println(DETALLE);
        System.out.println(FOTO);
        System.out.println("TERMINO CARGA CONDUCTOR");
           
        return personal;
    }
    
    public personal ObtenerAdministrador (String RUT) throws SQLException, IOException{
        personal personal = null;
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaAdministradores(?,?,?,?,?,?,?,?,?,?,?,?,?); END;");

        System.out.println("***INICIO CARGA ADMINISTRADOS***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setString(1, RUT);

        System.out.println("Setiando Parametros SALIDA");
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.registerOutParameter(5, Types.VARCHAR);
        cs.registerOutParameter(6, Types.VARCHAR);
        cs.registerOutParameter(7, Types.INTEGER);                
        cs.registerOutParameter(8, Types.VARCHAR);
        cs.registerOutParameter(9, Types.INTEGER);
        cs.registerOutParameter(10, Types.DATE);
        cs.registerOutParameter(11, Types.DATE);                                
        cs.registerOutParameter(12, Types.VARCHAR);
        cs.registerOutParameter(13, Types.BLOB);
                                
        cs.execute();
        
        System.out.println("INICIO ASIGNACION VARIABLES OBTENIDAS DE BD");                                                
        this.RUT = RUT;
        NOMBRE = cs.getOracleObject(2).stringValue();
        CONTRASENA = cs.getOracleObject(3).stringValue();
        APELLIDOPAT = cs.getOracleObject(4).stringValue();
        APELLIDOMAT = cs.getOracleObject(5).stringValue();
        DIRECCION = cs.getOracleObject(6).stringValue();
        TELEFONO = cs.getOracleObject(7).intValue();
        EMAIL = cs.getOracleObject(8).stringValue();
        ID_COMUNA = cs.getOracleObject(9).intValue(); 
        String query = "Select nombre from comuna where id_comuna="+ID_COMUNA+"";
        ResultSet rs = Conexion.ejecutarQuery(query);
        while (rs.next()){
            NOMBRE_COMUNA = rs.getString("nombre");
            }
        FECHAIN = cs.getOracleObject(10).stringValue();                
        FECHANA = cs.getOracleObject(11).stringValue();                
        DETALLE = cs.getOracleObject(12).stringValue();                
        FOTO = cs.getBytes(13);                   
        String FINGRESO = FECHAIN.substring(0, 10);
        String FNACIMIENTO = FECHANA.substring(0, 10);
        String dateParts1[] = FINGRESO.split("/");
        String dateParts2[] = FNACIMIENTO.split("/");
        month  = dateParts1[0];
        day  = dateParts1[1];
        year = dateParts1[2].trim();
        month2  = dateParts2[0];
        day2  = dateParts2[1];
        year2 = dateParts2[2].trim();
        System.out.println ("FECHAINGRESO sin java sql: " +month);
        System.out.println ("FECHAINGRESO sin java sql: " +day);
        System.out.println ("FECHAINGRESO sin java sql: " +year);
               
        System.out.println ("FECHAINGRESO sin java sql: " +month2);
        System.out.println ("FECHAINGRESO sin java sql: " +day2);
        System.out.println ("FECHAINGRESO sin java sql: " +year2);
        mes = retornarMes(month);
        mes2 = retornarMes(month2);
                                
        System.out.println(NOMBRE);
        System.out.println(APELLIDOPAT);
        System.out.println(APELLIDOMAT);
        System.out.println(DIRECCION);
        System.out.println(TELEFONO);
        System.out.println(EMAIL);
        System.out.println(ID_COMUNA);
        System.out.println(FECHAIN);        
        System.out.println(FECHANA);
        System.out.println(RADIO);
        System.out.println(LICENCIA);
        System.out.println(DETALLE);
        System.out.println(FOTO);
        System.out.println("TERMINO CARGA ADMIN");
           
        return personal;
    }
    
    public personal ObtenerMecanico (String RUT) throws SQLException, IOException{
        personal personal = null;
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaMecanicos(?,?,?,?,?,?,?,?,?,?,?,?); END;");

         System.out.println("***INICIO CARGA MECANICO***");
         System.out.println("Setiando Parametros ENTRADA");
         cs.setString(1, RUT);

         System.out.println("Setiando Parametros SALIDA");
         cs.registerOutParameter(2, Types.VARCHAR);                
         cs.registerOutParameter(3, Types.VARCHAR);
         cs.registerOutParameter(4, Types.VARCHAR);
         cs.registerOutParameter(5, Types.VARCHAR);
         cs.registerOutParameter(6, Types.INTEGER);                
         cs.registerOutParameter(7, Types.VARCHAR);
         cs.registerOutParameter(8, Types.INTEGER);
         cs.registerOutParameter(9, Types.DATE);
         cs.registerOutParameter(10, Types.DATE);                                
         cs.registerOutParameter(11, Types.VARCHAR);
         cs.registerOutParameter(12, Types.BLOB);
                                
         cs.execute();         
         //Asignacion a las variables
         this.RUT = RUT;
         NOMBRE = cs.getOracleObject(2).stringValue();                
         APELLIDOPAT = cs.getOracleObject(3).stringValue();
         APELLIDOMAT = cs.getOracleObject(4).stringValue();
         DIRECCION = cs.getOracleObject(5).stringValue();
         TELEFONO = cs.getOracleObject(6).intValue();
         EMAIL = cs.getOracleObject(7).stringValue();
         ID_COMUNA = cs.getOracleObject(8).intValue();         
         String query = "Select nombre from comuna where id_comuna="+ID_COMUNA+"";
         ResultSet rs = Conexion.ejecutarQuery(query);
         while (rs.next()){
             NOMBRE_COMUNA = rs.getString("nombre");
             }
         FECHAIN = cs.getOracleObject(9).stringValue();                
         FECHANA = cs.getOracleObject(10).stringValue();                
         DETALLE = cs.getOracleObject(11).stringValue();                
         FOTO = cs.getBytes(12);
         
         String FINGRESO = FECHAIN.substring(0, 10);
         String FNACIMIENTO = FECHANA.substring(0, 10);
         String dateParts1[] = FINGRESO.split("/");
         String dateParts2[] = FNACIMIENTO.split("/");
         month  = dateParts1[0];
         day  = dateParts1[1];
         year = dateParts1[2].trim();
         month2  = dateParts2[0];
         day2  = dateParts2[1];
         year2 = dateParts2[2].trim();
         System.out.println ("FECHAINGRESO sin java sql: " +month);
         System.out.println ("FECHAINGRESO sin java sql: " +day);
         System.out.println ("FECHAINGRESO sin java sql: " +year);
                
         System.out.println ("FECHAINGRESO sin java sql: " +month2);
         System.out.println ("FECHAINGRESO sin java sql: " +day2);
         System.out.println ("FECHAINGRESO sin java sql: " +year2);
         mes = retornarMes(month);
         mes2 = retornarMes(month2);
                                
         System.out.println("IMPRIMIENDO FOTO: "+FOTO);
         //Hasta aqui el codigo funciona bien!
         System.out.println(NOMBRE);                
         System.out.println(APELLIDOPAT);
         System.out.println(APELLIDOMAT);
         System.out.println(DIRECCION);
         System.out.println(TELEFONO);
         System.out.println(EMAIL);
         System.out.println(ID_COMUNA);
         System.out.println(FECHAIN);                
         System.out.println(FECHANA);                
         System.out.println(DETALLE);
         System.out.println(FOTO);         
         System.out.println("TERMINO CARGA MECANICOS");
           
        return personal;
    }
    
    public personal ObtenerEncargadoB (String RUT) throws SQLException, IOException{
        personal personal = null;
        Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
        OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN CargaEncargadoB(?,?,?,?,?,?,?,?,?,?,?,?,?); END;");

        System.out.println("***INICIO CARGA MECANICO***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setString(1, RUT);

        System.out.println("***INICIO CARGA ENCARGADO BODEGA***");
        System.out.println("Setiando Parametros ENTRADA");
        cs.setString(1, RUT);

        System.out.println("Setiando Parametros SALIDA");
        cs.registerOutParameter(2, Types.VARCHAR);
        cs.registerOutParameter(3, Types.VARCHAR);
        cs.registerOutParameter(4, Types.VARCHAR);
        cs.registerOutParameter(5, Types.VARCHAR);
        cs.registerOutParameter(6, Types.VARCHAR);
        cs.registerOutParameter(7, Types.INTEGER);                
        cs.registerOutParameter(8, Types.VARCHAR);
        cs.registerOutParameter(9, Types.INTEGER);
        cs.registerOutParameter(10, Types.DATE);
        cs.registerOutParameter(11, Types.DATE);                                
        cs.registerOutParameter(12, Types.VARCHAR);
        cs.registerOutParameter(13, Types.BLOB);
                               
        cs.execute();
                
        //Asignacion a las variables
        this.RUT = RUT;
        NOMBRE = cs.getOracleObject(2).stringValue();
        CONTRASENA = cs.getOracleObject(3).stringValue();
        APELLIDOPAT = cs.getOracleObject(4).stringValue();
        APELLIDOMAT = cs.getOracleObject(5).stringValue();
        DIRECCION = cs.getOracleObject(6).stringValue();
        TELEFONO = cs.getOracleObject(7).intValue();
        EMAIL = cs.getOracleObject(8).stringValue();
        ID_COMUNA = cs.getOracleObject(9).intValue();        
        String query = "Select nombre from comuna where id_comuna="+ID_COMUNA+"";
        ResultSet rs = Conexion.ejecutarQuery(query);
        while (rs.next()){
            NOMBRE_COMUNA = rs.getString("nombre");
            }
        FECHAIN = cs.getOracleObject(10).stringValue();                
        FECHANA = cs.getOracleObject(11).stringValue();        
        System.out.println("FECHA INGRESO " +FECHAIN);
        System.out.println("FECHA NACIMIENTO " +FECHANA);
        
        DETALLE = cs.getOracleObject(12).stringValue();                         
        FOTO = cs.getBytes(13);
        String FINGRESO = FECHAIN.substring(0, 10);
        String FNACIMIENTO = FECHANA.substring(0, 10);
        String dateParts1[] = FINGRESO.split("/");
        String dateParts2[] = FNACIMIENTO.split("/");
        month  = dateParts1[0];
        day  = dateParts1[1];
        year = dateParts1[2].trim();
        month2  = dateParts2[0];
        day2  = dateParts2[1];
        year2 = dateParts2[2].trim();
        System.out.println ("FECHAINGRESO sin java sql: " +month);
        System.out.println ("FECHAINGRESO sin java sql: " +day);
        System.out.println ("FECHAINGRESO sin java sql: " +year);
               
        System.out.println ("FECHAINGRESO sin java sql: " +month2);
        System.out.println ("FECHAINGRESO sin java sql: " +day2);
        System.out.println ("FECHAINGRESO sin java sql: " +year2);
        mes = retornarMes(month);
        mes2 = retornarMes(month2);
                                
        System.out.println("IMPRIMIENDO FOTO: "+FOTO);
        //Hasta aqui el codigo funciona bien!
        System.out.println(NOMBRE);
        System.out.println(CONTRASENA);
        System.out.println(APELLIDOPAT);
        System.out.println(APELLIDOMAT);
        System.out.println(DIRECCION);
        System.out.println(TELEFONO);
        System.out.println(EMAIL);
        System.out.println(ID_COMUNA);
        System.out.println(FECHAIN);                
        System.out.println(FECHANA);                
        System.out.println(DETALLE);
        System.out.println(FOTO);
        System.out.println("TERMINO CARGA ENCARGADO BODEGA");
           
        return personal;
    }
    
    public void borrarPersonal(String RUT, String CUENTA){
        try {
            if ("Administrador".equals(CUENTA)){
                Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
                OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarAdministrador(?); END;");
                cs.setString(1, RUT);
                cs.executeUpdate();
                JOptionPane.showMessageDialog(null, "Administrador Borrado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
            }
            else if("Conductor".equals(CUENTA)){
                Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
                OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarConductor(?); END;");
                cs.setString(1, RUT);
                cs.executeUpdate();                                        
                JOptionPane.showMessageDialog(null, "Conductor Borrado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                                                        
            }
            else if("Mecanico".equals(CUENTA)){
                Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
                OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarMecanico(?); END;");
                cs.setString(1, RUT);
                cs.executeUpdate();
                JOptionPane.showMessageDialog(null, "Mecanico Borrado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                                                        
            }
            else if("Encargado Bodega".equals(CUENTA)){
                Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
                OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BorrarEncargadoB(?); END;");
                cs.setString(1, RUT);
                cs.executeUpdate();
                JOptionPane.showMessageDialog(null, "Encargado de Bodega Borrado Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                                                        
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(falla.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se puede borrar la falla debido a que ya ha sido registrada en ordenes de trabajo", "Mensajero", JOptionPane.ERROR_MESSAGE);
        }
                    
    }
    
    public void actualizarAdminstrador(personal p) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de actualizacion Administrador");
            
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarAdministrador(?,?,?,?,?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, p.getRUT());
            cs.setString(2, p.getNOMBRE());
            cs.setString(3, p.getCONTRASENA());
            cs.setString(4, p.getAPELLIDOPAT());
            cs.setString(5, p.getAPELLIDOMAT());
            cs.setString(6, p.getDIRECCION());
            cs.setInt(7, p.getTELEFONO());
            cs.setString(8, p.getEMAIL());
            cs.setInt(9, p.getID_COMUNA());
            cs.setDate(10, p.getFECHA_NACIMIENTO());
            cs.setString(11, p.getDETALLE());
            cs.setBytes(12, p.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
                        
            System.out.println("TERMINO del Stored Procedure de actualizacion Administrador");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }    
    }
    
    
    public void actualizarMecanico(personal p) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de actualizacion Mecanico");
            
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarMecanico(?,?,?,?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, p.getRUT());
            cs.setString(2, p.getNOMBRE());            
            cs.setString(3, p.getAPELLIDOPAT());
            cs.setString(4, p.getAPELLIDOMAT());
            cs.setString(5, p.getDIRECCION());
            cs.setInt(6, p.getTELEFONO());
            cs.setString(7, p.getEMAIL());
            cs.setInt(8, p.getID_COMUNA());
            cs.setDate(9, p.getFECHA_NACIMIENTO());
            cs.setString(10, p.getDETALLE());
            cs.setBytes(11, p.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();            
            
            System.out.println("TERMINO del Stored Procedure de actualizacion Mecanico");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }    
    }
    
    public void actualizarEncargadoB(personal p) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de actualizacion Encargado Bodega");
            
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarEncargadoB(?,?,?,?,?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, p.getRUT());
            cs.setString(2, p.getNOMBRE());
            cs.setString(3, p.getCONTRASENA());
            cs.setString(4, p.getAPELLIDOPAT());
            cs.setString(5, p.getAPELLIDOMAT());
            cs.setString(6, p.getDIRECCION());
            cs.setInt(7, p.getTELEFONO());
            cs.setString(8, p.getEMAIL());
            cs.setInt(9, p.getID_COMUNA());
            cs.setDate(10, p.getFECHA_NACIMIENTO());
            cs.setString(11, p.getDETALLE());
            cs.setBytes(12, p.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
                        
            System.out.println("TERMINO del Stored Procedure de actualizacion Encargado Bodega");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }    
    }
    
    public void registrarConductor(personal conductor) throws SQLException{
        try {
            //LISTO
            System.out.println("INICIO del Stored Procedure de insercion Conductor");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarConductor(?,?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, conductor.getRUT());
            cs.setString(2,conductor.getNOMBRE());
            cs.setString(3,conductor.getAPELLIDOPAT());
            cs.setString(4,conductor.getAPELLIDOMAT());
            cs.setString(5, conductor.getDIRECCION());
            cs.setInt(6,conductor.getTELEFONO());
            cs.setString(7, conductor.getEMAIL());
            cs.setInt(8, conductor.getID_COMUNA());
            cs.setDate(9, conductor.getFECHA_INGRESO());            
            cs.setDate(10, conductor.getFECHA_NACIMIENTO());
            cs.setInt(11, conductor.getRADIO());
            cs.setString(12, conductor.getLICENCIA());
            cs.setString(13, conductor.getDETALLE());
            cs.setBytes(14, conductor.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion Conductor");
        } catch (SQLException ex) {
            ex.printStackTrace();    
        }        
    }
        
    public void registrarEncargadoBodega(personal encargadob) throws SQLException{
        try {            
            System.out.println("INICIO del Stored Procedure de insercion Encargado Bodega");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarEncargadoB(?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, encargadob.getRUT());
            cs.setString(2, encargadob.getNOMBRE());
            cs.setString(3, encargadob.getCONTRASENA());
            cs.setString(4, encargadob.getAPELLIDOPAT());
            cs.setString(5, encargadob.getAPELLIDOMAT());
            cs.setString(6, encargadob.getDIRECCION());
            cs.setInt(7, encargadob.getTELEFONO());
            cs.setString(8, encargadob.getEMAIL());
            cs.setInt(9, encargadob.getID_COMUNA());
            cs.setDate(10, encargadob.getFECHA_INGRESO());            
            cs.setDate(11, encargadob.getFECHA_NACIMIENTO());            
            cs.setString(12, encargadob.getDETALLE());
            cs.setBytes(13, encargadob.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion Encargado Bodega");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }    
    
    public void registrarMecanico(personal mecanico) throws SQLException{
        try {            
            System.out.println("INICIO del Stored Procedure de insercion Mecanico");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarMecanico(?,?,?,?,?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, mecanico.getRUT());
            cs.setString(2, mecanico.getNOMBRE());            
            cs.setString(3, mecanico.getAPELLIDOPAT());
            cs.setString(4, mecanico.getAPELLIDOMAT());
            cs.setString(5, mecanico.getDIRECCION());
            cs.setInt(6, mecanico.getTELEFONO());
            cs.setString(7, mecanico.getEMAIL());
            cs.setInt(8, mecanico.getID_COMUNA());
            cs.setDate(9, mecanico.getFECHA_INGRESO());            
            cs.setDate(10, mecanico.getFECHA_NACIMIENTO());            
            cs.setString(11, mecanico.getDETALLE());
            cs.setBytes(12, mecanico.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion Mecanico");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }          
    
    public void registrarAdministrador(personal administrador) throws SQLException{
        try {
            //LISTO
            System.out.println("INICIO del Stored Procedure de insercion Administrador");
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin registrarAdministrador(?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, administrador.getRUT());
            cs.setString(2, administrador.getNOMBRE());
            cs.setString(3, administrador.getCONTRASENA());
            cs.setString(4, administrador.getAPELLIDOPAT());
            cs.setString(5, administrador.getAPELLIDOMAT());
            cs.setString(6, administrador.getDIRECCION());
            cs.setInt(7, administrador.getTELEFONO());
            cs.setString(8, administrador.getEMAIL());
            cs.setInt(9, administrador.getID_COMUNA());
            cs.setDate(10, administrador.getFECHA_INGRESO());            
            cs.setDate(11, administrador.getFECHA_NACIMIENTO());            
            cs.setString(12, administrador.getDETALLE());
            cs.setBytes(13, administrador.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();
            System.out.println("TERMINO del Stored Procedure de insercion Administrador");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }
        
    }       
    
    public void actualizarConductor(personal p) throws SQLException{
        try {
            System.out.println("INICIO del Stored Procedure de actualizacion Conductor");
            
            OracleCallableStatement cs = (OracleCallableStatement) Conexion.con.prepareCall("begin actualizarConductor(?,?,?,?,?,?,?,?,?,?,?,?,?); end;");
            System.out.println("AQUI YA LLAME AL STORED PROCEDURE");
            cs.setString(1, p.getRUT());
            cs.setString(2, p.getNOMBRE());
            cs.setString(3, p.getAPELLIDOPAT());
            cs.setString(4, p.getAPELLIDOMAT());
            cs.setString(5, p.getDIRECCION());
            cs.setInt(6, p.getTELEFONO());
            cs.setString(7, p.getEMAIL());
            cs.setInt(8, p.getID_COMUNA());
            cs.setDate(9, p.getFECHA_NACIMIENTO());
            cs.setInt(10, p.getRADIO());
            cs.setString(11, p.getLICENCIA());            
            cs.setString(12, p.getDETALLE());
            cs.setBytes(13, p.getFOTO());            
            cs.executeUpdate();

            System.out.println("\nBlob succesfully inserted");
            Conexion.con.commit();            
            
            System.out.println("TERMINO del Stored Procedure de actualizacion Administrador");
        } catch (SQLException ex) {
            ex.printStackTrace();
    
        }    
    }
    
    public static String getMD5(String clear) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] b = md.digest(clear.getBytes());
        int size = b.length;
        @SuppressWarnings("StringBufferMayBeStringBuilder")
        StringBuffer h = new StringBuffer(size);
        //algoritmo y arreglo md5
            for (int i = 0; i < size; i++) {
                int u = b[i] & 255;
                    if (u < 16) {
                        h.append("0" + Integer.toHexString(u));
                    }
                else {
                        h.append(Integer.toHexString(u));
                }
            }
        //clave encriptada
        return h.toString();
    } 
    
    public personal LoginPersonal(String TIPOC, String RUT, String PW) throws SQLException{
        personal personal = null;
        if ("Encargado Bodega".equals(TIPOC)){
              
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BuscarEncargadoB(?,?,?,?,?); END;");

             System.out.println("***INICIO CARGA ENCARGADO***");
             System.out.println("Setiando Parametros ENTRADA");
             cs.setString(1, RUT);
             cs.setString(2, PW);

             System.out.println("Setiando Parametros SALIDA");
             cs.registerOutParameter(3, Types.VARCHAR);                
             cs.registerOutParameter(4, Types.VARCHAR);
             cs.registerOutParameter(5, Types.VARCHAR);
             cs.execute();
             
             this.RUT = RUT;
             this.CONTRASENA = PW;
             NOMBRE = cs.getOracleObject(3).stringValue();
             APELLIDOPAT = cs.getOracleObject(4).stringValue();
             APELLIDOMAT = cs.getOracleObject(5).stringValue();                                                   
             return personal;
        }
        
        else if("Administrador".equals(TIPOC)){
        
            Connection con = DriverManager.getConnection(Conexion.url, Conexion.usuario, Conexion.clave);
            OracleCallableStatement cs = (OracleCallableStatement) con.prepareCall("BEGIN BuscarAdmin(?,?,?,?,?); END;");

             System.out.println("***INICIO CARGA ENCARGADO***");
             System.out.println("Setiando Parametros ENTRADA");
             cs.setString(1, RUT);
             cs.setString(2, PW);

             System.out.println("Setiando Parametros SALIDA");
             cs.registerOutParameter(3, Types.VARCHAR);                
             cs.registerOutParameter(4, Types.VARCHAR);
             cs.registerOutParameter(5, Types.VARCHAR);
             cs.execute();
             
             this.RUT = RUT;
             this.CONTRASENA = PW;
             NOMBRE = cs.getOracleObject(3).stringValue();
             APELLIDOPAT = cs.getOracleObject(4).stringValue();
             APELLIDOMAT = cs.getOracleObject(5).stringValue();                                      
             return personal;
                         
        
        }
        return personal;
            
    }        
    
}
