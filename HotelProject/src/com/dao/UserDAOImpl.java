package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.entity.User;

public class UserDAOImpl extends HibernateDaoSupport implements UserDAO{

	@Override
	public void saveUser(User user) {
		this.getHibernateTemplate().save(user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> findAllUsers() {
		String hql = "from User user order by user.userid desc";
		return (List<User>)this.getHibernateTemplate().find(hql);
	}

	@Override
	public void removeUser(User user) {
		this.getHibernateTemplate().delete(user);
	}

	@Override
	public void updateUser(User user) {
		this.getHibernateTemplate().update(user);
	}

	@Override
	public User findUserById(Integer id) {
		User user = this.getHibernateTemplate().get(User.class, id);
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User loginUser(User user) {
		String hql = "from User user where user.username = ' " + user.getUsername() + " ' and user.password =  ' " + user.getPassword() + " ' ";
		List<User> users = this.getHibernateTemplate().find(hql);
		
		if (users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

}
