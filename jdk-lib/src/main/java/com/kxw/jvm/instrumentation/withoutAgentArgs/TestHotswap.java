package com.kxw.jvm.instrumentation.withoutAgentArgs;

/**
 * 1. Premain-Class: com.kxw.jvm.instrumentation.withoutAgentArgs.HotswapAgent
 * 2. -javaagent:E:\github\Frieza-revenge\jdk-lib\target\jdk-lib-0.0.1-SNAPSHOT.jar
 * 一定要加这个参数
 */
public class TestHotswap {

    /**
     * -javaagent:添加了agent.jar
     * main 函数启动时执行agent.jar 里面的MANIFEST.MF文件上定义的Premain-Class
     * agent.jar里可以不包含Premain-Class所定义的类的字节码
     * 因为premain运行的是本应用的Premain-Class的字节码，而不是agent.jar的字节码
     * 所以agent.jar里可以不包含任何其他文件，只需包含MANIFEST.MF文件即可（kingson亲测过）
     * 并且Premain-Class只能有一个
     *
     * 之所以要以-javaagent:的形式加载，是因为main函数才是程序的入口，
     * 通过传-javaagent告诉jvm在启动main函数之前执行定义的remain方法，以便给应用传入instrument环境
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getId());
        Class[] classes =  HotswapAgent.getAllClasses();
        System.out.println(classes.length);

    }
}
