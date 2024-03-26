package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlGrenadier;

import java.io.FileNotFoundException;

public class HilichurlGrenadierFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new HilichurlGrenadier(99, Element.HYDRO);
    }
}
