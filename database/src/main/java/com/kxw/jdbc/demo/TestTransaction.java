package com.kxw.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * 事务处理(重要)
 * 手动提交、一旦捕获到SQL异常则进行回滚，以保证数据一致性
 * 最典型应用场合：银行转账过程中，两个帐户的增减操作要保证原子性
 * @author Jukey
 *
 */
public class TestTransaction {

    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement prepStmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/company?user=root&password=mysql");

            // 事务处理的第一句，必须先将自动提交设置成false（默认的是true），以进行手动提交
            conn.setAutoCommit(false);
            prepStmt = conn.prepareStatement("");
            // Adds the given SQL command to the current list of commmands for this Statement object. 
            prepStmt.addBatch("insert into dept values(100,'dname100','loc100')");
            prepStmt.addBatch("insert into dept values(101,'dname101','loc101')");
            prepStmt.addBatch("insert into dept values(102,'dname102','loc102')");
            // Submits a batch of commands to the database for execution 
            // and if all commands execute successfully,returns an array of update counts.
            // 执行一批SQL命令语句
            prepStmt.executeBatch();
            // 手动提交
            conn.commit();
            // 将自动提交再设置回true（恢复现场）
            conn.setAutoCommit(true);

        } catch (ClassNotFoundException e) {
            System.out.println("驱动程序未找到，请加入mysql.jdbc的驱动包。。。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("执行SQL语句过程中出现了错误，程序会自动进行回滚操作，以保证数据库中数据的一致性。。。");
            e.printStackTrace();
            // 当上面try中的程序段在执行过程中发生SQL执行异常时，此处进行回滚。
            // 以保证所有的操作要么都执行，要么都不执行，以保证数据库中数据的一致性。
            if(conn != null) {
                try {
                    conn.rollback();
                    // 当发生SQL异常时，上面try段中的最后一句conn.setAutoCommit(true);很可能没有执行到。
                    // 此处再加一套保险，将自动提交设置回去（保护现场）。
                    conn.setAutoCommit(true);
                } catch (SQLException e1) {
                    System.out.println("执行回滚操作或自动提交恢复设置过程中出现了错误，问题比较严重，请联系管理员。。。");
                    e1.printStackTrace();
                }
            }
        } finally {
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