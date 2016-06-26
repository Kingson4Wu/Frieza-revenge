package com.kxw.webmagic;

import com.kxw.webmagic.pipeline.KingsonPipeline;
import org.junit.Test;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.scheduler.RedisScheduler;

/**
 * Created by kingsonwu on 16/6/26.
 */
public class WebMagicTest {


    @Test
    public void simpleTest(){


        String path =  this.getClass().getClassLoader().getResource("").getPath();
        System.out.println(path);

        Spider.create(new KingsonPageProcessor())
                //从https://github.com/code4craft开始抓
                .addUrl("http://blog.csdn.net/kingson_wu/")
                        //设置Scheduler，使用Redis来管理URL队列
                        //.setScheduler(new RedisScheduler("localhost"))
                        //设置Pipeline，将结果以json方式保存到文件
                .addPipeline(new JsonFilePipeline(path + "webmagic"))
                        //开启5个线程同时执行
                .addPipeline(new ConsolePipeline())
                .addPipeline(new KingsonPipeline())
                .thread(5)
                        //启动爬虫
                .run();


    }
}
