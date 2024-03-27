package com.example.lapa12;

import com.example.lapa12.factories.Factory;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.Hilichurls;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
    public void start(Stage stage) {

        Movement movement = new Movement();

        Menu menu = new Menu("Choose character");
        MenuItem[] menuItems = new MenuItem[]{new MenuItem("Hilichurl"), new MenuItem("Metachurl"),
                new MenuItem("Grenadier"), new MenuItem("Shooter"),new MenuItem( "Fighter"),
                new MenuItem("Lawachurl"),new MenuItem( "Guard")};


        for (MenuItem item : menuItems) {
            menu.getItems().add(item);
        }

        Factory[] factories = new Factory[7];
        Hilichurls characters = new Hilichurls();

        Logic logic = new Logic();
        logic.createHilichurlsFactories(factories);

        MenuBar menuBar = new MenuBar(menu);

        Button btnSerialize = new Button("Serialize");
        Button btnDeserialize = new Button("Deserialize");
        RadioButton rbJSON = new RadioButton("JSON");

        HBox controls = new HBox(10,menuBar, rbJSON, btnSerialize, btnDeserialize);
        Group root = new Group(controls);
        Scene scene = new Scene(root, W, H);

        scene.getRoot().requestFocus();
        stage.setTitle("lapa1,2");
        stage.setScene(scene);
        stage.show();

        btnSerialize.setOnAction(actionEvent -> {
            try {
                if (rbJSON.isSelected()){
                    logic.JSONSerialize(characters);
                } else {
                    logic.BINSerialize(characters.hilichurls);
                }
                scene.getRoot().requestFocus();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        btnDeserialize.setOnAction(actionEvent -> {
            try {
                if (rbJSON.isSelected()) {
                    characters.hilichurls.addAll(logic.JSONDeserialize().hilichurls);
                } else {
                    characters.hilichurls.addAll(logic.BINDeserialize());
                }
                for (Hilichurl character :
                        characters.hilichurls) {
                    Node currentImage = new ImageView(new Image(new FileInputStream(character.getImagePath()),150,140, true, true));
                    root.getChildren().add(currentImage);
                    movement.keyPress(scene, currentImage, character.getX(), character.getY());
                    movement.stopTimer();
                }
                scene.getRoot().requestFocus();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        EventHandler<ActionEvent> event = e -> scene.setOnMouseClicked(mouseEvent -> {
            movement.stopTimer();
            int index=menu.getItems().indexOf((MenuItem)e.getSource());
            Hilichurl currentCharacter;
            try {
                currentCharacter =  factories[index].create();
            } catch (FileNotFoundException ex) {
                throw new RuntimeException(ex);
            }
                double x = mouseEvent.getX();
                double y = mouseEvent.getY();

                currentCharacter.setX(x);
                currentCharacter.setY(y);

                try{
                    Node currentImage = new ImageView(new Image(new FileInputStream(currentCharacter.getImagePath()),150,140, true, true));
                    root.getChildren().add(currentImage);
                    characters.hilichurls.add(currentCharacter);
                    movement.keyPress(scene,currentImage, currentCharacter.getX(), currentCharacter.getY());
                } catch (Exception exception){
                    System.out.println("низя так");
                }
            });
        for (MenuItem menuItem : menuItems) {
            menuItem.setOnAction(event);
        }
    }
}
