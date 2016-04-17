package com.kxw.thread;

/**
 * 使用Runnable接口模拟4个售票窗口共同卖100张火车票的程序
 */
public class RunnableTest {

    public static void main(String[] args) {
        Runnable runnable = new MyThread();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
        new Thread(runnable).start();
    }

    public static class MyThread implements Runnable {
        private int tickets = 100;

        public void run() {
            while (tickets > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第【" + tickets-- + "】张火车票");
            }
        }
    }
}