package Algorithms;

import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;

public class GaussBlur {
    private BufferedImage image;

    public GaussBlur(BufferedImage image) {
        this.image = image;
    }

    public BufferedImage getResultImage() {
        return  getGaussianBlurFilter(20).filter(image, null);
    }

    public static ConvolveOp getGaussianBlurFilter(int radius) {

        if (radius < 1) {
            throw new IllegalArgumentException();
        }

        int size = radius * 2 + 1;
        float[] data = new float[size];

        float sigma = radius / 3.0f;
        float twoSigmaSquare = 2.0f * sigma * sigma;

        for (int i = -radius; i <= radius; i++) {
            float distance = i * i;
            int index = i + radius;
            data[index] = (float) (Math.exp(-distance / twoSigmaSquare) / Math.sqrt(twoSigmaSquare * Math.PI));
        }

        Kernel kernel = new Kernel(size, 1, data);

        return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
    }
}
