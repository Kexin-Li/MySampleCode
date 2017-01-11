package com.demo;

import java.io.FileReader;
//import java.io.IOException;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * ���ݿ����ӵĹ����࣬���config.propertiesʹ��
 * @author Kexin_Li
 */
public class DBUtil {

	private static String driver;
	private static String url;
	private static String username;
	private static String password;
	
	/**
	 * ��̬���ʼ��driver�Ȳ���
	 */
	static {
		Properties prop = new Properties();
		Reader in;
		try {
			in = new FileReader("src\\config.properties");
			prop.load(in);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		// �������ļ��õ���������
		driver = prop.getProperty("driver");
		url = prop.getProperty("url");
		username = prop.getProperty("username");
		password = prop.getProperty("password");
	}
	
	/**
	 * �����ݿ�����
	 * @return
	 */
	public static Connection open() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * �ر����ݿ�����
	 * @param conn
	 */
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
