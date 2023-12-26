package soprafamrv.ARCHIVOS_EXP;

/**
 *
 * @author Cri
 */
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.sql.SQLException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
 

public class GeneratePDF {
    // Se crea el documento
    Document documento = new Document(PageSize.A4);    
    int[] CANTIDADXMES = new int[12];
    int[] MESES = {1,2,3,4,5,6,7,8,9,10,11,12};

    public void crearDocumento(String NombrePDF) throws DocumentException, FileNotFoundException{
        System.out.println("INICIO DENTRO DE CLASE GENERATEPDF EN METODO CREARDOCUMENTO");
        // Se crea el OutputStream para el fichero donde queremos dejar el pdf.
        FileOutputStream ficheroPdf = new FileOutputStream(NombrePDF+".pdf");
        // Se asocia el documento al OutputStream y se indica que el espaciado entre
        // lineas sera de 20. Esta llamada debe hacerse antes de abrir el documento
        PdfWriter.getInstance(documento,ficheroPdf).setInitialLeading(20);
        // Se abre el documento.
        documento.open();
         System.out.println("TERMINO DENTRO DE CLASE GENERATEPDF EN METODO CREARDOCUMENTO");
    }
                
    //ESTO LO TENGO Q TRABAJAR Y TRATAR DE COPIAR IGUAL QUE LA TABLA
    public void ContenidoDocumento(String Encabezado, String Subtitulo, String TIPO, ResultSet InformacionPersonal, ResultSet ContenidoServicios) throws DocumentException, SQLException{
        //Agregando Encabezado
        Paragraph paragraph = new Paragraph(Encabezado, FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph);
        
        documento.add(new Paragraph("\n"));
        
        //Agregando Subtitulo
        Paragraph paragraph1 = new Paragraph(Subtitulo, FontFactory.getFont(FontFactory.COURIER_BOLD,14,Font.BOLD));
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph1);
        
        //documento.add(new Paragraph(Encabezado));
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (InformacionPersonal.next()){
            documento.add(new Paragraph("NUMERO ORDEN DE TRABAJO : " +InformacionPersonal.getString("idot"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));
            documento.add(new Paragraph("FECHA ORDEN DE TRABAJO : " +InformacionPersonal.getString("fechainicio"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));
            documento.add(new Paragraph("PATENTE VEHICULO        : " +InformacionPersonal.getString("patente"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));
            documento.add(new Paragraph("MECANICO                : " +InformacionPersonal.getString("mecnombre")+ " " +InformacionPersonal.getString("mecapepa")+ " " +InformacionPersonal.getString("mecamema"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));
            documento.add(new Paragraph("ADMINISTRADOR           : " +InformacionPersonal.getString("adnombre")+ " " +InformacionPersonal.getString("adapepa")+ " " +InformacionPersonal.getString("adamema"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));    
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        Paragraph paragraph2 = new Paragraph(TIPO,FontFactory.getFont(FontFactory.COURIER_BOLD,12,Font.BOLD));
        paragraph2.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph2);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        documento.add(new Paragraph("N U M E R O  S E R V I C I O      -      T I P O  S E R V I C I O      -      O B S E R V A C I O N E S", FontFactory.getFont(FontFactory.COURIER_BOLD,8,Font.BOLD)));
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (ContenidoServicios.next()){
            documento.add(new Paragraph(ContenidoServicios.getString("id_servicio")+ "  " +ContenidoServicios.getString("nombre"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));
            documento.add(new Paragraph("____________________________________________________________________________"));
            documento.add(new Paragraph("____________________________________________________________________________"));
        }
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("\n--------------------------------------------------------------------------------------------------------------------------------"));        
        documento.close();        
        
        }
    public void ContenidoInformeVehiculos(String Encabezado, String Subtitulo, String TIPO, ResultSet InformacionVehiculos, ResultSet Graficos) throws DocumentException, SQLException{
        //Agregando Encabezado
        Paragraph paragraph = new Paragraph(Encabezado, FontFactory.getFont(FontFactory.HELVETICA,16,Font.BOLD));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph);
        
        documento.add(new Paragraph("\n"));
        
        //Agregando Subtitulo
        Paragraph paragraph1 = new Paragraph(Subtitulo, FontFactory.getFont(FontFactory.HELVETICA,14,Font.BOLD));
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph1);
        
        //documento.add(new Paragraph(Encabezado));
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (InformacionVehiculos.next()){
            documento.add(new Paragraph("· Patente: " +InformacionVehiculos.getString("patente")+ " -- Chasis: " +InformacionVehiculos.getString("chasis")+ " -- Marca: " +InformacionVehiculos.getString("marca")+ " -- Modelo: " +InformacionVehiculos.getString("modelo")+ " -- Año: " +InformacionVehiculos.getString("ano"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("\n--------------------------------------------------------------------------------------------------------------------------------"));
        documento.close();
        JOptionPane.showMessageDialog(null, "Informe creado satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);                
    }
    
    public void ContenidoInformeRepuestos(String Encabezado, String Subtitulo, String TIPO, ResultSet RepUsados, ResultSet RepCompraXaño, ResultSet StockRep, ResultSet RepUsadoXvehi, ResultSet RepXmecan, ResultSet RepXencb) throws DocumentException, SQLException{
        //Agregando Encabezado
        Paragraph paragraph = new Paragraph(Encabezado, FontFactory.getFont(FontFactory.HELVETICA,16,Font.BOLD));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph);
        
        documento.add(new Paragraph("\n"));
        
        //Agregando Subtitulo
        Paragraph paragraph1 = new Paragraph(Subtitulo, FontFactory.getFont(FontFactory.HELVETICA,14,Font.BOLD));
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph1);
        
        //documento.add(new Paragraph(Encabezado));
        documento.add(new Paragraph("\n"));
        Paragraph paragraph2 = new Paragraph("REPUESTOS MAS UTILIZADOS (TOP 10)", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph2.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph2);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (RepUsados.next()){
            documento.add(new Paragraph("· Repuesto: "+RepUsados.getString("NOMBRE")+ " -- Cantidad: " +RepUsados.getString("TOTAL_USADO"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        //
        documento.add(new Paragraph("\n"));
        Paragraph paragraph3 = new Paragraph("REPUESTOS COMPRADOS POR AÑO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph3.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph3);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (RepCompraXaño.next()){
            documento.add(new Paragraph("· Repuesto: "+RepCompraXaño.getString("Nombre")+ " -- Cantidad: " +RepCompraXaño.getString("TOTAL_AÑO")+ " -- Fecha compra: " +RepCompraXaño.getString("Fecha_compra").substring(0, 10), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        //
        documento.add(new Paragraph("\n"));
        Paragraph paragraph4 = new Paragraph("STOCK DE REPUESTOS", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph4.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph4);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (StockRep.next()){
            documento.add(new Paragraph("· Repuesto: "+StockRep.getString("nombre")+ " -- Cantidad: " +StockRep.getString("TOTAL"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        //
        documento.add(new Paragraph("\n"));
        Paragraph paragraph5 = new Paragraph("REPUESTOS UTILIZADOS POR VEHICULO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph5.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph5);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (RepUsadoXvehi.next()){
            documento.add(new Paragraph("· Numero orden: "+RepUsadoXvehi.getString("NUMERO_ORDEN")+ " -- Patente: " +RepUsadoXvehi.getString("PATENTE")+ " -- Repuesto: " +RepUsadoXvehi.getString("repuesto")+ " -- Cantidad: " +RepUsadoXvehi.getString("TOTAL"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        //
        documento.add(new Paragraph("\n"));
        Paragraph paragraph6 = new Paragraph("REPUESTOS UTILIZADOS POR MECANICO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph6.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph6);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (RepXmecan.next()){
            documento.add(new Paragraph("· RUT Mecanico: "+RepXmecan.getString("RUT_MECANICO")+ " -- Nombre: " +RepXmecan.getString("NOMBRE")+ " " +RepXmecan.getString("APELLIDO_PATERNO")+ " " +RepXmecan.getString("APELLIDO_MATERNO")+ " -- Numero orden: " +RepXmecan.getString("ID_ORDEN")+ " -- Patente: " +RepXmecan.getString("PATENTE")+ " -- Repuesto: " +RepXmecan.getString("NOMBRE_REPUESTO")+ " -- Cantidad: " +RepXmecan.getString("TOTAL"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        //
        documento.add(new Paragraph("\n"));
        Paragraph paragraph7 = new Paragraph("REPUESTOS RETIRADOS POR ENCARGADO DE BODEGA", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph7.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph7);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (RepXencb.next()){
            documento.add(new Paragraph("· RUT Encargado Bodega: "+RepXencb.getString("RUT_ENCARGADO")+ " -- Nombre: " +RepXencb.getString("NOMBRE")+ " " +RepXencb.getString("APELLIDO_PATERNO")+ " " +RepXencb.getString("APELLIDO_MATERNO")+ " -- Numero orden: " +RepXencb.getString("ID_ORDEN")+ " -- Patente: " +RepXencb.getString("PATENTE")+ " -- Repuesto: " +RepXencb.getString("NOMBRE_REPUESTO")+ " -- Cantidad: " +RepXencb.getString("TOTAL"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        
        /*           
        
        
        Paragraph paragraph1000 = new Paragraph(TIPO,FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph1000.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph1000);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        documento.add(new Paragraph("G R A F I C O   E S T A D I S T I C O", FontFactory.getFont(FontFactory.HELVETICA,8,Font.BOLD)));
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        
        //DESDE AQUI DEBO COMENZAR A TRABAJAR CON EL GRAFICO
      
        while (RepCompraXaño.next()){
            documento.add(new Paragraph(RepCompraXaño.getString("TOTAL_AÑO")+ "  " +RepCompraXaño.getString("Nombre")+ "  " +RepCompraXaño.getString("Fecha_compra"), FontFactory.getFont(FontFactory.HELVETICA,10,Font.NORMAL)));
            documento.add(new Paragraph("____________________________________________________________________________"));
            documento.add(new Paragraph("____________________________________________________________________________"));
        }
        documento.add(new Paragraph(" "));
        documento.add(new Paragraph("\n--------------------------------------------------------------------------------------------------------------------------------"));
         * 
         */
        documento.close();
        JOptionPane.showMessageDialog(null, "Informe creado satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
                
    }
    
    public void ContenidoInformeFallas(String Encabezado, String Subtitulo, String TIPO, ResultSet FallaXaño, ResultSet MasDetect, ResultSet FallaXvehi, ResultSet FallaXmeca) throws DocumentException, SQLException{
        //Agregando Encabezado
        Paragraph paragraph = new Paragraph(Encabezado, FontFactory.getFont(FontFactory.HELVETICA,16,Font.BOLD));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph);
        
        documento.add(new Paragraph("\n"));
        
        //Agregando Subtitulo
        Paragraph paragraph1 = new Paragraph(Subtitulo, FontFactory.getFont(FontFactory.HELVETICA,14,Font.BOLD));
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph1);
        
        //documento.add(new Paragraph(Encabezado));
        documento.add(new Paragraph("\n"));
        Paragraph paragraph2 = new Paragraph("FALLAS POR AÑO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph2.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph2);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (FallaXaño.next()){
            documento.add(new Paragraph("· Cantidad: "+FallaXaño.getString("TOTAL_FALLAS"), FontFactory.getFont(FontFactory.HELVETICA,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        //
        documento.add(new Paragraph("\n"));
        Paragraph paragraph3 = new Paragraph("FALLAS MAS DETECTADAS (TOP 10)", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph3.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph3);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (MasDetect.next()){
            documento.add(new Paragraph("· Codigo falla: "+MasDetect.getString("ID_FALLA")+ " -- Nombre: " +MasDetect.getString("NOMBRE")+ " -- Cantidad: " +MasDetect.getString("TOTAL"), FontFactory.getFont(FontFactory.HELVETICA,10,Font.NORMAL)));            
        }
        //
        documento.add(new Paragraph("\n"));
        Paragraph paragraph4 = new Paragraph("FALLAS POR VEHICULO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph4.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph4);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (FallaXvehi.next()){
            documento.add(new Paragraph("· Patente: "+FallaXvehi.getString("PATENTE")+ " -- Falla: " +FallaXvehi.getString("NOMBRE")+ " -- Cantidad: " +FallaXvehi.getString("TOTAL"), FontFactory.getFont(FontFactory.HELVETICA,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        //        
        documento.add(new Paragraph("\n"));
        Paragraph paragraph6 = new Paragraph("FALLAS POR MECANICO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph6.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph6);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (FallaXmeca.next()){
            documento.add(new Paragraph("· RUT Mecanico: "+FallaXmeca.getString("RUT_MECANICO")+ " -- Nombre: " +FallaXmeca.getString("NOMBRE")+ " " +FallaXmeca.getString("APELLIDO_PATERNO")+ " " +FallaXmeca.getString("APELLIDO_MATERNO")+ " -- Numero orden: " +FallaXmeca.getString("ID_ORDEN")+ " -- Patente: " +FallaXmeca.getString("PATENTE")+ " -- Falla: " +FallaXmeca.getString("NOMBRE_FALLA")+ " -- Cantidad: " +FallaXmeca.getString("TOTAL"), FontFactory.getFont(FontFactory.HELVETICA,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));        
        documento.close();
        JOptionPane.showMessageDialog(null, "Informe creado satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
                
    }
    
    public void ContenidoInformeOT(String Encabezado, String Subtitulo, String TIPO, ResultSet otXaño, ResultSet otXveh, ResultSet otXadm, ResultSet otXmec) throws DocumentException, SQLException{
        //Agregando Encabezado
        Paragraph paragraph = new Paragraph(Encabezado, FontFactory.getFont(FontFactory.HELVETICA,16,Font.BOLD));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph);
        
        documento.add(new Paragraph("\n"));
        
        //Agregando Subtitulo
        Paragraph paragraph1 = new Paragraph(Subtitulo, FontFactory.getFont(FontFactory.HELVETICA,14,Font.BOLD));
        paragraph1.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph1);
        
        //documento.add(new Paragraph(Encabezado));
        documento.add(new Paragraph("\n"));
        Paragraph paragraph2 = new Paragraph("ORDENES DE TRABAJO POR AÑO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph2.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph2);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (otXaño.next()){
            documento.add(new Paragraph("· Cantidad: "+otXaño.getString("TOTAL_OT"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        //
        documento.add(new Paragraph("\n"));
        Paragraph paragraph3 = new Paragraph("ORDENES DE TRABAJO POR VEHICULO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph3.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph3);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (otXveh.next()){
            documento.add(new Paragraph("· Patente: "+otXveh.getString("PATENTE")+ " -- Total OT: " +otXveh.getString("TOTAL_OT"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }        
        //        
        documento.add(new Paragraph("\n"));
        Paragraph paragraph6 = new Paragraph("ORDENES DE TRABAJO POR ADMINISTRADOR", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph6.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph6);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (otXadm.next()){
            documento.add(new Paragraph("· RUT Administrador: "+otXadm.getString("RUT_ADMIN")+ " -- Nombre: " +otXadm.getString("NOMBRE")+ " " +otXadm.getString("APELLIDO_PATERNO")+ " " +otXadm.getString("APELLIDO_MATERNO")+ " -- Patente: " +otXadm.getString("PATENTE")+ " -- Total: " +otXadm.getString("TOTAL_POR_VEHICULO"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));                
        //        
        documento.add(new Paragraph("\n"));
        Paragraph paragraph7 = new Paragraph("ORDENES DE TRABAJO POR MECANICO", FontFactory.getFont(FontFactory.HELVETICA,12,Font.BOLD));
        paragraph7.setAlignment(Element.ALIGN_LEFT);
        documento.add(paragraph7);
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));
        while (otXmec.next()){
            documento.add(new Paragraph("· RUT Mecanico: "+otXmec.getString("RUT_MECANICO")+ " -- Nombre: " +otXmec.getString("NOMBRE")+ " " +otXmec.getString("APELLIDO_PATERNO")+ " " +otXmec.getString("APELLIDO_MATERNO")+ " -- Patente: " +otXmec.getString("PATENTE")+ " -- Total: " +otXmec.getString("TOTAL_POR_VEHICULO"), FontFactory.getFont(FontFactory.COURIER,10,Font.NORMAL)));            
        }
        documento.add(new Paragraph("--------------------------------------------------------------------------------------------------------------------------------"));        
        documento.close();
        JOptionPane.showMessageDialog(null, "Informe creado satisfactoriamente", "Mensajero", JOptionPane.INFORMATION_MESSAGE);
                
    }
    
    
    public void ContenidoCodBarra(String Encabezado, String NOMBRE, int CODIGO, int CANTIDAD) throws DocumentException, SQLException{        
        FontFactory.register("SOPRAF AMRV");        
        Paragraph paragraph = new Paragraph(Encabezado, FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD));
        paragraph.setAlignment(Element.ALIGN_CENTER);
        Paragraph p2 = new Paragraph("CODIGOS DE BARRA REPUESTO " +NOMBRE, FontFactory.getFont(FontFactory.COURIER,16,Font.BOLD));
        p2.setAlignment(Element.ALIGN_CENTER);
        documento.add(paragraph);        
        documento.add(p2);
        
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("\n"));
        documento.add(new Paragraph("\n"));
                       
        Phrase o = new Phrase("   ");
        Phrase p = new Phrase("*"+CODIGO+"*", FontFactory.getFont("IDAutomationHC39M.ttf", 14));
        Phrase q = new Phrase("   ");
        System.out.println ("CANTIDAD: " +CANTIDAD);
                
        int contador = 0;
        for (int i=0; i<CANTIDAD; i++){  
            documento.add(o);
            documento.add(p);
            documento.add(q);
            if (contador == 2){
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph("\n"));
                documento.add(new Paragraph("\n"));
                contador = -1;
            }
            contador++;                
        }   
                
        documento.close();                
        }    
}
