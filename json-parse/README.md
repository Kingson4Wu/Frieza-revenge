+ com.thoughtworks.xstream.annotations
  java 对象和xml的装换
  <http://www.blogjava.net/DLevin/archive/2012/11/30/392240.html>


简化JSON处理的开发库。

Genson：强大且易于使用的Java到JSON转换开发库。
Gson：支持在对象与JSON之间双向序列化，性能良好且可以实时调用。
Jackson：与GSON类似，在频繁使用时性能更佳。
LoganSquare：基于Jackson流式API，提供对JSON解析和序列化。比GSON与Jackson组合方式效果更好。
Fastjson: 一个Java语言编写的高性能功能完善的JSON库。它采用一种“假定有序快速匹配”的算法，把JSON Parse的性能提升到极致，
是目前Java语言中最快的JSON库。Fastjson接口简单易用，已经被广泛使用在缓存序列化、协议交互、Web输出、Android客户端等多种应用场景。
在正常情况下，Fastjson 确实解析很快，一旦并发量上来，就会越来越吃内存，甚至JVM很快出现内存溢出。原因呢，很简单，Fastjson设计初衷是：先把整个数据装载到内存，然后解析，所以执行很快，但很费内存。

