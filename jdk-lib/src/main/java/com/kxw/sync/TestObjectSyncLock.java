package com.kxw.sync;

public class TestObjectSyncLock {

    private static byte[] lock = new byte[0];//零长度的byte数组对象创建起来将比任何对象都经济――查看编译后的字节码：生成零长度的byte[]对象只需3条操作码，而Object lock = new Object()则需要7行操作码。

    public static void main(String[] args) throws InterruptedException {

        //1.同一个线程lock对象锁是否可复用（即不用等待）----可复用

        synchronized (lock) {

            new Thread(new Runnable() {

                public void run() {

                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("aaa");

                }
            }).start();
            // lock.notify();
        }

        synchronized (lock) {

            System.out.println("bbb");
            // lock.wait();
        }
    }
}