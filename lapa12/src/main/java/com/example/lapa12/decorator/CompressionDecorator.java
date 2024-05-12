package com.example.lapa12.decorator;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.InflaterInputStream;

public class CompressionDecorator extends DataSourceDecorator{
    public int compressionLevel = 6;
    public CompressionDecorator(DataSource source) {
        super(source);
    }

    public int getCompressionLevel() {
        return compressionLevel;
    }

    public void setCompressionLevel(int compressionLevel) {
        this.compressionLevel = compressionLevel;
    }

    @Override
    public void writeData(String data){
        super.writeData(compress(data));
    }

    @Override
    public String readData(){
        return decompress(super.readData());
    }

    private String compress(String stringData) {
        byte[] data = stringData.getBytes();
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
            DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream,
                    new Deflater(compressionLevel));
            deflaterOutputStream.write(data);
            deflaterOutputStream.close();
            byteArrayOutputStream.close();
            return Base64.getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
        } catch (IOException e) {
            return null;
        }
    }
    private String decompress(String stringData) {
        byte[] data = Base64.getDecoder().decode(stringData);
        try {
            InputStream byteArrayInputStream = new ByteArrayInputStream(data);
            InflaterInputStream inflaterInputStream = new InflaterInputStream(byteArrayInputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
            int b;
            while ((b = inflaterInputStream.read()) != -1) {
                byteArrayOutputStream.write(b);
            }
            byteArrayInputStream.close();
            inflaterInputStream.close();
            byteArrayOutputStream.close();
            return byteArrayOutputStream.toString();
        } catch (IOException ex) {
            return null;
        }
    }
}
