package com.example.hierarchyextension;

import com.example.hierarchyextension.factories.BonfireFactory;
import com.example.hierarchyextension.factories.BushFactory;
import com.example.hierarchyextension.factories.Factory;
import com.example.hierarchyextension.landscapes.Landscape;
import javafx.animation.AnimationTimer;

import java.io.File;

import static com.example.hierarchyextension.HierarchyExtension.imagePaths;
import static com.example.hierarchyextension.HierarchyExtension.landscapes;

public class Logic {
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
                        landscape.hitHilicurl();
                        System.out.println("some exception");
                    }
                    lastUpdateTime[0] = currentTime;
                }
            }
        }; animationTimer.start();


    }
    public void loadImages(){
        String folderPath = "../HierarchyExtension/images";

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
    public void createLandscapesFactories(Factory[] factories){
        factories[0] = new BonfireFactory();
        factories[1] = new BushFactory();
    }
}
