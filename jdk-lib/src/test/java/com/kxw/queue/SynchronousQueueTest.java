package com.kxw.queue;

import java.util.concurrent.SynchronousQueue;

/**
 * <a href = 'http://shift-alt-ctrl.iteye.com/blog/1840385'>@link</a>
 * 要有额外的"拒绝"策略支持.
 SynchronousQueue经常用来,一端或者双端严格遵守"单工"(单工作者)模式的场景,队列的两个操作端分别是productor和consumer.常用于一个productor多个consumer的场景。
 在ThreadPoolExecutor中,通过Executors创建的cachedThreadPool就是使用此类型队列.以确保,如果现有线程无法接收任务(offer失败),将会创建新的线程来执行.
 (空闲线程回对调用queue.take()阻塞等待，个人猜测！！)
 boolean offer(E o):向队列中提交一个元素,如果此时有其他线程正在被take阻塞(即其他线程已准备接收)或者"碰巧"有poll操作,那么将返回true,否则返回false.
 E take():获取并删除一个元素,阻塞直到有其他线程offer/put.
 boolean poll():获取并删除一个元素,如果此时有其他线程正在被put阻塞(即其他线程提交元素正等待被接收)或者"碰巧"有offer操作,那么将返回true,否则返回false.
 */
public class SynchronousQueueTest {
    /**
     * @param args
     */
    public static void main(String[] args) throws Exception {
        SynchronousQueue<Object> queue = new SynchronousQueue<Object>();
        for (int i = 0; i < 5; i++) {
            Thread t = new SQThread(queue, 1);
            t.start();
        }
        Thread.sleep(1000);
        //System.out.println(queue.take());
        for (int i = 0; i < 10; i++) {
            if (!queue.offer(new Object())) {
                System.out.println("Failure " + i);
            }else{
                System.out.println("offer success");
            }
        }
    }

    public static class SQThread extends Thread {
        private SynchronousQueue<Object> queue;
        int mode;

        SQThread(SynchronousQueue<Object> queue, int mode) {
            this.queue = queue;
            this.mode = mode;
        }

        @Override
        public void run() {
            Object item = null;
            try {
                System.out.println("Thread Id: " + Thread.currentThread().getId());
                if (mode == 1) {
                    while ((item = queue.take()) != null) {
                        System.out.println(item.toString());
                    }
                } else {
                    //
                }
            } catch (Exception e) {
                //
            }
            System.out.println("end");
        }
    }
}