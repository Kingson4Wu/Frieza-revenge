package com.kxw.thread;

/**
 * 使用Thread类模拟4个售票窗口共同卖100张火车票的程序，实际上是各卖100张
 */
public class ThreadTest {

    public static void main(String[] args) {
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
        new MyThread().start();
    }
}

class MyThread extends Thread {
    private int tickets = 100;

    public void run() {
        while (tickets > 0) {
            System.out.println(this.getName() + "卖出第【" + tickets-- + "】张火车票");
        }
    }
}