package com.revature.controllers;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.revature.beans.Users;
import com.revature.services.LoginService;

@Controller
@RequestMapping("/")
public class LoginController {

	@Autowired
	private LoginService loginService;

	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}
	/**
	 * 
	 * Does not forward, needs fixing
	 */
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ResponseEntity<Users> login(@RequestBody Users user, HttpServletRequest req){
		Users newUser = loginService.authenticate(user.getUserName(), user.getPassword());
		System.out.println(newUser.toString());
		if(newUser.getUserName() != null){
			req.getSession().setAttribute("loggedInUser", newUser);
			return new ResponseEntity<Users>(newUser, HttpStatus.OK);
		} else {
			return new ResponseEntity<Users>(HttpStatus.NO_CONTENT);
		}
	}
}
