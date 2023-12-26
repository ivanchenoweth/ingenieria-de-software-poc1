/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import soprafamrv.BD.Conexion;
import soprafamrv.Personal;

/**
 *
 * @author Administrador
 */
public class comuna {
    private int id_comuna;
    private ArrayList<String> nombre = new ArrayList<String>();
    private String descripcion;
    private ArrayList stringList = new ArrayList();

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId_comuna() {
        return id_comuna;
    }

    public void setId_comuna(int id_comuna) {
        this.id_comuna = id_comuna;
    }

    public ArrayList<String> getNobre() {
        return nombre;
    }

    public void setNombre(ArrayList<String> nombre) {
        this.nombre = nombre;
    }

    public ArrayList getStringList() {
        return stringList;
    }

    public void setStringList(ArrayList stringList) {
        this.stringList = stringList;
    }
    
    public void BuscarComuna() throws SQLException {
        
        Personal P = new Personal();
        P.JCCOMUNA.addItem("HOLA");
        /*try {
            
            String query = "Select nombre from buscarcomuna";
            ResultSet rs = Conexion.ejecutarQuery(query);
            
            while (rs.next()){
            P.JCCOMUNA.addItem(rs.getString("nombre"));
            
            }
            //rs.close();
            
            
        } catch (SQLException ex) {
            Logger.getLogger(comuna.class.getName()).log(Level.SEVERE, null, ex);
        }
         * 
         */
        
    }

    

}
