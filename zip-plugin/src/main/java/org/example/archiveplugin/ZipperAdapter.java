package org.example.archiveplugin;

import com.example.lapa12.PluginImplementation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

import static com.example.lapa12.Main.controls;
import static com.example.lapa12.Main.mSettings;

public class ZipperAdapter implements PluginImplementation {
    private final Zipper zipper = new Zipper();
    File openFile;

    @Override
    public void doSmth() {
        System.out.println("archiving");
        addNewInterface();
    }

    public void addNewInterface() {
        if (mSettings == null) {
            mSettings = new Menu("Settings");
            MenuBar mbSettings = new MenuBar(mSettings);
            controls.getChildren().add(mbSettings);
        }
        MenuItem miArchiving = new MenuItem("Archiving");
        mSettings.getItems().add(miArchiving);

        miArchiving.setOnAction(event -> {
            createArchivingWindow();
        });
    }

    public void createArchivingWindow() {
        Stage archivingStage = new Stage();
        FileChooser fcChooseFile = new FileChooser();

        Button btnZipFile = new Button("Zip file");
        Button btnUnzipFile = new Button("Unzip file");

        btnZipFile.setOnAction(event -> {
            openFile = fcChooseFile.showOpenDialog(archivingStage);
            zipper.zip(openFile.getAbsolutePath());
        });

        btnUnzipFile.setOnAction(event -> {
            openFile = fcChooseFile.showOpenDialog(archivingStage);
            zipper.unzip(openFile.getAbsolutePath());
        });

        VBox root = new VBox(10, btnZipFile, btnUnzipFile);
        root.setAlignment(Pos.CENTER);

        Scene optionsScene = new Scene(root, 100, 100);
        archivingStage.setScene(optionsScene);
        archivingStage.setTitle("Archiving");
        archivingStage.show();
    }
}
