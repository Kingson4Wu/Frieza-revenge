package com.kxw.skill;

/**
 * Created by kingsonwu on 18/3/29.
 */
public class HashMapResizeTest {

    private static final int MAXIMUM_CAPACITY = (int)Math.pow(2, 32);

    public static void main(String[] args) {

        //System.out.println(15 & (int)(Math.pow(2, 32) - 1));
        //System.out.println( (int)(Math.pow(2, 32) - 1));
        //System.out.println( (int)(Math.pow(2, 3) ));

        System.out.println(tableSizeFor(17));
        //System.out.println(tableSizeFor(15));
    }

    /**
     * 没次移位刚好错开上次与或的结果
     * @param cap
     * @return
     */
    static final int tableSizeFor(int cap) {
        System.out.println(Integer.toBinaryString(cap));
        int n = cap - 1;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> 1));
        n |= n >>> 1;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> 2));
        n |= n >>> 2;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> 4));
        n |= n >>> 4;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> 8));
        n |= n >>> 8;
        System.out.println(Integer.toBinaryString(n));
        System.out.println(Integer.toBinaryString(n >>> 16));
        n |= n >>> 16;
        System.out.println(Integer.toBinaryString(n));
       /* n |= n >>> 32;
        System.out.println(Integer.toBinaryString(n));*/
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    /*static final int tableSizeFor(int cap) {
        System.out.println(Integer.toBinaryString(cap));
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
       *//* n |= n >>> 32;
        System.out.println(Integer.toBinaryString(n));*//*
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }*/
}
