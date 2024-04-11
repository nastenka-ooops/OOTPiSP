package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlFighter;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class HilichurlFighterFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new HilichurlFighter(56, Element.GEO);
    }
    @Override
    public VBox createWindow(Hilichurl hilichurl) {
        Label lLevel = new Label("Level");
        TextField tfLevel = new TextField(String.valueOf(hilichurl.getLevel()));
        HBox hbLevel = new HBox(5,lLevel,tfLevel);
        VBox root = new VBox(20, hbLevel);

        ComboBox<Element> cbClub = new ComboBox<>(FXCollections.observableArrayList(Element.values()));

        Label lClub = new Label("Club");
        cbClub.setValue(((HilichurlFighter) hilichurl).getClub());
        HBox hbClub = new HBox(5,lClub,cbClub);
        root.getChildren().add(hbClub);

        return root;
    }
}
