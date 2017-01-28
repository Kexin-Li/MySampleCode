package com.action.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.entity.Order;
import com.entity.Room;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.OrderService;
import com.service.RoomService;

@SuppressWarnings("serial")
public class ListActionOrder extends ActionSupport{

	private Order order;
	private Room room;
	private OrderService orderService;
	private RoomService roomService;
	private String message;
	
	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	public RoomService getRoomService() {
		return roomService;
	}

	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String execute() throws Exception {
		List<Order> orders = this.orderService.findAllOrder();
		List<Room> rooms =  new ArrayList<>();
		
		if (orders.size() > 0) {
			for (Order order : orders) {
				rooms.add((Room)this.roomService.findRoomById(order.getRoomid()));
			}
		}
		Map<String, List<Room>> requestList = (Map<String, List<Room>>) ActionContext.getContext().get("request");
		requestList.put("listAllRoom", rooms);
		return SUCCESS;
	}
 
}
