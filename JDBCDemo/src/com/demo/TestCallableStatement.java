package com.demo;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestCallableStatement {

	public static void main(String[] args) {
		test1();
	}
	 
	static void test0() {
		Connection conn = DBUtil.open();
		try {
			CallableStatement cstmt = conn.prepareCall("{call all_customer()}");
			ResultSet rs = cstmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				System.out.println(id + ", " + name + ", " + email); 
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	static void test1() {
		Connection conn = DBUtil.open();
		try {
			CallableStatement cstmt = conn.prepareCall("{call insert_customer(?,?)}");
			cstmt.setString(1, "tom123");
			cstmt.setString(2, "tom123@gmail.com");
			cstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
}
