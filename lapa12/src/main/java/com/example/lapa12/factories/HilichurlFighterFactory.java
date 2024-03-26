package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlFighter;

import java.io.FileNotFoundException;

public class HilichurlFighterFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new HilichurlFighter(56, Element.GEO);
    }
}
