package com.kxw.callbackAndFuture;


 import java.util.Random;
 import java.util.concurrent.*;

 /**
 * {<a href='http://blog.csdn.net/ghsau/article/details/7451464'>@link</a>}
 * 另一种方式使用Callable和Future，通过ExecutorService的submit方法执行Callable，并返回Future
 */
public class CallableAndFuture2 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<Integer> future = threadPool.submit(new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        });
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
      *  代码是不是简化了很多，ExecutorService继承自Executor，它的目的是为我们管理Thread对象，
      *  从而简化并发编程，Executor使我们无需显示的去管理线程的生命周期，是JDK 5之后启动任务的首选方式。
      */
}
