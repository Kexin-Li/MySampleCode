package com.demo;

/**
 * Dao模式测试类类
 * @author Kexin_Li
 */
public class TestDao {

	public static void main(String[] args) {
		CustomerDao dao = new CustomerDaoImpl();
		Customer c = new Customer();
		c.setName("likexin"); c.setEmail("likexin@gmail.com");
		dao.add(c);
	}
}
