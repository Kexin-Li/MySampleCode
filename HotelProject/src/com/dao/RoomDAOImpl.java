package com.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.entity.Room;

public class RoomDAOImpl extends HibernateDaoSupport implements RoomDAO{

	@Override
	public void saveRoom(Room room) {
		this.getHibernateTemplate().save(room);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Room> findAllRoom() {
		String hql = "from Room room order by room.roomid desc";
		return (List<Room>)this.getHibernateTemplate().find(hql);
	}

	@Override
	public void removeRoom(Room room) {
		this.getHibernateTemplate().delete(room);
	}

	@Override
	public void updateRoom(Room room) {
		this.getHibernateTemplate().update(room);
	}

	@Override
	public Room findRoomById(Integer id) {
		return (Room)this.getHibernateTemplate().get(Room.class, id);
	}

}
