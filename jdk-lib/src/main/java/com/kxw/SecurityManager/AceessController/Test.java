package com.kxw.SecurityManager.AceessController;

import sun.security.util.SecurityConstants;

import java.io.FilePermission;
import java.security.Permission;

/**
 * Created by Kingson.wu on 2015/8/27.
 */

/**
 *  欢迎装载请说明出处： http://blog.csdn.net/yfqnihao/article/details/8271665
 这一节，我们要学习的是访问控制器，在阅读本节之前，如果没有前面几节的基础，对你来说可能会比较困难！
 本节实验源码下载：http://download.csdn.net/detail/yfqnihao/4863854
 知识回顾：
 我们先来回顾一下前几节的内容，在笔记三的时候我们学了类装载器，它主要的功能就是装载类，在装载的前后，class文件校验器会对class文件进行四趟的校验，而第一趟的校验会对文件的结构进行校验，对文件的结构完整性的校验时会校验class文件的hash摘要是否一致以确定文件没有中途被修改过，所以基于class文件校验我们又学习了jar的认证和签名，当class文件被装载到内存的时候，一个应用启动时，jvm会为该应用生成一个Policy的单例对象，它用于读取策略文件的grant信息，当类装载器装载一个类的时候，它根据jar包中的签名信息、证书、jar的url信息生成一个CodeSource对象，CodeSource对象向Policy对象索要一个PermissionCollecion权限集合，它是由各个grant子句中的permission语句的实例映射，再由CodeSource对象、PermissionCollecion权限集合、类加载器交由类加载器的defineClass方法组成了ProtectionDomain保护域。最后class字节码在内存中被放在了这个保护域中。
 是的内容非常的多，概念也非常的多，所以如果你对前面的知识回顾一头雾水，建议还是倒回去把那些基础的概念再补一补。
 回顾完目前为止的所有知识之后，我们需要解决两个问题
 第一，什么是访问控制器。
 第二，它是怎么样和安全管理器配合工作的。
 我们先来简单的回答第一个问题，你可以听不明白，但是如果你耐性的往下看，在我回答第二个问题的时候，我们会做几个比较复杂的demo，而这些复杂的demo，会在无形之中让你真正的认识到什么是访问控制器。在文章的最后如果篇幅够的话我们也会带大家来读一读jdk里的源码，看看他和安全管理是怎么配合工作的。
 那么什么是访问控制器?
 类java.security.AccessControler提供了一个默认的安全策略执行机制，他使用栈检查机制来决定潜在的不安全操作是否被允许。这个访问控制器不能够被实例化，它不是一个对象，而是集合在单个类的多个静态方法。AccesControler最核心的方法是checkPermission，这个方法决定一个特定的操作是否被允许，他接收一个Perssmission的子类对象，当AccessControler确定操作被允许，它将简单的返回，而如果操作被禁止，它将异常中止，并抛出一个AcssessControlException，或者是它的子类。
 */

public class Test {

    public static void main(String[] args) {

        Permission perOne = new FilePermission("d:/tmp/test.txt", SecurityConstants.FILE_READ_ACTION);
        Permission perAll = new FilePermission("d:/tmp/*",SecurityConstants.FILE_READ_ACTION);

        System.out.println(perOne.implies(perAll));
        System.out.println(perAll.implies(perOne));
    }
}
/**
 * 输出的结果为:
 false
 true
 说明:implies方法就是用于判断一个权限的范围是不是包含了另外一个权限的范围，在这个demo里，
 我们试着去判断对于perAll的权限是否包含perOne的权限还有perOne的权限是否包含perAll权限，
 很显然，perAll权限是包含perOne的。而实际上AccessControler里有一个权限栈，
 它就是遍历栈帧中的PermissionCollecion里的每个Permission然后调用里Permission的implies来判断是否包含某个权限的。
 * */