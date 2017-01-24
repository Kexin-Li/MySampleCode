package com.service;

import java.util.List;

import com.dao.UserDAO;
import com.entity.User;

/**
 * 业务逻辑组件的实现类
 * @author Kexin_Li
 */
public class UserServiceImpl implements UserService{

	private UserDAO userDAO;
	
	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	// it was == , I think its wrong.
	// but truth is ==, I was wrong!
	@Override
	public void saveUser(User user) {
		if (userDAO.findById(user.getId()) == null) {
			userDAO.save(user);
		}
	}

	@Override
	public User getUser(String name) {
		return userDAO.getUser(name);
	}

	@Override
	public void deleteUser(int id) {
		if (userDAO.findById(id) != null) {
			userDAO.delete(id);
		}
	}

	@Override
	public void updateUser(User user) {
		if (userDAO.findById(user.getId()) != null) {
			userDAO.update(user);
		}
	}

	@Override
	public User findUserById(int id) {
		return userDAO.findById(id);
	}

	@Override
	public List<User> findAll() {
		return userDAO.findAll();
	}

}
