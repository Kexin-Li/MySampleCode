package com.demo;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * ��һ��JDBCʵ�������DBUtil.javaʹ��
 * @author Kexin_Li
 */
public class TestDemo {

	public static void main(String[] args) {
		
		// ͨ�����ݿ����ӹ���������
		Connection conn = DBUtil.open();
		
		/**
		 * �������ݿ�
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
			// �ر����ݿ�
			DBUtil.close(conn);
		}
	}
}
