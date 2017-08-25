package com.revature.beans;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="EVENT_STATUS")
public class EventStatus {

	@Id
	@Column(name="EVENT_STATUS_ID")
	private int eventStatusId;
	
	@Column(name="EVENT_STATUS_DESCRIPTION")
	private String eventStatusDescription;
	
	@OneToMany(mappedBy="eventStatus")
	private Set<Events> events;

	public EventStatus() {
		super();
	}

	public EventStatus(int eventStatusId, String eventStatusDescription, Set<Events> events) {
		super();
		this.eventStatusId = eventStatusId;
		this.eventStatusDescription = eventStatusDescription;
		this.events = events;
	}

	public int getEventStatusId() {
		return eventStatusId;
	}

	public void setEventStatusId(int eventStatusId) {
		this.eventStatusId = eventStatusId;
	}

	public String getEventStatusDescription() {
		return eventStatusDescription;
	}

	public void setEventStatusDescription(String eventStatusDescription) {
		this.eventStatusDescription = eventStatusDescription;
	}

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}
	
	
}