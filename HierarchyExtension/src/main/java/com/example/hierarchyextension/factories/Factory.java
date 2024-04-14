package com.example.hierarchyextension.factories;

import com.example.hierarchyextension.landscapes.Landscape;

import java.io.FileNotFoundException;

public abstract class Factory {
    public abstract Landscape create(String imagePath) throws FileNotFoundException;
}
