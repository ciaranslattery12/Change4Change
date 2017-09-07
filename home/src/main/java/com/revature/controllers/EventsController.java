package com.revature.controllers;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpSession;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.revature.beans.EventStatus;
import com.revature.beans.EventType;
import com.revature.beans.Events;
import com.revature.beans.Photos;
import com.revature.beans.Users;
import com.revature.services.EventService;
import com.revature.services.InputValidationService;
import com.revature.services.LoginService;
import com.revature.services.PhotoService;

@Controller
public class EventsController {

	//private static final Logger logger = Logger.getLogger(UsersController.class);
	private EventService eventService;
	private InputValidationService inputValidationService;
	private LoginService loginService;
	private PhotoService photoService;
	
	@Autowired
	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}
	
	@Autowired
	public void setInputValidationService(InputValidationService inputValidationService){
		this.inputValidationService = inputValidationService;
	}
	
	@Autowired
	public void setLoginService(LoginService loginService){
		this.loginService = loginService;
	}
	
	@Autowired
	public void setPhotoService(PhotoService photoService){
		this.photoService = photoService;
	}
	
	@RequestMapping(value="/events/create", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Events> create(@Valid @RequestBody Events event){
		//logger.info("Creating New Event: " + event);
		HttpSession session = loginService.getSession();
		Users user = (Users) session.getAttribute("loggedInUser");
		event.setUser(user);
		Events validEvent = inputValidationService.validateInput(event);
		if(user.getUserRoleId().getUserRoleId() == 1){
			if(inputValidationService.isEventInputValidated()){
			inputValidationService.setEventInputValidated(false);
			eventService.createEvent(validEvent);
			return new ResponseEntity<Events>(validEvent, HttpStatus.CREATED);
			}else{
				return new ResponseEntity<Events>(validEvent, HttpStatus.BAD_REQUEST);
			}
		}else{
			return new ResponseEntity<Events>(validEvent, HttpStatus.UNAUTHORIZED);
		}
	}
	
	//get Multipart dependencies and map bean 
	@RequestMapping(value="/events/image", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createImage(@Valid @RequestParam String eventTitle,
			@RequestParam String maxCapacity, @RequestParam String startTime,
			@RequestParam String endTime, @RequestParam String eventDescription,
			@RequestParam String eventTypeId, @RequestParam MultipartFile image) throws NumberFormatException, ParseException{
		Date start = new Date(Long.parseLong(startTime));
		Date end = new Date(Long.parseLong(endTime));
		HttpSession session = loginService.getSession();
		Users user = (Users) session.getAttribute("loggedInUser");
		Events event = new Events(Integer.parseInt(maxCapacity),start, 
				end, eventDescription, new EventType(Integer.parseInt(eventTypeId)),
				user, new EventStatus(1, "UPCOMING"), eventTitle);
		event.setUser(user);
		event.toString();
		Events validEvent = inputValidationService.validateInput(event);
		if(user.getUserRoleId().getUserRoleId() == 1 && inputValidationService.isEventInputValidated()){
			inputValidationService.setEventInputValidated(false);
			byte[] img = null;
			try {
				img = image.getBytes();
			} catch (IOException e) {
				e.printStackTrace();
				return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
			}
			Photos photo = new Photos(img, validEvent);
			if(validEvent.getPhotos() == null){
				Set<Photos> photos = new HashSet<Photos>();
				photos.add(photo);
				validEvent.setPhotos(photos);
			}else{
				validEvent.getPhotos().add(photo);
			}
			eventService.createEvent(validEvent);
			photoService.createNewPhoto(photo);
			return new ResponseEntity<Void>(HttpStatus.CREATED);
		}else{
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
		}
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
		return new ResponseEntity<List<Events>>(this.eventService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/events", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Events> update(@RequestBody Events event){
		if(loginService.isLoggedIn()){
		Events toUpdate = this.eventService.findEvent(event.getEventId());
		eventService.updateEvent(toUpdate);
		Events updated = this.eventService.findEvent(toUpdate.getEventId());
		return new ResponseEntity<Events>(updated, HttpStatus.OK);
		}else
			return new ResponseEntity<Events>(HttpStatus.UNAUTHORIZED);
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
		HttpSession session = loginService.getSession();
		Users user = (Users) session.getAttribute("loggedInUser");
		if(user.getUserRoleId().getUserRoleId() == 1){
		this.eventService.deleteEvent(eventService.findEvent(id));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}else
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
}
