package com.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TestResultSet {

	public static void main(String[] args) {
		query();
	}
 
	static List<Customer> query() {
		Connection conn = DBUtil.open();
		String sql = "select id, name,email from CustomerTbl";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<Customer> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				String email = rs.getString(3);
				
				Customer c = new Customer();
				c.setId(id); c.setName(name); c.setEmail(email);
				list.add(c);
			}
			System.out.println(list);
			return list;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
