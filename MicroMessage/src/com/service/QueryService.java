package com.service;

import java.util.List;
import java.util.Random;

import com.bean.Command;
import com.bean.CommandContent;
import com.bean.Message;
import com.dao.CommandDao;
import com.dao.MessageDao;
import com.util.Iconst;

/**
 * ҵ���߼�
 * @author Kexin_Li
 *
 */
public class QueryService {

	/**
	 * ��ѯ����
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessageList(String command, String description) {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
	
	/**
	 * ͨ��ָ���ѯ�Զ��ظ����ݣ�������һ�Զ�ӳ�䣬��������ظ�һ�����ݡ�
	 * @param command
	 * @return
	 */
	public String queryCommandById(String command) {
		CommandDao commandDao = new CommandDao();
		List<Command> commandList;
		// ����ǰ���ָ�����һ��ȫ�����������ȥ��
		if (command.equals(Iconst.HELP_COMMAND)) {
			// ����������
			commandList = commandDao.queryCommandList(null, null);
			// ���
			StringBuilder result = new StringBuilder();
			for (int i=0; i < commandList.size(); i++) {
				// �ڵڶ��п�ʼ����
				if (i != 0) {
					result.append("<br>");
				}
				result.append("�ظ�[" + commandList.get(i).getName() + "]���Բ鿴" + commandList.get(i).getDescription());
			}
			return result.toString();
		}
		// û����������ָ���ôֱ�Ӱ�ָ��������
		commandList = commandDao.queryCommandList(command, null);
		if (commandList.size() > 0) {
			List<CommandContent> contentList = commandList.get(0).getContentList();
			return contentList.get(new Random().nextInt(contentList.size())).getContent();
		}
		return Iconst.NO_MATCHING_CONTENT;
	}
	
	/**
	 * ͨ��ָ���ѯ�Զ��ظ�����
	 * @param content
	 * @return
	 */
//	public String queryCommandById(String command) {
//		MessageDao messageDao = new MessageDao();
//		List<Message> commandList;
//		// ����ǰ���ָ�����һ��ȫ�����������ȥ��
//		if (command.equals(Iconst.HELP_COMMAND)) {
//			// ����������
//			commandList = messageDao.queryMessageList(null, null);
//			// ���
//			StringBuilder result = new StringBuilder();
//			for (int i=0; i < commandList.size(); i++) {
//				// �ڵڶ��п�ʼ����
//				if (i != 0) {
//					result.append("<br>");
//				}
//				result.append("�ظ�[" + commandList.get(i).getCommand() + "]���Բ鿴" + commandList.get(i).getDescription());
//			}
//			return result.toString();
//		}
//		// û����������ָ���ôֱ�Ӱ�ָ��������
//		commandList = messageDao.queryMessageList(command, null);
//		if (commandList.size() > 0) {
//			// ???
//			return commandList.get(0).getContent();
//		}
//		return Iconst.NO_MATCHING_CONTENT;
//	}
	
}
