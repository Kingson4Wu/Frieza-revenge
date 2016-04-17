package com.kxw.jdbc.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import com.bjsxt.bbs2009.model.Category;
//import com.bjsxt.bbs2009.util.DB;

public class CategoryService {
	/*public void add(Category c) {
		Connection conn = DB.createConn();
		String sql = "insert into _category values (null, ?, ?)";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setString(1, c.getName());
			ps.setString(2, c.getDescription());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
	}
	
	public List<Category> list() {
		Connection conn = DB.createConn();
		String sql = "select * from _category";
		PreparedStatement ps = DB.prepare(conn, sql);
		List<Category> categories = new ArrayList<Category>();
		try {
			ResultSet rs = ps.executeQuery();
			Category c = null;
			while(rs.next()) {
				c = new Category();
				c.setName(rs.getString("name"));
				c.setDescription(rs.getString("description"));
				categories.add(c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
		return categories;
	}
	
	public void delete(Category c) {
		deleteById(c.getId());
	}
	
	public void deleteById(int id) {
		Connection conn = DB.createConn();
		String sql = "delete from _category where id = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
	}
	
	public void update(Category c) {
		Connection conn = DB.createConn();
		String sql = "update _category set name = ?, description = ? where id = ?";
		PreparedStatement ps = DB.prepare(conn, sql);
		try {
			ps.setString(1, c.getName());
			ps.setString(2, c.getDescription());
			ps.setInt(3, c.getId());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DB.close(ps);
		DB.close(conn);
	}*/
}
