package com.example.lapa12.decorator;

import java.util.Base64;

public class EncryptionDecorator extends DataSourceDecorator{
    public static final byte coefficient =3;
    public EncryptionDecorator(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data){
        super.writeData(encode(data));
    }

    @Override
    public String readData(){
        return decode(super.readData());
    }

    private String encode(String data){
        byte[] result = data.getBytes();
        for (int i = 0; i < result.length; i++) {
            result[i] += coefficient;
        }
        return Base64.getEncoder().encodeToString(result);
    }

    private String decode(String data){
        byte[] result = Base64.getDecoder().decode(data);
        for (int i = 0; i < result.length; i++) {
            result[i] -= coefficient;
        }
        return new String(result);
    }
}
