package com.action;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

/**
 * 添加用户信息控制器
 * @author Kexin_Li
 */
@SuppressWarnings("serial")
public class UserAction extends ActionSupport{

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
		User u = new User();
	    // set pramater
		u.setUsername(user.getUsername());
	    u.setPassword(user.getPassword());
	    u.setUsertype(user.getUsertype());
	    userService.saveUser(u);
		return SUCCESS;
	}
	
}
