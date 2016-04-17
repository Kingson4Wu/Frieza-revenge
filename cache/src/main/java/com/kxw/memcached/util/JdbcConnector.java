package com.kxw.memcached.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class JdbcConnector {
  // 定义数据库连接常量
    private final static String DRIVER = "com.mysql.jdbc.Driver";
    private final static String URL = "jdbc:mysql://127.0.0.1:3306/test";
    private final static String DBNAME = "root";
    private final static String DBPASS = "123456";
    
    /**
     * 得到数据库连接
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Connection getConn()throws ClassNotFoundException,SQLException {
       // 加载驱动
       Class.forName(DRIVER);
       // 通过DriverManager对象得到连接
       Connection conn = DriverManager.getConnection(URL,DBNAME,DBPASS);
       // 返回数据库连接
       return conn;
    }
}