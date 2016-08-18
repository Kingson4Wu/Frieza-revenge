<http://blog.csdn.net/kingson_wu/article/details/51276231>

那attach机制是什么？说简单点就是jvm提供一种jvm进程间通信的能力，
能让一个进程传命令给另外一个进程，并让它执行内部的一些操作。


<http://lovestblog.cn/blog/2014/06/18/jvm-attach/>
attach 怎么开启？
 一开始会判断当前进程目录下是否有个.Attach_pid文件（前面提到了），
如果没有就会在/tmp下创建一个/tmp/.Attach_pid，当那个文件的uid和自己的uid是一致的情况下（为了安全）
再调用init方法

同个用户开启的进程则允许被attach到另一个jvm进程！！！

<pre>
agent jar中manifest的属性
Premain-Class: 当在VM启动时，在命令行中指定代理jar时，必须在manifest中设置Premain-Class属性，值为代理类全类名，并且该代理类必须提供premain方法。否则JVM会异常终止。
Agent-Class: 当在VM启动之后，动态添加代理jar包时，代理jar包中manifest必须设置Agent-Class属性，值为代理类全类名，并且该代理类必须提供agentmain方法，否则无法启动该代理。
Boot-Class-Path: Bootstrap class loader加载类时的搜索路径，可选。
Can-Redefine-Classes: true/false；标示代理类是否能够重定义类。可选。
Can-Retransform-Classes: true/false；标示代理类是否能够转换类定义。可选。
Can-Set-Native-Prefix::true/false；标示代理类是否需要本地方法前缀，可选。
当一个代理jar包中的manifest文件中既有Premain-Class又有Agent-Class时，如果以命令行方式在VM启动前指定代理jar，则使用Premain-Class；反之如果在VM启动后，动态添加代理jar，则使用Agent-Class
</pre>
<http://jiangbo.me/blog/2012/02/21/java-lang-instrument/>

JVM源码分析之javaagent原理完全解读:
<http://lovestblog.cn/blog/2015/09/14/javaagent/>

