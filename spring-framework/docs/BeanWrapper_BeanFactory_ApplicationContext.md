Sping之BeanWrapper、BeanFactory ApplicationContext

<http://www.blogjava.net/shenang/archive/2009/03/23/261476.html>

org.springframework.beans包中包括了核心组件的实现类， 核心中的核心为BeanWrapper和BeanFactory类。

我对这三个类的理解是：

1、 通过Spring BeanWrapper去操作一个JavaBean;

2、 Bean Factory负责根据配置文件创建Bean实例;

3、 BeanFactory提供了针对Java Bean的管理功能，而ApplicationContext提供了一更为框架化的实现（从上面的示例中可以看出，
BeanFactory的使用方式更加类似一个API，而非Framework style） 。 ApplicationContext覆盖了BeanFactory的所有功能，
并提供了更多的特性。此外，ApplicationContext为与现有应用框架相整合，提供了更为开放式的实现（如对于Web应用，
我们可以在web.xml中对ApplicationContext进行配置。

---

二、 Bean Factory

 Bean Factory，顾名思义，负责创建并维护Bean实例。

 Bean Factory负责根据配置文件创建Bean实例，可以配置的项目有：

1． Bean属性值及依赖关系（对其他Bean的引用）

2． Bean创建模式（是否Singleton模式，即是否只针对指定类维持全局唯一的实例）

3． Bean初始化和销毁方法

4． Bean的依赖关系

---
关于ApplicationContext

ApplicationContext

 BeanFactory提供了针对Java Bean的管理功能，而ApplicationContext提供了一个更为框架化的

实现（从上面的示例中可以看出，BeanFactory的使用方式更加类似一个API，而非Framework style）。 

ApplicationContext覆盖了BeanFactory的所有功能，并提供了更多的特性。此外，

ApplicationContext为与现有应用框架相整合，提供了更为开放式的实现（如对于Web应用，我们可以在

web.xml中对ApplicationContext进行配置）。

相对BeanFactory而言，ApplicationContext提供了以下扩展功能：

 1．国际化支持

我们可以在Beans.xml文件中，对程序中的语言信息（如提示信息）进行定义，将程序中的提示

信息抽取到配置文件中加以定义，为我们进行应用的各语言版本转换提供了极大的灵活性。

2．资源访问

支持对文件和URL的访问。

3．事件传播

事件传播特性为系统中状态改变时的检测提供了良好支持。

4．多实例加载

可以在同一个应用中加载多个Context实例。