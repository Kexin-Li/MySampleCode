package com.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * ���ݿ�Ӵ���
 * @author Kexin_Li
 */
public class DBAccess {

	/**
	 * ��ȡSqlSession
	 * @return
	 * @throws IOException
	 */
	public SqlSession getSqlSession() throws IOException {
		// ͨ�������ļ��õ����ݿ����ӵ���Ϣ
		Reader reader = Resources.getResourceAsReader("com/config/Configuration.xml");
		// ͨ�������ļ��õ�SQLSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// ͨ��SQLSessionFactory��һ�����ݿ�Ự
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
	
}
