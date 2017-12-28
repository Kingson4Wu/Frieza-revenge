
### coding
1. 改了类字段要手动clean build一下，tomcat插件不能热部署  <br/>
   项目编码不一致，json转换会有问题
2. System.getProperties().put("http.proxyHost","127.0.0.1");  <br/>
   System.getProperties().put("http.proxyPort","8888");
3. httpclient 添加代理
   ```
           //创建HttpClientBuilder
           HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
           //HttpClient
           CloseableHttpClient closeableHttpClient = httpClientBuilder.build();

           HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");
           RequestConfig config = RequestConfig.custom().setProxy(proxy).build();

           HttpGet httpGet = new HttpGet(url);
           httpGet.setConfig(config);

           //4.1.3
           httpClient.getParams().setParameter(ConnRouteParams.DEFAULT_PROXY, new HttpHost("100.84.250.97",8888, null))
   ```
3. 枚举转化  <br/>
   `abc.setAppType(AppType.valueOf(((String) Map.get("app_type")).toUpperCase()));`  <br/>
   `CodeConstants.LIST_SWITCH.ordinal();`
4.
```java
do{
break;
}while(false);
```
5. `request.getHeader("HTTP_CDN_SRC_IP");`   <br/>
   `request.getHeader("X-Forwarded-For");`
6. Arrays.asList(object);
7.  response.setHeader("ContentType", "text/html");
    response.setCharacterEncoding("UTF-8");
    response.getWriter().write(text);/*输出提示信息*/

8. `<? super T>`包括T在内的任何T的父类，`<? extends T>`表示包括T在内的任何T的子类  </br>
请记住PECS原则：生产者（Producer）使用extends，消费者（Consumer）使用super。
9. 只要是在方法内定义使用StringBuilder,完全不会有线程安全问题.
10. `@Scope("prototype")`  </br>
    最佳实践：  </br>
    1、不要在controller中定义成员变量。  </br>
    2、万一必须要定义一个非静态成员变量时候，则通过注解@Scope("prototype")，将其设置为多例模式。  
11. 多种条件判断可以在入口处全部判断完，用一个boolean值设置，后面根据这个布尔值判断即可。
12. `request.getRequestURI().substring(request.getContextPath().length())`
13. 怎样处理InterruptedException<http://www.importnew.com/17027.html>  <br/>
<http://www.ibm.com/developerworks/library/j-jtp05236/>  <br/>
```
try {
  Thread.sleep(100);
} catch (InterruptedException ex) {
  Thread.currentThread().interrupt(); // Here!
  throw new RuntimeException(ex);
}
```
14. Comparable一般表示类的自然序，比如定义一个Student类，学号为默认排序  </br>
Comparator一般表示类在某种场合下的特殊分类，需要定制化排序。比如现在想按照Student类的age来排序
15. 没有javadoc，就是当前方法没有自己的注释
    @see就是，用@see后面跟的类的方法的注释作为当前方法的注释
    
    例如可以这么写：
    /* (non-Javadoc)
    * @see com.xiaoxiang.common.util.IPageSupport#hasPreviousPage()
    */
16. Java 7 新的 try-with-resources 语句，自动资源释放,前提是，这些可关闭的资源必须实现 java.lang.AutoCloseable 接口
17. 不可变对象对多线程有什么帮助
    不可变对象保证了对象的内存可见性，对不可变对象的读取不需要进行额外的同步手段，提升了代码执行效率。
18. 由于Java采用抢占式的线程调度算法，因此可能会出现某条线程常常获取到CPU控制权的情况，为了让某些优先级比较低的线程也能获取到CPU控制权，
可以使用Thread.sleep(0)手动触发一次操作系统分配时间片的操作，这也是平衡CPU控制权的一种操作。
19. 高并发、任务执行时间短的业务怎样使用线程池？并发不高、任务执行时间长的业务怎样使用线程池？并发高、业务执行时间长的业务怎样使用线程池？
    这是我在并发编程网上看到的一个问题，把这个问题放在最后一个，希望每个人都能看到并且思考一下，因为这个问题非常好、非常实际、非常专业。关于这个问题，个人看法是：
    （1）高并发、任务执行时间短的业务，线程池线程数可以设置为CPU核数+1，减少线程上下文的切换
    （2）并发不高、任务执行时间长的业务要区分开看：
    　　a）假如是业务时间长集中在IO操作上，也就是IO密集型的任务，因为IO操作并不占用CPU，所以不要让所有的CPU闲下来，可以加大线程池中的线程数目，让CPU处理更多的业务
    　　b）假如是业务时间长集中在计算操作上，也就是计算密集型任务，这个就没办法了，和（1）一样吧，线程池中的线程数设置得少一些，减少线程上下文的切换
    （3）并发高、业务执行时间长，解决这种类型任务的关键不在于线程池而在于整体架构的设计，看看这些业务里面某些数据是否能做缓存是第一步，增加服务器是第二步，至于线程池的设置，设置参考（2）。
    最后，业务执行时间长的问题，也可能需要分析一下，看看能不能使用中间件对任务进行拆分和解耦。
    <http://www.cnblogs.com/xrq730/p/5060921.html>
20. <code>
        tag.setCreateTime(createTime);
        tag.setModifyTime(createTime);//防止应用服务器的时间和数据库服务器的时间不一致
    </code>
21. 警告：在webapp服务器上，可能会保持一个线程池，那么ThreadLocal变量会在响应客户端之前被移除，因为当前线程可能被下一个请求重复使用。
而 且，如果在使用完毕后不进行清理，它所保持的任何一个对类的引用—这个类会作为部署应用的一部分加载进来—将保留在永久堆栈中，永远不会被垃圾回收机制回收。    
    