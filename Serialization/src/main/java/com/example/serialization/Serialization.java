package com.example.serialization;

import com.example.lapa12.PluginImplementation;
import com.example.lapa12.heros.Hilichurl;
import com.example.lapa12.heros.Hilichurls;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.FileChooser;

import java.io.*;
import java.util.ArrayList;

import static com.example.lapa12.Main.*;


public class Serialization implements PluginImplementation {
    FileChooser fcChooseFile = new FileChooser();
    ObjectMapper mapper = new ObjectMapper();
    XmlMapper xmlMapper = new XmlMapper();


    @Override
    public void doSmth() {
        isPluginLoad = true;
        addNewSerializationInterface();
        addNewDeserializationInterface();
    }
    public void addNewSerializationInterface(){

        Menu mSerialization = new Menu("Serialization");
        MenuBar mbSerialization = new MenuBar(mSerialization);
        controls.getChildren().add(mbSerialization);

        MenuItem miBINSerialization = new MenuItem("BIN");
        mSerialization.getItems().add(miBINSerialization);

        miBINSerialization.setOnAction(event -> {
            try {
                File BINSerializeFile = fcChooseFile.showSaveDialog(scene.getWindow());

                FileOutputStream fileOutputStream = new FileOutputStream(BINSerializeFile);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

                objectOutputStream.writeObject(hilichurls.hilichurls);
                System.out.println("Well done");
                objectOutputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        });

        MenuItem miJSONSerialization = new MenuItem("JSON");
        mSerialization.getItems().add(miJSONSerialization);

        miJSONSerialization.setOnAction(event -> {
            try {
                File JSONSerializeFile = fcChooseFile.showSaveDialog(scene.getWindow());
                mapper.writeValue(JSONSerializeFile, hilichurls);
                System.out.println("Well done");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        MenuItem miXMLSerialization = new MenuItem("XML");
        mSerialization.getItems().add(miXMLSerialization);

        miXMLSerialization.setOnAction(event -> {
            try {
                File XMLSerializeFile = fcChooseFile.showSaveDialog(scene.getWindow());
                xmlMapper.writeValue(XMLSerializeFile, hilichurls);
                System.out.println("Well done");
            }catch (IOException e){
                e.printStackTrace();
            }
        });
    }
    public void addNewDeserializationInterface(){
        Menu mDeserialization = new Menu("Deserialization");
        MenuBar mbDeserialization = new MenuBar(mDeserialization);
        controls.getChildren().add(mbDeserialization);

        MenuItem miBINDeserialization = new MenuItem("BIN");
        mDeserialization.getItems().add(miBINDeserialization);

        miBINDeserialization.setOnAction(event -> {
            ArrayList<Hilichurl> characters = new ArrayList<>();
            try {
                File BINSerializeFile = fcChooseFile.showOpenDialog(scene.getWindow());
                FileInputStream fileInputStream = new FileInputStream(BINSerializeFile);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                characters = (ArrayList<Hilichurl>) objectInputStream.readObject();

                objectInputStream.close();

                hilichurls.hilichurls.addAll(characters);

                for (Hilichurl character :
                        hilichurls.hilichurls) {
                    movement.keyPress(scene, heroes, character, character.getX(),
                            character.getY());
                    movement.stopTimer();
                    scene.getRoot().requestFocus();
                }
                System.out.println("Well done");
            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
            }

        });

        MenuItem miJSONDeserialization = new MenuItem("JSON");
        mDeserialization.getItems().add(miJSONDeserialization);

        miJSONDeserialization.setOnAction(event -> {
            Hilichurls characters;
            try {
                File JSONSerializeFile = fcChooseFile.showOpenDialog(scene.getWindow());
                characters = mapper.readValue(JSONSerializeFile, Hilichurls.class);
                System.out.println("Well done");

                hilichurls.hilichurls.addAll(characters.hilichurls);

                for (Hilichurl character :
                        hilichurls.hilichurls) {
                    movement.keyPress(scene, heroes, character, character.getX(),
                            character.getY());
                    movement.stopTimer();
                    scene.getRoot().requestFocus();
                }
            } catch (IOException e){
                System.out.println("class that you add from another module not found");
            }
        });

        MenuItem miXMLDeserialization = new MenuItem("XML");
        mDeserialization.getItems().add(miXMLDeserialization);

        miXMLDeserialization.setOnAction(event -> {
            Hilichurls characters;
            try {
                File XMLSerializeFile = fcChooseFile.showOpenDialog(scene.getWindow());
                characters = xmlMapper.readValue(XMLSerializeFile, Hilichurls.class);
                System.out.println("Well done");

                hilichurls.hilichurls.addAll(characters.hilichurls);

                for (Hilichurl character :
                        hilichurls.hilichurls) {
                    movement.keyPress(scene, heroes, character, character.getX(),
                            character.getY());
                    movement.stopTimer();
                    scene.getRoot().requestFocus();
                }
            } catch (IOException e){
                System.out.println("class that you add from another module not found");
            }
        });
    }
}
