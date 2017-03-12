package com.kxw;

/**
 * Created by kingsonwu on 17/3/12.
 */
public class TestThread {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("kxw");
            }
        });

        thread.start();

        //thread.s
        Thread.sleep(1000);
        thread.start();

    }
}
