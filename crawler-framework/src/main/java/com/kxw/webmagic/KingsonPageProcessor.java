package com.kxw.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by kingsonwu on 16/6/26.
 */
public class KingsonPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    /**
     * doc selected api :<a href ='http://webmagic.io/docs/zh/posts/ch4-basic-page-processor/selectable.html'>@link</a>
     * @param page
     */

    @Override
    public void process(Page page) {
        //page.addTargetRequests(page.getHtml().links().regex("(http://blog\\.csdn\\.net/\\w+/\\w+)").all());
        page.putField("author", page.getUrl().regex("http://blog\\.csdn\\.net/(\\w+)/.*").toString());
        page.putField("description", page.getHtml().xpath("//div[@id='blog_title']/h3/text()").toString());
        /*if (page.getResultItems().get("name")==null){
            //skip this page
            page.setSkip(true);
        }*/
        /* page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));*/
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {

    }
}
