
package tn.edu.esprit.services;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

import java.awt.image.BufferedImage;

import com.google.zxing.LuminanceSource;
import java.awt.image.BufferedImage;

public class QRCodeDecoder {
    public String readQRCode(BufferedImage qrCodeImage) {
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(qrCodeImage);
            BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(source));
            MultiFormatReader reader = new MultiFormatReader();

            Result result = reader.decode(binaryBitmap);
            return result.getText();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static class BufferedImageLuminanceSource extends LuminanceSource {
        private final BufferedImage image;

        public BufferedImageLuminanceSource(BufferedImage qrCodeImage) {
            super(qrCodeImage.getWidth(), qrCodeImage.getHeight());
            this.image = qrCodeImage;
        }

        @Override
        public byte[] getRow(int y, byte[] row) {
            for (int x = 0; x < getWidth(); x++) {
                int pixel = image.getRGB(x, y);
                row[x] = (byte) ((pixel >> 16) & 0xFF); // Rouge
            }
            return row;
        }

        @Override
        public byte[] getMatrix() {
            int width = getWidth();
            int height = getHeight();
            byte[] matrix = new byte[width * height];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int pixel = image.getRGB(x, y);
                    matrix[y * width + x] = (byte) ((pixel >> 16) & 0xFF); // Rouge
                }
            }
            return matrix;
        }

        @Override
        public boolean isCropSupported() {
            return true;
        }

        @Override
        public LuminanceSource crop(int left, int top, int width, int height) {
            return new BufferedImageLuminanceSource(image.getSubimage(left, top, width, height));
        }

        @Override
        public boolean isRotateSupported() {
            return true;
        }

       @Override
public LuminanceSource rotateCounterClockwise() {
    return new BufferedImageLuminanceSource(rotateImage(image));
}

private BufferedImage rotateImage(BufferedImage image) {
    int width = image.getWidth();
    int height = image.getHeight();
    BufferedImage rotatedImage = new BufferedImage(height, width, image.getType());
    for (int x = 0; x < width; x++) {
        for (int y = 0; y < height; y++) {
            rotatedImage.setRGB(height - 1 - y, x, image.getRGB(x, y));
        }
    }
    return rotatedImage;
}

        }
    }