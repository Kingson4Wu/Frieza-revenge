package com.kxw.jni;

/**
 * <a href='https://www.cnblogs.com/yxysuanfa/p/7253560.html'>@link</a>\
 * 1.  javac jdk-lib/src/main/java/com/kxw/jni/Demo01.java -d .
 * 2. javah -jni com.kxw.jni.Demo01
 * 3. .c文件，实现hello方法
 * 4. cd jdk-lib/src/main/java/com/kxw/jni
 * 提示jni_md.h这个文件找不到；我们执行下面的命令拷贝一份：
 * sudo cp /Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include/darwin/jni_md.h /Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include
 *
 *  gcc -dynamiclib -I /Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/include/ hello.c -o libhello.jnilib
 *  使用gcc生成的动态链接库；在Mac下的后缀是固定的，就是.jnilib。此外我们生成的动态链接库必须是以lib开头的，而且我们加载动态库时需要去掉前面的lib.比如上面的例子，生成的是libhello.jnilib，后面调用加载动态库时使用的是hello这个动态库名。
 */
public class Demo01 {
    public native void hello();//没有实现

    static {
        //设置查找路径为当前项目路径
        //System.setProperty("java.library.path", ".");
        //System.out.println(Demo01.class.getResource(".").getPath());
        //加载动态库的名称
        //System.loadLibrary("hello");
        System.load("/Users/kingsonwu/Personal/github/Utils4Java/jdk-lib/src/main/java/com/kxw/jni/libhello.jnilib");
    }

    public static void main(String[] args) {

        new Demo01().hello();
    }
}
