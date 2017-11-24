package com.kxw;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import com.kxw.encryption.md5.MD5Encrypt;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.jsoup.select.Elements;

public class JsFilter {

    public static void main(String[] args) {

        String str = "<html><script>function oncli(){ alert('点击');}</script><label  onclick='oncli()'/><link rel='stylesheet' type='text/css' href='css路径.css' />sjkdksc<a href='https://baidu.com' onclick='oncli()'></a><img src ='https://ddd.com'/><script \n" +
                "> ==sjdkf</\n script><script> ﾟωﾟﾉ= /｀ｍ´）ﾉ ~┻━┻   //*´∇｀*/ ['_']; o=(ﾟｰﾟ)  =_=3; c=(ﾟΘﾟ) =(ﾟｰﾟ)-(ﾟｰﾟ); (ﾟДﾟ) =(ﾟΘﾟ)= (o^_^o)/ (o^_^o);(ﾟДﾟ)={ﾟΘﾟ: '_' ,ﾟωﾟﾉ : ((ﾟωﾟﾉ==3) +'_') [ﾟΘﾟ] ,ﾟｰﾟﾉ :(ﾟωﾟﾉ+ '_')[o^_^o -(ﾟΘﾟ)] ,ﾟДﾟﾉ:((ﾟｰﾟ==3) +'_')[ﾟｰﾟ] }; (ﾟДﾟ) [ﾟΘﾟ] =((ﾟωﾟﾉ==3) +'_') [c^_^o];(ﾟДﾟ) ['c'] = ((ﾟДﾟ)+'_') [ (ﾟｰﾟ)+(ﾟｰﾟ)-(ﾟΘﾟ) ];(ﾟДﾟ) ['o'] = ((ﾟДﾟ)+'_') [ﾟΘﾟ];(ﾟoﾟ)=(ﾟДﾟ) ['c']+(ﾟДﾟ) ['o']+(ﾟωﾟﾉ +'_')[ﾟΘﾟ]+ ((ﾟωﾟﾉ==3) +'_') [ﾟｰﾟ] + ((ﾟДﾟ) +'_') [(ﾟｰﾟ)+(ﾟｰﾟ)]+ ((ﾟｰﾟ==3) +'_') [ﾟΘﾟ]+((ﾟｰﾟ==3) +'_') [(ﾟｰﾟ) - (ﾟΘﾟ)]+(ﾟДﾟ) ['c']+((ﾟДﾟ)+'_') [(ﾟｰﾟ)+(ﾟｰﾟ)]+ (ﾟДﾟ) ['o']+((ﾟｰﾟ==3) +'_') [ﾟΘﾟ];(ﾟДﾟ) ['_'] =(o^_^o) [ﾟoﾟ] [ﾟoﾟ];(ﾟεﾟ)=((ﾟｰﾟ==3) +'_') [ﾟΘﾟ]+ (ﾟДﾟ) .ﾟДﾟﾉ+((ﾟДﾟ)+'_') [(ﾟｰﾟ) + (ﾟｰﾟ)]+((ﾟｰﾟ==3) +'_') [o^_^o -ﾟΘﾟ]+((ﾟｰﾟ==3) +'_') [ﾟΘﾟ]+ (ﾟωﾟﾉ +'_') [ﾟΘﾟ]; (ﾟｰﾟ)+=(ﾟΘﾟ); (ﾟДﾟ)[ﾟεﾟ]='\\\\'; (ﾟДﾟ).ﾟΘﾟﾉ=(ﾟДﾟ+ ﾟｰﾟ)[o^_^o -(ﾟΘﾟ)];(oﾟｰﾟo)=(ﾟωﾟﾉ +'_')[c^_^o];(ﾟДﾟ) [ﾟoﾟ]='\\\"';(ﾟДﾟ) ['_'] ( (ﾟДﾟ) ['_'] (ﾟεﾟ+(ﾟДﾟ)[ﾟoﾟ]+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ (ﾟｰﾟ)+ (ﾟΘﾟ)+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ ((ﾟｰﾟ) + (ﾟΘﾟ))+ (ﾟｰﾟ)+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ (ﾟｰﾟ)+ ((ﾟｰﾟ) + (ﾟΘﾟ))+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ ((o^_^o) +(o^_^o))+ ((o^_^o) - (ﾟΘﾟ))+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ ((o^_^o) +(o^_^o))+ (ﾟｰﾟ)+ (ﾟДﾟ)[ﾟεﾟ]+((ﾟｰﾟ) + (ﾟΘﾟ))+ (c^_^o)+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟｰﾟ)+ ((ﾟｰﾟ) + (o^_^o))+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ ((ﾟｰﾟ) + (o^_^o))+ (c^_^o)+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ ((ﾟｰﾟ) + (ﾟΘﾟ))+ (ﾟΘﾟ)+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ ((o^_^o) +(o^_^o))+ ((ﾟｰﾟ) + (ﾟΘﾟ))+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ ((ﾟｰﾟ) + (ﾟΘﾟ))+ ((ﾟｰﾟ) + (ﾟΘﾟ))+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟΘﾟ)+ ((o^_^o) +(o^_^o))+ ((ﾟｰﾟ) + (ﾟΘﾟ))+ (ﾟДﾟ)[ﾟεﾟ]+(ﾟｰﾟ)+ ((ﾟｰﾟ) + (o^_^o))+ (ﾟДﾟ)[ﾟεﾟ]+((ﾟｰﾟ) + (ﾟΘﾟ))+ (ﾟΘﾟ)+ (ﾟДﾟ)[ﾟoﾟ]) (ﾟΘﾟ)) ('_'); </script></html> ";

        //System.out.println(StringEscapeUtils.escapeJavaScript(str));

        Whitelist whitelist = Whitelist.relaxed();
        whitelist.addAttributes("div", "label-module");
        //String cleanStr = Jsoup.clean(str2,whitelist);


        /*System.out.println(str2);
        System.out.println("---");
        System.out.println(cleanStr);
        System.out.println(MD5Encrypt.MD5Encode(str2));
        System.out.println(MD5Encrypt.MD5Encode(cleanStr));*/

        /*System.out.println("------");
        String aa = StringEscapeUtils.escapeHtml4(str2);
        System.out.println(aa);
        String bb = StringEscapeUtils.unescapeHtml4(aa);
        System.out.println(bb);*/


        String validStr = "<html><head></head><div label-module=\"para-title\"><h3>简介</h3></div><div label-module=\"para\">雅力士这款首战功成于欧洲，畅销于世界的丰田传奇紧凑型<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E4%B8%A4%E5%8E%A2%E8%BD%A6/8071927\" data-lemmaid=\"8071927\">两厢车</a>，自1999年上市以来，在全球的累计销量已经达到300万台，其中，在欧洲市场累计销售达到160万台。与<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%87%AF%E7%BE%8E%E7%91%9E/3174579\" data-lemmaid=\"3174579\">凯美瑞</a>相同，雅力士也是丰田的全球战略车型。在广州，国产雅力士将与凯美瑞混线生产。</div><div label-module=\"para\">雅力士搭载的1ZR 1.6L双<a target=\"_blank\" href=\"https://baike.baidu.com/item/VVT-i/589333\" data-lemmaid=\"589333\">VVT-i</a>发动机具有澎湃动力，起步和加速都较一般的小型车快得多。雅力士在D挡情况下，起步很稳，开到30--50km/h时提速也很快，有一种很明显的推背感，从加速的效果来看，我觉得雅力士的动力和我以前开过的2.0L的轿车差不多了</div><div label-module=\"para\">在丰田的参展阵容中，主要包括<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E4%B8%B0%E7%94%B0\">丰田</a>全球三大战略车型之一的雅力士、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E4%B8%80%E6%B1%BD%E4%B8%B0%E7%94%B0/6060562\" data-lemmaid=\"6060562\">一汽丰田</a>全新威驰、通过广汽<a target=\"_blank\" href=\"https://baike.baidu.com/item/TOYOTA/906712\" data-lemmaid=\"906712\">TOYOTA</a><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%B8%A0%E9%81%93%E9%94%80%E5%94%AE/8900562\" data-lemmaid=\"8900562\">渠道销售</a>的两款进口车——<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%B1%89%E5%85%B0%E8%BE%BE/6126598\" data-lemmaid=\"6126598\">汉兰达</a>和FJ Cruiser，以及Hybrid X<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%B7%B7%E5%90%88%E5%8A%A8%E5%8A%9B/1406048\" data-lemmaid=\"1406048\">混合动力</a><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%A6%82%E5%BF%B5%E8%BD%A6/296704\" data-lemmaid=\"296704\">概念车</a>等车型。</div><p>\uD83D\uDE07&nbsp; \uD83D\uDE43&nbsp; \uD83D\uDE31&nbsp;&nbsp;<br></p> </html>";

        String invalidStr = "<html><script>function oncli(){ alert('点击');}</script><head></head><div label-module=\"para-title\"><h3>简介</h3></div><div label-module=\"para\">雅力士这款首战功成于欧洲，畅销于世界的丰田传奇紧凑型<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E4%B8%A4%E5%8E%A2%E8%BD%A6/8071927\" data-lemmaid=\"8071927\">两厢车</a>，自1999年上市以来，在全球的累计销量已经达到300万台，其中，在欧洲市场累计销售达到160万台。与<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E5%87%AF%E7%BE%8E%E7%91%9E/3174579\" data-lemmaid=\"3174579\">凯美瑞</a>相同，雅力士也是丰田的全球战略车型。在广州，国产雅力士将与凯美瑞混线生产。</div><div label-module=\"para\">雅力士搭载的1ZR 1.6L双<a target=\"_blank\" href=\"https://baike.baidu.com/item/VVT-i/589333\" data-lemmaid=\"589333\">VVT-i</a>发动机具有澎湃动力，起步和加速都较一般的小型车快得多。雅力士在D挡情况下，起步很稳，开到30--50km/h时提速也很快，有一种很明显的推背感，从加速的效果来看，我觉得雅力士的动力和我以前开过的2.0L的轿车差不多了</div><div label-module=\"para\">在丰田的参展阵容中，主要包括<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E4%B8%B0%E7%94%B0\">丰田</a>全球三大战略车型之一的雅力士、<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E4%B8%80%E6%B1%BD%E4%B8%B0%E7%94%B0/6060562\" data-lemmaid=\"6060562\">一汽丰田</a>全新威驰、通过广汽<a target=\"_blank\" href=\"https://baike.baidu.com/item/TOYOTA/906712\" data-lemmaid=\"906712\">TOYOTA</a><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%B8%A0%E9%81%93%E9%94%80%E5%94%AE/8900562\" data-lemmaid=\"8900562\">渠道销售</a>的两款进口车——<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%B1%89%E5%85%B0%E8%BE%BE/6126598\" data-lemmaid=\"6126598\">汉兰达</a>和FJ Cruiser，以及Hybrid X<a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%B7%B7%E5%90%88%E5%8A%A8%E5%8A%9B/1406048\" data-lemmaid=\"1406048\">混合动力</a><a target=\"_blank\" href=\"https://baike.baidu.com/item/%E6%A6%82%E5%BF%B5%E8%BD%A6/296704\" data-lemmaid=\"296704\">概念车</a>等车型。</div><p>\uD83D\uDE07&nbsp; \uD83D\uDE43&nbsp; \uD83D\uDE31&nbsp;&nbsp;<br></p> <script>alert('kxw');</script> </html>";


        Set<String> blackTags = new HashSet<>(Arrays.asList("script", "link", "iframe"));
        Set<String> whiteTags = new HashSet<>(Arrays.asList("body", "a", "b", "blockquote", "br", "caption", "cite", "code", "col",
                "colgroup", "dd", "div", "dl", "dt", "em", "h1", "h2", "h3", "h4", "h5", "h6",
                "i", "img", "li", "ol", "p", "pre", "q", "small", "span", "strike", "strong",
                "sub", "sup", "table", "tbody", "td", "tfoot", "th", "thead", "tr", "u",
                "ul"));

        //http://www.w3school.com.cn/tags/html_ref_eventattributes.asp
        Set<String> blackAttrs = new HashSet<>(Arrays.asList("onafterprint", "onbeforeprint", "onbeforeunload", "onerror", "onhaschange",
                "onload", "onmessage", "onoffline", "ononline", "onpagehide", "onpageshow", "onpopstate", "onredo",
                "onresize", "onstorage", "onundo", "onunload", "onblur", "onchange", "oncontextmenu", "onfocus",
                "onformchange", "onforminput", "oninput", "oninvalid", "onreset", "onselect", "onsubmit", "onkeydown",
                "onkeypress", "onkeyup", "onclick", "ondblclick", "ondrag", "ondragend", "ondragenter", "ondragleave",
                "ondragover", "ondragstart", "ondrop", "onmousedown", "onmousemove", "onmouseout", "onmouseover",
                "onmouseup", "onmousewheel", "onscroll", "onabort", "oncanplay", "oncanplaythrough", "ondurationchange",
                "onemptied", "onended", "onerror", "onloadeddata", "onloadedmetadata", "onloadstart", "onpause", "onplay",
                "onplaying", "onprogress", "onratechange", "onreadystatechange", "onseeked", "onseeking", "onstalled",
                "onsuspend", "ontimeupdate", "onvolumechange", "onwaiting"));

        System.out.println(validStr.length());
        Document doc = Jsoup.parse(validStr);
        String a = doc.body().html();//.replace("\n","");
        System.out.println(a.length());
        System.out.println(MD5Encrypt.MD5Encode(a));

        Elements allElements = doc.body().getAllElements();
        for (Element ele : allElements) {

            if (!whiteTags.contains(ele.nodeName().toLowerCase())) {
                ele.remove();
                continue;
            }

            for (Attribute attr : ele.attributes()) {
                if (blackAttrs.contains(attr.getKey().toLowerCase())) {
                    ele.removeAttr(attr.getKey());
                }
            }
        }

        String b = doc.body().html();
        System.out.println(MD5Encrypt.MD5Encode(b));
        System.out.println(b);
        System.out.println(b.length());

        //html 中危险的标签和属性列表

    }
}
