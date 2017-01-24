package com.action;

import java.util.Iterator;
import java.util.List;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

/**
 * �û���¼ Action���û�����û���Ϣ��������ݿ��д��ڸ��û���Ϣ���������¼��
 * @author Kexin_Li
 */
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{

	String username;    // �û���
	String password;    // ����
	String usertype;     // �û�����
	
	private UserService userService;
	
	// getter and setter
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getUsertype() {
		return usertype;
	}
	
	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		String page = "failer";
		
		List<User> list = (List<User>)userService.findAll();
		User u = new User();
		Iterator<User> it = list.iterator();
		// ����û���Ϣ�Ƿ��Ѿ����������ݿ���
		while (it.hasNext()) {
			u = (User)it.next();
			if (username.trim().equals(u.getUsername()) && password.trim().equals(u.getPassword()) && usertype.trim().equals(u.getUsertype())) {
				 page = "success";
			} else {
				page = "failer";
			}
		}
		return page;
	}
	
}
