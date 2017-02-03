package com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
	 * ��ѯ�����з�ҳ���ܡ�
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(Map<String, Object> parameter) {
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			// ��ȡ SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// ִ�� SQL ���
			// ͨ���ӿ���ʵ�֣�������ֱ�ӵ���xml�еķ�����
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			messageList = imessage.queryMessageList(parameter);
			// �ֶ� commit
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ص�SqlSession
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	/**
	 * ���ݲ�ѯ������ѯ��Ϣ�б������
	 * @param message
	 * @return
	 */
	public int count(Message message) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			// ��ȡ SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// ִ�� SQL ���
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			result = imessage.count(message);
			// �ֶ� commit
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// �ص�SqlSession
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return result;
	}
	
	/**
	 * ���ݲ�ѯ������ҳ��ѯ��Ϣ�б�,��ͨ��������ʵ�֡�
	 * @param command
	 * @param description
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageListByPage(Map<String, Object> parameter) {
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<Message>();
		SqlSession sqlSession = null;
		try {
			sqlSession = dbAccess.getSqlSession();
			// ͨ��sqlSessionִ��SQL���
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			messageList = imessage.queryMessageListByPage(parameter);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	/**
	 * ��ѯ����
	 * @param command
	 * @param description
	 * @return
	 */
//	public List<Message> queryMessageList(String command, String description) {
//		DBAccess dbAccess = new DBAccess();
//		List<Message> messageList = new ArrayList<>();
//		SqlSession sqlSession = null;
//		try {
//			// ��ȡ SqlSessions
//			sqlSession = dbAccess.getSqlSession();
//			// �� command �� description ��װ�� message ������
//			Message message = new Message();
//			message.setCommand(command);
//			message.setDescription(description); 
//			// ִ�� SQL ���
//			// ͨ���ӿ���ʵ�֣�������ֱ�ӵ���xml�еķ�����
////			messageList = sqlSession.selectList("Message.queryMessageList", message);
//			IMessage imessage = sqlSession.getMapper(IMessage.class);
//			messageList = imessage.queryMessageList(message);
//			// �������ǡ��ֶ� commit
//			sqlSession.commit();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			// �������ǡ��ص�SqlSession
//			if (sqlSession != null) {
//				sqlSession.close();
//			}
//		}
//		return messageList;
//	}
	
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
