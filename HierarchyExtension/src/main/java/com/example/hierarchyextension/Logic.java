package com.example.hierarchyextension;

import com.example.hierarchyextension.factories.*;
import com.example.hierarchyextension.landscapes.Landscape;
import com.example.lapa12.Main;
import javafx.animation.AnimationTimer;

import java.io.File;

import static com.example.hierarchyextension.HierarchyExtension.imagePaths;
import static com.example.hierarchyextension.HierarchyExtension.landscapes;

public class Logic extends com.example.lapa12.Logic {
    AnimationTimer animationTimer;

    public void hitHilichurl() {
        double desiredFrameRate = 0.3; // Желаемая частота кадров в кадрах в секунду
        long oneFrameTime = (long) (1_000_000_000 / desiredFrameRate); // Время одного кадра в наносекундах

        final long[] lastUpdateTime = {0};
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long currentTime) {
                long elapsedTime = currentTime - lastUpdateTime[0];
                if (elapsedTime >= oneFrameTime) {
                    for (Landscape landscape :
                            landscapes.landscapes) {
                        landscape.findNearHilichurls();
                    }
                    lastUpdateTime[0] = currentTime;
                }
            }
        }; animationTimer.start();


    }
    public void loadLandscapeImages(){
        String folderPath = "../HierarchyExtension/images/landscape";

        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        if (listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    imagePaths.add(file.getAbsolutePath());
                }
            }
        }
    }
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
    public void createLandscapesFactories(Factory[] factories){
        factories[0] = new BonfireFactory();
        factories[1] = new BushFactory();
    }
    public void createHilichurlFactories(){
        Main.factories.add(new ShamachurlFactory());
        Main.factories.add(new AbyssMageFactory());
    }


}
