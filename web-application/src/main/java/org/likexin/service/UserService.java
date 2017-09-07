package org.likexin.service;

import java.util.List;

import org.likexin.entity.User;

import jef.common.wrapper.Page;

public interface UserService {

	/**
	 * 添加用户
	 * @param user
	 */
	void addUser(User user);
	
	/**
	 * 删除单个用户
	 * @param str
	 * @return
	 */
	int deleteUser(Integer id);
	
	/**
	 * 批量删除用户
	 * @param idList
	 * @return
	 */
	void deleteBatch(List<Integer> idList);
	
	/**
	 * 更新用户
	 * @param user
	 */
	int updateUser(User user);
	
	/**
     * 查询所有用户
     * @param start
     * @param size
     * @return
     */
    Page<User> findAndPageAll(Integer start, Integer size);
    
    /**
     * 根据 search 查询用户
     * @param search
     * @param start
     * @param size
     * @return
     */
    Page<User> findAndPageByLike(String search, Integer start, Integer size);
    
    /**
     * 根据 username 查询用户
     * @param username
     * @return
     */
    List<User> getUserByUsername(String username);
    
    /**
     * 检查登录信息
     * @param username
     * @param password
     * @return
     */
    boolean checkUserInfo(String username, String password); 
    
}
