package com.kxw.jdbc.HikariCP;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * Created by kingsonwu on 15/12/13.
 * {<a href = 'https://github.com/brettwooldridge/HikariCP'>@link</a>}
 */
public class TestHikariCP {

    public static void main(String[] args) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
        config.setUsername("bart");
        config.setPassword("51mp50n");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        HikariDataSource ds = new HikariDataSource(config);
    }
}
/**
 * Initialization

 You can use the HikariConfig class like so:

 HikariConfig config = new HikariConfig();
 config.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
 config.setUsername("bart");
 config.setPassword("51mp50n");
 config.addDataSourceProperty("cachePrepStmts", "true");
 config.addDataSourceProperty("prepStmtCacheSize", "250");
 config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

 HikariDataSource ds = new HikariDataSource(config);
 or directly instantiate a HikariDataSource like so:

 HikariDataSource ds = new HikariDataSource();
 ds.setJdbcUrl("jdbc:mysql://localhost:3306/simpsons");
 ds.setUsername("bart");
 ds.setPassword("51mp50n");
 ...
 or property file based:

 HikariConfig config = new HikariConfig("some/path/hikari.properties");
 HikariDataSource ds = new HikariDataSource(config);
 Example property file:

 dataSourceClassName=org.postgresql.ds.PGSimpleDataSource
 dataSource.user=test
 dataSource.password=test
 dataSource.databaseName=mydb
 dataSource.portNumber=5432
 dataSource.serverName=localhost
 or java.util.Properties based:

 Properties props = new Properties();
 props.setProperty("dataSourceClassName", "org.postgresql.ds.PGSimpleDataSource");
 props.setProperty("dataSource.user", "test");
 props.setProperty("dataSource.password", "test");
 props.setProperty("dataSource.databaseName", "mydb");
 props.put("dataSource.logWriter", new PrintWriter(System.out));

 HikariConfig config = new HikariConfig(props);
 HikariDataSource ds = new HikariDataSource(config);
 There is also a System property available, hikaricp.configurationFile,
 that can be used to specify the location of a properties file. If you intend to use this option,
 construct a HikariConfig or HikariDataSource instance using the default constructor and the properties file will be loaded.
*/