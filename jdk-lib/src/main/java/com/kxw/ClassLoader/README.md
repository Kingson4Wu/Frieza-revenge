三种类加载器加载类文件的地方：

1) Bootstrap类加载器 – JRE/lib/rt.jar

2) Extension类加载器 – JRE/lib/ext或者java.ext.dirs指向的目录

3) Application类加载器 – CLASSPATH环境变量, 由-classpath或-cp选项定义,或者是JAR中的Manifest的classpath属性定义.