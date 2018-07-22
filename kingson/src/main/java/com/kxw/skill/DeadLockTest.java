package com.kxw.skill;

/**
 * Created by kingsonwu on 18/3/30.
 */
public class DeadLockTest {

    private static Object lock1 = new Object();
    private static Object lock2 = new Object();

    private volatile static boolean flag = false;

    public static void method1() {
        synchronized (lock1) {

            System.out.println("method1 lock1");

            while (!flag) {}

            System.out.println("method1 wait lock2");

            synchronized (lock2) {
                System.out.println("method1");
            }
        }
    }

    public static void method2() {
        synchronized (lock2) {

            System.out.println("method2 lock2");

            flag = true;

            System.out.println("method2 wait lock1");
            synchronized (lock1) {
                System.out.println("method2");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            method1();
        }).start();

        new Thread(() -> {
            method2();
        }).start();
    }

}
