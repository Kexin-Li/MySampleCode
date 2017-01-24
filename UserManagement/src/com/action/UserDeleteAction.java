package com.action;

import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

/**
 * 删除用户信息控制器
 * @author Kexin_Li
 */
@SuppressWarnings("serial")
public class UserDeleteAction extends ActionSupport{

	private UserService userService;
	private int id;

	// getter and setter
	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String execute() throws Exception {
		userService.deleteUser(id);
		return SUCCESS;
	}
	
}
