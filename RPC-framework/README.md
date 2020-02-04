Motan（https://github.com/weibocom/motan）

是微博技术团队研发的基于Java的轻量级RPC框架，已在内部大规模应用多年，每天稳定支撑微博上亿次的内部调用。

Motan是一套基于java开发的高性能、易于使用的分布式远程服务调用(RPC)框架，除了常规的点对点调用外，motan还提供服务治理功能，包括服务节点的自动发现、摘除、高可用和负载均衡等。Motan具有良好的扩展性，主要模块都提供了多种不同的实现，例如支持多种注册中心，支持多种rpc协议等。

功能
支持通过spring配置方式集成，无需额外编写代码即可为服务提供分布式调用能力。
支持集成consul、zookeeper等配置服务组件，提供集群环境的服务发现及治理能力。
支持动态自定义负载均衡、跨机房流量调整等高级服务调度能力。
基于高并发、高负载场景进行优化，保障生产环境下RPC服务高可用。

百度正式开源其RPC框架brpc:<https://mp.weixin.qq.com/s/WsXnXtK-RLy9ZJQDReSrog>
,<https://github.com/brpc/brpc>
-------------------------------

zeroc ice: <https://zeroc.com/products/ice>

ZEROC ICE 跨平台间程序调用 java版:<http://www.tuicool.com/articles/nYJVj2R>
<https://github.com/zeroc-ice/ice-demos/tree/master/java>

《ZeroC Ice 权威指南》笔记: <http://blog.csdn.net/jerome_s/article/details/50821719>

---

### gRPC
+ gRPC总结
跨语言，针对移动端：省电、省流量、高性能、双向流、支持DNS负载。关于性能，肯定比HTTP/1好，比TCP差，网上好多性能对比，
都是和TCP相关的RPC对比，没有可比性。

+ 我们为什么从REST转向gRPC: <https://mp.weixin.qq.com/s?__biz=MjM5MDE0Mjc4MA==&mid=2651017092&idx=2&sn=c0f928e2815ebcbab42f83f9fa8c8147&chksm=bdbeb7d78ac93ec1819591df3f2509473deb7e09ba56a19ab40aef17d350f22459f979d27a7c&xtrack=1&scene=90&subscene=93&sessionid=1561270344&clicktime=1561271269&ascene=56&devicetype=android-28&version=27000435&nettype=WIFI&abtest_cookie=BQABAAoACwASABMAFQAGACOXHgBWmR4AyJkeANyZHgDzmR4ACZoeAAAA&lang=zh_CN&pass_ticket=3Fb3H575ITWdjIUeli2ynyPc1W6RT6kfDRzEybnd1bBG7l32eidgWw8dFhiksdW7&wx_header=1>

#### 流式传输
<pre>
今年早些时候，我开始为我们的搜索服务设计一个新的 API。在我使用 JSON/HTTP 设计了第一版 API 之后，我的一个同事告诉我说，在某些情况下，我们需要流式传输搜索结果，也就是在有第一批结果时就开始传输。而我之前设计的 API 只返回一个单独的 JSON 数组，在服务器端收集到所有结果之前是不会向客户端发送任何数据的。

我们的 API 要求客户端轮询搜索结果，先是发送一个 POST 请求发起搜索，然后再不断发送 GET 请求获取搜索结果。响应消息中包含了一个用于表示搜索是否已完成的字段。这种方式虽然没有什么问题，但还不够优雅，而且要求服务器端将中间结果保存在数据存储（如 Redis）中。

这个时候，我们决定试一试 gRPC。要通过 gRPC 发送结果，只需要在.proto 文件中加入 stream 关键字。下面是我们的 Search 函数定义：

rpc Search (SearchRequest) returns (stream Trip) {}
使用 protoc 编译器生成的代码中包含了一个对象，这个对象有一个 Send 函数，我们的服务器端代码将调用这个函数将 Trip 对象一个接一个地发送出去。代码中还包含了一个 Recv 函数，客户端代码通过调用这个函数来接收 Trip 对象。从开发者的角度来看，这比实现轮询 API 要简单得多。
</pre>
#### 注意事项
+ 如果我们使用 JSON/HTTP 开发 API，就可以使用 curl、httpie 或者 Postman 进行简单的手动测试。gRPC 也有一个类似的工具叫作 grpcurl，不过它使用起来并不是很方便，你要么需要在服务器端添加 gRPC 服务器反射插件，要么需要在每个命令后面附上.proto 文件。
+ 另一个是 Kubernetes 负载均衡器问题，负载均衡器可以支持 HTTP，但对 gPRC 支持得并不好。gPRC 要求应用层的负载均衡，而不是在 TCP 连接层。为了解决这个问题，我们参考了这篇文章搭建了 Linkerd：
 https://kubernetes.io/blog/2018/11/07/grpc-load-balancing-on-kubernetes-without-tears/

+ 尽管开发 gRPC API 在前期需要做更多的工作，但拥有清晰的 API 定义和对流式传输的支持对我们来说更重要。在构建新的内部服务时，gRPC 将会是我们的首选。
 
---

