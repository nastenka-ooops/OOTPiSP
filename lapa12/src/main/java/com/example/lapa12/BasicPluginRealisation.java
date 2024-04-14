package com.example.lapa12;

import java.io.File;

public class BasicPluginRealisation implements PluginImplementation {
    @Override
    public void doSmth() {
        String folderPath = "images";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    com.example.lapa12.Main.imagePaths.add(file.getAbsolutePath());
                }
            }
        }
        System.out.println("Basic realisation");
    }
}
