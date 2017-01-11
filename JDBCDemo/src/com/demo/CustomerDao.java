package com.demo;

import java.util.List;

/**
 * 对Customer类相应操作的接口
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
