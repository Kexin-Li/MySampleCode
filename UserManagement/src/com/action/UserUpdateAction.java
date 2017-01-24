package com.action;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

/**
 * 更新用户信息控制器
 * @author Kexin_Li
 */
@SuppressWarnings("serial")
public class UserUpdateAction extends ActionSupport{

	private UserService userService;
	private User user;
	
	// getter and setter
	public UserService getUserService() {
		return userService;
	}
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String execute() throws Exception {
		if (userService.findUserById(user.getId()) != null) {
			setUser(user);
			userService.updateUser(user);
			return SUCCESS;
		}
		addActionMessage(getText("error.message.not.exist"));
		return INPUT;
	}
	
}
