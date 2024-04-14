package com.example.lapa12.heros;

import com.example.lapa12.Element;

import java.io.FileNotFoundException;
import java.io.Serializable;

public class HilichurlFighter extends Hilichurl implements Serializable {
    private Element club;

    public HilichurlFighter() {
    }

    @Override
    public String
    toString() {
        return "HilichurlFighter{" +
                "club=" + club +
                "} " + super.toString();
    }

    public HilichurlFighter(int level, Element club, String imagePath) throws FileNotFoundException {
        super(level, imagePath);
        this.club = club;
        this.setName("Fighter");
       // this.setImagePath("src/main/java/com/example/lapa12/images/" + this.getName() + ".jpg");
       // this.setImage(new Image(new FileInputStream(getImagePath()), 150, 140, true, true));
    }

    public Element getClub() {
        return club;
    }

    public void setClub(Element club) {
        this.club = club;
    }

    @Override
    public String printInfo(){
        return super.printInfo() +"with " + this.getClub() + " club ";
    }
}
