package com.kxw.classLoader.diffClassloader;

public class BMW implements Car {

    @Override
    public void sayHello() {
        System.out.println("Hello from loaded Bean class for test!!!");
        try {
            System.out.println(Class.forName("com.kxw.classLoader.diffClassloader.King").getClassLoader());
            System.out.println(King.class.getClassLoader());
            King king = (King) Class.forName("com.kxw.classLoader.diffClassloader.King").newInstance();

            king.haha();

            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            System.out.println(cl);
            King king2 = (King) cl.loadClass("com.kxw.classLoader.diffClassloader.King").newInstance();
            king2.haha();

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
