package Algorithms;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

public class FloydSteinberg {
    private BufferedImage image;
    private Binarization binarizator;
    private ShadesGray shadesGray;
    private int width;
    private int height;
    private int threshold;

    public FloydSteinberg(BufferedImage image) {
        shadesGray = new ShadesGray(image);
        this.image = deepCopy(shadesGray.getGrayImage());
        //threshold = Binarization.otsuTreshold(image); //210
        height = image.getHeight();
        width = image.getWidth();
    }

    public BufferedImage getResultImage() {
        BufferedImage result = new BufferedImage(width, height, image.getType());

        int oldPixel;
        int err;

        int currentPixel;
        int rightPixel;
        int rightDownPixel;
        int downPixel;
        int leftDownPixel;

        Color colorOfPixel;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                colorOfPixel = new Color(image.getRGB(x, y));
                oldPixel = colorOfPixel.getRed();

                if(oldPixel < 128) {
                    currentPixel = 0;
                } else {
                    currentPixel = 255;
                }
                result.setRGB(x, y, colorToRGB(colorOfPixel.getAlpha(), currentPixel, currentPixel, currentPixel));
                err = oldPixel - currentPixel;

                if(x + 1 < width){
                    colorOfPixel = new Color(image.getRGB(x + 1, y));
                    rightPixel = colorOfPixel.getRed();
                    rightPixel += (err * 7) >> 4;//.0/16;
                    image.setRGB(x + 1, y, colorToRGB(colorOfPixel.getAlpha(), rightPixel, rightPixel, rightPixel));
                }

                if((x - 1 >= 0) && (y + 1 < height)) {
                    colorOfPixel = new Color(image.getRGB(x - 1, y + 1));
                    leftDownPixel = colorOfPixel.getRed();
                    leftDownPixel += (err * 3) >> 4; /// 16;
                    image.setRGB(x - 1, y + 1, colorToRGB(colorOfPixel.getAlpha(), leftDownPixel, leftDownPixel, leftDownPixel));
                }

                if(y + 1 < height) {
                    colorOfPixel = new Color(image.getRGB(x, y + 1));
                    downPixel = colorOfPixel.getRed();
                    downPixel += (err * 5) >> 4;//.0 / 16;
                    image.setRGB(x, y + 1, colorToRGB(colorOfPixel.getAlpha(), downPixel, downPixel, downPixel));
                }

                if((y + 1 < height) && (x + 1 < width)) {
                    colorOfPixel = new Color(image.getRGB(x + 1, y + 1));
                    rightDownPixel = colorOfPixel.getRed();
                    rightDownPixel += (err / 16.0);
                    image.setRGB(x + 1, y + 1, colorToRGB(colorOfPixel.getAlpha(), rightDownPixel, colorOfPixel.getGreen(), colorOfPixel.getBlue()));
                }
            }
        }

        return result;
    }
        static BufferedImage deepCopy(BufferedImage bi) {
            ColorModel cm = bi.getColorModel();
            boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
            WritableRaster raster = bi.copyData(null);
            return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
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
//    }
}