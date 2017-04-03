+ Java 9 的这一基本功能，你可能从未听过:<www.iteye.com/news/32031>

<pre>

2017年7月即将发布的Java 9将会新增和修订不少功能和特性。在搜索JDK增强提议（JEP）时发现，JEP 266对CompletableFuture进行了一些有趣的改进，更新并发功能和支持Reactive Streams。

本文的焦点就是JEP 266的改进和一些有趣的特性，它极有可能成为Java 9中最常用的特性之一。

JEP 266改进计划

JEP 266是Oracle草拟JDK增强协议里面的一员，其中对并发进行了大量的更新。根据提案，将要进行更新的特性有：

支持Reactive Streams发布-订阅框架接口

对CompletableFuture API进行更新，例如支持延迟、超时、子类化和其它方法

其它一般改进，例如小调整和javadoc规范重写

本文重点关注前两个改进。首先，Java 9将支持Reactive Streams，这是一个来自Java社区的改进计划，旨在改进开发人员的并发工作流程。其次是CompletableFuture API提供的几个fixer-uppers，可以让开发人员回到Future<T>。

Flow类

Reactive Streams发布-订阅框架里面将嵌套一个Flow类，以及开发人员可以用来创建自定义组件使用的SubmissionPublisher。

先从最上面的说起，Reactive Streams主要解决背压（back-pressure）问题。当传入的任务速率大于系统处理能力时，数据处理将会对未处理数据产生一个缓冲区。

与此同时，我们还与Akka团队的高级开发人员Konrad Malawski进行了沟通，他是Reactive Streams计划的领导者，解释了Reactive Streams的重要性以及如何使用。

Oracle指出，新的增强将包括“一个很小的接口，其定义符合（来自Reactive Stream initiative的）广泛参与”，这就是Flow类的来源。

Reactive Streams由4个Java接口构成：

处理器（Processor）
发布商（Publisher）
订阅用户（Subscriber）
订阅（Subscription）

Flow类允许相互关联的接口和静态方法来建立流控制组件，其中发布者产生由一个或多个订阅者消费的项目，每个订阅者由订阅管理。

Reactive Streams构建在java.util.concurrent.Flow容器对象下，开发者可以在这里找到Flow.Publisher，一个用作lambda表达式或方法引用的赋值目标功能接口。该接口可以让开发者更容易生成Flow.Subscription元素，并且将它们链接在一起。

另一个元素Flow.Subscriber，是异步工作机制，由请求触发。它可以从Flow.Subscription请求多个元素，开发者还可以根据需要自定义缓冲区大小。

这些接口适用于并发和分布式异步设置，它们之间的通信依赖于一种简单的流控制形式，可用于避免资源管理问题。

感兴趣的用户可以在java.util.concurrent.Flow（http://gee.cs.oswego.edu/dl/concurrency-interest/index.html/）下面查看代码示例。

目前已有一些第三方库（https://github.com/reactive-streams/reactive-streams-jvm/）实现了Reactive Streams接口，你无需等到Java 9发布便可尝试一下。

CompletableFuture新特性

Java 8引进了CompletableFuture，继承自Future<T>。Futures是非常有用的，当我们不希望或者不需要一个直接计算结果的时候，我们会收到一个Future对象来保存计算完成时分配的实际结果。通过调用complete()方法并且无需异步等待即可显式完成。它还允许在一系列操作中构建管道数据流程。

这样，任何类型的可用值都可以在Future中使用默认返回值，即使计算没有完成。这也将成为CompletableFuture提案更新的一部分，包括延迟和超时、更好地支持子类化和一些实用方法。

更多CompletableFuture功能改进和提案，大家可以前往这里（http://blog.takipi.com/back-to-the-completablefuture-java-8-feature-highlight/）查看。

总结

Java 9即将带来一些重大的更新与改进，其中JEP 266会是最有趣的一部分改进。它的改进方案主要来自社区的一些倡议，旨在帮助开发人员解决异步处理方面的一些重大问题。

<pre>