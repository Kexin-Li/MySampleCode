package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;
/**
 * 查询用户信息的控制器
 * @author Kexin_Li
 *
 */
@SuppressWarnings("serial")
public class UserQueryAction extends ActionSupport{

	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public String execute() throws Exception {
		List<User> userlist = userService.findAll();
		// 将所有用户存放在 request 范围内
		ServletActionContext.getRequest().setAttribute("userlist", userlist);
		return SUCCESS; 
	}
	
}
