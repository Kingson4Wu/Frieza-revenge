<http://jinnianshilongnian.iteye.com/blog/1433316>

Spring对ORM的支持
       Spring对ORM的支持主要表现在以下方面：
一致的异常体系结构，对第三方ORM框架抛出的专有异常进行包装，从而在使我们在Spring中只看到DataAccessException异常体系；
一致的DAO抽象支持：提供类似与JdbcSupport的DAO支持类HibernateDaoSupport，使用HibernateTemplate模板类来简化常用操作，HibernateTemplate提供回调接口来支持复杂操作；
Spring事务管理：Spring对所有数据访问提供一致的事务管理，通过配置方式，简化事务管理。
Spring还在测试、数据源管理方面提供支持，从而允许方便测试，简化数据源使用

hibernate
mybatis
JPA

集成JPA:<http://jinnianshilongnian.iteye.com/blog/1439369>

