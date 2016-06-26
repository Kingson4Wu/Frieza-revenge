package com.kxw.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by kingsonwu on 16/6/26.
 */
public class QQPageProcesser implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);


    @Override
    public void process(Page page) {


        page.addTargetRequests(page.getHtml().links().regex("(http://info\\.3g\\.qq\\.com/g/s\\w+)").all());
        page.putField("title", page.getHtml().xpath("//*[@id='img-content']/h2[1]/text()").toString());
        page.putField("type", page.getHtml().xpath("//*[@id='img-content']/div[1]/text()").toString());


    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        String url = "http://info.3g.qq.com/g/channel_home.htm?chId=sports_nch";
        String url2 = "http://info.3g.qq.com/g/s?f_pid=131&newchannel=sports_nch&newpos=sports_1_tab&aid=wechat_ss&id=wechat_20160626014168&f_l=2710";

        Spider.create(new QQPageProcesser())
                //从https://github.com/code4craft开始抓
                .addUrl(url2)
                .addPipeline(new ConsolePipeline())
                .thread(5)
                .run();


    }
}
