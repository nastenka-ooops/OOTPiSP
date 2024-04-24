package com.example.hierarchyextension.heros;

import com.example.lapa12.Element;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.FileNotFoundException;
import java.io.Serializable;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="@type")
@JsonSubTypes.Type(value = AbyssMage.class, name = "AbyssMage")
public class AbyssMage extends Shamachurl implements Serializable {
    private Element shield;

    public AbyssMage() {
    }

    public AbyssMage(int level, String imagePath, Element staff, Element shield) throws FileNotFoundException {
        super(level, imagePath, staff);
        this.shield = shield;
        this.setName("Abyss Mage");
    }
    @Override
    public String printInfo(){
        return super.printInfo() + " and " + this.getShield() + " shield";
    }

    public Element getShield() {
        return shield;
    }

    public void setShield(Element shield) {
        this.shield = shield;
    }

    @Override
    public String toString() {
        return "AbyssMage{" +
                "shield=" + shield +
                "} " + super.toString();
    }
}
