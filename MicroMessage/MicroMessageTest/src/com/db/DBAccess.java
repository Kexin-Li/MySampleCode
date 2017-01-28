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
		// ͨ�������ļ���ȡ���ݿ����������Ϣ
		// �����������쳣������Dao�㴦���쳣
		Reader reader = Resources.getResourceAsReader("com/config/Configuration.xml");
		// ͨ��������Ϣ����SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		// ͨ��SqlSessionFactory��һ�����ݿ�Ự
		SqlSession sqlSession = sqlSessionFactory.openSession();
		return sqlSession;
	}
}
