package com.example.hierarchyextension.landscapes;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;

import java.io.FileNotFoundException;

public class Bonfire extends Landscape{
    private final Element element=Element.FIRE;
    public Bonfire(String imagePath) throws FileNotFoundException {
        super(imagePath);
        this.setName("Bonfire");
    }

    @Override
    public void hitHilicurl(Hilichurl hilichurl) {
        hilichurl.setXP(hilichurl.getXP() - 30);
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        return "Bonfire{" +
                "element=" + element +
                "} " + super.toString();
    }
}
