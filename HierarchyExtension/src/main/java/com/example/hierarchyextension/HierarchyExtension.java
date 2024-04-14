package com.example.hierarchyextension;

import com.example.hierarchyextension.factories.Factory;
import com.example.hierarchyextension.landscapes.Landscape;
import com.example.hierarchyextension.landscapes.Landscapes;
import com.example.lapa12.PluginImplementation;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import static com.example.lapa12.Main.*;

public class HierarchyExtension implements PluginImplementation {
    public static void main(String[] args) {
        System.out.println("Plugin was built");
    }
    public static Group heroes;
    public static Landscapes landscapes;
    Factory[] factories;
    public static ArrayList<String> typeNames = new ArrayList<>(List.of(new String[]{"Bonfire", "Bush"}));
    public static ArrayList<String>  imagePaths = new ArrayList<>();
    Logic logic = new Logic();
    @Override
    public void doSmth() {
        System.out.println("extension");
        addNewInterface();
        logic.hitHilichurl();
    }

    public void addNewInterface(){
        logic.loadImages();


        Menu menu = new Menu("Choose landscape");
        MenuItem[] menuItems = new MenuItem[typeNames.size()];
        for (int i = 0; i < typeNames.size(); i++) {
            menuItems[i] = new MenuItem(typeNames.get(i));
        }

        for (MenuItem item : menuItems) {
            menu.getItems().add(item);
        }
        MenuBar menuBar = new MenuBar(menu);
        controls.getChildren().add(menuBar);

        factories = new Factory[2];
        landscapes = new Landscapes();

        logic.createLandscapesFactories(factories);

        heroes = new Group();
        root.getChildren().add(heroes);

        EventHandler<ActionEvent> event = e -> scene.setOnMouseClicked(mouseEvent -> {
            Landscape landscape;
            int index = 0;
            if (e.getSource() instanceof MenuItem) {
                index = menu.getItems().indexOf((MenuItem) e.getSource());
            }

            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

            int i = isShowData(heroes, x,y);
            if (i<0) {
                Landscape currentLandscape;
                try {
                    currentLandscape = factories[index].create(imagePaths.get(index));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                currentLandscape.setX(x);
                currentLandscape.setY(y);

                try {
                    landscapes.landscapes.add(currentLandscape);

                    Node hero = new ImageView(currentLandscape.getImage());
                    heroes.getChildren().add(hero);

                    double cx = hero.getBoundsInLocal().getWidth()/2;
                    double cy = hero.getBoundsInLocal().getHeight()/2;

                    hero.relocate(x-cx, y-cy);

                } catch (Exception exception) {
                    System.out.println("низя так");
                }
            } else {
                landscape = landscapes.landscapes.get(i);
                createNewWindow(heroes, landscape);
            }
        });

        for (MenuItem menuItem : menuItems) {
            menuItem.setOnAction(event);
        }
    }
    private int isShowData(Group heroes, double x, double y){
        for (int i = 0; i < landscapes.landscapes.size(); i++) {
            double rightBound = heroes.getChildren().get(i).getBoundsInParent().getMaxX();
            double leftBound = heroes.getChildren().get(i).getBoundsInParent().getMinX();
            double bottomBound = heroes.getChildren().get(i).getBoundsInParent().getMaxY();
            double topBound = heroes.getChildren().get(i).getBoundsInParent().getMinY();

            if (x >= leftBound &&
                    x <= rightBound &&
                    y >= topBound &&
                    y <= bottomBound) {
                System.out.println("нажал на картинку");
                return i;
            }
        }
        return -1;
    }
    private void createNewWindow(Group group, Landscape landscape) {
        Stage optionsStage = new Stage();

        Label lXP = new Label("XP");
        TextField tfXP = new TextField(String.valueOf(landscape.getXP()));
        HBox hbXP = new HBox(5, lXP,tfXP);

        Button btnDelete = new Button("Delete");
        HBox hbButtons = new HBox(btnDelete);
        hbButtons.setAlignment(Pos.BOTTOM_CENTER);
        VBox root = new VBox(20, hbXP, hbButtons);

        btnDelete.setOnAction(actionEvent -> {
                    group.getChildren().remove(landscapes.landscapes.indexOf(landscape));
                    landscapes.landscapes.remove(landscape);
                    optionsStage.close();
                }
        );

        Scene optionsScene = new Scene(root, 200, 100);
        optionsStage.setScene(optionsScene);
        optionsStage.setTitle("Options");
        optionsStage.show();
    }
}
