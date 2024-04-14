package com.example.hierarchyextension.factories;

import com.example.hierarchyextension.landscapes.Bush;
import com.example.hierarchyextension.landscapes.Landscape;

import java.io.FileNotFoundException;

public class BushFactory extends Factory{
    @Override
    public Landscape create(String imagePath) throws FileNotFoundException {
        return new Bush(imagePath);
    }
}
