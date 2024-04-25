package com.example.lapa12.heros;

import com.example.lapa12.Element;

import java.io.FileNotFoundException;
import java.io.Serial;
import java.io.Serializable;

public class HilichurlGrenadier extends Hilichurl implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Element slime;

    public HilichurlGrenadier() {
    }

    public HilichurlGrenadier(int level, Element slime, String imagePath) throws FileNotFoundException {
        super(level, imagePath);
        this.slime = slime;
        this.setName("Grenadier");
       }

    public Element getSlime() {
        return slime;
    }

    public void setSlime(Element slime) {
        this.slime = slime;
    }

    @Override
    public String printInfo(){
        return super.printInfo()+ " with " + this.getSlime()+ " slime";
    }

    @Override
    public String toString() {
        return "HilichurlGrenadier{" +
                "slime=" + slime +
                "} " + super.toString();
    }
}
