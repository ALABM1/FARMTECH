package tn.edu.esprit.services;



import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class QRCodeGenerator {

    public static void generateQRCode(String data, String filePath) {
        int width = 300; // Largeur du code QR en pixels
        int height = 300; // Hauteur du code QR en pixels

        // Configuration du code QR
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        Writer writer = new MultiFormatWriter();
        try {
            BitMatrix bitMatrix = writer.encode(data, BarcodeFormat.QR_CODE, width, height, hints);

            // Convertir BitMatrix en BufferedImage
            BufferedImage qrImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    qrImage.setRGB(x, y, bitMatrix.get(x, y) ? 0x000000 : 0xFFFFFF);
                }
            }

            // Enregistrer l'image en tant que fichier PNG
            File outputFile = new File(filePath);
            ImageIO.write(qrImage, "PNG", outputFile);
           
            System.out.println("Code QR généré avec succès.");
        } catch (WriterException | IOException e) {
            e.printStackTrace();
        }
    }

   
}


