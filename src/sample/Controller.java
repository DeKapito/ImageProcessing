package sample;

import Algorithms.*;
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
    private Button binarizationButton;
    @FXML
    private Button pseudoToneButton;
    @FXML
    private Button floydSteinbergButton;
    @FXML
    private Button blurBoxButton;
    @FXML
    private Button gaussBlurButton;
    @FXML
    private ImageView viewPicture;
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
    private String fileExtension;

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
                fileExtension = ImageInfo.getFileExtension(filepath);
                resultImage = startImage;
            } catch (IOException e) {
                labelWidth.setText("Не вдалось відкрити зображення");
            }
        }
        resetInfo();
        setResultPicture(resultImage);
    }

    @FXML
    public void saveFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter(fileExtension.toUpperCase(), "*." + fileExtension));
        File selectedFile = fc.showSaveDialog(null);

        try {
            ImageIO.write(resultImage, fileExtension, selectedFile);
        } catch (Exception e) {
            labelWidth.setText("Не вдалось зберегти зображення");
        }
    }

    public void setResultPicture(BufferedImage image) {
        if(image != null)
            viewPicture.setImage(SwingFXUtils.toFXImage(image, null));
    }

    @FXML
    public void shadesGray() {
        if(startImage != null) {
            ShadesGray transform = new ShadesGray(startImage);
            resultImage = transform.getGrayImage();
            setResultPicture(resultImage);
        }
    }

    @FXML
    public void binarization() {
        if(startImage != null) {
            Binarization binarization = new Binarization(startImage);
            resultImage = binarization.getBinarizedImage();
            setResultPicture(resultImage);
        }
    }

    @FXML
    public void pseudoTone() {
        if(startImage != null) {
            PseudoTone pseudoTone = new PseudoTone(startImage);
            resultImage = pseudoTone.getPseudoTone();
            setResultPicture(resultImage);
        }
    }

    @FXML
    public void blurBox() {
        if(startImage != null) {
            BlurBox blurBox = new BlurBox(startImage);
            resultImage = blurBox.getResultImage();
            setResultPicture(resultImage);
        }
    }

    @FXML
    public void gaussBlur() {
        if(startImage != null) {
            GaussBlur gaussBlur = new GaussBlur(startImage);
            resultImage = gaussBlur.getResultImage();
            setResultPicture(resultImage);
        }
    }

    @FXML
    public void floydSteinberg() {
        if(startImage != null) {
            FloydSteinberg floydSteinberg = new FloydSteinberg(startImage);
            resultImage = floydSteinberg.getResultImage();
            setResultPicture(resultImage);
        }
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

    public void resetInfo() {
        labelWidth.setText("");
        labelHeight.setText("");
        labelDepth.setText("");
        labelPixelization.setText("");
        labelFileExtension.setText("");
    }
}
