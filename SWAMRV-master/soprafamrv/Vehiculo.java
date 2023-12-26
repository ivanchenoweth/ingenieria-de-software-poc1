/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Vehiculos.java
 *
 * Created on 28-12-2011, 11:19:31 PM
 */
package soprafamrv;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import org.jdesktop.application.Action;
import soprafamrv.BD.Conexion;
import soprafamrv.SISTEMA.miPanel;
import soprafamrv.SISTEMA.subeImagen;
import soprafamrv.SISTEMA.vehiculo;


/**
 *
 * @author Cri
 */
public class Vehiculo extends javax.swing.JInternalFrame {

    /** Creates new form Vehiculos */
    public Vehiculo() throws SQLException {
        initComponents();
        CargarVehiculos();    
        AsignarFechaIngreso();
    }
    byte[] IMAGEN;
    int contador = 0;
    int contador2 = 0;
    
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
    
    @Action
    public void guardar() throws SQLException, ParseException{
        System.out.println("Creación de objetos");
        subeImagen s = new subeImagen();
        vehiculo v = new vehiculo();
        
        System.out.println("INICIO DEFINICION DE VARIABLES");
        String PATENTE = this.JFPATENTE.getText().toUpperCase();
        String CHASIS = this.JFCHASIS.getText().toUpperCase().trim();
        int ANO = Integer.parseInt(this.JFANO.getText().trim());
        String COLOR = (String) this.JCCOLOR.getText().toUpperCase();
        String MARCA = (String) this.JCMARCA.getText().toUpperCase();
        String MODELO = (String) this.JCMODELO.getText().toUpperCase();
        String FECHA_INGRESO = this.JCFECHAING1.getSelectedItem() + "-" + this.JCFECHAING2.getSelectedItem() + "-" + this.JCFECHAING3.getSelectedItem();
        //byte[] FOTO = s.obtenerBytes();
        byte[] FOTO = IMAGEN;
        if (contador2 == 1){
            System.out.println("numero contador2: " +contador2);
            FOTO = s.ObtenerBytes();    
        }
        else if(contador2 == 2){
            FOTO = s.ObtenerBytes();    
        }
        
        System.out.println("Definicion formato fecha");
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMMMMMMMMM-yyyy", new Locale("es", "ES"));
        java.sql.Date ingreso = new java.sql.Date(sdf.parse(FECHA_INGRESO).getTime());
        
        
        v.setPATENTE(PATENTE);
        v.setCHASIS(CHASIS);
        v.setANO(ANO);
        v.setCOLOR(COLOR);
        v.setMARCA(MARCA);
        v.setMODELO(MODELO);        
        v.setFECHA_INGRESO(ingreso);
        v.setFOTO(FOTO);
        
        System.out.println("Termino Definición Variables");
        vehiculo conexionVehiculo = new vehiculo();
        
        
        System.out.println("INICIO impresion variables asignadas");
        System.out.println("PATENTE: " +v.getPATENTE());
        System.out.println("CHASIS: " +v.getCHASIS());
        System.out.println("AÑO: " +v.getANO());
        System.out.println("COLOR: " +v.getCOLOR());
        System.out.println("MARCA: " +v.getMARCA());
        System.out.println("MODELO: " +v.getMODELO());
        System.out.println("FECHA_IN: " +v.getFECHA_INGRESO());        
        System.out.println("FOTO: " +v.getFOTO());
        System.out.println("TERMINO impresion variables asignadas");
        if (contador == 2){
            System.out.println("NUMERO CONTADOR DENTRO DEL IF: " +contador);
            if (v.getPATENTE() != null && v.getCHASIS() != null && v.getANO() != 0 && !"Seleccionar Color".equals(v.getCOLOR()) && !"Seleccionar Marca".equals(v.getMARCA()) && !"Seleccionar Modelo".equals(v.getMODELO()) && v.getFOTO() != null){
                int n = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea Guardar?", "Mensajero", JOptionPane.YES_NO_CANCEL_OPTION);
                //n = 0 es YES, n = 1 es NO, n = 2 es Cancel
                System.out.println("numero" + JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    conexionVehiculo.actualizarVehiculo(v);
                    ResetearCampos();
                    CargarVehiculos();  
                    HabilitarCampos(false);
                    contador = 0;
                    contador2 = 0;                        
                    JPanelImagen.removeAll();        
                    JPanelImagen.repaint();
                    CargarVehiculos();  
                    } else if (n == 1) {
                        ResetearCampos();
                }                
                }else{
                JOptionPane.showMessageDialog(null,"Codigo: " +"Debe llenartodos los campos solicitados", "Error", JOptionPane.ERROR_MESSAGE); 
            }
            
        }
        else{
             if (v.getPATENTE() != null && v.getCHASIS() != null && v.getANO() != 0 && !"Seleccionar Color".equals(v.getCOLOR()) && !"Seleccionar Marca".equals(v.getMARCA()) && !"Seleccionar Modelo".equals(v.getMODELO()) && v.getFOTO() != null){
                int n = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea Guardar?", "Mensajero", JOptionPane.YES_NO_CANCEL_OPTION);
                //n = 0 es YES, n = 1 es NO, n = 2 es Cancel
                System.out.println("numero" + JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    conexionVehiculo.registrarVehiculo(v);
                    ResetearCampos();
                    CargarVehiculos();                      
                    contador = 0;
                    contador2 = 0;
                    HabilitarCampos(false);                                                                    
                    CargarVehiculos();  
                    } else if (n == 1) {
                        ResetearCampos();
                    }                
                }else{
                JOptionPane.showMessageDialog(null,"Codigo: " +"Debe llenar todos los campos solicitados", "Error", JOptionPane.ERROR_MESSAGE); 
            }
        }
                
    }
    
    private void cargaVehiculoConductor(){
   try {
            String query = "Select * from buscarconductor";
            ResultSet rs = Conexion.ejecutarQuery(query);

            while (rs.next()) {
                this.JCConductor.addItem(rs.getString("rut_conductor")+ " - " +rs.getString("nombre")+ " " +rs.getString("apellido_paterno")+ " " +rs.getString("apellido_materno"));               

            }
            
            String query2 = "Select * from buscarvehiculo";
            ResultSet rs2 = Conexion.ejecutarQuery(query2);
            while (rs2.next()) {                
                this.JCVEHICULO.addItem(rs2.getString("patente")+ " - " +rs2.getString("marca")+ " " +rs2.getString("modelo")+ " " +rs2.getString("ano"));

            }
            //rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
                    
    private void HabilitarCampos(boolean x) {
        this.JFPATENTE.setEnabled(x);
        this.JFCHASIS.setEnabled(x);
        this.JFANO.setEnabled(x);
        this.JCCOLOR.setEnabled(x);
        this.JCMARCA.setEnabled(x);
        this.JCMODELO.setEnabled(x);
        this.jButton3.setEnabled(x);
        this.jButton1.setEnabled(x);        
    }
    
    private void CargarVehiculos() throws SQLException{
        TablaVehiculo.removeAll();
        String query = "Select * from DatosVehiculo";
        ResultSet rs = Conexion.ejecutarQuery(query);
        vehiculo.llenarTablaVehiculos(TablaVehiculo, rs);    
        String query2 = "Select * from buscarvehiculo";
        ResultSet rs2 = Conexion.ejecutarQuery(query2);
        while (rs2.next()) {                
            this.JCPATENTE.addItem(rs2.getString("patente")+ " - " +rs2.getString("marca")+ " " +rs2.getString("modelo")+ " " +rs2.getString("ano"));
        }
    }
    
    private void AsignarFechaIngreso() {
        //Puedo jugar con DD MM YYYY para mostrarlos individualmente
        DateFormat dia = new SimpleDateFormat("dd");
        DateFormat mes = new SimpleDateFormat("MMMMMMMMMM");
        DateFormat ano = new SimpleDateFormat("yyyy");
        //DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();

        this.JCFECHAING1.addItem(dia.format(date));
        this.JCFECHAING2.addItem(mes.format(date).toUpperCase());
        this.JCFECHAING3.addItem(ano.format(date));
        this.JCFECHAING4.addItem(dia.format(date));
        this.JCFECHAING5.addItem(mes.format(date).toUpperCase());
        this.JCFECHAING6.addItem(ano.format(date));
                
        this.JCFECHAING1.setSelectedIndex(1);
        this.JCFECHAING2.setSelectedIndex(1);
        this.JCFECHAING3.setSelectedIndex(1);
        this.JCFECHAING4.setSelectedIndex(1);
        this.JCFECHAING5.setSelectedIndex(1);
        this.JCFECHAING6.setSelectedIndex(1);
    }
    
    private void ResetearCampos() {
        IMAGEN = null;
        JPanelImagen.removeAll();        
        JPanelImagen.repaint();
        this.JFPATENTE.setText(null);
        this.JFCHASIS.setText(null);
        this.JFANO.setText(null);
        this.JCCOLOR.setText(null);
        this.JCMARCA.setText(null);
        this.JCMODELO.setText(null);
        
        this.JCFECHAING1.setSelectedIndex(0);
        this.JCFECHAING2.setSelectedIndex(0);
        this.JCFECHAING3.setSelectedIndex(0);
        this.JPanelImagen.removeAll();
        this.JPanelImagen.repaint();
        this.JCConductor.setSelectedIndex(0);
        this.JCVEHICULO.setSelectedIndex(0);
        this.JTDESCRIPCION.setText(null);

    }

        
        

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser = new javax.swing.JFileChooser();
        JDConductorVehiculo = new javax.swing.JDialog();
        jLabel12 = new javax.swing.JLabel();
        JCConductor = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        JCVEHICULO = new javax.swing.JComboBox();
        JCFECHAING4 = new javax.swing.JComboBox();
        jLabel18 = new javax.swing.JLabel();
        JCFECHAING5 = new javax.swing.JComboBox();
        jLabel19 = new javax.swing.JLabel();
        JCFECHAING6 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTDESCRIPCION = new javax.swing.JTextArea();
        jButton2 = new javax.swing.JButton();
        jToolBar1 = new javax.swing.JToolBar();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        JFCHASIS = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JCFECHAING3 = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        JFPATENTE = new javax.swing.JFormattedTextField();
        JCFECHAING2 = new javax.swing.JComboBox();
        JCFECHAING1 = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        JPanelImagen = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        JFANO = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();
        JCCOLOR = new javax.swing.JFormattedTextField();
        JCMARCA = new javax.swing.JFormattedTextField();
        JCMODELO = new javax.swing.JFormattedTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaVehiculo = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaVehiculoConductor = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        JCPATENTE = new javax.swing.JComboBox();

        jFileChooser.setName("jFileChooser"); // NOI18N

        JDConductorVehiculo.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(soprafamrv.SOPRAFAMRVApp0.class).getContext().getResourceMap(Vehiculo.class);
        JDConductorVehiculo.setTitle(resourceMap.getString("JDConductorVehiculo.title")); // NOI18N
        JDConductorVehiculo.setName("JDConductorVehiculo"); // NOI18N
        JDConductorVehiculo.setResizable(false);

        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        JCConductor.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Conductor" }));
        JCConductor.setName("JCConductor"); // NOI18N

        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        JCVEHICULO.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Vehículo" }));
        JCVEHICULO.setName("JCVEHICULO"); // NOI18N

        JCFECHAING4.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Día" }));
        JCFECHAING4.setEnabled(false);
        JCFECHAING4.setName("JCFECHAING4"); // NOI18N

        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        JCFECHAING5.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mes" }));
        JCFECHAING5.setEnabled(false);
        JCFECHAING5.setName("JCFECHAING5"); // NOI18N

        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        JCFECHAING6.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Año" }));
        JCFECHAING6.setEnabled(false);
        JCFECHAING6.setName("JCFECHAING6"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        JTDESCRIPCION.setColumns(20);
        JTDESCRIPCION.setLineWrap(true);
        JTDESCRIPCION.setRows(5);
        JTDESCRIPCION.setName("JTDESCRIPCION"); // NOI18N
        jScrollPane2.setViewportView(JTDESCRIPCION);

        jButton2.setText(resourceMap.getString("jButton2.text")); // NOI18N
        jButton2.setName("jButton2"); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout JDConductorVehiculoLayout = new javax.swing.GroupLayout(JDConductorVehiculo.getContentPane());
        JDConductorVehiculo.getContentPane().setLayout(JDConductorVehiculoLayout);
        JDConductorVehiculoLayout.setHorizontalGroup(
            JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDConductorVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JDConductorVehiculoLayout.createSequentialGroup()
                        .addGroup(JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel16)
                            .addComponent(jLabel12)
                            .addComponent(jLabel14))
                        .addGap(18, 18, 18)
                        .addGroup(JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                            .addComponent(JCVEHICULO, 0, 458, Short.MAX_VALUE)
                            .addGroup(JDConductorVehiculoLayout.createSequentialGroup()
                                .addComponent(JCFECHAING4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JCFECHAING5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addGap(4, 4, 4)
                                .addComponent(JCFECHAING6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(JCConductor, 0, 458, Short.MAX_VALUE)))
                    .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        JDConductorVehiculoLayout.setVerticalGroup(
            JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JDConductorVehiculoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(JCConductor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(JCVEHICULO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(JCFECHAING4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18)
                    .addComponent(JCFECHAING5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(JCFECHAING6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(JDConductorVehiculoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N
        setPreferredSize(new java.awt.Dimension(1280, 700));

        jToolBar1.setBorder(null);
        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);
        jToolBar1.setName("jToolBar1"); // NOI18N

        jButton4.setText(resourceMap.getString("jButton4.text")); // NOI18N
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setName("jButton4"); // NOI18N
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setText(resourceMap.getString("jButton5.text")); // NOI18N
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setName("jButton5"); // NOI18N
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setText(resourceMap.getString("jButton6.text")); // NOI18N
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setName("jButton6"); // NOI18N
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        jButton9.setText(resourceMap.getString("jButton9.text")); // NOI18N
        jButton9.setFocusable(false);
        jButton9.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton9.setName("jButton9"); // NOI18N
        jButton9.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton9);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        try {
            JFCHASIS.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("**********")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        JFCHASIS.setText(resourceMap.getString("JFCHASIS.text")); // NOI18N
        JFCHASIS.setEnabled(false);
        JFCHASIS.setName("JFCHASIS"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        JCFECHAING3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Año" }));
        JCFECHAING3.setEnabled(false);
        JCFECHAING3.setName("JCFECHAING3"); // NOI18N

        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        try {
            JFPATENTE.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("******")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        JFPATENTE.setText(resourceMap.getString("JFPATENTE.text")); // NOI18N
        JFPATENTE.setEnabled(false);
        JFPATENTE.setName("JFPATENTE"); // NOI18N

        JCFECHAING2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Mes" }));
        JCFECHAING2.setEnabled(false);
        JCFECHAING2.setName("JCFECHAING2"); // NOI18N

        JCFECHAING1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Día" }));
        JCFECHAING1.setEnabled(false);
        JCFECHAING1.setName("JCFECHAING1"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jButton3.setText(resourceMap.getString("jButton3.text")); // NOI18N
        jButton3.setEnabled(false);
        jButton3.setName("jButton3"); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        JPanelImagen.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("JPanelImagen.border.title"))); // NOI18N
        JPanelImagen.setName("JPanelImagen"); // NOI18N

        javax.swing.GroupLayout JPanelImagenLayout = new javax.swing.GroupLayout(JPanelImagen);
        JPanelImagen.setLayout(JPanelImagenLayout);
        JPanelImagenLayout.setHorizontalGroup(
            JPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 343, Short.MAX_VALUE)
        );
        JPanelImagenLayout.setVerticalGroup(
            JPanelImagenLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 274, Short.MAX_VALUE)
        );

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        try {
            JFANO.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("####")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        JFANO.setText(resourceMap.getString("JFANO.text")); // NOI18N
        JFANO.setEnabled(false);
        JFANO.setName("JFANO"); // NOI18N

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(soprafamrv.SOPRAFAMRVApp0.class).getContext().getActionMap(Vehiculo.class, this);
        jButton1.setAction(actionMap.get("guardar")); // NOI18N
        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        JCCOLOR.setText(resourceMap.getString("JCCOLOR.text")); // NOI18N
        JCCOLOR.setEnabled(false);
        JCCOLOR.setName("JCCOLOR"); // NOI18N

        JCMARCA.setText(resourceMap.getString("JCMARCA.text")); // NOI18N
        JCMARCA.setEnabled(false);
        JCMARCA.setName("JCMARCA"); // NOI18N

        JCMODELO.setText(resourceMap.getString("JCMODELO.text")); // NOI18N
        JCMODELO.setEnabled(false);
        JCMODELO.setName("JCMODELO"); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(JPanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(jPanel2Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(JCFECHAING1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(jLabel8)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(JCFECHAING2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel9)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(JCFECHAING3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel2)
                                        .addComponent(jLabel17)
                                        .addComponent(jLabel1))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(JFANO)
                                        .addComponent(JFCHASIS)
                                        .addComponent(JFPATENTE, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE))
                                    .addGap(18, 18, 18)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(JCMARCA, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addComponent(JCCOLOR, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE)
                                        .addComponent(JCMODELO, javax.swing.GroupLayout.DEFAULT_SIZE, 120, Short.MAX_VALUE))))
                            .addGap(0, 0, 0)
                            .addComponent(jButton3))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(JFPATENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(JFCHASIS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(JFANO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(JCCOLOR, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(JCMARCA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(JCMODELO, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JCFECHAING3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(JCFECHAING2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8)
                    .addComponent(JCFECHAING1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(44, 44, 44)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JPanelImagen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel1.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, resourceMap.getColor("jPanel1.border.titleColor"))); // NOI18N
        jPanel1.setName("jPanel1"); // NOI18N

        jButton10.setText(resourceMap.getString("jButton10.text")); // NOI18N
        jButton10.setName("jButton10"); // NOI18N
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel3.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, resourceMap.getColor("jPanel3.border.titleColor"))); // NOI18N
        jPanel3.setName("jPanel3"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        TablaVehiculo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaVehiculo.setName("TablaVehiculo"); // NOI18N
        TablaVehiculo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                TablaVehiculoFocusGained(evt);
            }
        });
        jScrollPane1.setViewportView(TablaVehiculo);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel4.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, resourceMap.getColor("jPanel4.border.titleColor"))); // NOI18N
        jPanel4.setName("jPanel4"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        TablaVehiculoConductor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        TablaVehiculoConductor.setName("TablaVehiculoConductor"); // NOI18N
        jScrollPane3.setViewportView(TablaVehiculoConductor);

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(resourceMap.getString("jPanel5.border.title"))); // NOI18N
        jPanel5.setName("jPanel5"); // NOI18N

        JCPATENTE.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Vehiculo" }));
        JCPATENTE.setName("JCPATENTE"); // NOI18N
        JCPATENTE.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCPATENTEItemStateChanged(evt);
            }
        });
        JCPATENTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JCPATENTEActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JCPATENTE, 0, 211, Short.MAX_VALUE)
                .addGap(368, 368, 368))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JCPATENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 601, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addGap(18, 18, 18)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        if (contador == 2){
            subeImagen m = new subeImagen();
            m.Abrir_Dialogo(JPanelImagen);
            contador2 = 1;
        }
        else{
        subeImagen m = new subeImagen();
        m.Abrir_Dialogo(JPanelImagen);
        contador2 = 2;
        }
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        HabilitarCampos(true);
        ResetearCampos();
        AsignarFechaIngreso();        
    }//GEN-LAST:event_jButton4ActionPerformed

    
    
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
    try {
                JPanelImagen.removeAll();
                HabilitarCampos(false);                                
                int x = this.TablaVehiculo.getSelectedRow();                    
                String PATENTE = (String) TablaVehiculo.getValueAt(x,0);                
                vehiculo v = new vehiculo();                
                v.cargarVehiculo(PATENTE);                
                System.out.println("imprimiendo PATENTE: " +v.getPATENTE());
                System.out.println("imprimiendo CHASIS: " +v.getCHASIS());
                System.out.println("imprimiendo MARCA: " +v.getMARCA());
                System.out.println("imprimiendo MODELO: " +v.getMODELO());
                System.out.println("imprimiendo AÑO: " +v.getANO());
                System.out.println("imprimiendo COLOR: " +v.getCOLOR());
                System.out.println("imprimiendo FECHA INGRESO: " +v.getFECHAIN());
                System.out.println("imprimiendo FOTO: " +v.getFOTO());                
                                                
                IMAGEN = v.getFOTO();
                InputStream z = new ByteArrayInputStream(v.getFOTO());
                BufferedImage FOTO = ImageIO.read(z);                
                               
                String FECHA = v.getFECHAIN().substring(0, 10);
                String dateParts[] = FECHA.split("/");
                String month  = dateParts[0];
                String day  = dateParts[1];
                String year = dateParts[2];
                System.out.println ("FECHAINGRESO sin java sql: " +month);
                System.out.println ("FECHAINGRESO sin java sql: " +day);
                System.out.println ("FECHAINGRESO sin java sql: " +year.substring(0,4));
                String mes = retornarMes(month);
                                                                
                System.out.println("TERMINO CARGA VEHICULO");
                                                
                this.JFPATENTE.setText(v.getPATENTE());
                this.JFCHASIS.setText(v.getCHASIS());
                this.JFANO.setText(String.valueOf(v.getANO()));
                this.JCCOLOR.setText(v.getCOLOR());
                this.JCMARCA.setText(v.getMARCA());
                this.JCMODELO.setText(v.getMODELO());
                
                this.JCFECHAING1.removeAllItems();
                this.JCFECHAING1.addItem(day);
                this.JCFECHAING1.setSelectedIndex(0);
                
                this.JCFECHAING2.removeAllItems();
                this.JCFECHAING2.addItem(mes);
                this.JCFECHAING2.setSelectedIndex(0);
                
                this.JCFECHAING3.removeAllItems();
                this.JCFECHAING3.addItem(year.substring(0,4));
                this.JCFECHAING3.setSelectedIndex(0);
                
                
                JPanelImagen.add(new miPanel(FOTO, JPanelImagen.getSize()));
                JPanelImagen.setVisible(true);
                JPanelImagen.repaint();
                this.jButton3.setEnabled(false);
                this.jButton1.setEnabled(false);
                contador = 1;
                
            }
              catch (IOException ex) {
                Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
    this.JDConductorVehiculo.setVisible(true);
    this.JDConductorVehiculo.setLocation(400, 200);
    this.JDConductorVehiculo.setSize(600,400);
    AsignarFechaIngreso();
    cargaVehiculoConductor();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        try {
            System.out.println("Creación de objetos");
            
            System.out.println("INICIO DEFINICION DE VARIABLES");
            String CONDUCTOR = this.JCConductor.getSelectedItem().toString().substring(0, 12).trim();
            String VEHICULO = this.JCVEHICULO.getSelectedItem().toString().substring(0, 6).trim();
            String FECHA_ASIGNACION = this.JCFECHAING4.getSelectedItem() + "-" + this.JCFECHAING5.getSelectedItem() + "-" + this.JCFECHAING6.getSelectedItem();            
            String DESCRIPCION = this.JTDESCRIPCION.getText().toUpperCase().trim();
            System.out.println("Definicion formato fecha");
            SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MMMMMMMMMM-yyyy", new Locale("es", "ES"));
            java.sql.Date asignacion = new java.sql.Date(sdf.parse(FECHA_ASIGNACION).getTime());
            
            System.out.println("Termino Definición Variables");
            Conexion c = new Conexion();
                        
            System.out.println("INICIO impresion variables asignadas");
            System.out.println("CONDUCTOR: "+CONDUCTOR);
            System.out.println("VEHICULO: "+VEHICULO);
            System.out.println("FECHA-INGRESO: "+FECHA_ASIGNACION);
            System.out.println("DESCRIPCION: "+DESCRIPCION);
            System.out.println("TERMINO impresion variables asignadas");
            
            if (!"Seleccionar Conductor".equals(this.JCConductor.getSelectedItem().toString()) && !"Seleccionar Vehículo".equals(this.JCVEHICULO.getSelectedItem().toString()) && this.JTDESCRIPCION.getText().trim() != null )        {
                int n = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea Guardar?", "Mensajero", JOptionPane.YES_NO_CANCEL_OPTION);
                //n = 0 es YES, n = 1 es NO, n = 2 es Cancel
                System.out.println("numero" + JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    //String query = "begin RegistrarConductor ('"+RUT+"','"+NOMBRE+"','"+APELLIDO_PATERNO+"','"+APELLIDO_MATERNO+"','"+DIRECCION+"',"+TELEFONO+",'"+EMAIL+"',"+COMUNA+",'"+FECHA_INGRESO+"','"+FECHA_RETIRO+"','"+FECHA_NACIMIENTO+"',"+NUM_RADIO+",'"+LICENCIA+"','"+DETALLES+"', "+FOTO+"); end;";       
                    try {
                    c.registrarVehiculoConductor(CONDUCTOR, VEHICULO, asignacion, DESCRIPCION);
                    ResetearCampos();
                    JOptionPane.showMessageDialog(null, "Datos Ingresados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                    
                    } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Se ha producido un error en la inserción", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    }        
                } else if (n == 1) {
                ResetearCampos();
                    }
            }else{
                JOptionPane.showMessageDialog(null,"Codigo: " +"Debe llenartodos los campos solicitados", "Error", JOptionPane.ERROR_MESSAGE); 
                }
        } catch (ParseException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
    if(contador == 1){
        this.jButton3.setEnabled(true);
        this.jButton1.setEnabled(true);        
        HabilitarCampos(true);
        this.JFPATENTE.setEnabled(false);
        contador = 2;
    }
    
}//GEN-LAST:event_jButton6ActionPerformed

private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
    if (contador == 1 || contador == 2){            
        int n = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea Eliminar?", "Mensajero", JOptionPane.YES_NO_OPTION);
        if (n == 0){
                try {
                    vehiculo v = new vehiculo();
                    System.out.println("IMPRIMIENDO N :" +n);
                    String PATENTE = this.JFPATENTE.getText();
                    v.borrarVehiculo(PATENTE);
                    CargarVehiculos();
                    ResetearCampos();
                } catch (SQLException ex) {
                    Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
        }
        else{
            System.out.println("IMPRIMIENDO N :" +n);
            ResetearCampos();
        }
    }
    else {
        JOptionPane.showMessageDialog(null, "Primero debe cargar un Vehiculo", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
    }        
}//GEN-LAST:event_jButton5ActionPerformed

private void JCPATENTEItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCPATENTEItemStateChanged
        try {
            String patente = this.JCPATENTE.getSelectedItem().toString().substring(0, 6);
            String query2 = "Select * from DatosVehiculoConductor where patente = '"+patente+"'";
            ResultSet rs2 = Conexion.ejecutarQuery(query2);
            vehiculo.llenarTablaVehiculoConductor(TablaVehiculoConductor, rs2);
        } catch (SQLException ex) {
            Logger.getLogger(Vehiculo.class.getName()).log(Level.SEVERE, null, ex);
        }
    
             
}//GEN-LAST:event_JCPATENTEItemStateChanged

private void JCPATENTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JCPATENTEActionPerformed

}//GEN-LAST:event_JCPATENTEActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
}//GEN-LAST:event_jButton1ActionPerformed

private void TablaVehiculoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_TablaVehiculoFocusGained
    int y = this.TablaVehiculo.getSelectedColumn();
    int x = this.TablaVehiculo.getSelectedRow();
    
    String patente = (String) TablaVehiculo.getValueAt(x,1); 
    System.out.println("VALOR EN CELDA SELECCIONADA " +TablaVehiculo.getValueAt(x, 0));
}//GEN-LAST:event_TablaVehiculoFocusGained
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField JCCOLOR;
    private javax.swing.JComboBox JCConductor;
    private javax.swing.JComboBox JCFECHAING1;
    private javax.swing.JComboBox JCFECHAING2;
    private javax.swing.JComboBox JCFECHAING3;
    private javax.swing.JComboBox JCFECHAING4;
    private javax.swing.JComboBox JCFECHAING5;
    private javax.swing.JComboBox JCFECHAING6;
    private javax.swing.JFormattedTextField JCMARCA;
    private javax.swing.JFormattedTextField JCMODELO;
    private javax.swing.JComboBox JCPATENTE;
    private javax.swing.JComboBox JCVEHICULO;
    private javax.swing.JDialog JDConductorVehiculo;
    private javax.swing.JFormattedTextField JFANO;
    private javax.swing.JFormattedTextField JFCHASIS;
    private javax.swing.JFormattedTextField JFPATENTE;
    private javax.swing.JPanel JPanelImagen;
    private javax.swing.JTextArea JTDESCRIPCION;
    private javax.swing.JTable TablaVehiculo;
    private javax.swing.JTable TablaVehiculoConductor;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton9;
    private javax.swing.JFileChooser jFileChooser;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
