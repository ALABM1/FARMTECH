

package tn.edu.esprit.services;




import java.util.HashMap;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import java.util.List;
import java.util.Map;
import org.jfree.chart.ChartPanel;
import tn.edu.esprit.entities.Materiel;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.chart.plot.CategoryPlot;

public class MaterielGraph {

    public JFreeChart createParcMaterielChart(List<Materiel> materiels) {
        Map<String, Integer> parcMaterielCount = new HashMap<>();

        for (Materiel materiel : materiels) {
            String nomParc = materiel.getNomParc();
            parcMaterielCount.put(nomParc, parcMaterielCount.getOrDefault(nomParc, 0) + 1);
        }

        CategoryDataset dataset = createDataset(parcMaterielCount);
        JFreeChart chart = ChartFactory.createBarChart(
                "Nombre de Matériaux par Parc",
                "Nom du Parc",
                "Nombre de Matériaux",
                dataset);
        // Ajuster la largeur des barres
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = new BarRenderer();
        renderer.setMaximumBarWidth(0.05); // Ajustez cette valeur selon votre préférence
        plot.setRenderer(renderer);

        return chart;
    }

    private CategoryDataset createDataset(Map<String, Integer> parcMaterielCount) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        parcMaterielCount.entrySet().forEach((entry) -> {
            dataset.addValue(entry.getValue(), "Nombre de Matériaux", entry.getKey());
        });

        return dataset;
    }

    public ChartPanel createChartPanel(JFreeChart chart) {
        return new ChartPanel(chart);
    }

    // ...
}


