package com.example.lapa12.factories;

import com.example.lapa12.heros.Hilichurl;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class HilichurlFactory extends Factory{
    @Override
    public Hilichurl create(String imagePath) throws FileNotFoundException {
        return new Hilichurl(100, imagePath);
    }

    @Override
    public VBox createWindow(Hilichurl hilichurl) {
        Label lXP = new Label("XP");
        TextField tfXP = new TextField(String.valueOf(hilichurl.getXP()));
        tfXP.setDisable(true);
        HBox hbXP = new HBox(5,lXP,tfXP);

        Label lLevel = new Label("Level");
        TextField tfLevel = new TextField(String.valueOf(hilichurl.getLevel()));
        HBox hbLevel = new HBox(5,lLevel,tfLevel);

        VBox root = new VBox(20, hbLevel, hbXP);
        return root;
    }
}
