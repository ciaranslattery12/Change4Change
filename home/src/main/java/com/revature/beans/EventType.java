package com.revature.beans;

import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="EVENT_TYPE")
public class EventType {

	@Id
	@Column(name="EVENT_TYPE_ID")
	private int eventTypeId;
	
	@Column(name="EVENT_TYPE_DESCRIPTION")
	private String eventTypeDescription;
	
	@OneToMany(mappedBy="eventType")
	private Set<Events> events;

	public EventType() {
		super();
	}

	public EventType(int eventTypeId, String eventTypeDescription, Set<Events> events) {
		super();
		this.eventTypeId = eventTypeId;
		this.eventTypeDescription = eventTypeDescription;
		this.events = events;
	}

	public int getEventTypeId() {
		return eventTypeId;
	}

	public void setEventTypeId(int eventTypeId) {
		this.eventTypeId = eventTypeId;
	}

	public String getEventTypeDescription() {
		return eventTypeDescription;
	}

	public void setEventTypeDescription(String eventTypeDescription) {
		this.eventTypeDescription = eventTypeDescription;
	}

	public Set<Events> getEvents() {
		return events;
	}

	public void setEvents(Set<Events> events) {
		this.events = events;
	}
	
	
}
