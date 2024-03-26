package com.example.lapa12.heros;

import com.example.lapa12.Element;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class HilichurlShooter extends Hilichurl implements Serializable {
    private Element crossbow;

    public HilichurlShooter(int level, Element crossbow) throws FileNotFoundException {
        super(level);
        this.crossbow = crossbow;
        this.setName("Shooter");
        this.setImagePath("src/main/java/com/example/lapa12/images/" + this.getName() + ".jpg");
        this.setImage(new Image(new FileInputStream(getImagePath()), 150, 140, true, true));
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
