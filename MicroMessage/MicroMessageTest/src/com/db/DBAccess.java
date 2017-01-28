package com.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * DB Class
 * @author Kexin_Li
 *
 */
public class DBAccess {

	/**
	 * Get SqlSession method
	 * @return
	 * @throws IOException
	 */
	public SqlSession getSqlSession() throws IOException {
		// 通过配置文件获取数据库连接相关信息
		// 这里向外抛异常，留给Dao层处理异常
		Reader reader = Resources.getResourceAsReader("com/config/Configuration.xml");
		// 通过配置信息构建SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// 通过SqlSessionFactory打开一个数据库会话
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
