package com.example.hierarchyextension.heros;

import com.example.lapa12.Element;
import com.example.lapa12.heros.Hilichurl;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.io.FileNotFoundException;
import java.io.Serializable;
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property="@type")
@JsonSubTypes.Type(value = Shamachurl.class, name = "Shamachurl")
public class Shamachurl extends Hilichurl implements Serializable {
    private Element staff;

    public Shamachurl() {
    }

    public Shamachurl(int level, String imagePath, Element staff) throws FileNotFoundException {
        super(level, imagePath);
        this.staff = staff;
        this.setName("Shamachurl");
    }

    public Element getStaff() {
        return staff;
    }

    public void setStaff(Element staff) {
        this.staff = staff;
    }

    @Override
    public String toString() {
        return "Shamachurl{" +
                "staff=" + staff +
                "} " + super.toString();
    }
    @Override
    public String printInfo(){
        return super.printInfo() +"with " + this.getStaff() + " staff ";
    }
}

