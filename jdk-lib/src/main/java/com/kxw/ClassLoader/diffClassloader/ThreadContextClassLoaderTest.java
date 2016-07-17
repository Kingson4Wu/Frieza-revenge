package com.kxw.classLoader.diffClassloader;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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

        System.out.println("Thread class ClassLoader: " + Thread.class.getClassLoader());
        System.out.println("String class ClassLoader: " + String.class.getClassLoader());
        try {
            System.out.println(cl.loadClass("java.lang.Thread").getClassLoader());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

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

                Method getThreadContext = bClazz.getMethod("getContextClassLoader");
                getThreadContext.invoke(bClazz.newInstance());

            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }

        }).start();

/**
 * Bootstrap ClassLoader（引导类加载器）：作为JVM的一部分无法在应用程序中直接引用，由C/C++实现（其他JVM可能通过Java来实现）。
 * 负责加载①<JAVA>/jre/lib目录 或 ②-Xbootclasspath参数所指定的目录 或
 * ③系统属性sun.boot.class.path指定的目录中特定名称的jar包。
 * 在JVM启动时将通过Bootstrap ClassLoader加载rt.jar，
 * 并初始化sun.misc.Launcher从而创建Extension ClassLoader和System ClassLoader实例，
 * 和将System ClassLoader实例设置为主线程的默认Context ClassLoader（线程上下文加载器）。
 */
        /**
         * Bootstrap类加载器负责加载rt.jar中的JDK类文件，它是所有类加载器的父加载器。Bootstrap类加载器没有任何父类加载器，
         * 如果你调用String.class.getClassLoader()，会返回null，
         * 任何基于此的代码会抛出NUllPointerException异常。Bootstrap加载器被称为初始类加载器。
         *
         * Thread和String都是rt类的包,它们的classLoader为null
         */
    }
}
