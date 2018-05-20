package Algorithms;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class BlurBox {
    private BufferedImage image;
    private int width;
    private int height;

    public BlurBox(BufferedImage image) {
        this.image = image;
        width = image.getWidth();
        height = image.getHeight();
    }

    public BufferedImage getResultImage() {
        return  getBlurFilter(5).filter(image, null);
    }

    public static ConvolveOp getBlurFilter(int radius) {
        if(radius < 1) {
            throw new IllegalArgumentException();
        }

        int size = radius * 2 + 1;

        float dev = 1.0f / (size * size);
        float[] filterData = new float[size * size];

        for(int i = 0; i < filterData.length; i++) {
            filterData[i] = dev;
        }

        Kernel kernel = new Kernel(size, size, filterData);

        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }
}
