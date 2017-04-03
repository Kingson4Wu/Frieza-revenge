package com.kxw.jvm.instrumentation.agentmainbugfix;

import com.sun.tools.attach.VirtualMachine;

/**
 * Created by kingsonwu on 17/4/3.
 *
 * @author kingsonwu
 * @date 2017/04/03
 */
public class BugFixMain {
    /**
     * 修复过程
     * 1. 改写HeaderUtility类, 编译成class文件,
     * 2. 拷贝 /Users/kingsonwu/Personal/github/Utils4Java/jdk-lib/target/classes/com/kxw/jvm/instrumentation/agentmain/HeaderUtility.class
     * 到 /Users/kingsonwu/Personal/github/Utils4Java/jdk-lib/src/main/java/com/kxw/jvm/instrumentation/agentmainjar/typo.fix
     * 3. cd jdk-lib  && mvn clean install -Dmaven.test.skip=true 打包成jar (Agent-Class: com.kxw.jvm.instrumentation.agentmainjar.BugFixAgent )
     * 4. /Users/kingsonwu/Personal/github/Utils4Java/jdk-lib/target/jdk-lib-0.0.1-SNAPSHOT.jar
     * 5. jps 获得pid 23759
     *
     *
     * objc[25585]: Class JavaLaunchHelper is implemented in both /Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/bin/java and /Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/jre/lib/libinstrument.dylib. One of the two will be used. Which one is undefined.
     * http://quabr.com/23590613/java-8-class-javalaunchhelper-is-implemented-in-both
     * This is a bug in the JDK and is still not fixed (as of 8u25). See more info
     * http://stackoverflow.com/questions/18794573/objc10012-class-javalaunchhelper-is-implemented-in-both-libinstrument-dyl/18958973#18958973
     */

    public static void main(String[] args){

        /**
         * 在对 API 的 JAR (或者模块) 进行了定位之后，我们就该让其对附件进程可用。
         * 在 OpenJDK 上，被用来连接到另外一个 JVM 的类叫做 VirtualMachine，
         * 它向任何由位于同一台物理机器上的 JDK 或者是一个普通的 HtpSpot JVM 所运行的 VM 提供了一个入口点。
         */
        try {
            // the following strings must be provided by us
            String processId = "25585";
            String jarFileName
                = "/Users/kingsonwu/Personal/github/Utils4Java/jdk-lib/target/jdk-lib-0.0.1-SNAPSHOT.jar";
            VirtualMachine virtualMachine = VirtualMachine.attach(processId);
            try {
                //virtualMachine.loadAgent(jarFileName);
                virtualMachine.loadAgent(jarFileName);
            } finally {
                virtualMachine.detach();
            }
        }catch (Exception e){

            e.printStackTrace();
        }
    }
}
