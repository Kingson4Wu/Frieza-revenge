package com.kxw.jvm.instrumentation.bytebuddy;

import java.lang.instrument.Instrumentation;

import net.bytebuddy.agent.ByteBuddyAgent;

/**
 * <p/>
 * <br/>==========================
 * <br/> @date 2017年04月05日 13:53
 * <br/>==========================
 */
public class ByteBuddyAgentTest {

    public static void main(String[] args) {
        ByteBuddyAgent.install();
        Instrumentation instrumentation = ByteBuddyAgent.getInstrumentation();

        System.out.println(instrumentation);
    }
}
