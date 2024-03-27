package com.example.lapa12;

import com.example.lapa12.factories.Factory;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.Hilichurls;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
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
        Hilichurls hilichurls = new Hilichurls();

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
                    logic.JSONSerialize(hilichurls);
                } else {
                    logic.BINSerialize(hilichurls.hilichurls);
                }
                scene.getRoot().requestFocus();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });


        btnDeserialize.setOnAction(actionEvent -> {
            try {
                if (rbJSON.isSelected()) {
                    hilichurls.hilichurls.addAll(logic.JSONDeserialize().hilichurls);
                } else {
                    hilichurls.hilichurls.addAll(logic.BINDeserialize());
                }
                for (Hilichurl character :
                        hilichurls.hilichurls) {
                    movement.keyPress(scene,root, character, character.getX(), character.getY());
                    movement.stopTimer();
                }
                scene.getRoot().requestFocus();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });

        EventHandler<ActionEvent> event = e -> scene.setOnMouseClicked(mouseEvent -> {
            boolean isShowData = false;
            movement.stopTimer();
            int index=menu.getItems().indexOf((MenuItem)e.getSource());

            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

            for (int i = 0; i < hilichurls.hilichurls.size(); i++) {
                double rightBound = root.getChildren().get(i+1).getBoundsInParent().getMaxX();
                double leftBound = root.getChildren().get(i+1).getBoundsInParent().getMinX();
                double bottomBound = root.getChildren().get(i+1).getBoundsInParent().getMaxY();
                double topBound = root.getChildren().get(i+1).getBoundsInParent().getMinY();

                if (x>=leftBound &&
                        x<=rightBound &&
                        y>=topBound &&
                        y<=bottomBound){
                    isShowData = true;
                    System.out.println("нажал на картинку");
                }
            }

            if (!isShowData) {
                Hilichurl currentCharacter;
                try {
                    currentCharacter = factories[index].create();
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                currentCharacter.setX(x);
                currentCharacter.setY(y);

                try {
                    hilichurls.hilichurls.add(currentCharacter);
                    movement.keyPress(scene, root, currentCharacter, currentCharacter.getX(), currentCharacter.getY());
                } catch (Exception exception) {
                    System.out.println("низя так");
                }
            }else{

            }
            });
        for (MenuItem menuItem : menuItems) {
            menuItem.setOnAction(event);
        }
    }
}
