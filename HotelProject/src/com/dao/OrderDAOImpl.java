package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.entity.Order;
import com.entity.User;

public class OrderDAOImpl extends HibernateDaoSupport implements OrderDAO{

	@Override
	public void saveOrder(Order order) {
		this.getHibernateTemplate().save(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findAllOrder() {
		String hql = "from Order ord order by ord.orderid desc";
		return(List<Order>) this.getHibernateTemplate().find(hql);
	}

	@Override
	public void removeOrder(Order order) {
		this.getHibernateTemplate().delete(order);
	}

	@Override
	public void updateOrder(Order order) {
		this.getHibernateTemplate().update(order);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> getUserOrder(User user) {
		String hql = "from Order ord where ord.userid = ' " + user.getUserid() + " ' ";
		return (List<Order>)this.getHibernateTemplate().find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Order findOrderByUseridAndRoomid(int userid, int roomid) {
		String hql = "from Order ord where ord.userid = " + userid + "and ord.roomid = " + roomid + "";
		List<Order> orders = (List<Order>)this.getHibernateTemplate().find(hql);
		return orders == null ? null : orders.get(0);
	}

}
