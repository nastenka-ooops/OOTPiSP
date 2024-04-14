package com.example.custompictures;

import com.example.lapa12.PluginRealisation;

public class Main implements PluginRealisation {
    public static void main(String[] args) {
        int w = com.example.lapa12.Main.W;
        System.out.println(w);
    }

    @Override
    public void doSmth() {
        System.out.println("custom");
    }
}
