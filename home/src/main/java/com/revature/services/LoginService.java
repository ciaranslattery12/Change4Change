package com.revature.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
			if (username.equals(userDAOManager.findByUserName(username).getUserName())) {
				Users user = userDAOManager.findByUserName(username);
				if (Password.checkPassword(password, user.getPassword())) {
					return new Users(user.getUsersId(), user.getFirstName(), user.getLastName(), user.getUserName(), null, user.getEmail(), new UsersRole(user.getUserRoleId().getUserRoleId()));
				}
			}
		} else {
			return new Users();
		}
		return new Users();
	}
}
