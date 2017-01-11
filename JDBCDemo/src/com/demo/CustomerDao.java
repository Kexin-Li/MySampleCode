package com.demo;

import java.util.List;

/**
 * ��Customer����Ӧ�����Ľӿ�
 * @author Kexin_Li
 */
public interface CustomerDao {
	//CRUD 
	public void add(Customer c);
	public void update(Customer c);
	public void delete(int id);
	public Customer getCustomerById(int id);
	public List<Customer> query();
}
