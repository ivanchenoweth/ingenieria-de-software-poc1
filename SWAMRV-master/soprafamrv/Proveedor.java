/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Proveedor.java
 *
 * Created on 11-03-2012, 01:53:16 PM
 */
package soprafamrv;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import soprafamrv.BD.Conexion;
import soprafamrv.SISTEMA.proveedor;

/**
 *
 * @author Administrador
 */
public class Proveedor extends javax.swing.JInternalFrame {

    /** Creates new form Proveedor */
    public Proveedor() {
        initComponents();
        cargarProveedor();
        cargarNuevo();
        this.JFComunaID.setVisible(false);
    }
    
    int contador = 0;
    
    private void cargarNuevo() {
        //Carga las COMUNAS en el comboBox
        try {
            String query = "Select nombre from buscarcomuna";
            ResultSet rs = Conexion.ejecutarQuery(query);

            while (rs.next()) {
                this.JCCOMUNA.addItem(rs.getString("nombre"));
            }
                       
        } catch (SQLException ex) {
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void cargarProveedor(){
        try {
            this.JLProveedor.removeAll();
            String query = "Select nombre from proveedor";
            ResultSet rs = Conexion.ejecutarQuery(query);
            while (rs.next()) {
                this.JLProveedor.add(rs.getString("nombre"));
             }
        } catch (SQLException ex) {
            Logger.getLogger(Falla.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    public void HabilitarCampos(boolean x){
        this.JFNOMBRE.setEnabled(x);
        this.JFDIRE.setEnabled(x);
        this.JCCOMUNA.setEnabled(x);
        this.JFEMAIL.setEnabled(x);
        this.JFTEL.setEnabled(x);
        this.JTDESCRP.setEnabled(x);        
        this.jButton1.setEnabled(x);
    }
    
    public void ResetearCampos(){
        this.JFNOMBRE.setText(null);
        this.JFDIRE.setText(null);
        this.JCCOMUNA.setSelectedIndex(0);
        this.JFEMAIL.setText(null);
        this.JFTEL.setText(null);
        this.JTDESCRP.setText(null);    
        this.JFPATENTE.setText(null);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        JFNOMBRE = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        JFPATENTE = new javax.swing.JFormattedTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        JFDIRE = new javax.swing.JFormattedTextField();
        JFEMAIL = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        JTDESCRP = new javax.swing.JTextArea();
        JFTEL = new javax.swing.JFormattedTextField();
        jLabel6 = new javax.swing.JLabel();
        JCCOMUNA = new javax.swing.JComboBox();
        JFComunaID = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton10 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        JLProveedor = new java.awt.List();
        jToolBar3 = new javax.swing.JToolBar();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(soprafamrv.SOPRAFAMRVApp0.class).getContext().getResourceMap(Proveedor.class);
        setTitle(resourceMap.getString("Form.title")); // NOI18N
        setName("Form"); // NOI18N

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, resourceMap.getString("jPanel2.border.title"), javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, null, resourceMap.getColor("jPanel2.border.titleColor"))); // NOI18N
        jPanel2.setName("jPanel2"); // NOI18N

        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        JFNOMBRE.setEnabled(false);
        JFNOMBRE.setName("JFNOMBRE"); // NOI18N

        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        JFPATENTE.setEnabled(false);
        JFPATENTE.setName("JFPATENTE"); // NOI18N

        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        JFDIRE.setEnabled(false);
        JFDIRE.setName("JFDIRE"); // NOI18N

        JFEMAIL.setText(resourceMap.getString("JFEMAIL.text")); // NOI18N
        JFEMAIL.setEnabled(false);
        JFEMAIL.setName("JFEMAIL"); // NOI18N

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        JTDESCRP.setColumns(20);
        JTDESCRP.setRows(5);
        JTDESCRP.setEnabled(false);
        JTDESCRP.setName("JTDESCRP"); // NOI18N
        jScrollPane2.setViewportView(JTDESCRP);

        try {
            JFTEL.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.MaskFormatter("")));
        } catch (java.text.ParseException ex) {
            ex.printStackTrace();
        }
        JFTEL.setEnabled(false);
        JFTEL.setName("JFTEL"); // NOI18N

        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        JCCOMUNA.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar Comuna" }));
        JCCOMUNA.setEnabled(false);
        JCCOMUNA.setName("JCCOMUNA"); // NOI18N
        JCCOMUNA.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JCCOMUNAItemStateChanged(evt);
            }
        });

        JFComunaID.setText(resourceMap.getString("JFComunaID.text")); // NOI18N
        JFComunaID.setEnabled(false);
        JFComunaID.setName("JFComunaID"); // NOI18N

        jButton1.setText(resourceMap.getString("jButton1.text")); // NOI18N
        jButton1.setEnabled(false);
        jButton1.setName("jButton1"); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel17)
                            .addComponent(jLabel1)
                            .addComponent(jLabel6)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(JFPATENTE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(JFDIRE, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JFEMAIL, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JCCOMUNA, javax.swing.GroupLayout.Alignment.LEADING, 0, 194, Short.MAX_VALUE)
                                    .addComponent(JFTEL, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(JFNOMBRE, javax.swing.GroupLayout.Alignment.LEADING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JFComunaID, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addContainerGap(480, Short.MAX_VALUE)
                        .addComponent(jButton1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(JFPATENTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(JFNOMBRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(JFDIRE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(JCCOMUNA, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(JFComunaID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(JFEMAIL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(JFTEL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addGap(73, 73, 73))
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

        JLProveedor.setName("JLProveedor"); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JLProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jButton10))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(12, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addContainerGap(34, Short.MAX_VALUE))
        );

        jToolBar3.setFloatable(false);
        jToolBar3.setRollover(true);
        jToolBar3.setName("jToolBar3"); // NOI18N

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
        jToolBar3.add(jButton6);

        jButton7.setText(resourceMap.getString("jButton7.text")); // NOI18N
        jButton7.setFocusable(false);
        jButton7.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton7.setName("jButton7"); // NOI18N
        jButton7.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton7);

        jButton8.setText(resourceMap.getString("jButton8.text")); // NOI18N
        jButton8.setFocusable(false);
        jButton8.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton8.setName("jButton8"); // NOI18N
        jButton8.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });
        jToolBar3.add(jButton8);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 641, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 487, Short.MAX_VALUE))
                .addContainerGap(51, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        try {
            proveedor p = new proveedor();
            String NOMBRE = this.JLProveedor.getSelectedItem();
            p.ObtenerProveedor(NOMBRE);
            
            System.out.println("imprimiendo ID_PROVEEDOR: " +p.getID_PROVEEDOR());
            System.out.println("imprimiendo NOMBRE: " +p.getNOMBRE());
            System.out.println("imprimiendo DESCRIPCION: " +p.getDESCRIPCION());
            System.out.println("imprimiendo DIRECCION: " +p.getDIRECCION());
            System.out.println("imprimiendo EMAIL: " +p.getEMAIL());
            System.out.println("imprimiendo TELEFONO: " +p.getTELEFONO());
            System.out.println("imprimiendo IDCOMUNA: " +p.getID_COMUNA());
            
            this.JFPATENTE.setText(String.valueOf(p.getID_PROVEEDOR()));
            this.JFNOMBRE.setText(p.getNOMBRE());
            this.JFDIRE.setText(p.getDIRECCION());
            this.JFEMAIL.setText(p.getEMAIL());
            this.JFTEL.setText(String.valueOf(p.getTELEFONO()));
            this.JTDESCRP.setText(p.getDESCRIPCION());
            
            String query = "Select nombre from COMUNA where id_comuna="+p.getID_COMUNA()+"";
            ResultSet rs = Conexion.ejecutarQuery(query);
            while (rs.next()){
                this.JCCOMUNA.setSelectedItem(rs.getString("nombre"));
            }
            contador = 1;
            
        } catch (IOException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Proveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jButton10ActionPerformed

private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            proveedor p = new proveedor();
            int ID = p.ObtenerMaxID();
            HabilitarCampos(true);            
            this.JFPATENTE.setText(String.valueOf(ID));
        } catch (SQLException ex) {
            Logger.getLogger(Repuesto.class.getName()).log(Level.SEVERE, null, ex);
        }
}//GEN-LAST:event_jButton6ActionPerformed

private void JCCOMUNAItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JCCOMUNAItemStateChanged
        if(this.JCCOMUNA.getSelectedIndex() != 0){
        try {
            proveedor p = new proveedor();
            String Nombre = (String) this.JCCOMUNA.getSelectedItem();            
            int ID_COMUNA = p.ObtenerComuna(Nombre);
            this.JFComunaID.setText(String.valueOf(ID_COMUNA));            
            System.out.println("Fin Operacion");
        } catch (SQLException ex) {
            Logger.getLogger(Personal.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        else{
            this.JCCOMUNA.setSelectedIndex(48);
        }
}//GEN-LAST:event_JCCOMUNAItemStateChanged

private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
   if (contador == 1 || contador == 2){            
        int n = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea Eliminar?", "Mensajero", JOptionPane.YES_NO_OPTION);
        if (n == 0){
            proveedor p = new proveedor();            
            int IDPROVEEDOR = Integer.parseInt(this.JFPATENTE.getText().trim());
            System.out.println("IMPRIMIENDO IDPROVEEDOR: " +IDPROVEEDOR);
            p.borrarProveedor(IDPROVEEDOR);
            cargarProveedor();
            ResetearCampos();
            HabilitarCampos(false);            
        }                                
        else{
            System.out.println("IMPRIMIENDO N :" +n);
            ResetearCampos();
        }
    }
    else {
        JOptionPane.showMessageDialog(null, "Primero debe cargar un Proveedor", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
    }    
}//GEN-LAST:event_jButton7ActionPerformed

private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
    if (contador == 1){
        HabilitarCampos(true);
        contador = 2;
    }  
}//GEN-LAST:event_jButton8ActionPerformed

private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    proveedor p = new proveedor();    
    System.out.println("Inicio Definición Variables");
    int ID_PROVEEDOR = Integer.parseInt(this.JFPATENTE.getText());    
    String NOMBRE = this.JFNOMBRE.getText().toUpperCase().trim();        
    String DESCRIPCION  = this.JTDESCRP.getText().toUpperCase().trim();    
    String DIRECCION = this.JFDIRE.getText().toUpperCase().trim();
    String EMAIL = this.JFEMAIL.getText().toUpperCase().trim();              
    int TELEFONO = Integer.parseInt(this.JFTEL.getText());
    int IDCOMUNA = Integer.parseInt(this.JFComunaID.getText());
 
    
    p.setID_PROVEEDOR(ID_PROVEEDOR);
    p.setNOMBRE(NOMBRE);
    p.setDESCRIPCION(DESCRIPCION);
    p.setDIRECCION(DIRECCION);
    p.setEMAIL(EMAIL);
    p.setTELEFONO(TELEFONO);
    p.setID_COMUNA(IDCOMUNA);
        
    proveedor conexionProveedor = new proveedor();    
    System.out.println("Termino Definición Variables");    
    
    System.out.println("INICIO impresion variables asignadas");
    System.out.println("ID_PROVEEDOR: " +p.getID_PROVEEDOR());
    System.out.println("NOMBRE: " +p.getNOMBRE());    
    System.out.println("DESCRIPCION: " +p.getDESCRIPCION());
    System.out.println("DIRECCION: " +p.getDIRECCION());
    System.out.println("TELEFONO: " +p.getTELEFONO());
    System.out.println("EMAIL: " +p.getEMAIL());
    System.out.println("ID_COMUNA: " +p.getID_COMUNA());
    
    if(contador == 2){
           if (p.getID_PROVEEDOR() != 0 && p.getNOMBRE() != null && p.getDESCRIPCION() != null && p.getDIRECCION() != null && p.getTELEFONO() != 0 && p.getEMAIL() != null && p.getID_COMUNA() != 0){
                int n = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea Guardar?", "Mensajero", JOptionPane.YES_NO_CANCEL_OPTION);
                //n = 0 es YES, n = 1 es NO, n = 2 es Cancel
                System.out.println("numero" + JOptionPane.YES_NO_CANCEL_OPTION);
                if (n == 0) {
                    try {
                    conexionProveedor.actualizarProveedor(p);
                    ResetearCampos();                
                    JOptionPane.showMessageDialog(null, "Datos Actualizados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
                    contador = 0;                                        
                    cargarProveedor();
                    HabilitarCampos(false);
                    } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Se ha producido un error en la Actualización", "Error", JOptionPane.ERROR_MESSAGE);
                    
                  }        
                } else if (n == 1) {
                ResetearCampos();
            }
        }
        else{
            JOptionPane.showMessageDialog(null,"Codigo: " +"Debe llenar todos los campos solicitados", "Error", JOptionPane.ERROR_MESSAGE); 
        }
        
    }
    
    else{    
        if (p.getID_PROVEEDOR() != 0 && p.getNOMBRE() != null && p.getDESCRIPCION() != null && p.getDIRECCION() != null && p.getTELEFONO() != 0 && p.getEMAIL() != null && p.getID_COMUNA() != 0){
            int n = JOptionPane.showConfirmDialog(rootPane, "¿Está seguro que desea Guardar?", "Mensajero", JOptionPane.YES_NO_CANCEL_OPTION);
            //n = 0 es YES, n = 1 es NO, n = 2 es Cancel
            System.out.println("numero" + JOptionPane.YES_NO_CANCEL_OPTION);
            if (n == 0) {
                try {
                conexionProveedor.registrarProveedor(p);
                ResetearCampos();                
                JOptionPane.showMessageDialog(null, "Datos Ingresados Satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
                contador = 0;                               
                cargarProveedor();
                HabilitarCampos(false);
                } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Se ha producido un error en la inserción", "Error", JOptionPane.ERROR_MESSAGE);
                
                }        
            } else if (n == 1) {
            ResetearCampos();
      }

        }
        else{
            JOptionPane.showMessageDialog(null,"Codigo: " +"Debe llenar todos los campos solicitados", "Error", JOptionPane.ERROR_MESSAGE); 
        
        }
    } 
}//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox JCCOMUNA;
    private javax.swing.JTextField JFComunaID;
    private javax.swing.JFormattedTextField JFDIRE;
    private javax.swing.JTextField JFEMAIL;
    private javax.swing.JFormattedTextField JFNOMBRE;
    private javax.swing.JFormattedTextField JFPATENTE;
    private javax.swing.JFormattedTextField JFTEL;
    private java.awt.List JLProveedor;
    private javax.swing.JTextArea JTDESCRP;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JToolBar jToolBar3;
    // End of variables declaration//GEN-END:variables
}