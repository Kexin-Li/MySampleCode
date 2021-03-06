package com.demo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * 学习Statement基本操作
 * @author Kexin_Li
 */
public class TestStatement {

	public static void main(String[] args) {
//		createTable();
//		insert();
//		update();
//		delete();
		query();
	}

	/**
	 * 创建表
	 */
	static void createTable() {
		Connection conn = DBUtil.open();
		String sql = "create table UserTbl(id int primary key auto_increment,name varchar(20))";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 添加字段
	 */
	static void insert() {
		Connection conn = DBUtil.open();
		String sql = "insert into UserTbl (name) value('tom')";
		try {
			Statement stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 更新表
	 */
	static void update() {
		Connection conn = DBUtil.open();
		String sql = "update UserTbl set name='big tom' where id = 3";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 删除字段
	 */
	static void delete() {
		Connection conn = DBUtil.open();
		String sql = "delete from UserTbl where id = 1";
		try {
			Statement stmt = conn.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 查询字段
	 */
	/*
	static void query() {
		Connection conn = DBUtil.open();
		String sql = "select id,name from UserTbl";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				System.out.println(id + ", " + name);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
	} */
	
	/**
	 * 查询字段并返回一个List
	 * @return
	 */
	static List<User> query() {
		Connection conn = DBUtil.open();
		String sql = "select id,name from UserTbl";
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			List<User> list = new ArrayList<>();
			while (rs.next()) {
				int id = rs.getInt(1);
				String name = rs.getString(2);
				
				User u = new User();
				u.setId(id); u.setName(name);
				list.add(u);
			}
			 
			System.out.println(list);
			return list;
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.close(conn);
		}
		
		return null;
	}
} 