package com.demo;

/**
 * UserTbl表对应的User类
 * @author Kexin_Li
 */
public class User {

	private int id;
	private String name;
	
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
	 
	@Override
	public String toString() {
		return id + ": " + name;
	}
}
