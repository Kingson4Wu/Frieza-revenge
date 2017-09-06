package com.kxw.gc.jdk8;

/**
 * <a href='https://www.ibm.com/developerworks/cn/java/j-lo-jvm-optimize-experience/index.html'>@link</a>
 * Created by kingsonwu on 17/8/23.
 */
public class PutBigObjectOldGen {

    /**
     * 如何让大对象进入年老代
     我们在大部分情况下都会选择将对象分配在年轻代。
     但是，对于占用内存较多的大对象而言，它的选择可能就不是这样的。
     因为大对象出现在年轻代很可能扰乱年轻代 GC，并破坏年轻代原有的对象结构。
     因为尝试在年轻代分配大对象，很可能导致空间不足，为了有足够的空间容纳大对象，JVM 不得不将年轻代中的年轻对象挪到年老代。
     因为大对象占用空间多，所以可能需要移动大量小的年轻对象进入年老代，这对 GC 相当不利。
     基于以上原因，可以将大对象直接分配到年老代，保持年轻代对象结构的完整性，这样可以提高 GC 的效率。
     如果一个大对象同时又是一个短命的对象，假设这种情况出现很频繁，那对于 GC 来说会是一场灾难。
     原本应该用于存放永久对象的年老代，被短命的对象塞满，这也意味着对堆空间进行了洗牌，扰乱了分代内存回收的基本思路。
     因此，在软件开发过程中，应该尽可能避免使用短命的大对象。可以使用参数-XX:PetenureSizeThreshold 设置大对象直接进入年老代的阈值。
     当对象的大小超过这个值时，将直接在年老代分配。
     参数-XX:PetenureSizeThreshold 只对串行收集器和年轻代并行收集器有效，并行回收收集器不识别这个参数。
     * @param args
     */
    public static void main(String[] args) {
        byte[] b,b2;
        b = new byte[1024*1024];
        //b2 = new byte[1024*1024];
        //分配一个 1MB 的对象
        //-XX:+PrintGCDetails -Xmx20M -Xms20M

        /**
         * Heap
         PSYoungGen      total 6144K, used 2393K [0x00000007bf980000, 0x00000007c0000000, 0x00000007c0000000)
         eden space 5632K, 42% used [0x00000007bf980000,0x00000007bfbd6578,0x00000007bff00000)
         from space 512K, 0% used [0x00000007bff80000,0x00000007bff80000,0x00000007c0000000)
         to   space 512K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007bff80000)
         ParOldGen       total 13824K, used 0K [0x00000007bec00000, 0x00000007bf980000, 0x00000007bf980000)
         object space 13824K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bf980000)
         Metaspace       used 2905K, capacity 4494K, committed 4864K, reserved 1056768
         */
        //该对象被分配在了年轻代，占用了 42%的空间。如果需要将 1MB 以上的对象直接在年老代分配，设置-XX:PretenureSizeThreshold=1000000
        //PretenureSizeThreshold参数只对Serial和ParNew两款收集器有效

        //-XX:+PrintCommandLineFlags -version  查看JVM使用的什么垃圾收集器
        // -XX:+UseParallelGC：选择垃圾收集器为并行收集器。此配置仅对年轻代有效。即上述配置下，年轻代使用并发收集，而年老代仍旧使用串行收集。
        // -XX:PretenureSizeThreshold=1000000  -XX:+PrintCommandLineFlags -version

        //-XX:+PrintGCDetails -Xmx20M -Xms20M -XX:+UseSerialGC -XX:PretenureSizeThreshold=1000000
        //设置-XX:+UseSerialGC 才生效
        /**
         * <pre>
         *     Heap
         def new generation   total 6144K, used 1338K [0x00000007bec00000, 0x00000007bf2a0000, 0x00000007bf2a0000)
         eden space 5504K,  24% used [0x00000007bec00000, 0x00000007bed4e9b8, 0x00000007bf160000)
         from space 640K,   0% used [0x00000007bf160000, 0x00000007bf160000, 0x00000007bf200000)
         to   space 640K,   0% used [0x00000007bf200000, 0x00000007bf200000, 0x00000007bf2a0000)
         tenured generation   total 13696K, used 1024K [0x00000007bf2a0000, 0x00000007c0000000, 0x00000007c0000000)
         the space 13696K,   7% used [0x00000007bf2a0000, 0x00000007bf3a0010, 0x00000007bf3a0200, 0x00000007c0000000)
         Metaspace       used 2886K, capacity 4494K, committed 4864K, reserved 1056768K
         class space    used 318K, capacity 386K, committed 512K, reserved 1048576K
         * </pre>
         */

        //当满 1MB 时进入到了年老代
    }
}
