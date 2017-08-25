package com.revature.tests;

import static org.junit.Assert.assertEquals;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.revature.beans.EventStatus;
import com.revature.beans.EventType;
import com.revature.beans.Events;
import com.revature.beans.Users;
import com.revature.data.services.EventDAO;

public class EventDAOTests {

	//private static final Logger log = Logger.getLogger(EventDAOTests.class);
	
	@Test
	public void createTest() throws ParseException{
		
		//log.info("CREATE TEST");
		
		// set date in the future
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = dateFormat.parse("01/09/2017");
		long time = date.getTime();

		// instantiate custom types
		EventDAO dao = new EventDAO();
		//EventType type = new EventType(1, "Diabetes");
		EventStatus status = new EventStatus(1, "UPCOMING", new HashSet<Events>());
		Set<Users> users = new HashSet<Users>();
		
		// construct event and create
		//Events newEvent = new Events(1, 250, new Timestamp(time), "Diabetes Walk", users, type, new Users(), status);
		dao.create(newEvent);
		
		// check it was added
		List<Events> allEvents = dao.findAll();
		assertEquals(newEvent.getEventId(), allEvents.get(0).getEventId());
	}
}
