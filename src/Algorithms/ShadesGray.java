package Algorithms;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;

public class ShadesGray {
    private BufferedImage image;
    private int[] data;
    private int height;
    private int width;

    public ShadesGray(BufferedImage image) {
        this.image = image;
        height = image.getHeight();
        width = image.getWidth();
    }

    public static Color toGrayScale(Color color) {
        int rgb = (int) (((double) color.getRed() * 0.2125) + ((double) color.getGreen() * 0.7154) + ((double) color.getBlue() * 0.0721));
        if (rgb > 255) {
            rgb = 255;
        }
        return new Color(rgb, rgb, rgb, color.getAlpha());
    }

    public BufferedImage getGrayImage() {

        WritableRaster raster = image.getRaster();
        data = new int[4];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                data = raster.getPixel(x, y, new int[4]);
                Color color = new Color(data[0], data[1], data[2], data[3]);
                Color color2 = ShadesGray.toGrayScale(color);
                data = new int[]{color2.getRed(), color2.getGreen(), color2.getBlue(), color2.getAlpha()};
                raster.setPixel(x, y, data);
            }
        }
        return image;
    }
}
