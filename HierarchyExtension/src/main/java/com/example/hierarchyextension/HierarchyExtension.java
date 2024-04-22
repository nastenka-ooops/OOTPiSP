package com.example.hierarchyextension;

import com.example.lapa12.PluginImplementation;
import javafx.scene.control.MenuItem;

import java.util.ArrayList;
import java.util.List;

import static com.example.lapa12.Main.menu;
import static com.example.lapa12.Main.menuItems;

public class HierarchyExtension implements PluginImplementation {
    public static void main(String[] args) {
        System.out.println("Plugin was built");
    }
    com.example.lapa12.factories.Factory[] hilichurlFactories;
    public static ArrayList<String> hilichurlNames = new ArrayList<>(List.of(new String[]{"Shamachurl", "Abyss Mage"}));
    public static ArrayList<String>  imagePaths = new ArrayList<>();
    Logic logic = new Logic();
    @Override
    public void doSmth() {
        System.out.println("extension");
        extendHierarchy();
    }


    public void extendHierarchy(){
        logic.loadHilichurlImages();
        hilichurlFactories = new com.example.lapa12.factories.Factory[2];
        for (String typeName : hilichurlNames) {
            menuItems.add(new MenuItem(typeName));
        }
        for (int i = menuItems.size()-hilichurlNames.size(); i < menuItems.size(); i++) {
            MenuItem item = menuItems.get(i);
            menu.getItems().add(item);
        }
        logic.createHilichurlFactories();
    }
}
