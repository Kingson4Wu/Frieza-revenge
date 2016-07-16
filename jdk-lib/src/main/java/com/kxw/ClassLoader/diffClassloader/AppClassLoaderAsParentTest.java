package com.kxw.classLoader.diffClassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class AppClassLoaderAsParentTest {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, MalformedURLException, NoSuchMethodException, InvocationTargetException {

        String dir = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        // Getting the jar URL which contains target class
        URL[] classLoaderUrls = new URL[]{new URL("file:" + dir)};

        // Create a new URLClassLoader
        //parent class Loader 为AppClassLoader!!!!!!
        URLClassLoader cl = new KingsonClassLoader(classLoaderUrls, AppClassLoaderAsParentTest.class.getClassLoader());

        System.out.println("cl parent: " + cl.getParent());
        System.out.println("cl: " + cl);
        Class<?> factoryClass = cl.loadClass("com.kxw.classLoader.diffClassloader.Factory");
        //Class<?> factoryClass = Class.forName("com.kxw.classLoader.diffClassloader.Factory");
        Object factoryObject = factoryClass.newInstance();

        Method factoryMethod = factoryClass.getMethod("setCar", cl.loadClass("com.kxw.classLoader.diffClassloader.Car"));
        //Method factoryMethod = factoryClass.getMethod("setCar", Car.class);
        Method factoryGetMethod = factoryClass.getMethod("getCar");
        Method factoryGetCarClassLoaderMethod = factoryClass.getMethod("getCarClassLoader");

        Car car = new BMW();


        System.out.println("factory classLoader : " + factoryObject.getClass().getClassLoader());
        System.out.println("factory classLoader parent: " + factoryObject.getClass().getClassLoader().getParent());
        System.out.println("factory car classLoader: " + factoryGetCarClassLoaderMethod.invoke(factoryObject));
        System.out.println("car classLoader:" + car.getClass().getClassLoader());
        factoryMethod.invoke(factoryObject, car);

     /*   Method factoryGetMethod = factoryClass.getMethod("getCar");

        System.out.println("factory car classLoader: " + factoryGetMethod.invoke(factoryObject).getClass().getClassLoader());
*/


        /**
         * 虽然这里的car是使用AppClassLoader加载,而Factory使用的是KingsonClassLoader,
         * AppClassLoader 是KingsonClassLoader 的父加载器
         * 但是不通加载器之间的类还是不能相互set,即使是父的set到子的!! (factory里的car属性是使用KingsonClassLoader加载的)
         */
        //System.out.println();

    }
}

