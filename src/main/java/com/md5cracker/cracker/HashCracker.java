package com.md5cracker.cracker;
import java.io.File;

public class HashCracker {

    private File dictionary;
    private String hash;

    public HashCracker() {

    }

    public void setFilePath(File getDictionary) {
        // Maybe add try?
        dictionary = getDictionary;
    }

    public String getFilePath() {
         return dictionary.getPath();
    }

    public String yolo() {
        return "Sup";
    }

}
