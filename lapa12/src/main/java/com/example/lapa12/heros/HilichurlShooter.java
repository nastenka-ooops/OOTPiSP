package com.example.lapa12.heros;

import com.example.lapa12.Element;

import java.io.FileNotFoundException;
import java.io.Serial;
import java.io.Serializable;

public class HilichurlShooter extends Hilichurl implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Element crossbow;

    public HilichurlShooter() {
    }

    public HilichurlShooter(int level, Element crossbow, String imagePath) throws FileNotFoundException {
        super(level, imagePath);
        this.crossbow = crossbow;
        this.setName("Shooter");
      }

    public Element getCrossbow() {
        return crossbow;
    }

    public void setCrossbow(Element crossbow) {
        this.crossbow = crossbow;
    }

    @Override
    public String printInfo(){
        return super.printInfo() + " with " + this.getCrossbow() + " crossbow";
    }

    @Override
    public String toString() {
        return "HilichurlShooter{" +
                "crossbow=" + crossbow +
                "} " + super.toString();
    }
}
