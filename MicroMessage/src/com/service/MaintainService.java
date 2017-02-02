package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.MessageDao;
/**
 * ά��service
 * @author Kexin_Li
 *
 */
public class MaintainService {

	/**
	 * ����ɾ��
	 * @param id
	 */
	public void deleteOne(String id) {
		MessageDao messageDao = new MessageDao();
		// id �п�
		if (id != null && !"".equals(id)) {
			messageDao.deleteOne(Integer.valueOf(id));
		}
	}
	
	/**
	 * ����ɾ��
	 * @param ids
	 */
	public void deleteBatch(String[] ids) {
		MessageDao messageDao = new MessageDao();
		List<Integer> idList = new ArrayList<>();
		// �� String[] ���͵� ids ת���� List<Integer>
		for (String id : ids) {
			idList.add(Integer.valueOf(id));
		}
		messageDao.deleteBatch(idList); 
	}
	
}
