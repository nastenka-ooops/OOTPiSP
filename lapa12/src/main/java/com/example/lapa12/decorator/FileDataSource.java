package com.example.lapa12.decorator;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileDataSource implements DataSource{
    private String name;

    public FileDataSource(String name) {
        this.name = name;
    }

    @Override
    public void writeData(String data) {
        File file = new File(name);
        try (OutputStream fileOutputStream = new FileOutputStream(file)){
            fileOutputStream.write(data.getBytes(StandardCharsets.UTF_8),0, data.length());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String readData() {
        char[] buffer = null;
        File file = new File(name);
        try(FileReader fileReader = new FileReader(file)) {
            buffer = new char[(int) file.length()];
            fileReader.read(buffer);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new String(buffer);
    }
}
