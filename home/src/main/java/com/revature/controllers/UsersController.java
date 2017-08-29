package com.revature.controllers;

import java.util.List;

import javax.validation.Valid;

import org.apache.log4j.Logger;
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

import com.revature.beans.Users;
import com.revature.data.services.UserService;

@Controller
public class UsersController {
	
	private static final Logger logger = Logger.getLogger(UsersController.class);
	private UserService userService;
	
	@Autowired
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	/**
	 * 
	 * Create User function
	 *
	 */
	
	@RequestMapping(value="admin/users/create", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user){
		logger.info("Creating New User: " + user);
		userService.createNewUser(user);
		return new ResponseEntity<Users>(user, HttpStatus.CREATED);
	}
	
	/**
	 * 
	 * Find User Api, functions available: findUserByUserId(), findUserByUserName(),
	 * 									   findUserByEmail(), findAll()
	 * 
	 */
	
	@RequestMapping(value="admin/users/findById", method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> findUserById(@PathVariable int userId){
		logger.info("Finding User by Id number : " + userId);
		Users user = userService.findUserById(userId);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="admin/users/findByUserName", method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> findUserByUserName(@PathVariable String userName){
		logger.info("Finding User by userName : " + userName);
		Users user = userService.findUserByUserName(userName);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="admin/users/findByEmail", method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> findUserByEmail(@PathVariable String email){
		logger.info("Finding User by email : " + email);
		Users user = userService.findUserByEmail(email);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="admin/users/findAll", method=RequestMethod.GET,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<List<Users>> findAllUsers(){
		logger.info("Finding all Users in the Database");
		List<Users> users = userService.findAll();
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
	}
	
	/**
	 *
	 * Update User Function, takes in changes to a user and
	 * calls the userService to update the changes made to 
	 * the user object passed in 
	 * 
	 */
	
	@RequestMapping(value="admin/users/findAll", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Void> updateUser(@Valid @RequestBody Users user){
		logger.info("Updating User: " + user);
		userService.updateUser(user);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	/**
	 *
	 * Delete User Function, takes in a user and calls
	 * the user delete function in the userService class
	 * 
	 */
	
	@RequestMapping(value="admin/users/deleteUser", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Void> deleteUser(@PathVariable int userId){
		logger.info("Deleting User with id number: " + userId);
		Users user = new Users();
		user.setUsersId(userId);
		userService.deleteUser(user);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
