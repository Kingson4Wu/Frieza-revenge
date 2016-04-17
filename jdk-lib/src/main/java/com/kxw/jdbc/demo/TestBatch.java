package com.kxw.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 批处理（不重要）
 * 其实在事务处理程序中已经有批处理的代码段了
 * @author Jukey
 *
 */
public class TestBatch {

    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        PreparedStatement prepStmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/company?user=root&password=mysql");
            
            /*
            // ①stmt普通SQL语句处理段
            stmt = conn.createStatement();
            
            stmt.addBatch("insert into dept values(60,'d60','loc60')");
            stmt.addBatch("insert into dept values(61,'d61','loc61')");
            stmt.addBatch("insert into dept values(62,'d62','loc62')");
            stmt.addBatch("insert into dept values(63,'d63','loc63')");
            stmt.addBatch("insert into dept values(64,'d64','loc64')");
            
            stmt.executeBatch();
            */

            // ②预编译语句处理段
            String sql = "insert into dept values(?,?,?)";
            prepStmt = conn.prepareStatement(sql);

            prepStmt.setInt(1, 70);
            prepStmt.setString(2, "d70");
            prepStmt.setString(3, "loc70");
            // 将一条插入语句加入到Batch命令中去
            prepStmt.addBatch();

            prepStmt.setInt(1, 71);
            prepStmt.setString(2, "d71");
            prepStmt.setString(3, "loc71");
            prepStmt.addBatch();

            prepStmt.setInt(1, 72);
            prepStmt.setString(2, "d72");
            prepStmt.setString(3, "loc72");
            prepStmt.addBatch();

            prepStmt.setInt(1, 73);
            prepStmt.setString(2, "d73");
            prepStmt.setString(3, "loc73");
            prepStmt.addBatch();

            prepStmt.setInt(1, 75);
            prepStmt.setString(2, "d75");
            prepStmt.setString(3, "loc75");
            prepStmt.addBatch();

            // 执行批处理命令
            prepStmt.executeBatch();

        } catch (ClassNotFoundException e) {
            System.out.println("驱动程序未找到，请加入mysql.jdbc的驱动包。。。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("执行SQL语句过程中出现了错误。。。");
            e.printStackTrace();
        } finally {
            try {
                if (prepStmt != null) {
                    prepStmt.close();
                    prepStmt = null;
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

