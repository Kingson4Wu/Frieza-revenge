package com.kxw.webmagic;

import org.junit.Test;
import us.codecraft.webmagic.Spider;

public class HappyjuziTest {

    @Test
    public void simpleTest() {

        String path = this.getClass().getClassLoader().getResource("").getPath();
        System.out.println(path);

        String[] urls = new String[10];
        for (int i = 1; i <= 10; i++) {
            urls[i-1] = "http://www.happyjuzi.com/movie/p" + i;
        }

        Spider spider = Spider.create(new HappyjuziPageProcessor())
                .addUrl(urls)
                //.addPipeline(new HappyjuziPipeline())
                //开启5个线程同时执行
                .thread(5);

        if (!Spider.Status.Running.equals(spider.getStatus())) {
                spider.start();
            //spider.run();
        }

      /*  String articleUrl = "http://www.happyjuzi.com/movie/77189.html";
        Spider.create(new HappyjuziArticlePageProcessor())
                .addUrl(articleUrl)
                //.addPipeline(new HappyjuziPipeline())
                //开启5个线程同时执行
                .thread(5)
                .run();*/

    }

}
