package com.kxw.jdbc.demo;
/**
 * 最简单的数据操作语言DML语句测试，
 * 通过JDBC在Java程序中向MySQL数据库的表中插入一条数据
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * (重要)
 * 最简单的数据操作语言DML语句测试，通过JDBC在Java程序中向MySQL数据库的表中插入一条数据
 * DML 事件包括对表或视图发出的 UPDATE、INSERT 或 DELETE 语句。
 * @author Jukey
 *
 */
public class TestDML {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;

        try {
            // 寻找针对MySql的JDBC驱动包（需要将驱动的jar包导入当前项目，本人此处使用的是mysql-connector-java-5.0.8-bin.jar）
            Class.forName("com.mysql.jdbc.Driver");
            // 试图建立到给定数据库 URL 的连接。DriverManager 试图从已注册的驱动程序集中选择一个适当的驱动程序。
            // 获取和某一具体数据库的连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost/company?user=root&password=mysql");
            // 产生表达式
            stmt = conn.createStatement();
            // 指定要执行插入操作的sql语句
            String sql = "insert into dept values(60,'JPO','shanghai')";
            // 执行插入操作
            stmt.executeUpdate(sql);

        } catch (ClassNotFoundException e) {
            System.out.println("驱动程序未找到，请加入mysql.jdbc的驱动包。。。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("执行SQL语句过程中出现了错误。。。");
            e.printStackTrace();
        } finally {
            // 最后关闭资源（后打开的先关闭）
            try {
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
