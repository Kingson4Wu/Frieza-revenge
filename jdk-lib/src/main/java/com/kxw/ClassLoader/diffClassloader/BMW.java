package com.kxw.classLoader.diffClassloader;

public class BMW implements Car {

    @Override
    public void sayHello() {
        System.out.println("Hello from loaded Bean class for test!!!");
        try {

            ClassLoader kingForNameClassLoader = Class.forName("com.kxw.classLoader.diffClassloader.King").getClassLoader();

            System.out.println("BMW  King Class.forName classLoader: " + kingForNameClassLoader);
            System.out.println("BMW King Class classLoader: " + King.class.getClassLoader());
            System.out.println("BMW King  new Class classLoader: " + new King().getClass().getClassLoader());
            King king = (King) Class.forName("com.kxw.classLoader.diffClassloader.King").newInstance();

            king.haha();

            //-----
            System.out.println("--------------");

           /* ClassLoader cl = Thread.currentThread().getContextClassLoader();
            System.out.println("BMW ContextClassLoader : " + cl);
            King king2 = (King) cl.loadClass("com.kxw.classLoader.diffClassloader.King").newInstance();
            king2.haha();*/

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void getContextClassLoader() {

        System.out.println("BMW contextClassLoader: " + Thread.currentThread().getContextClassLoader());

        System.out.println("new Thread ClassLoader: " + new Thread().getClass().getClassLoader());
        System.out.println("Thread class ClassLoader: " + Thread.class.getClassLoader());
        System.out.println("Kingson class ClassLoader: " + King.class.getClassLoader());
        System.out.println("Thread class ClassLoader: " + Torres.class.getClassLoader());

        new Thread(() -> {
            System.out.println("BMW new Thread contextClassLoader: " + Thread.currentThread().getContextClassLoader());
        }).start();
    }
}
