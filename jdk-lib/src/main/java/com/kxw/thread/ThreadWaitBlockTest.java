package com.kxw.thread;

/**
 * Created by kingson.wu on 2016/4/7.
 * <a href='http://bbs.csdn.net/topics/391928976'>@link</a>
 */
public class ThreadWaitBlockTest {

    /**
     * 两者都表示线程当前暂停执行的状态，而两者的区别，基本可以理解为：
     * 进入 waiting 状态是线程主动的，而进入 blocked 状态是被动的。
     * 更进一步的说，进入 blocked 状态是在同步（synchronized）代码之外，
     * 而进入 waiting 状态是在同步代码之内（然后马上退出同步）。
     */

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    run();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }


    public static void run() throws InterruptedException {
        synchronized (ThreadWaitBlockTest.class) {
            ThreadWaitBlockTest.class.wait(1000);
            //Thread.currentThread().sleep(2000);
            System.out.println("123");
        }
    }


}

/**
 调用Thread.sleep 和调用 wait 的最显著的区别在于锁有没有被释放

 synchronized(Thread.class) {
 wait(1000);
 //sleep(1000);
 System.out.println("123");
 }

 在多线程情况下运行,效果是非常明显的。
 wait的情况是->等了大约一秒,输出两行123
 sleep的情况是->等了大约一秒,输出一行123,又等了大约一秒,又输出一行123
 */