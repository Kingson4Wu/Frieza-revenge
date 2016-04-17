<http://www.importnew.com/17552.html>
<http://www.importnew.com/17570.html>
+ 使用<alias>标签指定别名，别名也必须在IoC容器中唯一

```xml
<bean name="bean" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>
<alias alias="alias1" name="bean"/>
<alias alias="alias2" name="bean"/>
```


### 实例化Bean

Spring IoC容器如何实例化Bean呢？传统应用程序可以通过new和反射方式进行实例化Bean。
而Spring IoC容器则需要根据Bean定义里的配置元数据使用反射机制来创建Bean。

在Spring IoC容器中根据Bean定义创建Bean主要有以下几种方式:

1. 使用构造器实例化Bean：这是最简单的方式，Spring IoC容器即能使用默认空构造器也能使用有参数构造器两种方式创建Bean
```xml
<bean name="bean2" class="cn.javass.spring.chapter2.HelloImpl2">
<!-- 指定构造器参数 -->
    <constructor-arg index="0" value="Hello Spring!"/>
</bean>
```

2. 使用静态工厂方式实例化Bean，使用这种方式除了指定必须的class属性，还要指定factory-method属性来指定实例化Bean的方法，
而且使用静态工厂方法也允许指定方法参数，spring IoC容器将调用此属性指定的方法来获取Bean

<pre>
（1）先来看看静态工厂类代码吧HelloApiStaticFactory：

public class HelloApiStaticFactory {
    //工厂方法
    public static HelloApi newInstance(String message) {
    //返回需要的Bean实例
    return new HelloImpl2(message);
   }
}
 
（2）静态工厂写完了，让我们在配置文件(resources/chapter2/instantiatingBean.xml)配置Bean定义：

<bean id="bean3" class="cn.javass.spring.chapter2.HelloApiStaticFactory" factory-method="newInstance">
    <constructor-arg index="0" value="Hello Spring!"/>
</bean>

（3）配置完了，写段测试代码来测试一下吧，InstantiatingBeanTest：

@Test
public void testInstantiatingBeanByStaticFactory() {
    //使用静态工厂方法
    BeanFactory beanFactory =
        new ClassPathXmlApplicationContext("chaper2/instantiatingBean.xml");
    HelloApi bean3 = beanFactory.getBean("bean3", HelloApi.class);
    bean3.sayHello();
}
</pre>

3. 使用实例工厂方法实例化Bean，使用这种方式不能指定class属性，此时必须使用factory-bean属性来指定工厂Bean，
factory-method属性指定实例化Bean的方法，而且使用实例工厂方法允许指定方法参数，方式和使用构造器方式一样

<pre>
1）实例工厂类代码（HelloApiInstanceFactory.java）如下：

package cn.javass.spring.chapter2;
public class HelloApiInstanceFactory {
     public HelloApi newInstance(String message) {
         return new HelloImpl2(message);
     }
}

（2）让我们在配置文件(resources/chapter2/instantiatingBean.xml)配置Bean定义：

<!—1、定义实例工厂Bean -->
<bean id="beanInstanceFactory"
class="cn.javass.spring.chapter2.HelloApiInstanceFactory"/>
<!—2、使用实例工厂Bean创建Bean -->
<bean id="bean4"
factory-bean="beanInstanceFactory"
   factory-method="newInstance">
   <constructor-arg index="0" value="Hello Spring!"></constructor-arg>
</bean>

（3）测试代码InstantiatingBeanTest：

@Test
public void testInstantiatingBeanByInstanceFactory() {
    //使用实例工厂方法
    BeanFactory beanFactory =
        new ClassPathXmlApplicationContext("chapter2/instantiatingBean.xml");
    HelloApi bean4 = beanFactory.getBean("bean4", HelloApi.class);
    bean4.sayHello();
}
</pre>


4. setter注入，是通过在通过构造器、静态工厂或实例工厂实例好Bean后，通过调用Bean类的setter方法进行注入依赖
```xml
<!-- 通过setter方式进行依赖注入 -->
    <bean id="bean" class="cn.javass.spring.chapter3.HelloImpl4">
        <property name="message" value="Hello World!"/>
        <property name="index">
            <value>1</value>
        </property>
    </bean>
```

+ 注入常量
<pre>
Spring容器目前能对各种基本类型把配置的String参数转换为需要的类型。

注：Spring类型转换系统对于boolean类型进行了容错处理，除了可以使用“true/false”标准的Java值进行注入，还能使用“yes/no”、“on/off”、“1/0”来代表“真/假”，所以大家在学习或工作中遇到这种类似问题不要觉得是人家配置错了，而是Spring容错做的非常好。

测试类
public class BooleanTestBean {
    private boolean success;
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }
}
配置文件（chapter3/booleanInject.xml）片段：
<!-- boolean参数值可以用on/off -->
<bean id="bean2" class="cn.javass.spring.chapter3.bean.BooleanTestBean">
    <property name="success" value="on"/>
</bean>
<!-- boolean参数值可以用yes/no -->
<bean id="bean3" class="cn.javass.spring.chapter3.bean.BooleanTestBean">
    <property name="success" value="yes"/>
</bean>
<!-- boolean参数值可以用1/0 -->
<bean id="bean4" class="cn.javass.spring.chapter3.bean.BooleanTestBean">
    <property name="success" value="1"/>
</bean>

</pre>

+ 注入Bean ID
<property name="id"><idref bean="bean1"/></property>

+ 注入集合类型：包括Collection类型、Set类型、List类型数据
+ 注入数组类型 :需要使用<array>标签来配置注入
+ 注入字典（Map）类型：字典类型是包含键值对数据的数据结构，需要使用<map>标签来配置注入，其属性“key-type”和“value-type”分别指定“键”和“值”的数据类型
+ Properties注入：Spring能注入java.util.Properties类型数据，需要使用<props>标签来配置注入，键和值类型必须是String



5. 除了最基本配置方式以外，Spring还提供了另外两种更高级的配置方式，<ref local=””/>和<ref parent=””/>
+ <ref local=””/>配置方式：用于引用通过<bean id=”beanName”>方式中通过id属性指定的Bean，
它能利用XML解析器的验证功能在读取配置文件时来验证引用的Bean是否存在。
因此如果在当前配置文件中有相互引用的Bean可以采用<ref local>方式从而如果配置错误能在开发调试时就发现错误。

+ <ref parent=””/>配置方式：用于引用父容器中的Bean，不会引用当前容器中的Bean，
当然父容器中的Bean和当前容器的Bean是可以重名的，获取顺序是直接到父容器找

<pre>
<!-- sources/chapter3/parentBeanInject.xml表示父容器配置-->
<!--注意此处可能子容器也定义一个该Bean-->
<bean id="helloApi" class="cn.javass.spring.chapter3.HelloImpl4">
<property name="index" value="1"/>
<property name="message" value="Hello Parent!"/>
</bean>
 
<!-- sources/chapter3/localBeanInject.xml表示当前容器配置-->
<!-- 注意父容器中也定义了id 为 helloApi的Bean -->
<bean id="helloApi" class="cn.javass.spring.chapter3.HelloImpl4">
<property name="index" value="1"/>
    <property name="message" value="Hello Local!"/>
</bean>
<!-- 通过local注入 -->
<bean id="bean1" class="cn.javass.spring.chapter3.bean.HelloApiDecorator">
<constructor-arg index="0"><ref local="helloApi"/></constructor-arg>
</bean>
<!-- 通过parent注入 -->
<bean id="bean2" class="cn.javass.spring.chapter3.bean.HelloApiDecorator">
<property name="helloApi"><ref parent="helloApi"/></property>
</bean>

写测试类测试一下吧，具体代码片段如下：

@Test
public void testLocalAndparentBeanInject() {
//初始化父容器
ApplicationContext parentBeanContext =
new ClassPathXmlApplicationContext("chapter3/parentBeanInject.xml");
//初始化当前容器
ApplicationContext beanContext = new ClassPathXmlApplicationContext(
new String[] {"chapter3/localBeanInject.xml"}, parentBeanContext);
    HelloApi bean1 = beanContext.getBean("bean1", HelloApi.class);
    bean1.sayHello();//该Bean引用local bean
HelloApi bean2 = beanContext.getBean("bean2", HelloApi.class);
bean2.sayHello();//该Bean引用parent bean
}

“bean1”将输出“Hello Local!”表示引用当前容器的Bean，”bean2”将输出“Hello Paren!”，表示引用父容器的Bean
</pre>

6. 内部Bean定义
内部Bean就是在<property>或<constructor-arg>内通过<bean>标签定义的Bean，该Bean不管是否指定id或name，该Bean都会有唯一的匿名标识符，而且不能指定别名，该内部Bean对其他外部Bean不可见
```xml
<bean id="bean" class="cn.javass.spring.chapter3.bean.HelloApiDecorator">
<property name="helloApi">
<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>
</property>
</bean>
```
7. 处理null值
Spring通过<value>标签或value属性注入常量值，所有注入的数据都是字符串，那如何注入null值呢？
通过“null”值吗？当然不是因为如果注入“null”则认为是字符串。Spring通过<null/>标签注入null值。

8. 对象图导航注入支持
所谓对象图导航是指类似a.b.c这种点缀访问形式的访问或修改值。Spring支持对象图导航方式依赖注入。
对象图导航依赖注入有一个限制就是比如a.b.c对象导航图注入中a和b必须为非null值才能注入c，否则将抛出空指针异常。

Spring不仅支持对象的导航，还支持数组、列表、字典、Properties数据类型的导航，对Set数据类型无法支持，因为无法导航。

数组和列表数据类型可以用array[0]、list[1]导航，注意”[]”里的必须是数字，因为是按照索引进行导航，对于数组类型注意不要数组越界错误。
字典Map数据类型可以使用map[1]、map[str]进行导航，其中“[]”里的是基本类型，无法放置引用类型。

<pre>
首先准备测试类，在此我们需要三个测试类，以便实现对象图导航功能演示：

NavigationC类用于打印测试代码，从而观察配置是否正确；具体类如下所示：

package cn.javass.spring.chapter3.bean;
public class NavigationC {
    public void sayNavigation() {
        System.out.println("===navigation c");
    }
}
 

NavigationB类，包含对象和列表、Properties、数组字典数据类型导航，而且这些复合数据类型保存的条目都是对象，正好练习一下如何往复合数据类型中注入对象依赖。具体类如下所示：

package cn.javass.spring.chapter3.bean;
import java.util.List;
import java.util.Map;
import java.util.Properties;
public class NavigationB {
    private NavigationC navigationC;
    private List<NavigationC> list;
    private Properties properties;
    private NavigationC[] array = new NavigationC[1];
    private Map<String, NavigationC> map;
   //由于setter和getter方法占用太多空间，故省略，大家自己实现吧
}
 

NavigationA类是我们的前端类，通过对它的导航进行注入值，具体代码如下：

package cn.javass.spring.chapter3.bean;
public class NavigationA {
    private NavigationB navigationB;
    public void setNavigationB(NavigationB navigationB) {
        this.navigationB = navigationB;
    }
    public NavigationB getNavigationB() {
        return navigationB;
    }
}
 

接下来该进行Bean定义配置（resources/chapter3/navigationBeanInject.xml）了，首先让我们配置一下需要被导航的数据，NavigationC和NavigationB类，其中配置NavigationB时注意要确保比如array字段不为空值，这就需要或者在代码中赋值如“NavigationC[] array = new NavigationC[1];”，或者通过配置文件注入如“<list></list>”注入一个不包含条目的列表。具体配置如下：

<bean id="c" class="cn.javass.spring.chapter3.bean.NavigationC"/>
<bean id="b" class="cn.javass.spring.chapter3.bean.NavigationB">
<property name="list"><list></list></property>
    <property name="map"><map></map></property>
    <property name="properties"><props></props></property>
</bean>
 

配置完需要被导航的Bean定义了，该来配置NavigationA导航Bean了，在此需要注意，由于“navigationB”属性为空值，在此需要首先注入“navigationB”值；还有对于数组导航不能越界否则报错；具体配置如下：

<bean id="a" class="cn.javass.spring.chapter3.bean.NavigationA">
<!-- 首先注入navigatiionB 确保它非空 -->
<property name="navigationB" ref="b"/>
<!-- 对象图导航注入 -->
<property name="navigationB.navigationC" ref="c"/>
<!-- 注入列表数据类型数据 -->
<property name="navigationB.list[0]" ref="c"/>
<!-- 注入map类型数据 -->
<property name="navigationB.map[key]" ref="c"/>
<!-- 注入properties类型数据 -->
<property name="navigationB.properties[0]" ref="c"/>
<!-- 注入properties类型数据 -->
<property name="navigationB.properties[1]" ref="c"/>
<!-- 注入数组类型数据 ，注意不要越界-->
<property name="navigationB.array[0]" ref="c"/>
</bean>
 

配置完毕，具体测试代码在cn.javass.spring.chapter3. DependencyInjectTest，让我们看下测试代码吧：

//对象图导航
@Test
public void testNavigationBeanInject() {
ApplicationContext context =
new ClassPathXmlApplicationContext("chapter3/navigationBeanInject.xml");       
NavigationA navigationA = context.getBean("a", NavigationA.class);
navigationA.getNavigationB().getNavigationC().sayNavigation();
navigationA.getNavigationB().getList().get(0).sayNavigation();
navigationA.getNavigationB().getMap().get("key").sayNavigation();
navigationA.getNavigationB().getArray()[0].sayNavigation();
((NavigationC)navigationA.getNavigationB().getProperties().get("1"))
.sayNavigation();       
}
 

测试完毕，应该输出5个“===navigation c”，是不是很简单，注意这种方式是不推荐使用的，了解一下就够了

</pre>


9. 使用p命名空间简化setter注入
<pre>
ImportNew
首页所有文章资讯Web架构基础技术书籍教程我要投稿更多频道 »
跟我学Spring3（3.1）:DI的配置使用
2016/01/24 | 分类： 未分类 | 0 条评论
分享到： 3
原文出处： 张开涛
本系列：

跟我学 Spring 3（1）： Spring 概述

跟我学 Spring 3（2.1）：IoC 基础 

跟我学Spring3（2.2）：IoC容器基本原理

跟我学Spring3（2.3）：IoC容器基本原理

 

3.1.1  依赖和依赖注入

传统应用程序设计中所说的依赖一般指“类之间的关系”，那先让我们复习一下类之间的关系：

泛化：表示类与类之间的继承关系、接口与接口之间的继承关系；

实现：表示类对接口的实现；

依赖：当类与类之间有使用关系时就属于依赖关系，不同于关联关系，依赖不具有“拥有关系”，而是一种“相识关系”，只在某个特定地方（比如某个方法体内）才有关系。

关联：表示类与类或类与接口之间的依赖关系，表现为“拥有关系”；具体到代码可以用实例变量来表示；

聚合：属于是关联的特殊情况，体现部分-整体关系，是一种弱拥有关系；整体和部分可以有不一样的生命周期；是一种弱关联；

组合：属于是关联的特殊情况，也体现了体现部分-整体关系，是一种强“拥有关系”；整体与部分有相同的生命周期，是一种强关联；

Spring IoC容器的依赖有两层含义：Bean依赖容器和容器注入Bean的依赖资源：

Bean依赖容器：也就是说Bean要依赖于容器，这里的依赖是指容器负责创建Bean并管理Bean的生命周期，正是由于由容器来控制创建Bean并注入依赖，也就是控制权被反转了，这也正是IoC名字的由来，此处的有依赖是指Bean和容器之间的依赖关系。

容器注入Bean的依赖资源：容器负责注入Bean的依赖资源，依赖资源可以是Bean、外部文件、常量数据等，在Java中都反映为对象，并且由容器负责组装Bean之间的依赖关系，此处的依赖是指Bean之间的依赖关系，可以认为是传统类与类之间的“关联”、“聚合”、“组合”关系。

 

为什么要应用依赖注入，应用依赖注入能给我们带来哪些好处呢？

动态替换Bean依赖对象，程序更灵活：替换Bean依赖对象，无需修改源文件：应用依赖注入后，由于可以采用配置文件方式实现，从而能随时动态的替换Bean的依赖对象，无需修改java源文件；

更好实践面向接口编程，代码更清晰：在Bean中只需指定依赖对象的接口，接口定义依赖对象完成的功能，通过容器注入依赖实现；

更好实践优先使用对象组合，而不是类继承：因为IoC容器采用注入依赖，也就是组合对象，从而更好的实践对象组合。

采用对象组合，Bean的功能可能由几个依赖Bean的功能组合而成，其Bean本身可能只提供少许功能或根本无任何功能，全部委托给依赖Bean，对象组合具有动态性，能更方便的替换掉依赖Bean，从而改变Bean功能；
而如果采用类继承，Bean没有依赖Bean，而是采用继承方式添加新功能，，而且功能是在编译时就确定了，不具有动态性，而且采用类继承导致Bean与子Bean之间高度耦合，难以复用。
增加Bean可复用性：依赖于对象组合，Bean更可复用且复用更简单；

降低Bean之间耦合：由于我们完全采用面向接口编程，在代码中没有直接引用Bean依赖实现，全部引用接口，而且不会出现显示的创建依赖对象代码，而且这些依赖是由容器来注入，很容易替换依赖实现类，从而降低Bean与依赖之间耦合；

代码结构更清晰：要应用依赖注入，代码结构要按照规约方式进行书写，从而更好的应用一些最佳实践，因此代码结构更清晰。

 

从以上我们可以看出，其实依赖注入只是一种装配对象的手段，设计的类结构才是基础，如果设计的类结构不支持依赖注入，Spring IoC容器也注入不了任何东西，从而从根本上说“如何设计好类结构才是关键，依赖注入只是一种装配对象手段”。

前边IoC一章我们已经了解了Bean依赖容器，那容器如何注入Bean的依赖资源，Spring IoC容器注入依赖资源主要有以下两种基本实现方式：

构造器注入：就是容器实例化Bean时注入那些依赖，通过在在Bean定义中指定构造器参数进行注入依赖，包括实例工厂方法参数注入依赖，但静态工厂方法参数不允许注入依赖；

setter注入：通过setter方法进行注入依赖；

方法注入：能通过配置方式替换掉Bean方法，也就是通过配置改变Bean方法 功能。

 

我们已经知道注入实现方式了，接下来让我们来看看具体配置吧。

 

3.1.2  构造器注入

使用构造器注入通过配置构造器参数实现，构造器参数就是依赖。除了构造器方式，还有静态工厂、实例工厂方法可以进行构造器注入。如图3-1所示：



图3-1 实例化

构造器注入可以根据参数索引注入、参数类型注入或Spring3支持的参数名注入，但参数名注入是有限制的，需要使用在编译程序时打开调试模式（即在编译时使用“javac –g:vars”在class文件中生成变量调试信息，默认是不包含变量调试信息的，从而能获取参数名字，否则获取不到参数名字）或在构造器上使用@ConstructorProperties（java.beans.ConstructorProperties）注解来指定参数名。

首先让我们准备测试构造器类HelloImpl3.java，该类只有一个包含两个参数的构造器：

 
package cn.javass.spring.chapter3.helloworld;
public class HelloImpl3 implements HelloApi {
    private String message;
private int index;
//@java.beans.ConstructorProperties({"message", "index"})
    public HelloImpl3(String message, int index) {
        this.message = message;
        this.index = index;
    }
    @Override
    public void sayHello() {
        System.out.println(index + ":" + message);
    }
}
 

一、根据参数索引注入，使用标签“<constructor-arg index=”1″ value=”1″/>”来指定注入的依赖，其中“index”表示索引，从0开始，即第一个参数索引为0，“value”来指定注入的常量值，配置方式如下：



 

二、根据参数类型进行注入，使用标签“<constructor-arg type=”java.lang.String” value=”Hello World!”/>”来指定注入的依赖，其中“type”表示需要匹配的参数类型，可以是基本类型也可以是其他类型，如“int”、“java.lang.String”，“value”来指定注入的常量值，配置方式如下：



 

三、根据参数名进行注入，使用标签“<constructor-arg name=”message” value=”Hello World!”/>”来指定注入的依赖，其中“name”表示需要匹配的参数名字，“value”来指定注入的常量值，配置方式如下：



 

四、让我们来用具体的例子来看一下构造器注入怎么使用吧。

（1）首先准备Bean类，在此我们就使用“HelloImpl3”这个类。

（2）有了Bean类，接下来要进行Bean定义配置，我们需要配置三个Bean来完成上述三种依赖注入测试，其中Bean ”byIndex”是通过索引注入依赖；Bean ”byType”是根据类型进行注入依赖；Bean ”byName”是根据参数名字进行注入依赖，具体配置文件（resources/chapter3/ constructorDependencyInject.xml）如下：


<!-- 通过构造器参数索引方式依赖注入 -->
<bean id="byIndex" class="cn.javass.spring.chapter3.HelloImpl3">
<constructor-arg index="0" value="Hello World!"/>
    <constructor-arg index="1" value="1"/>
</bean>
<!-- 通过构造器参数类型方式依赖注入 -->
<bean id="byType" class="cn.javass.spring.chapter3.HelloImpl3">
   <constructor-arg type="java.lang.String" value="Hello World!"/>
   <constructor-arg type="int" value="2"/>
</bean>
<!-- 通过构造器参数名称方式依赖注入 -->
<bean id="byName" class="cn.javass.spring.chapter3.HelloImpl3">
   <constructor-arg name="message" value="Hello World!"/>
   <constructor-arg name="index" value="3"/>
</bean>
（3）配置完毕后，在测试之前，因为我们使用了通过构造器参数名字注入方式，请确保编译时class文件包含“变量信息”，具体查看编译时是否包含“变量调试信息”请右击项目，在弹出的对话框选择属性；然后在弹出的对话框选择“Java Compiler”条目，在“Class 文件 生成”框中选择“添加变量信息到Class文件（调试器使用）”，具体如图3-2：



图3-2 编译时打开“添加变量信息选项”

（4）接下来让我们测试一下配置是否工作，具体测试代码（cn.javass.spring.chapter3. DependencyInjectTest）如下：

 
@Test
public void testConstructorDependencyInjectTest() {      
BeanFactory beanFactory =  new ClassPathXmlApplicationContext("chapter3/constructorDependencyInject.xml");
//获取根据参数索引依赖注入的Bean
HelloApi byIndex = beanFactory.getBean("byIndex", HelloApi.class);
byIndex.sayHello();
//获取根据参数类型依赖注入的Bean
HelloApi byType = beanFactory.getBean("byType", HelloApi.class);
byType.sayHello();
//获取根据参数名字依赖注入的Bean
HelloApi byName = beanFactory.getBean("byName", HelloApi.class);
byName.sayHello();
}
通过以上测试我们已经会基本的构造器注入配置了，在测试通过参数名字注入时，除了可以使用以上方式，还可以通过在构造器上添加@java.beans.ConstructorProperties({“message”, “index”})注解来指定参数名字，在HelloImpl3构造器上把注释掉的“ConstructorProperties”打开就可以了，这个就留给大家做练习，自己配置然后测试一下。

 

五、大家已经会了构造器注入，那让我们再看一下静态工厂方法注入和实例工厂注入吧，其实它们注入配置是完全一样，在此我们只示范一下静态工厂注入方式和实例工厂方式配置，测试就留给大家自己练习：

（1）静态工厂类

//静态工厂类
package cn.javass.spring.chapter3;
import cn.javass.spring.chapter2.helloworld.HelloApi;
public class DependencyInjectByStaticFactory {
       public static HelloApi newInstance(String message, int index) {
              return new HelloImpl3(message, index);
       }
}
 

静态工厂类Bean定义配置文件（chapter3/staticFactoryDependencyInject.xml）

<bean id="byIndex"
class="cn.javass.spring.chapter3.DependencyInjectByStaticFactory" factory-method="newInstance">
<constructor-arg index="0" value="Hello World!"/>
<constructor-arg index="1" value="1"/>
</bean>
<bean id="byType"
class="cn.javass.spring.chapter3.DependencyInjectByStaticFactory" factory-method="newInstance">
<constructor-arg type="java.lang.String" value="Hello World!"/>
       <constructor-arg type="int" value="2"/>
</bean>
<bean id="byName"
class="cn.javass.spring.chapter3.DependencyInjectByStaticFactory" factory-method="newInstance">
       <constructor-arg name="message" value="Hello World!"/>
       <constructor-arg name="index" value="3"/>
</bean>
 

（2）实例工厂类


//实例工厂类
package cn.javass.spring.chapter3;
import cn.javass.spring.chapter2.helloworld.HelloApi;
public class DependencyInjectByInstanceFactory {
    public HelloApi newInstance(String message, int index) {
        return new HelloImpl3(message, index);
    }
}
 

实例工厂类Bean定义配置文件（chapter3/instanceFactoryDependencyInject.xml）


<bean id="instanceFactory"
class="cn.javass.spring.chapter3.DependencyInjectByInstanceFactory"/>
 
<bean id="byIndex"
factory-bean="instanceFactory"  factory-method="newInstance">
    <constructor-arg index="0" value="Hello World!"/>
       <constructor-arg index="1" value="1"/>
</bean>
<bean id="byType"
factory-bean="instanceFactory" factory-method="newInstance">
<constructor-arg type="java.lang.String" value="Hello World!"/>
<constructor-arg type="int" value="2"/>
</bean>
<bean id="byName"
factory-bean="instanceFactory" factory-method="newInstance">
<constructor-arg name="message" value="Hello World!"/>
<constructor-arg name="index" value="3"/>
</bean>
 

（3）测试代码和构造器方式完全一样，只是配置文件不一样，大家只需把测试文件改一下就可以了。还有一点需要大家注意就是静态工厂方式和实例工厂方式根据参数名字注入的方式只支持通过在class文件中添加“变量调试信息”方式才能运行，ConstructorProperties注解方式不能工作，它只对构造器方式起作用，不建议使用根据参数名进行构造器注入。

 

3.1.3  setter注入

setter注入，是通过在通过构造器、静态工厂或实例工厂实例好Bean后，通过调用Bean类的setter方法进行注入依赖，如图3-3所示：



图3-3 setter注入方式

setter注入方式只有一种根据setter名字进行注入：



知道配置方式了，接下来先让我们来做个简单例子吧。

（1）准备测试类HelloImpl4，需要两个setter方法“setMessage”和“setIndex”：

package cn.javass.spring.chapter3;
import cn.javass.spring.chapter2.helloworld.HelloApi;
public class HelloImpl4 implements HelloApi {
    private String message;
    private int index;
//setter方法
    public void setMessage(String message) {
        this.message = message;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    @Override
    public void sayHello() {
        System.out.println(index + ":" + message);
    }
}
 

（2）配置Bean定义，具体配置文件（resources/chapter3/setterDependencyInject.xml）片段如下：

<!-- 通过setter方式进行依赖注入 -->
    <bean id="bean" class="cn.javass.spring.chapter3.HelloImpl4">
        <property name="message" value="Hello World!"/>
        <property name="index">
            <value>1</value>
        </property>
    </bean>
 

（3）该写测试进行测试一下是否满足能工作了，其实测试代码一点没变，变的是配置：

 

 

知道如何配置了，但Spring如何知道setter方法？如何将值注入进去的呢？其实方法名是要遵守约定的，setter注入的方法名要遵循“JavaBean getter/setter 方法命名约定”：

 

 

 

JavaBean：是本质就是一个POJO类，但具有一下限制：

该类必须要有公共的无参构造器，如public HelloImpl4() {}；

属性为private访问级别，不建议public，如private String message;

属性必要时通过一组setter（修改器）和getter（访问器）方法来访问；

setter方法，以“set” 开头，后跟首字母大写的属性名，如“setMesssage”,简单属性一般只有一个方法参数，方法返回值通常为“void”;

getter方法，一般属性以“get”开头，对于boolean类型一般以“is”开头，后跟首字母大写的属性名，如“getMesssage”，“isOk”；

还有一些其他特殊情况，比如属性有连续两个大写字母开头，如“URL”,则setter/getter方法为：“setURL”和“getURL”，其他一些特殊情况请参看“Java Bean”命名规范。

 

 

3.1.4  注入常量

注入常量是依赖注入中最简单的。配置方式如下所示：

<property name="message" value="Hello World!"/>
或
<property name="index"><value>1</value></property><span class="Apple-style-span" style="font-size: 14px; white-space: normal; background-color: #ffffff;"> </span>
以上两种方式都可以，从配置来看第一种更简洁。注意此处“value”中指定的全是字符串，由Spring容器将此字符串转换成属性所需要的类型，如果转换出错，将抛出相应的异常。

Spring容器目前能对各种基本类型把配置的String参数转换为需要的类型。

注：Spring类型转换系统对于boolean类型进行了容错处理，除了可以使用“true/false”标准的Java值进行注入，还能使用“yes/no”、“on/off”、“1/0”来代表“真/假”，所以大家在学习或工作中遇到这种类似问题不要觉得是人家配置错了，而是Spring容错做的非常好。

测试类
public class BooleanTestBean {
    private boolean success;
    public void setSuccess(boolean success) {
        this.success = success;
    }
    public boolean isSuccess() {
        return success;
    }
}
配置文件（chapter3/booleanInject.xml）片段：
<!-- boolean参数值可以用on/off -->
<bean id="bean2" class="cn.javass.spring.chapter3.bean.BooleanTestBean">
    <property name="success" value="on"/>
</bean>
<!-- boolean参数值可以用yes/no -->
<bean id="bean3" class="cn.javass.spring.chapter3.bean.BooleanTestBean">
    <property name="success" value="yes"/>
</bean>
<!-- boolean参数值可以用1/0 -->
<bean id="bean4" class="cn.javass.spring.chapter3.bean.BooleanTestBean">
    <property name="success" value="1"/>
</bean>
 

3.1.5    注入Bean ID

用于注入Bean的ID，ID是一个常量不是引用，且类似于注入常量，但提供错误验证功能，配置方式如下所示：

<property name="id"><idref bean="bean1"/></property>
<property name="id"><idref local="bean2"/></property>
 

两种方式都可以，上述配置本质上在运行时等于如下方式

<bean id="bean1" class="……"/>
<bean id="idrefBean1" class="……">
<property name="id" value ="bean1"/>
</bean>
第二种方式（<idref bean=”bean1″/>）可以在容器初始化时校验被引用的Bean是否存在，如果不存在将抛出异常，而第一种方式（<idref local=”bean2″/>）只有在Bean实际使用时才能发现传入的Bean的ID是否正确，可能发生不可预料的错误。因此如果想注入Bean的ID，推荐使用第二种方式。

接下来学习一下如何使用吧：

首先定义测试Bean：

package cn.javass.spring.chapter3.bean
public class IdRefTestBean {
    private String id;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
}
 

其次定义配置文件（chapter3/idRefInject.xml）：

<bean id="bean1" class="java.lang.String">
<constructor-arg index="0" value="test"/>
</bean>
<bean id="bean2" class="java.lang.String">
    <constructor-arg index="0" value="test"/>
</bean>
 

<bean id="idrefBean1" class="cn.javass.spring.chapter3.bean.IdRefTestBean">
        <property name="id"><idref bean="bean1"/></property>
</bean>
<bean id="idrefBean2" class="cn.javass.spring.chapter3.bean.IdRefTestBean">
    <property name="id"><idref local="bean2"/></property>
</bean>
 

从配置中可以看出，注入的Bean的ID是一个java.lang.String类型，即字符串类型，因此注入的同样是常量，只是具有校验功能。

<idref bean=”……”/>将在容器初始化时校验注入的ID对于的Bean是否存在，如果不存在将抛出异常。

<idref local=”……”/>将在XML解析时校验注入的ID对于的Bean在当前配置文件中是否存在，如果不存在将抛出异常，它不同于<idref bean=”……”/>，<idref local=”……”/>是校验发生在XML解析式而非容器初始化时，且只检查当前配置文件中是否存在相应的Bean。

3.1.6  注入集合、数组和字典

Spring不仅能注入简单类型数据，还能注入集合（Collection、无序集合Set、有序集合List）类型、数组(Array)类型、字典(Map)类型数据、Properties类型数据，接下来就让我们一个个看看如何注入这些数据类型的数据。

       一、注入集合类型：包括Collection类型、Set类型、List类型数据：

       （1）List类型：需要使用<list>标签来配置注入，其具体配置如下：



让我们来写个测试来练习一下吧：

准备测试类：

package cn.javass.spring.chapter3.bean;
import java.util.List;
public class ListTestBean {
    private List<String> values;
    public List<String> getValues() {
        return values;
    }
    public void setValues(List<String> values) {
        this.values = values;
    }
}
 

进行Bean定义，在配置文件（resources/chapter3/listInject.xml）中配置list注入：

<bean id="listBean" class="cn.javass.spring.chapter3.bean.ListTestBean">
    <property name="values">
        <list>
            <value>1</value>
            <value>2</value>
            <value>3</value>
        </list>
   </property>
</bean>
              

测试代码：

@Test
public void testListInject() {
   BeanFactory beanFactory =
new ClassPathXmlApplicationContext("chapter3/listInject.xml");
ListTestBean listBean = beanFactory.getBean("listBean", ListTestBean.class);
System.out.println(listBean.getValues().size());
Assert.assertEquals(3, listBean.getValues().size());
}
 

（2）Set类型：需要使用<set>标签来配置注入，其配置参数及含义和<lsit>标签完全一样，在此就不阐述了：

准备测试类：

package cn.javass.spring.chapter3.bean;
import java.util.Collection;
public class CollectionTestBean {
    private Collection<String> values;
    public void setValues(Collection<String> values) {
        this.values = values;
    }
    public Collection<String> getValues() {
        return values;
    }
}
 

进行Bean定义，在配置文件（resources/chapter3/listInject.xml）中配置list注入：

<bean id="setBean" class="cn.javass.spring.chapter3.bean.SetTestBean">
<property name="values">
<set>
<value>1</value>
<value>2</value>
<value>3</value>
</set>
</property>
</bean>
 

具体测试代码就不写了，和listBean测试代码完全一样。

 

（2）Collection类型：因为Collection类型是Set和List类型的基类型，所以使用<set>或<list>标签都可以进行注入，配置方式完全和以上配置方式一样，只是将测试类属性改成“Collection”类型，如果配置有问题，可参考cn.javass.spring.chapter3.DependencyInjectTest测试类中的testCollectionInject测试方法中的代码。

二、注入数组类型：需要使用<array>标签来配置注入，其中标签属性“value-type”和“merge”和<list>标签含义完全一样，具体配置如下：



如果练习时遇到配置问题，可以参考“cn.javass.spring.chapter3.DependencyInjectTest”测试类中的testArrayInject测试方法中的代码。

 

       三、注入字典（Map）类型：字典类型是包含键值对数据的数据结构，需要使用<map>标签来配置注入，其属性“key-type”和“value-type”分别指定“键”和“值”的数据类型，其含义和<list>标签的“value-type”含义一样，在此就不罗嗦了，并使用<key>子标签来指定键数据，<value>子标签来指定键对应的值数据，具体配置如下：



如果练习时遇到配置问题，可以参考“cn.javass.spring.chapter3.DependencyInjectTest”测试类中的testMapInject测试方法中的代码。

四、Properties注入：Spring能注入java.util.Properties类型数据，需要使用<props>标签来配置注入，键和值类型必须是String，不能变，子标签<prop key=”键”>值</prop>来指定键值对，具体配置如下：





如果练习时遇到配置问题，可以参考cn.javass.spring.chapter3.DependencyInjectTest测试类中的testPropertiesInject测试方法中的代码。

到此我们已经把简单类型及集合类型介绍完了，大家可能会问怎么没见注入“Bean之间关系”的例子呢？接下来就让我们来讲解配置Bean之间依赖关系，也就是注入依赖Bean。

 

3.1.7  引用其它Bean

上边章节已经介绍了注入常量、集合等基本数据类型和集合数据类型，本小节将介绍注入依赖Bean及注入内部Bean。

引用其他Bean的步骤与注入常量的步骤一样，可以通过构造器注入及setter注入引用其他Bean，只是引用其他Bean的注入配置稍微变化了一下：可以将“<constructor-arg index=”0″ value=”Hello World!”/>”和“<property name=”message” value=”Hello World!”/>”中的value属性替换成bean属性，其中bean属性指定配置文件中的其他Bean的id或别名。另一种是把<value>标签替换为<.ref bean=”beanName”>，bean属性也是指定配置文件中的其他Bean的id或别名。那让我们看一下具体配置吧：

 

一、构造器注入方式：

（1）通过” <constructor-arg>”标签的ref属性来引用其他Bean，这是最简化的配置：



（2）通过” <constructor-arg>”标签的子<ref>标签来引用其他Bean，使用bean属性来指定引用的Bean：



       二、setter注入方式：

（1）通过” <property>”标签的ref属性来引用其他Bean，这是最简化的配置：



（2）通过” <property>”标签的子<ref>标签来引用其他Bean，使用bean属性来指定引用的Bean：



 

三、接下来让我们用个具体例子来讲解一下具体使用吧：

（1）首先让我们定义测试引用Bean的类，在此我们可以使用原有的HelloApi实现，然后再定义一个装饰器来引用其他Bean，具体装饰类如下：

package cn.javass.spring.chapter3.bean;
import cn.javass.spring.chapter2.helloworld.HelloApi;
public class HelloApiDecorator implements HelloApi {
private HelloApi helloApi;
//空参构造器
    public HelloApiDecorator() {
}
//有参构造器
    public HelloApiDecorator(HelloApi helloApi) {
        this.helloApi = helloApi;
}  
public void setHelloApi(HelloApi helloApi) {
        this.helloApi = helloApi;
    }
    @Override
    public void sayHello() {
        System.out.println("==========装饰一下===========");
        helloApi.sayHello();
        System.out.println("==========装饰一下===========");
    }
}
 

（2）定义好了测试引用Bean接下来该在配置文件(resources/chapter3/beanInject.xml)进行配置Bean定义了，在此将演示通过构造器及setter方法方式注入依赖Bean：

<!-- 定义依赖Bean -->
<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>
<!-- 通过构造器注入 -->
<bean id="bean1" class="cn.javass.spring.chapter3.bean.HelloApiDecorator">
<constructor-arg index="0" ref="helloApi"/>
</bean>
<!-- 通过构造器注入 -->
<bean id="bean2" class="cn.javass.spring.chapter3.bean.HelloApiDecorator">
    <property name="helloApi"><ref bean=" helloApi"/></property>
</bean>
 

 

（3）测试一下吧，测试代码(cn.javass.spring.chapter3.DependencyInjectTest)片段如下：
@Test
public void testBeanInject() {
    BeanFactory beanFactory =
new ClassPathXmlApplicationContext("chapter3/beanInject.xml");
    //通过构造器方式注入
    HelloApi bean1 = beanFactory.getBean("bean1", HelloApi.class);
    bean1.sayHello();
    //通过setter方式注入
    HelloApi bean2 = beanFactory.getBean("bean2", HelloApi.class);
    bean2.sayHello();
}
 

四、其他引用方式：除了最基本配置方式以外，Spring还提供了另外两种更高级的配置方式，<ref local=””/>和<ref parent=””/>：

（1）<ref local=””/>配置方式：用于引用通过<bean id=”beanName”>方式中通过id属性指定的Bean，它能利用XML解析器的验证功能在读取配置文件时来验证引用的Bean是否存在。因此如果在当前配置文件中有相互引用的Bean可以采用<ref local>方式从而如果配置错误能在开发调试时就发现错误。

如果引用一个在当前配置文件中不存在的Bean将抛出如下异常：

org.springframework.beans.factory.xml.XmlBeanDefinitionStoreException: Line21 inXML document from class path resource [chapter3/beanInject2.xml] is invalid; nested exception is org.xml.sax.SAXParseException: cvc-id.1: There is no ID/IDREF binding for IDREF ‘helloApi’.

<ref local>具体配置方式如下：



（2）<ref parent=””/>配置方式：用于引用父容器中的Bean，不会引用当前容器中的Bean，当然父容器中的Bean和当前容器的Bean是可以重名的，获取顺序是直接到父容器找。具体配置方式如下：



接下来让我们用个例子演示一下<ref local>和<ref parent>的配置过程：

首先还是准备测试类，在此我们就使用以前写好的HelloApiDecorator和HelloImpl4类；其次进行Bean定义，其中当前容器bean1引用本地的”helloApi”，而”bean2”将引用父容器的”helloApi”，配置如下：

<!-- sources/chapter3/parentBeanInject.xml表示父容器配置-->
<!--注意此处可能子容器也定义一个该Bean-->
<bean id="helloApi" class="cn.javass.spring.chapter3.HelloImpl4">
<property name="index" value="1"/>
<property name="message" value="Hello Parent!"/>
</bean>
 

<!-- sources/chapter3/localBeanInject.xml表示当前容器配置-->
<!-- 注意父容器中也定义了id 为 helloApi的Bean -->
<bean id="helloApi" class="cn.javass.spring.chapter3.HelloImpl4">
<property name="index" value="1"/>
    <property name="message" value="Hello Local!"/>
</bean>
<!-- 通过local注入 -->
<bean id="bean1" class="cn.javass.spring.chapter3.bean.HelloApiDecorator">
<constructor-arg index="0"><ref local="helloApi"/></constructor-arg>
</bean>
<!-- 通过parent注入 -->
<bean id="bean2" class="cn.javass.spring.chapter3.bean.HelloApiDecorator">
<property name="helloApi"><ref parent="helloApi"/></property>
</bean>
 

（3）写测试类测试一下吧，具体代码片段如下：

@Test
public void testLocalAndparentBeanInject() {
//初始化父容器
ApplicationContext parentBeanContext =
new ClassPathXmlApplicationContext("chapter3/parentBeanInject.xml");
//初始化当前容器
ApplicationContext beanContext = new ClassPathXmlApplicationContext(
new String[] {"chapter3/localBeanInject.xml"}, parentBeanContext);
    HelloApi bean1 = beanContext.getBean("bean1", HelloApi.class);
    bean1.sayHello();//该Bean引用local bean
HelloApi bean2 = beanContext.getBean("bean2", HelloApi.class);
bean2.sayHello();//该Bean引用parent bean
}
 

“bean1”将输出“Hello Local!”表示引用当前容器的Bean，”bean2”将输出“Hello Paren!”，表示引用父容器的Bean，如配置有问题请参考cn.javass.spring.chapter3.DependencyInjectTest中的testLocalAndparentBeanInject测试方法。

3.1.8  内部Bean定义

内部Bean就是在<property>或<constructor-arg>内通过<bean>标签定义的Bean，该Bean不管是否指定id或name，该Bean都会有唯一的匿名标识符，而且不能指定别名，该内部Bean对其他外部Bean不可见，具体配置如下：



（1）让我们写个例子测试一下吧，具体配置文件如下：


<bean id="bean" class="cn.javass.spring.chapter3.bean.HelloApiDecorator">
<property name="helloApi">
<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>
</property>
</bean>
 

（2）测试代码（cn.javass.spring.chapter3.DependencyInjectTest.testInnerBeanInject）：

@Test
public void testInnerBeanInject() {
ApplicationContext context =
new ClassPathXmlApplicationContext("chapter3/innerBeanInject.xml");
HelloApi bean = context.getBean("bean", HelloApi.class);
bean.sayHello();
}
 

3.1.9  处理null值

Spring通过<value>标签或value属性注入常量值，所有注入的数据都是字符串，那如何注入null值呢？通过“null”值吗？当然不是因为如果注入“null”则认为是字符串。Spring通过<null/>标签注入null值。即可以采用如下配置方式：



3.1.10 对象图导航注入支持

所谓对象图导航是指类似a.b.c这种点缀访问形式的访问或修改值。Spring支持对象图导航方式依赖注入。对象图导航依赖注入有一个限制就是比如a.b.c对象导航图注入中a和b必须为非null值才能注入c，否则将抛出空指针异常。

Spring不仅支持对象的导航，还支持数组、列表、字典、Properties数据类型的导航，对Set数据类型无法支持，因为无法导航。

数组和列表数据类型可以用array[0]、list[1]导航，注意”[]”里的必须是数字，因为是按照索引进行导航，对于数组类型注意不要数组越界错误。

字典Map数据类型可以使用map[1]、map[str]进行导航，其中“[]”里的是基本类型，无法放置引用类型。

让我们来练习一下吧。首先准备测试类，在此我们需要三个测试类，以便实现对象图导航功能演示：

NavigationC类用于打印测试代码，从而观察配置是否正确；具体类如下所示：


package cn.javass.spring.chapter3.bean;
public class NavigationC {
    public void sayNavigation() {
        System.out.println("===navigation c");
    }
}
 

NavigationB类，包含对象和列表、Properties、数组字典数据类型导航，而且这些复合数据类型保存的条目都是对象，正好练习一下如何往复合数据类型中注入对象依赖。具体类如下所示：

package cn.javass.spring.chapter3.bean;
import java.util.List;
import java.util.Map;
import java.util.Properties;
public class NavigationB {
    private NavigationC navigationC;
    private List<NavigationC> list;
    private Properties properties;
    private NavigationC[] array = new NavigationC[1];
    private Map<String, NavigationC> map;
   //由于setter和getter方法占用太多空间，故省略，大家自己实现吧
}
 

NavigationA类是我们的前端类，通过对它的导航进行注入值，具体代码如下：

package cn.javass.spring.chapter3.bean;
public class NavigationA {
    private NavigationB navigationB;
    public void setNavigationB(NavigationB navigationB) {
        this.navigationB = navigationB;
    }
    public NavigationB getNavigationB() {
        return navigationB;
    }
}
 

接下来该进行Bean定义配置（resources/chapter3/navigationBeanInject.xml）了，首先让我们配置一下需要被导航的数据，NavigationC和NavigationB类，其中配置NavigationB时注意要确保比如array字段不为空值，这就需要或者在代码中赋值如“NavigationC[] array = new NavigationC[1];”，或者通过配置文件注入如“<list></list>”注入一个不包含条目的列表。具体配置如下：

<bean id="c" class="cn.javass.spring.chapter3.bean.NavigationC"/>
<bean id="b" class="cn.javass.spring.chapter3.bean.NavigationB">
<property name="list"><list></list></property>
    <property name="map"><map></map></property>
    <property name="properties"><props></props></property>
</bean>
 

配置完需要被导航的Bean定义了，该来配置NavigationA导航Bean了，在此需要注意，由于“navigationB”属性为空值，在此需要首先注入“navigationB”值；还有对于数组导航不能越界否则报错；具体配置如下：

<bean id="a" class="cn.javass.spring.chapter3.bean.NavigationA">
<!-- 首先注入navigatiionB 确保它非空 -->
<property name="navigationB" ref="b"/>
<!-- 对象图导航注入 -->
<property name="navigationB.navigationC" ref="c"/>
<!-- 注入列表数据类型数据 -->
<property name="navigationB.list[0]" ref="c"/>
<!-- 注入map类型数据 -->
<property name="navigationB.map[key]" ref="c"/>
<!-- 注入properties类型数据 -->
<property name="navigationB.properties[0]" ref="c"/>
<!-- 注入properties类型数据 -->
<property name="navigationB.properties[1]" ref="c"/>
<!-- 注入数组类型数据 ，注意不要越界-->
<property name="navigationB.array[0]" ref="c"/>
</bean>
 

配置完毕，具体测试代码在cn.javass.spring.chapter3. DependencyInjectTest，让我们看下测试代码吧：

//对象图导航
@Test
public void testNavigationBeanInject() {
ApplicationContext context =
new ClassPathXmlApplicationContext("chapter3/navigationBeanInject.xml");       
NavigationA navigationA = context.getBean("a", NavigationA.class);
navigationA.getNavigationB().getNavigationC().sayNavigation();
navigationA.getNavigationB().getList().get(0).sayNavigation();
navigationA.getNavigationB().getMap().get("key").sayNavigation();
navigationA.getNavigationB().getArray()[0].sayNavigation();
((NavigationC)navigationA.getNavigationB().getProperties().get("1"))
.sayNavigation();       
}
 

测试完毕，应该输出5个“===navigation c”，是不是很简单，注意这种方式是不推荐使用的，了解一下就够了，最好使用3.1.5一节使用的配置方式。

 

3.1.11配置简写

让我们来总结一下依赖注入配置及简写形式，其实我们已经在以上部分穿插着进行简化配置了：

 

一、构造器注入：

1）常量值

简写：<constructor-arg index=”0″ value=”常量”/>

全写：<constructor-arg index=”0″><value>常量</value></constructor-arg>

2）引用

简写：<constructor-arg index=”0″ ref=”引用”/>

全写：<constructor-arg index=”0″><ref bean=”引用”/></constructor-arg>

 

二、setter注入：

1）常量值

简写：<property name=”message” value=”常量”/>

全写：<property name=”message”><value>常量</value></ property>

2）引用

简写：<property name=”message” ref=”引用”/>

全写：<property name=”message”><ref bean=”引用”/></ property>

3）数组：<array>没有简写形式

4）列表：<list>没有简写形式

5）集合：<set>没有简写形式

6）字典

简写：<map>

<entry key=”键常量” value=”值常量”/>

<entry key-ref=”键引用” value-ref=”值引用”/>

</map>

全写：<map>

<entry><key><value>键常量</value></key><value>值常量</value></entry>

<entry><key><ref bean=”键引用”/></key><ref bean=”值引用”/></entry>

</map>

7）Properties：没有简写形式

 

三、使用p命名空间简化setter注入：

使用p命名空间来简化setter注入，具体使用如下：
<?xml version="1.0" encoding="UTF-8"?>
<beans  xmlns="http://www.springframework.org/schema/beans"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xmlns:p="http://www.springframework.org/schema/p"
        xsi:schemaLocation="
http://www.springframework.org/schema/beans
http://www.springframework.org/schema/beans/spring-beans-3.0.xsd">
 
<bean id="bean1" class="java.lang.String">
        <constructor-arg index="0" value="test"/>
    </bean>
<bean id="idrefBean1" class="cn.javass.spring.chapter3.bean.IdRefTestBean"
p:id="value"/>
<bean id="idrefBean2" class="cn.javass.spring.chapter3.bean.IdRefTestBean"
p:id-ref="bean1"/>
</beans>
 
xmlns:p=”http://www.springframework.org/schema/p” ：首先指定p命名空间
</pre>



---

Factory bean !!!!

---

### 获取 spring 的 bean 方法总结

+ 通过ApplicationContext获取Sping配置文件中的Bean
1. 
```java
ApplicationContext applicationContext = new FileSystemXmlApplicationContext("applicationContext.xml");
```
或者
```java
private ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
```
2. 通过Spring提供的工具类获取ApplicationContext对象，专为web工程定制的方法，推荐Web项目中使用
