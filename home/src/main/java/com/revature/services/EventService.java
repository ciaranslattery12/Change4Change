package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Events;

@Service
public class EventService {

	private EventDAOManager eventDAOManager;
	//private Logger logger;
	
	@Autowired
	public void setEventDAOManager(EventDAOManager eventDAOManager) {
		this.eventDAOManager = eventDAOManager;
	}

	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createEvent(Events event){
		//logger.debug("Event saved: " + event);
		eventDAOManager.create(event);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updateEvent(Events event){
		//logger.debug("Event updated: " + event);
		eventDAOManager.update(event);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void deleteEvent(Events event){
		//logger.debug("Event deleted: " + event);
		eventDAOManager.delete(event);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Events findEvent(int eventId){
		//logger.debug("Find event by: " + eventId);
		return eventDAOManager.findById(eventId);
	}
	
	public List<Events> findAll(){
		//logger.debug("Finding all events");
		List<Events> events = eventDAOManager.findAll();
		System.out.println(events.get(0).toString());
		return events;
	}
}
