package com.example.transformingxmlintojson;

import com.example.lapa12.PluginImplementation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.JSONObject;
import org.json.XML;

import java.io.*;

import static com.example.lapa12.Main.controls;
import static com.example.lapa12.Main.mSettings;

public class TransformingXMLintoJSON implements PluginImplementation {
    File openFile;
    File transformedFile;

    String xmlString;
    String input="";
    String output="";
    String jsonString;

    @Override
    public void doSmth() {
        System.out.println("transforming");
        addNewInterface();
    }
    public void addNewInterface(){
        if(mSettings==null){
            mSettings = new Menu("Settings");
            MenuBar mbSettings = new MenuBar(mSettings);
            controls.getChildren().add(mbSettings);
        }
        MenuItem miEncryption = new MenuItem("Transforming XML into JSON");
        mSettings.getItems().add(miEncryption);

        miEncryption.setOnAction(event -> {
            createTransformingWindow();
        });
    }
    public void createTransformingWindow(){
        Stage transformingStage = new Stage();
        FileChooser fcChooseFile = new FileChooser();

        Button btnSaveFile = new Button("Save file");

        Button btnOpenFile = new Button("Choose file");
        Button btnTransformFile = new Button("Transform file");
        VBox vbButtons = new VBox(5, btnOpenFile, btnTransformFile);

        RadioButton rbFromXMLintoJSON = new RadioButton("from XML into JSON");
        rbFromXMLintoJSON.setSelected(true);
        RadioButton rbFromJSONintoXML = new RadioButton("from JSON into XML");
        VBox vbRadioButtons = new VBox(5, rbFromXMLintoJSON, rbFromJSONintoXML);

        HBox hbControls = new HBox(10, vbButtons, vbRadioButtons);

        Label lOpenFile = new Label("Open file: ");
        TextArea taOpenFile = new TextArea();
        taOpenFile.setWrapText(true);
        HBox hbOpenFile = new HBox(10, lOpenFile, taOpenFile);

        Label lTransformedFile = new Label("Transformed file: ");
        TextArea taTransformedFile = new TextArea();
        taTransformedFile.setWrapText(true);
        HBox hbTransformedFile = new HBox(10, lTransformedFile, taTransformedFile);

        rbFromJSONintoXML.setOnAction(event -> {
            if (rbFromXMLintoJSON.isSelected()){
                rbFromXMLintoJSON.setSelected(false);
            }
            if (!rbFromJSONintoXML.isSelected()){
                rbFromJSONintoXML.setSelected(true);
            }
        });

        rbFromXMLintoJSON.setOnAction(event -> {
            if (rbFromJSONintoXML.isSelected()){
                rbFromJSONintoXML.setSelected(false);
            }
            if (!rbFromXMLintoJSON.isSelected()){
                rbFromXMLintoJSON.setSelected(true);
            }
        });
        btnOpenFile.setOnAction(event -> {
            try {
                openFile = fcChooseFile.showOpenDialog(transformingStage);
                BufferedReader bufferedReader= new BufferedReader(new FileReader(openFile));

                String line = bufferedReader.readLine();
                while (line!=null){
                    input+=line;
                    line = bufferedReader.readLine();
                }
                taOpenFile.setText(input);
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnTransformFile.setOnAction(event -> {
            if (rbFromXMLintoJSON.isSelected()){
                xmlString=input;
                JSONObject jsonObject = XML.toJSONObject(xmlString);
                jsonString = jsonObject.toString();
                taTransformedFile.setText(jsonString);
            } else {
                try {
                    jsonString = input;
                    ObjectMapper objectMapper = new ObjectMapper();
                    JsonNode jsonNode = objectMapper.readTree(jsonString);
                    xmlString=new XmlMapper().writeValueAsString(jsonNode);
                    taTransformedFile.setText(xmlString);
                } catch (JsonProcessingException e) {
                    System.out.println("wrong data format");
                }
            }
            output=taTransformedFile.getText();
        });

        btnSaveFile.setOnAction(event -> {
            try {
                transformedFile = fcChooseFile.showSaveDialog(transformingStage);
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(transformedFile));

                bufferedWriter.write(output);

                bufferedWriter.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        });

        VBox root = new VBox(10, hbControls, hbOpenFile, hbTransformedFile, btnSaveFile);

        Scene optionsScene = new Scene(root, 600, 500);
        transformingStage.setScene(optionsScene);
        transformingStage.setTitle("Transforming");
        transformingStage.show();
    }
}
