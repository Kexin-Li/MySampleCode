package com.service;

import java.util.List;

import com.dao.UserDAO;
import com.entity.User;

public class UserServiceImpl implements UserService{

	private UserDAO userDao;
	
	// getter && setter
	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}

	// Override method
	@Override
	public void saveUser(User user) {
		this.userDao.saveUser(user);
	}

	@Override
	public List<User> findAllUsers() {
		return this.userDao.findAllUsers();
	}

	@Override
	public void removeUser(User user) {
		this.userDao.removeUser(user);
	}

	@Override
	public void updateUser(User user) {
		this.userDao.updateUser(user);
	}

	@Override
	public User findUserById(Integer id) {
		return this.userDao.findUserById(id);
	}

	@Override
	public User loginUser(User user) {
		return this.userDao.loginUser(user);
	}

}
