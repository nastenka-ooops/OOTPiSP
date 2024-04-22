package com.example.encryption;

import java.math.BigInteger;
import java.util.ArrayList;

public class Logic {

    public int[] EEA(int a, int b)
    {
        // Исходные значения для уравнения ax + by = НОД(a, b)
        int x0 = 1, y0 = 0;
        int x1 = 0, y1 = 1;

        while (b != 0)
        {
            int q = a / b;
            int temp = b;
            b = a % b;
            a = temp;

            int xx = x0 - q * x1;
            int yy = y0 - q * y1;
            x0 = x1; y0 = y1;
            x1 = xx; y1 = yy;
        }
        return new int[]{a, x0, x1};
    }
    public long modPower(int base, int exponent, int modulus) {
        int tempBase = base;
        int tempExponent= exponent;
        int result = 1;
        while (tempExponent!=0){
            while (tempExponent%2==0){
                tempExponent/=2;
                tempBase = (tempBase*tempBase)%modulus;
            }
            tempExponent-=1;
            result = (result*tempBase) %modulus;
        }
        return result;

    }
    public boolean isPrimeNumber(int number) {
        BigInteger bigInteger = BigInteger.valueOf(number);
        return (bigInteger.isProbablePrime((int) Math.log(number)))&&(number!=1);
    }

    public boolean isRelativelyPrime(int num1, int num2){
        BigInteger bigInteger1 = BigInteger.valueOf(num1);
        BigInteger bigInteger2 = BigInteger.valueOf(num2);

        int result = EEA(num1, num2)[0];
        return bigInteger1.gcd(bigInteger2).intValue() == 1;
    }

    public ArrayList<Integer> findAllPrimeDivisors(int p) {
        int tempP = p;
        ArrayList<Integer> primeDivisors = new ArrayList<>();
        for (int i = 2; i * i <= tempP; i++) {
            while (tempP % i == 0) {
                primeDivisors.add(i);
                tempP /= i;
            }
        }
        if (tempP > 1) {
            primeDivisors.add(tempP);
        }
        return primeDivisors;
    }

    public int powerMod(int base, int exponent, int modulus) {
        BigInteger bigIntegerBase = BigInteger.valueOf(base);
        BigInteger bigIntegerExponent = BigInteger.valueOf(exponent);
        BigInteger bigIntegerModulus = BigInteger.valueOf(modulus);

        int result = (int) modPower(base, exponent, modulus);
        result = bigIntegerBase.modPow(bigIntegerExponent, bigIntegerModulus).intValue();
        return result;
    }

    public int powerModWithMultiply(int base, int exponent, int multiply, int modulus) {
        BigInteger bigIntegerBase = BigInteger.valueOf(base);
        BigInteger bigIntegerMultiply = BigInteger.valueOf(multiply);
        BigInteger bigIntegerModulus = BigInteger.valueOf(modulus);

        BigInteger power;
        if (exponent>=0) {
            power = bigIntegerBase.pow(exponent);
        } else {
            exponent = Math.abs(exponent);
            power = bigIntegerBase.pow(modulus - 1 -exponent);
        }

        return power.multiply(bigIntegerMultiply).mod(bigIntegerModulus).intValue();
    }

    public ArrayList<Integer> findAllPrimitiveRoots(int p, ArrayList<Integer> primeDivisors) {
        ArrayList<Integer> primitiveRoots = new ArrayList<>();

        for (int i = 2; i < p; i++) {
            int g = i;
            boolean flag = true;
            for (Integer primeDivisor : primeDivisors) {
                if (powerMod(g,(p-1)/primeDivisor, p)==1){
                    flag = false;
                }
            }
            if (flag){
                primitiveRoots.add(g);
            }
        }
        return primitiveRoots;
    }
    public byte[] fromArrayListOfBytesToArrayOfBytes(ArrayList<Byte> arrayList){
        byte[] tempArray = new byte[arrayList.size()];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i]= arrayList.get(i);
        }
        return tempArray;
    }
    public byte[] fromArrayListOfIntegersToArrayOfBytes(ArrayList<Integer> arrayList){
        byte[] tempArray = new byte[arrayList.size()];
        for (int i = 0; i < tempArray.length; i++) {
            tempArray[i]= arrayList.get(i).byteValue();
        }
        return tempArray;
    }
}
