package com.example.lapa12;

import com.example.lapa12.factories.Factory;
import com.example.lapa12.heros.*;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Main extends Application {
    public static final int W = 700;
    public static final int H = 700;

    Hilichurls hilichurls;

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
        hilichurls = new Hilichurls();

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
            Hilichurl hilichurl = new Hilichurl();
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
                    hilichurl = hilichurls.hilichurls.get(i);

                    System.out.println("нажал на картинку");
                    break;
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
                createNewWindow(root,hilichurl);
            }
            });
        for (MenuItem menuItem : menuItems) {
            menuItem.setOnAction(event);
        }
    }
    private void createNewWindow(Group group, Hilichurl hilichurl){
        Stage optionsStage = new Stage();

        Label lLevel = new Label("Level");
        TextField tfLevel = new TextField(String.valueOf(hilichurl.getLevel()));
        HBox hbLevel = new HBox(5,lLevel,tfLevel);
        VBox root = new VBox(20, hbLevel);

        ComboBox<Element> cbClub = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbShield = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbAxe = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbShell = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbSlime = new ComboBox<>(FXCollections.observableArrayList(Element.values()));
        ComboBox<Element> cbCrossbow = new ComboBox<>(FXCollections.observableArrayList(Element.values()));


        if (hilichurl.getName().equals("Fighter") || hilichurl.getName().equals("Guard")){
            Label lClub = new Label("Club");
            cbClub.setValue(((HilichurlFighter) hilichurl).getClub());
            HBox hbClub = new HBox(5,lClub,cbClub);
            root.getChildren().add(hbClub);

            if (hilichurl.getName().equals("Shield")){
                Label lShield = new Label("Shield");
                cbShield.setValue(((HilichurlGuard) hilichurl).getShield());
                HBox hbShield = new HBox(5,lShield,cbShield);
                root.getChildren().add(hbShield);
            }
        }

        if (hilichurl.getName().equals("Lawachurl") || hilichurl.getName().equals("Mitachurl")){
            Label lAxe = new Label("Axe");
            cbAxe.setValue(((Mitachurl) hilichurl).getAxe());
            HBox hbAxe = new HBox(5,lAxe,cbAxe);
            root.getChildren().add(hbAxe);

            Label lShield = new Label("Shield");
            cbShield.setValue(((Mitachurl) hilichurl).getShield());
            HBox hbShield = new HBox(5,lShield,cbShield);
            root.getChildren().add(hbShield);

            if (hilichurl.getName().equals("Lawachurl")){
                Label lShell = new Label("Shell");
                cbShell.setValue(((Lawachurl) hilichurl).getShell());
                HBox hbShell = new HBox(5,lShell,cbShell);
                root.getChildren().add(hbShell);
            }
        }

        if (hilichurl.getName().equals("Grenadier")){
            Label lSlime = new Label("Slime");
            cbSlime.setValue(((HilichurlGrenadier) hilichurl).getSlime());
            HBox hbSlime = new HBox(5,lSlime,cbSlime);
            root.getChildren().add(hbSlime);
        }

        if (hilichurl.getName().equals("Shooter")){
            Label lCrossbow = new Label("Crossbow");
            cbCrossbow.setValue(((HilichurlShooter) hilichurl).getCrossbow());
            HBox hbCrossbow = new HBox(5,lCrossbow,cbCrossbow);
            root.getChildren().add(hbCrossbow);
        }

        Button btnChange = new Button("Change");
        Button btnDelete = new Button("Delete");
        HBox hbButtons=new HBox(30,btnChange, btnDelete);
        hbButtons.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(hbButtons);

        btnDelete.setOnAction(actionEvent -> {
            group.getChildren().remove(hilichurls.hilichurls.indexOf(hilichurl)+1);
            hilichurls.hilichurls.remove(hilichurl);
            optionsStage.close();
            }
        );

        btnChange.setOnAction(actionEvent -> {
            hilichurl.setLevel(Integer.parseInt(tfLevel.getText()));
            if (hilichurl.getName().equals("Fighter") || hilichurl.getName().equals("Guard")) {
                ((HilichurlFighter) hilichurl).setClub(cbClub.getValue());
                if (hilichurl.getName().equals("Shield")) {
                    ((HilichurlGuard) hilichurl).setShield(cbShield.getValue());
                }
            }
            if (hilichurl.getName().equals("Lawachurl") || hilichurl.getName().equals("Mitachurl")) {
                ((Mitachurl) hilichurl).setAxe(cbAxe.getValue());
                ((Mitachurl) hilichurl).setShield(cbShield.getValue());
                if (hilichurl.getName().equals("Lawachurl")) {
                    ((Lawachurl) hilichurl).setShell(cbShell.getValue());
                }
            }
            if (hilichurl.getName().equals("Grenadier")) {
                ((HilichurlGrenadier) hilichurl).setSlime(cbSlime.getValue());
            }
            if (hilichurl.getName().equals("Shooter")) {
                ((HilichurlShooter) hilichurl).setCrossbow(cbCrossbow.getValue());
            }
            optionsStage.close();
        });

        Scene optionsScene = new Scene(root, 200, 230);
        optionsStage.setScene(optionsScene);
        optionsStage.setTitle("Options");
        optionsStage.show();
    }
}
