package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlShooter;

import java.io.FileNotFoundException;

public class HilichurlShooterFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new HilichurlShooter(55, Element.ANEMO);
    }
}
