package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlFighter;
import com.example.lapa12.heros.HilichurlGuard;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class HIlichurlGuardFactory extends Factory{
    @Override
    public Hilichurl create(String imagePath) throws FileNotFoundException {
        return new HilichurlGuard(69, Element.FIRE, Element.DENDRO, imagePath);
    }

    @Override
    public VBox createWindow(Hilichurl hilichurl) {
        Label lLevel = new Label("Level");
        TextField tfLevel = new TextField(String.valueOf(hilichurl.getLevel()));
        HBox hbLevel = new HBox(5,lLevel,tfLevel);
        VBox root = new VBox(20, hbLevel);

        ComboBox<Element> cbClub = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbShield = new ComboBox<>(FXCollections.observableArrayList(Element.values()));

        Label lClub = new Label("Club");
        cbClub.setValue(((HilichurlFighter) hilichurl).getClub());
        HBox hbClub = new HBox(5,lClub,cbClub);
        root.getChildren().add(hbClub);

        Label lShield = new Label("Shield");
        cbShield.setValue(((HilichurlGuard) hilichurl).getShield());
        HBox hbShield = new HBox(5,lShield,cbShield);
        root.getChildren().add(hbShield);

        return root;
    }
}
