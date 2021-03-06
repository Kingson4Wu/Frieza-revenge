# Utils4Java
utils code package for Java Programming.<br/>

Java各种开源框架的工具类，Java基础的md文件笔记，代码技巧等


---
org.apache.commons.lang3
ArrayUtils.toArray
StringUtils
NumberUtils

#### Apache Commons
<http://blog.csdn.net/kingson_wu/article/details/24103405>
+ BeanUtils	提供了对于JavaBean进行各种操作，克隆对象,属性等等.
+ Betwixt	XML与Java对象之间相互转换.
+ Codec	处理常用的编码方法的工具类包 例如DES、SHA1、MD5、Base64等.
+ Collections	java集合框架操作.
+ Compress	java提供文件打包 压缩类库.
+ Configuration	一个java应用程序的配置管理类库.
+ DBCP	提供数据库连接池服务.
+ DbUtils	提供对jdbc 的操作封装来简化数据查询和记录读取操作.
+ Email	java发送邮件 对javamail的封装.
+ FileUpload	提供文件上传功能.
+ HttpClien	提供HTTP客户端与服务器的各种通讯操作. 现在已改成HttpComponents
+ IO	io工具的封装.
+ Lang	Java基本对象方法的工具类包 如：StringUtils,ArrayUtils等等.
+ Logging	提供的是一个Java 的日志接口.
+ Validator	提供了客户端和服务器端的数据验证框架.

#### Google Guava
<http://ifeve.com/google-guava/>
- >> Guava Reflection ClassPath

Guava工程包含了若干被Google的 Java项目广泛依赖 的核心库，
例如：集合 [collections] 、缓存 [caching] 、原生类型支持 [primitives support] 、并发库 [concurrency libraries] 、
通用注解 [common annotations] 、字符串处理 [string processing] 、I/O 等等。 
所有这些工具每天都在被Google的工程师应用在产品服务中。

+ 比较器 Guava提供了ComparisonChain (Fluent接口风格的可读性更高，发生错误编码的几率更小，并且能避免做不必要的工作。)

#### 依赖注入——Guice
      
Guice是一个轻量级的依赖注入(DI)Java框架，它扮演着与Spring的内核控制反转（IOC)库作用相似的角色。它和Guava一样流行，它能够缓解对工厂模式（factories）的需求并简化代码中new的使用。用谷歌自己的话说，Guice的注入是为new进行new操作。
      
这个库无法让你彻底摆脱工厂模式，但是它能够确保你的代码可以不直接依赖于它们。通过这种方式，你能够更加方便的进行单元测试和代码重用。
      
闪光点：
      
这个库旨在使开发和调试的过程更快更容易，使你能够更好的理解自己的代码。此外，Guice还提供了一个很酷的功能：当错误发生时，它会生成一段有用的信息，告诉你到底发生了什么问题以及该如何解决这些问题。

----
### advice

#### concurrency
+ future -- > guava ListenableFuture ??

#### 读取配置文件
1.通过xml文件
2.通过properties文件 （file-operation/properties）
3.通过设置jvm参数

+ properties
jdk properties,spring,spring 自定义PropertyPlaceholderConfigurer,ReourceBundle,typesafe.config,aeonbits.owner,

#### 解析命令行参数
1. org.apache.commons.cli.* (guava)
2. args4j
官方网站：http://args4j.kohsuke.org/
开源地址：https://github.com/kohsuke/args4j

#### 参数校验
1. <http://beanvalidation.org/1.1/>
2. <https://github.com/neoremind/fluent-validator>
<http://neoremind.com/2016/02/java%E7%9A%84%E4%B8%9A%E5%8A%A1%E9%80%BB%E8%BE%91%E9%AA%8C%E8%AF%81%E6%A1%86%E6%9E%B6fluent-validator/>
3. alibaba 最快的声明式校验框架fastvalidator
4. apache-bval


#### Thread Pool
IO thread pool : CPU 核数 / 2
Worker Thread pool :  CPU 核数 * 2

#### file
nio file (Path,File Attributes,Symbolic和Hard Links ,DirectoryStream ,FileVisitor和Files.walkFileTree ,Watch Service API ,Random Access Files )
<http://zjumty.iteye.com/blog/1896350> kingson4wu-github-nio-demo

#### http request
1. 自己实现的httpclient,适合业务运行时使用,,比如多并发,要求快速响应,可设置超时等
2. IOUtils 适合系统初始化时使用
3. spring 的 new UrlResource(“http://地址”)；适合系统初始化时使用

#### 获取所有实现接口的实现类
自己定义classloader扫描，spring getBeanoOfType,@Autowire List<interface>

#### 数据库操作
jdbc，apache common DBUtils,spring jdbcTemplate,mybatis,hibernate

#### stopwatch
spring stopwatch ,common lang3 ,自定义annotation

#### timer
jdk，Quartz(<http://ifeve.com/quartz-tutorial-quickstart/>)，spring

#### 在Java中定时执行任务
在Java中，如果要定时执行某项任务，需要用到java.util.Timer类，对于喜欢使用框架的朋友，
可以采用 开源的任务调度框架quartz，Spring框架也支持quartz
（也可以在spring3中的bean中通过annotation注解某个方法达到定时执行的目的）。
除此这外，还有一种方法就是启动一个线程，在线程的run()方法中写一个死循环，
然后使用Thread.sleep()来保证线程定时执行某项任务。

#### 编写代码生成器
+ guava CaseFormat


#### 给线上代码增加log
+ btrace <http://blog.csdn.net/qyongkang/article/details/6090497>
+ jdk8 Nashorn 
 
 
#### 线程池设置
如果是CPU密集型任务，就需要尽量压榨CPU，参考值可以设为 NCPU+1
如果是IO密集型任务，参考值可以设置为2*NCPU
<http://blog.csdn.net/kingson_wu/article/details/51088218>


#### language
+ jython
+ jphp <http://j-php.net/wiki/Home>
+ nashorn
+ scala
+ groovy


#### software
+ redis destop manager
jdk
maven
git
Cygwin
tomcat
IntelliJ IDEA
gradle
sublime
switchhosts
webstorm
QQ
gsen
fiddler
nodejs
navicat
atom

---
### Java
#### map的使用
+ LinkedHashMap 按插入顺序排列
+ Treemap 按key排序


### network
计算机仅仅知道ip地址是无法通信的，ip数据报在封装到数据链路层中时需要加上比如以太网报头，
报头中应该含有数据链路层能理解的地址即MAC地址。ARP就是着么一种将IP转换成MAC地址的协议。

---
GenericObjectPool
BlockingQueue
HttpServletRequestWrapper、HttpServletResponseWrapper,HttpSessionWrapper

二维码生成:<http://blog.sina.com.cn/s/blog_5a6efa330102v1lb.html>

----

+ /Users/kingsonwu/Personal/github/Utils4Java/RPC-framework/RPC-Notes.md
