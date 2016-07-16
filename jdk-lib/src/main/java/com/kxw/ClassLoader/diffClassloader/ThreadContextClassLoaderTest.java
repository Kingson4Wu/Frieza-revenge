package com.kxw.classLoader.diffClassloader;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by kingsonwu on 16/7/16.
 */
public class ThreadContextClassLoaderTest {

    public static void main(String[] args) throws MalformedURLException {

        String dir = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        // Getting the jar URL which contains target class
        URL[] classLoaderUrls = new URL[]{new URL("file:" + dir)};

        // Create a new URLClassLoader
        //parent class Loader 为extClassLoader!!!!!!
        final URLClassLoader cl = new KingsonClassLoader(classLoaderUrls, DifferentClassLoaderTest.class.getClassLoader().getParent());

        System.out.println("????");

        new Thread(() -> {
            try {
                Class<?> bClazz = cl.loadClass("com.kxw.classLoader.diffClassloader.BMW");
                System.out.println("new thread class loader : " + Thread.currentThread().getContextClassLoader());
              /*  try {
                    BMW b = (BMW) bClazz.newInstance();
                    b.getContextClassLoader();
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }*/
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }).start();

    }
}
