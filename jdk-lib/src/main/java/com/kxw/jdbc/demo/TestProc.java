package com.kxw.jdbc.demo;

import java.sql.DriverManager;
import java.sql.Types;

import com.mysql.jdbc.CallableStatement;
import com.mysql.jdbc.Connection;

public class TestProc {
	   public static void main(String[] args) throws Exception{
		   
		
			Class.forName("com.mysql.jdbc.Driver");
		
        Connection   conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/company?user=root&password=mysql");
		  CallableStatement cstmt=(CallableStatement) conn.prepareCall("call p(?,?,?,?)");
		   cstmt.registerOutParameter(3, Types.INTEGER);
		   cstmt.registerOutParameter(4, Types.INTEGER);
		   cstmt.setInt(1, 3);
		   cstmt.setInt(2, 4);
		   cstmt.setInt(4, 5);
		   cstmt.execute();
		   System.out.println(cstmt.getInt(3));
		   System.out.println(cstmt.getInt(4));
		   cstmt.close();
		   conn.close();
		   
		   
	   }

}
