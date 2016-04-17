package com.kxw.jdeferred;

import org.jdeferred.*;
import org.jdeferred.impl.DefaultDeferredManager;
import org.jdeferred.impl.DeferredObject;
import org.jdeferred.multiple.OneResult;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by kingsonwu on 16/1/30.
 */
public class TestDeferredManager {
    public static void main(String[] args) {

        int cpuNums = Runtime.getRuntime().availableProcessors();
        //获取当前系统的CPU 数目
        ExecutorService executorService = Executors.newFixedThreadPool(cpuNums * 2);

        //DeferredManager dm = new DefaultDeferredManager();
        DeferredManager dm = new DefaultDeferredManager(executorService);
        Promise p1, p2, p3;
        Deferred deferred = new DeferredObject();
        p1 = deferred.promise();
        p2 = deferred.promise();
        p3 = deferred.promise();

        // initialize p1, p2, p3
        p1.done(result -> {
            System.out.println("kxw done1");
            System.out.println(result);
        });
        p2.done(result -> {
            System.out.println("kxw done2");
            System.out.println(result);
        });
        p3.done(result -> {
            System.out.println("kxw done3");
            System.out.println(result);
        });

        dm.when(p1, p2, p3).done(results -> {
            for (OneResult result : results) {
                System.out.println(result.getResult() + "---");
            }

        }).fail(oneReject -> {

        });


        deferred.resolve("done 123");
    }
}
