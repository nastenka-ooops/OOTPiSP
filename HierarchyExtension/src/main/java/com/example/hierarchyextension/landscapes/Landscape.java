package com.example.hierarchyextension.landscapes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="@type")
@JsonSubTypes({})

public abstract class Landscape implements Serializable {
    private String name;
    private String imagePath;
    @JsonIgnore
    transient private Image image;
    private int XP;
    private final int maxXP;
    private double x;
    private double y;

    public abstract void hitHilicurl();

    public Landscape(String imagePath) throws FileNotFoundException {
        this.imagePath = imagePath;
        this.image = new Image(new FileInputStream(imagePath), 100,130, true,true);
        this.maxXP=200;
        this.XP=this.maxXP;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public int getMaxXP() {
        return maxXP;
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

    @Override
    public String toString() {
        return "Landscape{" +
                "name='" + name + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", XP=" + XP +
                ", maxXP=" + maxXP +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
