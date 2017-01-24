package com.action;

import java.util.Iterator;
import java.util.List;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;

/**
 * 用户登录 Action，用户检查用户信息，如果数据库中存在该用户信息，则允许登录。
 * @author Kexin_Li
 */
@SuppressWarnings("serial")
public class LoginAction extends ActionSupport{

	String username;    // 用户名
	String password;    // 密码
	String usertype;     // 用户类型
	
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
		// 检查用户信息是否已经存在于数据库中
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
