package com.service;

import java.util.List;

import com.entity.User;

/**
 * ҵ���߼�����ӿ�
 * @author Kexin_Li
 */
public interface UserService {

	void saveUser(User user);					// ����û�
	User getUser(String name);		        // ���û��������û�
	void deleteUser(int id);						// ɾ���û�
	void updateUser(User user);				// �����û�
	User findUserById(int id);					// �� id �����û�
	List<User> findAll();						        // ����ȫ���û�
	
}
