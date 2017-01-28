package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.entity.Admin;

public class AdminDAOImpl extends HibernateDaoSupport implements AdminDAO{
	
	@Override
	public void saveAdmin(Admin admin) {
		this.getHibernateTemplate().save(admin);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findAllAdmin() {
		String hql = "from admin order by admin.username desc";
		return (List<Admin>)this.getHibernateTemplate().find(hql);
	}

	@Override
	public void removeAdmin(Admin admin) {
		this.getHibernateTemplate().delete(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		this.getHibernateTemplate().update(admin);
	}

	@Override
	public Admin findAdminById(Integer id) {
		return (Admin)this.getHibernateTemplate().get(Admin.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Admin loginAdmin(Admin admin) {
		String hql = "from Admin admin where admin.username = ' " + admin.getUsername() + " ' and admin.password = ' " + admin.getPassword() + " ' ";
		List<Admin> admins = (List<Admin>)this.getHibernateTemplate().find(hql);
		
		if (admins.size() > 0) {
			return admins.get(0);
		}
		return null;
	}

}
