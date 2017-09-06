package com.kxw.gc.jdk8;

/**
 * Created by kingsonwu on 17/6/17.
 *
 * 使用 JVM 参数-XX:+PrintGCDetails -Xmx20M -Xms20M 运行
 * -Xms:初始堆大小 ,-Xmx:最大堆大小
 */
public class PutInEden {
    public static void main(String[] args){
        byte[] b1,b2,b3,b4,b5,b6;//定义变量
        //分配 1MB 堆空间，考察堆空间的使用情况
        b1=new byte[1024*1024];
        b2=new byte[1024*1024];
        b3=new byte[1024*1024];
        b4=new byte[1024*1024];
        //b5=new byte[1024*1024];
        //b6=new byte[1024*1024];

       // System.out.println("-----");
    }
}

/**
 * 1. 使用 JVM 参数-XX:+PrintGCDetails -Xmx20M -Xms20M 运行
 * -Xms:初始堆大小 ,-Xmx:最大堆大小
 *
 * <pre>
 Heap
 PSYoungGen      total 6144K, used 5465K [0x00000007bf980000, 0x00000007c0000000, 0x00000007c0000000)
 eden space 5632K, 97% used [0x00000007bf980000,0x00000007bfed65c0,0x00000007bff00000)
 from space 512K, 0% used [0x00000007bff80000,0x00000007bff80000,0x00000007c0000000)
 to   space 512K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007bff80000)
 ParOldGen       total 13824K, used 0K [0x00000007bec00000, 0x00000007bf980000, 0x00000007bf980000)
 object space 13824K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bf980000)
 Metaspace       used 2935K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 324K, capacity 386K, committed 512K, reserved 1048576K
 * </pre>
 *
 * 日志输出显示年轻代 Eden 的大小有 5MB 左右。分配足够大的年轻代空间-Xmn8M
 *
 * 2. -XX:+PrintGCDetails -Xmx20M -Xms20M -Xmn8M
 * 设置年轻代大小为8M
 * <pre>
 Heap
 PSYoungGen      total 7168K, used 5473K [0x00000007bf800000, 0x00000007c0000000, 0x00000007c0000000)
 eden space 6144K, 89% used [0x00000007bf800000,0x00000007bfd58410,0x00000007bfe00000)
 from space 1024K, 0% used [0x00000007bff00000,0x00000007bff00000,0x00000007c0000000)
 to   space 1024K, 0% used [0x00000007bfe00000,0x00000007bfe00000,0x00000007bff00000)
 ParOldGen       total 12288K, used 0K [0x00000007bec00000, 0x00000007bf800000, 0x00000007bf800000)
 object space 12288K, 0% used [0x00000007bec00000,0x00000007bec00000,0x00000007bf800000)
 Metaspace       used 2934K, capacity 4494K, committed 4864K, reserved 1056768K
 class space    used 324K, capacity 386K, committed 512K, reserved 1048576K
 * </pre>
 *
 * 通过设置一个较大的年轻代预留新对象，设置合理的 Survivor 区并且提供 Survivor 区的使用率，可以将年轻对象保存在年轻代。一般来说，Survivor 区的空间不够，或者占用量达到 50%时，就会使对象进入年老代
 *
 *  3. -XX:SurvivorRatio=8, -XX:TargetSurvivorRatio=90 参数，这样可以提高 from 区的利用率，使 from 区使用到 90%时，再将对象送入年老代
 *
 *
 *
 *
 */