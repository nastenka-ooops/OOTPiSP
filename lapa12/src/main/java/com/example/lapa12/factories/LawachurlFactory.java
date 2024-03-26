package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.Lawachurl;

import java.io.FileNotFoundException;

public class LawachurlFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new Lawachurl(80, Element.ELECTRO, Element.GEO, Element.FIRE);
    }
}
