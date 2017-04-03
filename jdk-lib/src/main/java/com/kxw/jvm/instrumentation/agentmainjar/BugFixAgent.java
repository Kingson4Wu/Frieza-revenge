package com.kxw.jvm.instrumentation.agentmainjar;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.lang.instrument.ClassDefinition;
import java.lang.instrument.Instrumentation;

/**
 * Created by kingsonwu on 17/4/3.
 *
 * @author kingsonwu
 * @date 2017/04/03
 */
public class BugFixAgent {

    /**
     * HeaderUtility 类会被重定义以对应其修补的版本。对 isPrivileged 的任何后续调用现在将读取正确的头信息。
     * 作为一个小的附加说明，JVM 可能会在应用类重定义时执行完全的垃圾回收，并且会对受影响的代码进行重新优化。
     * 总之，这会导致应用程序性能的短时下降。然而，在大多数情况下，这是较之完全重启进程更好的方式。
     *
     *
     * 当应用代码更改时，要确保新类定义了与它替换的类完全相同的字段、方法和修饰符。
     * 尝试修改任何此类属性的类重定义行为都会导致 UnsupportedOperationException。
     * 现在 HotSpot 团队正试图去掉这个限制。此外，基于 OpenJDK 的动态代码演变虚拟机支持预览此功能。
     */
    public static void agentmain(String arg, Instrumentation inst)
        throws Exception {
        // only if header utility is on the class path; otherwise,
        // a class can be found within any class loader by iterating
        // over the return value of Instrumentation::getAllLoadedClasses
        Class<?> headerUtility = Class.forName("com.kxw.jvm.instrumentation.agentmain.HeaderUtility");

        // copy the contents of typo.fix into a byte array
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try (InputStream input =
                 //BugFixAgent.class.getResourceAsStream("./class/typo.fix")) {
                 BugFixAgent.class.getClassLoader().getResourceAsStream("./class/typo.fix")) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = input.read(buffer)) != -1) {
                output.write(buffer, 0, length);
            }
        }

        // Apply the redefinition
        inst.redefineClasses(
            new ClassDefinition(headerUtility, output.toByteArray()));
    }

}