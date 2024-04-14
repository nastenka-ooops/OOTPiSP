package com.example.hierarchyextension.factories;

import com.example.hierarchyextension.heros.Shamachurl;
import com.example.lapa12.Element;
import com.example.lapa12.factories.Factory;
import com.example.lapa12.heros.Hilichurl;
import javafx.collections.FXCollections;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public class ShamachurlFactory extends Factory {
    @Override
    public Hilichurl create(String imagePath) throws FileNotFoundException {
        return new Shamachurl(99, imagePath, Element.ANEMO);
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

        ComboBox<Element> cbStaff = new ComboBox<>(FXCollections.observableArrayList(Element.values()));

        Label lStaff = new Label("Staff");
        cbStaff.setValue(((Shamachurl) hilichurl).getStaff());
        HBox hbStaff = new HBox(5,lStaff,cbStaff);
        root.getChildren().add(hbStaff);

        return root;
    }
}
