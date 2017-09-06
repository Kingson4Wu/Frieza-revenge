/**
 java version "1.8.0_60"
 Java(TM) SE Runtime Environment (build 1.8.0_60-b27)
 Java HotSpot(TM) 64-Bit Server VM (build 25.60-b23, mixed mode)
 */

/**
 * 基于上述版本测试
 */

/**
 * <a href='http://blog.csdn.net/kingson_wu/article/details/63255086'>@link</a>
 *
 * JVM 内存共分为虚拟机栈、堆、方法区、程序计数器、本地方法栈五个部分
 *
 * 1. 虚拟机栈：每个线程有一个私有的栈，随着线程的创建而创建。栈里面存着的是一种叫“栈帧”的东西，每个方法会创建一个栈帧，栈帧中存放了局部变量表（基本数据类型和对象引用）、操作数栈、方法出口等信息。栈的大小可以固定也可以动态扩展。当栈调用深度大于JVM所允许的范围，会抛出StackOverflowError的错误
 * 2. 本地方法栈：这部分主要与虚拟机用到的 Native 方法相关，一般情况下， Java 应用程序员并不需要关心这部分的内容
 * 3. PC 寄存器，也叫程序计数器。JVM支持多个线程同时运行，每个线程都有自己的程序计数器。倘若当前执行的是 JVM 的方法，则该寄存器中保存当前执行指令的地址；倘若执行的是native 方法，则PC寄存器中为空。
 * 4. 堆内存是 JVM 所有线程共享的部分，在虚拟机启动的时候就已经创建。所有的对象和数组都在堆上进行分配。这部分空间可通过 GC 进行回收。当申请不到空间时会抛出 OutOfMemoryError。
 * 5. 方法区：方法区也是所有线程共享。主要用于存储类的信息、常量池、方法数据、方法代码等。方法区逻辑上属于堆的一部分，但是为了与堆进行区分，通常又叫“非堆”。
 *
 *  堆空间(Heap): 新生代(Eden与两个Survivor区)(Eden[8/10] + from[1/8] + to[1/8]) + 老年代
 *
 * (JDK 1.7)
 * PermGen（永久代）
 * 绝大部分 Java 程序员应该都见过 "java.lang.OutOfMemoryError: PermGen space "这个异常。
 * 这里的 “PermGen space”其实指的就是方法区。不过方法区和“PermGen space”又有着本质的区别。
 * 前者是 JVM 的规范，而后者则是 JVM 规范的一种实现，并且只有 HotSpot 才有 “PermGen space”
 * (由于方法区主要存储类的相关信息，所以对于动态生成类的情况比较容易出现永久代的内存溢出。最典型的场景就是，在 jsp 页面比较多的情况，容易出现永久代内存溢出。)
 *
 * (JDK 1.8 中已经不存在永久代)
 * Metaspace（元空间）
 * 其实，移除永久代的工作从JDK1.7就开始了。JDK1.7中，存储在永久代的部分数据就已经转移到了Java Heap或者是 Native Heap。但永久代仍存在于JDK1.7中，并没完全移除，譬如符号引用(Symbols)转移到了native heap；字面量(interned strings)转移到了java heap；类的静态变量(class statics)转移到了java heap。
 *
 *
 * 以2的指数级不断的生成新的字符串，这样可以比较快速的消耗内存。
 * JDK 1.6 java.lang.OutOfMemoryError: PermGen space
 * JDK 1.7 java.lang.OutOfMemoryError: Java heap space
 * JDK 1.8 java.lang.OutOfMemoryError: Java heap space
 *
 * (JDK 1.7 和 1.8 将字符串常量由永久代转移到堆中，并且 JDK 1.8 中已经不存在永久代)
 *
 * 元空间的本质和永久代类似，都是对JVM规范中方法区的实现。
 * 不过元空间与永久代之间最大的区别在于：元空间并不在虚拟机中，而是使用本地内存。
 * 因此，默认情况下，元空间的大小仅受本地内存限制，但可以通过以下参数来指定元空间的大小
 * -XX:MetaspaceSize，初始空间大小,-XX:MaxMetaspaceSize
 * 指定 MetaSpaceSize 和 MaxMetaSpaceSize的大小, 输出结果如下：
 * JDK 1.8 java.lang.OutOfMemoryError: Metaspace
 *
 * 方法区主要用于存储类的信息、常量池、方法数据、方法代码等。
 * JDK 6-7实现: 永久代
 * JDK 8实现: 元空间 (测试元空间溢出可以通过加载类)
 * JDK 7-8 : 字符串常量由永久代转移到堆中
 *
 */

/**
 设置对象进入年老代的年龄: 通过参数-XX:MaxTenuringThreshold 来设置，默认值是 15

 -XX:MinHeapFreeRatio 参数用来设置堆空间最小空闲比例，默认值是 40。当堆空间的空闲内存小于这个数值时，JVM 便会扩展堆空间。
 -XX:MaxHeapFreeRatio 参数用来设置堆空间最大空闲比例，默认值是 70。当堆空间的空闲内存大于这个数值时，便会压缩堆空间，得到一个较小的堆。
 –XX:+LargePageSizeInBytes：设置大页的大小。

 吞吐量优先的方案将会尽可能减少系统执行垃圾回收的总时间，故可以考虑关注系统吞吐量的并行回收收集器。在拥有高性能的计算机上，进行吞吐量优先优化，可以使用参数：
 java –Xmx3800m –Xms3800m –Xmn2G –Xss128k –XX:+UseParallelGC
 –XX:ParallelGC-Threads=20 –XX:+UseParallelOldGC
 */