package com.dao;

import java.util.List;

import com.entity.User;

/**
 * DAO �ӿ�
 * @author Kexin_Li
 */
public interface UserDAO {

	void save(User user);					// ����û�
	User getUser(String name);		// ���û��������û�
	void delete(int id);						// ɾ���û�
	void update(User user);				// �����û�
	User findById(int id);					// �� id �����û�
	List<User> findAll();						// ����ȫ���û�
	
}
