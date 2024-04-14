package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlGrenadier;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class HilichurlGrenadierFactory extends Factory{
    @Override
    public Hilichurl create(String imagePath) throws FileNotFoundException {
        return new HilichurlGrenadier(99, Element.HYDRO, imagePath);
    }

    @Override
    public VBox createWindow(Hilichurl hilichurl) {
        Label lLevel = new Label("Level");
        TextField tfLevel = new TextField(String.valueOf(hilichurl.getLevel()));
        HBox hbLevel = new HBox(5,lLevel,tfLevel);
        VBox root = new VBox(20, hbLevel);

        ComboBox<Element> cbSlime = new ComboBox<>(FXCollections.observableArrayList(Element.values()));

        Label lSlime = new Label("Slime");
        cbSlime.setValue(((HilichurlGrenadier) hilichurl).getSlime());
        HBox hbSlime = new HBox(5,lSlime,cbSlime);
        root.getChildren().add(hbSlime);

        return root;
    }
}
