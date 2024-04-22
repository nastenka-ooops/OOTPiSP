package com.example.hierarchyextension;

import com.example.hierarchyextension.factories.AbyssMageFactory;
import com.example.hierarchyextension.factories.ShamachurlFactory;
import com.example.lapa12.Main;

import java.io.File;

public class Logic extends com.example.lapa12.Logic {
    public void loadHilichurlImages(){
        String folderPath = "../HierarchyExtension/images/hilichurl";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    Main.imagePaths.add(file.getAbsolutePath());
                }
            }
        }
    }
    public void createHilichurlFactories(){
        Main.factories.add(new ShamachurlFactory());
        Main.factories.add(new AbyssMageFactory());
    }


}
