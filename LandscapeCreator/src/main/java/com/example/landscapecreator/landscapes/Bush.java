package com.example.landscapecreator.landscapes;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;

import java.io.FileNotFoundException;

public class Bush extends Landscape {
    private final Element element=Element.DENDRO;

    public Bush(String imagePath) throws FileNotFoundException {
        super(imagePath);
        this.setName("Bush");
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        return "Bush{" +
                "element=" + element +
                "} " + super.toString();
    }

    @Override
    public void hitHilicurl(Hilichurl hilichurl) {
        hilichurl.setXP(hilichurl.getXP() - 10);
        System.out.println("ударило кустом");
    }
}
