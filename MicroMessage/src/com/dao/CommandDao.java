package com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.Command;
import com.db.DBAccess;

/**
 * ��ָ���б���ص����ݿ����
 * @author Kexin_Li
 *
 */
public class CommandDao {

	/**
	 * ��ѯָ���б�
	 * @param name
	 * @param description
	 * @return
	 */
	public List< Command> queryCommandList(String name, String description) {
		DBAccess dbAccess = new DBAccess();
		List< Command>  commandList = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			// ��ȡ SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// �� command �� description ��װ�� message ������
			 Command  command = new  Command();
			 command.setName(name);
			 command.setDescription(description); 
			// ִ�� SQL ���
			 commandList = sqlSession.selectList("Command.queryCommandList", command);
			// �������ǡ��ֶ� commit
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �������ǡ��ص�SqlSession
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return commandList;
	}
}
