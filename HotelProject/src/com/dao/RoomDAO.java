package com.dao;

import java.util.List;

import com.entity.Room;

public interface RoomDAO {

	public void saveRoom(Room room);
	public List<Room> findAllRoom();
	public void removeRoom(Room room);
	public void updateRoom(Room room);
	public Room findRoomById(Integer id);
	
}