package com.action.order;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.entity.Order;
import com.entity.Room;
import com.entity.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.OrderService;
import com.service.RoomService;

@SuppressWarnings("serial")
public class SaveOrderAction extends ActionSupport{

	private Order order;
	private User user;
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
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
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
		Map<String, List<Room>> request = (Map<String, List<Room>>) ActionContext.getContext().get("request");
		request.put("listRoom", this.roomService.findAllRoom());
		return super.execute();
	}
	
	// add room
	public String add() {
		Room roomUser = this.roomService.findRoomById(room.getRoomid());
		// judge
		if (roomUser.getStatus() == 0) {
			HttpServletRequest request = ServletActionContext.getRequest();
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			order.setUserid(user.getUserid());
			
			// order
			this.orderService.saveOrder(order);
			roomUser.setStatus(1);
			this.roomService.updateRoom(roomUser);
			return "addSuc";
		} else {
			message = "该房间已经有客人入住了";
			return "addFail";
		}
	}
	
}
