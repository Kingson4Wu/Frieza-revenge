<http://hessian.caucho.com/>

+ JSON
+ hessian(HTTP传输)
+ thrift(自带传输协议)
+ protobuf
+ FlatBuffers:<http://www.csdn.net/article/2015-08-31/2825579>
FlatBuffers是Google专为游戏开发而创建的开源、高效的跨平台序列化库，Facebook以其取代JSON来提升数据处理性能。本文作者介绍了在Android应用中使用FlatBuffers的方法，并与JSON对比，深剖FlatBuffers工作原理。


---

#### 文本协议与二进制协议
+ 1)文本协议是根据文本中出现某些字符来表达信息，如出现\n、<、{、"等等，json xml等就是文本协议；
+ 2)二进制协议是按照字节的位置和长度来表达信息，最近半年做的push系统的消息协议就是二进制协议

<http://ninecloud.blog.51cto.com/1899173/1372120>

