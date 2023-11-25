/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.edu.esprit.services;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import tn.edu.esprit.entities.Materiel;

/**
 *
 * @author megbl
 */
public class ExporterPDFAllMateriel {

   public  void exportMaterielToPDF(List<Materiel> materiels) {
 

        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);
            

            
            PDType0Font font = PDType0Font.load(document, ExporterPDF.class.getResourceAsStream("/fonts/Roboto-Regular.ttf"));
            contentStream.setFont(font, 12);
            
            float margin = 30;
            float yStart = 700;
            float tableWidthActual = page.getMediaBox().getWidth() - 2 * margin;
            float rowHeight = 20f;
            float cellMargin = 5f;

            float nextTextX = margin + cellMargin; 

            float nextTextY = yStart;
            
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(formatter);
            contentStream.addRect(0, 0, page.getMediaBox().getWidth(), page.getMediaBox().getHeight());
            contentStream.setStrokingColor(0f, 0f, 0f); // Couleur de la bordure (noir)
            contentStream.setLineWidth(1f); // Largeur de la bordure (1 point)
            contentStream.stroke();
            // Ajouter le texte du header
            contentStream.setNonStrokingColor(0f, 0f, 0f); // Noir
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.newLineAtOffset(nextTextX, nextTextY);
            contentStream.showText("Nom Parc");
            nextTextX += 120; 
            contentStream.newLineAtOffset(120, 0);
            contentStream.showText("Nom Matériel");
            nextTextX += 120; 
            contentStream.newLineAtOffset(120, 0);
            contentStream.showText("État Matériel");
            nextTextX += 120; 
            contentStream.newLineAtOffset(120, 0);
            contentStream.showText("Quantité Matériel");
            nextTextX += 120; 
            contentStream.newLineAtOffset(120, 0);
            contentStream.showText("Date Ajout");
            contentStream.endText();

            nextTextY -= rowHeight;

            // Remplir le tableau avec les données
            boolean colorSwitch = false;

            for (Materiel materiel : materiels) {
                contentStream.addRect(margin, nextTextY - 15, tableWidthActual, rowHeight);
                if (colorSwitch) {
                    contentStream.setNonStrokingColor(0.8f, 0.8f, 0.8f); // Gris clair
                } else {
                    contentStream.setNonStrokingColor(1f, 1f, 1f); // Blanc
                }
                contentStream.fill();
                contentStream.setNonStrokingColor(0f, 0f, 0f); // Noir

                contentStream.beginText();
                contentStream.setFont(font, 12);
                contentStream.newLineAtOffset(margin + cellMargin, nextTextY - 10);

                contentStream.showText(materiel.getNomParc());
                contentStream.newLineAtOffset(120, 0);
                contentStream.showText(materiel.getNomMat());
                contentStream.newLineAtOffset(120, 0);
                contentStream.showText(materiel.getEtatMat());
                contentStream.newLineAtOffset(120, 0);
                contentStream.showText(String.valueOf(materiel.getQuantiteMat()));
                contentStream.newLineAtOffset(120, 0);
                contentStream.showText(materiel.getDateAjout().format(formatter));
                contentStream.endText();

                nextTextY -= rowHeight;
                colorSwitch = !colorSwitch;

                if (nextTextY <= 30) {
                    contentStream.close();
                    PDPage newPage = new PDPage();
                    document.addPage(newPage);
                    contentStream = new PDPageContentStream(document, newPage);
                    contentStream.setFont(font, 12);

                    // Ajouter le texte du header pour la nouvelle page
                    contentStream.setNonStrokingColor(0f, 0f, 0f); // Noir
                    contentStream.beginText();
                    contentStream.setFont(font, 12);
                    nextTextX = margin + cellMargin;
                    nextTextY = yStart;
                    contentStream.newLineAtOffset(nextTextX, nextTextY);
                    contentStream.showText("Nom Parc");
                    nextTextX += 120; 
                    contentStream.newLineAtOffset(120, 0);
                    contentStream.showText("Nom Matériel");
                    nextTextX += 120; 
                    contentStream.newLineAtOffset(120, 0);
                    contentStream.showText("État Matériel");
                    nextTextX += 120; 
                    contentStream.newLineAtOffset(120, 0);
                    contentStream.showText("Quantité Matériel");
                    nextTextX += 120; 
                    contentStream.newLineAtOffset(120, 0);
                    contentStream.showText("Date Ajout");
                    contentStream.endText();
                    
                    contentStream.beginText();
                    contentStream.setFont(font, 16);
                    contentStream.newLineAtOffset(240, 740); // Ajustez les coordonnées selon vos besoins
                    contentStream.showText("Liste des materiels");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.setFont(font, 8);
                    contentStream.newLineAtOffset(480, 30); // Ajustez les coordonnées selon vos besoins
                    contentStream.showText("INFINITYFARM");
                    contentStream.endText();

                    contentStream.beginText();
                    contentStream.setFont(font, 8);
                    contentStream.newLineAtOffset(50, 30); // Ajustez les coordonnées selon vos besoins
                    contentStream.showText(formattedDate);
                    contentStream.endText();

                    nextTextY -= rowHeight;
                    colorSwitch = false;
                }
            }
                    contentStream.beginText();
                    contentStream.setFont(font, 16);
                    contentStream.newLineAtOffset(240, 740); // Ajustez les coordonnées selon vos besoins
                    contentStream.showText("Liste des materiels");
                    contentStream.endText();
            // Ajouter la date au pied de page
            contentStream.beginText();
            contentStream.setFont(font, 10);
            contentStream.newLineAtOffset(510, 13); // Ajustez les coordonnées selon vos besoins
            contentStream.showText("INFINITYFARM");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.newLineAtOffset(margin, 13); // Ajustez les coordonnées selon vos besoins
            contentStream.showText(formattedDate);
            contentStream.endText();

            contentStream.close();

            document.save(new FileOutputStream("Tous_materiels.pdf"));
            document.close();

            System.out.println("Le PDF a été généré avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


