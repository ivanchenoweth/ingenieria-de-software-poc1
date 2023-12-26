/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soprafamrv.ARCHIVOS_EXP;


import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.*;
import java.io.*;
import org.jfree.chart.ChartFactory;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeriesCollection;

/**
 *
 * @author Cri
 */
public class CrearGrafico {
    
    public void BarChart(String nombre,int[] CantidadPorMes, int ANO) {
        // Create a simple Bar chart        
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(CantidadPorMes[0], "Compras", "Enero");
        dataset.setValue(CantidadPorMes[1], "Compras", "Febrero");
        dataset.setValue(CantidadPorMes[2], "Compras", "Marzo");
        dataset.setValue(CantidadPorMes[3], "Compras", "Abril");
        dataset.setValue(CantidadPorMes[4], "Compras", "Mayo");
        dataset.setValue(CantidadPorMes[5], "Compras", "Junio");
        dataset.setValue(CantidadPorMes[6], "Compras", "Julio");
        dataset.setValue(CantidadPorMes[7], "Compras", "Agosto");
        dataset.setValue(CantidadPorMes[8], "Compras", "Septiembre");
        dataset.setValue(CantidadPorMes[9], "Compras", "Octubre");
        dataset.setValue(CantidadPorMes[10], "Compras", "Noviembre");
        dataset.setValue(CantidadPorMes[11], "Compras", "Diciembre");        
        
        JFreeChart chart = ChartFactory.createBarChart("Compras realizadas", "Compras de Repuestos Realizadas, "+ANO, "Numero de Compras", dataset, PlotOrientation.VERTICAL, false, true, false);
        try {
            ChartUtilities.saveChartAsJPEG(new File(nombre+ " - AÑO - " +ANO+ ".jpg"), chart, 800, 600);
            } catch (IOException e) {
                System.err.println("Error creando grafico.");
                }        
    }

// Create a time series chart
    public void GraficoLineal(){
        org.jfree.data.time.TimeSeries pop = new org.jfree.data.time.TimeSeries("Linea de Crecimiento", Day.class);
        pop.add(new Day(2, 1, 2007), 100);
        pop.add(new Day(2, 2, 2007), 150);
        pop.add(new Day(2, 3, 2007), 200);
        pop.add(new Day(2, 4, 2007), 250);
        pop.add(new Day(2, 5, 2007), 300);
        pop.add(new Day(2, 6, 2007), 1500);
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        dataset.addSeries(pop);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Crecimiento Ubuntu",
                "Fecha",
                "Numero Usuarios",
                dataset,true,true,false);
        try {
            ChartUtilities.saveChartAsJPEG(new File("/home/jose/Desktop/TimeSeries.jpg"), chart, 500, 300);
            } catch (IOException e) {
                System.err.println("Error creando grafico.");
                }
        }
    
}
    

