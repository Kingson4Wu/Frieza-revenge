package com.kxw.sync;

public class TestSync {

    public static void main(String[] args) throws InterruptedException {
        final Sync sync = new Sync();

        new Thread(() -> {
            try {
                sync.thisSync2();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        sync.thisSync();

       /* for(int i =0; i<50;i++){
            System.out.println((int)(Math.random()*10)/5);
        }*/

    }
}
