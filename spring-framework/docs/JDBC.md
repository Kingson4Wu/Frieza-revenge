<http://jinnianshilongnian.iteye.com/blog/1423896>

Spring对JDBC的支持
       
Spring通过抽象JDBC访问并提供一致的API来简化JDBC编程的工作量。我们只需要声明SQL、调用合适的Spring JDBC框架API、处理结果集即可。事务由Spring管理，并将JDBC受查异常转换为Spring一致的非受查异常，从而简化开发。
Spring主要提供JDBC模板方式、关系数据库对象化方式和SimpleJdbc方式三种方式来简化JDBC编程，这三种方式就是Spring JDBC的工作模式：
 JDBC模板方式：Spring JDBC框架提供以下几种模板类来简化JDBC编程，实现GoF模板设计模式，将可变部分和非可变部分分离，可变部分采用回调接口方式由用户来实现：如JdbcTemplate、NamedParameterJdbcTemplate、SimpleJdbcTemplate。
关系数据库操作对象化方式：Spring JDBC框架提供了将关系数据库操作对象化的表示形式，从而使用户可以采用面向对象编程来完成对数据库的访问；如MappingSqlQuery、SqlUpdate、SqlCall、SqlFunction、StoredProcedure等类。这些类的实现一旦建立即可重用并且是线程安全的。
SimpleJdbc方式：Spring JDBC框架还提供了SimpleJdbc方式来简化JDBC编程，SimpleJdbcInsert 、 SimpleJdbcCall用来简化数据库表插入、存储过程或函数访问。
Spring JDBC还提供了一些强大的工具类，如DataSourceUtils来在必要的时候手工获取数据库连接等

Spring的JDBC架构
Spring JDBC抽象框架由四部分组成：datasource、support、core、object

support包：提供将JDBC异常转换为DAO非检查异常转换类、一些工具类如JdbcUtils等。
datasource包：提供简化访问JDBC 数据源（javax.sql.DataSource实现）工具类，并提供了一些DataSource简单实现类从而能使从这些DataSource获取的连接能自动得到Spring管理事务支持。
core包：提供JDBC模板类实现及可变部分的回调接口，还提供SimpleJdbcInsert等简单辅助类。
object包：提供关系数据库的对象表示形式，如MappingSqlQuery、SqlUpdate、SqlCall、SqlFunction、StoredProcedure等类，该包是基于core包JDBC模板类实现。


---

JdbcTemplate

  JdbcTemplate主要提供以下五类方法：
execute方法：可以用于执行任何SQL语句，一般用于执行DDL语句；
update方法及batchUpdate方法：update方法用于执行新增、修改、删除等语句；batchUpdate方法用于执行批处理相关语句；
query方法及queryForXXX方法：用于执行查询相关语句；
call方法：用于执行存储过程、函数相关语句。
 
JdbcTemplate类支持的回调类：
预编译语句及存储过程创建回调：用于根据JdbcTemplate提供的连接创建相应的语句；
         PreparedStatementCreator：通过回调获取JdbcTemplate提供的Connection，由用户使用该Conncetion创建相关的PreparedStatement；
         CallableStatementCreator：通过回调获取JdbcTemplate提供的Connection，由用户使用该Conncetion创建相关的CallableStatement；
预编译语句设值回调：用于给预编译语句相应参数设值；
         PreparedStatementSetter：通过回调获取JdbcTemplate提供的PreparedStatement，由用户来对相应的预编译语句相应参数设值；
         BatchPreparedStatementSetter：；类似于PreparedStatementSetter，但用于批处理，需要指定批处理大小；
自定义功能回调：提供给用户一个扩展点，用户可以在指定类型的扩展点执行任何数量需要的操作；
         ConnectionCallback：通过回调获取JdbcTemplate提供的Connection，用户可在该Connection执行任何数量的操作；
         StatementCallback：通过回调获取JdbcTemplate提供的Statement，用户可以在该Statement执行任何数量的操作；
         PreparedStatementCallback：通过回调获取JdbcTemplate提供的PreparedStatement，用户可以在该PreparedStatement执行任何数量的操作；
         CallableStatementCallback：通过回调获取JdbcTemplate提供的CallableStatement，用户可以在该CallableStatement执行任何数量的操作；
结果集处理回调：通过回调处理ResultSet或将ResultSet转换为需要的形式；
         RowMapper：用于将结果集每行数据转换为需要的类型，用户需实现方法mapRow(ResultSet rs, int rowNum)来完成将每行数据转换为相应的类型。
         RowCallbackHandler：用于处理ResultSet的每一行结果，用户需实现方法processRow(ResultSet rs)来完成处理，在该回调方法中无需执行rs.next()，该操作由JdbcTemplate来执行，用户只需按行获取数据然后处理即可。
         ResultSetExtractor：用于结果集数据提取，用户需实现方法extractData(ResultSet rs)来处理结果集，用户必须处理整个结果集；
         
   
---

 所谓关系数据库对象化其实就是用面向对象方式表示关系数据库操作，从而可以复用。
Spring JDBC框架将数据库操作封装为一个RdbmsOperation，该对象是线程安全的、可复用的对象，是所有数据库对象的父类。而SqlOperation继承了RdbmsOperation，代表了数据库SQL操作，如select、update、call等


---
SimpleJdbc方式
       Spring JDBC抽象框架提供SimpleJdbcInsert和SimpleJdbcCall类，这两个类通过利用JDBC驱动提供的数据库元数据来简化JDBC操作
       
---

集成Spring JDBC及最佳实践
       大多数情况下Spring JDBC都是与IOC容器一起使用。通过配置方式使用Spring JDBC。
       而且大部分时间都是使用JdbcTemplate类（或SimpleJdbcTemplate和NamedParameterJdbcTemplate）进行开发，即可能80%时间使用JdbcTemplate类，而只有20%时间使用其他类开发，符合80/20法则。

       Spring JDBC通过实现DaoSupport来支持一致的数据库访问。
 
 
Spring JDBC提供如下DaoSupport实现：
JdbcDaoSupport：用于支持一致的JdbcTemplate访问；
NamedParameterJdbcDaoSupport:继承JdbcDaoSupport，同时提供NamedParameterJdbcTemplate访问；
SimpleJdbcDaoSupport：继承JdbcDaoSupport，同时提供SimpleJdbcTemplate访问。


由于JdbcTemplate、NamedParameterJdbcTemplate、SimpleJdbcTemplate类使用DataSourceUtils获取及释放连接，而且连接是与线程绑定的，因此这些JDBC模板类是线程安全的，即JdbcTemplate对象可以在多线程中重用。