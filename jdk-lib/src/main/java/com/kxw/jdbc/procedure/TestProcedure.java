package com.kxw.jdbc.procedure;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

import java.sql.DriverManager;

/**
 * Created by kxw on 2016/1/5.
 */
public class TestProcedure {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.jdbc.Driver");

        Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/test?user=root&password=111111");

        String sql = "{call sel(?)}";

        CallableStatement cs = (CallableStatement)con.prepareCall(sql);

        cs.execute();

        String name = cs.getString(1);

        System.out.println(name);
    }
}
/**
 * drop procedure if exists sel;

 create procedure sel(out name1 varchar(225))

 begin

 select name into name1 from a limit 1;

 end
 */