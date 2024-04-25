package com.example.encryption;

import com.example.lapa12.PluginImplementation;
import com.example.lapa12.heros.Hilichurl;
import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import static com.example.lapa12.Main.*;

public class Encryption implements PluginImplementation {
    int p;
    int k;
    int x;
    int y;
    int g;
    byte[] byteInputText;
    int[] intInputText;
    ArrayList<Integer> encryptText = new ArrayList<>();
    byte[] byteDecryptText;
    int[] intDecryptText;
    Random random = new Random();
    Logic logic = new Logic();
    File encryptFile = new File("../Encryption/encryptFile.txt");
    @Override
    public void doSmth() {
        System.out.println("encryption");
        addNewInterface();
    }
    public void addNewInterface(){
        if(mSettings==null){
            mSettings = new Menu("Settings");
            MenuBar mbSettings = new MenuBar(mSettings);
            controls.getChildren().add(mbSettings);
        }
        MenuItem miEncryption = new MenuItem("Encryption");
        mSettings.getItems().add(miEncryption);

        miEncryption.setOnAction(event -> {
            createEncryptionWindow();
        });
    }
    public void createEncryptionWindow(){
        Stage encryptionStage = new Stage();
        FileChooser fcChooseFile = new FileChooser();

        Label lP = new Label("  p: ");
        TextField tfP = new TextField();
        HBox hbP = new HBox(5, lP, tfP);

        Label lX = new Label("  x: ");
        TextField tfX = new TextField();
        HBox hbX = new HBox(5, lX, tfX);

        Label lK = new Label("  k: ");
        TextField tfK = new TextField();
        HBox hbK = new HBox(5, lK, tfK);


        Button btnCalculatePublicKey = new Button("Calculate public key");

        Button btnEnterPrivateKey = new Button("Enter private key");
        btnEnterPrivateKey.setDisable(true);

        Button btnEnterSecretNumber = new Button("Enter secret number");
        btnEnterSecretNumber.setDisable(true);

        Button btnEncrypt = new Button("Encrypt");
        btnEncrypt.setDisable(true);

        Button btnDecrypt = new Button("Decrypt");
        btnDecrypt.setDisable(true);

        HBox buttons = new HBox(10, btnEncrypt, btnDecrypt);
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        Label lPrimitiveRoots = new Label("  primitive roots: ");
        ComboBox<Integer> cbPrimitiveRoots = new ComboBox<>();
        cbPrimitiveRoots.setDisable(true);
        HBox hbPrimitiveRoots = new HBox(5, lPrimitiveRoots, cbPrimitiveRoots);

        btnCalculatePublicKey.setOnAction(event -> {
            int tempP;
            try {
                tempP = Integer.parseInt(tfP.getText());
                if (logic.isPrimeNumber(tempP)) {
                    p = tempP;
                    cbPrimitiveRoots.setItems(FXCollections.observableList(logic.findAllPrimitiveRoots(p, logic.findAllPrimeDivisors(p - 1))));

                    cbPrimitiveRoots.setValue(cbPrimitiveRoots.getItems().get(0));
                    cbPrimitiveRoots.setDisable(false);
                    btnEnterPrivateKey.setDisable(false);
                    btnEnterSecretNumber.setDisable(false);
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("p is not prime number");
                    alert.showAndWait();
                }
            } catch (NumberFormatException | IndexOutOfBoundsException e ) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("invalid p");
                alert.showAndWait();
            }
        });

        btnEnterPrivateKey.setOnAction(event -> {
            int tempX;
            try {
                tempX = Integer.parseInt(tfX.getText());
                if (tempX >= p - 1 || tempX == 1) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("invalid x");
                    alert.showAndWait();
                } else {
                    x = tempX;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("x is set");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("invalid x");
                alert.showAndWait();
            }
        });

        btnEnterSecretNumber.setOnAction(event -> {
            int tempK;
            try {
                tempK = Integer.parseInt(tfK.getText());
                if ((tempK>0 && tempK<p) && !logic.isRelativelyPrime(tempK, p-1)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("invalid k");
                    alert.showAndWait();
                } else {
                    btnEncrypt.setDisable(false);
                    btnDecrypt.setDisable(false);
                    k = tempK;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setContentText("k is set");
                    alert.showAndWait();
                }
            } catch (NumberFormatException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("invalid k");
                alert.showAndWait();
            }
        });

        btnEncrypt.setOnAction(event -> {
            try {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(hilichurls.hilichurls);
                byteInputText = byteArrayOutputStream.toByteArray();
                intInputText = logic.fromByteArray2IntArray(byteInputText);
                objectOutputStream.close();
                byteArrayOutputStream.close();
            } catch (IOException e){
                e.printStackTrace();
            }

            g = cbPrimitiveRoots.getValue();

            y = logic.powerMod(g, x, p);

            int randomNumber=0;

            for (int value : intInputText) {
                int a = logic.powerMod(g, k + randomNumber, p);
                int b = logic.powerModWithMultiply(y, k+randomNumber, value, p);
                encryptText.add(a);
                encryptText.add(b);
                do {
                    randomNumber = random.nextInt(100);
                } while ((k+randomNumber>0 && k+randomNumber<p) && !logic.isRelativelyPrime(k+randomNumber,p-1 ));
            }

            try {
                encryptFile = fcChooseFile.showSaveDialog(encryptionStage);
                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(encryptFile));
                FileOutputStream fileOutputStream = new FileOutputStream(encryptFile);
                for (Integer integer :
                        encryptText) {
                    //fileOutputStream.write(integer);
                    bufferedWriter.write(integer);
                }
                bufferedWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            encryptionStage.close();
        });

        btnDecrypt.setOnAction(event -> {
            encryptText= new ArrayList<>();
            try{
                encryptFile=fcChooseFile.showOpenDialog(encryptionStage);
                BufferedReader bufferedReader = new BufferedReader(new FileReader(encryptFile));
                //FileInputStream fileInputStream = new FileInputStream(encryptFile);
                int temp = bufferedReader.read();
                //int temp = fileInputStream.read();
                while (temp>=0){
                    encryptText.add(temp);
                    //temp = fileInputStream.read();
                    temp = bufferedReader.read();
                }
                //fileInputStream.close();
                bufferedReader.close();
            } catch (IOException e){
                e.printStackTrace();
            }

            intDecryptText = new int[encryptText.size()/2];
            for (int i = 0; i < encryptText.size(); i+=2) {
                int temp = logic.powerModWithMultiply(encryptText.get(i),-x, encryptText.get(i+1),p);
                intDecryptText[i / 2] = temp;
            }
            byteDecryptText= logic.fromIntArray2ByteArray(intDecryptText);

            try {
                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteDecryptText);
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

                ArrayList<Hilichurl> characters = (ArrayList<Hilichurl>) objectInputStream.readObject();
                hilichurls.hilichurls.addAll(characters);

                objectInputStream.close();
                byteArrayInputStream.close();

                for (Hilichurl character :
                        hilichurls.hilichurls) {
                    movement.keyPress(scene, heroes, character, character.getX(),
                            character.getY());
                    movement.stopTimer();
                }
                scene.getRoot().requestFocus();

            } catch (IOException | ClassNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText("The file could not be decrypted, check that public and private keys are entered" +
                        " correctly");
                alert.showAndWait();
                e.printStackTrace();
            }
            encryptionStage.close();
        });

        VBox root = new VBox(10, hbP, btnCalculatePublicKey, hbPrimitiveRoots, hbX, btnEnterPrivateKey,
                hbK,btnEnterSecretNumber, buttons);

        Scene optionsScene = new Scene(root, 300, 350);
        encryptionStage.setScene(optionsScene);
        encryptionStage.setTitle("Encryption");
        encryptionStage.show();
    }
}