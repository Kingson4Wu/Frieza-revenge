package com.kxw.sync;

class Sync {

    private byte[] lock = new byte[0];

    public void sync() throws InterruptedException {

        synchronized (lock) {
            runThread();
        }

    }

    public void thisSync() throws InterruptedException {
        System.out.println("thisSync");
        synchronized (this) {
            runThread();
        }

    }

    public void thisSync2() throws InterruptedException {

        System.out.println("thisSync2");
        synchronized (this) {
            runThread();
        }

    }

    public synchronized static void staticSync() throws InterruptedException { // 同步的static 函数
        runThread();
    }

    public void classSync() throws InterruptedException { //

        synchronized (Sync.class) {
            runThread();
        }
    }

    private static void runThread() throws InterruptedException {

        Thread current = Thread.currentThread();
        System.out.println("current thread id:" + current.getId() + "enter...");

        System.out.println("1111");
        Thread.sleep(1000);
        System.out.println("2222");
        Thread.sleep(1000);
        System.out.println("3333");
        Thread.sleep(1000);
        System.out.println("4444");
        Thread.sleep(1000);
        System.out.println("5555");

        System.out.println("current thread id:" + current.getId() + "out...");

    }

}