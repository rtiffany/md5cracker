package com.md5cracker.controller;

import com.md5cracker.cracker.HashCracker;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

public class MainController {
    @FXML
    private Label filePath;

    @FXML
    private Label dictionaryErrorDialog;

    @FXML
    private Label hashErrorDialog;

    @FXML
    private TextField enteredHash;

    @FXML
    private Button crackButton;

    HashCracker crackHash = new HashCracker();

    @FXML
    public void initialize() {
        //System.out.println("HelloWorld");
        crackButton.disableProperty().bind(Bindings.isEmpty(enteredHash.textProperty()));
    }

    @FXML
    protected void selectDictionaryPath() {
        FileChooser fileChooser = new FileChooser();
        try {
            crackHash.setFilePath(fileChooser.showOpenDialog(filePath.getScene().getWindow()));
            filePath.setText(crackHash.getFilePath());
        }
        catch (NullPointerException e) {
            dictionaryErrorDialog.setText("Error: No dictionary selected.");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void crackHash() {
        System.out.println(crackHash.yolo());

    }

}