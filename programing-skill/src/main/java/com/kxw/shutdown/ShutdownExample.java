package com.kxw.shutdown;

/**
 * Created by  Ethan.wei on 2015/9/23.
 */
public class ShutdownExample {
    public static void main(String[] args) {


        Runtime.getRuntime().addShutdownHook(new Thread() {

            @Override
            public void run() {
                //
            }
        });
        waitIndefinitely();
    }

    //
    private static void waitIndefinitely() {
        Object lock = new Object();
        synchronized (lock) {
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
