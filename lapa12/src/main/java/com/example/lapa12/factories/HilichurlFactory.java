package com.example.lapa12.factories;

import com.example.lapa12.heros.Hilichurl;

import java.io.FileNotFoundException;

public class HilichurlFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new Hilichurl(100);
    }
}
