package sample;

import Algorithms.ImageInfo;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class Controller {
    @FXML
    private Button btnChooseFile;
    @FXML
    private Button informationButton;
    @FXML
    private ImageView resultPicture;
    @FXML
    private Label labelWidth;
    @FXML
    private Label labelHeight;
    @FXML
    private Label labelDepth;
    @FXML
    private Label labelPixelization;
    @FXML
    private Label labelFileExtension;

    private String filepath;
    private BufferedImage startImage;
    private BufferedImage resultImage;

    @FXML
    public void clickChooseFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
                                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                                        new FileChooser.ExtensionFilter("PNG", "*.png"),
                                        new FileChooser.ExtensionFilter("BMP", "*.bmp"));
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null) {
            filepath = selectedFile.getAbsolutePath();
            try {
                startImage = ImageIO.read(new File(filepath));
                resultImage = startImage;
            } catch (IOException e) {
                labelWidth.setText("Не вдалось відкрити зображення");
            }
        }
        setResultPicture(resultImage);
    }

    @FXML
    public void saveFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(//new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("BMP", "*.bmp"));
        File selectedFile = fc.showSaveDialog(null);

        try {
            ImageIO.write(resultImage, ImageInfo.getFileExtension(selectedFile.getAbsolutePath()), selectedFile);
        } catch (Exception e) {
            labelWidth.setText("Не вдалось зберегти зображення");
        }
    }

    @FXML
    public void setResultPicture(BufferedImage image) {
        resultPicture.setImage(SwingFXUtils.toFXImage(image, null));
    }

    @FXML
    public void clickInformationButton() {
        try {
            ImageInfo imageInfo = new ImageInfo(filepath);
            labelWidth.setText("Ширина зображення: " + imageInfo.getWidth());
            labelHeight.setText("Висота зображення: " + imageInfo.getHeight());
            labelDepth.setText("Глибина зображення: " + imageInfo.getDepth());
            labelPixelization.setText("Пікселізація зображення: " + imageInfo.getPixelization());
            labelFileExtension.setText("Розширення зображення: " + imageInfo.getFileFormat());
        } catch (IOException | NullPointerException e) {
            labelWidth.setText("Не вдалось відкрити зображення");
        }
    }
}
