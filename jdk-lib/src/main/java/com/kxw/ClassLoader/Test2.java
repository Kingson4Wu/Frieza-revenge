package com.kxw.classLoader;

/**
 * Created by kingsonwu on 16/4/11.
 */
public class Test2 {
    private Test2(){
        System.out.println("xss");
    }

    public static void main(String[] args) {
        func();
    }
    static Test2 st = new Test2();
    static void func(){
        System.out.println("ddd");
    }
}