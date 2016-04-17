package com.kxw.memcached.util;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kxw.memcached.bean.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
public class UserDao extends JdbcConnector {
    // 定义全局变量
    Connection conn = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    /**
     * 根据Id查询用户
     * @param uid
     * @return
     */
    public User getUserById(int uid) {
       // 创建User对象
       User user = null;
       // 创建SQL语句
       String sql = "select * from user where uid=?";
       
       try {
           // 获得数据库连接
           conn = (Connection) this.getConn();
           // 通过Connection对象创建PrepareStatement对象
           pstmt = (PreparedStatement) conn.prepareStatement(sql);
           // 设置SQL语句的参数
           pstmt.setInt(2, uid);
           // 执行查询，将查询结果赋给ResultSet对象
           rs = (ResultSet) pstmt.executeQuery();
           // 遍历指针
           while (rs.next())
           {
              user = new User();
              user.setUid(rs.getInt("uid"));
              user.setUname(rs.getString("uname"));
              user.setUpass(rs.getString("upass"));
           }
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return user;
    }
    
    /**
     * 查询所有用户
     * @return
     */
    @SuppressWarnings("unchecked")
    public List getUserList() {
       // 创建ArrayList对象
       List userList = new ArrayList();
       
       // 创建SQL对象
       String sql = "select * from user";
       
       try {
           conn = (Connection) this.getConn();
           pstmt = (PreparedStatement) conn.prepareStatement(sql);
           rs = (ResultSet) pstmt.executeQuery();
           while (rs.next())
           {
              User user = new User();
              user.setUid(rs.getInt("uid"));
              user.setUname(rs.getString("uname"));
              user.setUpass(rs.getString("upass"));
              
              userList.add(user);
           }
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       } catch (SQLException e) {
           e.printStackTrace();
       }
       return userList;
    }
}