package com.example.lapa12.heros;

import com.example.lapa12.Element;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class Mitachurl extends Hilichurl implements Serializable {
    @JsonProperty("axe")
    private Element axe;
    @JsonProperty("shield")
    private Element shield;

    public Mitachurl() {
    }

    public Mitachurl(int level, Element axe, Element shield, String imagePath) throws FileNotFoundException {
        super(level,imagePath);
        this.axe = axe;
        this.shield = shield;
        this.setName("Mitachurl");
        //  this.setImagePath("src/main/java/com/example/lapa12/images/" + this.getName() + ".jpg");
       // this.setImage(new Image(new FileInputStream(getImagePath()), 150, 140, true, true));
    }
    @Override
    public String printInfo(){
        return super.printInfo() + " with " + this.getAxe() + " axe and " + this.getShield()+ " shield";
    }
    @JsonProperty("axe")
    public Element getAxe() {
        return axe;
    }

    public void setAxe(Element axe) {
        this.axe = axe;
    }
    @JsonProperty("shield")
    public Element getShield() {
        return shield;
    }

    public void setShield(Element shield) {
        this.shield = shield;
    }

    @Override
    public String toString() {
        return "Mitachurl{" +
                "axe=" + axe +
                ", shield=" + shield +
                "} " + super.toString();
    }
}
