package com.demo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * CustomerDao µœ÷¿‡
 * @author Kexin_Li
 *
 */
public class CustomerDaoImpl implements CustomerDao{
	
	@Override
	public void add(Customer c) {
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

	@Override
	public void update(Customer c) {	
		String sql = "update CustomerTbl set id=?,name=?,email=?";
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

	@Override
	public void delete(int id) {		
		String sql = "delete from CustomerTbl where id = ?";
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

	@Override
	public Customer getCustomerById(int id) {
		String sql = "select id,name,email from CustomerTbl where id = ?";
		Connection conn = DBUtil.open();
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, id); 
			ResultSet rs = pstmt.executeQuery(sql);
			List<Customer> list = new ArrayList<>();
			if (rs.next()) {
				String name = rs.getString(2);
				String email = rs.getString(3);
				
				Customer c = new Customer();
				c.setId(id); c.setName(name); c.setEmail(email);
				list.add(c);
				
				return c;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}

	@Override
	public List<Customer> query() {
		String sql = "select id,name,email from CustomerTbl";
		Connection conn = DBUtil.open();
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
				
				return list;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		return null;
	}
}
