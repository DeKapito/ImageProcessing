package Algorithms;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PseudoTone {
    private BufferedImage image;
    private Binarization binarizator;
    private ShadesGray shadesGray;
    private int width;
    private int height;
    private int threshold;

    public PseudoTone(BufferedImage image) {
        shadesGray = new ShadesGray(image);
        this.image = shadesGray.getGrayImage();
        threshold = Binarization.otsuTreshold(image); //210
        height = image.getHeight();
        width = image.getWidth();
    }

    public BufferedImage getPseudoTone() {
        BufferedImage result = new BufferedImage(width, height, image.getType());

        int alpha, red;
        int pixel;
        int gray;
        Color colorOfPixel;

        int matrix[][] = {
                {1, 9, 3, 11},
                {13, 5, 15, 7},
                {4, 12, 2, 10},
                {16, 8, 14, 6} };

        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                pixel = image.getRGB(x, y);
                colorOfPixel = new Color(pixel);
                alpha = colorOfPixel.getAlpha();
                red = colorOfPixel.getRed();

                gray = red;

                gray = gray + (gray * matrix[x % 4][y % 4]) / 17;

                if(gray < 220) {
                    gray = 0;
                } else {
                    gray = 255;
                }

                result.setRGB(x, y, colorToRGB(alpha, gray, gray, gray));
            }
        }

        return result;
    }
    private static int colorToRGB(int alpha, int red, int green, int blue) {

        int newPixel = 0;
        newPixel += alpha;
        newPixel = newPixel << 8;
        newPixel += red; newPixel = newPixel << 8;
        newPixel += green; newPixel = newPixel << 8;
        newPixel += blue;

        return newPixel;

    }
}
