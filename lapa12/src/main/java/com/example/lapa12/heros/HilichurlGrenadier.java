package com.example.lapa12.heros;

import com.example.lapa12.Element;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class HilichurlGrenadier extends Hilichurl implements Serializable {
    private Element slime;

    public HilichurlGrenadier(int level, Element slime) throws FileNotFoundException {
        super(level);
        this.slime = slime;
        this.setName("Grenadier");
        this.setImagePath("src/main/java/com/example/lapa12/images/" + this.getName() + ".jpg");
        this.setImage(new Image(new FileInputStream(getImagePath()), 150, 140, true, true));
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
