package org.likexin.entity;

import java.util.Date;
import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import jef.codegen.EntityEnhancer;
import jef.database.DbClient;
import jef.database.DbClientBuilder;

public class UserTest {

	static DbClient db = new DbClientBuilder().build();
	
	@BeforeClass
	public static void hello() {
		new EntityEnhancer().enhance("com.hikvision");
	}
	
	@Test
	public void entityTest() throws SQLException {
		db.createTable(User.class);
		
		User user = new User();
		user.setUsername("happy"); user.setPassword("123"); user.setUserEmail("sara@email.com"); user.setUserPhone("13055889933"); user.setCreateTime(new Date());
		db.insert(user);
	}
	
}
