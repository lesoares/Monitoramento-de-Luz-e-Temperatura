/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cd;


import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import java.io.*;
/**
 *
 * @author marcelovieitas
 */
public class Grafico {
    
    public static void main(String[] args) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        
        dataset.addValue(10.1, "Maximo", "Hora 1");
        dataset.addValue(20.1, "Maximo", "Hora 2");
        dataset.addValue(30.1, "Maximo", "Hora 3");
        dataset.addValue(40.1, "Maximo", "Hora 4");
        dataset.addValue(70.1, "Maximo", "Hora 5");
        
        JFreeChart criaGrafico = ChartFactory.createLineChart("Título", "Hora", "Valor", dataset, PlotOrientation.VERTICAL,true,true,false);
        
        try {
            System.out.println("Criando o gráfico...");
            OutputStream png = new FileOutputStream("GraficoSimples.png");
            ChartUtilities.writeChartAsPNG(png, criaGrafico, 500, 400);
            png.close();
        } catch (IOException io) {
            System.out.println("Erro: " + io.getMessage());
        }
        System.out.println("Gráfico criado.");
    }
    
}
