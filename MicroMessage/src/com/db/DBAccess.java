package com.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 数据库接触类
 * @author Kexin_Li
 */
public class DBAccess {

	/**
	 * 获取SqlSession
	 * @return
	 * @throws IOException
	 */
	public SqlSession getSqlSession() throws IOException {
		// 通过配置文件得到数据库连接等信息
		Reader reader = Resources.getResourceAsReader("com/config/Configuration.xml");
		// 通过配置文件拿到SQLSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 通过SQLSessionFactory打开一个数据库会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
}
