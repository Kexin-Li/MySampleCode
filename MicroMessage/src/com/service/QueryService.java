package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.bean.Command;
import com.bean.CommandContent;
import com.bean.Message;
import com.dao.CommandDao;
import com.dao.MessageDao;
import com.entity.Page;
import com.util.Iconst;

/**
 * 业务逻辑
 * @author Kexin_Li
 *
 */
public class QueryService {

	/**
	 * 查询操作，带分页功能。
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(String command, String description, Page page) {
		MessageDao messageDao = new MessageDao();
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		// 根据条件查询条数
		int totalNumber = messageDao.count(message);
		// 组织分页查询参数
		page.setTotalNumber(totalNumber);
		Map<String, Object> parameter = new HashMap<>();
		parameter.put("message", message);
		parameter.put("page", page);
		// 分页查询并返回结果
		return messageDao.queryMessageList(parameter);
	}
	
	/**
	 * 通过指令查询自动回复内容，并且有一对多映射，可以随机回复一条内容。
	 * @param command
	 * @return
	 */
	public String queryCommandById(String command) {
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		// 如果是帮助指令，则做一次全检索并输出出去。
		if (command.equals(Iconst.HELP_COMMAND)) {
			// 无条件检索
			commandList = commandDao.queryCommandList(null, null);
			// 输出
			StringBuilder result = new StringBuilder();
			for (int i=0; i < commandList.size(); i++) {
				// 在第二行开始换行
				if (i != 0) {
					result.append("<br>");
				}
				result.append("回复[" + commandList.get(i).getName() + "]可以查看" + commandList.get(i).getDescription());
			}
			return result.toString();
		}
		// 没有遇到帮助指令，那么直接按指令搜索。
		commandList = commandDao.queryCommandList(command, null);
		if (commandList.size() > 0) {
			List<CommandContent> contentList = commandList.get(0).getContentList();
			return contentList.get(new Random().nextInt(contentList.size())).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
	
	/**
	 * 根据查询条件分页查询消息列表，通过拦截器实现。
	 * @param command
	 * @param description
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageListByPage(String command, String description, Page page) {
		Map<String, Object> parameter = new HashMap<>();
		// 组织消息对象
		Message message = new Message();
		message.setCommand(command);
		message.setDescription(description);
		parameter.put("message", message);
		parameter.put("page", page);
		MessageDao messageDao = new MessageDao();
		// 分页查询并返回结果
		return messageDao.queryMessageListByPage(parameter);
	}
	
	/**
	 * 通过指令查询自动回复内容
	 * @param content
	 * @return
	 */
//	public String queryCommandById(String command) {
//		MessageDao messageDao = new MessageDao();
//		List<Message> commandList;
//		// 如果是帮助指令，则做一次全检索并输出出去。
//		if (command.equals(Iconst.HELP_COMMAND)) {
//			// 无条件检索
//			commandList = messageDao.queryMessageList(null, null);
//			// 输出
//			StringBuilder result = new StringBuilder();
//			for (int i=0; i < commandList.size(); i++) {
//				// 在第二行开始换行
//				if (i != 0) {
//					result.append("<br>");
//				}
//				result.append("回复[" + commandList.get(i).getCommand() + "]可以查看" + commandList.get(i).getDescription());
//			}
//			return result.toString();
//		}
//		// 没有遇到帮助指令，那么直接按指令搜索。
//		commandList = messageDao.queryMessageList(command, null);
//		if (commandList.size() > 0) {
//			// ???
//			return commandList.get(0).getContent();
//		}
//		return Iconst.NO_MATCHING_CONTENT;
//	}
	
}
