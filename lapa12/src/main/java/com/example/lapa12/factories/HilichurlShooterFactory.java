package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlShooter;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class HilichurlShooterFactory extends Factory{
    @Override
    public Hilichurl create(String imagePath) throws FileNotFoundException {
        return new HilichurlShooter(55, Element.ANEMO, imagePath);
    }
    @Override
    public VBox createWindow(Hilichurl hilichurl) {
        Label lLevel = new Label("Level");
        TextField tfLevel = new TextField(String.valueOf(hilichurl.getLevel()));
        HBox hbLevel = new HBox(5,lLevel,tfLevel);
        VBox root = new VBox(20, hbLevel);

        ComboBox<Element> cbCrossbow = new ComboBox<>(FXCollections.observableArrayList(Element.values()));

        Label lCrossbow = new Label("Crossbow");
        cbCrossbow.setValue(((HilichurlShooter) hilichurl).getCrossbow());
        HBox hbCrossbow = new HBox(5,lCrossbow,cbCrossbow);
        root.getChildren().add(hbCrossbow);

        return root;
    }
}
