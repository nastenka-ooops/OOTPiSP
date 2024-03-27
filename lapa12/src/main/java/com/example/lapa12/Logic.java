package com.example.lapa12;

import com.example.lapa12.factories.*;
import com.example.lapa12.heros.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Logic {
    private static final ObjectMapper mapper = new ObjectMapper();
    private static final File BINSerializeFile = new File("ser.bin");
    private static final File JSONSerializeFile = new File("ser.json");
    public void BINSerialize(List<Hilichurl> characters) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(BINSerializeFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

        objectOutputStream.writeObject(characters);
        System.out.println("Well done");
        objectOutputStream.close();
    }

    public void JSONSerialize(Hilichurls characters) throws IOException {
        mapper.writeValue(JSONSerializeFile, characters);
    }

    public ArrayList<Hilichurl> BINDeserialize() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(BINSerializeFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        ArrayList<Hilichurl> characters = (ArrayList<Hilichurl>) objectInputStream.readObject();

        objectInputStream.close();
        System.out.println("Well done");
        return characters;
    }

    public Hilichurls JSONDeserialize() throws IOException {
        return mapper.readValue(JSONSerializeFile, Hilichurls.class);
    }
    public Hilichurl[] createHilichurls(Hilichurl[] hilichurls) throws FileNotFoundException {
        hilichurls[0] = new Hilichurl(20);
        hilichurls[1] = new Mitachurl(44, Element.ANEMO, Element.DENDRO);
        hilichurls[2] = new HilichurlGrenadier(67, Element.ANEMO);
        hilichurls[3] = new HilichurlShooter(55, Element.ELECTRO);
        hilichurls[4] = new HilichurlFighter(30, Element.FIRE);
        hilichurls[5] = new Lawachurl(89, Element.HYDRO, Element.GEO, Element.FIRE);
        hilichurls[6] = new HilichurlGuard(78, Element.CRIO, Element.GEO);

        return hilichurls;

    }

    public void createHilichurlsFactories(Factory[] factories){
        factories[0] = new HilichurlFactory();
        factories[1] = new MitachurlFactory();
        factories[2] = new HilichurlGrenadierFactory();
        factories[3] = new HilichurlShooterFactory();
        factories[4] = new HilichurlFighterFactory();
        factories[5] = new LawachurlFactory();
        factories[6] = new HIlichurlGuardFactory();

    }

    public Hilichurl[] recoverHilichurls(Hilichurl[] hilichurls){
        for (Hilichurl hilichurl :
                hilichurls) {
            hilichurl.recovery();
        }
        return hilichurls;
    }
    public void showHilichurls(Hilichurl[] hilichurls, GridPane gridPane){

        Label label = new Label();
        label.setWrapText(true);
        gridPane.add(new ImageView(hilichurls[0].getImage()), 2,0);
        label.setText(hilichurls[0].printInfo());
        gridPane.add(label, 2,1);
        for (int i = 1; i < 5 ; i++) {
            Label label1 = new Label();
            label1.setWrapText(true);
            label1.setText(hilichurls[i].printInfo());
            if (i<=2){
                gridPane.add(new ImageView(hilichurls[i].getImage()), i-1,2);
                gridPane.add(label1, i-1,3);
            }
            else {
                gridPane.add(new ImageView(hilichurls[i].getImage()), i, 2);
                gridPane.add(label1, i,3);
            }
        }
        Label label2 = new Label();
        label2.setWrapText(true);
        gridPane.add(new ImageView(hilichurls[5].getImage()), 0,4);
        label2.setText(hilichurls[5].printInfo());
        gridPane.add(label2, 0,5);

        Label label3 = new Label();
        label3.setWrapText(true);
        gridPane.add(new ImageView(hilichurls[6].getImage()), 4,4);
        label3.setText(hilichurls[6].printInfo());
        gridPane.add(label3, 4,5);
    }
}
