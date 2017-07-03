package com.kxw.gc.jdk8;

/**
 * Created by kingsonwu on 17/6/17.
 *
 * 使用 JVM 参数-XX:+PrintGCDetails -Xmx20M -Xms20M 运行
 * -Xms:初始堆大小 ,-Xmx:最大堆大小
 */
public class PutInEden {
    public static void main(String[] args){
        byte[] b1,b2,b3,b4;//定义变量
        //分配 1MB 堆空间，考察堆空间的使用情况
        b1=new byte[1024*1024];
        b2=new byte[1024*1024];
        b3=new byte[1024*1024];
        b4=new byte[1024*1024];
    }
}
