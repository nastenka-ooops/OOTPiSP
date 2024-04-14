package com.example.hierarchyextension.landscapes;

import com.example.lapa12.Element;
import com.example.lapa12.Main;
import com.example.lapa12.heros.Hilichurl;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static com.example.hierarchyextension.HierarchyExtension.heroes;
import static com.example.hierarchyextension.HierarchyExtension.landscapes;

public class Bush extends Landscape{
    private final Element element=Element.DENDRO;

    public Bush(String imagePath) throws FileNotFoundException {
        super(imagePath);
        this.setName("Bush");
    }

    public Element getElement() {
        return element;
    }

    @Override
    public String toString() {
        return "Bush{" +
                "element=" + element +
                "} " + super.toString();
    }

    @Override
    public void hitHilicurl() {
        int i = landscapes.landscapes.indexOf(this);

        double rightBound = heroes.getChildren().get(i).getBoundsInParent().getMaxX();
        double leftBound = heroes.getChildren().get(i).getBoundsInParent().getMinX();
        double bottomBound = heroes.getChildren().get(i).getBoundsInParent().getMaxY();
        double topBound = heroes.getChildren().get(i).getBoundsInParent().getMinY();

        for (Hilichurl hilichurl :
                new ArrayList<>(Main.hilichurls.hilichurls)) {
            double hilichurlX = hilichurl.getX();
            double hilichurlY = hilichurl.getY();

            if (hilichurlX >= leftBound - 100 &&
                    hilichurlX <= rightBound + 100 &&
                    hilichurlY >= topBound - 100 &&
                    hilichurlY <= bottomBound + 100) {
                System.out.println("ударило кустом");
                hilichurl.setXP(hilichurl.getXP() - 10);
                if (hilichurl.getXP() < 10) {
                    Main.heroes.getChildren().remove(Main.hilichurls.hilichurls.indexOf(hilichurl));
                    Main.hilichurls.hilichurls.remove(hilichurl);
                    System.out.println(hilichurl.getName() + " died because of bush");
                }
            }
        }
    }
}
