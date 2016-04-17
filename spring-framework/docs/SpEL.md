<http://www.importnew.com/17692.html>

Spring表达式语言全称为“Spring Expression Language”，缩写为“SpEL”，类似于Struts2x中使用的OGNL表达式语言，
能在运行时构建复杂表达式、存取对象图属性、对象方法调用等等，并且能与Spring功能完美整合，如能用来配置Bean定义。

表达式语言给静态Java语言增加了动态功能。

SpEL是单独模块，只依赖于core模块，不依赖于其他模块，可以单独使用。

表达式语言一般是用最简单的形式完成最主要的工作，减少我们的工作量。

SpEL支持如下表达式：

一、基本表达式：字面量表达式、关系，逻辑与算数运算表达式、字符串连接及截取表达式、三目运算及Elivis表达式、正则表达式、括号优先级表达式；

二、类相关表达式：类类型表达式、类实例化、instanceof表达式、变量定义及引用、赋值表达式、自定义函数、对象属性存取及安全导航表达式、对象方法调用、Bean引用；

三、集合相关表达式：内联List、内联数组、集合，字典访问、列表，字典，数组修改、集合投影、集合选择；不支持多维内联数组初始化；不支持内联字典定义；

四、其他表达式：模板表达式。

---

SpEL原理及接口

SpEL提供简单的接口从而简化用户使用，在介绍原理前让我们学习下几个概念：

一、表达式：表达式是表达式语言的核心，所以表达式语言都是围绕表达式进行的，从我们角度来看是“干什么”；

二、解析器：用于将字符串表达式解析为表达式对象，从我们角度来看是“谁来干”；

三、上下文：表达式对象执行的环境，该环境可能定义变量、定义自定义函数、提供类型转换等等，从我们角度看是“在哪干”；

四、根对象及活动上下文对象：根对象是默认的活动上下文对象，活动上下文对象表示了当前表达式操作的对象，从我们角度看是“对谁干”。


1）ExpressionParser接口：表示解析器，默认实现是org.springframework.expression.spel.standard包中的SpelExpressionParser类，使用parseExpression方法将字符串表达式转换为Expression对象，对于ParserContext接口用于定义字符串表达式是不是模板，及模板开始与结束字符：

public interface ExpressionParser {
       Expression parseExpression(String expressionString);
       Expression parseExpression(String expressionString, ParserContext context);
}

2）EvaluationContext接口：表示上下文环境，默认实现是org.springframework.expression.spel.support包中的StandardEvaluationContext类，使用setRootObject方法来设置根对象，使用setVariable方法来注册自定义变量，使用registerFunction来注册自定义函数等等。

3）Expression接口：表示表达式对象，默认实现是org.springframework.expression.spel.standard包中的SpelExpression，提供getValue方法用于获取表达式值，提供setValue方法用于设置对象值。

---
Spring 表达式语言之 SpEL 语法
<http://www.importnew.com/17724.html>

---

在Bean定义中使用EL !!!!!!!!!!!!!!!
xml风格的配置
SpEL支持在Bean定义时注入，默认使用“#{SpEL表达式}”表示，其中“#root”根对象默认可以认为是ApplicationContext，
只有ApplicationContext实现默认支持SpEL，获取根对象属性其实是获取容器中的Bean。

<http://www.importnew.com/17741.html>

首先看下配置方式（chapter5/el1.xml）吧：

java代码：

<bean id="world" class="java.lang.String"> 
 <constructor-arg value="#{' World!'}"/> 
</bean> 
<bean id="hello1" class="java.lang.String"> 
 <constructor-arg value="#{'Hello'}#{world}"/> 
</bean> 
<bean id="hello2" class="java.lang.String"> 
 <constructor-arg value="#{'Hello' + world}"/> 
 <!-- 不支持嵌套的 --> 
 <!--<constructor-arg value="#{'Hello'#{world}}"/>--> 
</bean> 
<bean id="hello3" class="java.lang.String"> 
 <constructor-arg value="#{'Hello' + @world}"/> 
</bean>
模板默认以前缀“#{”开头，以后缀“}”结尾，且不允许嵌套，如“#{‘Hello’#{world}}”错误，如“#{‘Hello’ + world}”中“world”默认解析为Bean。当然可以使用“@bean”引用了。

接下来测试一下吧：

java代码：

@Test
public void testXmlExpression() { 
 ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter5/el1.xml"); 
 String hello1 = ctx.getBean("hello1", String.class); 
 String hello2 = ctx.getBean("hello2", String.class); 
 String hello3 = ctx.getBean("hello3", String.class); 
 Assert.assertEquals("Hello World!", hello1); 
 Assert.assertEquals("Hello World!", hello2); 
 Assert.assertEquals("Hello World!", hello3); 
}
是不是很简单，除了XML配置方式，Spring还提供一种注解方式@Value，接着往下看吧。

5.4.2 注解风格的配置


基于注解风格的SpEL配置也非常简单，使用@Value注解来指定SpEL表达式，该注解可以放到字段、方法及方法参数上。
测试Bean类如下，使用@Value来指定SpEL表达式：

java代码：

package cn.javass.spring.chapter5; 
import org.springframework.beans.factory.annotation.Value; 
public class SpELBean { 
 @Value("#{'Hello' + world}") 
 private String value; 
 //setter和getter由于篇幅省略，自己写上 
}
首先看下配置文件(chapter5/el2.xml)：

java代码：

<?xml version="1.0" encoding="UTF-8"?> 
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xmlns:context="http://www.springframework.org/schema/context"
 xsi:schemaLocation=" 
 
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context-3.0.xsd">
 
 <context:annotation-config/> 
 <bean id="world" class="java.lang.String"> 
 <constructor-arg value="#{' World!'}"/> 
 </bean> 
 <bean id="helloBean1" class="cn.javass.spring.chapter5.SpELBean"/> 
 <bean id="helloBean2" class="cn.javass.spring.chapter5.SpELBean"> 
 <property name="value" value="haha"/> 
 </bean> 
</beans>
配置时必须使用“<context:annotation-config/>”来开启对注解的支持。

有了配置文件那开始测试吧：

java代码：

@Test
public void testAnnotationExpression() { 
 ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter5/el2.xml"); 
 SpELBean helloBean1 = ctx.getBean("helloBean1", SpELBean.class); 
 Assert.assertEquals("Hello World!", helloBean1.getValue()); 
 SpELBean helloBean2 = ctx.getBean("helloBean2", SpELBean.class); 
 Assert.assertEquals("haha", helloBean2.getValue()); 
}
其中“helloBean1 ”值是SpEL表达式的值，而“helloBean2”是通过setter注入的值，这说明setter注入将覆盖@Value的值。

5.4.3 在Bean定义中SpEL的问题
如果有同学问“#{我不是SpEL表达式}”不是SpEL表达式，而是公司内部的模板，想换个前缀和后缀该如何实现呢？
那我们来看下Spring如何在IoC容器内使用BeanExpressionResolver接口实现来求值SpEL表达式，那如果我们通过某种方式获取该接口实现，然后把前缀后缀修改了不就可以了。

此处我们使用BeanFactoryPostProcessor接口提供postProcessBeanFactory回调方法，它是在IoC容器创建好但还未进行任何Bean初始化时被ApplicationContext实现调用，因此在这个阶段把SpEL前缀及后缀修改掉是安全的，具体代码如下：

java代码：

package cn.javass.spring.chapter5; 
import org.springframework.beans.BeansException; 
import org.springframework.beans.factory.config.BeanFactoryPostProcessor; 
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory; 
import org.springframework.context.expression.StandardBeanExpressionResolver; 
public class SpELBeanFactoryPostProcessor implements BeanFactoryPostProcessor { 
 @Override
 public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) 
 throws BeansException { 
 StandardBeanExpressionResolver resolver = (StandardBeanExpressionResolver) beanFactory.getBeanExpressionResolver(); 
 resolver.setExpressionPrefix("%{"); 
 resolver.setExpressionSuffix("}"); 
 } 
}
首先通过 ConfigurableListableBeanFactory的getBeanExpressionResolver方法获取BeanExpressionResolver实现，其次强制类型转换为StandardBeanExpressionResolver，其为Spring默认实现，然后改掉前缀及后缀。

开始测试吧，首先准备配置文件(chapter5/el3.xml)：

java代码：

<?xml version="1.0" encoding="UTF-8"?> 
<beans xmlns="http://www.springframework.org/schema/beans"
 xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
 xmlns:context="http://www.springframework.org/schema/context"
 xsi:schemaLocation=" 
 
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
http://www.springframework.org/schema/context
http://www.springframework.org/schema/context/spring-context-3.0.xsd">
 
 <context:annotation-config/> 
 <bean class="cn.javass.spring.chapter5.SpELBeanFactoryPostProcessor"/> 
 <bean id="world" class="java.lang.String"> 
 <constructor-arg value="%{' World!'}"/> 
 </bean> 
 <bean id="helloBean1" class="cn.javass.spring.chapter5.SpELBean"/> 
 <bean id="helloBean2" class="cn.javass.spring.chapter5.SpELBean"> 
 <property name="value" value="%{'Hello' + world}"/> 
 </bean> 
</beans>
配置文件和注解风格的几乎一样，只有SpEL表达式前缀变为“%{”了，并且注册了“cn.javass.spring.chapter5.SpELBeanFactoryPostProcessor”Bean，用于修改前缀和后缀的。
写测试代码测试一下吧：

java代码：

@Test
public void testPrefixExpression() { 
 ApplicationContext ctx = new ClassPathXmlApplicationContext("chapter5/el3.xml"); 
 SpELBean helloBean1 = ctx.getBean("helloBean1", SpELBean.class); 
 Assert.assertEquals("#{'Hello' + world}", helloBean1.getValue()); 
 SpELBean helloBean2 = ctx.getBean("helloBean2", SpELBean.class); 
 Assert.assertEquals("Hello World!", helloBean2.getValue()); 
}
此处helloBean1 中通过@Value注入的“#{‘Hello’ + world}”结果还是“#{‘Hello’ + world}”说明不对其进行SpEL表达式求值了，而helloBean2使用“%{‘Hello’ + world}”注入，得到正确的“”Hello World!”。
