package com.kxw.jvm.agent;

//import org.junit.Test;

public class JavaAgentTest {
    /**
     * 在启动时，使用-javaagent方式加入代理
     * <code>
     * -javaagent:D:/customagent.jar="Here, your can input agent arguments"
     * 如果要指定参数值
     * </code>
     */
   // @Test
    public void testPremain() {
        System.out.println("test premain,  sepcial inst...");
    }
}