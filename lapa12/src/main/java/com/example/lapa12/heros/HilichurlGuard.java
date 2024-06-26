package com.example.lapa12.heros;

import com.example.lapa12.Element;
import com.example.lapa12.visitor.Visitor;

import java.io.FileNotFoundException;
import java.io.Serial;
import java.io.Serializable;

public class HilichurlGuard extends HilichurlFighter implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Element shield;

    public HilichurlGuard() {
    }

    public HilichurlGuard(int level, Element club, Element shield, String imagePath) throws FileNotFoundException {
        super(level, club, imagePath);
        this.shield = shield;
        this.setName("Guard");
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
    @Override
    public void accept(Visitor visitor){
        visitor.visitHilichurlGuard(this);
    }
}
