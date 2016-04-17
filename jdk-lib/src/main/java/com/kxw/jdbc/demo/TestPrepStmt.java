package com.kxw.jdbc.demo;
/**
 * 预编译SQL语句，灵活指定SQL语句中的变量。
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * （很重要）
 * 相对于最普通的Statement，预编译SQL语句，灵活指定SQL语句中的变量。
 * 看起来更清楚，执行效率更高。实际开发中大都使用预编译SQL语句。
 * @author Jukey
 *
 */
public class TestPrepStmt {

    public static void main(String[] args) {

        // 给程序传3个参数进来
        if(args.length != 3) {
            System.out.println("对不起，您老人家输入的参数个数不对，请输入3个参数123。。。");
            System.exit(-1);
        }

        int deptno = 0;
        // 对可能因为第一个参数格式不对而产生的数据格式异常进行监控、捕获和相应处理
        try {
            deptno = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("第一个参数的格式不对，请输入整数。。。");
            e.printStackTrace();
        }

        // 从参数端获取部门名和位置
        String dname = args[1];
        String loc = args[2];

        Connection conn = null;
        // 预编译sql语句对象
        PreparedStatement prepStmt = null;

        try {
            // 寻找针对MySql的JDBC驱动包（需要将驱动的jar包导入当前项目，本人此处使用的是mysql-connector-java-5.0.8-bin.jar）
            Class.forName("com.mysql.jdbc.Driver");
            // 试图建立到给定数据库 URL 的连接。DriverManager 试图从已注册的驱动程序集中选择一个适当的驱动程序。
            // 获取和某一具体数据库的连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost/company?user=root&password=mysql");

            // 一个？表示一个占位符，需要向各个位置传入一个值
            String sql = "insert into dept values(?,?,?)";
            prepStmt = conn.prepareStatement(sql);

            // 向预编译sql语句对象中传值
            prepStmt.setInt(1, deptno);
            prepStmt.setString(2, dname);
            prepStmt.setString(3, loc);
            // 执行插入操作
            prepStmt.executeUpdate();

        } catch (ClassNotFoundException e) {
            System.out.println("驱动程序未找到，请加入mysql.jdbc的驱动包。。。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("执行SQL语句过程中出现了错误。。。");
            e.printStackTrace();
        } finally {
            // 最后关闭资源（后打开的先关闭）
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                    prepStmt = null;
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