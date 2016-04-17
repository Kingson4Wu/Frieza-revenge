package com.kxw.thread.interrupt;

/**
 * <a href ='http://blog.sina.com.cn/s/blog_6ca570ed01016mti.html'>@link</a>
 */
public class InterruptTaskTest {

    public static void main(String[] args) throws Exception {
//将任务交给一个线程执行
        Thread t = new Thread(new ATask());
        t.start();

//运行一断时间中断线程
        Thread.sleep(100);
        System.out.println("****************************");
        System.out.println("Interrupted Thread!");
        System.out.println("****************************");
        t.interrupt();//将线程标志为中断，但不会抛出异常，但如果线程刚好在sleep则为抛出异常
    }
}
