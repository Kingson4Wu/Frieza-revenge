package com.kxw.jvm.instrumentation.withoutAgentArgs;

import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;
import java.lang.instrument.UnmodifiableClassException;

public class HotswapAgent {
    static Instrumentation instrumentation;
    public static boolean enabled = false;

    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out.println("HotswapAgent premain: " + Thread.currentThread().getId());
        HotswapAgent.instrumentation = instrumentation;
        HotswapAgent.enabled = true;
    }

    public static void reload(ClassDefinition... definitions) throws UnmodifiableClassException, ClassNotFoundException {
        instrumentation.redefineClasses(definitions);
    }

    public static Class[] getAllClasses(){
        System.out.println(Thread.currentThread().getId());
        return instrumentation.getAllLoadedClasses();
    }
}