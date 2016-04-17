<http://www.importnew.com/17599.html>
 自动装配

自动装配就是指由Spring来自动地注入依赖对象，无需人工参与。

目前Spring3.0支持“no”、“byName ”、“byType”、“constructor”四种自动装配，默认是“no”指不支持自动装配的，其中Spring3.0已不推荐使用之前版本的“autodetect”自动装配，
推荐使用Java 5+支持的（@Autowired）注解方式代替；如果想支持“autodetect”自动装配，请将schema改为“spring-beans-2.5.xsd”或去掉。

自动装配的好处是减少构造器注入和setter注入配置，减少配置文件的长度。自动装配通过配置<bean>标签的“autowire”属性来改变自动装配方式。接下来让我们挨着看下配置的含义。
 

一、default：表示使用默认的自动装配，默认的自动装配需要在<beans>标签中使用default-autowire属性指定，其支持“no”、“byName ”、“byType”、“constructor”四种自动装配，
如果需要覆盖默认自动装配，请继续往下看；

二、no：意思是不支持自动装配，必须明确指定依赖。

三、byName：通过设置Bean定义属性autowire=”byName”，意思是根据名字进行自动装配，只能用于setter注入。比如我们有方法“setHelloApi”，
则“byName”方式Spring容器将查找名字为helloApi的Bean并注入，如果找不到指定的Bean，将什么也不注入。

例如如下Bean定义配置：

<bean id="helloApi" class="cn.javass.spring.chapter2.helloworld.HelloImpl"/>
<bean id="bean" class="cn.javass.spring.chapter3.bean.HelloApiDecorator"
     autowire="byName"/>
     
四、“byType”：通过设置Bean定义属性autowire=”byType”，意思是指根据类型注入，用于setter注入，比如如果指定自动装配方式为“byType”，
而“setHelloApi”方法需要注入HelloApi类型数据，则Spring容器将查找HelloApi类型数据，如果找到一个则注入该Bean，如果找不到将什么也不注入，
如果找到多个Bean将优先注入<bean>标签“primary”属性为true的Bean，否则抛出异常来表明有个多个Bean发现但不知道使用哪个。

根据类型找到多个Bean时，对于集合类型（如List、Set）将注入所有匹配的候选者，而对于其他类型遇到这种情况可能需要
使用“autowire-candidate”属性为false来让指定的Bean放弃作为自动装配的候选者，或使用“primary”属性为true来指定某个Bean为首选Bean


五、“constructor”：通过设置Bean定义属性autowire=”constructor”，功能和“byType”功能一样，根据类型注入构造器参数，只是用于构造器注入方式

可以采用在“<beans>”标签中通过“default-autowire”属性指定全局的自动装配方式，即如果default-autowire=”byName”，将对所有Bean进行根据名字进行自动装配。

---
数组、集合、字典类型的根据类型自动装配和普通类型的自动装配是有区别的：

数组类型、集合（Set、Collection、List）接口类型：将根据泛型获取匹配的所有候选者并注入到数组或集合中，如“List<HelloApi> list”将选择所有的HelloApi类型Bean并注入到list中，
而对于集合的具体类型将只选择一个候选者，“如 ArrayList<HelloApi> list”将选择一个类型为ArrayList的Bean注入，而不是选择所有的HelloApi类型Bean进行注入；
字典（Map）接口类型：同样根据泛型信息注入，键必须为String类型的Bean名字，值根据泛型信息获取，如“Map<String, HelloApi> map” 将选择所有的HelloApi类型Bean并注入到map中，
而对于具体字典类型如“HashMap<String, HelloApi> map”将只选择类型为HashMap的Bean注入，而不是选择所有的HelloApi类型Bean进行注入。

自动装配也是有缺点的，最重要的缺点就是没有了配置，在查找注入错误时非常麻烦，还有比如基本类型没法完成自动装配，所以可能经常发生一些莫名其妙的错误，
在此我推荐大家不要使用该方式，最好是指定明确的注入方式，或者采用最新的Java5+注解注入方式。
所以大家在使用自动装配时应该考虑自己负责项目的复杂度来进行衡量是否选择自动装配方式。

自动装配注入方式能和配置注入方式一同工作吗？当然可以，大家只需记住配置注入的数据会覆盖自动装配注入的数据。

---
依赖检查

上一节介绍的自动装配，很可能发生没有匹配的Bean进行自动装配，如果此种情况发生，只有在程序运行过程中发生了空指针异常才能发现错误，如果能提前发现该多好啊，这就是依赖检查的作用。
依赖检查：用于检查Bean定义的属性都注入数据了，不管是自动装配的还是配置方式注入的都能检查，如果没有注入数据将报错，从而提前发现注入错误，只检查具有setter方法的属性。
Spring3+也不推荐配置方式依赖检查了，建议采用Java5+ @Required注解方式

---

方法注入

所谓方法注入其实就是通过配置方式覆盖或拦截指定的方法，通常通过代理模式实现。Spring提供两种方法注入：查找方法注入和方法替换注入。

因为Spring是通过CGLIB动态代理方式实现方法注入，也就是通过动态修改类的字节码来实现的，本质就是生成需方法注入的类的子类方式实现。

传统方式和Spring容器管理方式唯一不同的是不需要我们手动生成子类，而是通过配置方式来实现

一、查找方法注入：又称为Lookup方法注入，用于注入方法返回结果，也就是说能通过配置方式替换方法返回结果。使用<lookup-method name=”方法名” bean=”bean名字”/>配置；其中name属性指定方法名，bean属性指定方法需返回的Bean。

方法定义格式：访问级别必须是public或protected，保证能被子类重载，可以是抽象方法，必须有返回值，必须是无参数方法，查找方法的类和被重载的方法必须为非final：

`<public|protected> [abstract] <return-type> theMethodName(no-arguments);`

因为“singleton”Bean在容器中只有一个实例，而“prototype”Bean是每次获取容器都返回一个全新的实例，所以如果“singleton”Bean在使用“prototype” Bean情况时，那么“prototype”Bean由于是“singleton”Bean的一个字段属性，所以获取的这个“prototype”Bean就和它所在的“singleton”Bean具有同样的生命周期，所以不是我们所期待的结果。因此查找方法注入就是用于解决这个问题。

二、替换方法注入：也叫“MethodReplacer”注入，和查找注入方法不一样的是，他主要用来替换方法体。通过首先定义一个MethodReplacer接口实现，然后如下配置来实现：

<replaced-method name="方法名" replacer="MethodReplacer实现">
<arg-type>参数类型</arg-type>
</replaced-method>”

1）首先定义MethodReplacer实现，完全替换掉被替换方法的方法体及返回值，其中reimplement方法重定义方法 功能，参数obj为被替换方法的对象，method为被替换方法，args为方法参数；最需要注意的是不能再 通过“method.invoke(obj, new String[]{“hehe”});” 反射形式再去调用原来方法，这样会产生循环调用；如果返回值类型为Void，请在实现中返回null：

package cn.javass.spring.chapter3.bean;
import java.lang.reflect.Method;
import org.springframework.beans.factory.support.MethodReplacer;
public class PrinterReplacer implements MethodReplacer {
    @Override
    public Object reimplement(Object obj, Method method, Object[] args)   throws Throwable {
        System.out.println("Print Replacer");
        //注意此处不能再通过反射调用了,否则会产生循环调用，知道内存溢出
        //method.invoke(obj, new String[]{"hehe"});
        return null;
    }
}
 

2）配置如下，首先定义MethodReplacer实现，使用< replaced-method >标签来指定要进行替换方法，属性name指定替换的方法名字，replacer指定该方法的重新实现者，子标签< arg-type >用来指定原来方法参数的类型，必须指定否则找不到原方法：

<bean id="replacer" class="cn.javass.spring.chapter3.bean.PrinterReplacer"/>
<bean id="printer" class="cn.javass.spring.chapter3.bean.Printer">
<replaced-method name="print" replacer="replacer">
        <arg-type>java.lang.String</arg-type>
    </replaced-method>
</bean>
 

3）测试代码将输出“Print Replacer ”，说明方法体确实被替换了：

@Test 
public void testMethodReplacer() {  
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("chapter3/methodReplacerInject.xml");  
    Printer printer = context.getBean("printer", Printer.class);  
    printer.print("我将被替换");  
    
    


