package com.dao;

import java.util.List;

import com.entity.User;

/**
 * DAO 接口
 * @author Kexin_Li
 */
public interface UserDAO {

	void save(User user);					// 添加用户
	User getUser(String name);		// 按用户名查找用户
	void delete(int id);						// 删除用户
	void update(User user);				// 更新用户
	User findById(int id);					// 按 id 查找用户
	List<User> findAll();						// 查找全部用户
	
}
