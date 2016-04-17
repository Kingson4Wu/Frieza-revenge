package cn.itheima;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class CopyOfTest1 {
    public static void main(String[] args) {
        //用来存放共享的日志对象，需要线程同步
        final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
        System.out.println("begin:" + System.currentTimeMillis() / 1000);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                while (true) {
                    //将日志对象拿出来
                    String log;
                    try {
                        log = queue.take();
                        parseLog(log);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
        //模拟处理16行日志，下面的代码产生16个日志对象，需运行16秒才能打印完
        //修改程序代码，开4个线程让这16个日志在4秒钟打完
        for (int i = 0; i < 16; i++)    //这行代码不能改动
        {
            final String log = "" + (i + 1);    //这行代码不能改动
            {
                //不能再内部创建线程，只能在外部创建，需要将日志对象进行共享
                try {
                    queue.put(log);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //Test1.parseLog(log);
            }
        }
    }

    //parseLog方法内部代码不能改动
    public static void parseLog(String log) {
        System.out.println(log + ":" + (System.currentTimeMillis() / 1000));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}