package com.revature.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
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

import com.revature.beans.Users;
import com.revature.services.InputValidationService;
import com.revature.services.LoginService;
import com.revature.services.UserService;

@Controller
public class UsersController {
	
	//private static final Logger logger = Logger.getLogger(UsersController.class);
	@Autowired
	private UserService userService;
	
	@Autowired
	private InputValidationService inputValidationService;
	
	@Autowired
	private LoginService loginService;
	
	public void setUserService(UserService userService){
		this.userService = userService;
	}
	
	public void setInputValidationService(InputValidationService inputValidationService) {
		this.inputValidationService = inputValidationService;
	}

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


	/**
	 * 
	 * Create User function
	 *
	 */
	
	@RequestMapping(value="/users", method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> createUser(@Valid @RequestBody Users user){
		//logger.info("Creating New User: " + user);
		Users validUser = inputValidationService.validateInput(user);
		if(inputValidationService.isSignupInputValidated() && !loginService.isLoggedIn()){
		userService.createNewUser(validUser);
		return new ResponseEntity<Users>(validUser, HttpStatus.CREATED);
		}else{
		return new ResponseEntity<Users>(validUser, HttpStatus.BAD_REQUEST);
		}
	}
	
	/**
	 * 
	 * Find User Api, functions available: findUserByUserId(), findUserByUserName(),
	 * 									   findUserByEmail(), findAll()
	 * 
	 */
	
	@RequestMapping(value="/users/findById/{userId}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> findUserById(@PathVariable int userId){
		//logger.info("Finding User by Id number : " + userId);
		Users user = userService.findUserById(userId);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/findByUserName/{userName}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> findUserByUserName(@PathVariable String userName){
		//logger.info("Finding User by userName : " + userName);
		Users user = userService.findUserByUserName(userName);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/findByEmail/{email}", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Users> findUserByEmail(@PathVariable String email){
		//logger.info("Finding User by email : " + email);
		Users user = userService.findUserByEmail(email);
		return new ResponseEntity<Users>(user, HttpStatus.OK);
	}
	
	@RequestMapping(value="/users/findAll", method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<List<Users>> findAllUsers(){
		//logger.info("Finding all Users in the Database");
		if(loginService.isLoggedIn()){
		List<Users> users = userService.findAll();
		return new ResponseEntity<List<Users>>(users, HttpStatus.OK);
		}else{
			return new ResponseEntity<List<Users>>(HttpStatus.UNAUTHORIZED);
		}
	}
	
	/**
	 *
	 * Update User Function, takes in changes to a user and
	 * calls the userService to update the changes made to 
	 * the user object passed in 
	 * 
	 */
	
	//change request to take in an event instead
	@RequestMapping(value="/users", method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Void> updateUser(@Valid @RequestBody Users user){
		//logger.info("Updating User: " + user);
		if(loginService.isLoggedIn()){
		Users updatedUser = userService.findUserById(user.getUsersId());
		updatedUser.setEvents(user.getEvents());
		userService.updateUser(updatedUser);
		HttpSession session = loginService.getSession();
		Users sessionUser = new Users(updatedUser.getUsersId(), updatedUser.getFirstName(), updatedUser.getLastName(),
				updatedUser.getEvents(), updatedUser.getOwnedEvents(), updatedUser.getUserRoleId());
		session.setAttribute("loggedInUser", sessionUser);
		return new ResponseEntity<Void>(HttpStatus.OK);
		}else
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	/**
	 *
	 * Delete User Function, takes in a user and calls
	 * the user delete function in the userService class
	 * 
	 */
	
	@RequestMapping(value="/users", method=RequestMethod.DELETE,
			consumes=MediaType.APPLICATION_JSON_VALUE)
	@Transactional(isolation=Isolation.READ_COMMITTED, propagation=Propagation.REQUIRED, rollbackFor=Exception.class)
	public ResponseEntity<Void> deleteUser(@PathVariable int userId){
		//logger.info("Deleting User with id number: " + userId);
		HttpSession session = loginService.getSession();
		Users validUser = (Users) session.getAttribute("loggedInUser");
		if(validUser.getUserRoleId().getUserRoleId() == 1){
		Users user = new Users();
		user.setUsersId(userId);
		userService.deleteUser(user);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		}else
			return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}
	
	
}
