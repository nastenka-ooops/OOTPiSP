package com.example.lapa12;

import com.example.lapa12.factories.Factory;
import com.example.lapa12.heros.Hilichurl;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    public static final int W = 700;
    public static final int H = 700;
    
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {

        Movement movement = new Movement();

        Menu menu = new Menu("Choose character");
        MenuItem[] menuItems = new MenuItem[]{new MenuItem("Hilichurl"), new MenuItem("Metachurl"),
                new MenuItem("Grenadier"), new MenuItem("Shooter"),new MenuItem( "Fighter"),
                new MenuItem("Lawachurl"),new MenuItem( "Guard")};


        for (MenuItem item : menuItems) {
            menu.getItems().add(item);
        }

        Factory[] factories = new Factory[7];
        ArrayList<Hilichurl> characters = new ArrayList<>();

        Logic logic = new Logic();
        logic.createHilichurlsFactories(factories);

        MenuBar menuBar = new MenuBar(menu);

        ArrayList<Image> heroImage = new ArrayList<>();
        heroImage.add(new Image(new FileInputStream("src/main/java/com/example/lapa12/images/empty.jpg"), 1, 1, true, true));
        ArrayList<Node> hero = new ArrayList<>();
        hero.add(new ImageView(heroImage.get(0)));

        Button btnSerialize = new Button("Serialize");
        Button btnDeserialize = new Button("Deserialize");

        HBox controls = new HBox(menuBar,btnSerialize, btnDeserialize);
        Group root = new Group(controls,hero.get(0));
        Scene scene = new Scene(root, W, H);

        stage.setTitle("lapa1,2");
        stage.setScene(scene);
        stage.show();

        btnSerialize.setOnAction(actionEvent -> {
            try {
                logic.serialize(characters);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        btnDeserialize.setOnAction(actionEvent -> {
            try {
                //characters.clear();
                //hero.clear();
                characters.addAll(logic.deserialize());
                //root.getChildren().clear();
                for (Hilichurl character :
                        characters) {
                    heroImage.add(new Image(new FileInputStream(character.getImagePath()),150, 140, true, true));
                    hero.add(new ImageView(heroImage.get(heroImage.size()-1)));
                    root.getChildren().add(hero.get(hero.size()-1));
                    movement.keyPress(scene, hero.get(hero.size()-1), character.getX(), character.getY());
                }
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        EventHandler<ActionEvent> event = e -> {
            int index=menu.getItems().indexOf((MenuItem)e.getSource());
            Hilichurl currentCharacter;
            try {
                currentCharacter =  factories[index].create();
                heroImage.add(currentCharacter.getImage());
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }

            hero.add(new ImageView(heroImage.get(heroImage.size()-1)));

            scene.setOnMouseClicked(mouseEvent -> {
                    double x = mouseEvent.getX();
                    double y = mouseEvent.getY();

                    currentCharacter.setX(x);
                    currentCharacter.setY(y);

                    try{
                        root.getChildren().add(hero.get(hero.size()-1));
                        characters.add(currentCharacter);
                        movement.keyPress(scene, hero.get(hero.size()-1), x, y);
                    } catch (Exception exception){
                        System.out.println("низя так");
                    }
                });
            movement.stopTimer();
        };
        for (MenuItem menuItem : menuItems) {
            menuItem.setOnAction(event);
        }
    }
}
