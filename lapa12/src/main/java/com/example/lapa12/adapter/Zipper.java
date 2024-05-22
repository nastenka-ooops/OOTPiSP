package com.example.lapa12.adapter;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Zipper {

    public void zip(String filename) {

        try (FileOutputStream fos = new FileOutputStream(filename + ".zip");
             ZipOutputStream zos = new ZipOutputStream(fos);
             FileInputStream fis = new FileInputStream(filename)) {

            zos.putNextEntry(new ZipEntry(filename));

            byte[] buffer = new byte[1024];
            int length;

            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }

        } catch (IOException ignored) {
        }
    }

    public void unzip(String zipFilename) {

        try (ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFilename));
             FileOutputStream fos = new FileOutputStream("unzipped")) {

            ZipEntry zipEntry = zis.getNextEntry();
            if (zipEntry != null) {

                byte[] buffer = new byte[1024];
                int length;

                while ((length = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, length);
                }
            }

        } catch (IOException ignored) {
        }
    }
}
