DI之Bean的作用域
<http://www.importnew.com/17609.html>

什么是作用域呢？即“scope”，在面向对象程序设计中一般指对象或变量之间的可见范围。而在Spring容器中是指其创建的Bean对象相对于其他Bean对象的请求可见范围。

Spring提供“singleton”和“prototype”两种基本作用域，另外提供“request”、“session”、“global session”三种web作用域；Spring还允许用户定制自己的作用域。

+ 基本的作用域

一、singleton：指“singleton”作用域的Bean只会在每个Spring IoC容器中存在一个实例，而且其完整生命周期完全由Spring容器管理。对于所有获取该Bean的操作Spring容器将只返回同一个Bean。
GoF单例设计模式指“保证一个类仅有一个实例，并提供一个访问它的全局访问点”，介绍了两种实现：通过在类上定义静态属性保持该实例和通过注册表方式。


1）通过在类上定义静态属性保持该实例：一般指一个Java虚拟机 ClassLoader装载的类只有一个实例，一般通过类静态属性保持该实例，这样就造成需要单例的类都需要按照单例设计模式进行编码；Spring没采用这种方式，因为该方式属于侵入式设计；代码样例如下：

package cn.javass.spring.chapter3.bean;
public class Singleton {
    //1.私有化构造器
    private Singleton() {}
    //2.单例缓存者，惰性初始化，第一次使用时初始化
    private static class InstanceHolder {
        private static final Singleton INSTANCE = new Singleton();
    }
    //3.提供全局访问点
    public static Singleton getInstance() {
        return InstanceHolder.INSTANCE;
    }
    //4.提供一个计数器来验证一个ClassLoader一个实例
    private int counter=0;
}
 

以上定义个了个单例类，首先要私有化类构造器；其次使用InstanceHolder静态内部类持有单例对象，这样可以得到惰性初始化好处；最后提供全局访问点getInstance，使得需要该单例实例的对象能获取到；我们在此还提供了一个counter计数器来验证一个ClassLoader一个实例。具体一个ClassLoader有一个单例实例测试请参考代码“cn.javass.spring.chapter3. SingletonTest”中的“testSingleton”测试方法，里边详细演示了一个ClassLoader有一个单例实例。

 
2）  通过注册表方式： 首先将需要单例的实例通过唯一键注册到注册表，然后通过键来获取单例，让我们直接看实现吧，注意本注册表实现了Spring接口“SingletonBeanRegistry”，该接口定义了操作共享的单例对象，Spring容器实现将实现此接口；所以共享单例对象通过“registerSingleton”方法注册，通过“getSingleton”方法获取，消除了编程方式单例，注意在实现中不考虑并发：

package cn.javass.spring.chapter3;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
public class SingletonBeanRegister implements SingletonBeanRegistry {
    //单例Bean缓存池，此处不考虑并发
    private final Map<String, Object> BEANS = new HashMap<String, Object>();
    public boolean containsSingleton(String beanName) {
        return BEANS.containsKey(beanName);
    }
    public Object getSingleton(String beanName) {
        return BEANS.get(beanName);
    }
    @Override
    public int getSingletonCount() {
        return BEANS.size();
    }
    @Override
    public String[] getSingletonNames() {
        return BEANS.keySet().toArray(new String[0]);
    }
    @Override
    public void registerSingleton(String beanName, Object bean) {
        if(BEANS.containsKey(beanName)) {
            throw new RuntimeException("[" + beanName + "] 已存在");
        }
        BEANS.put(beanName, bean);
}
}
 

Spring是注册表单例设计模式的实现，消除了编程式单例，而且对代码是非入侵式。

接下来让我们看看在Spring中如何配置单例Bean吧，在Spring容器中如果没指定作用域默认就是“singleton”，配置方式通过scope属性配置，具体配置如下：

<bean  class="cn.javass.spring.chapter3.bean.Printer" scope="singleton"/>
Spring管理单例对象在Spring容器中存储如图3-5所示，Spring不仅会缓存单例对象，Bean定义也是会缓存的，对于惰性初始化的对象是在首次使用时根据Bean定义创建并存放于单例缓存池。


---
二、prototype：即原型，指每次向Spring容器请求获取Bean都返回一个全新的Bean，相对于“singleton”来说就是不缓存Bean，每次都是一个根据Bean定义创建的全新Bean。

GoF原型设计模式，指用原型实例指定创建对象的种类，并且通过拷贝这些原型创建新的对象。

Spring中的原型和GoF中介绍的原型含义是不一样的：

GoF通过用原型实例指定创建对象的种类，而Spring容器用Bean定义指定创建对象的种类；

GoF通过拷贝这些原型创建新的对象，而Spring容器根据Bean定义创建新对象。

其相同地方都是根据某些东西创建新东西，而且GoF原型必须显示实现克隆操作，属于侵入式，而Spring容器只需配置即可，属于非侵入式。

 
接下来让我们看看Spring如何实现原型呢？


1）首先让我们来定义Bean“原型”：Bean定义，所有对象将根据Bean定义创建；在此我们只是简单示例一下，不会涉及依赖注入等复杂实现：BeanDefinition类定义属性“class”表示原型类，“id”表示唯一标识，“scope”表示作用域，具体如下：

package cn.javass.spring.chapter3;
public class BeanDefinition {
    //单例
    public static final int SCOPE_SINGLETON = 0;
    //原型
    public static final int SCOPE_PROTOTYPE = 1;
    //唯一标识
    private String id;
    //class全限定名
    private String clazz;
    //作用域
private int scope = SCOPE_SINGLETON;
    //鉴于篇幅，省略setter和getter方法；
}
2）接下来让我们看看Bean定义注册表，类似于单例注册表：

package cn.javass.spring.chapter3;
import java.util.HashMap;
import java.util.Map;
public class BeanDifinitionRegister {
    //bean定义缓存，此处不考虑并发问题
private final Map<String, BeanDefinition> DEFINITIONS =
 new HashMap<String, BeanDefinition>();
    public void registerBeanDefinition(String beanName, BeanDefinition bd) {
        //1.本实现不允许覆盖Bean定义
        if(DEFINITIONS.containsKey(bd.getId())) {
            throw new RuntimeException("已存在Bean定义，此实现不允许覆盖");
        }
        //2.将Bean定义放入Bean定义缓存池
        DEFINITIONS.put(bd.getId(), bd);
    }
    public BeanDefinition getBeanDefinition(String beanName) {
        return DEFINITIONS.get(beanName);
    }
public boolean containsBeanDefinition(String beanName) {      
 return DEFINITIONS.containsKey(beanName);
    }
}
 

3）接下来应该来定义BeanFactory了：

package cn.javass.spring.chapter3;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
public class DefaultBeanFactory {
    //Bean定义注册表
    private BeanDifinitionRegister DEFINITIONS = new BeanDifinitionRegister();
 
    //单例注册表
    private final SingletonBeanRegistry SINGLETONS = new SingletonBeanRegister();
 
    public Object getBean(String beanName) {
        //1.验证Bean定义是否存在
        if(!DEFINITIONS.containsBeanDefinition(beanName)) {
            throw new RuntimeException("不存在[" + beanName + "]Bean定义");
        }
        //2.获取Bean定义
        BeanDefinition bd = DEFINITIONS.getBeanDefinition(beanName);
        //3.是否该Bean定义是单例作用域
        if(bd.getScope() == BeanDefinition.SCOPE_SINGLETON) {
            //3.1 如果单例注册表包含Bean，则直接返回该Bean
            if(SINGLETONS.containsSingleton(beanName)) {
                return SINGLETONS.getSingleton(beanName);
            }
            //3.2单例注册表不包含该Bean，
            //则创建并注册到单例注册表，从而缓存
            SINGLETONS.registerSingleton(beanName, createBean(bd));
            return SINGLETONS.getSingleton(beanName);
        }
        //4.如果是原型Bean定义,则直接返回根据Bean定义创建的新Bean，
//每次都是新的，无缓存
        if(bd.getScope() == BeanDefinition.SCOPE_PROTOTYPE) {
            return createBean(bd);
        }
        //5.其他情况错误的Bean定义
        throw new RuntimeException("错误的Bean定义");
    }
 
   public void registerBeanDefinition(BeanDefinition bd) {
        DEFINITIONS.registerBeanDefinition(bd.getId(), bd);
    }
 
    private Object createBean(BeanDefinition bd) {
        //根据Bean定义创建Bean
        try {
            Class clazz = Class.forName(bd.getClazz());
            //通过反射使用无参数构造器创建Bean
            return clazz.getConstructor().newInstance();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("没有找到Bean[" + bd.getId() + "]类");
        } catch (Exception e) {
            throw new RuntimeException("创建Bean[" + bd.getId() + "]失败");
        }
    }
}
 

其中方法getBean用于获取根据beanName对于的Bean定义创建的对象，有单例和原型两类Bean；registerBeanDefinition方法用于注册Bean定义，私有方法createBean用于根据Bean定义中的类型信息创建Bean。

 

3）测试一下吧，在此我们只测试原型作用域Bean，对于每次从Bean工厂中获取的Bean都是一个全新的对象，代码片段（BeanFatoryTest）如下：

@Test
public void testPrototype () throws Exception {
//1.创建Bean工厂
DefaultBeanFactory bf = new DefaultBeanFactory();
//2.创建原型 Bean定义
BeanDefinition bd = new BeanDefinition();
bd.setId("bean");
bd.setScope(BeanDefinition.SCOPE_PROTOTYPE);
bd.setClazz(HelloImpl2.class.getName());
bf.registerBeanDefinition(bd);
//对于原型Bean每次应该返回一个全新的Bean
System.out.println(bf.getBean("bean") != bf.getBean("bean"));
}
 

最后让我们看看如何在Spring中进行配置吧，只需指定<bean>标签属性“scope”属性为“prototype”即可：

<bean class="cn.javass.spring.chapter3.bean.Printer" />
Spring管理原型对象在Spring容器中存储如图3-6所示，Spring不会缓存原型对象，而是根据Bean定义每次请求返回一个全新的Bean

---

Web应用中的作用域

在Web应用中，我们可能需要将数据存储到request、session、global session。因此Spring提供了三种Web作用域：request、session、globalSession。

一、request作用域：表示每个请求需要容器创建一个全新Bean。比如提交表单的数据必须是对每次请求新建一个Bean来保持这些表单数据，请求结束释放这些数据。
 
二、session作用域：表示每个会话需要容器创建一个全新Bean。比如对于每个用户一般会有一个会话，该用户的用户信息需要存储到会话中，此时可以将该Bean配置为web作用域。

三、globalSession：类似于session作用域，只是其用于portlet环境的web应用。如果在非portlet环境将视为session作用域。

---
自定义作用域

在日常程序开发中，几乎用不到自定义作用域，除非又必要才进行自定义作用域。

首先让我们看下Scope接口吧：

package org.springframework.beans.factory.config;
import org.springframework.beans.factory.ObjectFactory;
public interface Scope {
       Object get(String name, ObjectFactory<?> objectFactory);
       Object remove(String name);
       void registerDestructionCallback(String name, Runnable callback);
       Object resolveContextualObject(String key);
       String getConversationId();
}