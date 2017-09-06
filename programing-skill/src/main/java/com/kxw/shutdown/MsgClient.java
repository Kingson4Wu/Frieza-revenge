package com.kxw.shutdown;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import sun.misc.Signal;
import sun.misc.SignalHandler;

/**
 * @author lujianing01@58.com
 * @Description:
 * @date 2016/11/14
 * <a href='https://my.oschina.net/lujianing/blog/787745'>@link</a>
 */
public class MsgClient {

    //模拟消费线程池 同时4个线程处理
    private static final ThreadPoolExecutor THREAD_POOL = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);

    //模拟消息生产任务
    private static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newSingleThreadScheduledExecutor();

    //用于判断是否关闭订阅
    private static volatile boolean isClose = false;

    public static void main(String[] args) throws InterruptedException {

        //注册钩子方法
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                close();
            }
        });

        BlockingQueue <String> queue = new ArrayBlockingQueue<String>(100);
        producer(queue);
        consumer(queue);

    }

    //模拟消息队列生产者
    private static void producer(final BlockingQueue  queue){
        //每200毫秒向队列中放入一个消息
        SCHEDULED_EXECUTOR_SERVICE.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                queue.offer("");
            }
        }, 0L, 200L, TimeUnit.MILLISECONDS);
    }

    //模拟消息队列消费者 生产者每秒生产5个   消费者4个线程消费1个1秒  每秒积压1个
    private static void consumer(final BlockingQueue queue) throws InterruptedException {
        while (!isClose){
            getPoolBacklogSize();
            //从队列中拿到消息
            final String msg = (String)queue.take();
            //放入线程池处理
            if(!THREAD_POOL.isShutdown()) {
                THREAD_POOL.execute(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            //System.out.println(msg);
                            TimeUnit.MILLISECONDS.sleep(1000L);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        }
    }

    //查看线程池堆积消息个数
    private static long getPoolBacklogSize(){
        long backlog = THREAD_POOL.getTaskCount()- THREAD_POOL.getCompletedTaskCount();
        System.out.println(String.format("[%s]THREAD_POOL backlog:%s",System.currentTimeMillis(),backlog));
        return backlog;
    }

    private static void close(){
        System.out.println("收到kill消息，执行关闭操作");
        //关闭订阅消费
        isClose = true;
        //关闭线程池，等待线程池积压消息处理
        THREAD_POOL.shutdown();
        //判断线程池是否关闭
        while (!THREAD_POOL.isTerminated()) {
            try {
                //每200毫秒 判断线程池积压数量
                getPoolBacklogSize();
                TimeUnit.MILLISECONDS.sleep(200L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("订阅者关闭，线程池处理完毕");
    }

    static {
        String osName = System.getProperty("os.name").toLowerCase();
        if(osName != null && osName.indexOf("window") == -1) {
            //注册linux kill信号量  kill -12
            Signal sig = new Signal("USR2");
            Signal.handle(sig, new SignalHandler() {
                @Override
                public void handle(Signal signal) {
                    close();
                }
            });
        }
    }

}

/**
 平滑关闭的思路如下：

 在关闭程序时，首先关闭消息订阅，保证不再接收新的消息。
 关闭线程池，等待线程池中的消息处理完毕。
 程序退出。
 关闭消息订阅：消息队列的客户端都会提供关闭连接的方法，具体可以自行查看API。

 关闭线程池：Java的ThreadPoolExecutor线程池提供shutdown()和shutdownNow()两个方法，区别是前者会等待线程池中的消息都处理完毕，后者会直接停止所有线程并返回未处理完的线程List。因为我们需要使用shutdown()方法进行关闭，并通过isTerminated()方法，判断线程池是否已经关闭。

 那么问题又来了，我们如何通知到程序，需要执行关闭操作呢?

 在Linux中，进程的关闭是通过信号传递的，我们可以用kill -9 pid关闭进程，除了-9之外，我们可以通过 kill -l，查看kill命令的其它信号量。
 */

/**
 提供两种关闭方法：

 程序中添加Runtime.getRuntime().addShutdownHook钩子方法，SIGTERM,SIGINT,SIGHUP三种信号都会触发该方法（分别对应kill -1/kill -2/kill -15，Ctrl+C也会触发SIGINT信号）。

 程序中通过Signal类注册信号监听，比如USR2（对应kill -12），在handle方法中执行关闭操作。

 补充说明：addShutdownHook方法和handle方法中如果再调用System.exit，会造成deadlock，使进程无法正常退出。
 */

/**
 * sun.misc.SignalHandler，sun.misc.Signal不太推荐使用这些类吧！
 */

/**
 <pre>
 Java.Runtime.addShutdownHook(Thread hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在一下几种场景中被调用：

 程序正常退出
 使用System.exit()
 终端使用Ctrl+C触发的中断
 系统关闭
 OutOfMemory宕机
 使用Kill pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的）
 </pre>
 */

/**
 * <pre>
 *     在 Linux 中，通常可以发送一些信号来杀死一个进程，
 *     一般用来杀死进程的信号有 SIGTERM、 SIGKILL。
 *     但是，如果希望进程合理地终止，就不要发送硬中断信号 SIGKILL，因为该信号是不能拦截的，
 *     进程接到该信号之后会马上退出，并没有机会进行现场清理——这包括对线程的关闭等操作。
 *     更好的做法是，发送 SIGTERM 信号，这样进程在接到该信号后，可以做一些退出的准备工作。
 * </pre>
 *
 * <pre>
 *     linux杀死进程以及发送或响应信号
 *     <a href='http://www.cnblogs.com/gengzj/p/3827108.html'>@link</a>
 *      kill -l : 该命令会打印出信号数(signal number)和信号名称。
 *      要通过kill命令向进程发送特定的信号，可以使用：$ kill -s SIGNAL PID
 *
 *      尽管可以指定很多信号用于不同的目的，我们经常用到的其实只有少数几个，具体如下所示。
 *      1）、SIGHUP 1 --- 对控制进程或终端进行挂起检测(hangup detection)。
 *      2）、SIGINT 2 --- 当按下CTRL+C时发送该信号。
 *      3）、SIGKILL 9 --- 用于强行杀死进程。
 *      4）、SIGTERM 15 --- 默认用于终止进程。
 *      5）、SIGTSTP 20 --- 当按下Ctrl+Z时发送该信号。(暂停,挂起)
 *
 *      如果要强行杀死进程，则使用：
 *      $ kill -s SIGKILL PROCESSED_ID 或者 $ kill -9 PROCESSED_ID
 * </pre>
 */