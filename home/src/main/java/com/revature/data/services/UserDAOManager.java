package com.revature.data.services;

import java.util.List;

import com.revature.beans.Users;

public interface UserDAOManager {
	
	public void create(Users user);
	public void update(Users user);
	public void delete(Users user);
	public Users findById(int userId);
	public List<Users> findAll();
}
