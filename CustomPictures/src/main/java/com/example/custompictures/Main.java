package com.example.custompictures;

import com.example.lapa12.PluginRealisation;

import java.io.File;

public class Main implements PluginRealisation {
    public static void main(String[] args) {
        System.out.println("Plugin CustomPictures was built");
    }
    @Override
    public void doSmth() {
        String folderPath = "../CustomPictures/images";

        // Получение списка файлов в папке
        File folder = new File(folderPath);
        File[] listOfFiles = folder.listFiles();

        // Создание списка для хранения имен файлов
        //List<String> fileList = new ArrayList<>();

        // Проверка, что список файлов не пустой
        if (listOfFiles != null) {
            // Добавление имен файлов в список
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    com.example.lapa12.Main.imagePaths.add(file.getAbsolutePath());
                }
            }
        }
        System.out.println("custom");
    }
}
