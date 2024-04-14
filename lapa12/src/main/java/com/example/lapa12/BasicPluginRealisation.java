package com.example.lapa12;

import java.io.File;

public class BasicPluginRealisation implements PluginRealisation {
    @Override
    public void doSmth() {
        String folderPath = "images";

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
       // Main.imagePaths.add("src/main/java/com/example/lapa12/images/Hilichurl.jpg");
        System.out.println("Basic realisation");
    }
}
