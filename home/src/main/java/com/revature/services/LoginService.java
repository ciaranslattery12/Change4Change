package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import com.revature.beans.Users;
import com.revature.beans.UsersRole;
import com.revature.helpers.Password;

@Service
public class LoginService {
	
private UserDAOManager userDAOManager;
	
	private boolean authenticated = false;

	@Autowired
	public void setUserDAOManager(UserDAOManager userDAOManager) {
		this.userDAOManager = userDAOManager;
	}
	
	public Users authenticate(String username, String password){
		if (username != null) {
			String cleanUsername = Jsoup.clean(username, Whitelist.basic());
			String cleanPassword = Jsoup.clean(password, Whitelist.basic());
			if (cleanUsername.equals(userDAOManager.findByUserName(cleanUsername).getUserName())) {
				Users user = userDAOManager.findByUserName(cleanUsername);
				if (Password.checkPassword(cleanPassword, user.getPassword())) {
					return new Users(user.getUsersId(), user.getFirstName(), user.getLastName(), user.getUserName(), null, user.getEmail(), new UsersRole(user.getUserRoleId().getUserRoleId()));
				}
			}
		} else {
			return new Users();
		}
		return new Users();
	}
}
