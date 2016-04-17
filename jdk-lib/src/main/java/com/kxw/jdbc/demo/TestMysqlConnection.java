package com.kxw.jdbc.demo;
/**
 * 最简单的JDBC连接测试（连接MySQL4.1）

 说明：JDBC的程序要尽可能地写完善些（比如try-catch语句的使用，
 conn的个数尽可能地少、使用完后一定要关闭资源等等，节省数据库资 源）。
 此程序还有一处可以改进，就是在捕获到异常后，可以使用Log4j的知识将异常信息打印到日志log中，
 便于排错和日常管理。

 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 最简单的JDBC连接测试
 * 将Java程序和MySQL数据库连接起来，查询出数据表中的信息
 * @author Jukey
 *
 */
public class TestMysqlConnection {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 寻找针对MySql的JDBC驱动包（需要将驱动的jar包导入当前项目，本人此处使用的是mysql-connector-java-5.0.8-bin.jar）
            // Returns the Class object associated with the class or interface with the given string name.
            // 根据str参数中指定的内容去寻找对应的类，该方法返回的是Class类型。
            Class.forName("com.mysql.jdbc.Driver");
            // 试图建立到给定数据库 URL 的连接。DriverManager 试图从已注册的驱动程序集中选择一个适当的驱动程序。
            // 获取和某一具体数据库的连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost/company?user=root&password=mysql");
            // 产生表达式
            stmt = conn.createStatement();
            // 指定sql语句
            String sql = "select * from dept";
            // 执行查询，结果存储在结果集对象rs中
            rs = stmt.executeQuery(sql);
            // 遍历结果集
            while(rs.next()) {
                // 将结果集对象rs中的每条数据取出，进行相应处理
                System.out.println(rs.getString("dname"));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("驱动程序未找到，请加入mysql.jdbc的驱动包。。。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("执行SQL语句过程中出现了错误。。。");
            e.printStackTrace();
        } finally {
            // 最后关闭资源（后打开的先关闭）
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
                System.out.println("关闭资源时出现了错误。。。");
                e.printStackTrace();
            }
        }
    }

}
  