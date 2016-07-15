package com.kxw.classLoader.diffClassloader;

import java.net.MalformedURLException;

public class DifferentClassLoaderTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException {

        A a = new A();
        Class<?> bClazz = new KingsonClassLoader().loadClass("com.kxw.classLoader.diffClassloader.B");

        System.out.println(A.class.getClassLoader());
        System.out.println(bClazz.getClassLoader());

        B b = (B) bClazz.newInstance();

        a.setB(b);/////dddd!!

    }
}

