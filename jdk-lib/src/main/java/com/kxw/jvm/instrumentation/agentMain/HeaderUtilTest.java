package com.kxw.jvm.instrumentation.agentmain;

/**
 * Created by kingsonwu on 17/4/3.
 *
 * @author kingsonwu
 * @date 2017/04/03
 */
public class HeaderUtilTest {

    public static void main(String[] args) {

        String input = "X-Priority";
        System.out.println(HeaderUtility.isPriorityCall(input));

        while (true) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(HeaderUtility.isPriorityCall(input));
        }

        //waitIndefinitely();
    }

    public static void waitIndefinitely() {
        Object lock = new Object();
        try {
            synchronized (lock) {
                lock.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
