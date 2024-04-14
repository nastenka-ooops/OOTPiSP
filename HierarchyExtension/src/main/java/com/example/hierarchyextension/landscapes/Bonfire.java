package com.example.hierarchyextension.landscapes;

import com.example.lapa12.Element;
import com.example.lapa12.Main;
import com.example.lapa12.heros.Hilichurl;

import java.io.FileNotFoundException;

import static com.example.hierarchyextension.HierarchyExtension.heroes;
import static com.example.hierarchyextension.HierarchyExtension.landscapes;

public class Bonfire extends Landscape{
    private final Element element=Element.FIRE;
    public Bonfire(String imagePath) throws FileNotFoundException {
        super(imagePath);
        this.setName("Bonfire");
    }

    @Override
    public void hitHilicurl() {
        int i = landscapes.landscapes.indexOf(this);

        double rightBound = heroes.getChildren().get(i).getBoundsInParent().getMaxX();
        double leftBound = heroes.getChildren().get(i).getBoundsInParent().getMinX();
        double bottomBound = heroes.getChildren().get(i).getBoundsInParent().getMaxY();
        double topBound = heroes.getChildren().get(i).getBoundsInParent().getMinY();

        for (Hilichurl hilichurl :
                Main.hilichurls.hilichurls  ) {
            double hilichurlX = hilichurl.getX();
            double hilichurlY = hilichurl.getY();

            if (hilichurlX >= leftBound-100 &&
                    hilichurlX <= rightBound+100 &&
                    hilichurlY >= topBound-100 &&
                    hilichurlY <= bottomBound+100) {
                System.out.println("ударило костром");
                hilichurl.setXP(hilichurl.getXP()-20);
            }
        }
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        return "Bonfire{" +
                "element=" + element +
                "} " + super.toString();
    }
}
