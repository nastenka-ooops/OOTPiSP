package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlFighter;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class HilichurlFighterFactory extends Factory{
    @Override
    public Hilichurl create(String imagePath) throws FileNotFoundException {
        return new HilichurlFighter(56, Element.GEO, imagePath);
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

        ComboBox<Element> cbClub = new ComboBox<>(FXCollections.observableArrayList(Element.values()));

        Label lClub = new Label("Club");
        cbClub.setValue(((HilichurlFighter) hilichurl).getClub());
        HBox hbClub = new HBox(5,lClub,cbClub);
        root.getChildren().add(hbClub);

        return root;
    }
}
