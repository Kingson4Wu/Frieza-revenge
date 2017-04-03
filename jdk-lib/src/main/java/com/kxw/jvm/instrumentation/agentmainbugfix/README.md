#### 总结
在本文中，我们深入了解了 attach API，它使得将一个 Java 代理按顺序注入到任意一个运行中的 JVM 进程成为可能。
该代理被表示为一个 JAR 文件，里面包含了一个类，类里面有一个远程进程可以在一个指定线程中执行的 agentmain 方法。
该方法可以接收一个 Instrumentation 接口的实例作为参数，可以读已经加载的类进行重新定义。对代码的重定义，
既可以通过用打了补丁的版本替换整个 class 文件来实现，也可以通过对现有类的字节码进行修改来实现，
这样的操作可以比较简单的用诸如 Byte Buddy 这样的库来完成。

<https://www.oschina.net/translate/fixing-bugs-in-running-java-code-with-dynamic-attach>


