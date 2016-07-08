package com.kxw.string;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberUtils {


    public static void main(String[] args) {

        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        for (int i =0;i<100;i++) {
            System.out.println(threadLocalRandom.nextInt(1000, 10000));
        }
    }


    public static String getRandNum(int charCount) {
        String charValue = "";
        for (int i = 0; i < charCount; i++) {
            char c = (char) (randomInt(0, 10) + '0');
            charValue += String.valueOf(c);
        }
        return charValue;
    }
    public static int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
}
