package com.example.lapa12;

import com.example.lapa12.factories.Factory;
import com.example.lapa12.heros.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    public static final int W = 700;
    public static final int H = 700;

    public static Hilichurls hilichurls;
    Factory[] factories;

    public static ArrayList<String> typeNames = new ArrayList<>(List.of(new String[]{"Hilichurl", "Metachurl", "Grenadier", "Shooter", "Fighter", "Lawachurl", "Guard"}));
    public static ArrayList<String>  imagePaths = new ArrayList<>();
    public static HBox controls;
    public static Group root;
    public static Scene scene;
    public static int offset=8;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Movement movement = new Movement();

        Menu menu = new Menu("Choose character");
        MenuItem[] menuItems = new MenuItem[typeNames.size()];
        for (int i = 0; i < typeNames.size(); i++) {
            menuItems[i] = new MenuItem(typeNames.get(i));
        }

        for (MenuItem item : menuItems) {
            menu.getItems().add(item);
        }

        factories = new Factory[7];
        hilichurls = new Hilichurls();

        Logic logic = new Logic();
        logic.createHilichurlsFactories(factories);

        MenuBar menuBar = new MenuBar(menu);

        Button btnSerialize = new Button("Serialize");
        Button btnDeserialize = new Button("Deserialize");
        RadioButton rbJSON = new RadioButton("JSON");

        controls = new HBox(10, menuBar, rbJSON, btnSerialize, btnDeserialize);
        Group heroes = new Group();
        root = new Group(controls, heroes);
        scene = new Scene(root, W, H);

        scene.getRoot().requestFocus();
        stage.setTitle("lapa 1,2,3,4");
        stage.setScene(scene);
        stage.show();

        rbJSON.setOnAction(actionEvent -> scene.getRoot().requestFocus());

        btnSerialize.setOnAction(actionEvent -> {
            try {
                if (rbJSON.isSelected()) {
                    logic.JSONSerialize(hilichurls);
                } else {
                    logic.BINSerialize(hilichurls.hilichurls);
                }
                scene.getRoot().requestFocus();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        EventHandler<ActionEvent> event = e -> scene.setOnMouseClicked(mouseEvent -> {
            Hilichurl hilichurl;
            movement.stopTimer();
            int index = 0;
            if (e.getSource() instanceof MenuItem) {
                index = menu.getItems().indexOf((MenuItem) e.getSource());
            }

            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

            int i = isShowData(heroes, x, y);
            if (i<0) {
                Hilichurl currentCharacter;
                try {
                    currentCharacter = factories[index].create(imagePaths.get(index+offset));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                currentCharacter.setX(x);
                currentCharacter.setY(y);

                try {
                    hilichurls.hilichurls.add(currentCharacter);
                    movement.keyPress(scene, heroes, currentCharacter, currentCharacter.getX(), currentCharacter.getY());
                } catch (Exception exception) {
                    System.out.println("низя так");
                }
            } else {
                hilichurl = hilichurls.hilichurls.get(i);
                createNewWindow(heroes, hilichurl);
            }
        });

        for (MenuItem menuItem : menuItems) {
            menuItem.setOnAction(event);
        }

        btnDeserialize.setOnAction(actionEvent -> {
            try {
                if (rbJSON.isSelected()) {
                    hilichurls.hilichurls.addAll(logic.JSONDeserialize().hilichurls);
                } else {
                    hilichurls.hilichurls.addAll(logic.BINDeserialize());
                }
                for (Hilichurl character :
                        hilichurls.hilichurls) {
                    movement.keyPress(scene, heroes, character, character.getX(), character.getY());
                    movement.stopTimer();
                }
                System.out.println(heroes.getChildren());
                scene.getRoot().requestFocus();
            } catch (IOException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
            event.handle(actionEvent);
        });
        loadPlugins();
    }

    private int isShowData(Group heroes, double x, double y){
        for (int i = 0; i < hilichurls.hilichurls.size(); i++) {
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

    public static void loadPlugins(){
        Path pluginsDir = Paths.get("plugins");

        // Будем искать плагины в папке plugins
        ModuleFinder pluginsFinder = ModuleFinder.of(pluginsDir);

        List<String> plugins = pluginsFinder
                .findAll()
                .stream()
                .map(ModuleReference::descriptor)
                .map(ModuleDescriptor::name)
                .toList();
        // Создадим конфигурацию, которая выполнит резолюцию указанных модулей (проверит корректность графа зависимостей)
        Configuration pluginsConfiguration = ModuleLayer
                .boot()
                .configuration()
                .resolve(pluginsFinder, ModuleFinder.of(), plugins);

        // Создадим слой модулей для плагинов
        ModuleLayer layer = ModuleLayer
                .boot()
                .defineModulesWithOneLoader(pluginsConfiguration, ClassLoader.getSystemClassLoader());

        // Найдём все реализации сервиса IService в слое плагинов и в слое Boot
        List<PluginImplementation> services = PluginImplementation.getServices(layer);
        for (PluginImplementation service : services) {
            service.doSmth();
        }
    }

    private void createNewWindow(Group group, Hilichurl hilichurl) {
        Stage optionsStage = new Stage();
        int index = 0;
        for (int i = 0; i < typeNames.size(); i++) {
            if (hilichurl.getName().equals(typeNames.get(i))) {
                index = i;
                break;
            }
        }
        VBox root = factories[index].createWindow(hilichurl);

        Button btnChange = new Button("Change");
        Button btnDelete = new Button("Delete");
        HBox hbButtons = new HBox(30, btnChange, btnDelete);
        hbButtons.setAlignment(Pos.BOTTOM_CENTER);
        root.getChildren().addAll(hbButtons);

        btnDelete.setOnAction(actionEvent -> {
                    group.getChildren().remove(hilichurls.hilichurls.indexOf(hilichurl));
                    hilichurls.hilichurls.remove(hilichurl);
                    optionsStage.close();
                }
        );


        btnChange.setOnAction(actionEvent -> {
            ArrayList<Node> controls = new ArrayList<>();
            for (Node node : root.getChildren()) {
                if (node instanceof HBox) {
                    controls.addAll(((HBox) node).getChildren());
                }
            }
            try {
                hilichurl.setLevel(Integer.parseInt(((TextField) controls.get(1)).getText()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid level");
            }
            if (hilichurl instanceof HilichurlFighter) {
                ((HilichurlFighter) hilichurl).setClub((Element) ((ComboBox<?>) controls.get(5)).getValue());
                if (hilichurl instanceof HilichurlGuard) {
                    ((HilichurlGuard) hilichurl).setShield((Element) ((ComboBox<?>) controls.get(7)).getValue());
                }
            }
            if (hilichurl instanceof Mitachurl) {
                ((Mitachurl) hilichurl).setAxe((Element) ((ComboBox<?>) controls.get(5)).getValue());
                ((Mitachurl) hilichurl).setShield((Element) ((ComboBox<?>) controls.get(7)).getValue());
                if (hilichurl instanceof Lawachurl) {
                    ((Lawachurl) hilichurl).setShell((Element) ((ComboBox<?>) controls.get(9)).getValue());
                }
            }
            if (hilichurl instanceof HilichurlGrenadier) {
                ((HilichurlGrenadier) hilichurl).setSlime((Element) ((ComboBox<?>) controls.get(5)).getValue());
            }
            if (hilichurl instanceof HilichurlShooter) {
                ((HilichurlShooter) hilichurl).setCrossbow((Element) ((ComboBox<?>) controls.get(5)).getValue());
            }
            optionsStage.close();
        });

        Scene optionsScene = new Scene(root, 200, 230);
        optionsStage.setScene(optionsScene);
        optionsStage.setTitle("Options");
        optionsStage.show();

    }
}