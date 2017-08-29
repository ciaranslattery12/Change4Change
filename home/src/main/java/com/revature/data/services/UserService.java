package com.revature.data.services;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.revature.beans.Users;

@Service
public class UserService {
	
	private Logger logger;
	private UserDAOManager userDAOManager;

	public UserDAOManager getUserDAOManager() {
		return userDAOManager;
	}

	public void setUserDAOManager(UserDAOManager userDAOManager) {
		this.userDAOManager = userDAOManager;
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void createNewUser(Users user){
		logger.debug("Created User: " + user);
		userDAOManager.create(user);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void updateUser(Users user){
		logger.debug("Update User: " + user);
		userDAOManager.update(user);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public void deleteUser(Users user){
		logger.debug("Deleted User: " + user);
		userDAOManager.delete(user);
	}
	
	@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
	public Users findUserById(int userId){
		logger.debug("Find user " + userId);
		return userDAOManager.findById(userId);
	}
	
	public List<Users> findAll(){
		logger.debug("Finding all users");
		return userDAOManager.findAll();
	}
	
	public Users findUserByUserName(String userName){
		logger.debug("Find User by userName: " + userName);
		return userDAOManager.findByUserName(userName);
	}
	
	public Users findUserByEmail(String email){
		logger.debug("Find User by email: " + email);
		return userDAOManager.findByEmail(email);
	}
}
