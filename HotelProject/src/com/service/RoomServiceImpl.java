package com.service;

import java.util.List;

import com.dao.RoomDAO;
import com.entity.Room;

public class RoomServiceImpl implements RoomService{

	private RoomDAO roomDao;
	
	// gettter && settter
	public RoomDAO getRoomDao() {
		return roomDao;
	}

	public void setRoomDao(RoomDAO roomDao) {
		this.roomDao = roomDao;
	}

	// Override method
	@Override
	public void saveRoom(Room room) {
		this.roomDao.saveRoom(room);
	}

	@Override
	public List<Room> findAllRoom() {
		return this.roomDao.findAllRoom();
	}

	@Override
	public void removeRoom(Room room) {
		this.roomDao.removeRoom(room);
	}

	@Override
	public void updateRoom(Room room) {
		this.roomDao.updateRoom(room);
	}

	@Override
	public Room findRoomById(Integer id) {
		return this.roomDao.findRoomById(id);
	}

}
