package com.example.lapa12.factories;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.HilichurlGuard;

import java.io.FileNotFoundException;

public class HIlichurlGuardFactory extends Factory{
    @Override
    public Hilichurl create() throws FileNotFoundException {
        return new HilichurlGuard(69, Element.FIRE, Element.DENDRO);
    }
}
