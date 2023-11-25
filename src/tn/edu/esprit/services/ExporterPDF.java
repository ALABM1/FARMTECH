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

import tn.edu.esprit.entities.Parc;

public class ExporterPDF {

    public static void exportToPDF(List<Parc> parcs) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage();
            document.addPage(page);

            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            PDType0Font font = PDType0Font.load(document, ExporterPDF.class.getResourceAsStream("/fonts/Roboto-Regular.ttf"));
            contentStream.setFont(font, 12);
            
            float margin = 50;
            float yStart = 700;
            float tableWidthActual = page.getMediaBox().getWidth() - 2 * margin;
            float rowHeight = 20f;
            float cellMargin = 5f;

            float nextTextX = margin + cellMargin;
            float nextTextY = yStart;
             
            LocalDate currentDate = LocalDate.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDate = currentDate.format(formatter);

            // Ajouter le texte du header
            contentStream.setNonStrokingColor(0f, 0f, 0f); // Noir
            contentStream.beginText();
            contentStream.setFont(font, 12);
            contentStream.newLineAtOffset(nextTextX, nextTextY);
            contentStream.showText("Nom Parc");
            nextTextX += 150; 
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("Adresse de Parc");
            nextTextX += 150; 
            contentStream.newLineAtOffset(150, 0);
            contentStream.showText("Superficie de Parc");
            contentStream.endText();

            nextTextY -= rowHeight;

            // Remplir le tableau avec les données
            boolean colorSwitch = false;

            for (Parc parc : parcs) {
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

                contentStream.showText(parc.getNomParc());
                contentStream.newLineAtOffset(150, 0);
                contentStream.showText(parc.getAdresseParc());
                contentStream.newLineAtOffset(150, 0);
                contentStream.showText(String.valueOf(parc.getSuperficieParc()));
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
                    nextTextX += 150; 
                    contentStream.newLineAtOffset(150, 0);
                    contentStream.showText("Adresse de Parc");
                    nextTextX += 150; 
                    contentStream.newLineAtOffset(150, 0);
                    contentStream.showText("Superficie de Parc");
                    contentStream.endText();
                    
                    contentStream.beginText();
                    contentStream.setFont(font, 16);
                    contentStream.newLineAtOffset(240, 740); // Ajustez les coordonnées selon vos besoins
                    contentStream.showText("Liste des parcs");
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
                    contentStream.showText("Liste des parcs");
                    contentStream.endText();
            // Ajouter la date au pied de page
            contentStream.beginText();
            contentStream.setFont(font, 10);
            contentStream.newLineAtOffset(480, 13); // Ajustez les coordonnées selon vos besoins
            contentStream.showText("INFINITYFARM");
            contentStream.endText();

            contentStream.beginText();
            contentStream.setFont(font, 11);
            contentStream.newLineAtOffset(margin, 13); // Ajustez les coordonnées selon vos besoins
            contentStream.showText(formattedDate);
            contentStream.endText();

                 
            contentStream.close();

            document.save(new FileOutputStream("parcs.pdf"));
            document.close();

            System.out.println("Le PDF a été généré avec succès.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
