/Library/Java/JavaVirtualMachines/jdk1.8.0_60.jdk/Contents/Home/bin/javap -c com.kxw.asm.aop.TestBean
Compiled from "TestBean.java"
public class com.kxw.asm.aop.TestBean {
  public com.kxw.asm.aop.TestBean();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public void halloAop();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String Hello Aop
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return
}


第 04 行：这一行开始到第 08 行 表示的是一个的默认构造方法，虽然我们没有在 TestBean 类中编写任何构造方法，但是作为 Java 类都应当有一个默认的无参构造方法，而这个构造方法是编译器为我们自动添加的。
第 10 行：从这行开始到结束就是我们编写的 halloAop 方法，下面将会介绍一下上面出现的几个字节码指令。
下面如果我们可以在上面字节码的12行和15行动态的插入代码那么我们的AOP目的就达到了。


    在 Java 中每一个方法在执行的时候 JVM 都会为其分配一个“帧”，帧是用来存储方法中计算所需要的所有数据。其中第 0 个元素就是 “this”，如果方法有参数传入会排在它的后面。

ALOAD_0：
    这个指令是LOAD系列指令中的一个，它的意思表示装载当前第 0 个元素到堆栈中。代码上相当于“this”。而这个数据元素的类型是一个引用类型。这些指令包含了：ALOAD，ILOAD，LLOAD，FLOAD，DLOAD。区分它们的作用就是针对不用数据类型而准备的LOAD指令，此外还有专门负责处理数组的指令 SALOAD。

invokespecial：
    这个指令是调用系列指令中的一个。其目的是调用对象类的方法。后面需要给上父类的方法完整签名。“#8”的意思是 .class 文件常量表中第8个元素。值为：“java/lang/Object."<init>":()V”。结合ALOAD_0。这两个指令可以翻译为：“super()”。其含义是调用自己的父类构造方法。

GETSTATIC：
    这个指令是GET系列指令中的一个其作用是获取静态字段内容到堆栈中。这一系列指令包括了：GETFIELD、GETSTATIC。它们分别用于获取动态字段和静态字段。

IDC：
    这个指令的功能是从常量表中装载一个数据到堆栈中。

invokevirtual：
    也是一种调用指令，这个指令区别与 invokespecial 的是它是根据引用调用对象类的方法。这里有一篇文章专门讲解这两个指令：“http://wensiqun.iteye.com/blog/1125503”。

RETURN：
    这也是一系列指令中的一个，其目的是方法调用完毕返回：可用的其他指令有：IRETURN，DRETURN，ARETURN等，用于表示不同类型参数的返回。

    讲了这么多指令想必已经有很多同学开始打退堂鼓了。没错，ASM 就是让我们直接面对底层字节码。要追求最快的 AOP 执行效率也要从字节码入手。不过为了方便开发，我再介绍一个工具，ASM-Bytecode。它是一个Eclipse插件，专门用于 ASM 框架下开发的辅助工具。它可以帮助我们生成一些繁琐的代码，从而让我们尽量绕开对底层组合虚拟机指令的关心。插件更新地址：“http://andrei.gmxhome.de/eclipse/”，项目首页：“http://andrei.gmxhome.de/bytecode/index.html”

idea 也有对应的插件

为了区别它们分别使用两个方法来代表 Aop 不同的切点，分别是调用前和调用后，拦截器代码如下：

public class AopInterceptor {
    public static void beforeInvoke() {
        System.out.println("before");
    };
    public static void afterInvoke() {
        System.out.println("after");
    };
}
    接下来只需要在代理类的方法中插入对这两个方法的调用即可。首先设想被代理的方法最终执行的代码应该是下面这个样子的：

public class TestBean {
    public void halloAop() {
        AopInterceptor.beforeInvoke();
        System.out.println("Hello Aop");
        AopInterceptor.afterInvoke();
    }
}
使用 ASM-Bytecode 工具，将这段代码转变为 ASM 代码。

https://my.oschina.net/ta8210/blog/162796


