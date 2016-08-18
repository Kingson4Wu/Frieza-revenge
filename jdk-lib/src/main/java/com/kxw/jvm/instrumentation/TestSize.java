package com.kxw.jvm.instrumentation;


/**
 * 编写MySizeOf ,打包 (mvn clean install)
 * 编写 META-INF/MANIFEST.MF文件 Premain-Class: com.kxw.jvm.instrumentation.MySizeOf
 * java -javaagent:agent.jar TestSize
 * (java -javaagent:/Users/kingsonwu/Personal/github/Utils4Java/jdk-lib/target/jdk-lib-0.0.1-SNAPSHOT.jar)
 * <a href='http://tech.it168.com/a2011/0117/1150/000001150794_1.shtml'>@link</a>
 * <a href='http://blog.csdn.net/kindazrael/article/details/7614512'>@link</a>
 * java默认提供的方法只能测量对象当前的大小，如果要测量这个对象实际的大小（也就是包含了子对象，那么就需要自己写算法来计算了)
 * <a href='http://blog.csdn.net/xieyuooo/article/details/7068216'>@link</a>
 */
public class TestSize {
    public static void main(String []args) {
        System.out.println(MySizeOf.sizeOf(new Integer(1)));
        System.out.println(MySizeOf.sizeOf(new String("a")));
        System.out.println(MySizeOf.fullSizeOf(new String("a")));
        System.out.println(MySizeOf.sizeOf(new char[1]));
    }
}