package com.revature.data.services;

import java.util.List;

import com.revature.beans.Events;

public interface EventDAOManager {
	
	public void create(Events event);
	public void update(Events event);
	public void delete(Events event);
	public Events findById(int eventId);
	public List<Events> findAll();
}
