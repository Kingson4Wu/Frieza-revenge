package com.kxw.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by kingsonwu on 16/6/26.
 */
public class HappyjuziArticlePageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {

        List<String> itemList = page.getHtml().xpath("//div[@class='left']/div[@class='ordinary']").all();
        List<String> leftOrdinaryList = page.getHtml().xpath("//div[@class='left']/div[@class='ordinary']/div[@class='left_ordinary']/a").links().all();
        System.out.println("size: " + leftOrdinaryList.size());
        for (String item : leftOrdinaryList) {
            System.out.println(item);
            //把全部url先保存到redis，接触下来把url拿出来继续解析
            System.out.println("--------------");
        }

        String title = page.getHtml().xpath("//div[@class='detail-title']/h1/text()").toString();
        String tag = page.getHtml().xpath("//div[@class='juzi-tag']/a/text()").toString();
        String author = page.getHtml().xpath("//div[@class='juzi-author']/text()").toString();

        String photo = page.getHtml().xpath("//div[@id='juzi_img']/p/img/@src").toString();
        String content = page.getHtml().xpath("//div[@id='juzi_content']").get();

        page.putField("title", title);
        page.putField("tag", tag);
        page.putField("author", author);
        page.putField("photo", photo);
        page.putField("content", content);


    }

    @Override
    public Site getSite() {
        return site;
    }

}
