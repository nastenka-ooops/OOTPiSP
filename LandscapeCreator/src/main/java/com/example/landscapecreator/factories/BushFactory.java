package com.example.landscapecreator.factories;


import com.example.landscapecreator.landscapes.Bush;
import com.example.landscapecreator.landscapes.Landscape;

import java.io.FileNotFoundException;

public class BushFactory extends Factory {
    @Override
    public Landscape create(String imagePath) throws FileNotFoundException {
        return new Bush(imagePath);
    }
}
