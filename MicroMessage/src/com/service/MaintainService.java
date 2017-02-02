package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.MessageDao;
/**
 * 维护service
 * @author Kexin_Li
 *
 */
public class MaintainService {

	/**
	 * 单条删除
	 * @param id
	 */
	public void deleteOne(String id) {
		MessageDao messageDao = new MessageDao();
		// id 判空
		if (id != null && !"".equals(id)) {
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(String[] ids) {
		MessageDao messageDao = new MessageDao();
		List<Integer> idList = new ArrayList<>();
		// 将 String[] 类型的 ids 转换成 List<Integer>
		for (String id : ids) {
			idList.add(Integer.valueOf(id));
		}
		messageDao.deleteBatch(idList); 
	}
	
}
