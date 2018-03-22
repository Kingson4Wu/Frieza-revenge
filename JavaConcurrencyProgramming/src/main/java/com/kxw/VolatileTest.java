package com.kxw;

/**
 * Created by kingsonwu on 18/3/15.
 */
public class VolatileTest {

    int a = 0;
    volatile boolean flag = false;

    public void writer() {
        System.out.println("start");
        a = 1;                   //1


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = true;               //2
    }

    public void reader() {
        if (flag) {                //3
            int i = a;           //4

            System.out.println("kxw:" + i);
        }
        System.out.println("finish");
    }

    public static void main(String[] args) {

        VolatileTest test = new VolatileTest();

        new Thread(() -> {
            test.writer();
        }).start();

        new Thread(() -> {
            test.reader();
        }).start();
    }
}
