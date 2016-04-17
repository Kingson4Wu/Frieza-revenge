package com.kxw.jvm.agent;

import java.io.IOException;
import java.lang.instrument.Instrumentation;

import com.sun.tools.attach.AttachNotSupportedException;

public class CustomAgent {
    /**
     * 如果Agent是通过JVM选项的方式捆绑到程序中，则在JVM初化完毕后，会执行premain方法，premain执行之后才是程序的main方法。
     * 清单文件中需要指定Premain-Class
     * <p>
     * premain有两种形式，默认会执行1), 如果没有1)则会执行2), 1)和2)只会执行一个<br>
     * <code>
     * 1) public static void premain(String agentArgs, Instrumentation instrumentation)<br/>
     * 2) public static void premain(String agentArgs)
     * </code></p>
     *
     * @param agentArgs
     * @param instrumentation
     */
    public static void premain(String agentArgs, Instrumentation instrumentation) {
        System.out
                .println("CustomAgent#premain(String agentArgs, Instrumentation instrumentation)");
        parseAgentArgs(agentArgs);
    }

    public static void premain(String agentArgs) {
        System.out.println("CustomAgent#premain(String agentArgs)");
        parseAgentArgs(agentArgs);
    }


    /**
     * 如果Agent是在程序运行过程中，动态的捆绑到程序中，则是执行agentmain方法。
     * 清单文件中要指定 Agent-Class
     * <p>
     * agentmain有两种形式，默认会执行1), 如果没有1)则会执行2), 1)和2)只会执行一个<br>
     * <code>
     * 1) public static void agentmain(String agentArgs, Instrumentation instrumentation)<br/>
     * 2) public static void agentmain(String agentArgs)
     * </code></p>
     * <p>
     * 通过程序捆绑的代码：<br/>
     * <code>
     * VirtualMachine vm=VirtualMachine.attach("PID"); //给指定的进程捆绑agent<br/>
     * 在得到目标进程的vm后，就可以通过
     * vm.loadAgent("agentjar"),vm.loadAgentLibrary(dll), and loadAgentPath(dllPath) 进行捆绑操作了 <br/>
     * 其中:<br>
     * loadAgent是捆绑一个jar文件，
     * loadAgentLibrary,loadAgentPath则是捆绑本地方法库（动态连接库）
     * </code>
     *
     * @param agentArgs
     * @param inst
     */
    public static void agentmain(String agentArgs, Instrumentation inst) {
        System.out
                .println("CustomAgent#agentmain(String agentArgs, Instrumentation instrumentation)");
        parseAgentArgs(agentArgs);
    }

    public static void agentmain(String agentArgs) {
        System.out.println("CustomAgent#agentmain(String agentArgs)");
        parseAgentArgs(agentArgs);
    }

    /**
     * 不论是premain,还在agentmain,都可以指定参数，参数是一个字符串，具体怎么解析，是程序自己的事      * @param agentArgs
     *
     * @return
     * @throws IOException
     * @throws AttachNotSupportedException
     */
    private static boolean parseAgentArgs(String agentArgs) {
        boolean hasArgs = false;
        if (agentArgs != null && !agentArgs.isEmpty()) {
            System.out.println("agentArgs is : " + agentArgs);
            hasArgs = true;
        } else {
            System.out.println("has no agentArgs .");
        }

        return hasArgs;
    }
}