package com.example.landscapecreator.factories;


import com.example.landscapecreator.landscapes.Bonfire;
import com.example.landscapecreator.landscapes.Landscape;

import java.io.FileNotFoundException;

public class BonfireFactory extends Factory {
    @Override
    public Landscape create(String imagePath) throws FileNotFoundException {
        return new Bonfire(imagePath);
    }
}
