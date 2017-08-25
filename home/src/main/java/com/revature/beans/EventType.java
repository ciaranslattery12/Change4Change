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
}
