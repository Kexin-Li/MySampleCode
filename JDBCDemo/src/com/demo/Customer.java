package com.demo;

/**
 * CustomerTbl表对应的Customer类
 * @author Kexin_Li
 */
public class Customer {

	private int id;
	private String name;
	private String email;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return id + ", " + name + ", " + email;
	}
}
