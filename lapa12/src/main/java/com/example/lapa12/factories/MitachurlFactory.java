package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.Mitachurl;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class MitachurlFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new Mitachurl(79, Element.HYDRO, Element.CRIO);
    }

    @Override
    public VBox createWindow(Hilichurl hilichurl) {
        Label lLevel = new Label("Level");
        TextField tfLevel = new TextField(String.valueOf(hilichurl.getLevel()));
        HBox hbLevel = new HBox(5,lLevel,tfLevel);
        VBox root = new VBox(20, hbLevel);

        ComboBox<Element> cbAxe = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbShield = new ComboBox<>(FXCollections.observableArrayList(Element.values()));

        Label lAxe = new Label("Axe");
        cbAxe.setValue(((Mitachurl) hilichurl).getAxe());
        HBox hbAxe = new HBox(5,lAxe,cbAxe);
        root.getChildren().add(hbAxe);

        Label lShield = new Label("Shield");
        cbShield.setValue(((Mitachurl) hilichurl).getShield());
        HBox hbShield = new HBox(5,lShield,cbShield);
        root.getChildren().add(hbShield);

        return root;
    }
}
