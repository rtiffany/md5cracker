package com.md5cracker.controller;

import com.md5cracker.cracker.HashCracker;
import javafx.beans.binding.Bindings;
import javafx.css.PseudoClass;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.stage.FileChooser;
import javafx.util.StringConverter;
import java.io.File;
import java.util.function.UnaryOperator;

public class MainController {

    @FXML
    private TextField enteredHash;

    @FXML
    private Button dictionaryButton;

    @FXML
    private Button crackButton;

    @FXML
    private Label crackedHash;

    HashCracker crackHash = new HashCracker();

    @FXML
    protected void selectDictionaryPath() {

        FileChooser fileChooser = new FileChooser();

        // Only lists text files in FileChooser
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("Text files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extensionFilter);

        // Attempts to get user's home directory and set it the default directory when FileChooser opens
        String userDirectoryString = System.getProperty("user.home");
        File userDirectory = new File(userDirectoryString);
        if(userDirectory.canRead()) {
            fileChooser.setInitialDirectory(userDirectory);
        }
        
        // Tries to set crackHash's file path to the location of the dictionary text file
        try {
            crackHash.setFilePath(fileChooser.showOpenDialog(null));
            dictionaryButton.setText(crackHash.getFilePath());
        }
        catch (NullPointerException e) {
            dictionaryButton.setText("Choose Dictionary");
            crackedHash.setText("Error: No dictionary selected.");
        }
        catch (Exception e) {
            System.out.println(e);
        }
    }

    public void crackButtonAction() {

        // Check hash length and dictionary path
        try {
            if (enteredHash.getText().length() != 32) {
                crackedHash.setText("Error: Not an MD5 hash");
            }
            else if (crackHash.getFilePath() != null) {
                crackHash.setHash(enteredHash.getText());
                crackedHash.setText(crackHash.performCrack());
            }
        }
        // If dictionary path is null, throw an error
        catch (Exception e) {
            crackedHash.setText("Error: No dictionary path");
            System.out.println(e);
        }
    }

    @FXML
    public void initialize() {

        // Disable button if no text in enteredHash
        crackButton.disableProperty().bind(Bindings.isEmpty(enteredHash.textProperty()));

        // Style enteredHash TextField
        enteredHash.textProperty().addListener(event -> {
            enteredHash.pseudoClassStateChanged(
                    PseudoClass.getPseudoClass("error"),
                    !enteredHash.getText().isEmpty() &&
                            !enteredHash.getText().matches("[a-fA-F0-9]{32}") // regular expression for valid md5
            );
        });

        // Only Allow Certain Input in enteredHash TextField
        UnaryOperator<TextFormatter.Change> filter = change -> change.getControlNewText().matches("[0-9A-Fa-f]{0,32}") ? change : null;
        StringConverter<String> converter = new StringConverter<>() {
            @Override
            public String toString(String object) {
                return object == null ? "" : object;
            }
            @Override
            public String fromString(String string) {
                return string == null || string.isEmpty() ? null : string;
            }
        };
        TextFormatter<String> formatter = new TextFormatter<>(converter, null, filter);

        // listener for enteredHash TextField to HashCracker mutate method
        formatter.valueProperty().addListener((obs, oldValue, newValue) -> crackHash.setHash(newValue));
        enteredHash.setTextFormatter(formatter);
    }





}