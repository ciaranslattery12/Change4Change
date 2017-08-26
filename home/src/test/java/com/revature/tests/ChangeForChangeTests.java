package com.revature.tests;

import static org.junit.Assert.*;

import javax.naming.Context;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;

public class ChangeForChangeTests {

	protected static AbstractApplicationContext context;
	
	@Autowired
	protected JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean(JdbcTemplate.class);
	
	
	@BeforeClass
	public static void initialize(){
		context = new ClassPathXmlApplicationContext("dao-beans.xml");
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}
	
	@Test
	public void testJdbcTemplate() {
		assertNotNull(jdbcTemplate);
	}

}
