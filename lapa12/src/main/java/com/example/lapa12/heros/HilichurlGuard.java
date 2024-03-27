package com.example.lapa12.heros;

import com.example.lapa12.Element;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class HilichurlGuard extends HilichurlFighter implements Serializable {
    private Element shield;

    public HilichurlGuard() {
    }

    public HilichurlGuard(int level, Element club, Element shield) throws FileNotFoundException {
        super(level, club);
        this.shield = shield;
        this.setName("Guard");
        this.setImagePath("src/main/java/com/example/lapa12/images/" + this.getName() + ".jpg");
        this.setImage(new Image(new FileInputStream(getImagePath()), 150, 140, true, true));
    }

    public Element getShield() {
        return shield;
    }

    public void setShield(Element shield) {
        this.shield = shield;
    }

    @Override
    public String printInfo(){
        return super.printInfo() + " and " + this.getShield() + " shield";
    }

    @Override
    public String toString() {
        return "HilichurlGuard{" +
                "shield=" + shield +
                "} " + super.toString();
    }
}
