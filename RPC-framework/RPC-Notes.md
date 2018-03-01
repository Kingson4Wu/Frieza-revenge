
---
PRC调用序列化方式有xml，json，Hessian，protobuffer，thrift等。其中为什么protobuffer，thrift的序列化有中间描述文件xxx.thrift，xxx.proto,而Hessian却没有？
<http://blog.csdn.net/jiyiqinlovexx/article/details/17383183>
RPC框架关键在于将对接口方法的调用转换为TCP/HTTP请求，并等待响应返回。要不要中间文件对实现RPC框架没有太大的影响，hessian为什么没有我猜测是想做的简单，利用语言内置的动态代理技术就可以完成接口调用到HTTP请求的转换。有中间文件只是开发起来更加便捷，双方不用约定好接口(当然如果服务方提供客户端端调用sdk，那就不用约定了)。

---
Protobuffer和json深度对比
<http://cxshun.iteye.com/blog/1974498>
在数据量大的时候GPB是非常占优势的，但一般情况下客户端和服务端并不会直接进行这么大数据的交互，大数据主要发生在服务器端的传输上，如果你面对需求是每天需要把几百M的日志文件传到另外一台服务器，那么这里GPB可能就能帮你的大忙了。

 ---
 跨语言RPC框架Hessian、Thrift、Protocol Buffer之间的选择
<http://blog.csdn.net/jiyiqinlovexx/article/details/17383183>
总结在几者之间选择的考量：
1. 如果你不需要很多语言相互调用， 希望保持清晰的java接口代码（无任何业务不相关的接口继承和方法，属性定义），减少开放工作量，推荐Hessian。
2. 如果你的系统之间传输的数据量不是很大(<2M都不算大)， 推荐Hessian。
3. 如果需要支持大数据量的传输，多语言调用，极高的并发支持，推荐使用thrift/protocol buffer。 通常我们并发很难超过1000 req/s，如果超过1000 req/s，在国内互联网排名绝对前5，那么恭喜你。因此一般而言，用Hessian就够了。

基于中间描述的协议，例如corba的idl，protocol buffers的proto，thrift的ThriftIDL，ice的slice，这些协议可以通过中间描述生成代码，数据的编码和解析具有很好的性能，而基于直接类型的协议如xmlrpc，hessian等，则必须采用反射的方法进行编码和解析，性能相对较低。(只是记录，未自己考量过，还不确定)

---
序列化和反序列化
<http://blog.csdn.net/Kingson_Wu/article/details/48415121>

---
几种序列化协议(protobuf,xstream,jackjson,jdk,hessian)相关数据对比<http://blog.csdn.net/Kingson_Wu/article/details/48415121>
1. protobuf 不管是处理时间上，还是空间占用上都优于现有的其他序列化方式。内存暂用是java 序列化的1/9，时间也是差了一个数量级，一次操作在1us左右。缺点：就是对象结构体有限制，只适合于内部系统使用。
2. json格式在空间占用还是有一些优势，是java序列化的1/2.6。序列化和反序列化处理时间上差不多，也就在5us。当然这次使用的jackson，如果使用普通的jsonlib可能没有这样好的性能，jsonlib估计跟java序列化差不多。
3. xml相比于java序列化来说，空间占用上有点优势，但不明显。处理时间上比java序列化多了一个数量级，在100us左右。
4. 以前一种的java序列化，表现得有些失望
5. hessian测试有点意外，具体序列化数据上还步入json。性能上也不如jackjson，输得比较彻底。
6. hessian使用压缩，虽然在字节上有20%以上的空间提升，但性能上差了4,5倍，典型的以时间换空间。总的来说还是google protobuf比较给力

---
Google Protocol Buffer 的使用和原理
<https://www.ibm.com/developerworks/cn/linux/l-cn-gpb/>
封解包的速度
首先我们来了解一下 XML 的封解包过程。XML 需要从文件中读取出字符串，再转换为 XML 文档对象结构模型。之后，再从 XML 文档对象结构模型中读取指定节点的字符串，最后再将这个字符串转换成指定类型的变量。这个过程非常复杂，其中将 XML 文件转换为文档对象结构模型的过程通常需要完成词法文法分析等大量消耗 CPU 的复杂计算。
反观 Protobuf，它只需要简单地将一个二进制序列，按照指定的格式读取到 C++ 对应的结构类型中就可以了。从上一节的描述可以看到消息的 decoding 过程也可以通过几个位移操作组成的表达式计算即可完成。速度非常快。

---
请求传参方式
1. 请求参数有多个，这多个参数其实属于一个对象，如：
`id=4&name=torres&age=30`
然后服务端使用反射转化成对个对象，当然可以像spring mvc那样在项目启动保存类的参数名词，访问时快速使用反射转化。
2. 请求参数只有一个，就是一个json字符串，如：
`data={"id":4,"name":"torres","age":30}`可以使用性能较高的jackson等库来序列化成对象。

fastjson是怎么实现JSON的序列化和反序列化的
<https://www.zhihu.com/question/40002316/answer/84310005>

---

有人会说java 的序列化很慢，但是性能的瓶颈永远不会出现在序列化上。

+ 如何编写高性能的 RPC 框架:<https://mp.weixin.qq.com/s/bJkcjnX_5w6mPL0e-igDhw>

---
http://www.cppblog.com/tx7do/archive/2016/11/17/214415.html

 	protobuf	thrift
功能特性	主要是一种序列化机制	提供了全套RPC解决方案，包括序列化机制、传输层、并发处理框架等
支持语言	C++/Java/Python	C++, Java, Python, Ruby, Perl, PHP, C#, Erlang, Haskell
易用性	语法类似，使用方式等类似
生成代码的质量	可读性都还过得去，执行效率另测
升级时版本兼容性	均支持向后兼容和向前兼容
学习成本	功能单一，容易学习	功能丰富、学习成本高
文档&社区	官方文档较为丰富，google搜索protocol buffer有2000W+结果，google group被墙不能访问	官方文档较少，没有API文档，google搜索apache thrift仅40W结果，邮件列表不怎么活跃
 
 