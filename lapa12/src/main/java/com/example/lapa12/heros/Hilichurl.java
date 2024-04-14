package com.example.lapa12.heros;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="@type")
@JsonSubTypes({
        @JsonSubTypes.Type(value=HilichurlFighter.class, name="Fighter"),
        @JsonSubTypes.Type(value=HilichurlGrenadier.class, name="Grenadier"),
        @JsonSubTypes.Type(value=HilichurlGuard.class, name="Guard"),
        @JsonSubTypes.Type(value=HilichurlShooter.class, name="Shooter"),
        @JsonSubTypes.Type(value=Lawachurl.class, name="Lawachurl"),
        @JsonSubTypes.Type(value=Mitachurl.class, name="Mitachurl")
})
public class Hilichurl implements Serializable {
    private int level;
    private int XP;
    private int maxXP;
    private String name;
    @JsonIgnore
    transient private Image image;
    private String imagePath;
    private double x;
    private double y;

    public Hilichurl(int level, String imagePath) throws FileNotFoundException {
        setLevel(level);
        this.name = "Hilichurl";
        this.imagePath = imagePath;
        this.image = new Image(new FileInputStream(imagePath));
    }

    public Hilichurl() {
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String printInfo() {
        return "This is " + this.getName() + " of " + this.getLevel() + " level with " + this.getXP() + " XP";
    }
    public void recovery(){
        this.XP=this.maxXP;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
        if (level <= 0) {
            this.maxXP = 0;
        } else if (level > 0 && level < 30) {
            this.maxXP = 10;
        } else if (level >= 30 && level < 70) {
            this.maxXP = 50;
        } else if (level >= 70 && level < 100) {
            this.maxXP = 100;
        } else {
            this.maxXP = 150;
        }
        this.XP=this.maxXP;
    }

    public int getXP() {
        return XP;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Hilichurl{" +
                "level=" + level +
                ", XP=" + XP +
                ", maxXP=" + maxXP +
                ", name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
