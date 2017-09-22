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

gRPC

gRPC总结
跨语言，针对移动端：省电、省流量、高性能、双向流、支持DNS负载。关于性能，肯定比HTTP/1好，比TCP差，网上好多性能对比，
都是和TCP相关的RPC对比，没有可比性。

---

