<http://www.open-open.com/54.htm>

#### Javassist
+ Javassist学习总结:<http://blog.csdn.net/sadfishsc/article/details/9999169>

---

+ `javap -verbose MainApp`
+ `javap -p`


#### 字节码操作
Java动态代理机制详解（JDK和CGLIB，Javassist，ASM） <http://www.it165.net/pro/html/201406/14928.html>
Java动态编程初探——Javassist<http://www.cnblogs.com/hucn/p/3636912.html>
Java字节码操纵框架ASM小试<http://www.oseye.net/user/kevin/blog/304>
关于java字节码框架ASM的学习<http://www.cnblogs.com/liuling/archive/2013/05/25/asm.html>
使用 Antlr 开发领域语言<http://www.ibm.com/developerworks/cn/java/j-lo-antlr/>
javassist跟asm比较<http://www.educity.cn/wenda/379483.html>


#### ASM
+ ASM 库的介绍和使用:<https://www.jianshu.com/p/905be2a9a700>
ASM 官方为我们提供了 ASMifier，可以帮助我们生成这些晦涩难懂的 ASM 代码

how to use asm to spring boot?

---

#### beanCopy
+ CGLIB BeanCopier和BeanMap等实现:<https://yq.aliyun.com/articles/48665>
+ 常见Bean映射工具分析评测及Orika介绍:<https://www.jianshu.com/p/40e0e64797b9>
    - Orika是近期在github活跃的项目，底层采用了javassist类库生成Bean映射的字节码，之后直接加载执行生成的字节码文件，因此在速度上比使用反射进行赋值会快很多
    