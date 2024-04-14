package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.Lawachurl;
import com.example.lapa12.heros.Mitachurl;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class LawachurlFactory extends Factory{
    @Override
    public Hilichurl create(String imagePath) throws FileNotFoundException {
        return new Lawachurl(80, Element.ELECTRO, Element.GEO, Element.FIRE, imagePath);
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

        ComboBox<Element> cbAxe = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbShield = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbShell = new ComboBox<>(FXCollections.observableArrayList(Element.values()));

        Label lAxe = new Label("Axe");
        cbAxe.setValue(((Mitachurl) hilichurl).getAxe());
        HBox hbAxe = new HBox(5,lAxe,cbAxe);
        root.getChildren().add(hbAxe);

        Label lShield = new Label("Shield");
        cbShield.setValue(((Mitachurl) hilichurl).getShield());
        HBox hbShield = new HBox(5,lShield,cbShield);
        root.getChildren().add(hbShield);

        Label lShell = new Label("Shell");
        cbShell.setValue(((Lawachurl) hilichurl).getShell());
        HBox hbShell = new HBox(5,lShell,cbShell);
        root.getChildren().add(hbShell);

        return root;
    }
}
