package com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.Command;
import com.db.DBAccess;

/**
 * 与指令列表相关的数据库操作
 * @author Kexin_Li
 *
 */
public class CommandDao {

	/**
	 * 查询指令列表
	 * @param name
	 * @param description
	 * @return
	 */
	public List< Command> queryCommandList(String name, String description) {
		DBAccess dbAccess = new DBAccess();
		List< Command>  commandList = new ArrayList<>();
		SqlSession sqlSession = null;
		try {
			// 获取 SqlSessions
			sqlSession = dbAccess.getSqlSession();
			// 将 command 和 description 封装在 message 对象里
			 Command  command = new  Command();
			 command.setName(name);
			 command.setDescription(description); 
			// 执行 SQL 语句
			 commandList = sqlSession.selectList("Command.queryCommandList", command);
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
		return commandList;
	}
}
