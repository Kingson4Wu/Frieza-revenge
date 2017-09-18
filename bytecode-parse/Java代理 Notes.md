+ 静态代理：由程序员创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了。 
+ 动态代理：在程序运行时，运用反射机制动态创建而成。 
java静态代理和动态代理:<http://layznet.iteye.com/blog/1182924>

实际测试中，JDK的动态类创建过程很快，这是因为在这个内置实现中defineClass（）方法被定义为native实现，故性能高于其它几种实现。但在代理类的函数调用性能上，JDK的动态代理就不如CGLIB和Javassist的基于动态代理的代理。
<https://segmentfault.com/a/1190000004360241>

静态代理不使用反射
jdk动态代理生成的字节码也是使用反射调用被代理的方法
javaassist？
cglib？
asm？


不得不说的AOP——基于Javassist的动态代理实现<http://exolution.iteye.com/blog/1460833>

javaasist动态代理实现原理？？

---
深入剖析动态代理--性能比较:<http://blog.csdn.net/liutengteng130/article/details/46565309>
Java查看动态代理生成的代码：<http://www.cnblogs.com/ctgulong/p/5011614.html>


---

#### JDK 动态代理
+ JDK动态代理实现原理<http://rejoy.iteye.com/blog/1627405>
JDK的动态代理依靠接口实现，如果有些类并没有实现接口，则不能使用JDK代理，这就要使用cglib动态代理了。
+ Java的动态代理
      1. JAVA自带的动态代理是基于java.lang.reflect.Proxy、java.lang.reflect.InvocationHandler两个类来完成的，使用JAVA反射机制。
通常使用下面方法创建代理对象：
      2. Object proxy = Proxy.newProxyInstance(定义代理对象的类加载器,要代理的目标对象的归属接口数组,回调接口InvocationHandler);
      3. JDK的动态代理会动态的创建一个$Proxy0的类，这个类继承了Proxy并且实现了要代理的目标对象的接口，但是在JDK中是找不到这个类的，因为它是动态生成的。
      4. 目标对象的方法调用被Proxy拦截，在InvocationHandler中的回调方法中通过反射调用。这种动态代理的方法实现了对类的方法的运行时修改。
      来自：<http://blog.sina.com.cn/s/blog_548c8a8301013j6u.html>

+ JDK也是通过字节码生成来实现动态代理的，但生成的字节码也是用反射的方式调用被代理对象的方法。


       

---

#### Cglib
+ JDK的动态代理机制只能代理实现了接口的类，而不能实现接口的类就不能实现JDK的动态代理，cglib是针对类来实现代理的，他的原理是对指定的目标类生成一个子类，并覆盖其中方法实现增强，但因为采用的是继承，所以不能对final修饰的类进行代理。 

+ CGLib所创建的动态代理对象的性能比JDK的高大概10倍，但CGLib在创建代理对象的时间比JDK大概多8倍，所以对于singleton的代理对象或者具有实例池的代理，因为无需重复的创建代理对象，所以比较适合CGLib动态代理技术，反之选择JDK代理。值得一提的是由于CGLib采用动态创建子类的方式生成代理对象，所以不能对目标类中final的方法进行代理。
来自<http://www.xuebuyuan.com/324257.html>

+ CGLib动态代理原理及实现:<http://blog.csdn.net/yakoo5/article/details/9099133/>
CGLIB 和 JDK生成动态代理类的区别：<http://luyuanliang.iteye.com/blog/1137292>

+ cglib封装了asm，可以在运行期动态生成新的class。
+ CGLIB原理
1. CGLIB原理：动态生成一个要代理类的子类，子类重写要代理的类的所有不是final的方法。在子类中采用方法拦截的技术拦截所有父类方法的调用，顺势织入横切逻辑。它比使用java反射的JDK动态代理要快。
2. CGLIB底层：使用字节码处理框架ASM，来转换字节码并生成新的类。不鼓励直接使用ASM，因为它要求你必须对JVM内部结构包括class文件的格式和指令集都很熟悉。
3. CGLIB缺点：对于final方法，无法进行代理。
来自<http://shensy.iteye.com/blog/1867588>




---
#### Spring AOP

+ 1. 基于JDK动态代理 ，可以将@Transactional放置在接口和具体类上。
2. 基于CGLIB类代理，只能将@Transactional放置在具体类上。
因此 在实际开发时全部将@Transactional放到具体类上，而不是接口上。
建议大家使用基于Schema风格的事务（不用考虑这么多问题，也不用考虑是类还是方法）。而@Transactional建议放置到具体类上，不要放置到接口。
来自<http://jinnianshilongnian.iteye.com/blog/1508018>


+ `<tx:annotation-driven transaction-manager="txManager" proxy-target-class="true"/>`
 该配置方式是基于CGLIB类代理

+ 1. Spring使用JdkDynamicAopProxy实现代理
2. Spring将使用CGLIB动态代理，而内部通过Cglib2AopProxy实现代理，而内部通过DynamicAdvisedInterceptor进行拦截

+ Spring中JDK动态代理和CGLIB动态代理的性能比较:
<http://www.thinksaas.cn/group/topic/130731/>

---
#### 动态代理方案性能对比:
+ 测试结论： 
1. ASM和JAVAASSIST字节码生成方式不相上下，都很快，是CGLIB的5倍。 
2. CGLIB次之，是JDK自带的两倍。 
3. JDK自带的再次之，因JDK1.6对动态代理做了优化，如果用低版本JDK更慢，要注意的是JDK也是通过字节码生成来实现动态代理的，而不是反射。 (个人理解应该说不是简单的使用反射，而是使用字节码生成类，这类会使用反射调用)
4. JAVAASSIST提供者动态代理接口最慢，比JDK自带的还慢。 
(这也是为什么网上有人说JAVAASSIST比JDK还慢的原因，用JAVAASSIST最好别用它提供的动态代理接口，而可以考虑用它的字节码生成方式)

+ 差异原因： 
各方案生成的字节码不一样， 
像JDK和CGLIB都考虑了很多因素，以及继承或包装了自己的一些类， 
所以生成的字节码非常大，而我们很多时候用不上这些， 
而手工生成的字节码非常小，所以速度快

+ 最终选型： 
最终决定使用JAVAASSIST的字节码生成代理方式， 
虽然ASM稍快，但并没有快一个数量级， 
而JAVAASSIST的字节码生成方式比ASM方便， 
JAVAASSIST只需用字符串拼接出Java源码，便可生成相应字节码， 
而ASM需要手工写字节码。

+ 评论
1. 请问 用动态代理生成的bytecode是如何从jvm里导出来的？谢谢
可以用：
javap -c 类名
2. 这样的测试对cglib不公平,因为javassit采用的是直接调用，而cglib走了methodProxy.invoke()，说白了还是反射调用。
可以实施cglib的直接调用，比如使用的Dispatcher或则LazyLoader。最后的生成的字节就是一个直接调用，性能上就可以和javassist持平。
```java
class DirectService implements Dispatcher {  
  
    public Object loadObject() throws Exception {  
        return service;  
    }  
  
}  
```

来自:<<http://javatar.iteye.com/blog/814426/>>