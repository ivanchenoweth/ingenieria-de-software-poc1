/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import soprafamrv.Principal;

public class EjemploBarraProgreso extends JFrame {
  
  private JProgressBar barra;
  private JLabel label;
  
  public EjemploBarraProgreso() {
    
    barra = new JProgressBar(0,100);
    label = new JLabel("hola");
    
  }
  

    
  public void actionPerformed(ActionEvent e) {
        
        TareaBarra tarea = new TareaBarra();
        tarea.start();
        
      }
      

  
  public class TareaBarra extends Thread {
    
    public void run() {
            try {
                Principal p = new Principal();
              
                
                for (int i = 0; i < 100; i++) {
                  
                  System.out.println("Nombre Thread " +Thread.currentThread().getName());
                  
                  
                  
                }
            } catch (SQLException ex) {
                Logger.getLogger(EjemploBarraProgreso.class.getName()).log(Level.SEVERE, null, ex);
            }
      
      
    }
    
  }
  
}