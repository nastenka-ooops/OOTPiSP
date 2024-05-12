package com.example.lapa12.heros;

import com.example.lapa12.Element;
import com.example.lapa12.visitor.Visitor;

import java.io.FileNotFoundException;
import java.io.Serial;
import java.io.Serializable;

public class Lawachurl extends Mitachurl implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private Element shell;

    public Lawachurl() {
    }

    public Lawachurl(int level, Element axe, Element shield, Element shell, String imagePath) throws FileNotFoundException {
        super(level, axe, shield, imagePath);
        this.shell = shell;
        this.setName("Lawachurl");
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
    @Override
    public void accept(Visitor visitor){
        visitor.visitLawachurl(this);
    }
}
