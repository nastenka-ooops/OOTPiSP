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

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.module.Configuration;
import java.lang.module.ModuleDescriptor;
import java.lang.module.ModuleFinder;
import java.lang.module.ModuleReference;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class Main extends Application {
    public static final int W = 700;
    public static final int H = 700;

    public static Hilichurls hilichurls;
    public static ArrayList<Factory> factories;

    public static ArrayList<String> typeNames = new ArrayList<>(List.of(new String[]{
            "Hilichurl", "Mitachurl", "Grenadier", "Shooter", "Fighter", "Lawachurl",
            "Guard"}));
    public static ArrayList<String> imagePaths = new ArrayList<>();
    public static HBox controls;
    public static Group root;
    public static Scene scene;
    public static Group heroes;
    public static ArrayList<MenuItem> menuItems;
    public static Menu mChooseCharacter;
    public static Menu mSettings;
    public static int offset = 0;
    public static boolean isPluginLoad = false;
    public static Logic logic = new Logic();
    public static Movement movement = new Movement();

    public static void main(String[] args) {
        launch(args);
    }

    public MenuBar createChooseCharacterMenu() {
        mChooseCharacter = new Menu("Choose character");
        menuItems = new ArrayList<>();
        for (String typeName : typeNames) {
            menuItems.add(new MenuItem(typeName));
        }

        for (MenuItem item : menuItems) {
            mChooseCharacter.getItems().add(item);
        }
        return new MenuBar(mChooseCharacter);
    }

    @Override
    public void start(Stage stage) throws IOException {

        factories = new ArrayList<>();
        hilichurls = new Hilichurls();

        logic.createHilichurlsFactories(factories);
        logic.loadImages();

        Button btnSerialize = new Button("Serialize");
        Button btnDeserialize = new Button("Deserialize");
        RadioButton rbJSON = new RadioButton("JSON");

        controls = new HBox(10, createChooseCharacterMenu());
        heroes = new Group();
        root = new Group(controls, heroes);
        scene = new Scene(root, W, H);

        scene.getRoot().requestFocus();
        stage.setTitle("lapa 1,2,3,4,5");
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
                index = mChooseCharacter.getItems().indexOf((MenuItem) e.getSource());
            }

            double x = mouseEvent.getX();
            double y = mouseEvent.getY();

            int i = isShowData(heroes, x, y);
            if (i < 0) {
                Hilichurl currentCharacter;
                try {
                    currentCharacter = factories.get(index).create(imagePaths
                            .get(index + offset));
                } catch (FileNotFoundException ex) {
                    throw new RuntimeException(ex);
                }

                currentCharacter.setX(x);
                currentCharacter.setY(y);

                try {
                    hilichurls.hilichurls.add(currentCharacter);
                    movement.keyPress(scene, heroes, currentCharacter, currentCharacter
                            .getX(), currentCharacter.getY());
                } catch (Exception exception) {
                    System.out.println("низя так");
                }
            } else {
                hilichurl = hilichurls.hilichurls.get(i);
                createNewWindow(heroes, hilichurl);
            }
        });

        loadPlugins();
        if (!isPluginLoad){
            controls.getChildren().addAll(rbJSON, btnSerialize, btnDeserialize);
        }

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
                    movement.keyPress(scene, heroes, character, character.getX(),
                            character.getY());
                    movement.stopTimer();
                }
                scene.getRoot().requestFocus();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            event.handle(actionEvent);
        });

    }

    private int isShowData(Group heroes, double x, double y) {
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

    public boolean checkPlugins(String jarFilePath){
        try (JarFile jarFile = new JarFile(jarFilePath, true)) {
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();

                if (entry.isDirectory()) {
                    continue;
                }

                // Чтение файла, чтобы активировать подпись
                jarFile.getInputStream(entry).readAllBytes();

                Certificate[] certs = entry.getCertificates();

                if (certs != null && certs.length > 0) {
                    return true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading JAR file: " + e.getMessage());
        }

        return false;
        }

    public void loadPlugins(){
        Path pluginsDir = Paths.get("plugins");
        File directory = new File(String.valueOf(pluginsDir.toFile()));

        ArrayList<String> jarFiles = new ArrayList<>();
        if (directory.isDirectory()) {
            // Используем FilenameFilter для получения только файлов с расширением .jar
            FilenameFilter jarFilter = (dir, name) -> name.toLowerCase().endsWith(".jar");
            jarFiles.addAll(List.of(directory.list(jarFilter)));
        }
        for (String jarPath :
                jarFiles) {
            boolean isJarFileVerified = checkPlugins("plugins/"+jarPath);
            if (isJarFileVerified) {
                System.out.println(jarPath + " is signed");
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("plugin "+ jarPath +" is not verified");
                alert.showAndWait();
            }
        }

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
                .defineModulesWithOneLoader(pluginsConfiguration, ClassLoader
                        .getSystemClassLoader());

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
        VBox root = factories.get(index).createWindow(hilichurl);

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
                hilichurl.setLevel(Integer.parseInt(((TextField) controls.get(1))
                        .getText()));
            } catch (NumberFormatException e) {
                System.out.println("Invalid level");
            }
            if (hilichurl instanceof HilichurlFighter) {
                ((HilichurlFighter) hilichurl).setClub((Element) ((ComboBox<?>) controls
                        .get(5)).getValue());
                if (hilichurl instanceof HilichurlGuard) {
                    ((HilichurlGuard) hilichurl).setShield((Element) ((ComboBox<?>)
                            controls.get(7)).getValue());
                }
            }
            if (hilichurl instanceof Mitachurl) {
                ((Mitachurl) hilichurl).setAxe((Element) ((ComboBox<?>) controls.get(5))
                        .getValue());
                ((Mitachurl) hilichurl).setShield((Element) ((ComboBox<?>) controls
                        .get(7)).getValue());
                if (hilichurl instanceof Lawachurl) {
                    ((Lawachurl) hilichurl).setShell((Element) ((ComboBox<?>) controls
                            .get(9)).getValue());
                }
            }
            if (hilichurl instanceof HilichurlGrenadier) {
                ((HilichurlGrenadier) hilichurl).setSlime((Element) ((ComboBox<?>)
                        controls.get(5)).getValue());
            }
            if (hilichurl instanceof HilichurlShooter) {
                ((HilichurlShooter) hilichurl).setCrossbow((Element) ((ComboBox<?>)
                        controls.get(5)).getValue());
            }

            optionsStage.close();
        });

        Scene optionsScene = new Scene(root, 200, 260);
        optionsStage.setScene(optionsScene);
        optionsStage.setTitle("Options");
        optionsStage.show();
    }
}