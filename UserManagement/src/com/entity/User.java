package com.entity;

/**
 * Object/Relational Mapping Class
 * @author Kexin_Li
 */
public class User {

	private int id;
	private String username;
	private String password;
	private String usertype;
	
	// Constructor
	public User() {
	}

	public User(int id, String username, String password, String usertype) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.setUsertype(usertype);
	}

	// Getter and Setter
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	
}
