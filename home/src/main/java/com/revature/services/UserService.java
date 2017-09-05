package com.revature.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Users;
import com.revature.beans.UsersRole;
import com.revature.helpers.Password;

@Service
public class UserService {
	
	//private Logger logger;
	private UserDAOManager userDAOManager;
	
	@Autowired
	public void setUserDAOManager(UserDAOManager userDAOManager) {
		this.userDAOManager = userDAOManager;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createNewUser(Users user){
		//logger.debug("Created User: " + user);
		user.setPassword(Password.hashPassword(user.getPassword()));
		user.setUserRoleId(new UsersRole(2, "USER"));
		userDAOManager.create(user);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updateUser(Users user){
		//logger.debug("Update User: " + user);
		userDAOManager.update(user);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void deleteUser(Users user){
		//logger.debug("Deleted User: " + user);
		userDAOManager.delete(user);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Users findUserById(int userId){
		//logger.debug("Find user " + userId);
		return userDAOManager.findById(userId);
	}
	
	public List<Users> findAll(){
		//logger.debug("Finding all users");
		List<Users> user = userDAOManager.findAll();
		return user;
	}
	
	public Users findUserByUserName(String userName){
		//logger.debug("Find User by userName: " + userName);
		return userDAOManager.findByUserName(userName);
	}
	
	public Users findUserByEmail(String email){
		//logger.debug("Find User by email: " + email);
		return userDAOManager.findByEmail(email);
	}
}
