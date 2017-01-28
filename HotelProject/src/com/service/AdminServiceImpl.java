package com.service;

import java.util.List;

import com.dao.AdminDAO;
import com.entity.Admin;

public class AdminServiceImpl implements AdminService{

	private AdminDAO adminDao;
	
	// getter && settter
	public AdminDAO getAdminDao() {
		return adminDao;
	}

	public void setAdminDao(AdminDAO adminDao) {
		this.adminDao = adminDao;
	}

	// Override method
	@Override
	public void saveAdmin(Admin admin) {
		this.adminDao.saveAdmin(admin);
	}

	@Override
	public List<Admin> findAllAdmin() {
		return this.adminDao.findAllAdmin();
	}

	@Override
	public void removeAdmin(Admin admin) {
		this.adminDao.removeAdmin(admin);
	}

	@Override
	public void updateAdmin(Admin admin) {
		this.adminDao.updateAdmin(admin);
	}

	@Override
	public Admin findAdminById(Integer id) {
		return this.adminDao.findAdminById(id);
	}

	@Override
	public Admin loginAdmin(Admin admin) {
		return this.adminDao.loginAdmin(admin);
	}

}
