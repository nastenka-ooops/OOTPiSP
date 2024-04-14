package com.example.custompictures;

import com.example.lapa12.PluginImplementation;
import javafx.scene.control.RadioButton;

import java.io.File;

import static com.example.lapa12.Main.*;

public class CustomPictures implements PluginImplementation {
    @Override
    public void doSmth() {
        String folderPath = "../CustomPictures/images";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    com.example.lapa12.Main.imagePaths.add(file.getAbsolutePath());
                }
            }
        }
        System.out.println("custom");
        addNewInterface();
    }

    public void addNewInterface(){
        RadioButton rbCustomPictures = new RadioButton("Custom pictures");
        controls.getChildren().add(rbCustomPictures);
        rbCustomPictures.setOnAction(actionEvent -> {
            scene.getRoot().requestFocus();
            if (rbCustomPictures.isSelected()){
                offset=0;
            } else {
                offset=8;
            }
        });

    }
}
