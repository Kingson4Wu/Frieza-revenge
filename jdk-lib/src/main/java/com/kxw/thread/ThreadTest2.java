package com.kxw.thread;

/**
 * <a href='http://www.cnblogs.com/linjiqin/archive/2011/04/10/2011272.html'>@link</a>
 * Thread类中run()和start()方法的区别如下：
 run()方法:在本线程内调用该Runnable对象的run()方法，可以重复多次调用；
 start()方法:启动一个线程，调用该Runnable对象的run()方法，不能多次启动一个线程；
 */
public class ThreadTest2 {
    /**
     * 观察直接调用run()和用start()启动一个线程的差别
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) {
        Thread thread = new ThreadDemo();
        //第一种
        //表明: run()和其他方法的调用没任何不同,main方法按顺序执行了它,并打印出最后一句
        //thread.run();

        //第二种
        //表明: start()方法重新创建了一个线程,在main方法执行结束后,由于start()方法创建的线程没有运行结束,
        //因此主线程未能退出,直到线程thread也执行完毕.这里要注意,默认创建的线程是用户线程(非守护线程)
        //thread.start();

        //第三种
        //1、为什么没有打印出100句呢?因为我们将thread线程设置为了daemon(守护)线程,程序中只有守护线程存在的时候,是可以退出的,所以只打印了七句便退出了
        //2、当java虚拟机中有守护线程在运行的时候，java虚拟机会关闭。当所有常规线程运行完毕以后，
        //守护线程不管运行到哪里，虚拟机都会退出运行。所以你的守护线程最好不要写一些会影响程序的业务逻辑。否则无法预料程序到底会出现什么问题
        //thread.setDaemon(true);
        //thread.start();

        //第四种
        //用户线程可以被System.exit(0)强制kill掉,所以也只打印出七句
        thread.start();
        System.out.println("main thread is over");
        System.exit(1);
    }

    public static class ThreadDemo extends Thread {
        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                System.out.println("This is a Thread test" + i);
            }
        }
    }
}