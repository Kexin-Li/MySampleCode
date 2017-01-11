package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 学习PreparedStatement基本操作
 * @author Kexin_Li
 *
 */
public class TestPreparedStatement {

	public static void main(String[] args) {
//		insert("tom1", "tom1@gmail.com");
		
		Customer c = new Customer();
//		c.setName("rose"); c.setEmail("rose@email.com");
//		insert(c);
		
		c.setName("lite"); c.setId(2);
		update(c);
		
//		delete(1);
		
//		Customer c1 = query(5); 
//		System.out.println(c1.getName());
	}
	
	/**
	 * 添加一个字段
	 * @param name
	 * @param email
	 */
	static void insert(String name, String email) {
		String sql = "insert into CustomerTbl(name,email) values(?,?)";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, email);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 添加一个字段
	 * @param c
	 */
	static void insert(Customer c) {
		String sql = "insert into CustomerTbl(name,email) values(?,?)";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setString(2, c.getEmail());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 更新一个字段
	 * @param c
	 */
	static void update(Customer c) {
		String sql = "update CustomerTbl set name = ? where id = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c.getName());
			pstmt.setInt(2, c.getId());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 删除一个字段
	 * @param id
	 */
	static void delete(int id) {
		String sql = "delete from CustomerTbl where id = ? ";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 查询一个字段
	 * @param id
	 * @return
	 */
	static Customer query(int id) {
		String sql = "select id,name,email from CustomerTbl where id = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String name = rs.getString(2);
				String email = rs.getString(3);
				Customer c = new Customer();
				c.setId(id); c.setName(name); c.setEmail(email);
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}
}