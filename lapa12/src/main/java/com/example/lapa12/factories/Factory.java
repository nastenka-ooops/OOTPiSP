package com.example.lapa12.factories;

import com.example.lapa12.heros.Hilichurl;

import java.io.FileNotFoundException;

public abstract class Factory {
    public abstract Hilichurl create() throws FileNotFoundException;
}
