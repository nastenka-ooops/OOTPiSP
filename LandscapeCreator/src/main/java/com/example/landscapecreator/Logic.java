package com.example.landscapecreator;

import com.example.landscapecreator.factories.BonfireFactory;
import com.example.landscapecreator.factories.BushFactory;
import com.example.landscapecreator.factories.Factory;
import com.example.landscapecreator.landscapes.Landscape;
import javafx.animation.AnimationTimer;

import java.io.File;

import static com.example.landscapecreator.LandscapeCreator.imagePaths;
import static com.example.landscapecreator.LandscapeCreator.landscapes;

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
                        landscape.findNearHilichurls();
                    }
                    lastUpdateTime[0] = currentTime;
                }
            }
        }; animationTimer.start();


    }
    public void loadLandscapeImages(){
        String folderPath = "../LandscapeCreator/images/landscape";

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
