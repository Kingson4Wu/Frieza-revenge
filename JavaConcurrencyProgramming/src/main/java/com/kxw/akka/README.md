<http://akka.io/downloads/>
<http://www.gtan.com/akka_doc/> -- 过时了,直接看最新官方问的文档好了
<http://www.ibm.com/developerworks/cn/java/j-jvmc5/index.html>
<http://doc.akka.io/docs/akka/2.4.1/java.html>

我们相信编写出正确的具有容错性和可扩展性的并发程序太困难了。这多数是因为我们使用了错误的工具和错误的抽象级别。
Akka就是为了改变这种状况而生的。通过使用Actor模型我们提升了抽象级别，为构建正确的可扩展并发应用提供了一个更好的平台。
在容错性方面我们采取了“let it crash”（让它崩溃）模型，人们已经将这种模型用在了电信行业，构建出“自愈合”的应用和永不停机的系统，
取得了巨大成功。Actor还为透明的分布式系统以及真正的可扩展高容错应用的基础进行了抽象。

模块
Akka的模块化做得非常好，它为不同的功能提供了不同的Jar包。

akka-actor-2.0.jar – 标准Actor, 有类型Actor，等等
akka-remote-2.0.jar – 远程Actor
akka-slf4j-2.0.jar – SLF4J事件处理监听器
akka-testkit-2.0.jar – 用于测试Actor的工具包
akka-kernel-2.0.jar – Akka微内核，可运行一个基本的最小应用服务器
akka-<storage-system>-mailbox-2.0.jar – Akka可容错邮箱
要查看每个Akka模块的jar包依赖见 依赖 章节. 虽然不重要不过akka-actor 没有外部依赖 (除了 scala-library.jar JAR包).

<https://github.com/akka/akka/tree/master/akka-samples>


Actor 模型基础知识
用于并发计算的 actor 模型基于各种称为 actor 的原语来构建系统。Actor 执行操作来响应称为消息 的输入。这些操作包括更改 actor 自己的内部状态，以及发出其他消息和创建其他 actor。所有消息都是异步交付的，因此将消息发送方与接收方分开。正是由于这种分离，导致 actor 系统具有内在的并发性：可以不受限制地并行执行任何拥有输入消息的 actor。
在 Akka 术语中，actor 看起来就像是某种通过消息进行交互的行为神经束。像真实世界的演员一样，Akka actor 也需要一定程度的隐私。您不能直接将消息发送给 Akka actor。相反，需要将消息发送给等同于邮政信箱的 actor 引用。然后通过该引用将传入的消息路由到 actor 的邮箱，以后再传送给 actor。Akka actor 甚至要求所有传入的消息都是无菌的（或者在 JVM 术语中叫做不可变的），以免受到其他 actor 的污染。
Akka 高级知识
Akka 通过一些可提高灵活性的诀窍来实现 actor 模型。一个诀窍是就是完全分布式架构，允许 actor 系统分散在网络中的多个节点上。Akka 还使用一种分层的 actor 排列，其中每个 actor（除了系统根管理程序）有一个父 actor 负责处理子 actor 的任何故障。这些都是 Akka 的重要特性，但也是高级主题，所以这里只是简单介绍一下。
与一些真实世界中演员的需求不同，Akka 中由于某种原因而存在一些看似强制要求的限制。使用 actor 的引用可阻止交换消息以外的任何交互，这些交互可能破坏 actor 模型核心上的解耦本质。Actor 在执行上是单线程的（不超过 1 个线程执行一个特定的 actor 实例），所以邮箱充当着一个缓冲器，在处理消息前会一直保存这些消息。消息的不可变性（由于 JVM 的限制，目前未由 Akka 强制执行，但这是一项既定的要求）意味着根本无需担心可能影响 actor 之间各种共享的数据的同步问题；如果只有共享的数据是不可变的，那么根本不需要同步。

---
### actor模型
+ 以Akka为示例，介绍Actor模型: <http://www.infoq.com/cn/news/2014/11/intro-actor-model/>
+ Java并发的四种风味：Thread、Executor、ForkJoin和Actor:<http://www.importnew.com/14506.html>
+ Actor模型的本质：究竟是要解决什么问题:<http://developer.51cto.com/art/200908/141595.htm>
+ Actor模型:<http://blog.csdn.net/gulianchao/article/details/7249117>
+ 为什么Actor模型是高并发事务的终极解决方案？<http://www.jdon.com/45728>
