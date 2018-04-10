package sample;

import Algorithms.ImageInfo;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
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

    private String filepath;

    @FXML
    public void clickChooseFile() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Images", "*.*"),
                                        new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                                        new FileChooser.ExtensionFilter("PNG", "*.png"),
                                        new FileChooser.ExtensionFilter("BMP", "*.bmp"));
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile != null)
            filepath = selectedFile.getAbsolutePath();
        setResultPicture();
    }

    @FXML
    public void setResultPicture() {
        resultPicture.setImage(new Image("file:\\" + filepath));
    }

    @FXML
    public void clickInformationButton() {
        try {
            ImageInfo imageInfo = new ImageInfo(filepath);
            labelWidth.setText("Ширина зображення: " + imageInfo.getWidth());
            labelHeight.setText("Висота зображення: " + imageInfo.getHeight());
            labelDepth.setText("Глибина зображення: " + imageInfo.getDepth());
            labelPixelization.setText("Пікселізація зображення: " + imageInfo.getPixelization());
        } catch (IOException e) {
            labelWidth.setText("Не вдалось відкрити зображення");
        }

    }

}
