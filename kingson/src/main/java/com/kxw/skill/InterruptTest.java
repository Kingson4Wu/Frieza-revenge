package com.kxw.skill;

/**
 * Created by kingsonwu on 18/3/30.
 */
public class InterruptTest {
    public static void main(String[] args) {

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("-----");
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                }
                System.out.println("end");
            }
        });
        thread1.start();

        thread1.interrupt();
    }
}
