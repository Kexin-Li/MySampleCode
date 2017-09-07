package org.likexin.service.impl;

import java.util.List;

import org.easyframe.enterprise.spring.CommonDao;
import org.likexin.entity.User;
import org.likexin.enums.UserStateEnum;
import org.likexin.exception.UserException;
import org.likexin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jef.common.wrapper.Page;
import jef.database.QB;
import jef.database.Condition.Operator;
import jef.database.query.Selects;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private CommonDao commonDao;
	
	@Override
	public void addUser(User user) { // TODO 添加判断添加的用户已存在的条件
		if (user != null) {
			commonDao.insert(user);
		}
	}

	@Override
	public int deleteUser(Integer id) {
		User user = new User();
		user.setUserId(id);
		int result = commonDao.remove(user);
		return result;
	}

	@Override
	public void deleteBatch(List<Integer> idList) {
		for (int i = 0; i < idList.size(); i++) {
			deleteUser(idList.get(i));
		}
	}

	@Override
	public int updateUser(User user) { // TODO 添加判断更新的用户已存在的条件
		if (user != null) {
			return commonDao.update(user);
		}
		return 0;
	}

	@Override
	public Page<User> findAndPageAll(Integer start, Integer size) {
		User user = new User();
		user.getQuery().orderByDesc(User.Field.userId);
		Selects selects = QB.selectFrom(user.getQuery());
		selects.column(User.Field.userId);
		selects.column(User.Field.username);
		selects.column(User.Field.userPhone);
		selects.column(User.Field.userEmail);
		selects.column(User.Field.createTime);
		return commonDao.findAndPage(user, start, size);
	}

	@Override
	public Page<User> findAndPageByLike(String search, Integer start, Integer size) {
		User user = new User();
		user.getQuery().addCondition(User.Field.username, Operator.MATCH_ANY, search);
		Page<User> page = commonDao.findAndPage(user, start, size);
		return page;
	}

	@Override
	public List<User> getUserByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		List<User> results = commonDao.find(user);
		return results;
	}
	
	@Override
	public boolean checkUserInfo(String username, String password) {
		User user = new User();
		user.setUsername(username); user.setPassword(password);
		User n_user = commonDao.load(user);
		if (n_user == null) { // 用户不存在
			throw new UserException(UserStateEnum.USER_NOT_EXIT);
		} else { // 密码错误
			if (!n_user.getPassword().equals(password)) {
				return false;
			} else {
				return true;
			}
		}
	}

}
