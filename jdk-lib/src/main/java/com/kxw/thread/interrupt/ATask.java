package com.kxw.thread.interrupt;

class ATask implements Runnable {

    private double d = 0.0;

    @Override
    public void run() {
//死循环执行打印"I am running!" 和做消耗时间的浮点计算
        while (true) {
            System.out.println("I am running!");

            for (int i = 0; i < 900000; i++) {
                d = d + (Math.PI + Math.E) / d;
            }
//给线程调度器可以切换到其它进程的信号
            Thread.yield();
        }
    }
}