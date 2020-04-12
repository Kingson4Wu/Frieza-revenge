+ Java JDK：Observable和Observer
+ Guava:EventBus
    + 使用Guava之后, 如果要订阅消息, 就不用再继承指定的接口, 只需要在指定的方法上加上@Subscribe注解即可
    + 在工作中，经常会遇见使用异步的方式来发送事件，或者触发另外一个动作：经常用到的框架是MQ（分布式方式通知）。如果是同一个jvm里面通知的话，就可以使用EventBus。
    + 注册监听者:底层就是将类eventListener中所有注解有Subscribe的方法与其Event对放在一个map中（一个event可以对应多个Subscribe的方法）
        - @VisibleForTesting, @GwtCompatible??? 看源码
    + AsyncEventBus是异步的EventBus，那么EventBus应该就是同步的了  
    + https://cloud.tencent.com/developer/article/1377032  

+ <https://www.cnblogs.com/peida/p/EventBus.html>


+ EventBus 原理深度解析:<https://mp.weixin.qq.com/s/CSmtpJAHAsaBCnIvcHdt8g>, https://my.oschina.net/yangjianzhou/blog/2208677