package com.example.landscapecreator.factories;


import com.example.landscapecreator.landscapes.Landscape;

import java.io.FileNotFoundException;

public abstract class Factory {
    public abstract Landscape create(String imagePath) throws FileNotFoundException;
}
