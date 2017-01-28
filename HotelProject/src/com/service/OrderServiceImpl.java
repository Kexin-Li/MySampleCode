package com.service;

import java.util.List;

import com.dao.OrderDAO;
import com.entity.Order;
import com.entity.User;

public class OrderServiceImpl implements OrderService{
	
	private OrderDAO orderDao;

	// gettter && setter
	public OrderDAO getOrderDao() {
		return orderDao;
	}

	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}

	// Override method
	@Override
	public void saveOrder(Order order) {
		this.orderDao.saveOrder(order);
	}

	@Override
	public List<Order> findAllOrder() {
		return this.orderDao.findAllOrder();
	}

	@Override
	public void removeOrder(Order order) {
		this.orderDao.removeOrder(order);
	}

	@Override
	public void updateOrder(Order order) {
		this.orderDao.updateOrder(order);
	}

	@Override
	public List<Order> getUserOrder(User user) {
		return this.orderDao.getUserOrder(user);
	}

	@Override
	public Order findOrderByUseridAndRoomid(int userid, int roomid) {
		return this.orderDao.findOrderByUseridAndRoomid(userid, roomid);
	}

}
