事务首先是一系列操作组成的工作单元，该工作单元内的操作是不可分割的，即要么所有操作都做，要么所有操作都不做，这就是事务。
 
事务必需满足ACID（原子性、一致性、隔离性和持久性）特性，缺一不可：
原子性（Atomicity）：即事务是不可分割的最小工作单元，事务内的操作要么全做，要么全不做；
一致性（Consistency）：在事务执行前数据库的数据处于正确的状态，而事务执行完成后数据库的数据还是处于正确的状态，即数据完整性约束没有被破坏；如银行转帐，A转帐给B，必须保证A的钱一定转给B，一定不会出现A的钱转了但B没收到，否则数据库的数据就处于不一致（不正确）的状态。
隔离性（Isolation）：并发事务执行之间无影响，在一个事务内部的操作对其他事务是不产生影响，这需要事务隔离级别来指定隔离性；
持久性（Durability）：事务一旦执行成功，它对数据库的数据的改变必须是永久的，不会因比如遇到系统故障或断电造成数据不一致或丢失。
在实际项目开发中数据库操作一般都是并发执行的，即有多个事务并发执行，并发执行就可能遇到问题，目前常见的问题如下：
丢失更新：两个事务同时更新一行数据，最后一个事务的更新会覆盖掉第一个事务的更新，从而导致第一个事务更新的数据丢失，这是由于没有加锁造成的；
脏读：一个事务看到了另一个事务未提交的更新数据；
不可重复读：在同一事务中，多次读取同一数据却返回不同的结果；也就是有其他事务更改了这些数据；
幻读：一个事务在执行过程中读取到了另一个事务已提交的插入数据；即在第一个事务开始时读取到一批数据，但此后另一个事务又插入了新数据并提交，此时第一个事务又读取这批数据但发现多了一条，即好像发生幻觉一样。
为了解决这些并发问题，需要通过数据库隔离级别来解决，在标准SQL规范中定义了四种隔离级别：
未提交读（Read Uncommitted）：最低隔离级别，一个事务能读取到别的事务未提交的更新数据，很不安全，可能出现丢失更新、脏读、不可重复读、幻读；
提交读（Read Committed）：一个事务能读取到别的事务提交的更新数据，不能看到未提交的更新数据，不可能可能出现丢失更新、脏读，但可能出现不可重复读、幻读；
可重复读（Repeatable Read）：保证同一事务中先后执行的多次查询将返回同一结果，不受其他事务影响，可能可能出现丢失更新、脏读、不可重复读，但可能出现幻读；
序列化（Serializable）：最高隔离级别，不允许事务并发执行，而必须串行化执行，最安全，不可能出现更新、脏读、不可重复读、幻读。
隔离级别越高，数据库事务并发执行性能越差，能处理的操作越少。因此在实际项目开发中为了考虑并发性能一般使用提交读隔离级别，它能避免丢失更新和脏读，尽管不可重复读和幻读不能避免，但可以在可能出现的场合使用悲观锁或乐观锁来解决这些问题。


---

事务类型
数据库事务类型有本地事务和分布式事务：
本地事务：就是普通事务，能保证单台数据库上的操作的ACID，被限定在一台数据库上；
分布式事务：涉及两个或多个数据库源的事务，即跨越多台同类或异类数据库的事务（由每台数据库的本地事务组成的），分布式事务旨在保证这些本地事务的所有操作的ACID，使事务可以跨越多台数据库；
Java事务类型有JDBC事务和JTA事务：
JDBC事务：就是数据库事务类型中的本地事务，通过Connection对象的控制来管理事务；
JTA事务：JTA指Java事务API(Java Transaction API)，是Java EE数据库事务规范， JTA只提供了事务管理接口，由应用程序服务器厂商（如WebSphere Application Server）提供实现，JTA事务比JDBC更强大，支持分布式事务。
Java EE事务类型有本地事务和全局事务：
本地事务：使用JDBC编程实现事务；
全局事务：由应用程序服务器提供，使用JTA事务；
按是否通过编程实现事务有声明式事务和编程式事务；
声明式事务： 通过注解或XML配置文件指定事务信息；
编程式事务：通过编写代码实现事务。

---

Spring提供的事务管理
Spring框架最核心功能之一就是事务管理，而且提供一致的事务管理抽象，这能帮助我们：
提供一致的编程式事务管理API，不管使用Spring JDBC框架还是集成第三方框架使用该API进行事务编程；
无侵入式的声明式事务支持。
Spring支持声明式事务和编程式事务事务类型。

---

事务管理
<http://jinnianshilongnian.iteye.com/blog/1439900>

Spring框架支持事务管理的核心是事务管理器抽象，对于不同的数据访问框架（如Hibernate）通过实现策略接口PlatformTransactionManager，从而能支持各种数据访问框架的事务管理，PlatformTransactionManager接口定义如下：
 
public interface PlatformTransactionManager {  
       TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException;  
       void commit(TransactionStatus status) throws TransactionException;  
       void rollback(TransactionStatus status) throws TransactionException;  
}  


内置事务管理器实现
Spring提供了许多内置事务管理器实现：
DataSourceTransactionManager：位于org.springframework.jdbc.datasource包中，数据源事务管理器，提供对单个javax.sql.DataSource事务管理，用于Spring JDBC抽象框架、iBATIS或MyBatis框架的事务管理；
JdoTransactionManager：位于org.springframework.orm.jdo包中，提供对单个javax.jdo.PersistenceManagerFactory事务管理，用于集成JDO框架时的事务管理；
JpaTransactionManager：位于org.springframework.orm.jpa包中，提供对单个javax.persistence.EntityManagerFactory事务支持，用于集成JPA实现框架时的事务管理；
HibernateTransactionManager：位于org.springframework.orm.hibernate3包中，提供对单个org.hibernate.SessionFactory事务支持，用于集成Hibernate框架时的事务管理；该事务管理器只支持Hibernate3+版本，且Spring3.0+版本只支持Hibernate 3.2+版本；
JtaTransactionManager：位于org.springframework.transaction.jta包中，提供对分布式事务管理的支持，并将事务管理委托给Java EE应用服务器事务管理器；
OC4JjtaTransactionManager：位于org.springframework.transaction.jta包中，Spring提供的对OC4J10.1.3+应用服务器事务管理器的适配器，此适配器用于对应用服务器提供的高级事务的支持；
WebSphereUowTransactionManager：位于org.springframework.transaction.jta包中，Spring提供的对WebSphere 6.0+应用服务器事务管理器的适配器，此适配器用于对应用服务器提供的高级事务的支持；
WebLogicJtaTransactionManager：位于org.springframework.transaction.jta包中，Spring提供的对WebLogic 8.1+应用服务器事务管理器的适配器，此适配器用于对应用服务器提供的高级事务的支持。

---

编程式事务
<http://jinnianshilongnian.iteye.com/blog/1441271>

所谓编程式事务指的是通过编码方式实现事务，即类似于JDBC编程实现事务管理。
Spring框架提供一致的事务抽象，因此对于JDBC还是JTA事务都是采用相同的API进行编程。

---


声明式事务
<http://jinnianshilongnian.iteye.com/blog/1442376>
编程式实现事务管理可以深刻体会到编程式事务的痛苦，即使通过代理配置方式也是不小的工作量。

XML命名空间定义，定义用于事务支持的tx命名空间和AOP支持的aop命名空间
业务实现配置，非常简单，使用以前定义的非侵入式业务实现

