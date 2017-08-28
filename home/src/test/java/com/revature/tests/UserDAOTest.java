package com.revature.tests;



import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.beans.Users;
import com.revature.beans.UsersRole;
import com.revature.data.services.UserDAOManager;


public class UserDAOTest extends ChangeForChangeTests {
	
	private static final Logger logger = Logger.getLogger(UserDAOTest.class);
	private static final String USER_COUNT = "select count(users_id) from Users";
	private static final String USER_ID_Email = "select users_id from Users where email = 'patrick@example.com'";
	private static final String USER_ID_USERNAME = "select users_id from Users where username = 'patrickM'";
	
	private static UserDAOManager userDAOManager;
	
	@Ignore
	@Test
	public void userDAOTestCreate(){
		userDAOManager = (UserDAOManager) context.getBean("userDAO");
		UsersRole role = new UsersRole(1, "ADMIN");
		logger.info("got userDAOManager");
		logger.info("Create User");
		Users user = new Users("Patrick", "Muldoon", "patrickM" , "password", "patrick@example.com", 
				"Just a trial Bio", role);
		Long rowCount = jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		userDAOManager.create(user);
		Long newRowCount = jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		++rowCount;
		assertEquals(rowCount, newRowCount);
	}
	
	@Test
	public void userDAOTestRetrieveById(){
		logger.info("Retrieve user by ID");
		userDAOManager = (UserDAOManager) context.getBean("userDAO");
		Users expected = userDAOManager.findById(142);
		UsersRole role = new UsersRole(1, "ADMIN");
		Users actual = new Users("Patrick", "Muldoon", "patrickM" , "password", "patrick@example.com", 
				"Just a trial Bio", role);
		assertEquals(expected.getEmail(), actual.getEmail());
	}
	
	@Test
	public void userDAOTestRetriveByEmail(){
		logger.info("Retrieve User by Email");
		userDAOManager = (UserDAOManager) context.getBean("userDAO");
		Users user = userDAOManager.findByEmail("patrick@example.com");
		Long actualIdValue = jdbcTemplate.queryForObject(USER_ID_Email, Long.class);
		assertEquals(user.getUsersId(), actualIdValue.intValue());
	}
	
	@Test
	public void userDAOTestRetrieveByUserName(){
		logger.info("Retrieve User by UserName");
		userDAOManager = (UserDAOManager) context.getBean("userDAO");
		Users user = userDAOManager.findByUserName("patrickM");
		Long actualIdValue = jdbcTemplate.queryForObject(USER_ID_USERNAME, Long.class);
		assertEquals(user.getUsersId(), actualIdValue.intValue());
	}
	
	@Test
	public void userDAOTestRetrieveAll(){
		logger.info("Getting all Users");
		Long count = jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		userDAOManager = (UserDAOManager) context.getBean("userDAO");
		ArrayList<Users> usersList = (ArrayList<Users>) userDAOManager.findAll();
		assertEquals(count.intValue(), usersList.size());
	}
	
	@Ignore
	@Test
	public void userDAOTestDelete(){
		logger.info("Delete User");
		userDAOManager = (UserDAOManager) context.getBean("userDAO");
		Long rowCount =jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		UsersRole role = new UsersRole(1, "ADMIN");
		Users user = new Users("Patrick", "Muldoon", "patrickM" , "password", "patrick@example.com", 
				"Just a trial Bio", role);
		user.setUsersId(141);
		userDAOManager.delete(user);
		Long newRowCount = jdbcTemplate.queryForObject(USER_COUNT, Long.class);
		assertEquals(--rowCount, newRowCount);
	}
}
