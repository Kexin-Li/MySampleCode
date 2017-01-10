package com.demo;

public class TestDao {

	public static void main(String[] args) {
		CustomerDao dao = new CustomerDaoImpl();
		Customer c = new Customer();
		c.setName("likexin"); c.setEmail("likexin@gmail.com");
		dao.add(c);
	}
}
