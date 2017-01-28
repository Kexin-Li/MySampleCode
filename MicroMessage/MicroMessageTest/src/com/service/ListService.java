package com.service;

import java.util.List;

import com.bean.Message;
import com.dao.MessageDao;

/**
 * Service Class
 * @author Kexin_Li
 *
 */
public class ListService {

	public List<Message> queryMessageList(String command, String description) {
		MessageDao messageDao = new MessageDao();
		return messageDao.queryMessageList(command, description);
	}
	
}
