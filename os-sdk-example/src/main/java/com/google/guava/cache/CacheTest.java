package com.google.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.LoadingCache;

import javax.annotation.PostConstruct;

/**
 * Created by kxw on 2016/1/5.
 */
public class CacheTest {

  /*  private LoadingCache<String, List<AppsBrandProducts>> productsLocalCahce;
    private LoadingCache<String, Integer> productsCacheVersion;
    @PostConstruct
    public void loadLocalCache() {
        logger.info("load product data to local cache startup ...");

        productsLocalCahce = CacheBuilder.newBuilder()
                // .refreshAfterWrite(3, TimeUnit.MINUTES)// 三分钟更新一次
                // .expireAfterAccess(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, List<AppsBrandProducts>>() {
                    @Override
                    public List<AppsBrandProducts> load(String key) throws Exception {
                        logger.info("no data in local cache,key:{}",key);

                        *//** get data from memcached **//*
                        List<AppsBrandProducts> list = cacheService.get(key);
                        *//** if get data failed from memcached, get data from database **//*
                        if (null == list || list.isEmpty()) {
                            Map<String, Collection<AppsBrandProducts>> productsMap = getProductsFromDB();
                            *//** set data to memcached **//*
                            for (Map.Entry<String, Collection<AppsBrandProducts>> entry : productsMap.entrySet()) {
                                cacheService.set(entry.getKey(), BaseCacheService.EXPIRY_NEVER,
                                        (List<AppsBrandProducts>) entry.getValue());
                            }
                            return (List<AppsBrandProducts>) productsMap.get(key);
                        }
                        return list;
                    }
                });
        *//** load data from database to local cache **//*
        Map<String, Collection<AppsBrandProducts>> productsMap = getProductsFromDB();
        *//** set data to local cache **//*
        for (Map.Entry<String, Collection<AppsBrandProducts>> entry : productsMap.entrySet()) {
            productsLocalCahce.put(entry.getKey(), (List<AppsBrandProducts>) entry.getValue());
        }
        productsCacheVersion = CacheBuilder.newBuilder()
                .refreshAfterWrite(5, TimeUnit.SECONDS)// 三分钟更新一次
                .build(new CacheLoader<String, Integer>() {
                    @Override
                    public Integer load(String key) throws Exception {
                        logger.info("refresh product version from memcached");
                        *//** get version from memcached **//*
                        Integer cacheVersion = cacheService.get(key);
                        if (null != cacheVersion) {
                            System.out.println("======");
                            System.out.println(productsCacheVersion.get(key));
                            System.out.println(cacheVersion);
                            System.out.println("======");
                            *//** refresh data from memcached **//*
                          *//*  if (!productsCacheVersion.get(key).equals(cacheVersion)) {
                                for (String productKey : productsLocalCahce.asMap().keySet()) {
                                    productsLocalCahce.refresh(productKey);
                                }
                            }*//*
                            return cacheVersion;
                        }
                        return 0;
                    }
                });

        try {
            System.out.println("#########");
            System.out.println(productsCacheVersion.get("PRODUCTS_CACHE_VERSION"));
            Thread.sleep(6000);
            System.out.println(productsCacheVersion.get("PRODUCTS_CACHE_VERSION"));
            System.out.println("#########");
        } catch (ExecutionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        *//** init local cache version for comparison to memcached version **//*
        Integer cacheVersion = cacheService.get(MemcachedConstants.PRODUCTS_CACHE_VERSION.name());
        if (null != cacheVersion) {
            productsCacheVersion.put(MemcachedConstants.PRODUCTS_CACHE_VERSION.name(), cacheVersion);
        }else{
            productsCacheVersion.put(MemcachedConstants.PRODUCTS_CACHE_VERSION.name(), 0);
        }

        System.out.println(productsCacheVersion.asMap().keySet());

        logger.info("load product data to local cache finish ...");
    }*/

}
