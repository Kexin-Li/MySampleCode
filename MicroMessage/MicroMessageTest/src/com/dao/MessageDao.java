package com.dao;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.bean.Message;
import com.db.DBAccess;

/**
 * Dao Class
 * @author Kexin_Li
 *
 */
public class MessageDao {

	public List<Message> queryMessageList(String command, String description) {
		DBAccess dbAcess = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList = new ArrayList<>();
		try {
			 Message message = new Message();
			 message.setCommand(command);
			 message.setDescription(description);
			 
			 sqlSession = dbAcess.getSqlSession();
			// 执行SQL语句
			 messageList = sqlSession.selectList("Message.queryMessageList", message);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			sqlSession.close();
		}
		return messageList; 
	}
	
	/**
	 * query messageList method
	 * @param command
	 * @param description
	 * @return
	 */
//	public List<Message> queryMessageList(String command, String description) {
//		List<Message> messageList = new ArrayList<>();
//		try {
//			// 加载驱动 Driver
//			Class.forName("com.mysql.jdbc.Driver");
//			// 获取数据库连接
//			Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/micro_message", "root", "yjzsgNHZok147");
//			// SQL 语句
//			StringBuilder sql = new StringBuilder("select id, command, description, content from message where 1 = 1");
//			// 缓存 SQL 语句的 List
//			List<String> paramList = new ArrayList<>();
//			// 判断是否需要添加 command SQL
//			if (command != null && !"".equals(command.trim())) {
//				sql.append(" and command = ?");
//				paramList.add(command);
//			}
//			// 判断是否需要添加 description SQL
//			if (description != null && !"".equals(description.trim())) {
//				sql.append(" and description like '%' ? '%' ");
//				paramList.add(description);
//			}
//			// 执行 SQL 语句
//			PreparedStatement statement = conn.prepareStatement(sql.toString());
//			// 执行缓存的 SQL 语句
//			for (int i=0; i < paramList.size(); i++) {
//				statement.setString(i + 1, paramList.get(i)); 
//			}
//			// 执行查询并保存至结果集
//			ResultSet rs = statement.executeQuery();
//			// 遍历结果集并传给 Message
//			while (rs.next()) {
//				Message message = new Message();
//				messageList.add(message);
//				
//				message.setId(rs.getString("id"));
//				message.setCommand(rs.getString("command"));
//				message.setDescription(rs.getString("description"));
//				message.setContent(rs.getString("content"));
//			}
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//		return messageList; 
//	}
}
