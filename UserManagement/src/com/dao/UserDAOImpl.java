package com.dao;

import java.util.List;
import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import com.entity.User;

/**
 * DAO 接口的实现类
 * @author Kexin_Li
 */
public class UserDAOImpl extends HibernateDaoSupport implements UserDAO{

	@Override
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User getUser(String name) {
		String hsql = "from User u where u.name = ' "+name+" ' ";
		User result = (User) ((Query) this.getHibernateTemplate().find(hsql)).uniqueResult();
		return result;
	}

	@Override
	public void delete(int id) {
		this.getHibernateTemplate().delete(findById(id));
	}

	@Override
	public void update(User user) {
		this.getHibernateTemplate().update(user);
	}

	@Override
	public User findById(int id) {
		User user = this.getHibernateTemplate().get(User.class, id);
		return user;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		String queryString = "from User";
		List<User> list = this.getHibernateTemplate().find(queryString);
		return list;
	}

}
