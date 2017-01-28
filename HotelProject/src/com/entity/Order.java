package com.entity;
/**
 * Object/Relational Mapping Class---Order
 * @author Kexin_Li
 */
public class Order {

	private int userid;
	private int roomid;
	private int orderid;
	
	// getter && setter
	public int getUserid() {
		return userid;
	}
	
	public void setUserid(int userid) {
		this.userid = userid;
	}
	
	public int getRoomid() {
		return roomid;
	}
	
	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}
	
	public int getOrderid() {
		return orderid;
	}
	
	public void setOrderid(int orderid) {
		this.orderid = orderid;
	}
	
}
