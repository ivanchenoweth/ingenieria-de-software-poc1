/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

/**
 *
 * @author Cri
 */
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;


public class miPanel extends javax.swing.JPanel{
    BufferedImage _image;
    
    /* coloca la imagen que se encuentra en el Buffer en el objeto JPanel
     * redimensiona la imagen para que encaje en las dimensiones del Jpanel
     * pero la imagen que se encuentra en el Buffer, preserva el tamaÃ±o original
     */
    public miPanel(BufferedImage imagen, Dimension d){    
        this._image = imagen;
        this.setSize(d);
    }    
  
    @Override    
    public void paint(Graphics g){
        ImageIcon imagenFondo = new ImageIcon(_image);
        g.drawImage(imagenFondo.getImage(),0,0,getWidth(),getHeight(), null);
        setOpaque(false);
        super.paintComponent(g);
    }    
}
