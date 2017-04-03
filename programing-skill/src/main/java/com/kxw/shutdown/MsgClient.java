package com.kxw.shutdown;


import sun.misc.Signal;
import sun.misc.SignalHandler;
import java.util.concurrent.*;

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