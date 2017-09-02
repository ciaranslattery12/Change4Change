package com.revature.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.revature.beans.Users;
import com.revature.beans.UsersRole;
import com.revature.helpers.Password;

@Service
public class LoginService {
	
private UserDAOManager userDAOManager;
	
	private boolean isLoggedIn = false;
	private HttpSession session;
	
	@Autowired
	public void setUserDAOManager(UserDAOManager userDAOManager) {
		this.userDAOManager = userDAOManager;
	}
	
	public Users authenticate(Users user, HttpSession session){
		if (user.getUserName() != null) {
			if (user.getUserName().equals(userDAOManager.findByUserName(user.getUserName()).getUserName())) {
				Users validUser = userDAOManager.findByUserName(user.getUserName());
				if (Password.checkPassword(user.getPassword(), validUser.getPassword())) {
					session.setAttribute("loggedInUser", validUser);
					this.session = session;
					isLoggedIn = true;
					return new Users(validUser.getUsersId(), validUser.getFirstName(), validUser.getLastName(),
							validUser.getUserName(), null, validUser.getEmail(), 
							validUser.getUserRoleId());
				}
			}
		} else {
			return new Users();
		}
		return new Users();
	}
	
	public void logout(HttpSession session){
		session.invalidate();
		isLoggedIn = false;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
	
	
}
