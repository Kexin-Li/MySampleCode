package com.dao;

import java.util.List;
import java.util.Map;

import com.bean.Message;

/**
 * 与Message.xml文件对应的接口
 * @author Kexin_Li
 *
 */
public interface IMessage {

	/**
	 * queryMessageList
	 * @param message
	 * @return
	 */
//	public List<Message> queryMessageList(Message message);
	public List<Message> queryMessageList(Map<String, Object> parameter);
	
	/**
	 * count
	 * @param message
	 * @return
	 */
	public int count(Message message);
	
	/**
	 * queryMessageListByPage
	 * @param command
	 * @param description
	 * @param page
	 * @return
	 */
	public List<Message> queryMessageListByPage(Map<String, Object> parameter);
	
}
