package com.demo;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 第一个JDBC实例，配合DBUtil.java使用
 * @author Kexin_Li
 */
public class TestDemo {

	public static void main(String[] args) {
		
		// 通过数据库连接工具类连接
		Connection conn = DBUtil.open();
		
		/**
		 * 连接数据库
		 */
		// get Driver
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		}
		// get Connection 
//		try {
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb","root","yjzsgNHZok147");
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		// Query
		String sql = "select id, name, email from CustomerTbl";
		try {
			Statement stmt = conn.createStatement();
			// query
			ResultSet rs = stmt.executeQuery(sql);
			// cursor
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				System.out.println(id + ", " + name + ", " + email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { 
			// 关闭数据库
			DBUtil.close(conn);
		}
	}
}
