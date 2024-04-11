package com.example.lapa12.factories;

import com.example.lapa12.heros.Hilichurl;
import javafx.scene.layout.VBox;

import java.io.FileNotFoundException;

public abstract class Factory {
    public abstract Hilichurl create() throws FileNotFoundException;
    public abstract VBox createWindow(Hilichurl hilichurl);
}
