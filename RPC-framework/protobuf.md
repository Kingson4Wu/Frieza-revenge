+ protocol buffer下载地址：<https://repo1.maven.org/maven2/com/google/protobuf/protoc/3.3.0/>
+ protoc-gen-grpc-java下载地址：<https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.4.0/>
+ Protocol Buffers（proto3）常用语法:<http://www.jianshu.com/p/18b4e55c9f22>,
<http://www.jianshu.com/p/e06ba6249edc>

---

1. 编写.proto 文件
2. 进入该文件目录，并创建下列文件和目录
<pre>
E:\protobuf
λ tree /f
E:.
├─java
└─proto
        grpc-helloworld.proto
</pre>
3. `protoc --java_out=java --proto_path=proto proto/grpc-helloworld.proto`  <br/>
`protoc --plugin=protoc-gen-grpc-java=D:/bin/protoc-gen-grpc-java.exe --grpc-java_out=java --proto_path=proto proto/grpc-helloworld.proto`
4. 生成代码
<pre>
E:\protobuf
λ ptree /f
E:.
├─java
│  └─com
│      └─kxw
│          └─gRPC
│              └─schema
│                      Greeter.java
│                      HelloReply.java
│                      HelloReplyOrBuilder.java
│                      HelloRequest.java
│                      HelloRequestOrBuilder.java
│                      HelloWorldProto.java
│
└─proto
        grpc-helloworld.proto


</pre>

5. 使用maven插件生成代码

---

+ protobuf在netty里面的应用举例:<http://blog.csdn.net/goldenfish1919/article/details/6719276>
+ netty为protobuf提供了两个编码器（ProtobufEncoder，ProtobufVarint32LengthFieldPrepender），两个解码器（ProtobufVarint32FrameDecoder，ProtobufDecoder）
  [注]所谓的编码就是把应用程序使用的数据类型编码成在网络上传输的二进制字节流，反之同理。
+ 在netty中使用Protobuf需要注意的是：
  
   protobufDecoder仅仅负责编码，并不支持读半包，所以在之前，一定要有读半包的处理器。
  
   有三种方式可以选择：
  
  使用netty提供ProtobufVarint32FrameDecoder
  继承netty提供的通用半包处理器 LengthFieldBasedFrameDecoder
  继承ByteToMessageDecoder类，自己处理半包  
  
  
  