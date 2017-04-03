RxJava 是一个响应式编程框架，采用观察者设计模式。 Observable 和 Subscriber
<https://github.com/ReactiveX/RxJava/wiki/How-To-Use-RxJava>
http://square.github.io/retrofit/


<http://blog.csdn.net/lzyzsd/article/details/41833541>
RxJava最核心的两个东西是Observables（被观察者，事件源）和Subscribers（观察者）。Observables发出一系列事件，Subscribers处理这些事件。这里的事件可以是任何你感兴趣的东西（触摸事件，web接口调用返回的数据。。。）
一个Observable可以发出零个或者多个事件，知道结束或者出错。每发出一个事件，就会调用它的Subscriber的onNext方法，最后调用Subscriber.onNext()或者Subscriber.onError()结束。

Rxjava的看起来很想设计模式中的观察者模式，但是有一点明显不同，那就是如果一个Observerble没有任何的的Subscriber，那么这个Observable是不会发出任何事件的。

---

理解RxJava的线程模型:<http://colobu.com/2016/07/25/understanding-rxjava-thread-model/>

---

RxJava – JVM 的 Reactive Extensions (响应式扩展) – 一个用于 Java VM 的库，它通过可观测序列构成异步及基于事件的程序。

它扩展了观察者模式以支持数据/事件流，并添加了操作符，使你能以申明的方式组合处理序列，对一些事情进行抽象，比如低级线程、同步、线程安全和并发数据结构。

RxJava 常见的应用是在后台线程运行一些计算或网络请求，并在 UI 线程显示结果(或错误)

