package com.service;

import java.util.List;

import com.entity.Order;
import com.entity.User;

public interface OrderService {

	public void saveOrder(Order order);
	public List<Order> findAllOrder();
	public void removeOrder(Order order);
	public void updateOrder(Order order);
	public List<Order> getUserOrder(User user);
	public Order findOrderByUseridAndRoomid(int userid, int roomid);
	
}
