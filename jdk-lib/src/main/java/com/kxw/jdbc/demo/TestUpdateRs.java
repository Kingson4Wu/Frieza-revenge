package com.kxw.jdbc.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 可以更新的结果集
 * @author Jukey
 *
 */
public class TestUpdateRs {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/company?user=root&password=mysql");

            // 设置参数，使得结果集对于前后移动不敏感，并且可以更新
            stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("select * from emp2");

            rs.next();
            //更新一行数据
            rs.updateString("ename","VVVV");
            // 取消更新（这句使用的话，当前记录的ename字段的值不会被更新；这句被屏蔽的话，当前记录的ename字段的值会被更新掉）
            //rs.cancelRowUpdates();
            rs.updateRow();

            // 插入新行
            rs.moveToInsertRow();
            rs.updateInt(1, 9999);
            rs.updateString("ename","AAAA");
            rs.updateInt("mgr", 7839);
            rs.updateDouble("sal", 99.99);
            rs.insertRow();
            // 将光标移动到新建的行
            rs.moveToCurrentRow();

            // 删除行
            rs.absolute(5);
            // 将第5行数据删除
            rs.deleteRow();

            // 取消更新的语句
            //rs.cancelRowUpdates();

        } catch (ClassNotFoundException e) {
            System.out.println("驱动程序未找到，请加入mysql.jdbc的驱动包。。。");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("执行SQL语句过程中出现了错误。。。");
            e.printStackTrace();
        } finally {
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