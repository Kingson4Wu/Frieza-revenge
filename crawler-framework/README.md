+ ATA 爬虫平台架构历程

---

+ 初探 Headless Chrome：<https://mp.weixin.qq.com/s/sQRRoDVh0a-8N6FWNLsMVw>

##### 什么是 Headless Chrome
Headless Chrome 是 Chrome 浏览器的无界面形态，可以在不打开浏览器的前提下，使用所有 Chrome 支持的特性运行你的程序。
相比于现代浏览器，Headless Chrome 更加方便测试 web 应用，获得网站的截图，做爬虫抓取信息等。
相比于出道较早的 PhantomJS，SlimerJS 等，Headless Chrome 则更加贴近浏览器环境。

<https://juejin.im/entry/58fd5e645c497d005803b6a4>
<https://www.google.com/chrome/browser/beta.html?platform=linux>
Headless Chrome 入门:<https://mp.weixin.qq.com/s/QsO-B2qQy2rIKNHSfbiyRg>


#### 网络爬虫
+ <https://github.com/yahoo/anthelion>
+ Apache Nutch：可扩展可伸缩的Java 网络爬虫 <https://github.com/apache/nutch>
Nutch可以自动发现网页超链接，减少很多维护工作，比如检查坏链接，为所有访问过的页面建立拷贝进行搜索。
+ crawler4j：轻量级多线程网络爬虫 <https://github.com/yasserg/crawler4j>
