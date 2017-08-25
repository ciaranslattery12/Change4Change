package com.revature.tests;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.beans.Users;
import com.revature.beans.UsersRole;
import com.revature.data.services.UserDAO;

public class UserDAOTest extends ChangeForChangeTests {
	
	private static final Logger logger = Logger.getLogger(UserDAOTest.class);
	
	private static final String USER_COUNT = "select count(users_id) from Users";
	
	@Test
	public void userDAOTestCreate(){
		logger.info("Create User");
		Users user = new Users("Patrick", "Muldoon", "patrickM" , "password", "patrick@example.com",
				new UsersRole(1, "ADMIN"));
		UserDAO userDAO = new UserDAO();
		Long rowCount = jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		userDAO.create(user);
		Long newRowCount = jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		assertEquals(++rowCount, newRowCount);
		}
}
