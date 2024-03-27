package com.example.lapa12.heros;

import com.example.lapa12.Element;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Lawachurl extends Mitachurl implements Serializable {
    private Element shell;

    public Lawachurl() {
    }

    public Lawachurl(int level, Element axe, Element shield) throws FileNotFoundException {
        super(level, axe, shield);
    }

    public Lawachurl(int level, Element axe, Element shield, Element shell) throws FileNotFoundException {
        super(level, axe, shield);
        this.shell = shell;
        this.setName("Lawachurl");
        this.setImagePath("src/main/java/com/example/lapa12/images/" + this.getName() + ".jpg");
        this.setImage(new Image(new FileInputStream(getImagePath()), 150, 140, true, true));
    }
    @Override
    public String printInfo(){
        return super.printInfo() + " and " + this.getShell() + " shell";
    }

    public Element getShell() {
        return shell;
    }

    public void setShell(Element shell) {
        this.shell = shell;
    }

    @Override
    public String toString() {
        return "Lawachurl{" +
                "shell=" + shell +
                "} " + super.toString();
    }
}
