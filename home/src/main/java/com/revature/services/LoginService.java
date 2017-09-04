package com.revature.services;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.revature.beans.Users;
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
			String cleanUsername = Jsoup.clean(user.getUserName(), Whitelist.basic());
			String cleanPassword = Jsoup.clean(user.getPassword(), Whitelist.basic());
			if (cleanUsername.equals(userDAOManager.findByUserName(cleanUsername).getUserName())) {
				Users validUser = userDAOManager.findByUserName(cleanUsername);
				if (Password.checkPassword(cleanPassword, validUser.getPassword())) {
					isLoggedIn = true;
					Users sessionUser = new Users(validUser.getUsersId(), validUser.getFirstName(),
							validUser.getLastName(), validUser.getEvents(), validUser.getOwnedEvents());
					session.setAttribute("loggedInUser", sessionUser);
					this.session = session;
					return sessionUser;
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
