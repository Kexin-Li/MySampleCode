package com.demo;

//import java.io.FileNotFoundException;
import java.io.FileReader;
//import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 数据库连接的工具类
 * @author Kexin_Li
 */
public class DBUtil {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	static {
		Properties prop = new Properties();
		Reader in;
		try {
			in = new FileReader("src\\config.properties");
			prop.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// 从配置文件拿到几个参数
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
	}
	
	public static Connection open() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
