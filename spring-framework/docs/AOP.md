<http://www.importnew.com/17755.html>

public class HelloWorldAspect { 
 //前置通知 
 public void beforeAdvice() { 
 System.out.println("===========before advice"); 
} 
//后置最终通知 
 public void afterFinallyAdvice() { 
 System.out.println("===========after finally advice"); 
 } 
}

<bean id="aspect" class="cn.javass.spring.chapter6.aop.HelloWorldAspect"/> 
<aop:config> 
<aop:pointcut id="pointcut" expression="execution(* cn.javass..*.*(..))"/> 
 <aop:aspect ref="aspect"> 
 <aop:before pointcut-ref="pointcut" method="beforeAdvice"/> 
 <aop:after pointcut="execution(* cn.javass..*.*(..))" method="afterFinallyAdvice"/> 
 </aop:aspect> 
</aop:config>


切入点使用<aop:config>标签下的<aop:pointcut>配置，expression属性用于定义切入点模式，默认是AspectJ语法，“execution(* cn.javass..*.*(..))”表示匹配cn.javass包及子包下的任何方法执行。

切面使用<aop:config>标签下的<aop:aspect>标签配置，其中“ref”用来引用切面支持类的方法。

前置通知使用<aop:aspect>标签下的<aop:before>标签来定义，pointcut-ref属性用于引用切入点Bean，而method用来引用切面通知实现类中的方法，该方法就是通知实现，即在目标类方法执行之前调用的方法。

最终通知使用<aop:aspect>标签下的<aop:after >标签来定义，切入点除了使用pointcut-ref属性来引用已经存在的切入点，也可以使用pointcut属性来定义，如pointcut=”execution(* cn.javass..*.*(..))”，method属性同样是指定通知实现，即在目标类方法执行之后调用的方法。


Spring AOP框架自动生成AOP代理 !!!!

---
<http://www.importnew.com/17795.html>
基于Schema的AOP
基于Schema的AOP从Spring2.0之后通过“aop”命名空间来定义切面、切入点及声明通知。
在Spring配置文件中，所以AOP相关定义必须放在<aop:config>标签下，该标签下可以有<aop:pointcut>、<aop:advisor>、<aop:aspect>标签，配置顺序不可变。
<aop:pointcut>：用来定义切入点，该切入点可以重用；
<aop:advisor>：用来定义只有一个通知和一个切入点的切面；
<aop:aspect>：用来定义切面，该切面可以包含多个切入点和通知，而且标签内部的通知和切入点定义是无序的；和advisor的区别就在此，advisor只包含一个通知和一个切入点。


Advisor
Advisor表示只有一个通知和一个切入点的切面，由于Spring AOP都是基于AOP联盟的拦截器模型的环绕通知的，所以引入Advisor来支持各种通知类型（如前置通知等5种），Advisor概念来自于Spring1.2对AOP的支持，在AspectJ中没有相应的概念对应。
Advisor可以使用<aop:config>标签下的<aop:advisor>标签定义：

<aop:advisor pointcut="切入点表达式" pointcut-ref="切入点Bean引用"
 advice-ref="通知API实现引用"/>
pointcut和pointcut-ref：二者选一，指定切入点表达式；
advice-ref：引用通知API实现Bean，如前置通知接口为MethodBeforeAdvice；

cn.javass.spring.chapter6.service.impl. HelloWorldService定义实现

@Override
public void sayAdvisorBefore(String param) { 
 System.out.println("============say " + param); 
}

定义前置通知API实现：

package cn.javass.spring.chapter6.aop; 
import java.lang.reflect.Method; 
import org.springframework.aop.MethodBeforeAdvice; 
public class BeforeAdviceImpl implements MethodBeforeAdvice { 
 @Override
 public void before(Method method, Object[] args, Object target) throws Throwable { 
 System.out.println("===========before advice"); 
 } 
}

<bean id="beforeAdvice" class="cn.javass.spring.chapter6.aop.BeforeAdviceImpl"/>

<aop:advisor pointcut="execution(* cn.javass..*.sayAdvisorBefore(..))"
 advice-ref="beforeAdvice"/>
 
不推荐使用Advisor，除了在进行事务控制的情况下，其他情况一般不推荐使用该方式，该方式属于侵入式设计，必须实现通知API。
!!!!!!
 
---

基于@AspectJ的AOP
 
Spring除了支持Schema方式配置AOP，还支持注解方式：使用@AspectJ风格的切面声明。
启用对@AspectJ的支持

Spring默认不支持@AspectJ风格的切面声明，为了支持需要使用如下配置：

<aop:aspectj-autoproxy/>

1. 声明切面
<http://www.importnew.com/17813.html>
@AspectJ风格的声明切面非常简单，使用@Aspect注解进行声明：
@Aspect() 
Public class Aspect{ 
…… 
}
然后将该切面在配置文件中声明为Bean后，Spring就能自动识别并进行AOP方面的配置：

<bean id="aspect" class="……Aspect"/>
该切面就是一个POJO，可以在该切面中进行切入点及通知定义

2. 声明切入点

@AspectJ风格的命名切入点使用org.aspectj.lang.annotation包下的@Pointcut+方法（方法必须是返回void类型）实现。

Pointcut(value="切入点表达式", argNames = "参数名列表") 
public void pointcutName(……) {}

value：指定切入点表达式；
argNames：指定命名切入点方法参数列表参数名字，可以有多个用“，”分隔，这些参数将传递给通知方法同名的参数，同时比如切入点表达式“args(param)”将匹配参数类型为命名切入点方法同名参数指定的参数类型。
pointcutName：切入点名字，可以使用该名字进行引用该切入点表达式。

@Pointcut(value="execution(* cn.javass..*.sayAdvisorBefore(..)) && args(param)", argNames = "param") 
public void beforePointcut(String param) {}

定义了一个切入点，名字为“beforePointcut”，该切入点将匹配目标方法的第一个参数类型为通知方法实现中参数名为“param”的参数类型。

+ 声明通知

@AspectJ风格的声明通知也支持5种通知类型：

一、前置通知：使用org.aspectj.lang.annotation 包下的@Before注解声明；

@Before(value = "切入点表达式或命名切入点", argNames = "参数列表参数名")
value：指定切入点表达式或命名切入点；
argNames：与Schema方式配置中的同义。

二、后置返回通知：使用org.aspectj.lang.annotation 包下的@AfterReturning注解声明
三、后置异常通知：使用org.aspectj.lang.annotation 包下的@AfterThrowing注解声明
四、后置最终通知：使用org.aspectj.lang.annotation 包下的@After注解声明
五、环绕通知：使用org.aspectj.lang.annotation 包下的@Around注解声明

---
AspectJ切入点语法详解
<http://www.importnew.com/17828.html>

通知参数<http://www.importnew.com/17858.html>
通知顺序<http://www.importnew.com/17886.html>
切面实例化模型<http://www.importnew.com/17900.html>

---

代理机制
<http://www.importnew.com/17911.html>

Spring AOP通过代理模式实现，目前支持两种代理：JDK动态代理、CGLIB代理来创建AOP代理，Spring建议优先使用JDK动态代理。

JDK动态代理：使用java.lang.reflect.Proxy动态代理实现，即提取目标对象的接口，然后对接口创建AOP代理。

CGLIB代理：CGLIB代理不仅能进行接口代理，也能进行类代理，CGLIB代理需要注意以下问题：

不能通知final方法，因为final方法不能被覆盖（CGLIB通过生成子类来创建代理）。

会产生两次构造器调用，第一次是目标类的构造器调用，第二次是CGLIB生成的代理类的构造器调用。如果需要CGLIB代理方法，请确保两次构造器调用不影响应用。

Spring AOP默认首先使用JDK动态代理来代理目标对象，如果目标对象没有实现任何接口将使用CGLIB代理，如果需要强制使用CGLIB代理，请使用如下方式指定：

对于Schema风格配置切面使用如下方式来指定使用CGLIB代理：

<aop:config proxy-target-class="true"> 
</aop:config>
而如果使用@AspectJ风格使用如下方式来指定使用CGLIB代理：

<aop:aspectj-autoproxy proxy-target-class="true"/>

---

深入剖析动态代理--性能比较<http://blog.csdn.net/liutengteng130/article/details/46565309>

<http://blog.csdn.net/Kingson_Wu/article/details/50864853>