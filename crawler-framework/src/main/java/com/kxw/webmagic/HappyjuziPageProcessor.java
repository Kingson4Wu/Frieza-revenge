package com.kxw.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

/**
 * Created by kingsonwu on 16/6/26.
 */
public class HappyjuziPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    /**
     * doc selected api :<a href ='http://webmagic.io/docs/zh/posts/ch4-basic-page-processor/selectable.html'>@link</a>
     * @param page
     */

    @Override
    public void process(Page page) {
        //page.addTargetRequests(page.getHtml().links().regex("(http://blog\\.csdn\\.net/\\w+/\\w+)").all());
        //page.putField("item", page.getHtml().xpath("/html/body/div[2]/div[2]").toString());

        List<String> itemList = page.getHtml().xpath("//div[@class='left']/div[@class='ordinary']").all();
        List<String> leftOrdinaryList = page.getHtml().xpath("//div[@class='left']/div[@class='ordinary']/div[@class='left_ordinary']/a").links().all();
        System.out.println("size: " + leftOrdinaryList.size());
        for (String item : leftOrdinaryList){
            System.out.println(item);
            //把全部url先保存到redis，接触下来把url拿出来继续解析
            System.out.println("--------------");
        }

        //page.putField("item", page.getHtml().xpath("///div[@class='left']").all());

        //page.putField("url", page.getUrl().regex("http://blog\\.csdn\\.net/(\\w+)/.*").toString());
        //page.putField("description", page.getHtml().xpath("//div[@id='blog_title']/h3/text()").toString());
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

}
