/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.SISTEMA;

/**
 *
 * @author Cri
 */
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;


public class subeImagen {
    private BufferedImage _image = null;    
    private FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de Imagen","jpg","png");
    
    
    
    public subeImagen(){       
    }      
    
    
   /* crea una imagen en el BufferedImage y la pinta en el JPanel */
   /*public void crear_imagen(JPanel p){
    //crea una imagen 400x300 con pixels de 8 bits en RGB. 
    this._image = new BufferedImage(400, 300, BufferedImage.TYPE_INT_RGB);
    this._image.createGraphics();
    Graphics2D g = (Graphics2D)this._image.getGraphics();
    // pintamos una imagen
    g.setColor(Color.RED);
    g.fillRect(0, 0, 400, 100);
    g.setColor(Color.YELLOW);
    g.fillRect(0, 100, 400, 100);
    g.setColor(Color.GREEN);
    g.fillRect(0, 200, 400, 100);
    
    
    //aÃ±adimos al JPanel, con las dimensiones del contenedor, no de la imagen
    p.add(new miPanel(this._image, p.getSize()));
    p.setVisible(true);                
    p.repaint();           
   }
    * 
    */
   /*
   public void Guardar_Dialogo(){
       JFileChooser fileChooser = new JFileChooser();       
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showSaveDialog(null);       
       if ( result == JFileChooser.APPROVE_OPTION ){ 
                //se obtiene la direccion donde se guardara la imagen
                String url = fileChooser.getSelectedFile().toString();
                //String namefile = fileChooser.getSelectedFile().getName();
                System.out.println("url: " + url);
                //Se guarda la imagen
                guardar_imagen(url);
                
        }
   }
    * 
    */
   
    /* Metod que muestra una ventana de dialgo para aÃ±adir "archivo de imagen"
    *  en memoria  */
    static byte[] x = null;
    public void Abrir_Dialogo(JPanel p){
       JFileChooser fileChooser = new JFileChooser();       
       fileChooser.setFileFilter(filter);
       int result = fileChooser.showOpenDialog(null);  
       if ( result == JFileChooser.APPROVE_OPTION ){
            try {           
                //se asigna a "url" el archivo de imagen seleccionado
                URL url = fileChooser.getSelectedFile().toURL();
                //se lo coloca en memoria
                
                System.out.println("Imprimiendo URL: " +url);
                //guardar_imagen(url);
                
                cargar_imagen_en_buffer(url);
                //se aÃ±ade al contenedor
                
                p.removeAll();
                p.add(new miPanel(Obtener_imagen_de_Buffer(), p.getSize()));
                p.setVisible(true);
                p.repaint();
            } 
            catch (IOException ex) {
                Logger.getLogger(subeImagen.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }    
    
    /* crea un archivo de imagen desde el BufferedImage dada la direccion fisica */
   
   public void guardar_imagen(BufferedImage foto){
        try {
            //se extrae el fomato de la cadena "f" que contiene la direccion
            String formato = (foto.toString().endsWith(".jpg")) ? "jpg" : "png";            
            //ImageIO.write(_image, "jpg", new File("e:/carpeta/imagen2.jpg"));
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            //ImageIO.write(_image, formato, new File(f));
            ImageIO.write(_image, formato, os);
            ByteArrayInputStream is = new ByteArrayInputStream (os.toByteArray());
            x = os.toByteArray();
            //original ImageIO.write(_image, formato, new File(f));       
    }
        catch (IOException e) {            
            System.out.println("Error al crear el archivo");
    }
        
   }
   
   public byte[] ObtenerBytes(){
       System.out.println("Bytes de la imagen" +x);
       return x; 
   }
   
    /* dada una direccion fisica de un archivo de imagen,
     * coloca esta en el objeto BufferedImage, o sea en memoria */
    public void cargar_imagen_en_buffer(URL _url){
        //se llena el buffer con la imagen        
            try {
                _image = ImageIO.read(_url);
                guardar_imagen(_image);
                
                
            } catch (IOException ex) {
                Logger.getLogger(subeImagen.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
   
   
   
   
   /* retorna el objeto almacenado en memoria */
   public BufferedImage Obtener_imagen_de_Buffer(){
        
        return _image;
   }
    
}
