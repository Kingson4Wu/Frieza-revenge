package com.kxw.jsoup;

import org.apache.commons.collections.CollectionUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

/**
 * <p/>
 * <br/>==========================
 * <br/> 中国文化市场网 备案： 国产游戏 进口游戏
 * <br/> 创建时间：2016年12月20日 14:12
 * <br/>==========================
 */
public class BeianAllTest {

    public static void main(String[] args) {
      /*  String url = "http://www.ccm.gov.cn/swordcms/publish/default/static/main/index.htm";
        Document doc = Jsoup.connect(url).get();

        //String result = Xsoup.compile("//a[@value=

        Elements elements = doc.select("a[href]");
        elements.forEach(ele -> {
            if(ele.childNodes().toString().indexOf("国产游戏")>0){
                System.out.println(ele);
            }
        });*/

        String guochanUrl = "http://182.131.21.139/gspt/ccm-action/yxyhyd/showAll?page=%s&business_id=052021";
        String jinkouUrl = "http://182.131.21.139/gspt/ccm-action/yxyhyd/showAll?page=%s&business_id=052011";

        int page = 1;
        boolean end = false;
        while (!end) {//调不通重试
            try {
                String url = String.format(guochanUrl, page);
                Document doc = Jsoup.connect(url).timeout(5000).get();

                Elements topTable = doc.select("div.content-top dl dt");
                Elements contentTable = doc.select("div.content-top ul li");
                /*String pageSizeStr = doc.select("div.content-bottom div.content-fy span").text();
                int pageSize =  Integer.valueOf(pageSizeStr.substring(pageSizeStr.indexOf("/")+1,pageSizeStr.indexOf("页")));//校验*/
                int lineSize = topTable.size();

                if (CollectionUtils.isNotEmpty(contentTable)) {

                    for (int i = 0, length = contentTable.size(); i < length; i++) {
                        Element ele = contentTable.get(i);

                        if (i % lineSize == 0) {
                            System.out.println();
                            System.out.print(ele.text() + " ");
                            //new Object
                            //set id
                        } else {
                           /* switch (ele.attr("id")){
                                case "xh":
                                case "bawh":
                            }*/
                            System.out.print(ele.text() + " ");
                        }

                    }


                } else {
                    end = true;
                }
                page++;
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


    }
}



