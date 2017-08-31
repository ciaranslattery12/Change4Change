package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.revature.beans.EventStatus;
import com.revature.beans.EventType;
import com.revature.beans.Events;
import com.revature.beans.Users;
import com.revature.beans.UsersRole;
import com.revature.services.EventDAOManager;

public class EventDAOTests extends ChangeForChangeTests {

	//private static final Logger logger = Logger.getLogger(EventDAOTests.class);
	private static final String EVENT_COUNT = "select count(event_id) from Events";
	private static EventDAOManager eventDAOManager;

	@BeforeClass
	public static void setup() {
		context = new ClassPathXmlApplicationContext("dao-beans.xml");
	}

	@Ignore
	@Test
	public void createTest() throws ParseException {

		//logger.info("CREATE EVENT TEST");

		// set date in the future
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/09/2017");
		long time = date.getTime();

		eventDAOManager = (EventDAOManager) context.getBean("eventDAO");

		// instantiate custom types
		EventType type = new EventType(1, "WALK");
		EventStatus status = new EventStatus(1, "UPCOMING");
		UsersRole role = new UsersRole(1, "ADMIN");
		Users user = new Users(142, "Patrick", "Muldoon", "patrickM", "password", "patrick@example.com", role);

		// construct event and create
		Events newEvent = new Events(250, new Timestamp(time), "Diabetes Walk", type, user, status);
		Long rowCount = jdbcTemplate.queryForObject(EVENT_COUNT, Long.class);
		eventDAOManager.create(newEvent);
		Long newRowCount = jdbcTemplate.queryForObject(EVENT_COUNT, Long.class);

		assertEquals(++rowCount, newRowCount);
	}

	@Ignore
	@Test
	public void updateTest() throws ParseException {

		eventDAOManager = (EventDAOManager) context.getBean("eventDAO");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/09/2017");
		long time = date.getTime();

		// instantiate custom types
		EventType type = new EventType(1, "WALK");
		EventStatus status = new EventStatus(1, "UPCOMING");
		UsersRole role = new UsersRole(1, "ADMIN");
		Users user = new Users(142, "Patrick", "Muldoon", "patrickM", "password", "patrick@example.com", role);

		Events eventOld = eventDAOManager.findById(165);
		Events eventActual = new Events(165, 250, new Timestamp(time), "DiabeetuZ Walk", type, user, status);
		eventDAOManager.update(eventActual);

		assertEquals(eventActual.getEventId(), eventOld.getEventId());
		assertNotEquals(eventActual.getEventDescription(), eventOld.getEventDescription());
	}

	@Ignore
	@Test
	public void deleteTest() {
		//logger.info("Delete Event");

		eventDAOManager = (EventDAOManager) context.getBean("eventDAO");
		Long rowCount =jdbcTemplate.queryForObject(EVENT_COUNT, Long.class);
		
		Events event = eventDAOManager.findById(173);
		
		eventDAOManager.delete(event);
		
		Long newRowCount = jdbcTemplate.queryForObject(EVENT_COUNT, Long.class);
		assertEquals(--rowCount, newRowCount);
	}

	@Ignore
	@Test
	public void findByIdTest() throws ParseException {
		//logger.info("Find Event by ID");

		eventDAOManager = (EventDAOManager) context.getBean("eventDAO");

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/09/2017");
		long time = date.getTime();

		EventType type = new EventType(1, "WALK");
		EventStatus status = new EventStatus(1, "UPCOMING");
		UsersRole role = new UsersRole(1, "ADMIN");
		Users user = new Users(142, "Patrick", "Muldoon", "patrickM", "password", "patrick@example.com", role);
		Events actualEvent = new Events(165, 250, new Timestamp(time), "Diabetes Walk", type, user, status);
		int actualId = actualEvent.getEventId();

		Events expectedEvents = eventDAOManager.findById(165);
		int expectedId = expectedEvents.getEventId();
		assertEquals(expectedId, actualId);
	}

	@Ignore
	@Test
	public void findAllTest() {
		//logger.info("Find All Events");

		Long count = jdbcTemplate.queryForObject(EVENT_COUNT, Long.class);
		eventDAOManager = (EventDAOManager) context.getBean("eventDAO");
		ArrayList<Events> eventList = (ArrayList<Events>) eventDAOManager.findAll();
		assertEquals(count.intValue(), eventList.size());
	}

}
