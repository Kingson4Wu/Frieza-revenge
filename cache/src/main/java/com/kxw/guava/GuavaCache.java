package com.kxw.guava;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * Created by kingsonwu on 16/7/6.
 * <a href='http://www.cnblogs.com/peida/p/Guava_Cache.html'>@link</a>
 */
public class GuavaCache {

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        LoadingCache<String, String> cache = CacheBuilder
                .newBuilder()
                .maximumSize(1000)
                .refreshAfterWrite(120, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String key) throws Exception {

                        System.out.println("--------");
                        String strProValue = "hello " + key + "!";
                        return strProValue;
                    }

                });


        for (int i = 0; i < 10; i++) {
            System.out.println(cache.get("kxw"));
            Thread.sleep(3000);
        }


        //---------------


      /*  Cache<String, Boolean> activeUserEMailCache = CacheBuilder.newBuilder()
                .maximumSize(10000)
                //.expireAfterAccess(1, TimeUnit.HOURS)
                //.expireAfterWrite(6, TimeUnit.HOURS)
                .expireAfterWrite(5, TimeUnit.SECONDS)
                .build();

        System.out.println("size:" + activeUserEMailCache.size());
        activeUserEMailCache.put("kxw", true);
        System.out.println(activeUserEMailCache.getIfPresent("kxw") != null);
        System.out.println("size:" + activeUserEMailCache.size());
        Thread.sleep(10000);
        System.out.println(activeUserEMailCache.getIfPresent("kxw") != null);
        System.out.println("size:" + activeUserEMailCache.size());*/

    }
}
