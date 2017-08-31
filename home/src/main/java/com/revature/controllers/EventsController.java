package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.beans.Events;
import com.revature.beans.Users;
import com.revature.services.EventService;

@Controller
public class EventsController {

	//private static final Logger logger = Logger.getLogger(UsersController.class);
	private EventService eventService;
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@RequestMapping(value="/events/create", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Events> create(@Valid @RequestBody Events event){
		//logger.info("Creating New Event: " + event);
		eventService.createEvent(event);
		return new ResponseEntity<Events>(event, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/events/find/{eventId}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Events> findById(@PathVariable Integer eventId){
		//logger.info("Finding Event by Id number : " + eventId);
		Events event = eventService.findEvent(eventId);
		return new ResponseEntity<Events>(event, HttpStatus.OK);
	}
	
	@RequestMapping(value="/events/find", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Events>> findAll(){
		//logger.info("Finding all events");
		return new ResponseEntity<List<Events>>(this.eventService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/events", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Events> update(@RequestBody Events event){
		Events toUpdate = this.eventService.findEvent(event.getEventId());
		eventService.updateEvent(toUpdate);
		Events updated = this.eventService.findEvent(toUpdate.getEventId());
		return new ResponseEntity<Events>(updated, HttpStatus.OK);
	}
	
	/**
	 * DELETE /events HTTPv1.1
	 * id: 200
	 * Cookie: stbsrtbsrthsrt;
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/events", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@RequestHeader Integer id){
		this.eventService.deleteEvent(eventService.findEvent(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
