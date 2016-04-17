package com.kxw.callbackAndFuture;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * {<a href='http://blog.csdn.net/ghsau/article/details/7451464'>@link</a>}
 */
public class CallableAndFuture {

    /**
     * Callable接口类似于Runnable，从名字就可以看出来了，但是Runnable不会返回结果，并且无法抛出返回结果的异常，
     * 而Callable功能更强大一些，被线程执行后，可以返回值，这个返回值可以被Future拿到，也就是说，
     * Future可以拿到异步执行任务的返回值，下面来看一个简单的例子
     */
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();
        try {
            Thread.sleep(5000);// 可能做一些事情
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    /**
     * FutureTask实现了两个接口，Runnable和Future，所以它既可以作为Runnable被线程执行，
     * 又可以作为Future得到Callable的返回值，那么这个组合的使用有什么好处呢？假设有一个很耗时的返回值需要计算，
     * 并且这个返回值不是立刻需要的话，那么就可以使用这个组合，用另一个线程去计算返回值，
     * 而当前线程在使用这个返回值之前可以做其它的操作，等到需要这个返回值时，再通过Future得到，岂不美哉！
     * 这里有一个Future模式的介绍：http://openhome.cc/Gossip/DesignPattern/FuturePattern.htm。

     */
}