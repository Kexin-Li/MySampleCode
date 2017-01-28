package com.entity;
/**
 * Object/Relational Mapping Class---Admin
 * @author Kexin_Li
 */
public class Admin {

	private int adminid;
	private String username;
	private String password;
	
	// getter && setter
	public int getAdminid() {
		return adminid;
	}
	
	public void setAdminid(int adminid) {
		this.adminid = adminid;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
}
