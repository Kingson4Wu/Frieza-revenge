package com.kxw.callbackAndFuture;

import java.util.concurrent.*;

/**
 * {<a href='http://blog.csdn.net/ghsau/article/details/7451464'>@link</a>}
 *  执行多个带返回值的任务，并取得多个返回值
 */
public class CallableAndFuture3 {
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(threadPool);
        for(int i = 1; i < 5; i++) {
            final int taskID = i;
            cs.submit(new Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    return taskID;
                }
            });
        }
        // 可能做一些事情
        for(int i = 1; i < 5; i++) {
            try {
                System.out.println(cs.take().get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
    /**
     *  其实也可以不使用CompletionService，
     *  可以先创建一个装Future类型的集合，用Executor提交的任务返回值添加到集合中，最后遍历集合取出数据，
     */
}