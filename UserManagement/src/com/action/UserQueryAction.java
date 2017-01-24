package com.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.entity.User;
import com.opensymphony.xwork2.ActionSupport;
import com.service.UserService;
/**
 * ��ѯ�û���Ϣ�Ŀ�����
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
		// �������û������ request ��Χ��
		ServletActionContext.getRequest().setAttribute("userlist", userlist);
		return SUCCESS; 
	}
	
}
