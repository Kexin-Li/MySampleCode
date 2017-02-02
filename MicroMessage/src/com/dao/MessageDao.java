package com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.Message;
import com.db.DBAccess;

/**
 * 数据库相关操作
 * @author Kexin_Li
 *
 */
public class MessageDao {
	
	/**
	 * 查询操作
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(String command, String description) {
		DBAccess dbAccess = new DBAccess();
		List<Message> messageList = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			// 获取 SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// 将 command 和 description 封装在 message 对象里
			Message message = new Message();
			message.setCommand(command);
			message.setDescription(description); 
			// 执行 SQL 语句
			messageList = sqlSession.selectList("Message.queryMessageList", message);
			// 【已忘记】手动 commit
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 【已忘记】关掉SqlSession
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
		return messageList;
	}
	
	/**
	 * 单条删除
	 * @param id
	 */
	public void deleteOne(int id) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			// 获取 SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// 执行 SQL 语句
			sqlSession.delete("Message.deleteOne", id);
			// 【已忘记】手动 commit
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 【已忘记】关掉SqlSession
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
	/**
	 * 批量删除
	 * @param id
	 */
	public void deleteBatch(List<Integer> ids) {
		DBAccess dbAccess = new DBAccess();
		SqlSession sqlSession = null;
		try {
			// 获取 SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// 执行 SQL 语句
			sqlSession.delete("Message.deleteBatch", ids);
			// 【已忘记】手动 commit
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 【已忘记】关掉SqlSession
			if (sqlSession != null) {
				sqlSession.close();
			}
		}
	}
	
}
