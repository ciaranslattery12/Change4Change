package com.revature.tests;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.revature.beans.Events;
import com.revature.beans.Photos;
import com.revature.services.EventDAOManager;
import com.revature.services.PhotoDAOManager;

public class PhotoDAOTests extends ChangeForChangeTests {

	//private static final Logger logger = Logger.getLogger(PhotoDAOTests.class);
	private static PhotoDAOManager photoDAOManager;
	private static EventDAOManager eventDAOManager;
	private static Events event;
	private static final String PHOTO_COUNT = "select count(photo_id) from Photos";
	
	@BeforeClass
	public static void initializeTestObjects(){
		photoDAOManager = (PhotoDAOManager) context.getBean("photoDAO");
		eventDAOManager = (EventDAOManager) context.getBean("eventDAO");
		event = eventDAOManager.findAll().get(0);
	}
	
	@Ignore
	@Test
	public void photoDAOTestCreate(){
		//logger.info("Creating photo");
		Photos photo = new Photos(new byte[1], event);
		Long count = jdbcTemplate.queryForObject(PHOTO_COUNT, Long.class);
		photoDAOManager.create(photo);
		Long newCount = jdbcTemplate.queryForObject(PHOTO_COUNT, Long.class);
		assertEquals(++count, newCount);
	}
	
	@Test
	public void photoDAOTestFindById(){
		//logger.info("Finding photo by Id");
		Photos expected = photoDAOManager.findByPhotoId(168);
		Photos actual = new Photos(168, new byte[1], event);
		assertEquals(expected.getPhotoId(), actual.getPhotoId());
		assertEquals(expected.getEvent().getEventId(), 
				actual.getEvent().getEventId());
	}
	
	@Ignore
	@Test
	public void photoDAOTestDelete(){
		//logger.info("Delete photo");
		Photos photo = new Photos(168, new byte[1], event);
		Long count = jdbcTemplate.queryForObject(PHOTO_COUNT, Long.class);
		photoDAOManager.destroy(photo);
		Long newCount = jdbcTemplate.queryForObject(PHOTO_COUNT, Long.class);
		assertEquals(--count, newCount);
	}
}
