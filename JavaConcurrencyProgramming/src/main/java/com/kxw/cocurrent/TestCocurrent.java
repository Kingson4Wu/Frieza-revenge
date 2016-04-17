package com.kxw.cocurrent;

/**
 * Created by kxw on 2016/1/5.
 */
public class TestCocurrent {


   /* private ExecutorService exec = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);
    private final String apiPath="/abcs-mobile-brand-latest-api/rest/reloadCache?id=";
    @Override
    public Map<String, String> reload(int cacheType,String apiServerIds) {


        int a=CacheTypeConstants.BRAND_DETAIL;
        apiServerIds="localhost:8080";
        final String url=apiPath+cacheType;
        final Map<String, String> resultMap=new HashMap<String, String>();
        String[] apiServers=apiServerIds.split(",");

        final CountDownLatch latch = new CountDownLatch(apiServers.length);
        for(final String apiHost : apiServers){
            resultMap.put(apiHost, "-1");//not finish flag :-1

            exec.submit(new Runnable(){
                @Override
                public void run() {
                    try{
                        String requestUrl=apiHost+url;
                        String result=HttpClientUtils.doGet(requestUrl, "UTF-8", 10);

                        resultMap.put(apiHost, result);

                    }catch(Exception e){
                        logger.error("request api server failed,apiServerIp:{}",apiHost, e);
                    }finally{
                        latch.countDown();
                    }
                }
            });
        }


        try {
            latch.await(5, TimeUnit.MINUTES);
        } catch (InterruptedException e) {
            logger.warn("Interuppted while waiting completion of all reload tasks, maybe some of the reloading task not finished!");
            return resultMap;
        }

        return resultMap;
    }*/



    //使用了future之后：

    /*for(final String apiHost : apiServers){
        //resultMap.put(apiHost, "-1");//not finish flag :-1

        Future<String> future=	exec.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                String requestUrl="http://"+apiHost+url;
                String result=HttpClientUtils.doGet(requestUrl, "UTF-8", 20);//Timeout:20 seconds
                return result;
            }
        });

        try {
            resultMap.put(apiHost, future.get(5, TimeUnit.SECONDS));//Timeout:5 seconds
        } catch (Exception e) {
            resultMap.put(apiHost,"0");
            logger.error("Reload cache failed ,apiServer:{},cacheTpe:{}",apiHost,cacheType);
        }*/

        //感觉没必要用CountDownLatch

}
