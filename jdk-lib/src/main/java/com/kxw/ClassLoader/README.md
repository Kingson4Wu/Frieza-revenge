三种类加载器加载类文件的地方：

1) Bootstrap类加载器 – JRE/lib/rt.jar

2) Extension类加载器 – JRE/lib/ext或者java.ext.dirs指向的目录

3) Application类加载器 – CLASSPATH环境变量, 由-classpath或-cp选项定义,或者是JAR中的Manifest的classpath属性定义.

---

1. loadclass用来定义加载策略，打破双亲委托在这里定义
2. findclass用来定义class文件从哪里来，文件还是网络还是。。。生成byte数组再调用defineclass
3. defineclass用来把byte数组转化成class实例，final不能override
<http://blog.csdn.net/kingson_wu/article/details/47986763>