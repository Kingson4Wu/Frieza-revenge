<http://www.importnew.com/17599.html>

使用depends-on

depends-on是指指定Bean初始化及销毁时的顺序，使用depends-on属性指定的Bean要先初始化完毕后才初始化当前Bean，由于只有“singleton”Bean能被Spring管理销毁，所以当指定的Bean都是“singleton”时，使用depends-on属性指定的Bean要在指定的Bean之后销毁。
注：文档中说销毁Bean的顺序：Dependent beans that define a depends-on relationship with a given bean aredestroyed first, prior to the given bean itself being destroyed.
意思是：在depends-on属性中定义的“依赖Bean”要在定义该属性的Bean之前销毁。
但实际是错误的，定义“depends-on”属性的Bean会首先被销毁，然后才是“depends-on”指定的Bean被销毁。大家可以试验一下。
<pre>
配置方式如下：
<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>
<bean id="decorator"
    class="cn.javass.spring.chapter3.bean.HelloApiDecorator"
    depends-on="helloApi">
    <property name="helloApi"><ref bean="helloApi"/></property>
</bean>
</pre>

貌似之前用vutil时使用懒加载也可以解决 ............

“decorator”指定了“depends-on”属性为“helloApi”，所以在“decorator”Bean初始化之前要先初始化“helloApi”，而在销毁“helloApi”之前先要销毁“decorator”，大家注意一下销毁顺序，与文档上的不符。
“depends-on”属性可以指定多个Bean，若指定多个Bean可以用“;”、“,”、空格分割。
那“depends-on”有什么好处呢？主要是给出明确的初始化及销毁顺序，比如要初始化“decorator”时要确保“helloApi”Bean的资源准备好了，否则使用“decorator”时会看不到准备的资源；而在销毁时要先在“decorator”Bean的把对“helloApi”资源的引用释放掉才能销毁“helloApi”，否则可能销毁 “helloApi”时而“decorator”还保持着资源访问，造成资源不能释放或释放错误。

<pre>
在平常开发中我们可能需要访问文件系统，而文件打开、关闭是必须配对的，不能打开后不关闭，从而造成其他程序不能访问该文件。让我们来看具体配置吧：

 

1）准备测试类：

ResourceBean从配置文件中配置文件位置，然后定义初始化方法init中打开指定的文件，然后获取文件流；最后定义销毁方法destroy用于在应用程序关闭时调用该方法关闭掉文件流。

DependentBean中会注入ResourceBean，并从ResourceBean中获取文件流写入内容；定义初始化方法init用来定义一些初始化操作并向文件中输出文件头信息；最后定义销毁方法用于在关闭应用程序时想文件中输出文件尾信息。

具体代码如下：

package cn.javass.spring.chapter3.bean;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
public class ResourceBean {
    private FileOutputStream fos;   
    private File file;
    //初始化方法
    public void init() {
        System.out.println("ResourceBean:========初始化");
        //加载资源,在此只是演示
        System.out.println("ResourceBean:========加载资源，执行一些预操作");
        try {
            this.fos = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    //销毁资源方法
    public void destroy() {
        System.out.println("ResourceBean:========销毁");
        //释放资源
        System.out.println("ResourceBean:========释放资源，执行一些清理操作");
        try {
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public FileOutputStream getFos() {
        return fos;
    }
    public void setFile(File file) {
        this.file = file;
    }
}

package cn.javass.spring.chapter3.bean;
import java.io.IOException;
public class DependentBean {
    ResourceBean resourceBean;   
    public void write(String ss) throws IOException {
        System.out.println("DependentBean:=======写资源");
        resourceBean.getFos().write(ss.getBytes());
    }
    //初始化方法
    public void init() throws IOException {
        System.out.println("DependentBean:=======初始化");
resourceBean.getFos().write("DependentBean:=======初始化=====".getBytes());
    }
    //销毁方法
    public void destroy() throws IOException {
        System.out.println("DependentBean:=======销毁");
        //在销毁之前需要往文件中写销毁内容
        resourceBean.getFos().write("DependentBean:=======销毁=====".getBytes());
    }
 
    public void setResourceBean(ResourceBean resourceBean) {
        this.resourceBean = resourceBean;
    }
}
 

2）类定义好了，让我们来进行Bean定义吧，具体配置文件如下：

<bean id="resourceBean"
    class="cn.javass.spring.chapter3.bean.ResourceBean"
    init-method="init" destroy-method="destroy">
    <property name="file" value="D:/test.txt"/>
</bean>
<bean id="dependentBean"
    class="cn.javass.spring.chapter3.bean.DependentBean"
    init-method="init" destroy-method="destroy" depends-on="resourceBean">
    <property name="resourceBean" ref="resourceBean"/>
</bean>
       <property name=”file” value=”D:/test.txt”/>配置：Spring容器能自动把字符串转换为java.io.File。

 

init-method=”init” ：指定初始化方法，在构造器注入和setter注入完毕后执行。

       

      destroy-method=”destroy”：指定销毁方法，只有“singleton”作用域能销毁，“prototype”作用域的一定不能，其他作用域不一定能；后边再介绍。

 

在此配置中，dependentBean初始化在resourceBean之前被初始化，resourceBean销毁会在dependentBean销毁之后执行。

3）配置完毕，测试一下吧：

package cn.javass.spring.chapter3;
import java.io.IOException;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import cn.javass.spring.chapter3.bean.DependentBean;
public class MoreDependencyInjectTest {
    @Test
    public void testDependOn() throws IOException {
        ClassPathXmlApplicationContext context =
new ClassPathXmlApplicationContext("chapter3/depends-on.xml");
        //一点要注册销毁回调，否则我们定义的销毁方法不执行
        context.registerShutdownHook();
        DependentBean dependentBean =
context.getBean("dependentBean", DependentBean.class);
        dependentBean.write("aaa");
    }
}
 

测试跟其他测试完全一样，只是在此我们一定要注册销毁方法回调，否则销毁方法不会执行。

如果配置没问题会有如下输出：

ResourceBean:========初始化
ResourceBean:========加载资源，执行一些预操作
DependentBean:=========初始化
DependentBean:=========写资源
DependentBean:=========销毁
ResourceBean:========销毁
ResourceBean:========释放资源，执行一些清理操作
</pre>
---