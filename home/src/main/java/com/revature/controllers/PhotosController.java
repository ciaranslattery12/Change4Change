package com.revature.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.revature.beans.Photos;
import com.revature.services.PhotoService;

@Controller
public class PhotosController {

	private PhotoService photoService;

	@Autowired
	public void setPhotoService(PhotoService photoService) {
		this.photoService = photoService;
	}
	
	@RequestMapping(value="/photos", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Photos> createPhoto(@Valid @RequestBody Photos photo){
		//logger.info("Creating New Photo: " + user);
		photoService.createNewPhoto(photo);
		return new ResponseEntity<Photos>(photo, HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/photos/{photoId}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Photos> findUserById(@PathVariable int photoId){
		//logger.info("Finding User by Id number : " + userId);
		Photos photo = photoService.findPhotoById(photoId);
		return new ResponseEntity<Photos>(photo, HttpStatus.OK);
	}
	
	@RequestMapping(value="/photos/{photoId}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable int photoId){
		this.photoService.deletePhoto(photoService.findPhotoById(photoId));
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
