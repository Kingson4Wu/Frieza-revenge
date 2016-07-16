package com.kxw.classLoader.diffClassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by kingsonwu on 16/7/16.
 */
public class ClassForNameClassLoaderTest {

    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        String dir = Thread.currentThread().getContextClassLoader().getResource("").getPath();

        URL[] classLoaderUrls = new URL[]{new URL("file:" + dir)};

        //parent class Loader 为extClassLoader!!!!!!
        URLClassLoader cl = new KingsonClassLoader(classLoaderUrls, DifferentClassLoaderTest.class.getClassLoader().getParent());

        Class<?> bClazz = cl.loadClass("com.kxw.classLoader.diffClassloader.BMW");

        System.out.println(bClazz.getClassLoader());
        System.out.println("class classloader: " + BMW.class.getClassLoader());
        System.out.println("new classloader: " + new BMW().getClass().getClassLoader());

        Object o = bClazz.newInstance();

        System.out.println(o);

        // Getting a method from the loaded class and invoke it
        Method method = bClazz.getMethod("sayHello");
        method.invoke(o);


        //parent class Loader 为AppClassLoader!!!!!!
        URLClassLoader cl2 = new KingsonClassLoader(classLoaderUrls, DifferentClassLoaderTest.class.getClassLoader());
        Class<?> bClazz2 = cl2.loadClass("com.kxw.classLoader.diffClassloader.BMW");
        System.out.println("BMW classLoader: "+ bClazz2.getClassLoader());
        Object o2 = bClazz2.newInstance();
        Method method2 = bClazz2.getMethod("sayHello");
        method2.invoke(o2);


        /**
         * class.forName 以及new,以及Car.class 都是取目前所处的类的加载器作为自身的加载器
         * (当然加载自定义加载器无法加载的时候可能取父加载器,这个取决于自定义加载器里面的定义)
         *
         * 即从取目前所处的类的加载器作为自身的加载器开始加载
         */

    }
}
