package com.kxw.test;

class Test {
    private static final int MASK = (2 << 6) - 1;

    public static void main(String[] args) {

        System.out.println(130 & MASK);

        System.out.println((2 << 6) - 1);

        System.out.println(2 << 6);

    }

}