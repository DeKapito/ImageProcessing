package Algorithms;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageInfo {
    private BufferedImage image;
    private String filepath;
    private String fileFormat;
    private int width;
    private int height;
    private int depth;
    private long pixelization;

    public ImageInfo(String filepath) throws IOException {
        this.filepath = filepath;
        image = ImageIO.read(new File(filepath));
        fileFormat = getFileExtension(filepath);
        width = image.getWidth();
        height = image.getHeight();
        depth = image.getColorModel().getPixelSize();
        pixelization = width * height;
    }

    private static String getFileExtension(String filepath) {
        int index = filepath.indexOf('.');
        return index == -1? null : filepath.substring(index);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getDepth() {
        return depth;
    }

    public long getPixelization() {
        return pixelization;
    }
}
