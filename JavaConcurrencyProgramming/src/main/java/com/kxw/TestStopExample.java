package com.kxw;

import java.util.concurrent.TimeUnit;

/**
 * Created by kingsonwu on 17/3/19.
 *
 *  <a href='http://www.infoq.com/cn/articles/netty-concurrent-programming-analysis/'>@link</a>
 * @author kingsonwu
 * @date 2017/03/19
 */
public class TestStopExample {

    private static boolean stop;


    //跟预期的不符合...
    public static void main(String[] args) throws InterruptedException {
        Thread workThread = new Thread(() -> {
            int i=0;
            while (!stop) {
                i++;
                try {
                    TimeUnit.SECONDS.sleep(1);
                    System.out.println(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });
        workThread.start();
        TimeUnit.SECONDS.sleep(3);
        stop=true;
    }
}
