package com.md5cracker.controller;

import com.md5cracker.cracker.HashCracker;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

public class MainController {
    @FXML
    private Label filePath;

    HashCracker crackHash = new HashCracker();

    @FXML
    public void initialize() {
        System.out.println("HelloWorld");
    }

    @FXML
    protected void selectDictionaryPath() {
        FileChooser fileChooser = new FileChooser();
        crackHash.setFilePath(fileChooser.showOpenDialog(filePath.getScene().getWindow()));
        filePath.setText("Dictionary: " + crackHash.getFilePath());
    }
}