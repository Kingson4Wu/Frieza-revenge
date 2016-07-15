package com.kxw.webmagic;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;


public class ToutiaoPageProcessor implements PageProcessor{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(3000);
    private static final String DETAIL_URL = "http://toutiao.com/group";
    private static final String LIST_URL_PATTERN = "http://toutiao.com/api/article/recent/?source=2&category=news_entertainment";
    private static final String LIST_URL = "http://toutiao.com/api/article/recent/?source=2&category=news_entertainment&count=20&max_behot_time=%s&offset=0";

    @Override
    public void process(Page page) {
        String maxBehotTime = "";

        if (page.getUrl().get().contains(LIST_URL_PATTERN)) {
            //如果是第二页，则已经是通过api去获取的，直接通过jsonpath去解析得到下一个请求需要用到的maxBehotTime
            maxBehotTime = page.getJson().jsonPath("next.max_behot_time").get();
            //获取所有详情页面
            List<String> urlList = page.getJson().jsonPath("data[*].article_url").all();
            List<String> titleList = page.getJson().jsonPath("data[*].title").all();
            List<String> abstractList = page.getJson().jsonPath("data[*].abstract").all();
            List<String> pubTimeList = page.getJson().jsonPath("data[*].publish_time").all();

            for (int i = 0;i < urlList.size(); i ++) {
                if (urlList.get(i).contains(DETAIL_URL)) {
                    page.addTargetRequest(urlList.get(i));
                    System.out.println("url:" + urlList.get(i) + ",title:" + titleList.get(i) + ",abstract:" + abstractList.get(i) + ",pubTime:" + pubTimeList.get(i));
                }
            }
        } /*else if (page.getUrl().get().contains(DETAIL_URL)){
            //详情页有两种情况，一种是图集（因为图集的描述是放在JS动态显示的，处理起来难度比较大，暂时不处理），一种是文字，需要区分对待
            String title = page.getHtml().xpath("//div[@class='article-header']/h1/text()").get();
            if (StringUtils.isNotEmpty(title)) {
                String pubTime = page.getHtml().xpath("//div[@class='article-header']//span[@class='time']/text()").get();
                List<String> tagList = page.getHtml().xpath("//ul[@class='tag-list']/li/a/text()").all();
                System.out.println("title:" + title + ", pubTime:" + pubTime + ",tagList:" + tagList);
            }
        }*/

        //增加列表页的爬取
        page.addTargetRequest(String.format(LIST_URL, maxBehotTime));
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new ToutiaoPageProcessor()).addUrl("http://toutiao.com/api/article/recent/?source=2&category=news_entertainment").thread(5).run();
    }
}
