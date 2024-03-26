package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.Mitachurl;

import java.io.FileNotFoundException;

public class MitachurlFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new Mitachurl(79, Element.HYDRO, Element.CRIO);
    }
}
