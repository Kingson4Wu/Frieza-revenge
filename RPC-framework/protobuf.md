+ protocol buffer下载地址：<https://repo1.maven.org/maven2/com/google/protobuf/protoc/3.3.0/>
+ protoc-gen-grpc-java下载地址：<https://repo1.maven.org/maven2/io/grpc/protoc-gen-grpc-java/1.4.0/>

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
