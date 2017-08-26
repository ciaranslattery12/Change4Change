package com.revature.tests;



import static org.junit.Assert.assertEquals;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import com.revature.beans.Users;
import com.revature.beans.UsersRole;
import com.revature.data.services.UserDAOManager;

public class UserDAOTest extends ChangeForChangeTests {
	
	private static final Logger logger = Logger.getLogger(UserDAOTest.class);
	private static final String USER_COUNT = "select count(users_id) from Users";
	private static UserDAOManager userDAOManager;
	
	@Test
	public void userDAOTestCreate(){
		System.out.println(System.getenv("DB_URL"));
		userDAOManager = (UserDAOManager) context.getBean("userDAO");
		System.out.println("here");
		UsersRole role = new UsersRole(1, "ADMIN");
		logger.info("got userDAOManager");
		jdbcTemplate = (JdbcTemplate) context.getBean(JdbcTemplate.class);
		logger.info("Create User");
		Users user = new Users("Patrick", "Muldoon", "patrickM" , "password", "patrick@example.com", 
				"Just a trial Bio", role);
		System.out.println("made a user");
		Long rowCount = jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		System.out.println(rowCount);
		userDAOManager.create(user);
		System.out.println("user created");
		Long newRowCount = jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		System.out.println(newRowCount);
		++rowCount;
		assertEquals(rowCount, newRowCount);
	}
}
