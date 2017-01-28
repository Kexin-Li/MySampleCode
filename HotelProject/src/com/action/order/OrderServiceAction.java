package com.action.order;

import java.util.ArrayList;
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
public class OrderServiceAction<K, V> extends ActionSupport{

	private Room room;
	private OrderService orderService;
	private RoomService roomService;
	private String message;
	
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
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		List<Order> orders = this.orderService.getUserOrder(user);
		List<Room> rooms = new ArrayList<>();
		if (orders.size() > 0) {
			for (Order order : orders) {
				rooms.add(this.roomService.findRoomById(order.getRoomid()));
			}
		}
		Map<String, List<Room>> requestList = (Map<String, List<Room>>) ActionContext.getContext().get("request");
		requestList.put("listUserRoom", rooms);
		return SUCCESS; 
	}
	
	// delete
	public String delete() {
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		// delete
		Order order = this.orderService.findOrderByUseridAndRoomid(user.getUserid(), room.getRoomid());
		this.orderService.removeOrder(order);
		// set room status to 0
		Room roomUser = this.roomService.findRoomById(room.getRoomid());
		this.roomService.updateRoom(roomUser); 
		message = "退订房间成功";
		return "delSuc";
	}
}
