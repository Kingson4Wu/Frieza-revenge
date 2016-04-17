package org.msb.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 可以前后移动的结果集
 * @author Jukey
 *
 */
public class TestScroll {

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost/company?user=root&password=mysql");

            // 设置参数，使得结果集对于前后移动不敏感，但是不能更新
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery("select * from emp order by sal");
            // 将结果集rs看成一张新表（将原表emp按sal升序排列后的新表）
            // 刚开始，游标指针指在新表第一条数据的头顶上
            rs.next();
            // 执行过next方法后，游标指向第一条数据
            System.out.println(rs.getInt(1));
            rs.last();
            // 执行过last方法后，游标指向最后一条数据
            System.out.println(rs.getString(1));
            System.out.println(rs.isLast());
            System.out.println(rs.isAfterLast());
            // Retrieves the current row number. 
            // 返回当前行号（因为当前指针正指向最后一条记录，所以该返回值也可以用于确定一共有多少条记录）
            System.out.println(rs.getRow());
            rs.previous();
            // 执行过previous方法后，游标上移一位
            System.out.println(rs.getString(1));
            rs.absolute(6);
            // Moves the cursor（指针、游标） to the given row number in this ResultSet object. 
            // If the row number is positive（正数）, the cursor moves to the given row number with respect to the beginning of the result set.
            // （从第一行开始数，往下移）
            // The first row is row 1, the second is row 2, and so on. 
            System.out.println(rs.getString(1));

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