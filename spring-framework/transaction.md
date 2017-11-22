使用spring中的@Transactional注解时，可能需要注意的地方：<http://mojito515.github.io/blog/2016/08/31/transactionalinspring/>

编程式事务管理&声明式事务管理:

（一）编程式事务管理 在 Spring 出现以前，编程式事务管理对基于 POJO 的应用来说是唯一选择。
用过 Hibernate 的人都知道，我们需要在代码中显式调用beginTransaction()、commit()、rollback()等事务管理相关的方法，
这就是编程式事务管理。通过 Spring 提供的事务管理 API，我们可以在代码中灵活控制事务的执行。
在底层，Spring 仍然将事务操作委托给底层的持久化框架来执行。

（二）声明式事务管理

1）Spring 的声明式事务管理在底层是建立在 AOP 的基础之上的。其本质是对方法前后进行拦截，然后在目标方法开始之前创建或者加入一个事务，
在执行完目标方法之后根据执行情况提交或者回滚事务。

2）声明式事务最大的优点就是不需要通过编程的方式管理事务，这样就不需要在业务逻辑代码中掺杂事务管理的代码，
只需在配置文件中做相关的事务规则声明（或通过等价的基于标注的方式），便可以将事务规则应用到业务逻辑中。
因为事务管理本身就是一个典型的横切逻辑，正是 AOP 的用武之地。Spring 开发团队也意识到了这一点，为声明式事务提供了简单而强大的支持。

3）声明式事务管理曾经是 EJB 引以为傲的一个亮点， Spring 让 POJO 在事务管理方面也拥有了和 EJB 一样的待遇，
让开发人员在 EJB 容器之外也用上了强大的声明式事务管理功能，这主要得益于 Spring 依赖注入容器和 Spring AOP 的支持。
依赖注入容器为声明式事务管理提供了基础设施，使得 Bean 对于 Spring 框架而言是可管理的；
而 Spring AOP 则是声明式事务管理的直接实现者。

4）建议在开发中使用声明式事务，不仅因为其简单，更主要是因为这样使得纯业务代码不被污染，极大方便后期的代码维护。
和编程式事务相比，声明式事务唯一不足地方是，后者的最细粒度只能作用到方法级别，无法做到像编程式事务那样可以作用到代码块级别。
但是即便有这样的需求，也存在很多变通的方法，比如，可以将需要进行事务管理的代码块独立为方法等等。


---
总结：
Transactional 注解可以被应用于接口定义和接口方法、类定义和类的 public 方法上。
“proxy-target-class” 属性值来控制是基于接口的还是基于类的代理被创建。 
<tx:annotation-driven transaction-manager=“transactionManager” proxy-target-class=“true”/> 
注意：proxy-target-class属性值决定是基于接口的还是基于类的代理被创建。如果proxy-target-class 属性值被设置为true，
那么基于类的代理将起作用（这时需要cglib库）。如果proxy-target-class属值被设置为false或者这个属性被省略，
那么标准的JDK 基于接口的代理。
注解@Transactional cglib与java动态代理最大区别是代理目标对象不用实现接口,那么注解要是写到接口方法上，
要是使用cglib代理，这是注解事物就失效了，为了保持兼容注解最好都写到实现类方法上。
Spring团队建议在具体的类（或类的方法）上使用 @Transactional 注解，而不要使用在类所要实现的任何接口上。
在接口上使用 @Transactional 注解，只能当你设置了基于接口的代理时它才生效。因为注解是 不能继承的，
这就意味着如果正在使用基于类的代理时，那么事务的设置将不能被基于类的代理所识别，而且对象也将不会被事务代理所包装。
@Transactional 的事务开启 ，或者是基于接口的 或者是基于类的代理被创建。<b>
所以在同一个类中一个方法调用另一个方法有事务的方法，事务是不会起作用的。</b> 
原因：（这也是为什么在项目中有些@Async并没有异步执行） spring 在扫描bean的时候会扫描方法上是否包含@Transactional注解，
如果包含，spring会为这个bean动态地生成一个子类（即代理类，proxy），代理类是继承原来那个bean的。
此时，当这个有注解的方法被调用的时候，实际上是由代理类来调用的，代理类在调用之前就会启动transaction。
然而，如果这个有注解的方法是被同一个类中的其他方法调用的，那么该方法的调用并没有通过代理类，而是直接通过原来的那个bean，
所以就不会启动transaction，我们看到的现象就是@Transactional注解无效。


---
“Transaction rolled back because it has been marked as rollback-only”
：<http://narcissusoyf.iteye.com/blog/710261>
使用PROPAGATION_REQUIRES_NEW

<pre>
try-catch不起作用的原因简单的说就是，try-catch的不是地方，你认为你的try-catch是最接近异常抛出点了，是第一个处理的handler了。
实际上，spring在更早一步就try-catch 住了，同时还设置了一些标志位，再把catch住的异常往外抛。
这个时候才是我们的try-catch。而"Transaction rolled back because it has been marked as rollback-only"
就是因为事务在提交的时候，发现标志位已经被设置了，不应该去提交了，然后吭哧吭哧的回滚调，再提示你已经被设置成rollback-only了。

原因是既然如此，那么在不改变代码的情况下，依靠配置能否解决这个问题呢？
使用PROPAGATION_REQUIRES_NEW吧。对于bBo.insertB(b)开个新的事务，如果失败了就回滚调，不影响外面的insertA不就OK了。
</pre>

<pre>
spring在TransactionDefinition接口中规定了7种类型的事务传播行为，

它们规定了事务方法和事务方法发生嵌套调用时事务如何进行传播：

事务传播行为类型

事务传播行为类型

说明

PROPAGATION_REQUIRED

如果当前没有事务，就新建一个事务，如果已经存在一个事务中，加入到这个事务中。这是最常见的选择。

PROPAGATION_SUPPORTS

支持当前事务，如果当前没有事务，就以非事务方式执行。

PROPAGATION_MANDATORY

使用当前的事务，如果当前没有事务，就抛出异常。

PROPAGATION_REQUIRES_NEW

新建事务，如果当前存在事务，把当前事务挂起。

PROPAGATION_NOT_SUPPORTED

以非事务方式执行操作，如果当前存在事务，就把当前事务挂起。

PROPAGATION_NEVER

以非事务方式执行，如果当前存在事务，则抛出异常。

PROPAGATION_NESTED

如果当前存在事务，则在嵌套事务内执行。如果当前没有事务，则执行与PROPAGATION_REQUIRED类 似的操作。
</pre>

<http://blog.csdn.net/liuwei063608/article/details/7784800>

