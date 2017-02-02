package com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.Message;
import com.db.DBAccess;

/**
 * ���ݿ���ز���
 * @author Kexin_Li
 *
 */
public class MessageDao {
	
	/**
	 * ��ѯ����
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(String command, String description) {
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			// ��ȡ SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// �� command �� description ��װ�� message ������
			Message message = new Message();
			message.setCommand(command);
			message.setDescription(description); 
			// ִ�� SQL ���
			messageList = sqlSession.selectList("Message.queryMessageList", message);
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
		return messageList;
	}
	
	/**
	 * ����ɾ��
	 * @param id
	 */
	public void deleteOne(int id) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			// ��ȡ SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// ִ�� SQL ���
			sqlSession.delete("Message.deleteOne", id);
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
	}
	
	/**
	 * ����ɾ��
	 * @param id
	 */
	public void deleteBatch(List<Integer> ids) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			// ��ȡ SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// ִ�� SQL ���
			sqlSession.delete("Message.deleteBatch", ids);
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
	}
	
}
