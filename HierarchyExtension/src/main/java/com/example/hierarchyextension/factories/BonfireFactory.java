package com.example.hierarchyextension.factories;

import com.example.hierarchyextension.landscapes.Bonfire;
import com.example.hierarchyextension.landscapes.Landscape;

import java.io.FileNotFoundException;

public class BonfireFactory extends Factory {
    @Override
    public Landscape create(String imagePath) throws FileNotFoundException {
        return new Bonfire(imagePath);
    }
}
