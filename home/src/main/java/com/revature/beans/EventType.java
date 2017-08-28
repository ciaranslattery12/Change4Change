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
	
	public EventType() {
		super();
	}

	public EventType(int eventTypeId, String eventTypeDescription) {
		super();
		this.eventTypeId = eventTypeId;
		this.eventTypeDescription = eventTypeDescription;
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
	
}
