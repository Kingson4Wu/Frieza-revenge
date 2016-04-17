### 注解实现Bean依赖注入
 
---

@Required：依赖检查
@Requried  
setter方法

---
@Autowired：自动装配

@Autowired(required=true)

---
@Value：注入SpEL表达式

---
@Qualifier：限定描述符，用于细粒度选择候选者；
@Autowired默认是根据类型进行注入的，因此如果有多个类型一样的Bean候选者，则需要限定其中一个候选者，否则将抛出异常

---
@Resource：自动装配，默认根据类型装配，如果指定name属性将根据名字装配，可以使用如下方式来指定

---
@PostConstruct和PreDestroy：通过注解指定初始化和销毁方法定义

---
JSR-330注解
在测试之前需要准备JSR-330注解所需要的jar包，到spring-framework-3.0.5.RELEASE-dependencies.zip中拷贝如下jar包到类路径：
 
com.springsource.javax.inject-1.0.0.jar
 
       一、@Inject：等价于默认的@Autowired，只是没有required属性；
       二、@Named：指定Bean名字，对应于Spring自带@Qualifier中的缺省的根据Bean名字注入情况；
       三、@Qualifier：只对应于Spring自带@Qualifier中的扩展@Qualifier限定描述符注解，即只能扩展使用，没有value属性。
       
---
JPA注解
@PersistenceContext
@PersistenceUnit

---


### 注解实现Bean定义

 使用<context:component-scan>标签来表示需要要自动注册Bean定义，而通过base-package属性指定扫描的类路径位置。
       <context:component-scan>标签将自动开启“注解实现Bean依赖注入”支持。
       此处我们还通过<aop:aspectj-autoproxy/>用于开启Spring对@AspectJ风格切面的支持。
 
Spring基于注解实现Bean定义支持如下三种注解：
Spring自带的@Component注解及扩展@Repository、@Service、@Controller，如图12-1所示；
JSR-250 1.1版本中中定义的@ManagedBean注解，是Java EE 6标准规范之一，不包括在JDK中，需要在应用服务器环境使用（如Jboss），如图12-2所示；
JSR-330的@Named注解


---
细粒度控制Bean定义扫描
<http://jinnianshilongnian.iteye.com/blog/1461055>

base-package：表示扫描注解类的开始位置，即将在指定的包中扫描，其他包中的注解类将不被扫描，默认将扫描所有类路径；
resource-pattern：表示扫描注解类的后缀匹配模式，即“base-package+resource-pattern”将组成匹配模式用于匹配类路径中的组件，默认后缀为“**/*.class”，即指定包下的所有以.class结尾的类文件；
name-generator：默认情况下的Bean标识符生成策略，默认是AnnotationBeanNameGenerator，其将生成以小写开头的类名（不包括包名）；可以自定义自己的标识符生成策略；
use-default-filters：默认为true表示过滤@Component、@ManagedBean、@Named注解的类，如果改为false默认将不过滤这些默认的注解来定义Bean，即这些注解类不能被过滤到，即不能通过这些注解进行Bean定义；
annotation-config：表示是否自动支持注解实现Bean依赖注入，默认支持，如果设置为false，将关闭支持注解的依赖注入，需要通过<context:annotation-config/>开启。
默认情况下将自动过滤@Component、@ManagedBean、@Named注解的类并将其注册为Spring管理Bean，可以通过在<context:component-scan>标签中指定自定义过滤器将过滤到匹配条件的类注册为Spring管理Bean

---
提供更多的配置元数据

@Lazy：定义Bean将延迟初始化
@DependsOn：定义Bean初始化及销毁时的顺序
@Scope：定义Bean作用域，默认单例
@Qualifier：指定限定描述符，对应于基于XML配置中的<qualifier>标签
@Primary：自动装配时当出现多个Bean候选者时，被注解为@Primary的Bean将作为首选者，否则将抛出异常

---

### 基于Java类定义Bean配置元数据

基于Java类定义Bean配置元数据，其实就是通过Java类定义Spring配置元数据，且直接消除XML配置文件。
 
基于Java类定义Bean配置元数据中的@Configuration注解的类等价于XML配置文件，@Bean注解的方法等价于XML配置文件中的Bean定义。
 
基于Java类定义Bean配置元数据需要通过AnnotationConfigApplicationContext加载配置类及初始化容器，类似于XML配置文件需要使用ClassPathXmlApplicationContext加载配置文件及初始化容器。
 
基于Java类定义Bean配置元数据需要CGLIB的支持，因此要保证类路径中包括CGLIB的jar包。

---

使用@Configuration注解配置类，该配置类定义了Bean配置元数据；
使用@Bean注解配置类中的方法，该方法名就是Bean的名字，该方法返回值就是Bean对象。
使用new AnnotationConfigApplicationContext(ApplicationContextConfig.class)创建应用上下文，构造器参数为使用@Configuration注解的配置类，读取配置类进行实例化相应的Bean。



---

@Import
  类似于基于XML配置中的<import/>，基于Java的配置方式提供了@Import来组合模块化的配置类
  
@Configuration("ctxConfig2")  
@Import({ApplicationContextConfig.class})  
public class ApplicationContextConfig2 {  
    @Bean(name = {"message2"})  
    public String message() {  
        return "hello";  
    }  
}  

---
结合基于Java和基于XML方式的配置
基于Java方式的配置方式不是为了完全替代基于XML方式的配置，两者可以结合使用，因此可以有两种结合使用方式：
在基于Java方式的配置类中引入基于XML方式的配置文件；
在基于XML方式的配置文件中中引入基于Java方式的配置

@ImportResource注解引入基于XML方式的配置文件
@Configuration("ctxConfig") //1、使用@Configuration注解配置类  
@ImportResource("classpath:chapter12/configuration/importResource.xml")  
public class ApplicationContextConfig {  
……  
}  


---
基于Java方式的容器实例化
基于Java方式的容器由AnnotationConfigApplicationContext表示，其实例化方式主要有以下几种：

一、对于只有一个@Configuration注解的配置类，可以使用如下方式初始化容器：
 
AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationContextConfig.class);  
 
二、对于有多个@Configuration注解的配置类，可以使用如下方式初始化容器：
 
 
AnnotationConfigApplicationContext ctx1 = new AnnotationConfigApplicationContext(ApplicationContextConfig.class, ApplicationContextConfig2.class);  
 
或者
 
AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext();  
ctx2.register(ApplicationContextConfig.class);  
ctx2.register(ApplicationContextConfig2.class);  
 
 
三、对于【12.3  注解实现Bean定义】中通过扫描类路径中的特殊注解类来自动注册Bean定义，可以使用如下方式来实现：
 
public void testComponentScan() {  
    AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();  
    ctx.scan("cn.javass.chapter12.confiuration");  
    ctx.refresh();  
    Assert.assertEquals("hello", ctx.getBean("message"));  
}  
 
以上配置方式等价于基于XML方式中的如下配置：
 
<context:component-scan base-package="cn.javass.chapter12.confiuration"/>  

---

在web环境中使用基于Java方式的配置，通过修改通用配置实现
1、修改通用配置中的Web应用上下文实现，在此需要使用AnnotationConfigWebApplicationContext：
 
<context-param>  
    <param-name>contextClass</param-name>       
    <param-value>  
        org.springframework.web.context.support.AnnotationConfigWebApplicationContext  
    </param-value>  
</context-param>  
 
 
2、指定加载配置类，类似于指定加载文件位置，在基于Java方式中需要指定需要加载的配置类：
 
<context-param>  
    <param-name>contextConfigLocation</param-name>  
    <param-value>  
        cn.javass.spring.chapter12.configuration.ApplicationContextConfig,  
        cn.javass.spring.chapter12.configuration.ApplicationContextConfig2  
    </param-value>  
</context-param>  
contextConfigLocation：除了可以指定配置类，还可以指定“扫描的类路径”，其加载步骤如下：
      1、首先验证指定的配置是否是类，如果是则通过注册配置类来完成Bean定义加载，即如通过ctx.register(ApplicationContextConfig.class)加载定义；
      2、如果指定的配置不是类，则通过扫描类路径方式加载注解Bean定义，即将通过ctx.scan("cn.javass.chapter12.confiuration")加载Bean定义。

